package com.controller

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
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
import java.io.IOException

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

    @Throws(JsonProcessingException::class)
    protected open fun mapToJson(obj: Any?): String? {
        val objectMapper = ObjectMapper()
        return objectMapper.writeValueAsString(obj)
    }

    @Throws(JsonParseException::class, JsonMappingException::class, IOException::class)
    protected open fun <T> mapFromJson(json: String?, clazz: Class<T>?): T {
        val objectMapper = ObjectMapper().registerKotlinModule()
        return objectMapper.readValue(json, clazz)
    }

    @Throws(JsonParseException::class, JsonMappingException::class, IOException::class)
    protected open fun <T> mapFromJsonArray(json: String?, clazz: Class<T>): List<T> {
        val objectMapper = ObjectMapper().registerKotlinModule()
        return objectMapper.readValue(json, objectMapper.getTypeFactory()
                .constructCollectionType(MutableList::class.java, clazz))
    }
}