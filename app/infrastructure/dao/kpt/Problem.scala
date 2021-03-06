package infrastructure.dao

import java.time.LocalDateTime
import java.sql.Timestamp
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import domain.service.DateTypeService

import domain.model.Problem

class ProblemTable @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
) extends HasDatabaseConfigProvider[JdbcProfile] with DateTypeService {
  import profile.api._

  type TableElementTuple = (
    Option[Problem.Id],
    String,
    Timestamp,
    Timestamp
  )

  val query = TableQuery[TableColumn]

  protected class TableColumn(tag: Tag) extends Table[Problem](tag, "Problem") {
    def id        = column[Problem.Id]("id", O.PrimaryKey, O.AutoInc)
    def text      = column[String]    ("text")
    def createdAt = column[Timestamp] ("created_at")
    def updatedAt = column[Timestamp] ("updated_at")

    //  DB <=> Scala の相互のmapping定義
    def * = (id.?, text, createdAt, updatedAt).<>(
      // Tuple(table) => Model
      (t: TableElementTuple) => Problem(
        t._1, t._2, toLocalDateTime(t._3), toLocalDateTime(t._4)
      ),
      // Model => Tuple(table)
      (v: Problem) => Problem.unapply(v).map { t => (
        t._1, t._2, toTimestamp(LocalDateTime.now()), toTimestamp(LocalDateTime.now())
      )}
    )
  }

}
