package methods
import utils.Result

class LagrangeMethod extends InterpolationMethod {
  override def methodName: String = "Метод Лагранжа"

  def getMul(table: Array[(Double, Double)], x0: Double, xy: (Double, Double)): Double = {
    val m = table.filter((p) => p != xy).foldRight(1.0)((a, b) => b * (x0 - a._1) / (a._1 - xy._1))
    println(m)
    m
  }

  override def findInterpolation(table: Array[(Double, Double)]): (Double) => Double = {
    (x0) => {
      var res: Double = 0.0
      table.indices.foreach((i) => {
        var mul: Double = 1.0;
        table.indices.foreach((j) => {
          if (i != j) mul = mul * (x0 - table(j)._1) / (table(i)._1 - table(j)._1)
        })
        res = res + table(i)._2 * mul
      })
      res
    }
  }
}
