package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.internal.JsonInternalDependenciesKt
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementKt
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonSchemaCacheKt

@SourceDebugExtension(["SMAP\nTreeJsonDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TreeJsonDecoder.kt\nkotlinx/serialization/json/internal/JsonTreeDecoder\n+ 2 JsonNamesMap.kt\nkotlinx/serialization/json/internal/JsonNamesMapKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 TreeJsonDecoder.kt\nkotlinx/serialization/json/internal/AbstractJsonTreeDecoder\n+ 5 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/TreeJsonEncoderKt\n*L\n1#1,348:1\n133#2,18:349\n1#3:367\n73#4:368\n270#5,8:369\n*S KotlinDebug\n*F\n+ 1 TreeJsonDecoder.kt\nkotlinx/serialization/json/internal/JsonTreeDecoder\n*L\n212#1:349,18\n271#1:368\n271#1:369,8\n*E\n"])
private open class JsonTreeDecoder(json: Json, value: JsonObject, polymorphicDiscriminator: String? = null, polyDescriptor: SerialDescriptor? = null) : AbstractJsonTreeDecoder(
      json, value, polymorphicDiscriminator
   ) {
   public open val value: JsonObject
   private final val polyDescriptor: SerialDescriptor?
   private final var position: Int
   private final var forceNull: Boolean

   init {
      this.value = value;
      this.polyDescriptor = polyDescriptor;
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      while (this.position < descriptor.getElementsCount()) {
         val name: java.lang.String = this.getTag(descriptor, this.position++);
         val var15: Int = this.position - 1;
         this.forceNull = false;
         if (this.getValue().containsKey(name) || this.setForceNull(descriptor, var15)) {
            if (!this.configuration.getCoerceInputValues()) {
               return var15;
            }

            var var21: Boolean;
            val `$this$tryCoerceValue$iv`: Json = this.getJson();
            val `isOptional$iv`: Boolean = descriptor.isElementOptional(var15);
            val `elementDescriptor$iv`: SerialDescriptor = descriptor.getElementDescriptor(var15);
            label75:
            if (`isOptional$iv` && !`elementDescriptor$iv`.isNullable() && this.currentElementOrNull(name) is JsonNull) {
               var21 = true;
               break label75;
            } else {
               if (`elementDescriptor$iv`.getKind() == SerialKind.ENUM.INSTANCE) {
                  if (`elementDescriptor$iv`.isNullable() && this.currentElementOrNull(name) is JsonNull) {
                     var21 = false;
                     break label75;
                  }

                  val var20: JsonElement = this.currentElementOrNull(name);
                  val var10000: java.lang.String = if ((var20 as? JsonPrimitive) != null) JsonElementKt.getContentOrNull(var20 as? JsonPrimitive) else null;
                  if (var10000 == null) {
                     var21 = false;
                     break label75;
                  }

                  if (JsonNamesMapKt.getJsonNameIndex(`elementDescriptor$iv`, `$this$tryCoerceValue$iv`, var10000) == -3
                     && (`isOptional$iv` || !`$this$tryCoerceValue$iv`.getConfiguration().getExplicitNulls() && `elementDescriptor$iv`.isNullable())) {
                     if (this.setForceNull(descriptor, var15)) {
                        return var15;
                     }

                     var21 = true;
                     break label75;
                  }
               }

               var21 = false;
            }

            if (!var21) {
               return var15;
            }
         }
      }

      return -1;
   }

   private fun setForceNull(descriptor: SerialDescriptor, index: Int): Boolean {
      this.forceNull = !this.getJson().getConfiguration().getExplicitNulls()
         && !descriptor.isElementOptional(index)
         && descriptor.getElementDescriptor(index).isNullable();
      return this.forceNull;
   }

   public override fun decodeNotNullMark(): Boolean {
      return !this.forceNull && super.decodeNotNullMark();
   }

   protected override fun elementName(descriptor: SerialDescriptor, index: Int): String {
      val strategy: JsonNamingStrategy = JsonNamesMapKt.namingStrategy(descriptor, this.getJson());
      val baseName: java.lang.String = descriptor.getElementName(index);
      if (strategy == null) {
         if (!this.configuration.getUseAlternativeNames()) {
            return baseName;
         }

         if (this.getValue().keySet().contains(baseName)) {
            return baseName;
         }
      }

      val deserializationNamesMap: java.util.Map = JsonNamesMapKt.deserializationNamesMap(this.getJson(), descriptor);
      val var8: java.util.Iterator = this.getValue().keySet().iterator();

      var var16: Any;
      while (true) {
         if (!var8.hasNext()) {
            var16 = null;
            break;
         }

         var var9: Any;
         label45: {
            var9 = var8.next();
            var16 = deserializationNamesMap.get(var9 as java.lang.String) as Int;
            if (var16 != null) {
               if (var16 == index) {
                  var15 = true;
                  break label45;
               }
            }

            var15 = false;
         }

         if (var15) {
            var16 = (java.lang.String)var9;
            break;
         }
      }

      var fallbackName: java.lang.String = var16;
      if (var16 != null) {
         return fallbackName;
      } else {
         fallbackName = if (strategy != null) strategy.serialNameForJson(descriptor, index, baseName) else null;
         var16 = fallbackName;
         if (fallbackName == null) {
            var16 = baseName;
         }

         return var16;
      }
   }

   protected override fun currentElement(tag: String): JsonElement {
      return MapsKt.getValue(this.getValue(), tag);
   }

   public fun currentElementOrNull(tag: String): JsonElement? {
      return this.getValue().get((Object)tag) as JsonElement;
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      if (descriptor === this.polyDescriptor) {
         val var10000: JsonTreeDecoder = new JsonTreeDecoder;
         val var10002: Json = this.getJson();
         val `this_$iv`: AbstractJsonTreeDecoder = this;
         val `value$iv`: JsonElement = this.currentObject();
         val `serialName$iv$iv`: java.lang.String = this.polyDescriptor.getSerialName();
         if (`value$iv` !is JsonObject) {
            throw JsonExceptionsKt.JsonDecodingException(
               -1,
               "Expected ${(JsonObject::class).getSimpleName()}, but had ${(`value$iv`.getClass()::class).getSimpleName()} as the serialized body of $`serialName$iv$iv` at element: ${AbstractJsonTreeDecoder.access$renderTagStack(
                  `this_$iv`
               )}",
               `value$iv`.toString()
            );
         } else {
            var10000./* $VF: Unable to resugar constructor */<init>(var10002, `value$iv` as JsonObject, this.getPolymorphicDiscriminator(), this.polyDescriptor);
            return var10000;
         }
      } else {
         return super.beginStructure(descriptor);
      }
   }

   public override fun endStructure(descriptor: SerialDescriptor) {
      if (!JsonNamesMapKt.ignoreUnknownKeys(descriptor, this.getJson()) && descriptor.getKind() !is PolymorphicKind) {
         val strategy: JsonNamingStrategy = JsonNamesMapKt.namingStrategy(descriptor, this.getJson());
         var var10000: java.util.Set;
         if (strategy == null && !this.configuration.getUseAlternativeNames()) {
            var10000 = JsonInternalDependenciesKt.jsonCachedSerialNames(descriptor);
         } else if (strategy != null) {
            var10000 = JsonNamesMapKt.deserializationNamesMap(this.getJson(), descriptor).keySet();
         } else {
            var10000 = JsonInternalDependenciesKt.jsonCachedSerialNames(descriptor);
            val var10001: java.util.Map = JsonSchemaCacheKt.getSchemaCache(this.getJson()).get(descriptor, JsonNamesMapKt.getJsonDeserializationNamesKey());
            var var7: java.util.Set = if (var10001 != null) var10001.keySet() else null;
            if (var7 == null) {
               var7 = SetsKt.emptySet();
            }

            var10000 = SetsKt.plus(var10000, var7);
         }

         val names: java.util.Set = var10000;

         for (java.lang.String key : this.getValue().keySet()) {
            if (!names.contains(key) && !(key == this.getPolymorphicDiscriminator())) {
               throw JsonExceptionsKt.JsonDecodingException(
                  -1,
                  "Encountered an unknown key '$key' at element: ${this.renderTagStack()}\nUse 'ignoreUnknownKeys = true' in 'Json {}' builder or '@JsonIgnoreUnknownKeys' annotation to ignore unknown keys.\nJSON input: ${JsonExceptionsKt.minify$default(
                     this.getValue().toString(), 0, 1, null
                  )}"
               );
            }
         }
      }
   }
}
