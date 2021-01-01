package domain.model

import java.time.LocalDateTime

import TagColor._
case class TagColor(
  id:            Option[Id],         // TagColor ID
  color:         String,             // カラーコード
  createdAt:     LocalDateTime,
  updatedAt:     LocalDateTime
)

object TagColor {

  type Id = Long

}
