package presentation.json.writes

import java.time.LocalDateTime

import play.api.libs.json._
import play.api.libs.functional.syntax._

import domain.model.TagColor

case class JsValueTagColor(
  id:            Option[TagColor.Id],    // TagColor ID
  color:         String,                 // カラーコード
  createdAt:     LocalDateTime,
  updatedAt:     LocalDateTime
)

object JsValueTagColor {

  implicit val jsValueTagColor: Writes[JsValueTagColor] = (
    (__ \ "id"           ).write[Option[Long]]    and
    (__ \ "color"        ).write[String]          and
    (__ \ "createdAt"    ).write[LocalDateTime]   and
    (__ \ "updatedAt"    ).write[LocalDateTime]
  )(unlift(JsValueTagColor.unapply))

  def apply(tagColor: TagColor): JsValueTagColor =
    JsValueTagColor(
      id        = tagColor.id,
      color     = tagColor.color,
      createdAt = tagColor.createdAt,
      updatedAt = tagColor.updatedAt
    )

}
