package application

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api._
import play.api.mvc._

import infrastructure.repository.KeepRepository
import presentation.json.writes.JsValueKeep
import domain.model.Keep

@Singleton
class KeepApplication @Inject()(
  val controllerComponents: ControllerComponents,
  implicit val ec:          ExecutionContext,
  keepRepository:           KeepRepository
) extends BaseController {

  def getAll(): Future[Seq[JsValueKeep]] = {
    for {
      keepSeq <- keepRepository.getAll()
    } yield {
      keepSeq.map(JsValueKeep(_))
    }
  }

}
