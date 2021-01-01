package domain.model

import java.time.LocalDateTime

import domain.model.Try

import TryTask._
case class TryTask(
  id:            Option[Id],           // TryTassk ID
  tryId:         Try.Id,               // Try ID
  text:          String,               // 内容
  status:        Short,                // ステータス
  createdAt:     LocalDateTime,
  updatedAt:     LocalDateTime
)

object TryTask {

  type Id = Long

}
