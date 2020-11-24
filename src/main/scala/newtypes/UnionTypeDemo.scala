package newtypes

trait UtCommon {
  val common: String
}

class UT1(in:Int) extends UtCommon{
  val ut1field = in
  val common = "class1"
}

class UT2(in: String) extends UtCommon{
  val ut2field = in
  val common = "class2"
}

class UnionTypeDemo {
  
  def doSomething(ut: (UT1 | UT2) & UtCommon /*this does nothing!!!!*/ ) = {
    //ut.common cannot access
    ut match {
      case ut:UT1 =>
        println(s"====${ut.ut1field},${ut.common}")
      case ut:UT2 =>
        println(s"====${ut.ut2field},${ut.common}")
    }
  }

  def doSomethingonCommon(ut: UtCommon): Unit = {
    ut.common
  }

  val ut1 = UT1(1)
  val ut2 = UT2("A")

  doSomething(ut1)
  doSomething(ut2)
  
}
