package app.infrastructure.repository

import slick.jdbc.MySQLProfile.api._      // slickの .result とかを使うため
import javax.inject.{ Inject, Singleton }

import play.api.db.slick.{
  DatabaseConfigProvider,
  HasDatabaseConfigProvider,   // DIでSlickデータベースとプロファイルを使用するために必要
}
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

import app.infrastructure.dao.KeepTable
import app.domain.model.Keep

class KeepRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  ec:                             ExecutionContext,
  keep:                           KeepTable
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def getAll(): Future[Seq[Keep]] = {
    db.run {
      keep.query.result
    }
  }

}
