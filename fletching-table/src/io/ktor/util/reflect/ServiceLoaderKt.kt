@file:SourceDebugExtension(["SMAP\nServiceLoader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServiceLoader.kt\nio/ktor/util/reflect/ServiceLoaderKt\n*L\n1#1,48:1\n23#1,2:49\n22#1,4:51\n23#1,2:55\n22#1,4:57\n*S KotlinDebug\n*F\n+ 1 ServiceLoader.kt\nio/ktor/util/reflect/ServiceLoaderKt\n*L\n36#1:49,2\n36#1:51,4\n47#1:55,2\n47#1:57,4\n*E\n"])

package io.ktor.util.reflect

import io.ktor.utils.io.InternalAPI
import java.util.ServiceLoader
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension

@InternalAPI
@JvmSynthetic
public inline fun <reified T : Any> loadServicesAsSequence(): Sequence<Any> {
   Intrinsics.reifiedOperationMarker(4, "T");
   val var10000: Class = Object::class.java;
   Intrinsics.reifiedOperationMarker(4, "T");
   val var1: java.util.Iterator = ServiceLoader.load(var10000, Object::class.java.getClassLoader()).iterator();
   return SequencesKt.asSequence(var1);
}

@InternalAPI
@JvmSynthetic
public inline fun <reified T : Any> loadServices(): List<Any> {
   Intrinsics.reifiedOperationMarker(4, "T");
   val var10000: Class = Object::class.java;
   Intrinsics.reifiedOperationMarker(4, "T");
   val var2: java.util.Iterator = ServiceLoader.load(var10000, Object::class.java.getClassLoader()).iterator();
   return SequencesKt.toList(SequencesKt.asSequence(var2));
}

@InternalAPI
@JvmSynthetic
public inline fun <reified T : Any> loadServiceOrNull(): Any? {
   Intrinsics.reifiedOperationMarker(4, "T");
   val var10000: Class = Object::class.java;
   Intrinsics.reifiedOperationMarker(4, "T");
   val var2: java.util.Iterator = ServiceLoader.load(var10000, Object::class.java.getClassLoader()).iterator();
   return (T)SequencesKt.firstOrNull(SequencesKt.asSequence(var2));
}
