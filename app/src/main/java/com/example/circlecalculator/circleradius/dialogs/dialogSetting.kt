package com.example.circlecalculator.circleradius.dialogs


import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RadioButton
import com.example.circlecalculator.circleradius.utils.LoadSavedData
import com.example.circlecalculator.circleradius.R
import com.example.circlecalculator.circleradius.ResultViewBuilder
import com.example.circlecalculator.circleradius.utils.SaveDecimalPoints
import com.example.circlecalculator.circleradius.utils.saveDecimal
import kotlinx.android.synthetic.main.dialog_setting.view.*

/**
 * Created by Costi on 5/28/2018.
 */
class dialogSetting: DialogFragment() {

    interface OnDecimalSelected{
        fun onDecimalSelected()
    }
    var decimalSelected: OnDecimalSelected? = null

    var decimalSave = "decimalSave.txt"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.dialog_setting, container, false)

        val mDecimal = LoadSavedData(view.context, decimalSave, "#.##")

        for (i in 0 until view.rgDecimals.childCount){
            val id = view.rgDecimals.getChildAt(i).id
            val rb = view.findViewById<RadioButton>(id) as RadioButton
            rb.isChecked = (rb.text == mDecimal)
        }

        SaveDecimal(view)
        view.btnCancel.setOnClickListener { dialog.dismiss() }
        view.btnOk.setOnClickListener {
            SaveDecimalPoints(view.context, decimalSave)
            decimalSelected!!.onDecimalSelected()
            dismiss()
        }

        return view

//        var buildDialog = ResultViewBuilder(context)
//        var bv = buildDialog.myView("Ce faci boss", "2000 dulars")
//        val view: View = bv
//
//        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        super.onActivityCreated(savedInstanceState)
        dialog.window.attributes.windowAnimations = R.style.dialog_animation
    }

    fun SaveDecimal(view: View){
        //var decimals = arrayOf("#.#", "#.##", "#.###", "#.####", "#.#####", "#.######", "#.#######", "#.########")
        view.rgDecimals.setOnCheckedChangeListener { radioGroup, id ->
            val rbDecimal = view.findViewById<RadioButton>(id)
            saveDecimal = rbDecimal.text.toString()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            decimalSelected = context as OnDecimalSelected
        }catch (e: ClassCastException){
            throw ClassCastException(context.toString() + " in dialog unitMeasure" + e)
        }
    }
}