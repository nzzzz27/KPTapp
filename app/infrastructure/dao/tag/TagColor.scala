package infrastructure.dao

import java.time.LocalDateTime
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

import domain.model.TagColor

class TagColorTable @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  type TableElementTuple = (
    Option[TagColor.Id],
    String,
    LocalDateTime,
    LocalDateTime
  )

  val query = TableQuery[TableColumn]

  protected class TableColumn(tag: Tag) extends Table[TagColor](tag, "TagColor") {
    def id        = column[TagColor.Id]  ("id", O.PrimaryKey, O.AutoInc)
    def color     = column[String]       ("color")
    def createdAt = column[LocalDateTime]("created_at")
    def updatedAt = column[LocalDateTime]("updated_at")

    // DB <=> Scala の相互のmapping定義
    def * = (id.?, color, createdAt, updatedAt).<>(
      // Tuple(table) => Model
      (t: TableElementTuple) => TagColor(
        t._1, t._2, t._3, t._4
      ),
      // Model => Tuple(table)
      (v: TagColor) => TagColor.unapply(v).map { t => (
        t._1, t._2, LocalDateTime.now(), LocalDateTime.now()
      )}
    )
  }

}
