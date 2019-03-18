package com.server.scala.service

import java.util

import com.server.scala.entity.VehicleOnlineStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.{BeanPropertyRowMapper, JdbcTemplate}
import org.springframework.stereotype.Service

/**
  * @since 18-8-22 09:58 by jdk 1.8
  * @author qiwenshuai
  * @note
  */
@Service
class OnlineService(@Autowired jdbcTemplate: JdbcTemplate) {


  def selectOnline(vin: String): util.List[VehicleOnlineStatus] = {
    val sql = " select   *  from jmev.vehicle_online_status   t where t.vin =?"
     jdbcTemplate.query(sql, Array[AnyRef]{vin}, new BeanPropertyRowMapper[VehicleOnlineStatus](classOf[VehicleOnlineStatus]))
  }

}
