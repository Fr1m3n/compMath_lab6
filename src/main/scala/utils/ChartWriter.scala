package utils

import breeze.linalg._
import breeze.plot._
import com.github.sh0hei.mascalade.tableflip.TableFlip

object ChartWriter extends App {

    final val MARGIN = 0.01

    def drawPoints(p: Plot, table: Array[(Double, Double)]): Unit = {
        val x = DenseVector(table.map(_._1))
        val y = DenseVector(table.map(_._2))
        p += plot(x, y, '.', name="input")
        p.legend = true
    }

    def drawChartForFunction(figure: Figure,
                             function: FunctionObject,
                             range: (Double, Double),
                             subplot: Int = 0,
                             color: String=null): Unit = {
        val p = figure.subplot(subplot)
        p.legend = true
        val xLin = linspace(range._1, range._2)
        p += plot(xLin, xLin.map(xi => function.solution(xi)), name = function.str)
        p.xlabel = "x axis"
        p.ylabel = "y axis"
        //        figure.saveas("plot.png")
    }

    def drawChartForPoints(figure: Figure, points: Array[(Double, Double)], title: String = "title") = {
        val p = figure.subplot(0)
        p.legend = true
        p += plot(points.map(_._1), points.map(_._2), name=title)
    }


    def drawChartForFunctions(functions: Array[FunctionObject], table: Array[(Array[(Double, Double)], String)], range: (Double, Double)) = {
        val figure = Figure()
        val p = figure.subplot(0)
        p.legend = true
        // functions.indices.foreach((i) => {
        //     drawChartForFunction(figure, functions(i), table, i)
        //     drawPoints(figure.subplot(2, 2, i), table)
        // })
        table.foreach(p => drawChartForPoints(figure, p._1, p._2))
        functions.foreach((f) => drawChartForFunction(figure, f, range))
//        figure.saveas("plot.png")
    }


}
