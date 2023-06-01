package ir.snapfood.delaymanagement.application.exception

abstract class AppHttpException(message: String, val args: Array<Any>) : RuntimeException(message)
