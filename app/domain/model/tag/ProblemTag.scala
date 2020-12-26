package app.domain.model

import java.time.LocalDateTime

import app.domain.model.Problem
import app.domain.model.Tag

import ProblemTag._
case class ProblemTag(
  id:            Option[Id],         // ProblemTag ID
  problemId:     Problem.Id,         // Problem ID
  tagId:         Tag.Id,             // Tag ID
  createdAt:     LocalDateTime,
  modifiedAt:    LocalDateTime
)

object ProblemTag {

  type Id = Long

}
