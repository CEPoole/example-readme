package models

import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json

class UserInfoSpec extends PlaySpec {

  "UserInfo" should {

    val testName = "example user's name"
    val age = 42
    val location = "Example location"

    val fullJson = Json.parse(
      s"""
         |{
         |  "name": "$testName",
         |  "age": $age,
         |  "location": "$location"
         |}""".stripMargin)

    val minimumJson = Json.parse(s"""{ "name": "$testName" }""".stripMargin)


    "create valid JSON" when {
      "all values are provided" in {
        Json.toJson(UserInfo(testName, Some(age), Some(location))) mustBe fullJson
      }
      "no optional values are provided" in {
        Json.toJson(UserInfo(testName, None, None)) mustBe minimumJson
      }
    }

    "create a valid model from JSON" when {
      "all values are provided" in {
        fullJson.as[UserInfo] mustBe UserInfo(testName, Some(age), Some(location))
      }
      "no optional values are provided" in {
        minimumJson.as[UserInfo] mustBe UserInfo(testName, None, None)
      }
    }
  }
}