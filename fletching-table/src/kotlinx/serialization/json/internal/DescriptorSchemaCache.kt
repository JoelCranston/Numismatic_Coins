package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor

@SourceDebugExtension(["SMAP\nSchemaCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SchemaCache.kt\nkotlinx/serialization/json/internal/DescriptorSchemaCache\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,53:1\n382#2,7:54\n1#3:61\n*S KotlinDebug\n*F\n+ 1 SchemaCache.kt\nkotlinx/serialization/json/internal/DescriptorSchemaCache\n*L\n25#1:54,7\n*E\n"])
internal class DescriptorSchemaCache {
   private final val map: MutableMap<SerialDescriptor, MutableMap<kotlinx.serialization.json.internal.DescriptorSchemaCache.Key<Any>, Any>> =
      CreateMapForCacheKt.createMapForCache(16)

   public operator fun <T : Any> set(descriptor: SerialDescriptor, key: kotlinx.serialization.json.internal.DescriptorSchemaCache.Key<T>, value: T) {
      val `$this$getOrPut$iv`: java.util.Map = this.map;
      val `value$iv`: Any = this.map.get(descriptor);
      val var10000: Any;
      if (`value$iv` == null) {
         val var10: Any = CreateMapForCacheKt.createMapForCache(2);
         `$this$getOrPut$iv`.put(descriptor, var10);
         var10000 = var10;
      } else {
         var10000 = `value$iv`;
      }

      (var10000 as java.util.Map).put(key, value);
   }

   public fun <T : Any> getOrPut(descriptor: SerialDescriptor, key: kotlinx.serialization.json.internal.DescriptorSchemaCache.Key<T>, defaultValue: () -> T): T {
      val var10000: Any = this.get(descriptor, key);
      if (var10000 != null) {
         return (T)var10000;
      } else {
         val value: Any = defaultValue.invoke();
         this.set(descriptor, key, value);
         return (T)value;
      }
   }

   public operator fun <T : Any> get(descriptor: SerialDescriptor, key: kotlinx.serialization.json.internal.DescriptorSchemaCache.Key<T>): T? {
      val var10000: java.util.Map = this.map.get(descriptor);
      var var3: Any = if (var10000 != null) var10000.get(key) else null;
      if (var3 == null) {
         var3 = null;
      }

      return (T)var3;
   }

   public class Key<T>
}
