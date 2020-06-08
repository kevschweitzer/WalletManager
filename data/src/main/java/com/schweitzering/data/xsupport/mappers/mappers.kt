package com.schweitzering.data.xsupport.mappers

import com.schweitzering.data.accounts.AccountEntity
import com.schweitzering.data.accounts.AccountWithTransactionsRelation
import com.schweitzering.data.categories.TransactionCategoryEntity
import com.schweitzering.data.debts.DebtEntity
import com.schweitzering.data.fixedExpenses.FixedExpenseEntity
import com.schweitzering.data.fixedExpenses.generator.FixedExpenseGeneratorEntity
import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.data.transaction.TransactionWithCategoryRelation
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.accounts.AccountWithTransactions
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.debts.Debt
import com.schweitzering.domain.fixedExpenses.FixedExpense
import com.schweitzering.domain.fixedExpenses.generator.FixedExpenseGenerator
import com.schweitzering.domain.transaction.Transaction

fun Transaction.toTransactionEntity() =
    TransactionEntity(
                id = id,
                value = value,
                date = date, //Won't be null when transaction is created
                description = description,
                type = type,
                categoryId = category.id,
                accountId = accountId
    )

fun TransactionEntity.toTransaction() =
    Transaction(id = id,
                value = value,
                date = date,
                description = description,
                type = type,
                category = category.toTransactionCategory(),
                accountId = accountId)


fun TransactionCategory.toTransactionCategoryEntity() =
    TransactionCategoryEntity(id = id, type = type, name = name)
fun TransactionCategoryEntity.toTransactionCategory() =
    TransactionCategory(id = id, type = type, name = name)


fun FixedExpenseEntity.toFixedExpense() =
    FixedExpense(id, expense.toTransaction(), isAlreadyPaid, creationDate, paymentDate)

fun FixedExpense.toFixedExpenseEntity() =
    FixedExpenseEntity(id, expense.toTransactionEntity(), isAlreadyPaid, creationDate, paymentDate)


fun FixedExpenseGeneratorEntity.toFixedExpenseGenerator() =
    FixedExpenseGenerator(id, expense.toTransaction(), creationDate, schedule)
fun FixedExpenseGenerator.toFixedExpenseGeneratorEntity() =
    FixedExpenseGeneratorEntity(id ?: 0, expense.toTransactionEntity(), creationDate, schedule)

fun Debt.toDebtEntity() =
    DebtEntity(id, transaction.toTransactionEntity(), creationDate, isResolved, resolveDate)
fun DebtEntity.toDebt() =
    Debt(id, transaction.toTransaction(), creationDate, isResolved, resolveDate)


fun TransactionWithCategoryRelation.toTransaction() = Transaction(
    id = transaction.id,
    value = transaction.value,
    date = transaction.date,
    description = transaction.description,
    type = transaction.type,
    category = category.toTransactionCategory()
)


fun Account.toAccountEntity() =
    AccountEntity(id, name, description)
fun AccountEntity.toAccount() =
    Account(id, name, description)

fun AccountWithTransactionsRelation.toAccountWithTransactions() = AccountWithTransactions(
    account = account.toAccount(),
    transactions = transactions.map { it.toTransaction() })
fun AccountWithTransactions.toAccountWithTransactionRelation() = AccountWithTransactionsRelation(
    account = account.toAccountEntity(),
    transactions = transactions.map { it.toTransactionEntity() })