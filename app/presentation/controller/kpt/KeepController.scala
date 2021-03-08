package presentation

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }

import play.api._
import play.api.mvc.{ ControllerComponents, BaseController, Request, AnyContent }
import play.api.libs.json._
import play.api.i18n.I18nSupport

import application.KeepApplication

@Singleton
class KeepController @Inject()(
  val controllerComponents: ControllerComponents,
  keepApplication:          KeepApplication
)(implicit ec:  ExecutionContext) extends BaseController with I18nSupport{

  def index() = Action async { implicit req => for {
    jsValueSeq <- keepApplication.getAll()
  } yield {
    import presentation.json.writes._
    Ok(Json.toJson(jsValueSeq))
  }}

  def post() = Action { implicit req =>
    import presentation.json.reads._
    req.body.asJson.map {json =>
      println("ok" + json)
        Ok("ok")
      // println(json)
    }.getOrElse(BadRequest("Missing"))
    // req.body.validate[KeepJson] match {
    //   case data: KeepJson => {
    //     println("aa")
    //     Ok("ok")
    //   }
    //   case _ => {
    //     Ok("not ok")
    //   }
    // }
  }
}
