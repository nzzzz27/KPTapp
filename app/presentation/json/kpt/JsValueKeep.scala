package presentation.json.writes

import java.time.LocalDateTime

import play.api.libs.json._
import play.api.libs.functional.syntax._

import domain.model.Keep

// slaca -> json
case class JsValueKeep(
  id:            Option[Keep.Id],    // Keep ID
  text:          String,             // 内容
  createdAt:     LocalDateTime,
  updatedAt:     LocalDateTime
)

object JsValueKeep {

  // JsPath の代わりに __ と書いてもOK
  implicit val jsValueKeep: Writes[JsValueKeep] = (
    (JsPath \ "id"           ).write[Option[Long]]    and
    (JsPath \ "text"         ).write[String]          and
    (JsPath \ "createdAt"    ).write[LocalDateTime]   and
    (JsPath \ "updatedAt"    ).write[LocalDateTime]
  )(unlift(JsValueKeep.unapply))

  def apply(keep: Keep): JsValueKeep = JsValueKeep(keep)

}
