package com.muban.view

import android.content.Context
import android.graphics.*
import android.view.View

/**
 * draw练习
 * Android 坐标系：原点为左上角，右正左负，下正上负
 */
class DrawXXX(context: Context?) : View(context) {

    private var mPaint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            //            drawColor()
            //            drawGraphics()
            drawPath()
        }
    }

    /**
     * 颜色绘制
     */
    private fun Canvas.drawColor() {
        drawColor(Color.parseColor("#000000"))
        drawRGB(100, 200, 100)
        drawARGB(100, 100, 200, 100)
    }

    /**
     * 画各种图形
     */
    private fun Canvas.drawGraphics() {
        mPaint.color = Color.RED//设置画笔红色
        mPaint.style = Paint.Style.STROKE//设置画笔为画线模式
        mPaint.strokeWidth = 10f//设置画线宽度
        mPaint.isAntiAlias = true//设置抗锯齿
        //画圆cx：圆心横坐标 cy：圆心纵坐标 radius：半径 paint：画笔
        drawCircle(200f, 200f, 100f, mPaint)
        //画矩形 left, top, right, bottom 是矩形四条边的坐标
        drawRect(100f, 100f, 200f, 200f, mPaint)
        //画点
        //x 和 y 是点的坐标。点的大小可以通过 paint.setStrokeWidth(width) 来设置；
        //点的形状可以通过  paint.setStrokeCap(cap) 来设置
        //ROUND 画出来是圆形的点，SQUARE 或 BUTT 画出来是方形的点。
        drawPoint(150f, 150f, mPaint)
        //批量画点
        val points = floatArrayOf(0f, 0f, 10f, 10f, 20f, 20f, 30f, 30f, 40f, 40f)
        drawPoints(
            /*点坐标（2个一组）*/points,//点坐标
            /*跳过2个数，即前两个 0*/2,//跳过数组的前几个数再开始记坐标
            /*一共绘制 8 个数（4 个点）*/8,// 表示一共要绘制几个点
            mPaint
        )
        //画椭圆 left, top, right, bottom 是四个边界顶点坐标
//        drawOval(100f, 100f, 400f, 200f,mPaint)//API 21以上可用
        drawOval(RectF(100f, 100f, 400f, 200f), mPaint)
        //画线 起点x，y坐标，终点x，y坐标
        drawLine(200f, 200f, 800f, 800f, mPaint)
        //批量画线
        drawLines(lines, mPaint)
        //画圆角矩形 left, top, right, bottom 是矩形四条边的坐标 rx 和 ry 是圆角的横向半径和纵向半径。
        drawRoundRect(RectF(500f, 500f, 1000f, 800f), 10f, 10f, mPaint)
        //绘制扇形
        mPaint.style = Paint.Style.FILL
        drawArc(RectF(500f, 1000f, 1000f, 1300f), -100f, 100f, true, mPaint)
        //绘制弧形
        drawArc(RectF(500f, 1000f, 1000f, 1300f), 150f, 100f, false, mPaint)
        //绘制不封口的弧形
        mPaint.style = Paint.Style.STROKE
        drawArc(RectF(500f, 1000f, 1000f, 1300f), 0f, 100f, false, mPaint)


    }

    /**
     * 画路径
     */
    private fun Canvas.drawPath() {
        mPaint.color = Color.WHITE
        mPaint.isAntiAlias = true
        val path = Path()
        // 绘制出 path 描述的图形（心形）
//        path.addArc(RectF(200f, 200f, 400f, 400f), -225f, 225f)
//        path.arcTo(RectF(400f, 200f, 600f, 400f), -180f, 225f, false)
//        path.lineTo(400f, 542f)
//        drawPath(path, mPaint)
        //画线
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 5f
        //使路径移动到某点
        path.moveTo(0f, 500f)
        //lineTo(x, y) 的参数是绝对坐标
        path.lineTo(100f, 100f) // 由当前位置 (0, 500) 向 (100, 100) 画一条直线
        //rLineTo(x, y) 的参数是相对当前位置的相对坐标 （前缀 r 指的就是  relatively 「相对地」)
        path.rLineTo(100f, 0f)// 由当前位置 (100, 100) 向正右方 100 像素的位置画一条直线
        drawPath(path, mPaint)
        //二次贝塞尔曲线
        path.moveTo(0f, 500f)
        //x1,y1是控制点坐标，x2，y2是终点位置
        path.quadTo(100f, 500f, 400f, 700f)
        //三次贝塞尔曲线 控制点（x1，y1）和（x2，y2），终点（x3，y3）
        path.rCubicTo(100f, 200f, 500f, 500f, 300f, 200f)
        drawPath(path, mPaint)
        //画弧形
        path.moveTo(200f, 0f)
        path.rLineTo(100f, 100f)
        //强制移动到弧形起点（无痕迹）
        path.arcTo(RectF(300f, 100f, 500f, 300f), -90f, 90f, true)
        //直接连线连到弧形起点（有痕迹）
        path.arcTo(RectF(300f, 300f, 500f, 500f), -90f, 90f, false)
        //addArc() 只是一个直接使用了 forceMoveTo = true 的简化版 arcTo() 。
        path.addArc(RectF(500f, 300f, 700f, 500f), -90f, 90f)
        //封闭当前子图形
        path.close()
        drawPath(path, mPaint)

    }

    //工口线坐标
    val lines = floatArrayOf(
        20f, 20f, 120f, 20f,
        70f, 20f, 70f, 120f,
        20f, 120f, 120f, 120f,
        150f, 20f, 250f, 20f,
        150f, 20f, 150f, 120f,
        250f, 20f, 250f, 120f,
        150f, 120f, 250f, 120f
    )
}