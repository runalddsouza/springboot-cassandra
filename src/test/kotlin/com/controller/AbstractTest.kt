package com.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
@WebAppConfiguration
abstract class AbstractTest {
    protected var mvc: MockMvc? = null

    @Autowired
    var webApplicationContext: WebApplicationContext? = null

    protected open fun setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    protected open fun <T> mapFromJson(json: String?, clazz: Class<T>?): T {
        val objectMapper = getObjectMapper()
        return objectMapper.readValue(json, clazz)
    }

    protected open fun <T> mapFromJsonArray(json: String?, clazz: Class<T>): List<T> {
        val objectMapper = getObjectMapper()
        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(MutableList::class.java, clazz))
    }

    private fun getObjectMapper(): ObjectMapper = ObjectMapper().registerKotlinModule()
}