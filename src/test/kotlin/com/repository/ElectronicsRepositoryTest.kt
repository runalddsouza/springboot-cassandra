package com.repository

import org.cassandraunit.spring.CassandraDataSet
import org.cassandraunit.spring.CassandraUnit
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import java.math.BigInteger


@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
@ComponentScan
@ContextConfiguration
@TestExecutionListeners(CassandraUnitDependencyInjectionTestExecutionListener::class, DependencyInjectionTestExecutionListener::class)
@CassandraDataSet(value = ["bootstrap_test.cql"], keyspace = "products")
@CassandraUnit
class ElectronicsRepositoryTest {
    @Autowired
    var repo: ElectronicsRepository? = null

    @Test
    fun test001_findAll() {
        val list = repo!!.findAll()
        assertEquals(2, list.size)
    }

    @Test
    fun test002_findById() {
        val data = repo!!.findById("uuid-1")
        assertTrue(data.isPresent)
        assertEquals("uuid-1", data.get().id)
        assertEquals("name-1", data.get().name)
        assertEquals("code-1", data.get().code)
        assertEquals("type-1", data.get().type)
        assertEquals("brand-1", data.get().brand)
        assertEquals(1, data.get().links.size)
        assertEquals("link-type-1", data.get().links.first().type)
        assertEquals("link-1", data.get().links.first().link)
        assertEquals(2, data.get().details.size)
        assertEquals(BigInteger.valueOf(1601222808), data.get().lastUpdatedOn)
    }

    @Test
    fun test003_findByCode() {
        val data = repo!!.findByCode("code-2")
        assertTrue(data.isPresent)
        assertEquals("uuid-2", data.get().id)
        assertEquals("name-2", data.get().name)
        assertEquals("code-2", data.get().code)
        assertEquals("type-2", data.get().type)
        assertEquals("brand-2", data.get().brand)
        assertEquals(BigInteger.valueOf(1601222852), data.get().lastUpdatedOn)
    }

    @Test
    fun test004_findMissingId() {
        val data = repo!!.findById("uuid-x")
        assertFalse(data.isPresent)

    }

    @Test
    fun test005_findMissingCode() {
        val data = repo!!.findByCode("code-x")
        assertFalse(data.isPresent)
    }
}