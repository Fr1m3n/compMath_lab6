package utils

import com.github.sh0hei.mascalade.tableflip.TableFlip
import methods.Method

case class Result(val functionObject: FunctionObject,
             val result: Array[(Double, Double)],
             val method: Method,
             val R: Double,
             val steps: Array[Array[Double]],
             val extraInfo: Array[(String, String)] = Array.empty,
             val coefficients: Map[String, Double] = Map.empty) {


 override def toString: String = getInfo + getPrettyTable

 def getInfo: String = {
   new StringBuilder()
    .append(s"${method.methodName}: \n")
    .append(s"R = $R\n")
    .append('\n').toString()
 }

 def getPrettyTable: String = {
   val headers = Array("x", "y", "F(x)")
   TableFlip.of(headers, steps.map(x => x.map(_.toString)))
 }


}
