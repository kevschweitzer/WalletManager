package com.schweitzering.walletmanager.settings.categories.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.schweitzering.domain.transaction.TransactionCategory
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityNewCategoryBinding
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import org.koin.androidx.scope.currentScope

class NewCategoryActivity : AppCompatActivity(), DataBindingProtocol {

    companion object {
        const val NEW_CATEGORY_TYPE = "transaction_category"
        fun getIntent(context: Context, transaction: TransactionCategory): Intent {
            val intent = Intent(context, NewCategoryActivity::class.java)
            intent.putExtra(NEW_CATEGORY_TYPE, transaction)
            return intent
        }
    }

    private val viewModel: NewCategoryViewModel by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
        viewModel.handleIntent(intent)
    }

    override fun setDataBinding() {
        val binding = DataBindingUtil.setContentView<ActivityNewCategoryBinding>(this,
            R.layout.activity_new_category)
        binding.viewModel = viewModel
    }
}
