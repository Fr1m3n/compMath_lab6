package methods

import methods.Method
import utils.{FunctionObject, Result}

class ModifiedEulerMethod extends Method {
  
    val methodName = "Модифицированный метод Эйлера"

    def getNextY(xi: Double, yi: Double, h: Double, xi1: Double, f: (Double, Double) => Double): Double = {
        yi + h / 2 * (f(xi, yi) + f(xi1, yi + h * f(xi, yi)))
    }

    def solve(functionObject: FunctionObject, range: (Double, Double), h: Double, precission: Double, y0: Double): Result = {
        var res: Array[(Double, Double)] = Array((range._1, y0))
        while (range._2 - res.last._1 - h >= -0.0000001) {
            def xi = res.last._1
            def yi = res.last._2
            def xi1 = xi + h
            res = res.appended((xi1, getNextY(xi, yi, h, xi1, functionObject.f)))
            println(s"${res.last._1}-${range._2}")
        }
        val R = (res(2)._2 - solve(functionObject, range, h * 2, precission, y0).result(2)._2) / (Math.pow(getP(precission), 4) - 1)
        new Result(functionObject, res, this, R) 
    }

}
