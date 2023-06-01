package ir.snapfood.delaymanagement.service.gateway

import ir.snapfood.delaymanagement.common.abstracts.DTO
import ir.snapfood.delaymanagement.common.abstracts.UseCase
import kotlin.reflect.KClass

class UseCaseGateway {

    val useCases: MutableMap<KClass<*>, UseCase<DTO, *>> = mutableMapOf()

    inline fun <reified INPUT : DTO> register(useCase: UseCase<INPUT, *>) {
        useCases.compute(INPUT::class) { newValue, oldValue ->
            oldValue?.let { throw RuntimeException("useCaseAlreadyRegistered") }

            useCase as? UseCase<DTO, *>
        }
    }

    fun <OUTPUT> execute(input: DTO): OUTPUT {
        return useCases.getValue(input::class).execute(input) as OUTPUT
    }

    fun <OUTPUT> executeTransactional(input: DTO): OUTPUT {
        return useCases.getValue(input::class).executeTransactional(input) as OUTPUT
    }

}