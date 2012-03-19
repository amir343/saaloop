package com.jayway.saaloop.dsl

import org.apache.hadoop.mapreduce.{Reducer, Job, Mapper}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.fs.Path

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

trait SaaloopJob extends Types {

  val conf = new Configuration()

  object job {

    def apply[K1 <: key, V1 <: value, K2 <: key, V2 <: value, K3 <: key, V3 <: value, K4 <: key, V4 <: value, FIN <: fin, FOUT <: fout]
      (name:String)(mapWith:Mapper[K1, V1, K2, V2], 
                    reduceWith:Reducer[K3, V3, K4, V4], 
                    hadoopConfiguration:Configuration = conf,
                    inputPath:String = ".",
                    outputPath:String = ".",
                    inputFormatClass:Class[FIN] = null,
                    outputFormatClass:Class[FOUT] = null,
                    waitForCompletion:Boolean = false):Job = {

      val job = new Job(hadoopConfiguration, name)
      job.setMapperClass(mapWith.getClass)
      job.setReducerClass(reduceWith.getClass)
      val outputKeyType = asInstanceOf[K4]
      val outputValueType = asInstanceOf[V4]
      job.setOutputKeyClass(outputKeyType.getClass)
      job.setOutputValueClass(outputValueType.getClass)
      FileInputFormat.addInputPath(job, new Path(inputPath))
      FileOutputFormat.setOutputPath(job, new Path(outputPath))
      if (inputFormatClass != null) job.setInputFormatClass(inputFormatClass)
      if (outputFormatClass != null) job.setOutputFormatClass(outputFormatClass)
      job.waitForCompletion(waitForCompletion)

      job
    }

  }

}
