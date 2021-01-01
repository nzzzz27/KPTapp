package app.domain.service

import java.sql.Timestamp
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

/* slick3 以降から日付型のデフォルトがTimestampになった。
 * その為、LocalDateTimeと相互変換のメソッドを作成。
 * DAOで使うメソッドなのに、ドメインサービスにおいて良いのか謎....
 */
trait DateTypeService {
  def toLocalDateTime(timestamp: Timestamp): LocalDateTime = {
    val date = timestamp.toString.replace("-", "/").replace(".0", "")
    LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))
  }

  def toTimestamp(original: LocalDateTime): Timestamp = {
    val date: String = original.toString.replace("T", " ")
    Timestamp.valueOf(date);
  }
}
