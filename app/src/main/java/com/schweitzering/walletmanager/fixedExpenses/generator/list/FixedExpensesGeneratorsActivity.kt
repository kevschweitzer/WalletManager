package com.schweitzering.walletmanager.fixedExpenses.generator.list

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.schweitzering.walletmanager.R
import kotlinx.android.synthetic.main.activity_fixed_expenses_generators.*
import org.koin.androidx.scope.currentScope

class FixedExpensesGeneratorsActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, FixedExpensesGeneratorsActivity::class.java)
    }

    private val viewModel: FixedExpensesGeneratorsViewModel by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixed_expenses_generators)

        observeFixedExpenseGenerators()
    }

    private fun observeFixedExpenseGenerators() {
        viewModel.fixedExpenseGeneratos.observe(this, Observer {
            generators_list.apply {
                adapter = FixedExpensesGeneratorsAdapter(it, viewModel)
                layoutManager = LinearLayoutManager(this@FixedExpensesGeneratorsActivity)
            }
        })
    }
}
