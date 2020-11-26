package contextualabstractions

class UsingClauses {

  trait Ord[T] {
    def compare(x: T, y: T): Int

    extension (x: T) def <(y: T) = compare(x, y) < 0
    extension (x: T) def >(y: T) = compare(x, y) > 0
  }

  def max[T](x: T, y: T)(using ord: Ord[T]): T =
    if ord.compare(x, y) < 0 then y else x

  def maximum[T](xs: List[T])(using Ord[T]): T =
    xs.reduceLeft(max)

  def descending[T](using asc: Ord[T]): Ord[T] = new Ord[T] {
    def compare(x: T, y: T) = asc.compare(y, x)
  }

  def minimum[T](xs: List[T])(using Ord[T]) =
    maximum(xs)(using descending)

  given intOrd as Ord[Int] {
    def compare(x: Int, y: Int) =
      if (x < y) -1 else if (x > y) +1 else 0
  }

  val xs: List[Int] = List(1, 2, 3)

  println("min:" + minimum(xs))
  println("min using desc:" + maximum(xs)(using descending))
  //maximum(xs)(using descending(using listOrd)) why this does not work? //seems like an error in docs
  println("min using desc:" + maximum(xs)(using descending(using intOrd)))
}
