package infrastructure.dao

import java.time.LocalDateTime
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

import domain.model.KeepTag
import domain.model.{ Tag => KptTag }
import domain.model.Keep

class KeepTagTable @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  type TableElementTuple = (
    Option[KeepTag.Id],
    KptTag.Id,
    Keep.Id,
    LocalDateTime,
    LocalDateTime
  )

  val query = TableQuery[TableColumn]

  protected class TableColumn(tag: Tag) extends Table[KeepTag](tag, "KeepTag") {
    def id        = column[KeepTag.Id]   ("id", O.PrimaryKey, O.AutoInc)
    def tagId     = column[KptTag.Id]    ("tag_id")
    def keepId    = column[Keep.Id]      ("keep_id")
    def createdAt = column[LocalDateTime]("created_at")
    def updatedAt = column[LocalDateTime]("updated_at")

    // DB <=> Scala の相互のmapping定義
    def * = (id.?, tagId, keepId, createdAt, updatedAt).<>(
      // Tuple(table) => Model
      (t: TableElementTuple) => KeepTag(
        t._1, t._2, t._3, t._4, t._5
      ),
      // Model => Tuple(table)
      (v: KeepTag) => KeepTag.unapply(v).map { t => (
        t._1, t._2, t._3, LocalDateTime.now(), LocalDateTime.now()
      )}
    )
  }

}
