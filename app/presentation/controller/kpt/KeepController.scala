package presentation

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api._
import play.api.mvc.{ ControllerComponents, BaseController, Request, AnyContent }
import play.api.libs.json._
import play.api.i18n.I18nSupport

import application.KeepApplication
import model.ViewValueCommon

@Singleton
class KeepController @Inject()(
  val controllerComponents: ControllerComponents,
  keepApplication:          KeepApplication
)(implicit ec:  ExecutionContext) extends BaseController with I18nSupport{

  def index() = Action async { implicit req => for {
    jsValueSeq <- keepApplication.getAll()
  } yield {
    import presentation.json.writes._
    Ok(views.html.index(ViewValueCommon()))
    // Ok(Json.toJson(jsValueSeq))
  }}
}
