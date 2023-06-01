package ir.snapfood.delaymanagement.service.transaction

interface TransactionService {
    fun <R> doInTransaction(block: () -> R): R
    fun isStarted(): Boolean
}