package com.mathbot.pay.bitcoin

import play.api.libs.json.Json
import org.scalatest.funsuite.AnyFunSuite

class JsonRpcRequestBodyTest extends AnyFunSuite {

  test("format getbalance") {
    val json1 = s"""{"method":"getbalance","params":[],"jsonrpc":"1.0","id":"scala-jsonrpc"}"""
    val json2 = Json.toJson(JsonRpcRequestBody("getbalance", Json.arr(), "1.0", "scala-jsonrpc")).toString
    assert(json1 == json2)
  }

  test("format sendtoaddress 2") {
    val addr = "adress"
    val amount = "0.000001"
    val json1 = s"""{"method":"sendtoaddress","params":["$addr","$amount"],"jsonrpc":"1.0","id":"scala-jsonrpc"}"""
    val json2 = Json.toJson(JsonRpcRequestBody("sendtoaddress", Json.arr(addr, amount))).toString
    assert(json1 === json2)
  }
}
