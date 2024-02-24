package com.example.customviewspeedometer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import org.w3c.dom.Text
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MySpeedometer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttrs, defStyleRes) {

    private val maxAngle = -40
    private val minAngle = 220
    private val smallLineLength = 20
    private val largeLineLength = 50
    private val radius by lazy { width / 2 }
    private val maxSpeed = 140
    var minSpeed = 0
        set(value) {
            field = value
            invalidate()
        }

    private val smallLinePaint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 4f
        style = Paint.Style.FILL
        color = Color.parseColor("#07F1DB")
    }

    private val largeLinePaint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 10f
//        strokeJoin = 5f
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("#ffffff")
    }

    private val paintText = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("#00E2FF")
        textSize = 56f
        textScaleX = 1f
    }

    private val circleInnerPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.parseColor("#03C5B2")
    }
    private val circleAuthorPaint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 6f
        style = Paint.Style.STROKE
        color = Color.parseColor("#2FFBE8")
    }
    private val circlePaint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 8f
        style = Paint.Style.STROKE
        color = Color.parseColor("#03C5B2")
    }

    private val indicatorPaint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 12f
        style = Paint.Style.FILL
        color = Color.parseColor("#ffffff")

    }

    override fun onDraw(canvas: Canvas) {
        drawSmallLines(canvas)
        drawLargeLines(canvas)
        drawCircleWidth(canvas)
        drawIndicator(canvas)
        drawCircle(canvas)

    }


    private fun drawSmallLines(canvas: Canvas) {
        val extraRadius = radius - (largeLineLength - smallLineLength) / 2

        for (i in 0..maxSpeed step 2) {
            val alphaRadian = calculateAlpha(i).toRadian()
            canvas.drawLine(
                radius + (extraRadius - smallLineLength) * cos(alphaRadian).toFloat(),
                radius - (extraRadius - smallLineLength) * sin(alphaRadian).toFloat(),
                radius + extraRadius * cos(alphaRadian).toFloat(),
                radius - extraRadius * sin(alphaRadian).toFloat(),
                smallLinePaint
            )
        }
    }

    private fun drawLargeLines(canvas: Canvas) {
        for (i in 0..maxSpeed step 10) {
            val alphaRadian = calculateAlpha(i).toRadian()
            canvas.drawLine(
                radius + (radius*0.98f - largeLineLength) * cos(alphaRadian).toFloat(),
                radius - (radius*0.98f - largeLineLength) * sin(alphaRadian).toFloat(),
                radius + radius*0.98f * cos(alphaRadian).toFloat(),
                radius - radius*0.98f * sin(alphaRadian).toFloat(),
                largeLinePaint
            )
            canvas.drawCenterText(
                "$i",
                radius + (radius - 110) * cos(alphaRadian).toFloat(),
                radius - (radius - 110) * sin(alphaRadian).toFloat(),
                paintText
            )

        }
    }

    private fun drawCircle(canvas: Canvas) {
        canvas.drawCircle(radius.toFloat(), radius.toFloat(), radius * 0.1f, circleInnerPaint)
        canvas.drawCircle(radius.toFloat(), radius.toFloat(), radius * 0.98f, circlePaint)
    }
    private fun drawCircleWidth(canvas: Canvas){
        canvas.drawCircle(radius.toFloat(), radius.toFloat(), radius * 0.6f, circleAuthorPaint)

    }

    private fun drawIndicator(canvas: Canvas){
        val alphaRadian = calculateAlpha(minSpeed).toRadian()
        canvas.drawLine(
         radius.toFloat(),
            radius.toFloat(),
            radius + radius * 0.7f * cos(alphaRadian).toFloat(),
            radius - radius * 0.7f * sin(alphaRadian).toFloat(),
            indicatorPaint
        )
    }


    private fun calculateAlpha(speed: Int): Int {
        val betta = (speed * (minAngle - maxAngle) / maxSpeed)
        return minAngle - betta
    }

    private fun Int.toRadian(): Double = this * PI / 180

    private fun Canvas.drawCenterText(text: String, x: Float, y: Float, paint: Paint) {
        val rect = Rect()
        paint.getTextBounds(text, 0, text.length, rect)
        this.drawText(
            text,
            x - rect.exactCenterX(),
            y - rect.exactCenterY(),
            paint
        )
    }

}


















