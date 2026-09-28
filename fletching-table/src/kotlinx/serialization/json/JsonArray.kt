package kotlinx.serialization.json

import java.util.Comparator
import java.util.function.UnaryOperator
import kotlin.jvm.internal.CollectionToArray
import kotlin.jvm.internal.markers.KMappedMarker
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = JsonArraySerializer::class)
public class JsonArray(content: List<JsonElement>) : JsonElement(), java.util.List<JsonElement>, KMappedMarker {
   private final val content: List<JsonElement>
   public open val size: Int

   init {
      this.content = content;
   }

   public override operator fun equals(other: Any?): Boolean {
      return this.content == other;
   }

   public override fun hashCode(): Int {
      return this.content.hashCode();
   }

   public override fun toString(): String {
      return CollectionsKt.joinToString$default(this.content, ",", "[", "]", 0, null, null, 56, null);
   }

   public override fun isEmpty(): Boolean {
      return this.content.isEmpty();
   }

   public open operator fun contains(element: JsonElement): Boolean {
      return this.content.contains(element);
   }

   public override operator fun iterator(): Iterator<JsonElement> {
      return this.content.iterator();
   }

   public override fun containsAll(elements: Collection<JsonElement>): Boolean {
      return this.content.containsAll(elements);
   }

   public open operator fun get(index: Int): JsonElement {
      return this.content.get(index);
   }

   public open fun indexOf(element: JsonElement): Int {
      return this.content.indexOf(element);
   }

   public open fun lastIndexOf(element: JsonElement): Int {
      return this.content.lastIndexOf(element);
   }

   public override fun listIterator(): ListIterator<JsonElement> {
      return this.content.listIterator();
   }

   public override fun listIterator(index: Int): ListIterator<JsonElement> {
      return this.content.listIterator(index);
   }

   public override fun subList(fromIndex: Int, toIndex: Int): List<JsonElement> {
      return this.content.subList(fromIndex, toIndex);
   }

   fun add(element: JsonElement): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun add(index: Int, element: JsonElement) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun remove(element: Any): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun addAll(elements: MutableCollection<JsonElement>): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun addAll(index: Int, elements: MutableCollection<JsonElement>): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun removeAll(elements: MutableCollection<*>): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun retainAll(elements: MutableCollection<*>): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun clear() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun set(index: Int, element: JsonElement): JsonElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun remove(index: Int): JsonElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun replaceAll(p0: UnaryOperator<JsonElement>) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun sort(p0: Comparator<? super JsonElement>) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun <T> toArray(array: Array<T>): Array<T> {
      return (T[])CollectionToArray.toArray(this as MutableCollection<*>, array);
   }

   override fun toArray(): Array<Any> {
      return CollectionToArray.toArray(this as MutableCollection<*>);
   }

   public companion object {
      public fun serializer(): KSerializer<JsonArray> {
         return JsonArraySerializer.INSTANCE;
      }
   }
}
