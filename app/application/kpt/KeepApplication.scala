package application

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api.mvc.ControllerComponents

import infrastructure.repository.KeepRepository
import presentation.json.writes.JsValueKeep

@Singleton
class KeepApplication @Inject()(
  val controllerComponents: ControllerComponents,
  keepRepository:           KeepRepository
)(implicit ec: ExecutionContext) {

  def getAll(): Future[Seq[JsValueKeep]] = {
    for {
      keepSeq <- keepRepository.getAll()
    } yield {
      keepSeq.map{ keep => JsValueKeep(
        id        = keep.id,
        text      = keep.text,
        createdAt = keep.createdAt,
        updatedAt = keep.updatedAt
      )}
    }
  }

}
