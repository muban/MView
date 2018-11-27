package com.muban.view

import android.content.Context
import android.graphics.*
import android.view.View

/**
 * 直方图
 */
class SimpleHistogram(context: Context?) : View(context) {
    private val mPaint = Paint()
    private var mWidth = 0
    private var mHeight = 0
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mWidth = measuredWidth
        mHeight = measuredHeight
        canvas?.apply {
            drawHistogram()
        }
    }

    /**
     * 绘制直方图
     */
    private fun Canvas.drawHistogram() {
        drawColor(Color.WHITE)
        mPaint.isAntiAlias = true
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 2f
        //绘制坐标系
        val path = Path()
        path.moveTo(100f, 100f)
        path.rLineTo(0f, mHeight - 200f)
        path.rLineTo(mWidth - 200f, 0f)
        drawPath(path, mPaint)
        //重置路径
        path.reset()
        //绘制柱状图
        val names = arrayOf("java", "kotlin", "python", "go", "android", "ios", "web")
        mPaint.style = Paint.Style.FILL
        mPaint.textSize = 30f
        mPaint.textAlign = Paint.Align.CENTER//文字按原点居中
        val width = 100f//柱状图宽度
        val interval = 20f//柱状图间隔
        val yIndex = mHeight - 102f//y坐标原点位置
        for (i in 0..6) {
            mPaint.color = Color.parseColor("#FF27B258")
            //移动原点坐标位置
            path.moveTo((100f + interval) + i * (interval + width), yIndex)
            //随机高度
            val random = Math.random()//所占比列
            val height = (random * (mHeight - 200f)).toFloat()
            //绘制矩形
            path.rLineTo(0f, -height)
            path.rLineTo(width, 0f)
            path.rLineTo(0f, height)
            drawPath(path, mPaint)
            //绘制底部文字
            mPaint.color = Color.parseColor("#000000")
            drawText(
                names[i],
                (100f + interval) + i * (interval + width) + width / 2,
                yIndex + 40f,
                mPaint
            )
            //绘制顶部文字
//            drawText(
//                "${(random * 100)}%",
//                (100f + interval) + i * (interval + width) + width / 2,
//                yIndex - height - 40f,
//                mPaint
//            )
        }
    }
}
