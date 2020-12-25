package dao

import java.time.LocalDateTime
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import scala.concurrent.ExecutionContext
import slick.driver.JdbcProfile

class KeepDAO @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  implicit  val ec: ExecutionContext
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  private val keep = TableQuery[Keep]

  class Keep(tag: Tag) extends Table[Keep](tag, "Keep") {
    def id          = column[Option[Long]] ("id", O.PrimaryKey, O.AutoInc)
    def text        = column[String]       ("text")
    def created_at  = column[LocalDateTime]("created_at")
    def modified_at = column[LocalDateTime]("modified_at")
    def * = (id, text, created_at, modified_at).<>(Keep.tupled, Keep.unapply)
  }

}
case class Keep(
  id:            Option[Long],         // Keep ID
  text:          String,               // Keepの内容
  created_at:    LocalDateTime,
  modified_at:   LocalDateTime
)
