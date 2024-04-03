//package com.example.chipotlerep
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.MotionEvent
//import androidx.viewpager2.widget.ViewPager2
//
//
//class CustomViewPager2 : ViewPager2 {
//    private var viewPager: ViewPager2
//
//    constructor(context: Context, viewPager: ViewPager2) : super(context) {
//        this.viewPager = viewPager
//    }
//
//    constructor(context: Context, attrs: AttributeSet, viewPager: ViewPager2) : super(context, attrs) {
//        this.viewPager = viewPager
//    }
//
//    private var startY: Float = 0f
//
//    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        if (ev?.actionMasked == MotionEvent.ACTION_DOWN) {
//            startY = ev.y
//        } else if (ev?.actionMasked == MotionEvent.ACTION_MOVE) {
//            val deltaY = ev.y - startY
//            if (deltaY > 0 && Math.abs(deltaY) > SWIPE_THRESHOLD) {
//                // Downward swipe detected, show cart fragment
//                (viewPager.context as? MainActivity)?.showCartFragment()
//                return true // Intercept the touch event
//            }
//        }
//        return super.onInterceptTouchEvent(ev)
//    }
//
//    companion object {
//        private const val SWIPE_THRESHOLD = 100 // Adjust as needed
//    }
//}