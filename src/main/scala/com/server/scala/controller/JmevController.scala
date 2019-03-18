package com.server.scala.controller

import java.sql.Timestamp
import java.util

import com.server.java.util.date.DateUtil
import com.server.java.util.redis.RedisTool
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.web.bind.annotation.{GetMapping, RestController}

/**
  * @since 18-8-15 10:35 by jdk 1.8
  * @author qiwenshuai
  * @define @ Qualifier 指定名称注解
  *           加{inter} 占位符是为了解决 zset  并集和交集的槽点问题
  *           class HelloController(@Autowired()jdbcTemplate: JdbcTemplate,@Autowired()@Qualifier(value = "myDataSource") myDataSource: DataSource) {
  */


@RestController
class JmevController(@Autowired redisTool: RedisTool) {

  //此标识用于判断车辆所属于的车辆--北里平台;
  private val B_FLAG = "jmev_b_flag{inter}"
  //此标识用于判断车辆所属于的车辆--e智;
  private val E_FLAG = "jmev_e_flag{inter}"
  //交集key;
  private val INTER_KEY = "jmev_inter_flag{inter}"
  //差集key
  private val DIFF_KEY = "jmev_diff_flag{inter}"

  @GetMapping(Array {
    "/findB"
  })
  def hello(): Any = {
    //求并
    redisTool.zInterSet(B_FLAG, E_FLAG, INTER_KEY)
    val map = new util.HashMap[String, String]()
    val result = redisTool.getAllZSet(INTER_KEY): util.Set[ZSetOperations.TypedTuple[String]]
    val oneResult = result.iterator(): util.Iterator[ZSetOperations.TypedTuple[String]]
    while (oneResult.hasNext) {
      val value = oneResult.next()
      val time = DateUtil.dateToString(new Timestamp(value.getScore.longValue()), "")
      map.put(value.getValue, time)
    }
    map
  }


  @GetMapping(Array {
    "/findE"
  })
  def findE(): Any = {
    //并
    redisTool.zUnitonSet(B_FLAG, E_FLAG, DIFF_KEY)
    //移除为0的---差
    redisTool.rmUnionSet(DIFF_KEY)
    val map = new util.HashMap[String, String]()
    val result = redisTool.getAllZSet(DIFF_KEY): util.Set[ZSetOperations.TypedTuple[String]]
    val oneResult = result.iterator(): util.Iterator[ZSetOperations.TypedTuple[String]]
    while (oneResult.hasNext) {
      val value = oneResult.next()
      val time = DateUtil.getToday("")
      map.put(value.getValue, time)
    }
    map
  }

}
