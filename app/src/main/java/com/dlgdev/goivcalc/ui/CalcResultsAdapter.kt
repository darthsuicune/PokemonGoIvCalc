package com.dlgdev.goivcalc.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dlgdev.goivcalc.R
import com.dlgdev.goivcalc.models.CalcResults
import javax.inject.Inject

open class CalcResultsAdapter @Inject constructor() : RecyclerView.Adapter<CalcResultsViewHolder>() {
    var results = emptyList<CalcResults>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CalcResultsViewHolder {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.calc_results_view_holder, parent, false)
        return CalcResultsViewHolder(v)
    }

    override fun onBindViewHolder(holder: CalcResultsViewHolder?, position: Int) {
        holder!!.results = results[position]
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun showResults(results: List<CalcResults>) {
        this.results = results
        notifyDataSetChanged()
    }
}

