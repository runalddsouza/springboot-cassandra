package com.repository

import com.model.Electronics
import org.springframework.data.cassandra.repository.AllowFiltering
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ElectronicsRepository : CassandraRepository<Electronics, String> {
    @AllowFiltering
    fun findByCode(code: String): Optional<Electronics>

    @AllowFiltering
    override fun findById(id: String): Optional<Electronics>
}