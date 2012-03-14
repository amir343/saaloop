# Saaloop - A Scala DSL for Hadoop

## Introduction

[Saaloop](https://github.com/amir343/saaloop) is an experimental DSL implementation on top of [Apache Hadoop](http://hadoop.apache.org/) Java library. It avails from the strong Scala Type
System and provides you to write concise and easy-to-read DSL without any XML configuration

## How does it look like?

The classic word counts example looks like this in Saaloop:

```scala
import com.jayway.saaloop.dsl.Saaloop._

object WordCount {

  def main(args: Array[String]) {

    val c = hadoopConfig {
      ("fs.temp.dir" << "/tmp") +
      ("dfs.name.dir" << "/tmp") +
      ("dfs.info.port" << "13400")
    }

    val m = mapper {
      (i: Long, s: String) => s.split(" ").toList.map( w => (w, 1) )
    }

    val r = reducer {
      (w: String, list: List[Int]) => (w, list.sum)
    }

    job("myjob")(
      mapWith = m,
      reduceWith = r,
      hadoopConfiguration = c
    )
  }

}

```

Compare it to [this](http://wiki.apache.org/hadoop/WordCount)

## How to build

Simply clone the project and run `sbt` in the project root.