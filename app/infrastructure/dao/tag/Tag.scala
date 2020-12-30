package app.infrastructure.dao

import java.time.LocalDateTime
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

import app.domain.model.{ Tag => KptTag }
import app.domain.model.TagColor

class KptTagTable @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  type TableElementTuple = (
    Option[KptTag.Id],
    TagColor.Id,
    String,
    LocalDateTime,
    LocalDateTime
  )

  val query = TableQuery[TableColumn]

  protected class TableColumn(tag: Tag) extends Table[KptTag](tag, "KptTag") {
    def id          = column[KptTag.Id]    ("id", O.PrimaryKey, O.AutoInc)
    def tagColor    = column[TagColor.Id]  ("tag_color_id")
    def name        = column[String]       ("name")
    def created_at  = column[LocalDateTime]("created_at")
    def modified_at = column[LocalDateTime]("modified_at")

    // DB <=> Scala の相互のmapping定義
    def * = (id.?, tagColor, name, created_at, modified_at).<>(
      // Tuple(table) => Model
      (t: TableElementTuple) => KptTag(
        t._1, t._2, t._3, t._4, t._5
      ),
      // Model => Tuple(table)
      (v: KptTag) => KptTag.unapply(v).map { t => (
        t._1, t._2, t._3, LocalDateTime.now(), LocalDateTime.now()
      )}
    )
  }

}
