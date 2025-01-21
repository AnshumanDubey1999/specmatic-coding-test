package com.store.validators

import com.store.models.ProductDetails

object ProductValidator {
    fun validate(productDetails: ProductDetails) {
        validateInventory(productDetails.inventory)
    }

    private fun validateInventory(inventory: Int) {
        if(inventory < 1) throw IllegalArgumentException("Inventory should be greater than 0")
        if(inventory > 9999) throw IllegalArgumentException("Inventory should be less than 10000")
    }
}
