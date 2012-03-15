package com.jayway.saaloop.dsl

import org.specs2.mutable.Specification
import com.jayway.saaloop.dsl.Saaloop._
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

class HadoopConfigurationSpec extends Specification {

  "Hadoop configuration" should {
    "contain arbitrary number of properties" in {
      val conf = hadoopConfig {
          ("fs.temp.dir" << "/tmp") +
          ("dfs.name.dir" << "/tmp") +
          ("dfs.info.port" << "13400")
      }
      conf.get("fs.temp.dir") mustEqual "/tmp"
      conf.get("dfs.name.dir") mustEqual "/tmp"
      conf.get("dfs.info.port") mustEqual "13400"
    }
    "be instantiable with default configurations" in {
      hadoopConfig() mustNotEqual null
    }
  }

}
