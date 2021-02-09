package com.zolki.parker.feature.common

import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.zolki.parker.R

open class FullScreenDialogFragment : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.Theme_FullScreenDialog)
    }

    override fun onStart() {
        val attrs = dialog?.window?.attributes
        attrs?.width = WindowManager.LayoutParams.MATCH_PARENT
        attrs?.height = WindowManager.LayoutParams.MATCH_PARENT

        dialog?.window?.attributes = attrs

        super.onStart()
    }
}