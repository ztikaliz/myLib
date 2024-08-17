package com.example.testapp

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

object CustomView{
    /**Create view
    /**INITIALIZE VIEW'S**/
    val linearLayout = linearLayout();
    val relativeLayout = relativeLayout();
    val textView = textView();
    val button = button();

    /**ADD VIEW'S TO linearLayout**/
    linearLayout.addView(textView);
    linearLayout.addView(button);
    linearLayout.addView(relativeLayout);

    /**SET linearLayout AS CONTENT VIEW - linearLayout = R.layout.activity_main**/
    setContentView(linearLayout);
     **/
    fun Context.textView(): TextView {
        val textView = TextView(this)
        textView.text = "Textview"
        textView.gravity = Gravity.CENTER
        return textView
    }
    fun Context.button(): Button {
        val button = Button(this)
        button.text = "Button"
        return button
    }
    fun Context.imageView(): ImageView {
        val imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER

        return imageView
    }
    fun Context.relativeLayout(): RelativeLayout {
        val relativeLayout = RelativeLayout(this)

        // SET THE SIZE
        relativeLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)

        // SET BACKGROUND COLOR JUST TO MAKE LAYOUT VISIBLE
        relativeLayout.setBackgroundColor(Color.GREEN)
        return relativeLayout
    }
    fun Context.linearLayout(): LinearLayout {
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL

        return linearLayout
    }


    /**Use:
    val view = findViewById<View>(R.id.your_view_id)
    view.background = shapeDrawable**/
    fun shapeBorder(twoLine: Boolean): ShapeDrawable {
        /**Xác định bán kính góc cho RoundRectShape**/
        val outerRadii = floatArrayOf(
            16f, 16f, // top-left corner
            16f, 16f, // top-right corner
            16f, 16f, // bottom-right corner
            16f, 16f  // bottom-left corner
        )

        /**Xác định một mảng chèn nếu bạn muốn một hình chữ nhật bên trong (đệm bên trong hình dạng)**/
        val inset = RectF(10f, 10f, 10f, 10f)

        /**Xác định bán kính bên trong cho các góc hình chữ nhật bên trong (nếu cần)**/
        val innerRadii = floatArrayOf(
            8f, 8f, // top-left inner corner
            8f, 8f, // top-right inner corner
            8f, 8f, // bottom-right inner corner
            8f, 8f  // bottom-left inner corner
        )

        /**Tạo RoundRectShape**/
        val roundRectShape = RoundRectShape(outerRadii, inset, innerRadii)

        /**Tạo ShapeDrawable với RoundRectShape**/
        val shapeDrawable = ShapeDrawable(roundRectShape).apply {
            if(twoLine){
                /**Viền 2 đường song song**/
                paint.style = Paint.Style.STROKE
                paint.strokeWidth = 4f
            }else{
                /**Viền 1 đường lớn**/
                paint.style = Paint.Style.FILL
            }

            /**màu**/
            paint.color = Color.RED

            /**trong suốt / mờ**/
            paint.alpha = 90
        }

        return shapeDrawable
    }

    /**Use:
    val view = findViewById<View>(R.id.your_view_id)
    view.background = shapeDrawable**/
    fun shapeBorderBackground(): GradientDrawable {
        return GradientDrawable().apply {
            // Set the gradient colors
            colors = intArrayOf(Color.RED, Color.GREEN)

            // Set the gradient orientation
            orientation = GradientDrawable.Orientation.RIGHT_LEFT

            // Set the gradient type (LINEAR_GRADIENT, RADIAL_GRADIENT, SWEEP_GRADIENT)
            gradientType = GradientDrawable.LINEAR_GRADIENT

            // Set corner radii (top-left, top-right, bottom-right, bottom-left)
            cornerRadii = floatArrayOf(16f, 16f, 16f, 16f, 0f, 0f, 0f, 0f)

            // Set a stroke
            setStroke(4, Color.BLACK) // 4dp width, black color

            // Set a solid color (if you want a solid background without gradient)
            // setColor(0xFFFFFFFF.toInt()) // White color

            // Set shape type (RECTANGLE, OVAL, LINE, RING)
            shape = GradientDrawable.RECTANGLE

            // Set size (if needed, usually for custom shapes like rings)
            // setSize(200, 200) // Width, Height in pixels
        }
    }
}

