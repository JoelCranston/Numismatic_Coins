package kotlinx.serialization.internal

import java.util.ArrayList
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor

@InternalSerializationApi
@PublishedApi
internal class ArrayListSerializer<E>(element: KSerializer<Any>) : CollectionSerializer(element) {
   public open val descriptor: SerialDescriptor

   init {
      this.descriptor = new ArrayListClassDesc(element.getDescriptor());
   }

   protected open fun builder(): ArrayList<Any> {
      return new ArrayList<>();
   }

   protected open fun ArrayList<Any>.builderSize(): Int {
      return `$this$builderSize`.size();
   }

   protected open fun ArrayList<Any>.toResult(): List<Any> {
      return `$this$toResult`;
   }

   protected open fun List<Any>.toBuilder(): ArrayList<Any> {
      var var10000: ArrayList = `$this$toBuilder` as? ArrayList;
      if ((`$this$toBuilder` as? ArrayList) == null) {
         var10000 = new ArrayList<>(`$this$toBuilder`);
      }

      return var10000;
   }

   protected open fun ArrayList<Any>.checkCapacity(size: Int) {
      `$this$checkCapacity`.ensureCapacity(size);
   }

   protected open fun ArrayList<Any>.insert(index: Int, element: Any) {
      `$this$insert`.add(index, element);
   }
}
