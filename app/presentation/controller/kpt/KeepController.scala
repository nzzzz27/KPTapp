package presentation

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api._
import play.api.mvc._

import application.KeepApplication
import domain.model.Keep

@Singleton
class KeepController @Inject()(
  val controllerComponents: ControllerComponents,
  implicit val ec:          ExecutionContext,
  keepApplication:          KeepApplication
) extends BaseController {

  def index() = Action async { implicit req =>

    for {
      jsValueKeepSeq <- keepApplication.getAll()
    } yield {
      Ok("OK")
    }

  }

}
