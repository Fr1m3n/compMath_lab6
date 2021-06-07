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
                             table: Array[(Double, Double)],
                             subplot: Int = 0,
                             color: String=null): Unit = {
        val p = figure.subplot(2, 2, subplot)
        p.legend = true
        val xLin = linspace(table.map(_._1).min - MARGIN, table.map(_._1).max + MARGIN)
        p += plot(xLin, xLin.map(xi => function.f(xi)), name = function.str)
        p.xlabel = "x axis"
        p.ylabel = "y axis"
        //        figure.saveas("plot.png")
    }


    def drawChartForFunctions(functions: Array[FunctionObject], table: Array[(Double, Double)]) = {
        val figure = Figure()
        val p = figure.subplot(0)
        p.legend = true
        functions.indices.foreach((i) => {
            drawChartForFunction(figure, functions(i), table, i)
            drawPoints(figure.subplot(2, 2, i), table)
        })

        functions.foreach((f) => drawChartForFunction(figure, f, table, 2))
        drawPoints(figure.subplot(2, 2, 2), table)
        drawPoints(figure.subplot(2, 2, 3), table)
//        figure.saveas("plot.png")
    }


}
