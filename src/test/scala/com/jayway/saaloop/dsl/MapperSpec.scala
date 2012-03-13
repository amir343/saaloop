package com.jayway.saaloop.dsl

import org.specs2.mutable.Specification
import com.jayway.saaloop.dsl.Saaloop._
import org.apache.hadoop.mapreduce.Mapper

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

class MapperSpec extends Specification {
  
  "Mapper" should {
    "return correct instance of Mapper from MapReduce for pairs" in {
      val m = mapper {
        (i:Int, s:String) => (i, s.length())
      }
      m mustNotEqual null
      m match {
        case s:Mapper[Int, String, Int, Int] => true
        case _                               => false
      }
    }
    "return correct instance of Mapper from MapReduce for list of pairs" in {
      val m = mapper {
        (i:Int, s:String) => List((i, s.length()))
      }
      m mustNotEqual null
      m match {
        case s:Mapper[Int, String, Int, Int] => true
        case _                               => false
      }
    }
  }

}
