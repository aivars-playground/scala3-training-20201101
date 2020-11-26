package contextualabstractions

trait IntOps:
  extension (i: Int) def isZero: Boolean = i == 0

  extension (i: Int) def safeMod(x: Int): Option[Int] =
  // extension method defined in same scope IntOps
    if x.isZero then None
    else Some(i % x)

object IntOpsEx extends IntOps :
  extension (i: Int) def safeDiv(x: Int): Option[Int] =
  // extension method brought into scope via inheritance from IntOps
    if x.isZero then None
    else Some(i / x)

trait SafeDiv:

  import IntOpsEx._ // brings safeDiv and safeMod into scope

  extension (i: Int) def divide(d: Int): Option[(Int, Int)] =
  // extension methods imported and thus in scope
    (i.safeDiv(d), i.safeMod(d)) match
      case (Some(d), Some(r)) => Some((d, r))
      case _ => None

object CircleOps:
  extension (c: Circle)  //multiple deffinitions will follow
    def circumference: Double = c.radius * math.Pi * 2
    def volume: Double = c.radius * c.radius * math.Pi

case class Circle(x: Double, y: Double, radius: Double)

class ExtensionMethods:

  import IntOpsEx._

  val i: Int = 6
  val j: Int = 2
  println("i/j=" + i.safeDiv(j))

  import CircleOps.{circumference, volume}

  val c1 = Circle(1, 2, 3)
  println(s"crc:$c1, cf:${c1.circumference} vol:${c1.volume}")
