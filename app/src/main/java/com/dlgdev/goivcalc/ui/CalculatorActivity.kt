package com.dlgdev.goivcalc.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import com.dlgdev.goivcalc.R
import com.dlgdev.goivcalc.models.Calculator
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*
import javax.inject.Inject

class CalculatorActivity : DaggerAppCompatActivity() {
    @Inject lateinit var calc: Calculator
    @Inject lateinit var nameAdapter: PokemonNameAdapter

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
    }

    fun setupDustView() {
        dust_view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                TODO("not implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupHpView() {
        hp_view.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                TODO("not implemented")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

    private fun setupCpView() {
        cp_view.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            TODO("not implemented")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    })

    }

    private fun setupUsedPowerUp() {
        power_up_check_box.setOnCheckedChangeListener { _, isChecked ->
            calc.usedPowerUp = isChecked
        }
    }

    private fun setupHpCheckBox() {
        hp_check_box.setOnCheckedChangeListener { _, isChecked ->
            calc.hpIsMax = isChecked
        }
    }

    private fun setupAtkCheckBox() {
        atk_check_box.setOnCheckedChangeListener { _, isChecked ->
            calc.atkIsMax = isChecked
        }
    }

    private fun setupDefCheckBox() {
        def_check_box.setOnCheckedChangeListener { _, isChecked ->
            calc.defIsMax = isChecked
        }
    }

    fun setupLeaderSayingView() {
        iv_leader_saying.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                TODO("not implemented")
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}
