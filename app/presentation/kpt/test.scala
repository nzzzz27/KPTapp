package presentation

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api._
import play.api.mvc._

import infrastructure.repository.KeepRepository
import app.domain.model.Keep

@Singleton
class HomeController @Inject()(
  val controllerComponents: ControllerComponents,
  implicit val ec:          ExecutionContext,
  keepRepository:           KeepRepository
) extends BaseController {

  def index() = Action async { implicit requ =>

    for {
      keeps <- keepRepository.getAll()
    } yield {
      println(keeps)
      Ok("OK")
    }

  }

}
