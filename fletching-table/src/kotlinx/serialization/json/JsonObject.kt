package kotlinx.serialization.json

import java.util.Map.Entry
import java.util.function.BiFunction
import java.util.function.Function
import kotlin.jvm.internal.markers.KMappedMarker
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.internal.StringOpsKt

@Serializable(with = JsonObjectSerializer::class)
public class JsonObject(content: Map<String, JsonElement>) : JsonElement(), java.util.Map<java.lang.String, JsonElement>, KMappedMarker {
   private final val content: Map<String, JsonElement>
   public open val entries: Set<kotlin.collections.Map.Entry<String, JsonElement>>
   public open val keys: Set<String>
   public open val size: Int
   public open val values: Collection<JsonElement>

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
      return CollectionsKt.joinToString$default(this.content.entrySet(), ",", "{", "}", 0, null, JsonObject::toString$lambda$1, 24, null);
   }

   public override fun isEmpty(): Boolean {
      return this.content.isEmpty();
   }

   public open fun containsKey(key: String): Boolean {
      return this.content.containsKey(key);
   }

   public open fun containsValue(value: JsonElement): Boolean {
      return this.content.containsValue(value);
   }

   public open operator fun get(key: String): JsonElement? {
      return this.content.get(key);
   }

   fun put(key: java.lang.String, value: JsonElement): JsonElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun remove(key: Any): JsonElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun remove(key: Any, value: Any): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun putAll(from: MutableMap<java.lang.String, JsonElement>) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun clear() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun replaceAll(p0: BiFunction<? super java.lang.String, ? super JsonElement, ? extends JsonElement>) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun putIfAbsent(p0: java.lang.String, p1: JsonElement): JsonElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun replace(p0: java.lang.String, p1: JsonElement, p2: JsonElement): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun replace(p0: java.lang.String, p1: JsonElement): JsonElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun computeIfAbsent(p0: java.lang.String, p1: Function<? super java.lang.String, ? extends JsonElement>): JsonElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun computeIfPresent(p0: java.lang.String, p1: BiFunction<? super java.lang.String, ? super JsonElement, ? extends JsonElement>): JsonElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun compute(p0: java.lang.String, p1: BiFunction<? super java.lang.String, ? super JsonElement, ? extends JsonElement>): JsonElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   fun merge(p0: java.lang.String, p1: JsonElement, p2: BiFunction<? super JsonElement, ? super JsonElement, ? extends JsonElement>): JsonElement {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @JvmStatic
   fun `toString$lambda$1`(var0: Entry): java.lang.CharSequence {
      val k: java.lang.String = var0.getKey() as java.lang.String;
      val v: JsonElement = var0.getValue() as JsonElement;
      val var3: StringBuilder = new StringBuilder();
      StringOpsKt.printQuoted(var3, k);
      var3.append(':');
      var3.append(v);
      return var3.toString();
   }

   public companion object {
      public fun serializer(): KSerializer<JsonObject> {
         return JsonObjectSerializer.INSTANCE;
      }
   }
}
