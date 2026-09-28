package net.peanuuutz.tomlkt

import java.util.Comparator
import java.util.function.UnaryOperator
import kotlin.jvm.internal.CollectionToArray
import kotlin.jvm.internal.markers.KMappedMarker
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.internal.TomlArraySerializer

@Serializable(with = TomlArraySerializer::class)
public class TomlArray internal constructor(content: List<TomlElement>, annotations: List<List<Annotation>> = CollectionsKt.emptyList()) : TomlElement(),
   java.util.List<TomlElement>,
   KMappedMarker {
   public open val content: List<TomlElement>
   public final val annotations: List<List<Annotation>>
   public open val size: Int

   init {
      this.content = content;
      this.annotations = annotations;
   }

   public override operator fun equals(other: Any?): Boolean {
      return this.getContent() == other;
   }

   public override fun hashCode(): Int {
      return this.getContent().hashCode();
   }

   public override fun toString(): String {
      return CollectionsKt.joinToString$default(this.getContent(), null, "[ ", " ]", 0, null, null, 57, null);
   }

   public override fun isEmpty(): Boolean {
      return this.content.isEmpty();
   }

   public open operator fun contains(element: TomlElement): Boolean {
      return this.content.contains(element);
   }

   public override operator fun iterator(): Iterator<TomlElement> {
      return this.content.iterator();
   }

   public override fun containsAll(elements: Collection<TomlElement>): Boolean {
      return this.content.containsAll(elements);
   }

   public open operator fun get(index: Int): TomlElement {
      return this.content.get(index);
   }

   public open fun indexOf(element: TomlElement): Int {
      return this.content.indexOf(element);
   }

   public open fun lastIndexOf(element: TomlElement): Int {
      return this.content.lastIndexOf(element);
   }

   public override fun listIterator(): ListIterator<TomlElement> {
      return this.content.listIterator();
   }

   public override fun listIterator(index: Int): ListIterator<TomlElement> {
      return this.content.listIterator(index);
   }

   public override fun subList(fromIndex: Int, toIndex: Int): List<TomlElement> {
      return this.content.subList(fromIndex, toIndex);
   }

   fun add(element: TomlElement): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun add(index: Int, element: TomlElement) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun remove(element: Any): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun addAll(elements: MutableCollection<TomlElement>): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun addAll(index: Int, elements: MutableCollection<TomlElement>): Boolean {
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

   fun set(index: Int, element: TomlElement): TomlElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun remove(index: Int): TomlElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun replaceAll(p0: UnaryOperator<TomlElement>) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun sort(p0: Comparator<? super TomlElement>) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun <T> toArray(array: Array<T>): Array<T> {
      return (T[])CollectionToArray.toArray(this as MutableCollection<*>, array);
   }

   override fun toArray(): Array<Any> {
      return CollectionToArray.toArray(this as MutableCollection<*>);
   }

   public companion object {
      public final val Empty: TomlArray

      public fun serializer(): KSerializer<TomlArray> {
         return TomlArraySerializer.INSTANCE;
      }
   }
}
