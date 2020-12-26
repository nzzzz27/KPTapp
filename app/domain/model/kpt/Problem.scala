package app.domain.model

import java.time.LocalDateTime

import Problem._
case class Problem(
  id:            Option[Id],            // Problem ID
  text:          String,                // 内容
  createdAt:     LocalDateTime,
  modifiedAt:    LocalDateTime
)

object Problem {

  type Id = Long

}
