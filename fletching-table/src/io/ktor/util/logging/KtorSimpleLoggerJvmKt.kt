package io.ktor.util.logging

import org.slf4j.Logger
import org.slf4j.LoggerFactory

public fun KtorSimpleLogger(name: String): Logger {
   val var10000: Logger = LoggerFactory.getLogger(name);
   return var10000;
}
