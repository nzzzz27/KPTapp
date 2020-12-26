package app.infrastructure.model

import java.time.LocalDateTime

import app.infrastructure.model.Try
import app.infrastructure.model.Tag

import TryTag._
case class TryTag(
  id:            Option[Id],         // TryTag ID
  tryId:         Try.Id,             // Try ID
  tagId:         Tag.Id,             // Tag ID
  createdAt:     LocalDateTime,
  modifiedAt:    LocalDateTime
)

object TryTag {

  type Id = Long

}
