package contextualabstractions

class CaOverview {

  case class ValueContainer($value:String)

  {

   //is this conditional implicit value? do I understand this concept?
    implicit def i1(implicit x: ValueContainer): List[String] = List(x.$value)

    class Zzzz(implicit in: List[String]) {
      println(in.mkString)
    }

    //new Zzzz
    //no implicit argument of type List[String] was found for parameter in of constructor Zzzz in class Zzzz. I found: i1(/* missing */summon[CaOverview.this.ValueContainer]) But no implicit values were found that match type CaOverview.this.ValueContainer.

    implicit val vc = ValueContainer("abc1")
    new Zzzz
  }

  {
    //implicit conversion
    import scala.language.implicitConversions
    implicit def i2(x: ValueContainer): List[String] = List(x.$value)
    val vc = ValueContainer("abc2")
    class ExpectsImplicit(in: List[String]) {
      println(in.mkString)
    }
    ExpectsImplicit(vc)
  }

}
