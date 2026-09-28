package io.ktor.util

import java.util.Collections

public fun <T> Set<Any>.unmodifiable(): Set<Any> {
   val var10000: java.util.Set = Collections.unmodifiableSet(`$this$unmodifiable`);
   return var10000;
}
