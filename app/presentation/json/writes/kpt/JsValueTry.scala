package presentation.json.writes

import java.time.LocalDateTime

import play.api.libs.json._
import play.api.libs.functional.syntax._

import domain.model.Try

// slaca -> json
case class JsValueTry(
  id:         Option[Try.Id],  // Try ID
  text:       String,          // 内容
  status:     Short,           // ステータス
  createdAt:  LocalDateTime,
  updatedAt:  LocalDateTime
)

object JsValueTry {
  implicit val jsValueTry: Writes[JsValueTry] = (
    (__ \ "id"        ).write[Option[Long]]    and
    (__ \ "text"      ).write[String]          and
    (__ \ "status"    ).write[Short]           and
    (__ \ "createdAt" ).write[LocalDateTime]   and
    (__ \ "updatedAt" ).write[LocalDateTime]
  )(unlift(JsValueTry.unapply))

  def apply(item: Try): JsValueTry =
    JsValueTry(
      id        = item.id,
      text      = item.text,
      status    = item.status,
      createdAt = item.createdAt,
      updatedAt = item.updatedAt
    )
}