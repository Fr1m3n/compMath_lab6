package methods
import utils.Result

class NewtonMethod extends InterpolationMethod {
  override def methodName: String = "Метод Ньютона"

  def f(table: Array[(Double, Double)], x: Double): Double = {
    table.filter((a) => a._1 == x)(0)._2
  }

  def f(table: Array[(Double, Double)], x0: Double, x1: Double): Double = {
    (f(table, x1) - f(table, x0)) / (x1 - x0)
  }

  def f(table: Array[(Double, Double)], x0: Double, x1: Double, x2: Double): Double = {
    (f(table, x1, x2) - f(table, x0, x1)) / (x2 - x0)
  }

  def f(table: Array[(Double, Double)], x: Array[Double]):Double = {
     x.length match {
       case 1 => f(table, x(0))
       case 2 => f(table, x(0), x(1))
       case 3 => f(table, x(0), x(1), x(2))
       case _ => (f(table, x.drop(1)) - f(table, x.dropRight(1))) / (x.last - x.head)
     }
  }

  def sum(table: Array[(Double, Double)], x0: Double): Double = {
    val x = table.map(_._1)
    var res = 0.0
    1.to(table.length).foreach((k) => {
      var mul = 1.0
      0.until(k - 1).foreach((j) => {
        mul *= (x0 - x(j))
      })
      res += mul * f(table, x.dropRight(table.length - k))
    })
    res
  }

  override def findInterpolation(table: Array[(Double, Double)]): (Double) => Double = {
    (x0) => sum(table, x0)
  }
}
