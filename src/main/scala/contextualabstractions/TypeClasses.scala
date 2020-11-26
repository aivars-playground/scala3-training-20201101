package contextualabstractions

class TypeClasses {

  //TypeClass edds functionality /ad-hoc polymorphism

  val l1 = List(1, 2, 3)

  def combineAll[T: Monoid](xs: List[T]): T =
    xs.foldLeft(summon[Monoid[T]].unit)(_.combine(_))

  trait SemiGroup[T]:
    extension (x: T) def combine(y: T): T

  trait Monoid[T]extends SemiGroup[T] :
    def unit: T

  //old
  trait FunctorOld[F[_]]:
    def map[A, B](x: F[A], f: A => B): F[B]

  //new
  trait Functor[F[_]]:
    extension[A, B] (x: F[A])
      def mmap(f: A => B): F[B]

  {
    given FunctorOld[List] :
      def map[A, B](x: List[A], f: A => B): List[B] =
        x.map(f) // List already has a `map` method

    given Monoid[String] :
      extension (x: String) def combine(y: String): String = x.concat(y)
      def unit: String = "aaa"


    println("--s:" + summon[Monoid[String]])
    
    println(s"in:$l1 out:${l1.map(_ * 10)}")
  }

  //A Monad for type F[_] is a Functor[F] with two more operations:
  //
  //flatMap, which turns an F[A] into an F[B] when given a function of type A => F[B],
  //pure, which creates an F[A] from a single value A.


  given Functor[List] :
    extension[A, B] (xs: List[A])
      def mmap(f: A => B): List[B] =
        xs.map(f) // List already has a `map` method


  trait Monad[F[_]]extends Functor[F] :
    /** The unit value for a monad */
    def pure[A](x: A): F[A]
  
    extension[A, B] (x: F[A])
      /** The fundamental composition operation */
      def mflatMap(f: A => F[B]): F[B]
      /** The `map` operation can now be defined in terms of `flatMap` */
      def mmap(f: A => B) = x.mflatMap(f.andThen(pure))
  end Monad

  given listMonad as Monad[List]:
    def pure[A](x: A): List[A] =
      List(x)
    extension [A, B](xs: List[A])
      def mflatMap(f: A => List[B]): List[B] =
        xs.flatMap(f) // rely on the existing `flatMap` method of `List`

  println ("monad flatmap"+l1.mflatMap(item => List(item * 10)))
  println ("monad map" +l1.mmap(item => item * 10))

  given optionMonad as Monad[Option]:
    def pure[A](x: A): Option[A] =
      Option(x)
    extension [A, B](xo: Option[A])
      def mflatMap(f: A => Option[B]): Option[B] = xo match
        case Some(x) => f(x)
        case None => None

  val ab:Option[String] = optionMonad.pure("ab")
  val ba = ab.mflatMap{ (text: String) => Some(text.reverse)}
  
  println(s"ab:$ab -> ba:$ba")
  
  val sd = Some("sd")
  val ds = sd.mflatMap{ (text: String) => Some(text.reverse)}
  println(s"sd:$sd -> ds:$ds")
  
  //READERMONAD....
}
