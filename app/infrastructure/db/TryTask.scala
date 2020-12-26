package app.infrastructure.db

import java.time.LocalDateTime
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

import app.infrastructure.model.TryTask
import app.infrastructure.model.TryTask._
import app.infrastructure.model.Try

class TryTaskTable @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  type TableElementTuple = (
    Option[Id],
    Try.Id,
    String,
    Short,
    LocalDateTime,
    LocalDateTime
  )

  private val query = TableQuery[TableColumn]

  private class TableColumn(tag: Tag) extends Table[TryTask](tag, "TryTask") {
    def id          = column[Id]           ("id", O.PrimaryKey, O.AutoInc)
    def tryId       = column[Try.Id]       ("try_id")
    def text        = column[String]       ("text")
    def status      = column[Short]        ("status", O.Default(0))
    def created_at  = column[LocalDateTime]("created_at")
    def modified_at = column[LocalDateTime]("modified_at")

    /*  DB <=> Scala の相互のmapping定義
     *  コンパニオンオブジェクトにはtupledメソッドは使えないので、この書き方。
     *  case classへのマッピングなら、簡易verでもOK.
     *  def * = (id.?, text, created_at, modified_at) <> (Try.tupled, Try.unapply)
     */
    def * = (id.?, tryId, text, status, created_at, modified_at) <> (
      // Tuple(table) => Model
      (t: TableElementTuple) => TryTask(
        t._1, t._2, t._3, t._4, t._5, t._6
      ),
      // Model => Tuple(table)
      (v: TryTask) => TryTask.unapply(v).map { t => (
        t._1, t._2, t._3, t._4, LocalDateTime.now(), LocalDateTime.now()
      )}
    )
  }

}
