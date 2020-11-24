//new entry point
//no need for class/object...
@main def item1_application(others: String*) = println(others.mkString(","))

//https://dotty.epfl.ch/docs/reference/new-types/intersection-types.html
//no need for new keyword
@main def item2_intersect_type_demo() = newtypes.IntersectTypeDemo()

//https://dotty.epfl.ch/docs/reference/new-types/union-types.html
//@main def item3_union_type_demo() = newtypes.UnionTypeDemo //does not work, if omitting new, need to add ()
@main def item3_union_type_demo() = newtypes.UnionTypeDemo()
