package app.domain.model

import java.time.LocalDateTime

import app.domain.model.Try
import app.domain.model.Tag

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
