package infrastructure.dao

import java.time.LocalDateTime
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

import domain.model.TryTag
import domain.model.{ Tag => KptTag }
import domain.model.Try

class TryTagTable @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  type TableElementTuple = (
    Option[TryTag.Id],
    KptTag.Id,
    Try.Id,
    LocalDateTime,
    LocalDateTime
  )

  val query = TableQuery[TableColumn]

  protected class TableColumn(tag: Tag) extends Table[TryTag](tag, "TryTag") {
    def id        = column[TryTag.Id]    ("id", O.PrimaryKey, O.AutoInc)
    def tagId     = column[KptTag.Id]    ("tag_id")
    def tryId     = column[Try.Id]       ("try_id")
    def createdAt = column[LocalDateTime]("created_at")
    def updatedAt = column[LocalDateTime]("updated_at")

    // DB <=> Scala の相互のmapping定義
    def * = (id.?, tagId, tryId, createdAt, updatedAt).<>(
      // Tuple(table) => Model
      (t: TableElementTuple) => TryTag(
        t._1, t._2, t._3, t._4, t._5
      ),
      // Model => Tuple(table)
      (v: TryTag) => TryTag.unapply(v).map { t => (
        t._1, t._2, t._3, LocalDateTime.now(), LocalDateTime.now()
      )}
    )
  }

}
