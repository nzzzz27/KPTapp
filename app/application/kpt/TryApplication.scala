package application

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api.mvc.ControllerComponents

import infrastructure.repository.TryRepository
import presentation.json.writes.JsValueTry

@Singleton
class TryApplication @Inject()(
  val controllerComponents: ControllerComponents,
  tryRepository:            TryRepository
)(implicit ec: ExecutionContext) {

  def getAll(): Future[Seq[JsValueTry]] = {
    for {
      trySeq <- tryRepository.getAll()
    } yield trySeq.map(JsValueTry(_))
  }

}
