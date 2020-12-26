package app.infrastructure.model

import java.time.LocalDateTime

import app.infrastructure.model.Tag

import KeepTag._
case class KeepTag(
  id:            Option[Id],         // KeepTag ID
  tagId:         Tag.Id,             // Tag ID
  keepId:        Keep.Id,            // Keep ID
  createdAt:     LocalDateTime,
  modifiedAt:    LocalDateTime
)

object KeepTag {

  type Id = Long

}
