package com.dlgdev.goivcalc.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dlgdev.goivcalc.R
import com.dlgdev.goivcalc.models.CalcResults

class CalcResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {
        ButterKnife.bind(this, itemView)
    }

    @BindView(R.id.level) lateinit var level: TextView
    @BindView(R.id.stamina_iv) lateinit var stamina: TextView
    @BindView(R.id.attack_iv) lateinit var attack: TextView
    @BindView(R.id.defense_iv) lateinit var defense: TextView
    @BindView(R.id.percentage) lateinit var percentage: TextView

    var results = CalcResults(0, 0, 0, 0, false)
        set(value) {
            field = value
            level.text = level()
            stamina.text = value.stamina.toString()
            attack.text = value.attack.toString()
            defense.text = value.defense.toString()
            percentage.text = percentage()
        }

    fun level(): String {
        var lvl = results.level.toDouble()
        if(results.usedPowerUp) {
            lvl += 0.5
        }
        return lvl.toString()
    }

    fun percentage(): String {
        return String.format("%2.2f%%", (results.stamina + results.attack + results.defense) / 0.45)
    }
}