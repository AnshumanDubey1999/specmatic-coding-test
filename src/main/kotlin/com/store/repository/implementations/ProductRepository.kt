package com.store.repository.implementations

import com.store.models.ProductType
import com.store.repository.entities.Product
import org.springframework.stereotype.Repository

@Repository
class ProductRepository {
    private val products = mutableListOf<Product>()

    fun save(product: Product): Product {
        products.add(product)
        return product
    }

    fun findAll(): List<Product> {
        return products
    }

    fun findByType(type: ProductType): List<Product> {
        return products.filter { p -> p.type == type }
    }

    fun count(): Int {
        return products.size
    }
}
