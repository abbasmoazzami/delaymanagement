package ir.snapfood.delaymanagement.application.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class HttpBadRequestException(message: String, data: Array<Any> = emptyArray()) : AppHttpException(message, data)