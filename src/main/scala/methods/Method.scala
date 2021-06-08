package methods

import breeze.linalg.{DenseMatrix, DenseVector}
import utils.{FunctionObject, Result}

trait Method {

  def methodName: String

  def solve(functionObject: FunctionObject, range: (Double, Double), h: Double, precission: Double, y0: Double, needR: Boolean = true): Result

  def getP(precision: Double, accum: Int = 0): Double = {
    if (precision >= 1.0) accum
    else getP(precision * 10, accum + 1)
  }

  // def buildResult(table: Array[(Double, Double)],
  //                 f: (Double) => Double,
  //                 extraColumns: Array[(String, String)]=Array.empty,
  //                 extraInfo: Array[(String, String)]=Array.empty): Result = {
  //   new Result(
  //     new FunctionObject(f, methodName),
  //     table,
  //     methodName,
  //     extraInfo=extraInfo
  //   )
  // }

}
