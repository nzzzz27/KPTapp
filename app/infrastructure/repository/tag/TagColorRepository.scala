package infrastructure.repository

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import infrastructure.dao.TagColorTable
import domain.model.TagColor

class TagColorRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  ec:                             ExecutionContext,
  table:                          TagColorTable
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def getAll(): Future[Seq[TagColor]] = {
    db.run {
      table.query.result
    }
  }

}
