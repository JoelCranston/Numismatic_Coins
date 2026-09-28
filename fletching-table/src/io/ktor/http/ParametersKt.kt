@file:SourceDebugExtension(["SMAP\nParameters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Parameters.kt\nio/ktor/http/ParametersKt\n+ 2 Parameters.kt\nio/ktor/http/Parameters$Companion\n*L\n1#1,136:1\n31#2:137\n31#2:138\n*S KotlinDebug\n*F\n+ 1 Parameters.kt\nio/ktor/http/ParametersKt\n*L\n91#1:137\n113#1:138\n*E\n"])

package io.ktor.http

import kotlin.jvm.internal.SourceDebugExtension

public fun ParametersBuilder(size: Int = 8): ParametersBuilder {
   return new ParametersBuilderImpl(size);
}

@JvmSynthetic
fun `ParametersBuilder$default`(var0: Int, var1: Int, var2: Any): ParametersBuilder {
   if ((var1 and 1) != 0) {
      var0 = 8;
   }

   return ParametersBuilder(var0);
}

public fun parametersOf(): Parameters {
   return Parameters.Companion.getEmpty();
}

public fun parametersOf(name: String, value: String): Parameters {
   return new ParametersSingleImpl(name, CollectionsKt.listOf(value));
}

public fun parametersOf(name: String, values: List<String>): Parameters {
   return new ParametersSingleImpl(name, values);
}

public fun parametersOf(map: Map<String, List<String>>): Parameters {
   return new ParametersImpl(map);
}

public fun parametersOf(vararg pairs: Pair<String, List<String>>): Parameters {
   return new ParametersImpl(MapsKt.toMap(ArraysKt.asList(pairs)));
}

public fun parameters(builder: (ParametersBuilder) -> Unit): Parameters {
   val `this_$iv`: Parameters.Companion = Parameters.Companion;
   val var3: ParametersBuilder = ParametersBuilder$default(0, 1, null);
   builder.invoke(var3);
   return var3.build();
}

public operator fun Parameters.plus(other: Parameters): Parameters {
   if (`$this$plus`.getCaseInsensitiveName() == other.getCaseInsensitiveName()) {
      val var10000: Parameters;
      if (`$this$plus`.isEmpty()) {
         var10000 = other;
      } else if (other.isEmpty()) {
         var10000 = `$this$plus`;
      } else {
         val `this_$iv`: Parameters.Companion = Parameters.Companion;
         val var4: ParametersBuilder = ParametersBuilder$default(0, 1, null);
         var4.appendAll(`$this$plus`);
         var4.appendAll(other);
         var10000 = var4.build();
      }

      return var10000;
   } else {
      throw new IllegalArgumentException("Cannot concatenate Parameters with case-sensitive and case-insensitive names");
   }
}
