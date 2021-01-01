package domain.model

import java.time.LocalDateTime
import domain.model.TagColor

import Tag._
case class Tag(
  id:            Option[Id],         // Tag ID
  colorId:       TagColor.Id,        // Tagの色 ID
  name:          String,             // 名称
  createdAt:     LocalDateTime,
  updatedAt:     LocalDateTime
)

object Tag {

  type Id = Long

}
