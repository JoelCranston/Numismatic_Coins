package net.peanuuutz.tomlkt

import java.util.Map.Entry
import java.util.function.BiFunction
import java.util.function.Function
import kotlin.jvm.internal.markers.KMappedMarker
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.internal.StringUtilsKt
import net.peanuuutz.tomlkt.internal.TomlTableSerializer

@Serializable(with = TomlTableSerializer::class)
public class TomlTable internal constructor(content: Map<String, TomlElement>, annotations: Map<String, List<Annotation>> = MapsKt.emptyMap()) : TomlElement(),
   java.util.Map<java.lang.String, TomlElement>,
   KMappedMarker {
   public open val content: Map<String, TomlElement>
   public final val annotations: Map<String, List<Annotation>>
   public open val entries: Set<kotlin.collections.Map.Entry<String, TomlElement>>
   public open val keys: Set<String>
   public open val size: Int
   public open val values: Collection<TomlElement>

   init {
      this.content = content;
      this.annotations = annotations;
   }

   public operator fun get(key: Any?): TomlElement? {
      return this.get(TomlElementKt.toTomlKey(key)) as TomlElement;
   }

   public override operator fun equals(other: Any?): Boolean {
      return this.getContent() == other;
   }

   public override fun hashCode(): Int {
      return this.getContent().hashCode();
   }

   public override fun toString(): String {
      return CollectionsKt.joinToString$default(this.getContent().entrySet(), null, "{ ", " }", 0, null, TomlTable::toString$lambda$0, 25, null);
   }

   public override fun isEmpty(): Boolean {
      return this.content.isEmpty();
   }

   public open fun containsKey(key: String): Boolean {
      return this.content.containsKey(key);
   }

   public open fun containsValue(value: TomlElement): Boolean {
      return this.content.containsValue(value);
   }

   public open operator fun get(key: String): TomlElement? {
      return this.content.get(key);
   }

   fun put(key: java.lang.String, value: TomlElement): TomlElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun remove(key: Any): TomlElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun remove(key: Any, value: Any): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun putAll(from: MutableMap<java.lang.String, TomlElement>) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun clear() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun replaceAll(p0: BiFunction<? super java.lang.String, ? super TomlElement, ? extends TomlElement>) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun putIfAbsent(p0: java.lang.String, p1: TomlElement): TomlElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun replace(p0: java.lang.String, p1: TomlElement, p2: TomlElement): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun replace(p0: java.lang.String, p1: TomlElement): TomlElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun computeIfAbsent(p0: java.lang.String, p1: Function<? super java.lang.String, ? extends TomlElement>): TomlElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun computeIfPresent(p0: java.lang.String, p1: BiFunction<? super java.lang.String, ? super TomlElement, ? extends TomlElement>): TomlElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun compute(p0: java.lang.String, p1: BiFunction<? super java.lang.String, ? super TomlElement, ? extends TomlElement>): TomlElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun merge(p0: java.lang.String, p1: TomlElement, p2: BiFunction<? super TomlElement, ? super TomlElement, ? extends TomlElement>): TomlElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @JvmStatic
   fun `toString$lambda$0`(var0: Entry): java.lang.CharSequence {
      return "${StringUtilsKt.doubleQuotedIfNotPure(StringUtilsKt.escape$default(var0.getKey() as java.lang.String, false, 1, null))} = ${var0.getValue() as TomlElement}";
   }

   public companion object {
      public final val Empty: TomlTable

      public fun serializer(): KSerializer<TomlTable> {
         return TomlTableSerializer.INSTANCE;
      }
   }
}
