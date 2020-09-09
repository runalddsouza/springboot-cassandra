package com.repository

import com.model.Electronics
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface ElectronicsRepository : CassandraRepository<Electronics, String> {
}