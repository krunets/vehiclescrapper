package by.runets.carscrapper.database.service

interface IService<T, ID> {

    suspend fun save(entity: T): T?

    suspend fun saveAll(data: Iterable<T>)

    suspend fun findById(id: ID): T?

    suspend fun findAll(): T?

    suspend fun deleteById(id: ID): Void?
}