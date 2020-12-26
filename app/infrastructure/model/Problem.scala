package app.infrastructure.model

import java.time.LocalDateTime

case class Problem(
  id:            Option[Long],       // Problem ID
  text:          String,             // 内容
  created_at:    LocalDateTime,
  modified_at:   LocalDateTime
)
