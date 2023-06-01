package ir.snapfood.delaymanagement.application.service

import ir.snapfood.delaymanagement.service.transaction.TransactionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

@Service
class SpringTransactionService : TransactionService {

    @Transactional
    override fun <R> doInTransaction(block: () -> R) = block()

    override fun isStarted(): Boolean {
        return TransactionSynchronizationManager.isActualTransactionActive()
    }

}