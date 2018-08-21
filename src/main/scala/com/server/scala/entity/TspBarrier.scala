package com.server.scala.entity


import com.fasterxml.jackson.databind.annotation.JsonSerialize

/**
  * @since 18-8-16 18:04 by jdk 1.8
  * @author qiwenshuai
  * @note 一个测试实体
  */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
class TspBarrier {

  /**
    * id 初始化为null
    */
  private var id: String = _

  /**
    * 经度
    */
  private var lon = .0

  /**
    * 纬度
    */
  private var lat = .0


}

object TspBarrier {
  def main(args: Array[String]): Unit = {
    var tspBarrier = new TspBarrier
    print(tspBarrier.id)
    tspBarrier.id = "123"
    val a= 9
    print(tspBarrier.id)
    for (_ <- a to 10) {
      println(tspBarrier.id)
    }
  }
}