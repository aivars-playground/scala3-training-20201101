package newtypes

trait A {
  def doA: Int
  def common: List[A]
  override def toString: String = s"<A:$doA - ch:`${common.mkString(",")}`>"
}

trait B {
  def doB: String
  def common: List[B]
  override def toString: String = s"<B:$doB - ch:`${common.mkString(",")}`>"
}

trait Resettable {
  def reset(): Unit
}

trait Growable[T] {
  def add(t: T): Unit
}

class C(val doA: Int, val doB: String) extends A with B with Resettable with Growable[A & B]{

  private var list: List[A & B] = List.empty
  
  def reset() = {
    list = List.empty[A & B]
  }

  def add(child: A & B) = {
    list = child :: list 
  }
 
  def common: List[A & B] = list

  override def toString: String = s"<A:$doA & B:$doB - ch:`${common.mkString(",")}`>"
}

class IntersectTypeDemo {
  
  val x: C = new C(1, "first")
  val y: A & B = new C(2, "second")

  def addChild(item: Resettable & Growable[A & B], child: A & B ) = {
    item.reset()
    item.add(child)
  }
  
  val z:A = x
  
  println("-----x1:" + x)
  addChild(x,y)
  println("-----x2:" + x)
  println("-----z1:" + z)
}


