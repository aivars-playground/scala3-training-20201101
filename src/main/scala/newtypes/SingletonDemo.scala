package newtypes

class SingletonDemo {

  {
    //only int
    final case class MatrixWrong[A <: Int, B <: Int](n: A, m: B) {
      def *[C <: Int](other: MatrixWrong[B, C]): MatrixWrong[A, C] =
        MatrixWrong(n, other.m)
    }
  
    val aw = MatrixWrong(2, 4)
    val bw = MatrixWrong(111, 333)
    val abw = aw * bw
    println(s"wrong aw:$aw * $bw returns $abw")
  }

  {
    //Singleton
    final case class MatrixWrong[A <: Singleton, B <: Singleton](n: A, m: B) {
      def *[C <: Singleton](other: MatrixWrong[B, C]): MatrixWrong[A, C] =
        MatrixWrong(n, other.m)
    }

    val aw = MatrixWrong(2, 4)
    val bw = MatrixWrong(111, 333)
    val cw = MatrixWrong("a", 4) //we want int
    //val abw = aw * bw //does not compile, 
    //println(s"wrong aw:$aw * $bw returns $abw")
  }



  type Dim = Singleton & Int

  final case class Matrix[A <: Dim, B <: Dim](n: A, m: B) {
    def *[C <: Dim](other: Matrix[B, C]): Matrix[A, C] =
      Matrix(n, other.m)
  }

  val a = Matrix(2, 4)
  val b = Matrix(4, 3)
  val ab = a * b
  
  println(s"a:$a * $b returns $ab")

  val d = Matrix(333, 111)
  //val ad = a * d  //does not compile, incrrect dimensions




}
