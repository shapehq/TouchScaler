package com.novasa.touchscaler

import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.util.SizeF
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "TouchScaler"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val touchScaler = TouchScaler(targetView)

        touchScaler.contentSize = with(targetView.drawable) {
            SizeF(intrinsicWidth.toFloat(), intrinsicHeight.toFloat())
        }

        contentView.setOnTouchListener(touchScaler)

        buttonReset.setOnClickListener {
            touchScaler.reset()
        }

        buttonTest.setOnClickListener {
            touchScaler.scale(3f, PointF(contentView.width.toFloat(), 0f))
            touchScaler.applyScaleAndTranslation()
        }

        touchScaler.onChangeListener = object : TouchScaler.OnChangeListener {
            override fun onTouchScalerChange(scaler: TouchScaler) {

            }
        }

        touchScaler.onModeChangeListener = object: TouchScaler.OnModeChangeListener {
            override fun onTouchScalerModeChange(scaler: TouchScaler, mode: TouchScaler.Mode) {
                Log.d(TAG, "Mode change: $mode")
            }
        }
    }
}
