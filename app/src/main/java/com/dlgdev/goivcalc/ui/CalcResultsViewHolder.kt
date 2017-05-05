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

    var results = CalcResults(0, 0, 0, 0)
        set(value) {
            field = value
            level.text = value.level.toString()
            stamina.text = value.stamina.toString()
            attack.text = value.attack.toString()
            defense.text = value.defense.toString()
        }
}