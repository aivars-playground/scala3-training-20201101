package enums

class EnumAlgebraicDataType {

  enum Option[+T] {
    case Some(x: T)
    case None
  }
  
  println(Option.Some(1))

  enum Color(val rgb: Int) {
    def addColor(color: Color) = {
      Color.Mix(this.rgb + color.rgb)
    }
    case Red   extends Color(0xFF0000)
    case Green extends Color(0x00FF00)
    case Blue  extends Color(0x0000FF)
    case Mix(mix: Int) extends Color(mix)
  }

  val incorrectlyMixedColour = scala.runtime.RichLong(Color.Red.addColor(Color.Green).rgb)
  
  println(incorrectlyMixedColour.toHexString)
}
