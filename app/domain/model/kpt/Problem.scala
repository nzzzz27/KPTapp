package domain.model

import java.time.LocalDateTime

import Problem._
case class Problem(
  id:            Option[Id],            // Problem ID
  text:          String,                // 内容
  createdAt:     LocalDateTime,
  updatedAt:     LocalDateTime
)

object Problem {

  type Id = Long

}
