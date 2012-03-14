package com.jayway.saaloop.dsl

import org.apache.hadoop.io.{Text, LongWritable, IntWritable}


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

trait SaaloopWritable {

  object writable {
    def apply(v:Int):IntWritable = new IntWritable(v)
    def apply(v:Long):LongWritable = new LongWritable(v)
    def apply(v:String):Text = new Text(v)
  }
}
