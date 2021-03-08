package infrastructure.repository

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import infrastructure.dao.KeepTable
import domain.model.Keep

class KeepRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  ec:                             ExecutionContext,
  table:                          KeepTable
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def getAll(): Future[Seq[Keep]] = {
    db.run {
      table.query.result
    }
  }

  def create(data: Keep): Future[Long] = {
    db.run {
      // AutoIncの値を returning で取得する
      table.query returning table.query.map(_.id) += data;
    }
  }

}