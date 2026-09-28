@file:SourceDebugExtension(["SMAP\nServerSentEvent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServerSentEvent.kt\nio/ktor/sse/ServerSentEventKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,117:1\n1869#2,2:118\n*S KotlinDebug\n*F\n+ 1 ServerSentEvent.kt\nio/ktor/sse/ServerSentEventKt\n*L\n100#1:118,2\n*E\n"])

package io.ktor.sse

import io.ktor.utils.io.InternalAPI
import kotlin.jvm.internal.SourceDebugExtension

@InternalAPI
public const val COLON: String = ":"

@InternalAPI
public const val SPACE: String = " "

@InternalAPI
public const val END_OF_LINE: String = "\r\n"

@InternalAPI
public final val END_OF_LINE_VARIANTS: Regex = new Regex("\r\n|\r|\n")

private fun eventToString(data: String?, event: String?, id: String?, retry: Long?, comments: String?): String {
   val var5: StringBuilder = new StringBuilder();
   appendField(var5, "event", event);
   appendField(var5, "data", data);
   appendField(var5, "id", id);
   appendField(var5, "retry", retry);
   appendField(var5, "", comments);
   return var5.toString();
}

private fun <T> StringBuilder.appendField(name: String, value: Any?) {
   if (value != null) {
      val `$this$forEach$iv`: java.lang.CharSequence = value.toString();

      for (Object element$iv : var10) {
         `$this$appendField`.append("$name: ${`element$iv` as java.lang.String}\r\n");
      }
   }
}

@JvmSynthetic
fun `access$eventToString`(data: java.lang.String, event: java.lang.String, id: java.lang.String, retry: java.lang.Long, comments: java.lang.String): java.lang.String {
   return eventToString(data, event, id, retry, comments);
}
