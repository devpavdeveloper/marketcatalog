package by.market.resources.impl

import by.market.core.ProductFilter
import by.market.dto.characteristics.CharacteristicPairDTO
import by.market.dto.system.CategoryDTO
import by.market.dto.system.ContentPage
import by.market.facade.IProductFacade
import by.market.mapper.dto.AbstractProductDTO
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

open class BaseProductResource<TDto: AbstractProductDTO, TProductFacade: IProductFacade<TDto>>(protected val productFacade: TProductFacade)
    : AbstractResource<TDto, TProductFacade>(productFacade) {

    @GetMapping("/category")
    open fun findByCategory(@RequestParam("id") category: String, pageable: Pageable): ResponseEntity<ContentPage<TDto>> {
        return ResponseEntity.ok(productFacade.findByCategory(category, pageable))
    }

    @GetMapping("/categories")
    open fun findByCategories(categories: List<CategoryDTO>): ResponseEntity<MutableList<ContentPage<TDto>>> {
        return ResponseEntity.ok(null)
    }

    @GetMapping("/characteristic")
    open fun findCharacteristic(product: TDto): ResponseEntity<CharacteristicPairDTO> {
        return ResponseEntity.ok(productFacade.findCharacteristicByProduct(product))
    }

    @PostMapping("/filter")
    open fun findByFilter(productFilter: ProductFilter, pageable: Pageable): ResponseEntity<ContentPage<TDto>> {
        return ResponseEntity.ok(productFacade.findByFilter(productFilter, pageable))
    }

}
