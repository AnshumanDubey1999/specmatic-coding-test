package com.store.models

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper

data class ProductDetails internal constructor(
    val name: String,
    val type: String,
    val inventory: Int,
    val cost: Double
) {
    companion object {
        fun fromJson(json: String): ProductDetails {
            val objectMapper = ObjectMapper()
            val rootNode: JsonNode = objectMapper.readTree(json)

            val name = rootNode["name"]?.takeIf { it.isTextual }?.asText()
                ?: throw IllegalArgumentException("Invalid value for field: name")

            val type = rootNode["type"]?.takeIf { it.isTextual }?.asText()
                ?: throw IllegalArgumentException("Invalid value for field: type")

            val inventory = rootNode["inventory"]?.takeIf { it.isInt }?.asInt()
                ?: throw IllegalArgumentException("Invalid value for field: inventory")

            val costNode = rootNode["cost"]
            val cost = if(costNode == null) 0.0
            else if (costNode.isDouble) costNode.asDouble()
            else throw IllegalArgumentException("Invalid value for field: cost")

            return ProductDetails(name = name, type = type, inventory = inventory, cost = cost)
        }
    }
}
