package com.fevziomurtekin.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

const val REQUEST_KEY = "request"

class HomeFragment : Fragment() {

    private var tvResult: TextView? = null
    private var btnShowDialog: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home,container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvResult = view.findViewById(R.id.tv_result)
        btnShowDialog = view.findViewById(R.id.btn_show_dialog)

        // this listen to result.
        parentFragmentManager.setFragmentResultListener(
            REQUEST_KEY,
            this,
            { key, data ->
                if(key == REQUEST_KEY){
                    val result = data.getBoolean("data").let { isAccept->
                        if(isAccept) "Accepted" else "Rejected"
                    }
                    tvResult?.text = result
                }
            }
        )

        // show bottom dialog
        btnShowDialog?.setOnClickListener {
            findNavController().navigate(R.id.homeDialog)
        }

    }
}