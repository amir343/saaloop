package com.jayway.saaloop.dsl

import org.specs2.mutable.Specification
import com.jayway.saaloop.dsl.Saaloop._
import org.apache.hadoop.mapreduce.Reducer
import org.apache.hadoop.io.{Text, LongWritable}

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

class ReducerSpec extends Specification {
  
  "Reducer" should {
    "return correct instance of Reducer from MapReduce for pairs" in {
      val r = reducer {
        (i:LongWritable, s:Text) => (i, s)
      }
      r mustNotEqual null
      r match {
        case s: Reducer[LongWritable, Text, LongWritable, Text] => true
        case _                                    => false
      }
    }
    "return correct instance of Reducer from MapReduce for list of pairs" in {
      val r = reducer {
        (i:LongWritable, s:Text) => List((i, s))
      }
      r mustNotEqual null
      r match {
        case s: Reducer[LongWritable, Text, LongWritable, Text] => true
        case _                                    => false
      }
    }
  }

}
