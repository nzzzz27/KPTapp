package app.infrastructure.model

import java.time.LocalDateTime

import app.infrastructure.model.Try

import TryTask._
case class TryTask(
  id:            Option[Id],           // TryTassk ID
  tryId:         Try.Id,               // Try ID
  text:          String,               // 内容
  status:        Short,                // ステータス
  created_at:    LocalDateTime,
  modified_at:   LocalDateTime
)

object TryTask {

  type Id = Long

}
