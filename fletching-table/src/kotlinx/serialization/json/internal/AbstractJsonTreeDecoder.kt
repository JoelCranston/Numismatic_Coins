package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.PolymorphicSerializerKt
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import kotlinx.serialization.internal.NamedValueDecoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementKt
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.modules.SerializersModule

@SourceDebugExtension(["SMAP\nTreeJsonDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TreeJsonDecoder.kt\nkotlinx/serialization/json/internal/AbstractJsonTreeDecoder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n+ 4 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/TreeJsonEncoderKt\n+ 5 WriteMode.kt\nkotlinx/serialization/json/internal/WriteModeKt\n*L\n1#1,348:1\n73#1:373\n73#1:387\n73#1:398\n73#1:408\n74#1:433\n74#1:442\n84#1:451\n74#1:452\n87#1:461\n74#1:462\n88#1,5:471\n87#1:476\n74#1:477\n88#1,5:486\n87#1:491\n74#1:492\n88#1,5:501\n87#1:506\n74#1:507\n88#1,5:516\n87#1:521\n74#1:522\n88#1,5:531\n87#1:536\n74#1:537\n88#1,5:546\n87#1:551\n74#1:552\n88#1,5:561\n87#1:566\n74#1:567\n88#1,5:576\n74#1:581\n84#1:590\n74#1:591\n1#2:349\n78#3,6:350\n84#3,9:364\n270#4,8:356\n270#4,8:374\n270#4,8:388\n270#4,8:399\n270#4,8:409\n270#4,8:417\n270#4,8:425\n270#4,8:434\n270#4,8:443\n270#4,8:453\n270#4,8:463\n270#4,8:478\n270#4,8:493\n270#4,8:508\n270#4,8:523\n270#4,8:538\n270#4,8:553\n270#4,8:568\n270#4,8:582\n270#4,8:592\n36#5,5:382\n41#5,2:396\n44#5:407\n*S KotlinDebug\n*F\n+ 1 TreeJsonDecoder.kt\nkotlinx/serialization/json/internal/AbstractJsonTreeDecoder\n*L\n63#1:373\n66#1:387\n67#1:398\n69#1:408\n84#1:433\n87#1:442\n104#1:451\n104#1:452\n111#1:461\n111#1:462\n111#1:471,5\n113#1:476\n113#1:477\n113#1:486,5\n119#1:491\n119#1:492\n119#1:501,5\n125#1:506\n125#1:507\n125#1:516,5\n131#1:521\n131#1:522\n131#1:531,5\n134#1:536\n134#1:537\n134#1:546,5\n141#1:551\n141#1:552\n141#1:561,5\n147#1:566\n147#1:567\n147#1:576,5\n150#1:581\n163#1:590\n163#1:591\n55#1:350,6\n55#1:364,9\n55#1:356,8\n63#1:374,8\n66#1:388,8\n67#1:399,8\n69#1:409,8\n73#1:417,8\n74#1:425,8\n84#1:434,8\n87#1:443,8\n104#1:453,8\n111#1:463,8\n113#1:478,8\n119#1:493,8\n125#1:508,8\n131#1:523,8\n134#1:538,8\n141#1:553,8\n147#1:568,8\n150#1:582,8\n163#1:592,8\n64#1:382,5\n64#1:396,2\n64#1:407\n*E\n"])
private sealed class AbstractJsonTreeDecoder protected constructor(json: Json, value: JsonElement, polymorphicDiscriminator: String? = null)
   : NamedValueDecoder,
   JsonDecoder {
   public open val json: Json
   public open val value: JsonElement
   protected final val polymorphicDiscriminator: String?

   public open val serializersModule: SerializersModule
      public open get() {
         return this.getJson().getSerializersModule();
      }


   protected final val configuration: JsonConfiguration

   init {
      this.json = json;
      this.value = value;
      this.polymorphicDiscriminator = polymorphicDiscriminator;
      this.configuration = this.getJson().getConfiguration();
   }

   protected fun currentObject(): JsonElement {
      val var10000: java.lang.String = this.getCurrentTagOrNull();
      if (var10000 != null) {
         val var3: JsonElement = this.currentElement(var10000);
         if (var3 != null) {
            return var3;
         }
      }

      return this.getValue();
   }

   public fun renderTagStack(currentTag: String): String {
      return "${this.renderTagStack()}.$currentTag";
   }

   public override fun decodeJsonElement(): JsonElement {
      return this.currentObject();
   }

   public override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
      val `$this$decodeSerializableValuePolymorphic$iv`: JsonDecoder = this;
      val `deserializer$iv`: DeserializationStrategy = deserializer;
      var var10000: Any;
      if (deserializer is AbstractPolymorphicSerializer
         && !`$this$decodeSerializableValuePolymorphic$iv`.getJson().getConfiguration().getUseArrayPolymorphism()) {
         val `discriminator$iv`: java.lang.String = PolymorphicKt.classDiscriminator(
            (deserializer as AbstractPolymorphicSerializer).getDescriptor(), `$this$decodeSerializableValuePolymorphic$iv`.getJson()
         );
         val `type$iv`: JsonElement = `$this$decodeSerializableValuePolymorphic$iv`.decodeJsonElement();
         val `actualSerializer$iv`: java.lang.String = (deserializer as AbstractPolymorphicSerializer).getDescriptor().getSerialName();
         if (`type$iv` !is JsonObject) {
            throw JsonExceptionsKt.JsonDecodingException(
               -1,
               "Expected ${(JsonObject::class).getSimpleName()}, but had ${(`type$iv`.getClass()::class).getSimpleName()} as the serialized body of $`actualSerializer$iv` at element: ${this.renderTagStack()}",
               `type$iv`.toString()
            );
         }

         var `jsonTree$iv`: JsonObject;
         label28: {
            `jsonTree$iv` = `type$iv` as JsonObject;
            var10000 = (`type$iv` as JsonObject).get((Object)`discriminator$iv`) as JsonElement;
            if (var10000 != null) {
               var10000 = JsonElementKt.getJsonPrimitive((JsonElement)var10000);
               if (var10000 != null) {
                  var10000 = JsonElementKt.getContentOrNull((JsonPrimitive)var10000);
                  break label28;
               }
            }

            var10000 = null;
         }

         val var16: java.lang.String = (java.lang.String)var10000;

         var var11: DeserializationStrategy;
         try {
            var11 = PolymorphicSerializerKt.findPolymorphicSerializer(
               `deserializer$iv` as AbstractPolymorphicSerializer, `$this$decodeSerializableValuePolymorphic$iv`, var16
            );
         } catch (var15: SerializationException) {
            val var10001: java.lang.String = var15.getMessage();
            throw JsonExceptionsKt.JsonDecodingException(-1, var10001, `jsonTree$iv`.toString());
         }

         var10000 = TreeJsonDecoderKt.readPolymorphicJson(`$this$decodeSerializableValuePolymorphic$iv`.getJson(), `discriminator$iv`, `jsonTree$iv`, var11);
      } else {
         var10000 = deserializer.deserialize(`$this$decodeSerializableValuePolymorphic$iv`);
      }

      return (T)var10000;
   }

   protected override fun composeName(parentName: String, childName: String): String {
      return childName;
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      val currentObject: JsonElement = this.currentObject();
      val var3: SerialKind = descriptor.getKind();
      var var39: CompositeDecoder;
      if (var3 == StructureKind.LIST.INSTANCE || var3 is PolymorphicKind) {
         var39 = new JsonTreeListDecoder;
         val var44: Json = this.getJson();
         val var25: java.lang.String = descriptor.getSerialName();
         if (currentObject !is JsonArray) {
            throw JsonExceptionsKt.JsonDecodingException(
               -1,
               "Expected ${(JsonArray::class).getSimpleName()}, but had ${(currentObject.getClass()::class).getSimpleName()} as the serialized body of $var25 at element: ${access$renderTagStack(
                  this
               )}",
               currentObject.toString()
            );
         }

         var39./* $VF: Unable to resugar constructor */<init>(var44, currentObject as JsonArray);
         var39 = var39;
      } else if (var3 == StructureKind.MAP.INSTANCE) {
         val `this_$iv`: Json = this.getJson();
         val `$i$f$cast`: SerialDescriptor = WriteModeKt.carrierDescriptor(descriptor.getElementDescriptor(0), `this_$iv`.getSerializersModule());
         val `value$iv$iv`: SerialKind = `$i$f$cast`.getKind();
         if (`value$iv$iv` !is PrimitiveKind && !(`value$iv$iv` == SerialKind.ENUM.INSTANCE)) {
            if (!`this_$iv`.getConfiguration().getAllowStructuredMapKeys()) {
               throw JsonExceptionsKt.InvalidKeyKindException(`$i$f$cast`);
            }

            var39 = new JsonTreeListDecoder;
            val var42: Json = this.getJson();
            val var33: java.lang.String = descriptor.getSerialName();
            if (currentObject !is JsonArray) {
               throw JsonExceptionsKt.JsonDecodingException(
                  -1,
                  "Expected ${(JsonArray::class).getSimpleName()}, but had ${(currentObject.getClass()::class).getSimpleName()} as the serialized body of $var33 at element: ${access$renderTagStack(
                     this
                  )}",
                  currentObject.toString()
               );
            }

            var39./* $VF: Unable to resugar constructor */<init>(var42, currentObject as JsonArray);
         } else {
            var39 = new JsonTreeMapDecoder;
            val var10002: Json = this.getJson();
            val `serialName$iv$iv`: java.lang.String = descriptor.getSerialName();
            if (currentObject !is JsonObject) {
               throw JsonExceptionsKt.JsonDecodingException(
                  -1,
                  "Expected ${(JsonObject::class).getSimpleName()}, but had ${(currentObject.getClass()::class).getSimpleName()} as the serialized body of $`serialName$iv$iv` at element: ${access$renderTagStack(
                     this
                  )}",
                  currentObject.toString()
               );
            }

            var39./* $VF: Unable to resugar constructor */<init>(var10002, currentObject as JsonObject);
         }

         var39 = var39;
      } else {
         val var40: JsonTreeDecoder = new JsonTreeDecoder;
         val var43: Json = this.getJson();
         val var24: java.lang.String = descriptor.getSerialName();
         if (currentObject !is JsonObject) {
            throw JsonExceptionsKt.JsonDecodingException(
               -1,
               "Expected ${(JsonObject::class).getSimpleName()}, but had ${(currentObject.getClass()::class).getSimpleName()} as the serialized body of $var24 at element: ${access$renderTagStack(
                  this
               )}",
               currentObject.toString()
            );
         }

         var40./* $VF: Unable to resugar constructor */<init>(var43, currentObject as JsonObject, this.polymorphicDiscriminator, null, 8, null);
         var39 = var40;
      }

      return var39;
   }

   public override fun endStructure(descriptor: SerialDescriptor) {
   }

   public override fun decodeNotNullMark(): Boolean {
      return this.currentObject() !is JsonNull;
   }

   protected inline fun getPrimitiveValue(tag: String, descriptor: SerialDescriptor): JsonPrimitive {
      val `value$iv`: JsonElement = access$currentElement(this, tag);
      val `serialName$iv`: java.lang.String = descriptor.getSerialName();
      if (`value$iv` !is JsonPrimitive) {
         throw JsonExceptionsKt.JsonDecodingException(
            -1,
            "Expected ${(JsonPrimitive::class).getSimpleName()}, but had ${(`value$iv`.getClass()::class).getSimpleName()} as the serialized body of $`serialName$iv` at element: ${this.renderTagStack(
               tag
            )}",
            `value$iv`.toString()
         );
      } else {
         return `value$iv` as JsonPrimitive;
      }
   }

   private inline fun <T : Any> getPrimitiveValue(tag: String, primitiveName: String, convert: (JsonPrimitive) -> T?): T {
      val e: JsonElement = this.currentElement(tag);
      if (e !is JsonPrimitive) {
         throw JsonExceptionsKt.JsonDecodingException(
            -1,
            "Expected ${(JsonPrimitive::class).getSimpleName()}, but had ${(e.getClass()::class).getSimpleName()} as the serialized body of $primitiveName at element: ${this.renderTagStack(
               tag
            )}",
            e.toString()
         );
      } else {
         val literal: JsonPrimitive = e as JsonPrimitive;

         try {
            val var10000: Any = convert.invoke(literal);
            if (var10000 == null) {
               this.unparsedPrimitive(literal, primitiveName, tag);
               throw new KotlinNothingValueException();
            } else {
               return (T)var10000;
            }
         } catch (var18: IllegalArgumentException) {
            this.unparsedPrimitive(e as JsonPrimitive, primitiveName, tag);
            throw new KotlinNothingValueException();
         }
      }
   }

   private fun unparsedPrimitive(literal: JsonPrimitive, primitive: String, tag: String): Nothing {
      throw JsonExceptionsKt.JsonDecodingException(
         -1,
         "Failed to parse literal '$literal' as ${if (StringsKt.startsWith$default(primitive, "i", false, 2, null)) "an $primitive" else "a $primitive"} value at element: ${this.renderTagStack(
            tag
         )}",
         this.currentObject().toString()
      );
   }

   protected abstract fun currentElement(tag: String): JsonElement {
   }

   protected open fun decodeTaggedEnum(tag: String, enumDescriptor: SerialDescriptor): Int {
      val var10001: Json = this.getJson();
      val `value$iv$iv`: JsonElement = access$currentElement(this, tag);
      val `serialName$iv$iv`: java.lang.String = enumDescriptor.getSerialName();
      if (`value$iv$iv` !is JsonPrimitive) {
         throw JsonExceptionsKt.JsonDecodingException(
            -1,
            "Expected ${(JsonPrimitive::class).getSimpleName()}, but had ${(`value$iv$iv`.getClass()::class).getSimpleName()} as the serialized body of $`serialName$iv$iv` at element: ${this.renderTagStack(
               tag
            )}",
            `value$iv$iv`.toString()
         );
      } else {
         return JsonNamesMapKt.getJsonNameIndexOrThrow$default(enumDescriptor, var10001, (`value$iv$iv` as JsonPrimitive).getContent(), null, 4, null);
      }
   }

   protected open fun decodeTaggedNull(tag: String): Nothing? {
      return null;
   }

   protected open fun decodeTaggedNotNullMark(tag: String): Boolean {
      return this.currentElement(tag) != JsonNull.INSTANCE;
   }

   protected open fun decodeTaggedBoolean(tag: String): Boolean {
      val `this_$iv`: AbstractJsonTreeDecoder = this;
      val `tag$iv`: java.lang.String = tag;
      val `primitiveName$iv`: java.lang.String = "boolean";
      val `e$iv`: JsonElement = this.currentElement(tag);
      if (`e$iv` !is JsonPrimitive) {
         throw JsonExceptionsKt.JsonDecodingException(
            -1,
            "Expected ${(JsonPrimitive::class).getSimpleName()}, but had ${(`e$iv`.getClass()::class).getSimpleName()} as the serialized body of boolean at element: ${this.renderTagStack(
               tag
            )}",
            `e$iv`.toString()
         );
      } else {
         val `literal$iv`: JsonPrimitive = `e$iv` as JsonPrimitive;

         var var10000: java.lang.Boolean;
         try {
            var10000 = JsonElementKt.getBooleanOrNull(`literal$iv`);
            if (var10000 == null) {
               `this_$iv`.unparsedPrimitive(`literal$iv`, `primitiveName$iv`, `tag$iv`);
               throw new KotlinNothingValueException();
            }
         } catch (var21: IllegalArgumentException) {
            this.unparsedPrimitive(`e$iv` as JsonPrimitive, "boolean", tag);
            throw new KotlinNothingValueException();
         }

         return var10000;
      }
   }

   protected open fun decodeTaggedByte(tag: String): Byte {
      val `this_$iv`: AbstractJsonTreeDecoder = this;
      val `tag$iv`: java.lang.String = tag;
      val `primitiveName$iv`: java.lang.String = "byte";
      val `e$iv`: JsonElement = this.currentElement(tag);
      if (`e$iv` !is JsonPrimitive) {
         throw JsonExceptionsKt.JsonDecodingException(
            -1,
            "Expected ${(JsonPrimitive::class).getSimpleName()}, but had ${(`e$iv`.getClass()::class).getSimpleName()} as the serialized body of byte at element: ${this.renderTagStack(
               tag
            )}",
            `e$iv`.toString()
         );
      } else {
         val `literal$iv`: JsonPrimitive = `e$iv` as JsonPrimitive;

         var var10000: java.lang.Byte;
         try {
            val result: Long = JsonElementKt.parseLongImpl(`literal$iv`);
            var10000 = if (-128L <= result && result <= 127L) (byte)((int)result) else null;
            if (var10000 == null) {
               `this_$iv`.unparsedPrimitive(`literal$iv`, `primitiveName$iv`, `tag$iv`);
               throw new KotlinNothingValueException();
            }
         } catch (var23: IllegalArgumentException) {
            this.unparsedPrimitive(`e$iv` as JsonPrimitive, "byte", tag);
            throw new KotlinNothingValueException();
         }

         return var10000.byteValue();
      }
   }

   protected open fun decodeTaggedShort(tag: String): Short {
      val `this_$iv`: AbstractJsonTreeDecoder = this;
      val `tag$iv`: java.lang.String = tag;
      val `primitiveName$iv`: java.lang.String = "short";
      val `e$iv`: JsonElement = this.currentElement(tag);
      if (`e$iv` !is JsonPrimitive) {
         throw JsonExceptionsKt.JsonDecodingException(
            -1,
            "Expected ${(JsonPrimitive::class).getSimpleName()}, but had ${(`e$iv`.getClass()::class).getSimpleName()} as the serialized body of short at element: ${this.renderTagStack(
               tag
            )}",
            `e$iv`.toString()
         );
      } else {
         val `literal$iv`: JsonPrimitive = `e$iv` as JsonPrimitive;

         var var10000: java.lang.Short;
         try {
            val result: Long = JsonElementKt.parseLongImpl(`literal$iv`);
            var10000 = if (-32768L <= result && result <= 32767L) (short)((int)result) else null;
            if (var10000 == null) {
               `this_$iv`.unparsedPrimitive(`literal$iv`, `primitiveName$iv`, `tag$iv`);
               throw new KotlinNothingValueException();
            }
         } catch (var23: IllegalArgumentException) {
            this.unparsedPrimitive(`e$iv` as JsonPrimitive, "short", tag);
            throw new KotlinNothingValueException();
         }

         return var10000.shortValue();
      }
   }

   protected open fun decodeTaggedInt(tag: String): Int {
      val `this_$iv`: AbstractJsonTreeDecoder = this;
      val `tag$iv`: java.lang.String = tag;
      val `primitiveName$iv`: java.lang.String = "int";
      val `e$iv`: JsonElement = this.currentElement(tag);
      if (`e$iv` !is JsonPrimitive) {
         throw JsonExceptionsKt.JsonDecodingException(
            -1,
            "Expected ${(JsonPrimitive::class).getSimpleName()}, but had ${(`e$iv`.getClass()::class).getSimpleName()} as the serialized body of int at element: ${this.renderTagStack(
               tag
            )}",
            `e$iv`.toString()
         );
      } else {
         val `literal$iv`: JsonPrimitive = `e$iv` as JsonPrimitive;

         var var10000: Int;
         try {
            val result: Long = JsonElementKt.parseLongImpl(`literal$iv`);
            var10000 = if (-2147483648L <= result && result <= 2147483647L) (int)result else null;
            if (var10000 == null) {
               `this_$iv`.unparsedPrimitive(`literal$iv`, `primitiveName$iv`, `tag$iv`);
               throw new KotlinNothingValueException();
            }
         } catch (var23: IllegalArgumentException) {
            this.unparsedPrimitive(`e$iv` as JsonPrimitive, "int", tag);
            throw new KotlinNothingValueException();
         }

         return var10000.intValue();
      }
   }

   protected open fun decodeTaggedLong(tag: String): Long {
      val `e$iv`: JsonElement = this.currentElement(tag);
      if (`e$iv` !is JsonPrimitive) {
         throw JsonExceptionsKt.JsonDecodingException(
            -1,
            "Expected ${(JsonPrimitive::class).getSimpleName()}, but had ${(`e$iv`.getClass()::class).getSimpleName()} as the serialized body of long at element: ${this.renderTagStack(
               tag
            )}",
            `e$iv`.toString()
         );
      } else {
         val `literal$iv`: JsonPrimitive = `e$iv` as JsonPrimitive;

         try {
            return JsonElementKt.parseLongImpl(`literal$iv`);
         } catch (var21: IllegalArgumentException) {
            this.unparsedPrimitive(`e$iv` as JsonPrimitive, "long", tag);
            throw new KotlinNothingValueException();
         }
      }
   }

   protected open fun decodeTaggedFloat(tag: String): Float {
      val `e$iv`: JsonElement = this.currentElement(tag);
      if (`e$iv` !is JsonPrimitive) {
         throw JsonExceptionsKt.JsonDecodingException(
            -1,
            "Expected ${(JsonPrimitive::class).getSimpleName()}, but had ${(`e$iv`.getClass()::class).getSimpleName()} as the serialized body of float at element: ${this.renderTagStack(
               tag
            )}",
            `e$iv`.toString()
         );
      } else {
         val `literal$iv`: JsonPrimitive = `e$iv` as JsonPrimitive;

         var var10000: Float;
         try {
            var10000 = JsonElementKt.getFloat(`literal$iv`);
         } catch (var22: IllegalArgumentException) {
            this.unparsedPrimitive(`e$iv` as JsonPrimitive, "float", tag);
            throw new KotlinNothingValueException();
         }

         if (!this.getJson().getConfiguration().getAllowSpecialFloatingPointValues() && !(Math.abs(var10000) <= java.lang.Float.MAX_VALUE)) {
            throw JsonExceptionsKt.InvalidFloatingPointDecoded(var10000, tag, this.currentObject().toString());
         } else {
            return var10000;
         }
      }
   }

   protected open fun decodeTaggedDouble(tag: String): Double {
      val `e$iv`: JsonElement = this.currentElement(tag);
      if (`e$iv` !is JsonPrimitive) {
         throw JsonExceptionsKt.JsonDecodingException(
            -1,
            "Expected ${(JsonPrimitive::class).getSimpleName()}, but had ${(`e$iv`.getClass()::class).getSimpleName()} as the serialized body of double at element: ${this.renderTagStack(
               tag
            )}",
            `e$iv`.toString()
         );
      } else {
         val `literal$iv`: JsonPrimitive = `e$iv` as JsonPrimitive;

         var var10000: Double;
         try {
            var10000 = JsonElementKt.getDouble(`literal$iv`);
         } catch (var23: IllegalArgumentException) {
            this.unparsedPrimitive(`e$iv` as JsonPrimitive, "double", tag);
            throw new KotlinNothingValueException();
         }

         if (!this.getJson().getConfiguration().getAllowSpecialFloatingPointValues() && !(Math.abs(var10000) <= java.lang.Double.MAX_VALUE)) {
            throw JsonExceptionsKt.InvalidFloatingPointDecoded(var10000, tag, this.currentObject().toString());
         } else {
            return var10000;
         }
      }
   }

   protected open fun decodeTaggedChar(tag: String): Char {
      val `e$iv`: JsonElement = this.currentElement(tag);
      if (`e$iv` !is JsonPrimitive) {
         throw JsonExceptionsKt.JsonDecodingException(
            -1,
            "Expected ${(JsonPrimitive::class).getSimpleName()}, but had ${(`e$iv`.getClass()::class).getSimpleName()} as the serialized body of char at element: ${this.renderTagStack(
               tag
            )}",
            `e$iv`.toString()
         );
      } else {
         val `literal$iv`: JsonPrimitive = `e$iv` as JsonPrimitive;

         try {
            return StringsKt.single(`literal$iv`.getContent());
         } catch (var21: IllegalArgumentException) {
            this.unparsedPrimitive(`e$iv` as JsonPrimitive, "char", tag);
            throw new KotlinNothingValueException();
         }
      }
   }

   protected open fun decodeTaggedString(tag: String): String {
      val `value$iv`: JsonElement = this.currentElement(tag);
      if (`value$iv` !is JsonPrimitive) {
         throw JsonExceptionsKt.JsonDecodingException(
            -1,
            "Expected ${(JsonPrimitive::class).getSimpleName()}, but had ${(`value$iv`.getClass()::class).getSimpleName()} as the serialized body of string at element: ${this.renderTagStack(
               tag
            )}",
            `value$iv`.toString()
         );
      } else {
         val value: JsonPrimitive = `value$iv` as JsonPrimitive;
         if ((`value$iv` as JsonPrimitive) !is JsonLiteral) {
            throw JsonExceptionsKt.JsonDecodingException(
               -1,
               "Expected string value for a non-null key '$tag', got null literal instead at element: ${this.renderTagStack(tag)}",
               this.currentObject().toString()
            );
         } else if (!(value as JsonLiteral).isString() && !this.getJson().getConfiguration().isLenient()) {
            throw JsonExceptionsKt.JsonDecodingException(
               -1,
               "String literal for key '$tag' should be quoted at element: ${this.renderTagStack(tag)}.\nUse 'isLenient = true' in 'Json {}' builder to accept non-compliant JSON.",
               this.currentObject().toString()
            );
         } else {
            return (value as JsonLiteral).getContent();
         }
      }
   }

   protected open fun decodeTaggedInline(tag: String, inlineDescriptor: SerialDescriptor): Decoder {
      val var20: Decoder;
      if (StreamingJsonEncoderKt.isUnsignedNumber(inlineDescriptor)) {
         val var10000: Json = this.getJson();
         val `value$iv$iv`: JsonElement = access$currentElement(this, tag);
         val `serialName$iv$iv`: java.lang.String = inlineDescriptor.getSerialName();
         if (`value$iv$iv` !is JsonPrimitive) {
            throw JsonExceptionsKt.JsonDecodingException(
               -1,
               "Expected ${(JsonPrimitive::class).getSimpleName()}, but had ${(`value$iv$iv`.getClass()::class).getSimpleName()} as the serialized body of $`serialName$iv$iv` at element: ${this.renderTagStack(
                  tag
               )}",
               `value$iv$iv`.toString()
            );
         }

         var20 = new JsonDecoderForUnsignedTypes(StringJsonLexerKt.StringJsonLexer(var10000, (`value$iv$iv` as JsonPrimitive).getContent()), this.getJson());
      } else {
         var20 = super.decodeTaggedInline(tag, inlineDescriptor);
      }

      return var20;
   }

   public override fun decodeInline(descriptor: SerialDescriptor): Decoder {
      return if (this.getCurrentTagOrNull() != null)
         super.decodeInline(descriptor)
         else
         new JsonPrimitiveDecoder(this.getJson(), this.getValue(), this.polymorphicDiscriminator).decodeInline(descriptor);
   }
}
