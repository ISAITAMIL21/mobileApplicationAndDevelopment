package com.example.exp_4

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import kotlin.math.abs

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private lateinit var mDetector: GestureDetectorCompat
    private lateinit var helloTextView: TextView
    private var doubleTapCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloTextView = findViewById(R.id.helloTextView)
        mDetector = GestureDetectorCompat(this, this)
        mDetector.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    override fun onDown(event: MotionEvent): Boolean {
        return true
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (e1 != null) {
            val diffX = e2.x - e1.x
            if (abs(diffX) > 100 && abs(velocityX) > 100) {
                if (diffX > 0) {
                    Toast.makeText(this, "Right Swipe", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Left Swipe", Toast.LENGTH_SHORT).show()
                }
                return true
            }
        }
        return false
    }

    override fun onLongPress(event: MotionEvent) {}

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onShowPress(event: MotionEvent) {}

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        return false
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        doubleTapCount++
        if (doubleTapCount == 1) {
            helloTextView.visibility = View.VISIBLE
        } else if (doubleTapCount == 2) {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            doubleTapCount = 0 // Reset for next time
        }
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        return false
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        return false
    }
}
