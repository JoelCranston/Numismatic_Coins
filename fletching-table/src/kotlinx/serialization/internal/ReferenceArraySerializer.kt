package kotlinx.serialization.internal

import java.util.ArrayList
import kotlin.jvm.internal.ArrayIteratorKt
import kotlin.reflect.KClass
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor

@PublishedApi
internal class ReferenceArraySerializer<ElementKlass, Element extends ElementKlass>(kClass: KClass<Any>, eSerializer: KSerializer<Any>) : CollectionLikeSerializer(
      eSerializer
   ) {
   private final val kClass: KClass<Any>
   public open val descriptor: SerialDescriptor

   init {
      this.kClass = kClass;
      this.descriptor = new ArrayClassDesc(eSerializer.getDescriptor());
   }

   protected open fun Array<Any>.collectionSize(): Int {
      return `$this$collectionSize`.length;
   }

   protected open fun Array<Any>.collectionIterator(): Iterator<Any> {
      return ArrayIteratorKt.iterator((Element[])`$this$collectionIterator`);
   }

   protected open fun builder(): ArrayList<Any> {
      return new ArrayList<>();
   }

   protected open fun ArrayList<Any>.builderSize(): Int {
      return `$this$builderSize`.size();
   }

   protected open fun ArrayList<Any>.toResult(): Array<Any> {
      return (Element[])PlatformKt.toNativeArrayImpl(`$this$toResult`, this.kClass);
   }

   protected open fun Array<Any>.toBuilder(): ArrayList<Any> {
      return new ArrayList<>(ArraysKt.asList((Element[])`$this$toBuilder`));
   }

   protected open fun ArrayList<Any>.checkCapacity(size: Int) {
      `$this$checkCapacity`.ensureCapacity(size);
   }

   protected open fun ArrayList<Any>.insert(index: Int, element: Any) {
      `$this$insert`.add(index, element);
   }
}
