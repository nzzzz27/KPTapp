package presentation.json.writes

import java.time.LocalDateTime

import play.api.libs.json._
import play.api.libs.functional.syntax._

import domain.model.Problem

// slaca -> json
case class JsValueProblem(
  id:         Option[Problem.Id],  // Problem ID
  text:       String,              // 内容
  createdAt:  LocalDateTime,
  updatedAt:  LocalDateTime
)

object JsValueProblem {
  implicit val jsValueProblem: Writes[JsValueProblem] = (
    (__ \ "id"        ).write[Option[Long]]    and
    (__ \ "text"      ).write[String]          and
    (__ \ "createdAt" ).write[LocalDateTime]   and
    (__ \ "updatedAt" ).write[LocalDateTime]
  )(unlift(JsValueProblem.unapply))

  def apply(problem: Problem): JsValueProblem =
    JsValueProblem(
      id        = problem.id,
      text      = problem.text,
      createdAt = problem.createdAt,
      updatedAt = problem.updatedAt
    )
}