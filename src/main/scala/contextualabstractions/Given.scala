package contextualabstractions

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.Base64
import scala.concurrent.ExecutionContext

class Given {

  trait Ord[T] {
    def compare(x: T, y: T): Int
    extension (x: T) def < (y: T) = compare(x, y) < 0
    extension (x: T) def > (y: T) = compare(x, y) > 0
  }

  given listOrd[T](using ord: Ord[T]) as Ord[List[T]] {
    def compare(xs: List[T], ys: List[T]): Int = (xs, ys) match
      case (Nil, Nil) => 0
      case (Nil, _) => -1
      case (_, Nil) => +1
      case (x :: xs1, y :: ys1) =>
        val fst = ord.compare(x, y)
        if (fst != 0) fst else compare(xs1, ys1)
  }
  

  val l1 = List(1,2,3)
  val l2 = List(1,2,4)
  
  //println("l1>l2="+ (l1>l2)) // does not compile (/* missing */summon[Given.this.Ord[? >: Int]]).extension_>()

  given intOrd as Ord[Int] {
    def compare(x: Int, y: Int) =
      if (x < y) -1 else if (x > y) +1 else 0
  }
  
  println("l1>l2="+ (l1>l2))


  case class ValueContainer($value:Int)

  val lv1 = List(1,2,3).map(ValueContainer(_))
  val lv2 = List(1,2,4).map(ValueContainer(_))

  //better use named given instances!!! see above
  given Ord[ValueContainer] {
    def compare(x: ValueContainer, y: ValueContainer) =
      if (x.$value < y.$value) -1 else if (x.$value > y.$value) +1 else 0
  }
  
  println("lv1<l2="+ (lv1<lv2))

  //given alias -  given instance that is equal to some expression
  given myGlobal as ExecutionContext = ExecutionContext.global

  
  
  given GivenOps.Config = GivenOps.Config("MD5")    //anonymous
  import GivenOps.configurableFoo                   //import named 
  println("abc -> md5 = "+ GivenOps.FooBar())
}

object GivenOps {
  //An alias given can have type parameters and context parameters just like any other given,
  case class Config($value:String)
  given configurableFoo (using config: Config) as MessageDigest = MessageDigest.getInstance(config.$value)

  class FooBar(using configurableFoo: MessageDigest) {
    override def toString = {
      val md5Bytes = configurableFoo.digest("abc".getBytes())
      md5Bytes.map(b => f"$b%02x").mkString
    } 
  }
}
