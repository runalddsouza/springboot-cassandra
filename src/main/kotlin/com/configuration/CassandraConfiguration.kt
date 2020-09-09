package com.configuration

import com.datastax.oss.driver.api.core.CqlSession
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.CqlSessionFactoryBean
import org.springframework.data.cassandra.core.CassandraTemplate

@Configuration
@EnableConfigurationProperties
open class CassandraConfiguration {

    @Value("\${spring.data.cassandra.contact-points}")
    private val hostList: String? = null

    @Value("\${spring.data.cassandra.datacenter}")
    private val datacenter: String? = null

    @Value("\${spring.data.cassandra.keyspace-name}")
    private val keyspaceName: String? = null

    @Value("\${spring.data.cassandra.username}")
    private val userName: String? = null

    @Value("\${spring.data.cassandra.password}")
    private val password: String? = null

    @Value("\${spring.data.cassandra.port}")
    private val port: Int? = null

    @Bean
    open fun getCqlSession(): CqlSessionFactoryBean {
        val factory = CqlSessionFactoryBean()
        factory.setUsername(userName)
        factory.setPassword(password)
        factory.setPort(port!!)
        factory.setKeyspaceName(keyspaceName)
        factory.setContactPoints(hostList)
        factory.setLocalDatacenter(datacenter)
        return factory
    }

    @Bean
    @ConditionalOnMissingBean
    @Throws(Exception::class)
    open fun cassandraTemplate(session: CqlSession): CassandraTemplate = CassandraTemplate(session)
}