package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.util.AttributeKey
import io.ktor.util.converters.DataConversion.Configuration
import io.ktor.util.reflect.TypeInfo
import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType

@SourceDebugExtension(["SMAP\nDataConversion.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DataConversion.kt\nio/ktor/client/plugins/DataConversion\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,28:1\n21#2:29\n69#3:30\n84#3,8:31\n*S KotlinDebug\n*F\n+ 1 DataConversion.kt\nio/ktor/client/plugins/DataConversion\n*L\n17#1:29\n17#1:30\n17#1:31,8\n*E\n"])
public object DataConversion : HttpClientPlugin<io.ktor.util.converters.DataConversion.Configuration, io.ktor.util.converters.DataConversion> {
   public open val key: AttributeKey<io.ktor.util.converters.DataConversion>

   public open fun prepare(block: (Configuration) -> Unit): io.ktor.util.converters.DataConversion {
      val var3: io.ktor.util.converters.DataConversion.Configuration = new io.ktor.util.converters.DataConversion.Configuration();
      block.invoke(var3);
      return new io.ktor.util.converters.DataConversion(var3);
   }

   public open fun install(plugin: io.ktor.util.converters.DataConversion, scope: HttpClient) {
   }

   @JvmStatic
   fun {
      var var6: KType;
      try {
         var6 = Reflection.typeOf(io.ktor.util.converters.DataConversion.class);
      } catch (var12: java.lang.Throwable) {
         var6 = null;
      }

      key = new AttributeKey<>("DataConversion", new TypeInfo(io.ktor.util.converters.DataConversion::class, var6));
   }
}
