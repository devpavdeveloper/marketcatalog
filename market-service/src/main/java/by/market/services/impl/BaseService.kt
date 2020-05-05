package by.market.services.impl

import by.market.domain.BaseEntity
import by.market.repository.BaseRepository
import by.market.services.IService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

open class BaseService<TEntity: BaseEntity, TRepository: BaseRepository<TEntity>>(protected val rep: TRepository) : IService<TEntity> {

    override fun findAll(): MutableList<TEntity> = rep.findAll()

    override fun findAll(pageable: Pageable): Page<TEntity> = rep.findAll(pageable)

    override fun findById(id: UUID): Optional<TEntity> = rep.findById(id)

    override fun getReference(id: UUID): TEntity? {
        return try {
            rep.getOne(id)
        } catch (ex: Exception) {
            null
        }
    }

    override fun save(entity: TEntity): TEntity {
        return rep.save(entity)
    }

    override fun saveAll(iterable: Iterable<TEntity>): MutableList<TEntity> {
        val existsInvalidEntity: Boolean = iterable.any { it.id != null }

        if (existsInvalidEntity) {
            throw RuntimeException("List exists entities are already in by.market.exception.database")
        }

        return rep.saveAll(iterable)
    }

    override fun deleteAll(): Unit = rep.deleteAll()

    override fun deleteAll(iterable: Iterable<TEntity?>): Unit = rep.deleteAll(iterable)

    override fun deleteById(id: UUID): Unit = rep.deleteById(id)

    override fun existsById(id: UUID): Boolean = rep.existsById(id)

    override fun count(): Long = rep.count()



}
