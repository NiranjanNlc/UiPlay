package org.lniranjan.beautifulbottomsheeet

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.lniranjan.beautifulbottomsheeet.databinding.ActivityBottomSheetBinding
/*
*
*
*
*
*
*
* */
class BottomSheetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomSheetBinding
    private lateinit var dialog: BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_bottom_sheet)
        // Persistent BottomSheet
        init_persistent_bottomsheet()
        // Modal BottomSheet
        init_modal_bottomsheet()
        binding.button.setOnClickListener {
            dialog.show()
        }
    }

    private fun init_modal_bottomsheet() {
        val modalbottomsheet: View = layoutInflater.inflate(R.layout.modal_bottom_sheet, null)
        dialog = BottomSheetDialog(this)
        dialog.setContentView(modalbottomsheet)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
    }

    private fun init_persistent_bottomsheet() {
//        TODO("Not yet implemented")
    }
    fun canceldialog(view: View)
    {
        Toast.makeText(getApplicationContext(), "cancel is Clicked ", Toast.LENGTH_LONG).show();
        dialog.dismiss();
    }
    fun deletedialog(view: View)
    {
        Toast.makeText(getApplicationContext(), "deleete  is Clicked ", Toast.LENGTH_LONG).show();
        dialog.dismiss();
    }
}