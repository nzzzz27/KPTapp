package infrastructure.repository

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import infrastructure.dao.TryTagTable
import domain.model.TryTag

class TryTagRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  ec:                             ExecutionContext,
  table:                          TryTagTable
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def getAll(): Future[Seq[TryTag]] = {
    db.run {
      table.query.result
    }
  }

}
