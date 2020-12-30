package app.infrastructure.repository

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import app.infrastructure.dao.TryTable
import app.domain.model.Try

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
