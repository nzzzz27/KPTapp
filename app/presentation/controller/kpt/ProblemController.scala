package presentation

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api._
import play.api.mvc.{ ControllerComponents, BaseController, Request, AnyContent }
import play.api.libs.json._
import play.api.i18n.I18nSupport

import application.ProblemApplication

@Singleton
class ProblemController @Inject()(
  val controllerComponents: ControllerComponents,
  problemApplication:       ProblemApplication
)(implicit ec:  ExecutionContext) extends BaseController with I18nSupport{

  def index() = Action async { implicit req => for {
    jsValueSeq <- problemApplication.getAll()
  } yield {
    import presentation.json.writes._
    Ok(Json.toJson(jsValueSeq))
  }}
}
