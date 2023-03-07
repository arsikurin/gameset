import java.util

class Match(private val solutions: IndexedSeq[util.AbstractMap.SimpleEntry[Int, Solution]], private val game: Game) {
  private var matchFinishedObserver: MatchFinishedObserver = _
  private var invokers: Array[Invoker] = _
  private val matchReport: MatchReport = new MatchReport
  private val pipePathRoot: String = "pipes/"

  def run(observer: MatchFinishedObserver): Unit = {
    matchFinishedObserver = observer
    prepareInvokers()
    InvokerPool().getInstance().addToPool(
      new InvokerRequest(
        invokers,
        createReport,
        Some(setupInvokers)
      )
    )
  }

  private def prepareInvokers(): Unit = {
    val root = pipePathRoot + matchReport.matchID
    invokers = Array[Invoker](solutions.length + 1)
    for (i <- solutions.indices) {
      invokers(i) = new Invoker(solutions(i).getValue.path, Seq())
      initInvokerInOutNames(invokers(i), root)
    }

    prepareInteractor(root)
  }

  private def prepareInteractor(root: String): Unit = {
    val argv = Seq[String](solutions.length)
    for (i <- 0 until invokers.length - 1) {
      argv(i) = invokers(i).stdin + ":" + invokers(i).stdout
    }

    val interactor = new Invoker(game.interactorPath, argv)
    initInvokerInOutNames(interactor, root)
    invokers(solutions.length) = interactor
  }

  private def setupInvokers(): Unit = {
    for (invoker <- invokers) {
      createPipe(invoker.stdin.get)
      createPipe(invoker.stdout.get)
      createPipe(invoker.stderr.get)
    }
  }

  private def initInvokerInOutNames(invoker: Invoker, root: String): Unit = {
    invoker.stdin = Option(root + "_in" + "_inter" + ".pipe")
    invoker.stdout = Option(root + "_out" + "_inter" + ".pipe")
    invoker.stderr = Option(root + "_err" + "_inter" + ".pipe")
  }

  private def createPipe(path: String): Unit = {
    val cmds = Array("/bin/sh", "-c", String.format("\"mkfifo ~/%s && tail -f ~/%s | csh -s\"", path, path))
    Runtime.getRuntime.exec(cmds)
  }

  private def createReport(): Unit = {
    matchFinishedObserver.receive(new MatchReport())
  }
}