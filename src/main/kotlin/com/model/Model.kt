package com.model

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("Electronics")
data class Electronics(override val id: String,
                       override val name: String,
                       override val code: String,
                       override val type: String) : Product(id, name, code, type)

open class Product(@PrimaryKey open val id: String, open val name: String, open val code: String, open val type: String)

