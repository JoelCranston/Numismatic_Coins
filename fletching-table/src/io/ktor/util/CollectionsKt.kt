package io.ktor.util

public fun <Value : Any> caseInsensitiveMap(): MutableMap<String, Any> {
   return new CaseInsensitiveMap();
}
