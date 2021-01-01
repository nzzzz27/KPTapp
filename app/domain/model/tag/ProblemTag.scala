package domain.model

import java.time.LocalDateTime

import domain.model.Problem
import domain.model.Tag

import ProblemTag._
case class ProblemTag(
  id:            Option[Id],         // ProblemTag ID
  problemId:     Problem.Id,         // Problem ID
  tagId:         Tag.Id,             // Tag ID
  createdAt:     LocalDateTime,
  updatedAt:     LocalDateTime
)

object ProblemTag {

  type Id = Long

}
