package domain.model

import java.time.LocalDateTime

import domain.model.Try
import domain.model.Tag

import TryTag._
case class TryTag(
  id:            Option[Id],         // TryTag ID
  tryId:         Try.Id,             // Try ID
  tagId:         Tag.Id,             // Tag ID
  createdAt:     LocalDateTime,
  updatedAt:     LocalDateTime
)

object TryTag {

  type Id = Long

}
