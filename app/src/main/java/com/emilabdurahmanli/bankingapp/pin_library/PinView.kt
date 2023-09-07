package com.emilabdurahmanli.bankingapp.pin_library

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.emilabdurahmanli.bankingapp.R

@SuppressLint("ViewConstructor")
class PinView(context : Context, attrs: AttributeSet) : View(context, attrs) {

    companion object {
        private val DEFAULT_PIN_COLOR = Color.parseColor("#32D74B")
        private const val DEFAULT_BORDER_WIDTH = 4.0f
        private const val DEFAULT_CURRENT_PIN = 0
    }

    private var pinColor = DEFAULT_PIN_COLOR
    private var grayColor = Color.GRAY
    private var borderWidth = DEFAULT_BORDER_WIDTH
    private val paint = Paint()
    private var size = 320
    private val distance = size/3f
    private val radius = size / 20f
    private var cy : Float = radius
    private var circleX = radius
    var currentPin = DEFAULT_CURRENT_PIN
        set(pin){
            if(pin == 0){
                field = 0
            } else if(pin>field){
                if(field<4){
                    field = pin
                }
            }else if(pin < field){
                if(field>0) {
                    field = pin
                }
            }
            invalidate()
        }

    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.PinView,
            0, 0)

        pinColor = typedArray.getColor(R.styleable.PinView_pinColor, DEFAULT_PIN_COLOR)
        currentPin = typedArray.getInt(R.styleable.PinView_currentPin, DEFAULT_CURRENT_PIN)
        borderWidth = typedArray.getDimension(R.styleable.PinView_borderWidth,
            DEFAULT_BORDER_WIDTH)
        typedArray.recycle()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        // 8*radius 3*(size/4)
        val desiredWidth = (distance.toInt())*3 + (size/10)
        val desiredHeight = (2*radius).toInt()
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val width: Int
        val height: Int

        //Measure Width
        width = if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            widthSize
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            Math.min(desiredWidth, widthSize)
        } else {
            //Be whatever you want
            desiredWidth
        }

        //Measure Height
        height = if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            heightSize
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            Math.min(desiredHeight, heightSize)
        } else {
            //Be whatever you want
            desiredHeight
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPins(canvas)

    }


    private fun drawPins(canvas: Canvas){
        var cx = circleX
        for (i in 1 .. 4){
            if(i<=currentPin){
                paint.color = pinColor
            }else{
                paint.color = grayColor
            }
            canvas.drawCircle(cx, cy, radius, paint)
            cx += distance
        }
    }


}