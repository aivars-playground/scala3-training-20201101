//new entry point
@main def item1_application(others: String*) = println(others.mkString(","))

//https://dotty.epfl.ch/docs/reference/new-types/intersection-types.html
@main def item2_type_demo_1() = new newtypes.intersect.IntersectTypeDemo1
