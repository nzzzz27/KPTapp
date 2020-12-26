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

  type TableElementTuple = (
    Option[Problem.Id],
    String,
    LocalDateTime,
    LocalDateTime
  )

  private val query = TableQuery[TableColumn]

  private class TableColumn(tag: Tag) extends Table[Problem](tag, "Problem") {
    def id          = column[Problem.Id]   ("id", O.PrimaryKey, O.AutoInc)
    def text        = column[String]       ("text")
    def created_at  = column[LocalDateTime]("created_at")
    def modified_at = column[LocalDateTime]("modified_at")

    /*  DB <=> Scala の相互のmapping定義
     *  コンパニオンオブジェクトにはtupledメソッドは使えないので、この書き方。
     *  case classへのマッピングなら、簡易verでもOK.
     *  def * = (id.?, text, created_at, modified_at) <> (Try.tupled, Try.unapply)
     */
    def * = (id.?, text, created_at, modified_at) <> (
      // Tuple(table) => Model
      (t: TableElementTuple) => Problem(
        t._1, t._2, t._3, t._4
      ),
      // Model => Tuple(table)
      (v: Problem) => Problem.unapply(v).map { t => (
        t._1, t._2, LocalDateTime.now(), LocalDateTime.now()
      )}
    )
  }

}
