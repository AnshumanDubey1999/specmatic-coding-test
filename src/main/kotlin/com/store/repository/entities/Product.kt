package com.store.repository.entities

import com.store.models.ProductType

data class Product(
    val id: Int,
    val name: String,
    val type: ProductType,
    val inventory: Int
)
