package kotlinx.serialization.internal

import kotlin.collections.Map.Entry
import kotlin.jvm.internal.markers.KMappedMarker
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.descriptors.StructureKind

@PublishedApi
internal class MapEntrySerializer<K, V>(keySerializer: KSerializer<Any>, valueSerializer: KSerializer<Any>) : KeyValueSerializer(keySerializer, valueSerializer) {
   public open val descriptor: SerialDescriptor

   protected open val key: Any
      protected open get() {
         return (K)`$this$key`.getKey();
      }


   protected open val value: Any
      protected open get() {
         return (V)`$this$value`.getValue();
      }


   init {
      this.descriptor = SerialDescriptorsKt.buildSerialDescriptor(
         "kotlin.collections.Map.Entry", StructureKind.MAP.INSTANCE, new SerialDescriptor[0], MapEntrySerializer::descriptor$lambda$0
      );
   }

   protected open fun toResult(key: Any, value: Any): Entry<Any, Any> {
      return new MapEntrySerializer.MapEntry<>((K)key, (V)value);
   }

   @JvmStatic
   fun `descriptor$lambda$0`(`$keySerializer`: KSerializer, `$valueSerializer`: KSerializer, `$this$buildSerialDescriptor`: ClassSerialDescriptorBuilder): Unit {
      ClassSerialDescriptorBuilder.element$default(`$this$buildSerialDescriptor`, "key", `$keySerializer`.getDescriptor(), null, false, 12, null);
      ClassSerialDescriptorBuilder.element$default(`$this$buildSerialDescriptor`, "value", `$valueSerializer`.getDescriptor(), null, false, 12, null);
      return Unit.INSTANCE;
   }

   private data class MapEntry<K, V>(key: Any, value: Any) : java.util.Map.Entry<K, V>, KMappedMarker {
      public open val key: Any
      public open val value: Any

      init {
         this.key = (K)key;
         this.value = (V)value;
      }

      public operator fun component1(): Any {
         return this.key;
      }

      public operator fun component2(): Any {
         return this.value;
      }

      public fun copy(key: Any = this.key, value: Any = this.value): kotlinx.serialization.internal.MapEntrySerializer.MapEntry<Any, Any> {
         return new MapEntrySerializer.MapEntry<>((K)key, (V)value);
      }

      public override fun toString(): String {
         return "MapEntry(key=${this.key}, value=${this.value})";
      }

      public override fun hashCode(): Int {
         return (if (this.key == null) 0 else this.key.hashCode()) * 31 + (if (this.value == null) 0 else this.value.hashCode());
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is MapEntrySerializer.MapEntry) {
            return false;
         } else {
            val var2: MapEntrySerializer.MapEntry = other as MapEntrySerializer.MapEntry;
            if (!(this.key == (other as MapEntrySerializer.MapEntry).key)) {
               return false;
            } else {
               return this.value == var2.value;
            }
         }
      }

      override fun setValue(newValue: V): V {
         throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
   }
}
