package org.lniranjan.beautifulalertdialog

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.lniranjan.beautifulalertdialog.customization.CustomAlertDialog
import org.lniranjan.beautifulalertdialog.databinding.ActivityMainBinding

/* Custom linked over here :
* https://github.com/pedant/sweet-alert-dialog
* */
class BeautifulAlertDialogactivity : AppCompatActivity() ,View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.basic.setOnClickListener(this)
        binding.error.setOnClickListener (this)
        binding.success.setOnClickListener (this)
        binding.failure.setOnClickListener(this)
        
    }

   override fun onClick(v: View) {
        when (v.id) {
            R.id.basic -> {
                // default title "Here's a message!"
                val sd = CustomAlertDialog(this)
                sd.setCancelable(true)
                sd.setCanceledOnTouchOutside(true)
                sd.show()
            }
//            R.id.under_text_test -> CustomAlertDialog(this)
//                .setContentText("It's pretty, isn't it?")
//                .show()
            R.id.error -> CustomAlertDialog(this, CustomAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Something went wrong!")
                .show()
            R.id.success -> CustomAlertDialog(this, CustomAlertDialog.SUCCESS_TYPE)
                .setTitleText("Good job!")
                .setContentText("You clicked the button!")
                .show()
//            R.id.warning -> CustomAlertDialog(this, CustomAlertDialog.WARNING_TYPE)
//                .setTitleText("Are you sure?")
//                .setContentText("Won't be able to recover this file!")
//                .setConfirmText("Yes,delete it!")
//                .setConfirmClickListener(object : OnSweetClickListener() {
//                    fun onClick(sDialog: CustomAlertDialog) {
//                        // reuse previous dialog instance
//                        sDialog.setTitleText("Deleted!")
//                            .setContentText("Your imaginary file has been deleted!")
//                            .setConfirmText("OK")
//                            .setConfirmClickListener(null)
//                            .changeAlertType(CustomAlertDialog.SUCCESS_TYPE)
//                    }
//                })
//                .show()
//            R.id.warning_cancel_test -> CustomAlertDialog(this, CustomAlertDialog.WARNING_TYPE)
//                .setTitleText("Are you sure?")
//                .setContentText("Won't be able to recover this file!")
//                .setCancelText("No,cancel plx!")
//                .setConfirmText("Yes,delete it!")
//                .showCancelButton(true)
//                .setCancelClickListener(object : OnSweetClickListener() {
//                    fun onClick(sDialog: CustomAlertDialog) {
//                        // reuse previous dialog instance, keep widget user state, reset them if you need
//                        sDialog.setTitleText("Cancelled!")
//                            .setContentText("Your imaginary file is safe :)")
//                            .setConfirmText("OK")
//                            .showCancelButton(false)
//                            .setCancelClickListener(null)
//                            .setConfirmClickListener(null)
//                            .changeAlertType(CustomAlertDialog.ERROR_TYPE)
//
//                        // or you can new a CustomAlertDialog to show
//                        /* sDialog.dismiss();
//                                new CustomAlertDialog(SampleActivity.this, CustomAlertDialog.ERROR_TYPE)
//                                        .setTitleText("Cancelled!")
//                                        .setContentText("Your imaginary file is safe :)")
//                                        .setConfirmText("OK")
//                                        .show();*/
//                    }
//                })
//                .setConfirmClickListener(object : OnSweetClickListener() {
//                    fun onClick(sDialog: CustomAlertDialog) {
//                        sDialog.setTitleText("Deleted!")
//                            .setContentText("Your imaginary file has been deleted!")
//                            .setConfirmText("OK")
//                            .showCancelButton(false)
//                            .setCancelClickListener(null)
//                            .setConfirmClickListener(null)
//                            .changeAlertType(CustomAlertDialog.SUCCESS_TYPE)
//                    }
//                })
//                .show()
//            R.id.custom_img_test -> CustomAlertDialog(this, CustomAlertDialog.CUSTOM_IMAGE_TYPE)
//                .setTitleText("Sweet!")
//                .setContentText("Here's a custom image.")
//                .setCustomImage(R.drawable.custom_img)
//                .show()
//            R.id.progress_dialog -> {
//                val pDialog: CustomAlertDialog =
//                    CustomAlertDialog(this, CustomAlertDialog.PROGRESS_TYPE)
//                        .setTitleText("Loading")
//                pDialog.show()
//                pDialog.setCancelable(false)
//                object : CountDownTimer(800 * 7, 800) {
//                    override fun onTick(millisUntilFinished: Long) {
//                        // you can change the progress bar color by ProgressHelper every 800 millis
//                        i++
//                        when (i) {
//                            0 -> pDialog.getProgressHelper()
//                                .setBarColor(resources.getColor(R.color.blue_btn_bg_color))
//                            1 -> pDialog.getProgressHelper()
//                                .setBarColor(resources.getColor(R.color.material_deep_teal_50))
//                            2 -> pDialog.getProgressHelper()
//                                .setBarColor(resources.getColor(R.color.success_stroke_color))
//                            3 -> pDialog.getProgressHelper()
//                                .setBarColor(resources.getColor(R.color.material_deep_teal_20))
//                            4 -> pDialog.getProgressHelper()
//                                .setBarColor(resources.getColor(R.color.material_blue_grey_80))
//                            5 -> pDialog.getProgressHelper()
//                                .setBarColor(resources.getColor(R.color.warning_stroke_color))
//                            6 -> pDialog.getProgressHelper()
//                                .setBarColor(resources.getColor(R.color.success_stroke_color))
//                        }
//                    }
//
//                    override fun onFinish() {
//                        i = -1
//                        pDialog.setTitleText("Success!")
//                            .setConfirmText("OK")
//                            .changeAlertType(CustomAlertDialog.SUCCESS_TYPE)
//                    }
//                }.start()
//            }
        }
    }
}