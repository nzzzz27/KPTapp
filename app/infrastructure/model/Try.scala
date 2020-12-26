package app.infrastructure.model

import java.time.LocalDateTime

import app.infrastructure.model.Problem

import Try._
case class Try(
  id:            Option[Id],           // Try ID
  problemId:     Problem.Id,           // Problem ID
  text:          String,               // 内容
  status:        Short,                // ステータス
  created_at:    LocalDateTime,
  modified_at:   LocalDateTime
)

object Try {

  type Id = Long

}
