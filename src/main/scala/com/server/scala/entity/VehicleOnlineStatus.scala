package com.server.scala.entity

import java.util.Date

/**
  * @since 18-8-22 11:08 by jdk 1.8
  * @author qiwenshuai
  * @note
  */
class VehicleOnlineStatus {

  var vin: String = _

  var onlineStatus: Integer=0

  var chargeStatus: Integer=0

  var warnStatus: Integer=0

  var updateTime: Date=_


  def getVin: String = vin

  def setVin(vin: String): Unit = {
    this.vin = vin
  }

  def getOnlineStatus: Integer = onlineStatus

  def setOnlineStatus(onlineStatus: Integer): Unit = {
    this.onlineStatus = onlineStatus
  }

  def getChargeStatus: Integer = chargeStatus

  def setChargeStatus(chargeStatus: Integer): Unit = {
    this.chargeStatus = chargeStatus
  }

  def getWarnStatus: Integer = warnStatus

  def setWarnStatus(warnStatus: Integer): Unit = {
    this.warnStatus = warnStatus
  }

  def getUpdateTime: Date = updateTime

  def setUpdateTime(updateTime: Date): Unit = {
    this.updateTime = updateTime
  }

}
