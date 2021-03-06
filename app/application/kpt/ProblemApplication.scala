package application

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api.mvc.ControllerComponents

import infrastructure.repository.ProblemRepository
import presentation.json.writes.JsValueProblem

@Singleton
class ProblemApplication @Inject()(
  val controllerComponents: ControllerComponents,
  problemRepository:        ProblemRepository
)(implicit ec: ExecutionContext) {

  def getAll(): Future[Seq[JsValueProblem]] = {
    for {
      problemSeq <- problemRepository.getAll()
    } yield problemSeq.map(JsValueProblem(_))
  }

}