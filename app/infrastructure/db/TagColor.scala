package app.infrastructure.db

import java.time.LocalDateTime
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

import app.infrastructure.model.TagColor

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

  private val query = TableQuery[TableColumn]

  private class TableColumn(tag: Tag) extends Table[TagColor](tag, "TagColor") {
    def id          = column[TagColor.Id]  ("id", O.PrimaryKey, O.AutoInc)
    def color       = column[String]       ("color")
    def created_at  = column[LocalDateTime]("created_at")
    def modified_at = column[LocalDateTime]("modified_at")

    // DB <=> Scala の相互のmapping定義
    def * = (id.?, color, created_at, modified_at) <> (
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
