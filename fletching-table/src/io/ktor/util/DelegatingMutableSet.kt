package io.ktor.util

import io.ktor.util.DelegatingMutableSet.iterator.1
import java.util.ArrayList
import kotlin.jvm.internal.CollectionToArray
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.markers.KMutableSet

@SourceDebugExtension(["SMAP\nDelegatingMutableSet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DelegatingMutableSet.kt\nio/ktor/util/DelegatingMutableSet\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,59:1\n1563#2:60\n1634#2,3:61\n1563#2:64\n1634#2,3:65\n*S KotlinDebug\n*F\n+ 1 DelegatingMutableSet.kt\nio/ktor/util/DelegatingMutableSet\n*L\n13#1:60\n13#1:61,3\n14#1:64\n14#1:65,3\n*E\n"])
internal open class DelegatingMutableSet<From, To>(delegate: MutableSet<Any>, convertTo: (Any) -> Any, convert: (Any) -> Any) : java.util.Set<To>, KMutableSet {
   private final val delegate: MutableSet<Any>
   private final val convertTo: (Any) -> Any
   private final val convert: (Any) -> Any
   public open val size: Int

   init {
      this.delegate = delegate;
      this.convertTo = convertTo;
      this.convert = convert;
      this.size = this.delegate.size();
   }

   public open fun Collection<Any>.convert(): Collection<Any> {
      val `$this$map$iv`: java.lang.Iterable = `$this$convert`;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(`$this$convert`, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add(this.convert.invoke((To)`item$iv$iv`));
      }

      return `destination$iv$iv`;
   }

   public open fun Collection<Any>.convertTo(): Collection<Any> {
      val `$this$map$iv`: java.lang.Iterable = `$this$convertTo`;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(`$this$convertTo`, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add(this.convertTo.invoke((From)`item$iv$iv`));
      }

      return `destination$iv$iv`;
   }

   public override fun add(element: Any): Boolean {
      return this.delegate.add(this.convert.invoke((To)element));
   }

   public override fun addAll(elements: Collection<Any>): Boolean {
      return this.delegate.addAll(this.convert(elements));
   }

   public override fun clear() {
      this.delegate.clear();
   }

   public override fun remove(element: Any): Boolean {
      return this.delegate.remove(this.convert.invoke((To)element));
   }

   public override fun removeAll(elements: Collection<Any>): Boolean {
      return this.delegate.removeAll(kotlin.collections.CollectionsKt.toSet(this.convert(elements)));
   }

   public override fun retainAll(elements: Collection<Any>): Boolean {
      return this.delegate.retainAll(kotlin.collections.CollectionsKt.toSet(this.convert(elements)));
   }

   public override operator fun contains(element: Any): Boolean {
      return this.delegate.contains(this.convert.invoke((To)element));
   }

   public override fun containsAll(elements: Collection<Any>): Boolean {
      return this.delegate.containsAll(this.convert(elements));
   }

   public override fun isEmpty(): Boolean {
      return this.delegate.isEmpty();
   }

   public override operator fun iterator(): MutableIterator<Any> {
      return new 1(this);
   }

   public override fun hashCode(): Int {
      return this.delegate.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (other != null && other is java.util.Set) {
         val elements: java.util.Collection = this.convertTo(this.delegate);
         return (other as java.util.Set).containsAll(elements) && elements.containsAll(other as MutableCollection<*>);
      } else {
         return false;
      }
   }

   public override fun toString(): String {
      return this.convertTo(this.delegate).toString();
   }

   override fun <T> toArray(array: Array<T>): Array<T> {
      return (T[])CollectionToArray.toArray(this as MutableCollection<*>, array);
   }

   override fun toArray(): Array<Any> {
      return CollectionToArray.toArray(this as MutableCollection<*>);
   }
}
