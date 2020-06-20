package com.asif.utility

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.asif.happypagination.R


class Utils {
    companion object {
//        /*
//        * show dialog
//        * */
        @JvmStatic
        fun showLoadingDialog(context: Context): ProgressDialog {
            val progressDialog = ProgressDialog(context, R.style.ProgressBar)

            progressDialog.show()
            if (progressDialog.window != null) {
                progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            progressDialog.setContentView(R.layout.progress_dialog)
            progressDialog.isIndeterminate = true
            progressDialog.setCancelable(false)
            progressDialog.setCanceledOnTouchOutside(false)
            return progressDialog
        }






    }


}