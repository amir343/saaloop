package com.jayway.saaloop.dsl

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

trait SaaloopMapper extends Types {

  implicit def mapPairs[K1 <: key, V1 <: value, K2 <: key, V2 <: value] = new Fun[(K1, V1) => (K2, V2), Mapper[K1, V1, K2, V2]] {
    def apply(f: (K1, V1) => (K2, V2)) = {
      new Mapper[K1, V1, K2, V2] {
        def map(k:K1, v:V1, context:Context) {
          val (key, value) = f(k, v)
          context.write(key, value)
        }
      }
    }
  }

  implicit def mapPairs2[K1 <: key, V1 <: value, K2 <: key, V2 <: value] = new Fun[(K1, List[V1]) => (K2, V2), Mapper[K1, V1, K2, V2]] {
    def apply(f: (K1, List[V1]) => (K2, V2)) = {
      new Mapper[K1, V1, K2, V2] {
        def map(k:K1, v:List[V1], context:Context) {
          val (key, value) = f(k, v)
          context.write(key, value)
        }
      }
    }
  }

  implicit def mapListPairs[K1 <: key, V1 <: value, K2 <: key, V2 <: value] = new Fun[(K1, V1) => List[(K2, V2)], Mapper[K1, V1, K2, V2]] {
    def apply(f: (K1, V1) => List[(K2, V2)]) = {
      new Mapper[K1, V1, K2, V2] {
        def map(k:K1, v:V1, context:Context) {
          val result = f(k, v)
          result foreach { p:(K2, V2) => context.write(p._1, p._2) }
        }
      }
    }
  }

  implicit def mapListPairs2[K1 <: key, V1 <: value, K2 <: key, V2 <: value] = new Fun[(K1, List[V1]) => List[(K2, V2)], Mapper[K1, V1, K2, V2]] {
    def apply(f: (K1, List[V1]) => List[(K2, V2)]) = {
      new Mapper[K1, V1, K2, V2] {
        def map(k:K1, v:List[V1], context:Context) {
          val result = f(k, v)
          result foreach { p:(K2, V2) => context.write(p._1, p._2) }
        }
      }
    }
  }

  object mapper {
    def apply[F, K1, V1, K2, V2](f:F)(implicit fun:Fun[F, Mapper[K1, V1, K2, V2]]) = {
      fun(f)
    }
  }

}
