package app.infrastructure.db

import java.time.LocalDateTime
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

import app.infrastructure.model.Problem

class ProblemTable @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val problem = TableQuery[TableColumn]

  private class TableColumn(tag: Tag) extends Table[Problem](tag, "Problem") {
    def id          = column[Long]         ("id", O.PrimaryKey, O.AutoInc)
    def text        = column[String]       ("text")
    def created_at  = column[LocalDateTime]("created_at")
    def modified_at = column[LocalDateTime]("modified_at")
    def * = (id.?, text, created_at, modified_at) <> (Problem.tupled, Problem.unapply)
  }

}
