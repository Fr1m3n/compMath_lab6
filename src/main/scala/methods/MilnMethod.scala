package methods

import utils.FunctionObject
import utils.Result

class MilnMethod extends Method {
   val methodName = "Метод Милна"

    def getNextY(xi: Double, yi: Double, h: Double, xi1: Double, f: (Double, Double) => Double): Double = {
        yi + h / 2 * (f(xi, yi) + f(xi1, yi + h * f(xi, yi)))
    }

    def solve(functionObject: FunctionObject, range: (Double, Double), h: Double, precission: Double, y0: Double, needR: Boolean = true): Result = {
        var xyValues = new ModifiedEulerMethod().solve(functionObject, (range._1, range._1 + h * 3), h, precission, y0, needR=false).result
        var f: Array[Double] = Array()
        xyValues.foreach((a) => f = f.appended(functionObject.f(a._1, a._2)))
        var x = xyValues.last._1 + h
        while (x <= range._2) {
            
            var y_predicted = xyValues(xyValues.length - 4)._2 + 4 * h * (2 * f(0) - f(1) + 2 * f(3)) / 3
            var new_f = functionObject.f(x, y_predicted)
            var y_corrected = xyValues(xyValues.length - 2)._2 + h * (f(1) + 4 * f(2) + new_f) / 3

            while (Math.abs(y_corrected - y_predicted) > precission) {
                y_predicted = y_corrected
                new_f = functionObject.f(x, y_predicted)
                y_corrected = xyValues(xyValues.length - 2)._2 + h * (f(1) + 4 * f(2) + new_f) / 3
            }
            xyValues = xyValues.appended((x, y_predicted))
            f = f.drop(1).appended(new_f)

            x = x + h
        }
        val R = if (needR && xyValues.length > 2) (xyValues(2)._2 - solve(functionObject, range, h * 2, precission, y0, needR=false).result(2)._2) / (Math.pow(getP(precission), 4) - 1)
        else 0.0

        new Result(functionObject, xyValues, this, R)
    }
}
