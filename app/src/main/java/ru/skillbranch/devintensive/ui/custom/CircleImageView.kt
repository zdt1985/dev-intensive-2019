package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.core.content.ContextCompat
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.R

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ImageView(context, attrs, defStyleAttr) {
    companion object {
        private const val DEFAULT_BORDER_COLOR: Int = Color.WHITE
    }

    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth = 2

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderColor = a.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_BORDER_COLOR)
            borderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_cv_borderWidth, borderWidth)
            a.recycle()
        }
    }

    fun getBorderWidth():Int = borderWidth

    fun setBorderWidth(@Dimension dp:Int) {
        borderWidth = dp
        this.invalidate()
    }

    fun getBorderColor():Int = borderColor

    fun setBorderColor(hex:String) {
        borderColor = Color.parseColor(hex)
        this.invalidate()
    }
    fun setBorderColor(@ColorRes colorId: Int) {
        borderColor = ContextCompat.getColor(App.applicationContext(), colorId)
        this.invalidate()
    }
}