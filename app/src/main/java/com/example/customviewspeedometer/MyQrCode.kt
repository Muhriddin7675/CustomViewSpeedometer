package com.example.customviewspeedometer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.security.AttestedKeyPair
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class MyQrCode @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs) {


    private var matrix: Array<IntArray> = arrayOf(intArrayOf(0))
    private var formWeight = 0f
    private var formHeight = 0f


    private val paint = Paint().apply {
        style = Paint.Style.FILL
    }

    fun setMatrix(x: Int, y: Int) {
        matrix = Array(x) { IntArray(y) }
        for (i in 0 until x) {
            for (j in 0 until y) {/////
                matrix[i][j] = Random.nextInt(2)
                Log.d("TTT", matrix[i][j].toString())
            }
        }
        this.matrix = matrix
        formWeight = (width / x).toFloat()
        formHeight = (height / y).toFloat()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {

        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                val color = if (matrix[i][j] == 1) Color.BLACK
                else if (matrix[i][j] == 2) Color.BLUE
                else Color.WHITE
                Log.d("TTT", matrix[i][j].toString())
                paint.color = color

                val left = i * formWeight
                val top = j * formHeight
                canvas.drawRect(
                    left,
                    top,
                    left + formWeight,
                    top + formHeight,
                    paint
                )
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val x = (event.x.toInt() / formWeight.toInt())
            val y = (event.y.toInt() / formHeight.toInt())
            Log.d("TTT", "onTouchEvent: $x $y")
            if (matrix[x][y] == 0) {
                changeAdjacent(x, y)
            }
        }
        return true
    }

    private fun changeAdjacent(x: Int, y: Int) {
        if (x < 0 || y < 0 || x >= matrix.size || y >= matrix[0].size || matrix[x][y] != 0 || matrix[x][y] == 2) {
            return
        } else {
            matrix[x][y] = 2
            invalidate()
            postDelayed(
                {
                    changeAdjacent(x - 1, y)
                    changeAdjacent(x + 1, y)
                    changeAdjacent(x, y - 1)
                    changeAdjacent(x, y + 1)

                }, 200
            )
        }
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

}












