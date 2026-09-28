package kotlinx.serialization.internal

import java.util.HashMap
import kotlin.collections.Map.Entry
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor

@PublishedApi
internal class HashMapSerializer<K, V>(kSerializer: KSerializer<Any>, vSerializer: KSerializer<Any>) : MapLikeSerializer(kSerializer, vSerializer) {
   public open val descriptor: SerialDescriptor

   init {
      this.descriptor = new HashMapClassDesc(kSerializer.getDescriptor(), vSerializer.getDescriptor());
   }

   protected open fun Map<Any, Any>.collectionSize(): Int {
      return `$this$collectionSize`.size();
   }

   protected open fun Map<Any, Any>.collectionIterator(): Iterator<Entry<Any, Any>> {
      return `$this$collectionIterator`.entrySet().iterator();
   }

   protected open fun builder(): HashMap<Any, Any> {
      return new HashMap<>();
   }

   protected open fun HashMap<Any, Any>.builderSize(): Int {
      return `$this$builderSize`.size() * 2;
   }

   protected open fun HashMap<Any, Any>.toResult(): Map<Any, Any> {
      return `$this$toResult`;
   }

   protected open fun Map<Any, Any>.toBuilder(): HashMap<Any, Any> {
      var var10000: HashMap = `$this$toBuilder` as? HashMap;
      if ((`$this$toBuilder` as? HashMap) == null) {
         var10000 = new HashMap(`$this$toBuilder`);
      }

      return var10000;
   }

   protected open fun HashMap<Any, Any>.checkCapacity(size: Int) {
   }

   protected open fun HashMap<Any, Any>.insertKeyValuePair(index: Int, key: Any, value: Any) {
      `$this$insertKeyValuePair`.put(key, value);
   }
}
