package ru.letovo.gameset.web.models

import ru.letovo.gameset.logic.{TournamentFinishedObserver, TournamentSystem, TournamentSystemRegistry}
import ru.letovo.gameset.web.models.{GamesTable, User}
import play.api.libs.json.Json
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

case class Tournament(id: Long,
                      name: String,
                      gameID: Long,
                      authorID: Long,
                      tournamentSystemID: Long,
                     )


class TournamentTable(tag: Tag) extends Table[Tournament](tag, "tournaments") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def gameID = column[Long]("gameID")
  def game = foreignKey("GAME", gameID, TableQuery[GamesTable])(_.id)

  def authorID = column[Long]("authorid")
  def author = foreignKey("AUTHOR", authorID, TableQuery[UsersTable])(_.id)
  def tournamentSystemID = column[Long]("tournamentSystemID")

  def * = (id, name, gameID, authorID, tournamentSystemID) <> (Tournament.tupled, Tournament.unapply)
}