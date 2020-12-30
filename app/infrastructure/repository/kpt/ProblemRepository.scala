package app.infrastructure.repository

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import app.infrastructure.dao.ProblemTable
import app.domain.model.Problem

class ProblemRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  ec:                             ExecutionContext,
  table:                          ProblemTable
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def getAll(): Future[Seq[Problem]] = {
    db.run {
      table.query.result
    }
  }

}
