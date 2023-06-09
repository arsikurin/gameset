package ru.letovo.gameset.logic

class InvokerRequest(invokersNum: Int,
                     invokers: Array[Invoker],
                     val callback: () => Unit,
                     setup: Option[() => Unit]) {

  def this(invokers: Array[Invoker], callback: () => Unit, setup: Option[() => Unit]) = {
    this(invokers.size, invokers, callback, setup)
  }

  def getInvokersNum(): Int = {
    invokersNum
  }

  def getInvokers() : Array[Invoker] = {
    invokers
  }
}
