package com.model

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import org.springframework.data.cassandra.core.mapping.UserDefinedType

@Table("Electronics")
data class Electronics(override val id: String,
                       override val name: String,
                       override val code: String,
                       override val type: String,
                       override val brand: String,
                       override val links: List<Url>,
                       override val details: Map<String, String>) : Product(id, name, code, type, brand, links, details)

open class Product(@PrimaryKey open val id: String,
                   open val name: String,
                   open val code: String,
                   open val type: String,
                   open val brand: String,
                   open val links: List<Url>,
                   open val details: Map<String, String>)

@UserDefinedType
data class Url(val type: String, val link: String)
