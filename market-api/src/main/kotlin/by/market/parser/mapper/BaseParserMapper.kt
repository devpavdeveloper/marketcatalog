package by.market.parser.mapper

import by.market.core.IMapper
import by.market.domain.AbstractProduct
import by.market.domain.system.Category
import by.market.repository.BaseRepository
import product.AsforosProduct

abstract class BaseParserMapper<TProduct: AbstractProduct>(protected val rep: BaseRepository<TProduct>) : IMapper<AsforosProduct, TProduct> {
    private val categoryMap: HashMap<String, Category?> = createCategoryMap()
    private val characteristicMapperHandler: CharacteristicMapperHandler = CharacteristicMapperHandler()

    override suspend fun map(value: AsforosProduct): TProduct {
        val product = getDatabaseProductOrMakeEmptyProduct(value.title)
        product.category = categoryMap.getOrDefault(value.category, null)
        product.img = value.imgUrl
        product.title = value.title

        insertOrUpdateInDatabase(product)
        characteristicMapperHandler.handle(product, value)

        return product
    }

    abstract fun getDatabaseProductOrMakeEmptyProduct(title: String): TProduct

    private fun insertOrUpdateInDatabase(product: TProduct){
        product.id = rep.saveAndFlush(product).id
    }

    private fun createCategoryMap(): HashMap<String, Category?> {
        fun findCategory(parserCategory: String): Category? {
            TODO("Ищем по названию категории, категорию из БД")
        }

        val map = HashMap<String, Category?>()
        // Карнизы
        map["metallic"] = findCategory("metallic")
        map["plastic_ceilings"] = findCategory("plastic_ceilings")
        map["wall_metal_plastic"] = findCategory("wall_metal_plastic")
        map["accessories_for_ceiling"] = findCategory("accessories_for_ceiling")
        map["accessories_for_metal"] = findCategory("accessories_for_metal")
        map["flexible"] = findCategory("flexible")
        map["metal_plastic_accessories"] = findCategory("metal_plastic_accessories")

        // Жалюзи
        map["day_night"] = findCategory("day_night")
        map["standard"] = findCategory("standard")
        map["in_box"] = findCategory("in_box")
        map["premium"] = findCategory("premium")
        map["blackout"] = findCategory("blackout")

        // Рольшторы
        map["rolstor"] = findCategory("rolstor")

        // Аксессуары
        map["luversa"] = findCategory("luversa")
        map["grips_holders_hooks"] = findCategory("grips_holders_hooks")
        map["magnetic_clips"] = findCategory("magnetic_clips")

        return map
    }
}
