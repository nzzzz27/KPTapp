package presentation

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api._
import play.api.mvc.{ ControllerComponents, BaseController, Request, AnyContent }
import play.api.libs.json._
import play.api.i18n.I18nSupport

import application.TryApplication

@Singleton
class TryController @Inject()(
  val controllerComponents: ControllerComponents,
  tryApplication:           TryApplication
)(implicit ec:  ExecutionContext) extends BaseController with I18nSupport{

  def index() = Action async { implicit req => for {
    jsValueSeq <- tryApplication.getAll()
  } yield {
    import presentation.json.writes._
    Ok(Json.toJson(jsValueSeq))
  }}
}