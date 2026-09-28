package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.PolymorphicSerializerKt
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.ChunkedDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementKt
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.modules.SerializersModule

@SourceDebugExtension(["SMAP\nStreamingJsonDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamingJsonDecoder.kt\nkotlinx/serialization/json/internal/StreamingJsonDecoder\n+ 2 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n+ 3 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/TreeJsonEncoderKt\n+ 4 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n+ 5 JsonNamesMap.kt\nkotlinx/serialization/json/internal/JsonNamesMapKt\n+ 6 StreamingJsonDecoder.kt\nkotlinx/serialization/json/internal/StreamingJsonDecoderKt\n*L\n1#1,392:1\n78#2,6:393\n84#2,9:407\n270#3,8:399\n517#4,3:416\n517#4,3:419\n133#5,18:422\n385#6,5:440\n385#6,5:445\n*S KotlinDebug\n*F\n+ 1 StreamingJsonDecoder.kt\nkotlinx/serialization/json/internal/StreamingJsonDecoder\n*L\n75#1:393,6\n75#1:407,9\n75#1:399,8\n202#1:416,3\n203#1:419,3\n215#1:422,18\n309#1:440,5\n316#1:445,5\n*E\n"])
internal open class StreamingJsonDecoder(json: Json,
      mode: WriteMode,
      lexer: AbstractJsonLexer,
      descriptor: SerialDescriptor,
      discriminatorHolder: kotlinx.serialization.json.internal.StreamingJsonDecoder.DiscriminatorHolder?
   )
   : AbstractDecoder,
   JsonDecoder,
   ChunkedDecoder {
   public final val json: Json
   private final val mode: WriteMode
   internal final val lexer: AbstractJsonLexer
   public open val serializersModule: SerializersModule
   private final var currentIndex: Int
   private final var discriminatorHolder: kotlinx.serialization.json.internal.StreamingJsonDecoder.DiscriminatorHolder?
   private final val configuration: JsonConfiguration
   private final val elementMarker: JsonElementMarker?

   init {
      this.json = json;
      this.mode = mode;
      this.lexer = lexer;
      this.serializersModule = this.json.getSerializersModule();
      this.currentIndex = -1;
      this.discriminatorHolder = discriminatorHolder;
      this.configuration = this.json.getConfiguration();
      this.elementMarker = if (this.configuration.getExplicitNulls()) null else new JsonElementMarker(descriptor);
   }

   private fun kotlinx.serialization.json.internal.StreamingJsonDecoder.DiscriminatorHolder?.trySkip(unknownKey: String): Boolean {
      if (`$this$trySkip` == null) {
         return false;
      } else if (`$this$trySkip`.discriminatorToSkip == unknownKey) {
         `$this$trySkip`.discriminatorToSkip = null;
         return true;
      } else {
         return false;
      }
   }

   public override fun decodeJsonElement(): JsonElement {
      return new JsonTreeReader(this.json.getConfiguration(), this.lexer).read();
   }

   public override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
      try {
         if (deserializer is AbstractPolymorphicSerializer && !this.json.getConfiguration().getUseArrayPolymorphism()) {
            val discriminator: java.lang.String = PolymorphicKt.classDiscriminator((deserializer as AbstractPolymorphicSerializer).getDescriptor(), this.json);
            var var25: java.lang.String = this.lexer.peekLeadingMatchingValue(discriminator, this.configuration.isLenient());
            if (var25 != null) {
               val e: java.lang.String = var25;

               var var21: DeserializationStrategy;
               try {
                  var21 = PolymorphicSerializerKt.findPolymorphicSerializer(deserializer as AbstractPolymorphicSerializer, this, e);
               } catch (var19: SerializationException) {
                  var25 = var19.getMessage();
                  val var22: java.lang.String = StringsKt.removeSuffix(StringsKt.substringBefore$default(var25, '\n', null, 2, null), ".");
                  var25 = var19.getMessage();
                  AbstractJsonLexer.fail$default(this.lexer, var22, 0, StringsKt.substringAfter(var25, '\n', ""), 2, null);
                  throw new KotlinNothingValueException();
               }

               this.discriminatorHolder = new StreamingJsonDecoder.DiscriminatorHolder(discriminator);
               return (T)var21.deserialize(this);
            } else {
               val `$this$decodeSerializableValuePolymorphic$iv`: JsonDecoder = this;
               val `deserializer$iv`: DeserializationStrategy = deserializer;
               if (deserializer is AbstractPolymorphicSerializer
                  && !`$this$decodeSerializableValuePolymorphic$iv`.getJson().getConfiguration().getUseArrayPolymorphism()) {
                  val message: java.lang.String = PolymorphicKt.classDiscriminator(
                     (deserializer as AbstractPolymorphicSerializer).getDescriptor(), `$this$decodeSerializableValuePolymorphic$iv`.getJson()
                  );
                  val hint: JsonElement = `$this$decodeSerializableValuePolymorphic$iv`.decodeJsonElement();
                  val `actualSerializer$iv`: java.lang.String = (deserializer as AbstractPolymorphicSerializer).getDescriptor().getSerialName();
                  if (hint !is JsonObject) {
                     throw JsonExceptionsKt.JsonDecodingException(
                        -1,
                        "Expected ${(JsonObject::class).getSimpleName()}, but had ${(hint.getClass()::class).getSimpleName()} as the serialized body of $`actualSerializer$iv` at element: ${this.lexer
                           .path
                           .getPath()}",
                        hint.toString()
                     );
                  }

                  var `jsonTree$iv`: JsonObject;
                  label50: {
                     `jsonTree$iv` = hint as JsonObject;
                     val var27: JsonElement = (hint as JsonObject).get((Object)message) as JsonElement;
                     if (var27 != null) {
                        val var28: JsonPrimitive = JsonElementKt.getJsonPrimitive(var27);
                        if (var28 != null) {
                           var25 = JsonElementKt.getContentOrNull(var28);
                           break label50;
                        }
                     }

                     var25 = null;
                  }

                  val var23: java.lang.String = var25;

                  var var14: DeserializationStrategy;
                  try {
                     var14 = PolymorphicSerializerKt.findPolymorphicSerializer(
                        `deserializer$iv` as AbstractPolymorphicSerializer, `$this$decodeSerializableValuePolymorphic$iv`, var23
                     );
                  } catch (var18: SerializationException) {
                     val var10001: java.lang.String = var18.getMessage();
                     throw JsonExceptionsKt.JsonDecodingException(-1, var10001, `jsonTree$iv`.toString());
                  }

                  var25 = TreeJsonDecoderKt.readPolymorphicJson(`$this$decodeSerializableValuePolymorphic$iv`.getJson(), message, `jsonTree$iv`, var14);
               } else {
                  var25 = (java.lang.String)deserializer.deserialize(`$this$decodeSerializableValuePolymorphic$iv`);
               }

               return (T)var25;
            }
         } else {
            return (T)deserializer.deserialize(this);
         }
      } catch (var20: MissingFieldException) {
         val var10000: java.lang.String = var20.getMessage();
         if (StringsKt.contains$default(var10000, "at path", false, 2, null)) {
            throw var20;
         } else {
            throw new MissingFieldException(var20.getMissingFields(), "${var20.getMessage()} at path: ${this.lexer.path.getPath()}", var20);
         }
      }
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      val newMode: WriteMode = WriteModeKt.switchMode(this.json, descriptor);
      this.lexer.path.pushDescriptor(descriptor);
      this.lexer.consumeNextToken(newMode.begin);
      this.checkLeadingComma();
      var var10000: CompositeDecoder;
      switch (StreamingJsonDecoder.WhenMappings.$EnumSwitchMapping$0[newMode.ordinal()]) {
         case 1:
         case 2:
         case 3:
            var10000 = new StreamingJsonDecoder(this.json, newMode, this.lexer, descriptor, this.discriminatorHolder);
            break;
         default:
            var10000 = if (this.mode === newMode && this.json.getConfiguration().getExplicitNulls())
               this
               else
               new StreamingJsonDecoder(this.json, newMode, this.lexer, descriptor, this.discriminatorHolder);
      }

      return var10000;
   }

   public override fun endStructure(descriptor: SerialDescriptor) {
      if (descriptor.getElementsCount() == 0 && JsonNamesMapKt.ignoreUnknownKeys(descriptor, this.json)) {
         this.skipLeftoverElements(descriptor);
      }

      if (this.lexer.tryConsumeComma() && !this.json.getConfiguration().getAllowTrailingComma()) {
         JsonExceptionsKt.invalidTrailingComma(this.lexer, "");
         throw new KotlinNothingValueException();
      } else {
         this.lexer.consumeNextToken(this.mode.end);
         this.lexer.path.popDescriptor();
      }
   }

   private fun skipLeftoverElements(descriptor: SerialDescriptor) {
      while (this.decodeElementIndex(descriptor) != -1) {
      }
   }

   public override fun decodeNotNullMark(): Boolean {
      return (this.elementMarker == null || !this.elementMarker.isUnmarkedNull$kotlinx_serialization_json())
         && !AbstractJsonLexer.tryConsumeNull$default(this.lexer, false, 1, null);
   }

   public override fun decodeNull(): Nothing? {
      return null;
   }

   private fun checkLeadingComma() {
      if (this.lexer.peekNextToken() == 4) {
         AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
         throw new KotlinNothingValueException();
      }
   }

   public override fun <T> decodeSerializableElement(descriptor: SerialDescriptor, index: Int, deserializer: DeserializationStrategy<T>, previousValue: T?): T {
      val isMapKey: Boolean = this.mode === WriteMode.MAP && (index and 1) == 0;
      if (this.mode === WriteMode.MAP && (index and 1) == 0) {
         this.lexer.path.resetCurrentMapKey();
      }

      val value: Any = super.decodeSerializableElement(descriptor, index, deserializer, previousValue);
      if (isMapKey) {
         this.lexer.path.updateCurrentMapKey(value);
      }

      return (T)value;
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      var var10000: Int;
      switch (StreamingJsonDecoder.WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()]) {
         case 2:
            var10000 = this.decodeMapIndex();
            break;
         case 3:
         default:
            var10000 = this.decodeListIndex();
            break;
         case 4:
            var10000 = this.decodeObjectIndex(descriptor);
      }

      if (this.mode != WriteMode.MAP) {
         this.lexer.path.updateDescriptorIndex(var10000);
      }

      return var10000;
   }

   private fun decodeMapIndex(): Int {
      var hasComma: Boolean = false;
      val decodingKey: Boolean = this.currentIndex % 2 != 0;
      if (this.currentIndex % 2 != 0) {
         if (this.currentIndex != -1) {
            hasComma = this.lexer.tryConsumeComma();
         }
      } else {
         this.lexer.consumeNextToken(':');
      }

      val var10000: Int;
      if (this.lexer.canConsumeValue()) {
         if (decodingKey) {
            if (this.currentIndex == -1) {
               if (hasComma) {
                  AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", this.lexer.currentPosition, null, 4, null);
                  throw new KotlinNothingValueException();
               }
            } else if (!hasComma) {
               AbstractJsonLexer.fail$default(this.lexer, "Expected comma after the key-value pair", this.lexer.currentPosition, null, 4, null);
               throw new KotlinNothingValueException();
            }
         }

         this.currentIndex++;
         var10000 = this.currentIndex;
      } else {
         if (hasComma && !this.json.getConfiguration().getAllowTrailingComma()) {
            JsonExceptionsKt.invalidTrailingComma$default(this.lexer, null, 1, null);
            throw new KotlinNothingValueException();
         }

         var10000 = -1;
      }

      return var10000;
   }

   private fun coerceInputValue(descriptor: SerialDescriptor, index: Int): Boolean {
      val `$this$tryCoerceValue$iv`: Json = this.json;
      val `isOptional$iv`: Boolean = descriptor.isElementOptional(index);
      val `elementDescriptor$iv`: SerialDescriptor = descriptor.getElementDescriptor(index);
      if (`isOptional$iv` && !`elementDescriptor$iv`.isNullable() && this.lexer.tryConsumeNull(true)) {
         return true;
      } else {
         if (`elementDescriptor$iv`.getKind() == SerialKind.ENUM.INSTANCE) {
            if (`elementDescriptor$iv`.isNullable() && this.lexer.tryConsumeNull(false)) {
               return false;
            }

            val var10000: java.lang.String = this.lexer.peekString(this.configuration.isLenient());
            if (var10000 == null) {
               return false;
            }

            if (JsonNamesMapKt.getJsonNameIndex(`elementDescriptor$iv`, `$this$tryCoerceValue$iv`, var10000) == -3
               && (`isOptional$iv` || !`$this$tryCoerceValue$iv`.getConfiguration().getExplicitNulls() && `elementDescriptor$iv`.isNullable())) {
               this.lexer.consumeString();
               return true;
            }
         }

         return false;
      }
   }

   private fun decodeObjectIndex(descriptor: SerialDescriptor): Int {
      var hasComma: Boolean = this.lexer.tryConsumeComma();

      while (this.lexer.canConsumeValue()) {
         hasComma = false;
         val key: java.lang.String = this.decodeStringKey();
         this.lexer.consumeNextToken(':');
         val index: Int = JsonNamesMapKt.getJsonNameIndex(descriptor, this.json, key);
         val var10000: Boolean;
         if (index != -3) {
            if (!this.configuration.getCoerceInputValues() || !this.coerceInputValue(descriptor, index)) {
               if (this.elementMarker != null) {
                  this.elementMarker.mark$kotlinx_serialization_json(index);
               }

               return index;
            }

            hasComma = this.lexer.tryConsumeComma();
            var10000 = false;
         } else {
            var10000 = true;
         }

         if (var10000) {
            hasComma = this.handleUnknown(descriptor, key);
         }
      }

      if (hasComma && !this.json.getConfiguration().getAllowTrailingComma()) {
         JsonExceptionsKt.invalidTrailingComma$default(this.lexer, null, 1, null);
         throw new KotlinNothingValueException();
      } else {
         return if (this.elementMarker != null) this.elementMarker.nextUnmarkedIndex$kotlinx_serialization_json() else -1;
      }
   }

   private fun handleUnknown(descriptor: SerialDescriptor, key: String): Boolean {
      if (!JsonNamesMapKt.ignoreUnknownKeys(descriptor, this.json) && !this.trySkip(this.discriminatorHolder, key)) {
         this.lexer.path.popDescriptor();
         this.lexer.failOnUnknownKey(key);
      } else {
         this.lexer.skipElement(this.configuration.isLenient());
      }

      return this.lexer.tryConsumeComma();
   }

   private fun decodeListIndex(): Int {
      val hasComma: Boolean = this.lexer.tryConsumeComma();
      val var10000: Int;
      if (this.lexer.canConsumeValue()) {
         if (this.currentIndex != -1 && !hasComma) {
            AbstractJsonLexer.fail$default(this.lexer, "Expected end of the array or comma", 0, null, 6, null);
            throw new KotlinNothingValueException();
         }

         this.currentIndex++;
         var10000 = this.currentIndex;
      } else {
         if (hasComma && !this.json.getConfiguration().getAllowTrailingComma()) {
            JsonExceptionsKt.invalidTrailingComma(this.lexer, "array");
            throw new KotlinNothingValueException();
         }

         var10000 = -1;
      }

      return var10000;
   }

   public override fun decodeBoolean(): Boolean {
      return this.lexer.consumeBooleanLenient();
   }

   public override fun decodeByte(): Byte {
      val value: Long = this.lexer.consumeNumericLiteral();
      if (value != (byte)value) {
         AbstractJsonLexer.fail$default(this.lexer, "Failed to parse byte for input '$value'", 0, null, 6, null);
         throw new KotlinNothingValueException();
      } else {
         return (byte)value;
      }
   }

   public override fun decodeShort(): Short {
      val value: Long = this.lexer.consumeNumericLiteral();
      if (value != (short)value) {
         AbstractJsonLexer.fail$default(this.lexer, "Failed to parse short for input '$value'", 0, null, 6, null);
         throw new KotlinNothingValueException();
      } else {
         return (short)value;
      }
   }

   public override fun decodeInt(): Int {
      val value: Long = this.lexer.consumeNumericLiteral();
      if (value != (int)value) {
         AbstractJsonLexer.fail$default(this.lexer, "Failed to parse int for input '$value'", 0, null, 6, null);
         throw new KotlinNothingValueException();
      } else {
         return (int)value;
      }
   }

   public override fun decodeLong(): Long {
      return this.lexer.consumeNumericLiteral();
   }

   public override fun decodeFloat(): Float {
      val specialFp: AbstractJsonLexer = this.lexer;
      val `input$iv`: java.lang.String = this.lexer.consumeStringLenient();

      var var10000: Float;
      try {
         var10000 = java.lang.Float.parseFloat(`input$iv`);
      } catch (var8: IllegalArgumentException) {
         AbstractJsonLexer.fail$default(specialFp, "Failed to parse type 'float' for input '$`input$iv`'", 0, null, 6, null);
         throw new KotlinNothingValueException();
      }

      if (!this.json.getConfiguration().getAllowSpecialFloatingPointValues() && !(Math.abs(var10000) <= java.lang.Float.MAX_VALUE)) {
         JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, var10000);
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }

   public override fun decodeDouble(): Double {
      val specialFp: AbstractJsonLexer = this.lexer;
      val `input$iv`: java.lang.String = this.lexer.consumeStringLenient();

      var var10000: Double;
      try {
         var10000 = java.lang.Double.parseDouble(`input$iv`);
      } catch (var9: IllegalArgumentException) {
         AbstractJsonLexer.fail$default(specialFp, "Failed to parse type 'double' for input '$`input$iv`'", 0, null, 6, null);
         throw new KotlinNothingValueException();
      }

      if (!this.json.getConfiguration().getAllowSpecialFloatingPointValues() && !(Math.abs(var10000) <= java.lang.Double.MAX_VALUE)) {
         JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, var10000);
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }

   public override fun decodeChar(): Char {
      val string: java.lang.String = this.lexer.consumeStringLenient();
      if (string.length() != 1) {
         AbstractJsonLexer.fail$default(this.lexer, "Expected single char, but got '$string'", 0, null, 6, null);
         throw new KotlinNothingValueException();
      } else {
         return string.charAt(0);
      }
   }

   private fun decodeStringKey(): String {
      return if (this.configuration.isLenient()) this.lexer.consumeStringLenientNotNull() else this.lexer.consumeKeyString();
   }

   public override fun decodeString(): String {
      return if (this.configuration.isLenient()) this.lexer.consumeStringLenientNotNull() else this.lexer.consumeString();
   }

   public override fun decodeStringChunked(consumeChunk: (String) -> Unit) {
      this.lexer.consumeStringChunked(this.configuration.isLenient(), consumeChunk);
   }

   public override fun decodeInline(descriptor: SerialDescriptor): Decoder {
      return if (StreamingJsonEncoderKt.isUnsignedNumber(descriptor))
         new JsonDecoderForUnsignedTypes(this.lexer, this.json)
         else
         super.decodeInline(descriptor);
   }

   public override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
      return JsonNamesMapKt.getJsonNameIndexOrThrow(enumDescriptor, this.json, this.decodeString(), " at path ${this.lexer.path.getPath()}");
   }

   internal class DiscriminatorHolder(discriminatorToSkip: String?) {
      public final var discriminatorToSkip: String?
         private set

      init {
         this.discriminatorToSkip = discriminatorToSkip;
      }
   }
}
