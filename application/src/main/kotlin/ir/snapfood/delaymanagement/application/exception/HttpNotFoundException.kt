package ir.snapfood.delaymanagement.application.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class HttpNotFoundException(message: String, data: Array<Any> = emptyArray()) : AppHttpException(message, data)