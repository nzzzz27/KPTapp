package presentation.json.writes

import java.time.LocalDateTime

import play.api.libs.json._
import play.api.libs.functional.syntax._

import domain.model.{ Tag => kptTag }

case class JsValueTag(
  id:            Option[kptTag.Id],     // Tag ID
  color:         JsValueTagColor,    // Tagの色
  name:          String,             // 名称
  createdAt:     LocalDateTime,
  updatedAt:     LocalDateTime
)

object JsValueTag {

  implicit val jsValueTag: Writes[JsValueTag] = (
    (__ \ "id"           ).write[Option[Long]]    and
    (__ \ "color"        ).write[JsValueTagColor] and
    (__ \ "name"         ).write[String]          and
    (__ \ "createdAt"    ).write[LocalDateTime]   and
    (__ \ "updatedAt"    ).write[LocalDateTime]
  )(unlift(JsValueTag.unapply))

  def create(tag: kptTag, tagColor: JsValueTagColor): JsValueTag =
    JsValueTag(
      id        = tag.id,
      color     = tagColor,
      name      = tag.name,
      createdAt = tag.createdAt,
      updatedAt = tag.updatedAt
    )
}
