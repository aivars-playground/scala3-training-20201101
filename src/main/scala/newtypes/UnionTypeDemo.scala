package newtypes

trait UtCommon {
  val common: String
}

class UT1(in:Int) extends UtCommon{
  val ut1field = in
  val common = "classUT1"
}

class UT2(in: String) extends UtCommon{
  val ut2field = in
  val common = "classUT2"
}

class UnionTypeDemo {

  def doSomething(ut: (UT1 | UT2) & UtCommon /*this does nothing!!!!*/ ) = {
    //ut.common is not accessible
    ut match {
      case ut:UT1 =>
        println(s"====${ut.ut1field},${ut.common}")
      case ut:UT2 =>
        println(s"====${ut.ut2field},${ut.common}")
    }
  }

  val ut1 = UT1(1)
  val ut2 = UT2("A")

  val either: UT1 | UT2 = ut1
  doSomething(either)
  
  doSomething(ut2)

  //val typeHint: Any = if (true) ut1 else ut2 //why not Object & Product 
}
