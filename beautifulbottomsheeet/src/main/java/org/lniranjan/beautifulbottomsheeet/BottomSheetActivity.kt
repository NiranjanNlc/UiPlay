package org.lniranjan.beautifulbottomsheeet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.lniranjan.beautifulbottomsheeet.databinding.ActivityBottomSheetBinding

class BottomSheetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_bottom_sheet)
    }
}