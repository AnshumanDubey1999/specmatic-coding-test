package com.store.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductDetails @JsonCreator constructor(
    @JsonProperty("name") val name: Any,
    @JsonProperty("type") val type: Any,
    @JsonProperty("inventory") val inventory: Any
)
