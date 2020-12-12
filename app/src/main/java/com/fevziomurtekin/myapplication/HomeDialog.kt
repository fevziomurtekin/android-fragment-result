package com.fevziomurtekin.myapplication

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class HomeDialog : BottomSheetDialogFragment() {

    private var btnAccept: Button? = null
    private var btnReject: Button? = null

    override fun onStart() {
        super.onStart()
        (requireView().parent as? View)?.let { safeView ->
            BottomSheetBehavior.from(safeView).apply {
                state = BottomSheetBehavior.STATE_EXPANDED
                setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(p0: View, p1: Int) {
                        if (p1 == BottomSheetBehavior.STATE_DRAGGING) {
                            state = BottomSheetBehavior.STATE_EXPANDED
                        }
                    }

                    override fun onSlide(p0: View, p1: Float) {}
                })
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.dialog_home, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAccept = view.findViewById(R.id.btnAccept)
        btnReject = view.findViewById(R.id.btnReject)
        setListeners()  
    }

    private fun setListeners() {
        btnAccept?.setOnClickListener { setResult(true) }
        btnReject?.setOnClickListener { setResult(false) }
    }

    private fun setResult(isAccept: Boolean) {
        parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf("data" to isAccept))
        findNavController().popBackStack()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        setResult(false)
    }

}
