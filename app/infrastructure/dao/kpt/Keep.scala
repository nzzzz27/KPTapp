package app.infrastructure.dao

import java.time.LocalDateTime
import java.sql.Timestamp
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import app.domain.service.DateTypeService

import app.domain.model.Keep

class KeepTable @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
) extends HasDatabaseConfigProvider[JdbcProfile] with DateTypeService {
  import profile.api._

  type TableElementTuple = (
    Option[Keep.Id],
    String,
    Timestamp,
    Timestamp,
  )

  val query = TableQuery[TableColumn]

  protected class TableColumn(tag: Tag) extends Table[Keep](tag, "Keep") {
    def id          = column[Keep.Id]  ("id", O.PrimaryKey, O.AutoInc)
    def text        = column[String]   ("text")
    def created_at  = column[Timestamp]("created_at")
    def updated_at  = column[Timestamp]("updated_at")

    //  DB <=> Scala の相互のmapping定義
    def * = (id.?, text, created_at, updated_at).<>(
      // Tuple(table) => Model
      (t: TableElementTuple) => Keep(
        t._1, t._2, toLocalDateTime(t._3), toLocalDateTime(t._4)
      ),
      // Model => Tuple(table)
      (v: Keep) => Keep.unapply(v).map { t => (
        t._1, t._2, toTimestamp(LocalDateTime.now()), toTimestamp(LocalDateTime.now())
      )}
    )
  }

}
