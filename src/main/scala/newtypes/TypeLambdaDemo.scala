package newtypes

//https://underscore.io/blog/posts/2016/12/05/type-lambdas.html
class TypeLambdaDemo {
  //[X, Y] =>> Map[Y, X] //what is this?

  type L = List[(Option[(Int,Double)])]

  type T[A] = Option[Map[Int, A]]
  val t: T[String] = Some(Map(1 -> "abc", 2 -> "xyz"))


  trait Functor[F[_]]
  type F1 = Functor[Option] // OK
  type F2 = Functor[List]   // OK
  //type F3 = Functor[Map]    // !! does not compile

  type F5 = Functor[({ type T[A] = Map[Int, A] })#T] // OK

  def foo[A[_,_],B](functor: Functor[({type AB[C] = A[B,C]})#AB]): Unit = {} //?????
}
