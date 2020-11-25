package enums

class EnumDemo {

  enum Color {
    case Red, Green, Blue
  }
  
  val c1red = Color.Red
  
  println("Color.Red=>"+c1red)

  enum Colour(val rgb: Int) {
    
    def getColourDescription() = {
      s"int value for colour $this is $rgb"
    }
    
    case Red   extends Colour(0xFF0000)
    case Green extends Colour(0x00FF00)
    case Blue  extends Colour(0x0000FF)
  }
  
  val c2red = Colour.Red

  println("Colour.Red=>"+c2red)
  println("Colour.Red=>"+c2red.rgb)

  println("Colour.Red byName=>"+Colour.valueOf("Red"))

  println(Colour.Red.getColourDescription())

}
