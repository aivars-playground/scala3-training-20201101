//new entry point
//no need for class/object...
@main def application(others: String*) = println(others.mkString(","))

//https://dotty.epfl.ch/docs/reference/new-types/intersection-types.html
//no need for new keyword
@main def type_intersect_demo() = newtypes.IntersectTypeDemo()

//https://dotty.epfl.ch/docs/reference/new-types/union-types.html
//@main def item3_union_type_demo() = newtypes.UnionTypeDemo //does not work, if omitting new, need to add ()
@main def type_union_demo() = newtypes.UnionTypeDemo()

//https://dotty.epfl.ch/docs/reference/new-types/type-lambdas.html
//https://underscore.io/blog/posts/2016/12/05/type-lambdas.html
@main def type_lambda_demo() = newtypes.TypeLambdaDemo()

//https://dotty.epfl.ch/docs/reference/new-types/match-types.html
@main def type_matchtype_demo() = newtypes.MatchTypes()

//https://dotty.epfl.ch/docs/reference/new-types/dependent-function-types.html
@main def type_dependent_f_type_demo() = newtypes.DependentFunctionType()

//https://engineering.zalando.com/posts/2018/10/scala-three-experiment.html
//https://docs.scala-lang.org/sips/42.type.html
@main def type_singleton_demo() = newtypes.SingletonDemo()