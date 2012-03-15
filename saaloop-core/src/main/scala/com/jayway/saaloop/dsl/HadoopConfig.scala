package com.jayway.saaloop.dsl

import org.apache.hadoop.conf.Configuration

/**
 * Copyright 2012 Amir Moulavi (amir.moulavi@gmail.com)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * @author Amir Moulavi
 */

trait HadoopConfig {

  implicit def convertStringToConfigProperty(in: String): ConfigProperty = new ConfigProperty(in)

  object hadoopConfig {

    def apply():Configuration = new Configuration()

    def apply(propertyList:ConfigProperty):Configuration = {
      val conf = new Configuration()
      propertyList.properties foreach { p:ConfigProperty =>
        p.get match {
          case (k, v) => conf.set(k, v)
        }
      }
      conf
    }

  }

}

private[dsl] class ConfigProperty(key:String) {

  private var _value:String = _
  private var list:collection.mutable.ListBuffer[ConfigProperty] = new collection.mutable.ListBuffer[ConfigProperty]()

  def <<(value:String):ConfigProperty = {
    this._value = value
    list += this
    this
  }

  def +(p:ConfigProperty):ConfigProperty = {
    list += p
    this
  }

  def properties:collection.mutable.ListBuffer[ConfigProperty] = list
  
  def get = (key, _value)
}

