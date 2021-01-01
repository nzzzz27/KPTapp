package infrastructure.repository

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import infrastructure.dao.TryTable
import domain.model.Try

class TryRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  ec:                             ExecutionContext,
  table:                          TryTable
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def getAll(): Future[Seq[Try]] = {
    db.run {
      table.query.result
    }
  }

}
