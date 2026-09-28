package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt

@PublishedApi
internal class PairSerializer<K, V>(keySerializer: KSerializer<Any>, valueSerializer: KSerializer<Any>) : KeyValueSerializer(keySerializer, valueSerializer) {
   public open val descriptor: SerialDescriptor

   protected open val key: Any
      protected open get() {
         return (K)`$this$key`.getFirst();
      }


   protected open val value: Any
      protected open get() {
         return (V)`$this$value`.getSecond();
      }


   init {
      this.descriptor = SerialDescriptorsKt.buildClassSerialDescriptor("kotlin.Pair", new SerialDescriptor[0], PairSerializer::descriptor$lambda$0);
   }

   protected open fun toResult(key: Any, value: Any): Pair<Any, Any> {
      return kotlin.TuplesKt.to((K)key, (V)value);
   }

   @JvmStatic
   fun `descriptor$lambda$0`(`$keySerializer`: KSerializer, `$valueSerializer`: KSerializer, `$this$buildClassSerialDescriptor`: ClassSerialDescriptorBuilder): Unit {
      ClassSerialDescriptorBuilder.element$default(`$this$buildClassSerialDescriptor`, "first", `$keySerializer`.getDescriptor(), null, false, 12, null);
      ClassSerialDescriptorBuilder.element$default(`$this$buildClassSerialDescriptor`, "second", `$valueSerializer`.getDescriptor(), null, false, 12, null);
      return Unit.INSTANCE;
   }
}
