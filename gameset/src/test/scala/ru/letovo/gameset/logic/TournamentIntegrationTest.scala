/*
package ru.letovo.gameset.logic

import models.Game
import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuite
import ru.letovo.gameset.web.models.Solution

class TournamentIntegrationTest extends AnyFunSuite with BeforeAndAfter with TournamentFinishedObserver {
  private var tournament: Tournament = _
  test("Tournament execution") {
    val game = Game()
    game.interactorPath = Config.example_game_root + "/interactor"

    val tournament = new Tournament(
      "Test tour",
      game,
      new TournamentSystemRoundRobin(),
      2,
      this
    )
    val solution1 = Solution(Some(10), game.)
    solution1.path = Config.exe_test_files_root + "/mtdf"
    val solution2 = new Solution()
    solution2.path = Config.exe_test_files_root + "/negamax"

    tournament.open()
    tournament.addSolution(solution1)
    tournament.addSolution(solution2)
    tournament.close()
  }

  override def receiveTournamentFinished(): Unit = {
    assert(tournament.status == TournamentStatus.Finished)
  }
}
*/
