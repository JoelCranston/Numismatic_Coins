@file:SourceDebugExtension(["SMAP\nAttributes.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Attributes.kt\nio/ktor/util/AttributesKt\n+ 2 Type.kt\nio/ktor/util/reflect/TypeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,156:1\n69#2:157\n84#2,8:158\n1869#3,2:166\n*S KotlinDebug\n*F\n+ 1 Attributes.kt\nio/ktor/util/AttributesKt\n*L\n21#1:157\n21#1:158,8\n151#1:166,2\n*E\n"])

package io.ktor.util

import io.ktor.util.reflect.TypeInfo
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType

@JvmSynthetic
public inline fun <reified T : Any> AttributeKey(name: String): AttributeKey<Any> {
   Intrinsics.reifiedOperationMarker(4, "T");

   var var5: KType;
   try {
      Intrinsics.reifiedOperationMarker(6, "T");
      var5 = null;
   } catch (var12: java.lang.Throwable) {
      var5 = null as KType;
   }

   return new AttributeKey(name, new TypeInfo(Any::class, var5));
}

public fun Attributes.putAll(other: Attributes) {
   val `$this$forEach$iv`: java.lang.Iterable;
   for (Object element$iv : $this$forEach$iv) {
      val it: AttributeKey = `element$iv` as AttributeKey;
      `$this$putAll`.put(it, other.get(it));
   }
}

/** @deprecated */
@Deprecated(message = "Please use `AttributeKey` class instead", replaceWith = @ReplaceWith(expression = "AttributeKey", imports = []), level = DeprecationLevel.ERROR)
@JvmSynthetic
fun `EquatableAttributeKey$annotations`() {
}
