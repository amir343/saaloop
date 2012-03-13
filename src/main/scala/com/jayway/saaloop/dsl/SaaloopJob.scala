package com.jayway.saaloop.dsl

import org.apache.hadoop.mapreduce.{Reducer, Job, Mapper}
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

trait SaaloopJob {

  val conf = new Configuration()

  object job {

    def apply[K1, V1, K2, V2, K3, V3, K4, V4]
      (name:String)(mapWith:Mapper[K1, V1, K2, V2], reduceWith:Reducer[K3, V3, K4, V4], hadoopConfiguration:Configuration = conf):Job = {

      val job = new Job(hadoopConfiguration, name)
      job.setMapperClass(mapWith.getClass)
      job.setReducerClass(reduceWith.getClass)
      val outputKeyType = asInstanceOf[K4]
      val outputValueType = asInstanceOf[V4]
      job.setOutputKeyClass(outputKeyType.getClass)
      job.setOutputValueClass(outputValueType.getClass)

      job
    }

  }

}
