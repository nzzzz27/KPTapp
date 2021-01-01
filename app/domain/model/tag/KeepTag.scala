package domain.model

import java.time.LocalDateTime

import domain.model.Keep
import domain.model.Tag

import KeepTag._
case class KeepTag(
  id:            Option[Id],         // KeepTag ID
  tagId:         Tag.Id,             // Tag ID
  keepId:        Keep.Id,            // Keep ID
  createdAt:     LocalDateTime,
  updatedAt:     LocalDateTime
)

object KeepTag {

  type Id = Long

}
