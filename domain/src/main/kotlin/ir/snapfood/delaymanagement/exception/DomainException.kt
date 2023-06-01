package ir.snapfood.delaymanagement.exception

sealed class DomainException(message: String, val args: Array<Any>? = null) : RuntimeException(message)