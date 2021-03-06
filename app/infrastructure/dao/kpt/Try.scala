package infrastructure.dao

import java.time.LocalDateTime
import java.sql.Timestamp
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import domain.service.DateTypeService

import domain.model.Problem
import domain.model.Try
import domain.model.Try._

class TryTable @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
) extends HasDatabaseConfigProvider[JdbcProfile] with DateTypeService {
  import profile.api._

  type TableElementTuple = (
    Option[Id],
    Problem.Id,
    String,
    Short,
    Timestamp,
    Timestamp
  )

  val query = TableQuery[TableColumn]

  protected class TableColumn(tag: Tag) extends Table[Try](tag, "Try") {
    def id        = column[Id]        ("id", O.PrimaryKey, O.AutoInc)
    def problemId = column[Problem.Id]("problem_id")
    def text      = column[String]    ("text")
    def status    = column[Short]     ("status", O.Default(0))
    def createdAt = column[Timestamp] ("created_at")
    def updatedAt = column[Timestamp] ("updated_at")

    //  DB <=> Scala の相互のmapping定義
    def * = (id.?, problemId, text, status, createdAt, updatedAt).<>(
      // Tuple(table) => Model
      (t: TableElementTuple) => Try(
        t._1, t._2, t._3, t._4, toLocalDateTime(t._5), toLocalDateTime(t._6)
      ),
      // Model => Tuple(table)
      (v: Try) => Try.unapply(v).map { t => (
        t._1, t._2, t._3, t._4, toTimestamp(LocalDateTime.now()), toTimestamp(LocalDateTime.now())
      )}
    )
  }

}