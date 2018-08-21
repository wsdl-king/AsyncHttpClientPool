package com.test.scala


import com.server.scala.AnalysisApplication
import javax.sql.DataSource
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.junit4.SpringRunner


/**
  * @since 18-8-15 11:19 by jdk 1.8
  * @author qiwenshuai
  */
@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[AnalysisApplication]), webEnvironment = SpringBootTest.WebEnvironment.NONE)
class Testdd {

  private val logger: Logger = LoggerFactory.getLogger(classOf[Testdd])
  @Autowired
  private val dataSource: DataSource = null

  @Autowired
  private val  jdbcTemplate:JdbcTemplate =null
  @Test
  def testDataSource(): Unit = {
    //    com.alibaba.druid.pool.DruidDataSource
    logger.trace(">>>>>>>>>>>>>>>>>>>>> {}", this.dataSource.getClass)
    logger.debug(">>>>>>>>>>>>>>>>>>>>> {}", this.dataSource.getClass)
    logger.info(">>>>>>>>>>>>>>>>>>>>> {}", this.dataSource.getClass)
    logger.warn(">>>>>>>>>>>>>>>>>>>>> {}", this.dataSource.getClass)
    logger.warn(">>>>>>>>>>>>>>>>>>>>> {}", this.jdbcTemplate.getClass)
    println("1233131")
    //    Assert.assertTrue("类别不相同!!!", this.dataSource.isInstanceOf[DruidDataSource])
  }

}