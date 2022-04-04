package uz.med.shared

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.med.shared.util.Resource

interface BaseUseCase<T, R> {
    fun execute(): Flow<Resource<R>> = flow {}
    fun execute(data: T): Flow<Resource<R>> = flow {}
    fun execute(data: T, page: Int): Flow<Resource<R>> = flow {}
}
