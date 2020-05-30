package com.schweitzering.data.categories

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.data.R
import com.schweitzering.domain.transaction.TransactionType

@Entity(tableName = "transaction_categories")
data class TransactionCategoryEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var type: TransactionType,
    var name: String
) {
    companion object {
        fun getPredefinedCategories() =  listOf(
            TransactionCategoryEntity(type = TransactionType.EXPENSE, name = "Food"),
            TransactionCategoryEntity(type = TransactionType.EXPENSE, name = "Clothes"),
            TransactionCategoryEntity(type = TransactionType.EXPENSE, name = "Car"),
            TransactionCategoryEntity(type = TransactionType.INCOME, name = "Salary"),
            TransactionCategoryEntity(type = TransactionType.INCOME, name = "Sale"),
            TransactionCategoryEntity(type = TransactionType.INCOME, name = "Loan"),
            TransactionCategoryEntity(type = TransactionType.INVESTMENT, name = "Bonds"),
            TransactionCategoryEntity(type = TransactionType.INVESTMENT, name = "Shares"),
            TransactionCategoryEntity(type = TransactionType.SAVING, name = "Cash"),
            TransactionCategoryEntity(type = TransactionType.SAVING, name = "Bank Account")
        )
    }
}