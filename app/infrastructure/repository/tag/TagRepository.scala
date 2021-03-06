package infrastructure.repository

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import infrastructure.dao.KptTagTable
import domain.model.{ Tag => KptTag }

class TagRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  ec:                             ExecutionContext,
  table:                          KptTagTable
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def getAll(): Future[Seq[KptTag]] = {
    db.run {
      table.query.result
    }
  }

  def getByTagIdSeq(tagIdSeq: Seq[KptTag.Id]): Future[Seq[KptTag]] = {
    db.run {
      table.query.filter(_.id inSet tagIdSeq).result
    }
  }

}
