package domain.model

import java.time.LocalDateTime

import Keep._
case class Keep(
  id:            Option[Id],         // Keep ID
  text:          String,             // 内容
  createdAt:     LocalDateTime,
  updatedAt:     LocalDateTime
)

object Keep {

  type Id = Long

}
