import methods.{LagrangeMethod, NewtonMethod}
import utils.Result
import utils._


object Main extends App {
  val methods = Array(
    new LagrangeMethod(),
    new NewtonMethod()
  )

  val table = InputUtils.readFunctionTable()
  val x0 = InputUtils.requestDouble("x0: ")
  val interpolations = methods
    .map((method) => new FunctionObject(method.findInterpolation(table), method.methodName))
//  val f = methods(1).findInterpolation(table)
//  val f2 = methods(0).findInterpolation(table)
//  println(f(x0))
  interpolations.foreach((f) => {
    println(f"${f.str} x0 = $x0: ${f.f(x0)}")
  })
  ChartWriter.drawChartForFunctions(interpolations, table)
//  interpolations.foreach((f) => ChartWriter.drawChartForFunctions(Array(f), table))

//  val array: Array[Result] = methods.flatMap(m => m.findInterpolation(table)).sortBy(_.s)
//  ChartWriter.drawChartForFunctions(array.map(_.functionObject), table)
//  array.foreach((a) => println(a.toString))
//  println(s"Лучшая функция - ${array(0).methodName}. 𝜹 = ${array(0).averageSqrMiss}")
}
