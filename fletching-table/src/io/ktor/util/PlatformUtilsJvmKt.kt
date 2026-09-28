package io.ktor.util

private const val DEVELOPMENT_MODE_KEY: String = "io.ktor.development"

public final val platform: Platform
   public final get() {
      return Platform.Jvm.INSTANCE;
   }


internal final val isDevelopmentMode: Boolean
   internal final get() {
      val var10000: java.lang.String = System.getProperty("io.ktor.development");
      return var10000 != null && java.lang.Boolean.parseBoolean(var10000);
   }

