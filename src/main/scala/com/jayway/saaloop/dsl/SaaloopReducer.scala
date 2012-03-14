package com.jayway.saaloop.dsl

import org.apache.hadoop.mapreduce.Reducer

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

trait SaaloopReducer extends Types {

  implicit def reducePairs[K1 <: key, V1 <: value, K2 <: key, V2 <: value] = new Fun[(K1, V1) => (K2, V2), Reducer[K1, V1, K2, V2]] {
    def apply(f:(K1, V1) => (K2, V2)) = {
      new Reducer[K1, V1, K2, V2] {
        def reduce(k1:K1, v1:V1, context:Context) {
          val (key, value) = f(k1, v1)
          context.write(key, value)
        }
      }
    }
  }

  implicit def reducePairs2[K1 <: key, V1 <: value, K2 <: key, V2 <: value] = new Fun[(K1, List[V1]) => (K2, V2), Reducer[K1, V1, K2, V2]] {
    def apply(f:(K1, List[V1]) => (K2, V2)) = {
      new Reducer[K1, V1, K2, V2] {
        def reduce(k1:K1, v1:List[V1], context:Context) {
          val (key, value) = f(k1, v1)
          context.write(key, value)
        }
      }
    }
  }

  implicit def reduceListPairs[K1 <: key, V1 <: value, K2 <: key, V2 <: value] = new Fun[(K1, V1) => List[(K2, V2)], Reducer[K1, V1, K2, V2]] {
    def apply(f:(K1, V1) => List[(K2, V2)]) = {
      new Reducer[K1, V1, K2, V2] {
        def reduce(k1:K1, v1:V1, context:Context) {
          val result = f(k1, v1)
          result foreach { p:(K2, V2) => context.write(p._1, p._2) }
        }
      }
    }
  }

  implicit def reduceListPairs2[K1 <: key, V1 <: value, K2 <: key, V2 <: value] = new Fun[(K1, List[V1]) => List[(K2, V2)], Reducer[K1, V1, K2, V2]] {
    def apply(f:(K1, List[V1]) => List[(K2, V2)]) = {
      new Reducer[K1, V1, K2, V2] {
        def reduce(k1:K1, v1:List[V1], context:Context) {
          val result = f(k1, v1)
          result foreach { p:(K2, V2) => context.write(p._1, p._2) }
        }
      }
    }
  }

  object reducer {
    def apply[F, K1, V1, K2, V2](f:F)(implicit fun:Fun[F, Reducer[K1, V1, K2, V2]]) = {
      fun(f)
    }
  }
}
