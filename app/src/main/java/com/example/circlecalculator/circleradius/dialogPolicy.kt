package com.example.circlecalculator.circleradius

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.dialog_policy.view.*

/**
 * Created by Costi on 6/3/2018.
 */
class dialogPolicy : DialogFragment() {

    interface OnOptionSelected {
        fun onPolicyDecline(isDeclined: Boolean)
        fun onPolicyAccept(isAccepted: Boolean)
    }
    var mOnOptionSelected: OnOptionSelected? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater!!.inflate(R.layout.dialog_policy, container, false)

        view.btnDecline.setOnClickListener {
            mOnOptionSelected!!.onPolicyDecline(true)
            dismiss()
        }

        view.btnAccept.setOnClickListener {
            try {
//                policy = "true"
//                SavePolicy(view.context, "policy.txt")
                mOnOptionSelected!!.onPolicyAccept(true)
                dismiss()
            }catch(e: Exception){

            }

        }

        view.tvPolicy.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://pixiefan.eu/privacy-policy")))
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawableResource(R.drawable.bg_transparent_corners)
        super.onActivityCreated(savedInstanceState)
        dialog.setCancelable(false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mOnOptionSelected = context as OnOptionSelected
        }catch (e: ClassCastException){
            throw ClassCastException(context.toString() + " in dialog policy")
        }
    }

}