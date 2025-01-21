package com.store.controllers

import com.store.models.*
import com.store.repository.entities.Product
import com.store.repository.implementations.ProductRepository
import com.store.validators.ProductValidator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class Products(
    private val productRepository: ProductRepository
) {
    @GetMapping
    fun getProducts(@RequestParam type: String?): ResponseEntity<List<Product>> {
        val filteredProducts = if (type != null) {
            productRepository.findByType(ProductType.valueOf(type))
        } else {
            productRepository.findAll()
        }
        return ResponseEntity.ok(filteredProducts)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody productDetails: ProductDetails): ResponseEntity<ProductId> {
        ProductValidator.validate(productDetails)
        val newProduct = Product(
            id = productRepository.count() + 1,
            name = productDetails.name as String,
            type = ProductType.valueOf(productDetails.type as String),
            inventory = productDetails.inventory as Int
        )
        productRepository.save(newProduct)
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductId(id = newProduct.id))
    }

    @ExceptionHandler(Exception::class)
    fun handleError(ex: Exception): ResponseEntity<ErrorResponseBody> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponseBody(
                status = HttpStatus.BAD_REQUEST.value(),
                error = ex.message ?: "Bad Request",
                path = "/products"
            )
        )
    }
}
