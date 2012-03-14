import com.jayway.saaloop.dsl.Saaloop._
import org.apache.hadoop.io.{IntWritable, Text, LongWritable}

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

object WordCount {

  def main(args: Array[String]) {

    val c = hadoopConfig {
      ("fs.temp.dir" << "/tmp") +
      ("dfs.name.dir" << "/tmp") +
      ("dfs.info.port" << "13400")
    }

    val m = mapper {
      (i: LongWritable, s: Text) => s.toString.split(" ").toList.map( w => (writable(w), writable(1)) )
    }

    val r = reducer {
      (key: Text, list: List[IntWritable]) => (key, writable(list.foldLeft(0)(_ + _.get())) )
    }

    job("myjob")(
      mapWith = m,
      reduceWith = r,
      hadoopConfiguration = c
    )
  }

}
