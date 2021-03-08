package presentation.json.reads

import java.time.LocalDateTime

import play.api.libs.json._
import play.api.libs.functional.syntax._

import domain.model.Keep

// json -> slaca
case class KeepJson(
  id:         Option[Keep.Id],    // Keep ID
  text:       String,             // 内容
  createdAt:  LocalDateTime,
  updatedAt:  LocalDateTime
)

object KeepJson {
  implicit val keepJson = Json.reads[KeepJson]
}
