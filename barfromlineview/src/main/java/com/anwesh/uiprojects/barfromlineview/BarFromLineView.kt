package com.anwesh.uiprojects.barfromlineview

/**
 * Created by anweshmishra on 26/09/19.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val bars : Int = 3
val scGap : Float = 0.01f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#673AB7")
val backColor : Int = Color.parseColor("#BDBDBD")
val barSizeFactor : Float = 3f

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawBar(i : Int, gap : Float, sc : Float, paint : Paint) {
    val size : Float = gap / sizeFactor
    save()
    translate(i * gap, 0f)
    drawRect(RectF(-size, -2 * size * sc, size, 0f), paint)
    restore()
}

fun Canvas.drawBarLine(size : Float, sc : Float, paint : Paint) {
    save()
    drawLine(-size, 0f, size, 0f, paint)
    for (j in 0..(bars - 1)) {
        drawBar(j, size / bars, sc.divideScale(0, 2), paint)
    }
    restore()
}

fun Canvas.drawBFLNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = w / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    save()
    translate(gap * (i + 1), h / 2)
    rotate(90f * sc2)
    drawBarLine(size, sc1, paint)
    restore()
}
