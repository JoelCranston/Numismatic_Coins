package kotlinx.serialization.internal

import java.util.HashSet
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor

@PublishedApi
internal class HashSetSerializer<E>(eSerializer: KSerializer<Any>) : CollectionSerializer(eSerializer) {
   public open val descriptor: SerialDescriptor

   init {
      this.descriptor = new HashSetClassDesc(eSerializer.getDescriptor());
   }

   protected open fun builder(): HashSet<Any> {
      return new HashSet<>();
   }

   protected open fun HashSet<Any>.builderSize(): Int {
      return `$this$builderSize`.size();
   }

   protected open fun HashSet<Any>.toResult(): Set<Any> {
      return `$this$toResult`;
   }

   protected open fun Set<Any>.toBuilder(): HashSet<Any> {
      var var10000: HashSet = `$this$toBuilder` as? HashSet;
      if ((`$this$toBuilder` as? HashSet) == null) {
         var10000 = new HashSet<>(`$this$toBuilder`);
      }

      return var10000;
   }

   protected open fun HashSet<Any>.checkCapacity(size: Int) {
   }

   protected open fun HashSet<Any>.insert(index: Int, element: Any) {
      `$this$insert`.add(element);
   }
}
