package com.example.circlecalculator.circleradius

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RadioButton
import kotlinx.android.synthetic.main.dialog_setting.view.*

/**
 * Created by Costi on 5/28/2018.
 */
class dialogSetting: DialogFragment() {
    var decimalSave = "decimalSave.txt"

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater!!.inflate(R.layout.dialog_setting, container, false)

        var mDecimal = LoadSavedData(view.context, decimalSave, "#.##")

        for (i in 0 until view.rgDecimals.childCount){
            var id = view.rgDecimals.getChildAt(i).id
            var rb = view.findViewById<RadioButton>(id) as RadioButton
            rb.isChecked = (rb.text == mDecimal)
        }

        SaveDecimal(view)
        view.btnCancel.setOnClickListener { dialog.dismiss() }
        view.btnOk.setOnClickListener {
            SaveDecimalPoints(view.context, decimalSave)
            dismiss()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        super.onActivityCreated(savedInstanceState)
        dialog.window.attributes.windowAnimations = R.style.dialog_animation
    }

    fun SaveDecimal(view: View){
        //var decimals = arrayOf("#.#", "#.##", "#.###", "#.####", "#.#####", "#.######", "#.#######", "#.########")
        view.rgDecimals.setOnCheckedChangeListener { radioGroup, id ->
            var rbDecimal = view.findViewById<RadioButton>(id)
            saveDecimal = rbDecimal.text.toString()
        }
    }
}