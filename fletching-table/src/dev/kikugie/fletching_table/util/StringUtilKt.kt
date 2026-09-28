@file:SourceDebugExtension(["SMAP\nStringUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringUtil.kt\ndev/kikugie/fletching_table/util/StringUtilKt\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,38:1\n205#2:39\n*S KotlinDebug\n*F\n+ 1 StringUtil.kt\ndev/kikugie/fletching_table/util/StringUtilKt\n*L\n13#1:39\n*E\n"])

package dev.kikugie.fletching_table.util

import dev.kikugie.fletching_table.util.StringUtilKt.KEY_COMPARATOR.1
import java.util.Comparator
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.MagicApiIntrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

internal const val PACKAGE_PATTERN: String = "[a-zA-Z_][a-zA-Z0-9_]*(\\.[a-zA-Z_][a-zA-Z0-9_]*)*"
internal const val MIXIN_ENV_PATTERN: String = "default|client|server|DEFAULT|CLIENT|SERVER"
public final val KEY_COMPARATOR: Comparator<String> = 1.INSTANCE as Comparator

@JvmSynthetic
internal inline fun <reified T : Any> T.toJsonString(): String {
   val `this_$iv`: Json = Json.Default;
   val var5: SerializersModule = Json.Default.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return `this_$iv`.encodeToString(SerializersKt.serializer(var5, null), `$this$toJsonString`);
}

private fun String.dot(start: Int): Int {
   val it: Int = StringsKt.indexOf$default(`$this$dot`, '.', start, false, 4, null);
   return if (it < 0) `$this$dot`.length() else it + 1;
}

private fun compareBy(a: String, b: String, start: Int, segment1: Int, segment2: Int): Int {
   val var10000: java.lang.String = a.substring(start, segment1);
   val var5: java.lang.Comparable = var10000;
   val var10001: java.lang.String = b.substring(start, segment2);
   return var5.compareTo(var10001);
}

@JvmSynthetic
fun `access$dot`(`$receiver`: java.lang.String, start: Int): Int {
   return dot(`$receiver`, start);
}

@JvmSynthetic
fun `access$compareBy`(a: java.lang.String, b: java.lang.String, start: Int, segment1: Int, segment2: Int): Int {
   return compareBy(a, b, start, segment1, segment2);
}
