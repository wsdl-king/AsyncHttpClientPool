package com.server.scala

import com.alibaba.druid.pool.DruidDataSource
import javax.sql.DataSource
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.{Bean, ComponentScan}
import org.springframework.web.bind.annotation.{GetMapping, RestController}


@SpringBootApplication
@RestController
@ComponentScan(Array {
  "com.server"})
class AnalysisApplication

object AnalysisApplication extends App() {
  SpringApplication.run(classOf[AnalysisApplication], args: _*)

//  @GetMapping(Array {
//    "hello"
//  })
//  def hello(): Unit = {
//    println("123")
//  }
//
//    @Bean(name = Array{"myDataSource"})
//    def myDataSource(): DataSource = {
//      val datasource: DruidDataSource = new DruidDataSource
//      datasource.setUrl("jdbc:mysql://localhost/jmev?useUnicode=true&amp;characterEncoding=utf8&amp;characterSetResults=utf8&amp;zeroDateTimeBehavior=convertToNull")
//      datasource.setUsername("root")
//      datasource.setPassword("123456")
//      datasource.setDriverClassName("com.mysql.jdbc.Driver")
//      datasource.setMaxActive(13)
//      datasource
//    }
//

}





