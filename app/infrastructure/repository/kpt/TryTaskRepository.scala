package infrastructure.repository

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import infrastructure.dao.TryTaskTable
import domain.model.TryTask

class TryTaskRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  ec:                             ExecutionContext,
  table:                          TryTaskTable
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def getAll(): Future[Seq[TryTask]] = {
    db.run {
      table.query.result
    }
  }

}
