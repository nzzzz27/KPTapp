package app.domain.model

import java.time.LocalDateTime

import app.domain.model.Problem

import Try._
case class Try(
  id:            Option[Id],           // Try ID
  problemId:     Problem.Id,           // Problem ID
  text:          String,               // 内容
  status:        Short,                // ステータス
  createdAt:     LocalDateTime,
  modifiedAt:    LocalDateTime
)

object Try {

  type Id = Long

  sealed abstract class Status(val code: Short, val name: String)
  object Status {
    case object IS_TODO     extends Status(code = 0,   name = "TODO")
    case object IS_DONE     extends Status(code = 100, name = "DONE")
  }

}
