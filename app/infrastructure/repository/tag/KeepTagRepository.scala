package infrastructure.repository

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import infrastructure.dao.KeepTagTable
import domain.model.KeepTag

class KeepTagRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  ec:                             ExecutionContext,
  table:                          KeepTagTable
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def getAll(): Future[Seq[KeepTag]] = {
    db.run {
      table.query.result
    }
  }

}
