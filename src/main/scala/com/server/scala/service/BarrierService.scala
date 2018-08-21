package com.server.scala.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

/**
  * @since 18-8-16 10:35 by jdk 1.8
  * @author qiwenshuai
  * @define description
 */
@Service
class BarrierService(@Autowired jdbcTemplate: JdbcTemplate) {

// 查找电子围栏配置
 def updateConfig() : Any ={
  print("")
 }



}
