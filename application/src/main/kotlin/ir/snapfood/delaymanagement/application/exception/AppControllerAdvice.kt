package ir.snapfood.delaymanagement.application.exception

import ir.snapfood.delaymanagement.application.common.getMessageFa
import ir.snapfood.delaymanagement.exception.DomainException
import ir.snapfood.delaymanagement.exception.EntityNotFoundException
import ir.snapfood.delaymanagement.exception.InvalidDataException
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class AppControllerAdvice(
    private val messageSource: MessageSource,
) {

    @ExceptionHandler(value = [AppHttpException::class])
    fun handleAppExceptions(exception: AppHttpException) = ResponseEntity(
        BasicAppHttpExceptionResponse(
            code = exception.message,
            message = messageSource.getMessageFa(exception.message, exception.args)
        ),
        exception::class.annotations
            .filterIsInstance(ResponseStatus::class.java)
            .firstOrNull()?.code ?: HttpStatus.INTERNAL_SERVER_ERROR
    )

    @ExceptionHandler(value = [DomainException::class])
    fun handleDomainExceptions(exception: DomainException) = ResponseEntity(
        BasicAppHttpExceptionResponse(
            code = exception.message,
            message = messageSource.getMessageFa(exception.message, exception.args)
        ),
        when (exception) {
            is InvalidDataException -> HttpStatus.BAD_REQUEST
            is EntityNotFoundException -> HttpStatus.NOT_FOUND
        }
    )

}
