@file:SourceDebugExtension(["SMAP\nHeaders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Headers.kt\nio/ktor/http/HeadersKt\n+ 2 Headers.kt\nio/ktor/http/Headers$Companion\n*L\n1#1,108:1\n30#2:109\n*S KotlinDebug\n*F\n+ 1 Headers.kt\nio/ktor/http/HeadersKt\n*L\n94#1:109\n*E\n"])

package io.ktor.http

import kotlin.jvm.internal.SourceDebugExtension

public fun headersOf(): Headers {
   return Headers.Companion.getEmpty();
}

public fun headersOf(name: String, value: String): Headers {
   return new HeadersSingleImpl(name, CollectionsKt.listOf(value));
}

public fun headersOf(name: String, values: List<String>): Headers {
   return new HeadersSingleImpl(name, values);
}

public fun headersOf(vararg pairs: Pair<String, List<String>>): Headers {
   return new HeadersImpl(MapsKt.toMap(ArraysKt.asList(pairs)));
}

public fun headers(builder: (HeadersBuilder) -> Unit): Headers {
   val `this_$iv`: Headers.Companion = Headers.Companion;
   val var3: HeadersBuilder = new HeadersBuilder(0, 1, null);
   builder.invoke(var3);
   return var3.build();
}
