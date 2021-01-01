package infrastructure.dao

import java.time.LocalDateTime
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

import domain.model.Keep

class KeepTable @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  type TableElementTuple = (
    Option[Keep.Id],
    String,
    LocalDateTime,
    LocalDateTime
  )

  val query = TableQuery[TableColumn]

  protected class TableColumn(tag: Tag) extends Table[Keep](tag, "Keep") {
    def id         = column[Keep.Id]      ("id", O.PrimaryKey, O.AutoInc)
    def text       = column[String]       ("text")
    def createdAt  = column[LocalDateTime]("created_at")
    def updatedAt  = column[LocalDateTime]("updated_at")

    //  DB <=> Scala の相互のmapping定義
    def * = (id.?, text, createdAt, updatedAt).<>(
      // Tuple(table) => Model
      (t: TableElementTuple) => Keep(
        t._1, t._2, t._3, t._4
      ),
      // Model => Tuple(table)
      (v: Keep) => Keep.unapply(v).map { t => (
        t._1, t._2, LocalDateTime.now(), LocalDateTime.now()
      )}
    )
  }

}
