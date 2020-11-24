package newtypes

class DependentFunctionType {

  trait Entry { type Key; val key: Key }

  def extractKey(e: Entry): e.Key = e.key          // a dependent method
  val extractor: (e: Entry) => e.Key = extractKey  // a dependent function value
  //            ║   ⇓ ⇓ ⇓ ⇓ ⇓ ⇓ ⇓   ║
  //            ║     Dependent     ║
  //            ║   Function Type   ║
  //            ╚═══════════════════╝

  class ByIntKey(val key: Int, val $value: String) extends Entry {
    type Key = Int
  }
  
  val intk1 = ByIntKey(1,"ik1")
  
  val keyIntk1: Any = extractor(intk1)

  println("===intk1=>"+extractor(intk1))
  println("===intk1=>"+extractKey(intk1))


  trait StringEntry extends Entry {
    type Key = String
  }


  class ByStringKey(val key: String, val $value: String) extends StringEntry
  
  val strk1 = ByStringKey("a","val_a")
  val keyStrkA: Any = extractor(strk1)
  val keyStrkB: Any = extractKey(strk1)

  val keyStrkBT: strk1.Key = extractKey(strk1)
  println("===keyStrkBT=>" + keyStrkBT)
}
