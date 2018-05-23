package com.dlgdev.goivcalc.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import com.dlgdev.goivcalc.R
import com.dlgdev.goivcalc.models.Calculator
import com.dlgdev.goivcalc.models.Pokemon
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*
import javax.inject.Inject

class CalculatorActivity : DaggerAppCompatActivity() {
    @Inject lateinit var calc: Calculator
    @Inject lateinit var nameAdapter: PokemonNameAdapter
    @Inject lateinit var resultsAdapter: CalcResultsAdapter
    @Inject lateinit var recyclerLayoutManager: RecyclerView.LayoutManager

    //For dust
    private val DUST_SELECTION = "dust_selection"
    var dust_selection = 9 //Default to 2500 dust
    var pokemon: Pokemon? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(DUST_SELECTION, dust_view.selectedItemPosition)
    }

    fun restoreState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            dust_selection = savedInstanceState[DUST_SELECTION] as Int
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        restoreState(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupNameView()
        setupDustView()
        setupCpView()
        setupHpView()
        setupUsedPowerUp()
        setupHpCheckBox()
        setupAtkCheckBox()
        setupDefCheckBox()
        setupLeaderSayingView()

        setupResultsView()
    }

    private fun setupResultsView() {
        results_view.adapter = resultsAdapter
        results_view.layoutManager = recyclerLayoutManager
    }

    private fun setupNameView() {
        name_view.setAdapter(nameAdapter)
        name_view.onItemClickListener = AdapterView.OnItemClickListener {
            adapter, _, position, _ ->
            pokemon = adapter.getItemAtPosition(position) as Pokemon
            runCalculation()
            base_stats.text = "${pokemon!!.attack}/${pokemon!!.defense}/${pokemon!!.stamina}"
        }
    }

    fun setupDustView() {
        dust_view.setSelection(dust_selection)
        dust_view.onItemSelectedListener = object : NoOpItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calc.dust = Integer.parseInt(parent?.getItemAtPosition(position) as String)
                runCalculation()
            }
        }
    }

    private fun setupHpView() {
        hp_view.addTextChangedListener(object : NoOpTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                try {
                    val hp = Integer.parseInt(s.toString())
                    if (hp in 1..999) {
                        calc.hp = hp
                        runCalculation()
                    }
                } catch (nfe: NumberFormatException) {
                    hp_view.error = getString(R.string.error_number_not_valid)
                }
            }
        })
    }

    private fun setupCpView() {
        cp_view.addTextChangedListener(object : NoOpTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                try {
                    val cp = Integer.parseInt(s.toString())
                    if (cp in 1..9999) {
                        calc.cp = cp
                        runCalculation()
                    }
                } catch (nfe: NumberFormatException) {
                    cp_view.error = getString(R.string.error_number_not_valid)
                }
            }
        })
    }

    private fun setupUsedPowerUp() {
        power_up_check_box.setOnCheckedChangeListener { _, isChecked ->
            calc.usedPowerUp = isChecked
            runCalculation()
        }
    }

    private fun setupHpCheckBox() {
        hp_check_box.setOnCheckedChangeListener { _, isChecked ->
            calc.hpIsMax = isChecked
            runCalculation()
        }
    }

    private fun setupAtkCheckBox() {
        atk_check_box.setOnCheckedChangeListener { _, isChecked ->
            calc.atkIsMax = isChecked
            runCalculation()
        }
    }

    private fun setupDefCheckBox() {
        def_check_box.setOnCheckedChangeListener { _, isChecked ->
            calc.defIsMax = isChecked
            runCalculation()
        }
    }

    fun setupLeaderSayingView() {
        iv_leader_saying.onItemSelectedListener = object : NoOpItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calc.maxValue = Calculator.LeaderSayings.fromSpinner(position)
                runCalculation()
            }
        }
    }

    private fun runCalculation() {
        if (pokemon != null) {
            resultsAdapter.showResults(calc.calculate(pokemon!!))
        }
    }




    interface NoOpItemSelectedListener : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) { //NOOP
        }
    }

    interface NoOpTextWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //NOOP
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //NOOP
        }
    }
}
