/* 
** Copyright [2013-2014] [Megam Systems]
**
** Licensed under the Apache License, Version 2.0 (the "License");
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
** http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/
package models.json

import scalaz._
import scalaz.NonEmptyList._
import scalaz.Validation
import scalaz.Validation._
import Scalaz._
import net.liftweb.json._
import net.liftweb.json.scalaz.JsonScalaz._
import java.util.Date
import java.nio.charset.Charset
import controllers.funnel.FunnelErrors._
import controllers.Constants._
import controllers.funnel.SerializationBase
import models.{ MarketPlaceAddonsConfigMonitoring }
/**
 * @author rajthilak
 *
 */
object MarketPlaceAddonsConfigMonitoringSerialization  extends SerializationBase[MarketPlaceAddonsConfigMonitoring] {
  protected val AgentKey = "agent"  
  protected val Recipe  = "recipe"
  override implicit val writer = new JSONW[MarketPlaceAddonsConfigMonitoring] {

    override def write(h: MarketPlaceAddonsConfigMonitoring): JValue = {
      JObject(
        JField(AgentKey, toJSON(h.agent)) ::
        JField(Recipe, toJSON(h.recipe)) ::
           Nil)
    }
  }

  override implicit val reader = new JSONR[MarketPlaceAddonsConfigMonitoring] {

    override def read(json: JValue): Result[MarketPlaceAddonsConfigMonitoring] = {
      val agentField = field[String](AgentKey)(json)
      val recipeField = field[String](Recipe)(json)
      (agentField |@| recipeField) {
        (agent: String, recipe: String) =>
          new MarketPlaceAddonsConfigMonitoring(agent, recipe)
      }
    }
  }
}