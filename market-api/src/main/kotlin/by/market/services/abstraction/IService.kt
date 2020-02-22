package by.market.services.abstraction

import by.market.domain.BaseEntity
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import java.util.*

interface IService<TEntity> {
    fun findAll(): MutableList<TEntity>

    fun findAll(sort: Sort): MutableList<TEntity>

    fun findAll(pageable: Pageable): Page<TEntity>

    fun <S : TEntity?> findAll(example: Example<S>): MutableList<S>

    fun <S : TEntity?> findAll(example: Example<S>, sort: Sort): MutableList<S>

    fun <S : TEntity?> findAll(example: Example<S>, pageable: Pageable): Page<S>

    fun findAllById(iterable: Iterable<UUID?>): MutableList<TEntity>

    fun <S : TEntity?> findOne(example: Example<S>): Optional<S>

    fun findById(id: UUID): Optional<TEntity>

    fun getOne(id: UUID): BaseEntity

    fun <S : TEntity?> exists(example: Example<S>): Boolean

    fun existsById(id: UUID): Boolean

    fun count(): Long

    fun <S : TEntity?> count(example: Example<S>): Long
}
