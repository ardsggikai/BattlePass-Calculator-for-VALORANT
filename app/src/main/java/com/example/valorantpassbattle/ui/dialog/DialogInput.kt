package com.example.valorantpassbattle.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.model.Historic.UserInputsTier
import com.example.valorantpassbattle.model.PassBattle.Tier
import com.example.valorantpassbattle.ui.activity.MainActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.dialog_tierinput.view.*
import kotlinx.android.synthetic.main.dialog_title.view.*

class DialogInput(context: Context) : AlertDialog(context) {
    var tvTierIndex: TextInputEditText
    var tvTierExpMissing: TextInputEditText
    var mDialogView: View
    var builder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    init {
        val inflater = this.layoutInflater

        val titleView: View = inflater.inflate(R.layout.dialog_title, null)
        titleView.title.text = "Tier Form"

        mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_tierinput, null)

        tvTierIndex = mDialogView.tierinput_dialog_et_level_current
        tvTierExpMissing = mDialogView.tierinput_dialog_et_exp_missing

        builder = Builder(context).setView(mDialogView).setCustomTitle(titleView)
    }

    override fun show() {
        dialog = builder.show()
        setOnClickListener()
    }

    fun setOnClickListener() {
        mDialogView.tierinput_dialog_btn_save.setOnClickListener {
            val tierInputCurrent =
                if (MainActivity.historic.isEmpty()) null else MainActivity.historic.last()
            val tierCurrent = MainActivity.passBattle.getTier(tierInputCurrent?.tierCurrent ?: 1)
            if (validadeTierIndex(tvTierIndex, tierCurrent?.index ?: 0)) {
                val tierInput =
                    MainActivity.passBattle.getTier(tvTierIndex.text.toString().toInt())!!
                if (validadeTierExpMissing(tvTierExpMissing, tierInput)) {
                    val tier = tvTierIndex.text.toString().toInt()
                    val expMissing = tvTierExpMissing.text.toString().toInt()
                    val inputUser = UserInputsTier(tier, expMissing)
                    MainActivity.historic.add(inputUser)
                    dialog.dismiss()
                }
            }
        }
        mDialogView.tierinput_dialog_btn_cancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun validadeTierIndex(tv: TextView, index: Int): Boolean {
        val tierStr = tv.text.toString()
        val ultimoTier =
            if (MainActivity.historic.isEmpty()) 0 else MainActivity.historic.last().tierCurrent
        if (tierStr == "") tv.error = "Insira um tier!"
        if (tierStr.isNotEmpty()) {
            if (tierStr.length <= 3) {
                val tierInt = tierStr.toInt()
                if ((tierInt < index) or (tierInt > 50)) tv.error =
                    "Insira um tier entre ${index} e 50!"
                if (tierInt < ultimoTier) tv.error = "Insira um tier maior que ${ultimoTier}!"
            } else {
                tv.error = "Insira um tier menor que 50!"
            }
        }

        return tv.error == null
    }

    fun validadeTierExpMissing(tv: TextView, tier: Tier): Boolean {
        val tierStr = tv.text.toString()
        if (tierStr == "") tv.error = "Insira o EXP faltando!"
        if (tierStr.isNotEmpty()) {
            if (tierStr.length <= 5) {
                val tierInt = tierStr.toInt()
                if ((tierInt < 0) or (tierInt > tier.expMissing)) tv.error =
                    "Insira um EXP entre 0 e ${tier.expMissing}!"
            } else {
                tv.error = "Insira um EXP menor ou igual a ${tier.expMissing}!"
            }
        }
        return tv.error == null
    }
}