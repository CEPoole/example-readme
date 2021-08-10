package models

import play.api.libs.json.{Json, OFormat}


case class UserInfo(name: String,
                    age: Option[Int],
                    location: Option[String])

object UserInfo {
  implicit val format: OFormat[UserInfo] = Json.format[UserInfo]
}