package com.schweitzering.walletmanager.settings.categories.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityNewCategoryBinding
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import org.koin.androidx.scope.currentScope

class CRUDCategoryActivity : AppCompatActivity(), DataBindingProtocol {

    companion object {
        const val CATEGORY_TYPE = "transaction_category"

        fun getUpdateIntent(context: Context, transactionCategory: TransactionCategory): Intent {
            val intent = Intent(context, CRUDCategoryActivity::class.java)
            intent.putExtra(CATEGORY_TYPE, transactionCategory)
            return intent
        }

        fun getCreateIntent(context: Context, transaction: TransactionType): Intent {
            val intent = Intent(context, CRUDCategoryActivity::class.java)
            val category = TransactionCategory(category = transaction, type = "")
            intent.putExtra(CATEGORY_TYPE, category)
            return intent
        }
    }

    private val viewModel: CRUDCategoryViewModel by currentScope.inject()

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
