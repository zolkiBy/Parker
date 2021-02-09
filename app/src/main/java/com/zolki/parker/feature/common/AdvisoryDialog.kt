package com.zolki.parker.feature.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.zolki.parker.R
import com.zolki.parker.common.delegate.argument
import com.zolki.parker.common.delegate.argumentNullable
import com.zolki.parker.databinding.DialogAdvisoryBinding

class AdvisoryDialog : FullScreenDialogFragment() {

    private var title by argumentNullable<String>()
    private var description by argument<String>()
    private var btnText by argumentNullable<String>()

    private val binding by viewBinding(DialogAdvisoryBinding::bind)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_advisory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (title != null) {
            binding.title.text = title
        }
        if (btnText != null) {
            binding.actionBtn.text = btnText
        }
        binding.description.text = description
        binding.actionBtn.setOnClickListener {

            dismiss()
        }
    }
}