package newtypes

class MatchTypes {

  type Elem[X] = X match {
    case String => Char
    case Array[t] => t
    }


  type LeafElem[X] = X match {
    case String => Char
    case Array[t] => LeafElem[t]
    case AnyVal => X
    }

  def leafElem[X](x: X): LeafElem[X] = x match {
    case x: String      => x.charAt(0)
    case x: Array[t]    => leafElem(x(1))
    case x: AnyVal      => x
  }
  
  println("aaa->" + leafElem("aaa"))
  println("""Array("aa","bb","cc"))->""" + leafElem(Array("bb","cc")))
  println("true->" + leafElem(true))
}
