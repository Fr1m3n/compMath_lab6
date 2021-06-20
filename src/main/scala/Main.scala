import methods.{ModifiedEulerMethod, MilnMethod}
import utils.Result
import utils._

object Main extends App {

  val functions = Array(
    new FunctionObject((x, y) => 2 * x * y, "y' = 2xy", x => Math.exp(x * x)),
    new FunctionObject((x, y) => 2 * x - y + x * x, "y' = 2x - y + x^2", x => x * x),
    new FunctionObject((x, y) => Math.pow(x - y, 2) + 1, "y' = (x - y) ^ 2 + 1", x => x)
  )

  val methods = Array(
    new ModifiedEulerMethod,
    new MilnMethod
  )

  val choosenFunction = InputUtils.requestFunction(functions)

  val range = (InputUtils.requestDouble("Введите левую границу: "), InputUtils.requestDouble("Введите правую границу: "))

  val y0 = InputUtils.requestDouble(f"y(${range._1}) = ")
  val h = InputUtils.requestDouble(("h = "))
  val eps = InputUtils.requestDouble("e = ")

  
  val res = methods.map((m) => m.solve(choosenFunction, range, h, eps, y0))
  res.foreach(m => println(m.toString()))
  ChartWriter.drawChartForFunctions(Array(choosenFunction), res, range)
  // ChartWriter.drawChartForFunctions(interpolations, table)
//  interpolations.foreach((f) => ChartWriter.drawChartForFunctions(Array(f), table))

//  val array: Array[Result] = methods.flatMap(m => m.findInterpolation(table)).sortBy(_.s)
//  ChartWriter.drawChartForFunctions(array.map(_.functionObject), table)
//  array.foreach((a) => println(a.toString))
//  println(s"Лучшая функция - ${array(0).methodName}. 𝜹 = ${array(0).averageSqrMiss}")
}
