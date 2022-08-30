package org.lniranjan.beautifulprogressbar

import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import org.lniranjan.beautifulprogressbar.databinding.ProgressBarBinding

class ProgressBarActivity : AppCompatActivity() {
    private lateinit var binding: ProgressBarBinding
    private lateinit var waveDrawable: WaveDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.progress_bar)
        waveDrawable = WaveDrawable(this,R.drawable.progress)
        binding.image.apply {
           setImageDrawable(waveDrawable)
       }
        setWaveDrawable(waveDrawable)
        val  chromeWave =  WaveDrawable(this, R.drawable.chrome_logo);
        binding.image2.setImageDrawable(chromeWave)
//         Set customised animator here
        val  animator = ValueAnimator.ofFloat(0F, 1F);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(4000);
        animator.setInterpolator(AccelerateDecelerateInterpolator());
        chromeWave.setIndeterminateAnimator(animator);
//        chromeWave.setIndeterminate(true);

        val  color = getResources().getColor(R.color.colorAccent);
        val  colorWave =  WaveDrawable(ColorDrawable(color));
        binding.view.setBackground(colorWave);
//        colorWave.setIndeterminate(true)
    }

    private fun setWaveDrawable(waveDrawable: WaveDrawable) {
    waveDrawable.apply{
            setWaveAmplitude(50)
            setWaveSpeed(50)
            setWaveLength(50)
     }
    }
}