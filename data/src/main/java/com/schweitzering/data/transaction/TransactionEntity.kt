package com.schweitzering.data.transaction

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.schweitzering.data.categories.TransactionCategoryEntity
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.transaction.TransactionType
import java.sql.Timestamp

@Entity(tableName = "transactions",
    foreignKeys = [ForeignKey(entity = TransactionCategoryEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("categoryId"),
        onDelete = ForeignKey.NO_ACTION)])
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var value: Float,
    var date: Timestamp?,
    var description: String,
    var type: TransactionType,
    var categoryId: Int
) {
    @Ignore var category: TransactionCategoryEntity = TransactionCategoryEntity()
}