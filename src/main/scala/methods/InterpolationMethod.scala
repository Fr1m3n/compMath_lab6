package methods

import breeze.linalg.{DenseMatrix, DenseVector}
import utils.{FunctionObject, Result}

trait InterpolationMethod {

  def methodName: String

  def findInterpolation(table: Array[(Double, Double)]): (Double) => Double

  def buildResult(table: Array[(Double, Double)],
                  f: (Double) => Double,
                  extraColumns: Array[(String, String)]=Array.empty,
                  extraInfo: Array[(String, String)]=Array.empty): Result = {
    new Result(
      new FunctionObject(f, methodName),
      table,
      methodName,
      extraInfo=extraInfo
    )
  }

}
