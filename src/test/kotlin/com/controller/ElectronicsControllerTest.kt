package com.controller

import com.model.Product
import org.cassandraunit.spring.CassandraDataSet
import org.cassandraunit.spring.CassandraUnit
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.math.BigInteger


@ComponentScan
@ContextConfiguration
@TestExecutionListeners(CassandraUnitDependencyInjectionTestExecutionListener::class, DependencyInjectionTestExecutionListener::class)
@CassandraDataSet(value = ["bootstrap_test.cql"], keyspace = "products")
@CassandraUnit
class ElectronicsControllerTest : AbstractTest() {

    @Before
    fun before() {
        super.setUp()
    }

    @Test
    fun test001_list() {
        val uri = "/products/electronics/list"
        val mvcResult = mvc!!.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn()
        assertEquals(200, mvcResult.response.status)
        val list = super.mapFromJsonArray<Product>(mvcResult.response.contentAsString, Product::class.java)
        assertEquals(2, list.size)
    }

    @Test
    fun test002_findById() {
        val uri = "/products/electronics/uuid-1"
        val mvcResult = mvc!!.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn()
        assertEquals(200, mvcResult.response.status)
        val data = super.mapFromJson<Product>(mvcResult.response.contentAsString, Product::class.java)
        assertEquals("uuid-1", data.id)
        assertEquals("name-1", data.name)
        assertEquals("code-1", data.code)
        assertEquals("type-1", data.type)
        assertEquals(BigInteger.valueOf(1601222808), data.lastUpdatedOn)
    }

    @Test
    fun test003_findByMissingId() {
        val uri = "/products/electronics/uuid-x"
        val mvcResult = mvc!!.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn()
        assertEquals(404, mvcResult.response.status)
    }

    @Test
    fun test004_findByCode() {
        val uri = "/products/electronics/code/code-2"
        val mvcResult = mvc!!.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn()
        assertEquals(200, mvcResult.response.status)
        val data = super.mapFromJson<Product>(mvcResult.response.contentAsString, Product::class.java)
        assertEquals("uuid-2", data.id)
        assertEquals("name-2", data.name)
        assertEquals("code-2", data.code)
        assertEquals("type-2", data.type)
        assertEquals(BigInteger.valueOf(1601222852), data.lastUpdatedOn)
    }

    @Test
    fun test005_findByMissingCode() {
        val uri = "/products/electronics/code/code-x"
        val mvcResult = mvc!!.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn()
        assertEquals(404, mvcResult.response.status)
    }
}