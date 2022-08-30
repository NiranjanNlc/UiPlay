package org.lniranjan.beautifulprogressbar

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.ContentValues
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.Choreographer
import android.view.Choreographer.FrameCallback
import android.view.animation.DecelerateInterpolator


class WaveDrawable : Drawable, Animatable, AnimatorUpdateListener {
    private var mDrawable: Drawable? = null
    private var mWidth = 0
    private var mHeight = 0
    private var mWaveHeight = UNDEFINED_VALUE
    private var mWaveLength = UNDEFINED_VALUE
    private var mWaveStep = UNDEFINED_VALUE
    private var mWaveOffset = 0
    private var mWaveLevel = 0
    private var mAnimator: ValueAnimator? = null
    private var mProgress = 0.3f
    private var mPaint: Paint? = null
    private var mMask: Bitmap? = null
    private val mMatrix = Matrix()
    private var mRunning = false
    private var mIndeterminate = false
    private var mCurFilter: ColorFilter? = null
    private val mFrameCallback: FrameCallback = object : FrameCallback {
        override fun doFrame(l: Long) {
            invalidateSelf()
            if (mRunning) {
                Choreographer.getInstance().postFrameCallback(this)
            }
        }
    }

    constructor(drawable: Drawable?) {
        init(drawable)
    }

    constructor(context: Context, imgRes: Int) {
        val drawable: Drawable?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = context.getDrawable(imgRes)
        } else {
            drawable = context.resources.getDrawable(imgRes)
        }
        init(drawable)
    }

    private fun init(drawable: Drawable?) {
        mDrawable = drawable
        mMatrix.reset()
        mPaint = Paint()
        mPaint!!.isFilterBitmap = false
        mPaint!!.color = Color.BLACK
        mPaint!!.xfermode = sXfermode
        mWidth = mDrawable!!.intrinsicWidth
        mHeight = mDrawable!!.intrinsicHeight
        if (mWidth > 0 && mHeight > 0) {
            mWaveLength = mWidth
            mWaveHeight = Math.max(8, (mHeight * WAVE_HEIGHT_FACTOR).toInt())
            mWaveStep = Math.max(1, (mWidth * WAVE_SPEED_FACTOR).toInt())
            updateMask(mWidth, mWaveLength, mWaveHeight)
        }
        setProgress(0f)
        start()
    }

    /**
     * Set wave move distance (in pixels) in very animation frame
     * @param step distance in pixels
     */
    fun setWaveSpeed(step: Int) {
        mWaveStep = Math.min(step, mWidth / 2)
    }

    /**
     * Set wave amplitude (in pixels)
     * @param amplitude
     */
    fun setWaveAmplitude(amplitude: Int) {
        var amplitude = amplitude
        amplitude = Math.max(1, Math.min(amplitude, mHeight / 2))
        val height = amplitude * 2
        if (mWaveHeight != height) {
            mWaveHeight = height
            updateMask(mWidth, mWaveLength, mWaveHeight)
            invalidateSelf()
        }
    }

    /**
     * Set wave length (in pixels)
     * @param length
     */
    fun setWaveLength(length: Int) {
        var length = length
        length = Math.max(8, Math.min(mWidth * 2, length))
        if (length != mWaveLength) {
            mWaveLength = length
            updateMask(mWidth, mWaveLength, mWaveHeight)
            invalidateSelf()
        }
    }

    /**
     * Set customised animator for wave loading animation
     * @param animator
     */
    fun setIndeterminateAnimator(animator: ValueAnimator) {
        if (mAnimator === animator) {
            return
        }
        if (mAnimator != null) {
            mAnimator!!.removeUpdateListener(this)
            mAnimator!!.cancel()
        }
        mAnimator = animator
        if (mAnimator != null) {
            mAnimator!!.addUpdateListener(this)
        }
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        mDrawable!!.setBounds(left, top, right, bottom)
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        updateBounds(bounds)
    }

    private fun updateBounds(bounds: Rect) {
        if (bounds.width() <= 0 || bounds.height() <= 0) {
            return
        }
        if (mWidth < 0 || mHeight < 0) {
            mWidth = bounds.width()
            mHeight = bounds.height()
            if (mWaveHeight == UNDEFINED_VALUE) {
                mWaveHeight = Math.max(8, (mHeight * WAVE_HEIGHT_FACTOR).toInt())
            }
            if (mWaveLength == UNDEFINED_VALUE) {
                mWaveLength = mWidth
            }
            if (mWaveStep == UNDEFINED_VALUE) {
                mWaveStep = Math.max(1, (mWidth * WAVE_SPEED_FACTOR).toInt())
            }
            updateMask(mWidth, mWaveLength, mWaveHeight)
        }
    }

    override fun getIntrinsicHeight(): Int {
        return mHeight
    }

    override fun getIntrinsicWidth(): Int {
        return mWidth
    }

    override fun draw(canvas: Canvas) {
        mDrawable!!.colorFilter = sGrayFilter
        mDrawable!!.draw(canvas)
        mDrawable!!.colorFilter = mCurFilter
        if (mProgress <= 0.001f) {
            return
        }
        val sc = canvas.saveLayer(
            0f, 0f, mWidth.toFloat(), mHeight.toFloat(), null)
        if (mWaveLevel > 0) {
            canvas.clipRect(0, mWaveLevel, mWidth, mHeight)
        }
        mDrawable!!.draw(canvas)
        if (mProgress >= 0.999f) {
            return
        }
        mWaveOffset += mWaveStep
        if (mWaveOffset > mWaveLength) {
            mWaveOffset -= mWaveLength
        }
        if (mMask != null) {
            mMatrix.setTranslate(-mWaveOffset.toFloat(), mWaveLevel.toFloat())
            canvas.drawBitmap(mMask!!, mMatrix, mPaint)
        }
        canvas.restoreToCount(sc)
    }

    override fun onLevelChange(level: Int): Boolean {
        setProgress(level / 10000f)
        return true
    }

    override fun setAlpha(i: Int) {
        mDrawable!!.alpha = i
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mCurFilter = colorFilter
        invalidateSelf()
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun start() {
        mRunning = true
        Choreographer.getInstance().postFrameCallback(mFrameCallback)
    }

    override fun stop() {
        mRunning = false
        Choreographer.getInstance().removeFrameCallback(mFrameCallback)
    }

    override fun isRunning(): Boolean {
        return mRunning
    }

    override fun onAnimationUpdate(animation: ValueAnimator) {
        if (mIndeterminate) {
            setProgress(animation.animatedFraction)
            if (!mRunning) {
                invalidateSelf()
            }
        }
    }

    /**
     * Set the wave loading in indeterminate mode or not
     * @param indeterminate
     */
    var isIndeterminate: Boolean
        get() = mIndeterminate
        set(indeterminate) {
            mIndeterminate = indeterminate
            if (mIndeterminate) {
                if (mAnimator == null) {
                    mAnimator = defaultAnimator
                }
                mAnimator!!.addUpdateListener(this)
                mAnimator!!.start()
            } else {
                if (mAnimator != null) {
                    mAnimator!!.removeUpdateListener(this)
                    mAnimator!!.cancel()
                }
                level = calculateLevel()
            }
        }
    private val defaultAnimator: ValueAnimator
        private get() {
            val animator = ValueAnimator.ofFloat(0f, 1f)
            animator.interpolator = DecelerateInterpolator()
            animator.repeatMode = ValueAnimator.RESTART
            animator.repeatCount = ValueAnimator.INFINITE
            animator.duration = 5000
            return animator
        }

    private fun setProgress(progress: Float) {
        mProgress = progress
        mWaveLevel = mHeight - ((mHeight + mWaveHeight) * mProgress).toInt()
        invalidateSelf()
    }

    private fun calculateLevel(): Int {
        return (mHeight - mWaveLevel) * 10000 / (mHeight + mWaveHeight)
    }

    private fun updateMask(width: Int, length: Int, height: Int) {
        if ((width <= 0) || (length <= 0) || (height <= 0)) {
            Log.w(ContentValues.TAG, "updateMask: size must > 0")
            mMask = null
            return
        }
        val count = Math.ceil(((width + length) / length.toFloat()).toDouble()).toInt()
        val bm = Bitmap.createBitmap(length * count, height, Bitmap.Config.ARGB_8888)
        val c = Canvas(bm)
        val p = Paint(Paint.ANTI_ALIAS_FLAG)
        val amplitude = height / 2
        val path = Path()
        path.moveTo(0f, amplitude.toFloat())
        val stepX = length / 4f
        var x = 0f
        var y = -amplitude.toFloat()
        for (i in 0 until count * 2) {
            x += stepX
            path.quadTo(x, y, x + stepX, amplitude.toFloat())
            x += stepX
            y = bm.height - y
        }
        path.lineTo(bm.width.toFloat(), height.toFloat())
        path.lineTo(0f, height.toFloat())
        path.close()
        c.drawPath(path, p)
        mMask = bm
    }

    companion object {
        private val WAVE_HEIGHT_FACTOR = 0.2f
        private val WAVE_SPEED_FACTOR = 0.02f
        private val UNDEFINED_VALUE = Int.MIN_VALUE
        private val sXfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        private val sGrayFilter: ColorFilter = ColorMatrixColorFilter(
            floatArrayOf(
                0.264f, 0.472f, 0.088f, 0f, 0f,
                0.264f, 0.472f, 0.088f, 0f, 0f,
                0.264f, 0.472f, 0.088f, 0f, 0f, 0f, 0f, 0f, 1f, 0f
            )
        )
    }
}
