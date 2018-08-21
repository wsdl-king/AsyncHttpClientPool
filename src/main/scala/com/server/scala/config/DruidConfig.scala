package com.server.scala.config

import com.alibaba.druid.pool.DruidDataSource
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration, Primary}

/**
  * @since 18-8-14 18:05 by jdk 1.8
  * @author qiwenshuai
  **/
@Configuration
class DruidConfig {
  //对于_这样的赋值来说 只能用val
  @Value("${spring.datasource.url}")
  private val dbUrl:String = null

  @Value("${spring.datasource.username}")
  private val username:String = null

  @Value("${spring.datasource.password}")
  private val password :String = null

  @Value("${spring.datasource.driver-class-name}")
  private val driverClassName :String = null

  @Value("${spring.datasource.initialSize}")
  private val initialSize = 0

  @Value("${spring.datasource.minIdle}")
  private val minIdle = 0

  @Value("${spring.datasource.maxActive}")
  private val maxActive = 0

  @Value("${spring.datasource.maxWait}")
  private val maxWait = 0

  @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
  private val timeBetweenEvictionRunsMillis = 0

  @Value("${spring.datasource.minEvictableIdleTimeMillis}")
  private val minEvictableIdleTimeMillis = 0

  @Value("${spring.datasource.validationQuery}")
  private val validationQuery :String = null

  @Value("${spring.datasource.testWhileIdle}")
  private val testWhileIdle = false

  @Value("${spring.datasource.testOnBorrow}")
  private val testOnBorrow = false

  @Value("${spring.datasource.testOnReturn}")
  private val testOnReturn = false

  @Value("${spring.datasource.poolPreparedStatements}")
  private val poolPreparedStatements = false

  @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
  private val maxPoolPreparedStatementPerConnectionSize = 0


  @Value("${spring.datasource.connectionProperties}")
  private val connectionProperties :String =null

  @Bean // 声明其为Bean实例
  @Primary // 在同样的DataSource中，首先使用被标注的DataSource
  def dataSource: DataSource = {
    val datasource: DruidDataSource = new DruidDataSource
    datasource.setUrl(this.dbUrl)
    datasource.setUsername(username)
    datasource.setPassword(password)
    datasource.setDriverClassName(driverClassName)
    // configuration
    datasource.setInitialSize(initialSize)
    datasource.setMinIdle(minIdle)
    datasource.setMaxActive(maxActive)
    datasource.setMaxWait(maxWait)
    datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis)
    datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis)
    datasource.setValidationQuery(validationQuery)
    datasource.setTestWhileIdle(testWhileIdle)
    datasource.setTestOnBorrow(testOnBorrow)
    datasource.setTestOnReturn(testOnReturn)
    datasource.setPoolPreparedStatements(poolPreparedStatements)
    datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize)
    datasource.setConnectionProperties(connectionProperties)
    datasource
  }
}
