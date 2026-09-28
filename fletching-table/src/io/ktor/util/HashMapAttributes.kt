package io.ktor.util

import java.util.HashMap
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nAttributesJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AttributesJvm.kt\nio/ktor/util/HashMapAttributes\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,70:1\n1#2:71\n*E\n"])
private class HashMapAttributes : AttributesJvmBase {
   protected open val map: MutableMap<AttributeKey<*>, Any?> = (new HashMap()) as java.util.Map

   public override fun <T : Any> computeIfAbsent(key: AttributeKey<Any>, block: () -> Any): Any {
      var var10000: Any = this.getMap().get(key);
      if (var10000 != null) {
         return (T)var10000;
      } else {
         val result: Any = block.invoke();
         var10000 = this.getMap().put(key, result);
         if (var10000 == null) {
            var10000 = result;
         }

         return (T)var10000;
      }
   }
}
