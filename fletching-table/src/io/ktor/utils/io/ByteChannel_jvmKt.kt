package io.ktor.utils.io

private const val DEVELOPMENT_MODE_KEY: String = "io.ktor.development"

internal final val DEVELOPMENT_MODE: Boolean
   internal final get() {
      val var10000: java.lang.String = System.getProperty("io.ktor.development");
      return var10000 != null && java.lang.Boolean.parseBoolean(var10000);
   }

