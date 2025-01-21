package com.store.validators

import com.store.models.ProductDetails
import com.store.models.ProductType

object ProductValidator {
    fun validate(productDetails: ProductDetails) {
        validateInventory(productDetails.inventory)
        validateName(productDetails.name)
        validateType(productDetails.type)
    }

    private fun validateType(type: Any) {
        if(type !is String) throw IllegalArgumentException("Type should be string")
        ProductType.valueOf(type)
    }

    private fun validateName(name: Any) {
        if(name !is String) throw IllegalArgumentException("Name should be string")
    }

    private fun validateInventory(inventory: Any) {
        if(inventory !is Int) throw IllegalArgumentException("Inventory should be integer")
        if(inventory < 1) throw IllegalArgumentException("Inventory should be greater than 0")
        if(inventory > 9999) throw IllegalArgumentException("Inventory should be less than 10000")
    }
}
