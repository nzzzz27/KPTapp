package app.infrastructure.db

import java.time.LocalDateTime
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

import app.infrastructure.model.ProblemTag
import app.infrastructure.model.{ Tag => KptTag }
import app.infrastructure.model.Problem

class ProblemTagTable @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  type TableElementTuple = (
    Option[ProblemTag.Id],
    KptTag.Id,
    Problem.Id,
    LocalDateTime,
    LocalDateTime
  )

  private val query = TableQuery[TableColumn]

  private class TableColumn(tag: Tag) extends Table[ProblemTag](tag, "ProblemTag") {
    def id          = column[ProblemTag.Id]("id", O.PrimaryKey, O.AutoInc)
    def tagId       = column[KptTag.Id]    ("tag_id")
    def problemId   = column[Problem.Id]   ("problem_id")
    def created_at  = column[LocalDateTime]("created_at")
    def modified_at = column[LocalDateTime]("modified_at")

    // DB <=> Scala の相互のmapping定義
    def * = (id.?, tagId, problemId, created_at, modified_at) <> (
      // Tuple(table) => Model
      (t: TableElementTuple) => ProblemTag(
        t._1, t._2, t._3, t._4, t._5
      ),
      // Model => Tuple(table)
      (v: ProblemTag) => ProblemTag.unapply(v).map { t => (
        t._1, t._2, t._3, LocalDateTime.now(), LocalDateTime.now()
      )}
    )
  }

}
