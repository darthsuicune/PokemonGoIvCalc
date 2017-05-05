package com.dlgdev.goivcalc.ui

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
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
    }

    private fun setupNameView() {
        name_view.setAdapter(nameAdapter)
        name_view.onItemClickListener = AdapterView.OnItemClickListener {
            adapter, _, position, _ -> calc.pokemon = adapter.getItemAtPosition(position) as Pokemon
            resultsAdapter.showResults(calc.calculate())
        }
    }

    fun setupDustView() {
        dust_view.onItemSelectedListener = object : NoOpItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calc.dust = Integer.parseInt(parent?.getItemAtPosition(position) as String)
                resultsAdapter.showResults(calc.calculate())
            }
        }
    }

    private fun setupHpView() {
        hp_view.addTextChangedListener(object : NoOpTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                try {
                    val hp = Integer.parseInt(s.toString())
                    if (hp in 1..799) {
                        calc.hp = hp
                        resultsAdapter.showResults(calc.calculate())
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
                        resultsAdapter.showResults(calc.calculate())
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
            resultsAdapter.showResults(calc.calculate())
        }
    }

    private fun setupHpCheckBox() {
        hp_check_box.setOnCheckedChangeListener { _, isChecked ->
            calc.hpIsMax = isChecked
            resultsAdapter.showResults(calc.calculate())
        }
    }

    private fun setupAtkCheckBox() {
        atk_check_box.setOnCheckedChangeListener { _, isChecked ->
            calc.atkIsMax = isChecked
            resultsAdapter.showResults(calc.calculate())
        }
    }

    private fun setupDefCheckBox() {
        def_check_box.setOnCheckedChangeListener { _, isChecked ->
            calc.defIsMax = isChecked
            resultsAdapter.showResults(calc.calculate())
        }
    }

    fun setupLeaderSayingView() {
        iv_leader_saying.onItemSelectedListener = object : NoOpItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calc.maxValue = Calculator.LeaderSayings.fromSpinner(position)
                resultsAdapter.showResults(calc.calculate())
            }
        }
    }

    interface NoOpItemSelectedListener : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) { //NOOP
        }
    }

    interface NoOpTextWatcher: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //NOOP
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //NOOP
        }
    }
}
