package app.infrastructure.model

import java.time.LocalDateTime

case class Keep(
  id:            Option[Long],         // Keep ID
  text:          String,               // Keepの内容
  created_at:    LocalDateTime,
  modified_at:   LocalDateTime
)
