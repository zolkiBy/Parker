package com.zolki.parker.feature.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.zolki.parker.R
import com.zolki.parker.databinding.DialogPermissionExplanationBinding
import com.zolki.parker.feature.common.FullScreenDialogFragment
import org.koin.android.ext.android.get

class PermissionExplanationDialog : FullScreenDialogFragment() {

    private val viewModel: MapViewModel by navGraphViewModels(R.id.nav_graph) { MapViewModelFactory(get()) }
    private val binding by viewBinding(DialogPermissionExplanationBinding::bind)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_permission_explanation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            cancelBtn.setOnClickListener { dismiss() }
            okBtn.setOnClickListener {
                viewModel.onUserAgreeToShowPermissionRequest()
                dismiss()
            }
        }
    }
}