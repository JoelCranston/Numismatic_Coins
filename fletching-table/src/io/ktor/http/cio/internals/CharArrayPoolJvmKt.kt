package io.ktor.http.cio.internals

internal fun isPoolingDisabled(): Boolean {
   val var10000: java.lang.String = System.getProperty("ktor.internal.cio.disable.chararray.pooling");
   return var10000 != null && java.lang.Boolean.parseBoolean(var10000);
}
