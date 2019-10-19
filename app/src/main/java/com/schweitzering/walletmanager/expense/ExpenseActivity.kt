package com.schweitzering.walletmanager.expense

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.schweitzering.walletmanager.Constants.Companion.MAX_AMOUNT_DIGITS
import com.schweitzering.walletmanager.R
import kotlinx.android.synthetic.main.activity_expense.*
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope

class ExpenseActivity : AppCompatActivity() {

    private val viewModel: ExpenseViewModel by currentScope.inject()

    companion object {
        fun getIntent(context: Context) = Intent(context, ExpenseActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)
    }

    fun onNumberClicked(view: View) {
        view as Button
        if(view.text != "-") {
            if( (view.text == "." && !field_amount.text.contains(".") && field_amount.text.length!=MAX_AMOUNT_DIGITS-1
                        && field_amount.text.isNotEmpty()) || view.text != ".") {
                field_amount.hint = ""
                field_amount.text = "${field_amount.text}${view.text}"
            }
        } else {
            //remove last number
            if(field_amount.text.isNotEmpty()) {
                field_amount.text = field_amount.text.substring(0, field_amount.text.length-1)
            }
            if(field_amount.text.isEmpty()) {
                field_amount.hint = getString(R.string.amount_hint)
            }
        }
    }

    fun onContinueClicked(view: View) {
        val value = field_amount.text.toString().toDouble()
        if(value>0) {
            viewModel.addNewExpense(value)
        }
    }
}
