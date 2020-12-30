package app.infrastructure.repository

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import app.infrastructure.dao.ProblemTagTable
import app.domain.model.ProblemTag

class ProblemTagRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  ec:                             ExecutionContext,
  table:                          ProblemTagTable
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def getAll(): Future[Seq[ProblemTag]] = {
    db.run {
      table.query.result
    }
  }

}
