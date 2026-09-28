package kotlinx.serialization.json.internal

import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.PolymorphicSerializerKt
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import kotlinx.serialization.internal.NamedValueEncoder
import kotlinx.serialization.json.ClassDiscriminatorMode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementKt
import kotlinx.serialization.json.JsonElementSerializer
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.internal.AbstractJsonTreeEncoder.inlineUnsignedNumberEncoder.1
import kotlinx.serialization.modules.SerializersModule

@SourceDebugExtension(["SMAP\nTreeJsonEncoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/AbstractJsonTreeEncoder\n+ 2 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 WriteMode.kt\nkotlinx/serialization/json/internal/WriteModeKt\n*L\n1#1,279:1\n21#2,12:280\n35#2,15:293\n1#3:292\n36#4,9:308\n*S KotlinDebug\n*F\n+ 1 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/AbstractJsonTreeEncoder\n*L\n83#1:280,12\n83#1:293,15\n83#1:292\n153#1:308,9\n*E\n"])
private sealed class AbstractJsonTreeEncoder protected constructor(json: Json, nodeConsumer: (JsonElement) -> Unit) : NamedValueEncoder, JsonEncoder {
   public final val json: Json
   protected final val nodeConsumer: (JsonElement) -> Unit

   public final val serializersModule: SerializersModule
      public final get() {
         return this.json.getSerializersModule();
      }


   protected final val configuration: JsonConfiguration
   private final var polymorphicDiscriminator: String?
   private final var polymorphicSerialName: String?

   init {
      this.json = json;
      this.nodeConsumer = nodeConsumer;
      this.configuration = this.json.getConfiguration();
   }

   protected override fun elementName(descriptor: SerialDescriptor, index: Int): String {
      return JsonNamesMapKt.getJsonElementName(descriptor, this.json, index);
   }

   public override fun encodeJsonElement(element: JsonElement) {
      if (this.polymorphicDiscriminator != null && element !is JsonObject) {
         PolymorphicKt.throwJsonElementPolymorphicException(this.polymorphicSerialName, element);
         throw new KotlinNothingValueException();
      } else {
         this.encodeSerializableValue(JsonElementSerializer.INSTANCE, element);
      }
   }

   public override fun shouldEncodeElementDefault(descriptor: SerialDescriptor, index: Int): Boolean {
      return this.configuration.getEncodeDefaults();
   }

   protected override fun composeName(parentName: String, childName: String): String {
      return childName;
   }

   public abstract fun putElement(key: String, element: JsonElement) {
   }

   public abstract fun getCurrent(): JsonElement {
   }

   public override fun encodeNotNullMark() {
   }

   public override fun encodeNull() {
      val var10000: java.lang.String = this.getCurrentTagOrNull();
      if (var10000 == null) {
         this.nodeConsumer.invoke(JsonNull.INSTANCE);
      } else {
         this.encodeTaggedNull(var10000);
      }
   }

   protected open fun encodeTaggedNull(tag: String) {
      this.putElement(tag, JsonNull.INSTANCE);
   }

   protected open fun encodeTaggedInt(tag: String, value: Int) {
      this.putElement(tag, JsonElementKt.JsonPrimitive(value));
   }

   protected open fun encodeTaggedByte(tag: String, value: Byte) {
      this.putElement(tag, JsonElementKt.JsonPrimitive(value));
   }

   protected open fun encodeTaggedShort(tag: String, value: Short) {
      this.putElement(tag, JsonElementKt.JsonPrimitive(value));
   }

   protected open fun encodeTaggedLong(tag: String, value: Long) {
      this.putElement(tag, JsonElementKt.JsonPrimitive(value));
   }

   protected open fun encodeTaggedFloat(tag: String, value: Float) {
      this.putElement(tag, JsonElementKt.JsonPrimitive(value));
      if (!this.configuration.getAllowSpecialFloatingPointValues() && !(Math.abs(value) <= java.lang.Float.MAX_VALUE)) {
         throw JsonExceptionsKt.InvalidFloatingPointEncoded(value, tag, this.getCurrent().toString());
      }
   }

   public override fun <T> encodeSerializableValue(serializer: SerializationStrategy<T>, value: T) {
      if (this.getCurrentTagOrNull() == null
         && TreeJsonEncoderKt.access$getRequiresTopLevelTag(WriteModeKt.carrierDescriptor(serializer.getDescriptor(), this.getSerializersModule()))) {
         new JsonPrimitiveEncoder(this.json, this.nodeConsumer).encodeSerializableValue(serializer, value);
      } else {
         val `$this$encodePolymorphically$iv`: JsonEncoder = this;
         if (this.getJson().getConfiguration().getUseArrayPolymorphism()) {
            serializer.serialize(`$this$encodePolymorphically$iv`, value);
         } else {
            val `isPolymorphicSerializer$iv`: Boolean = serializer is AbstractPolymorphicSerializer;
            var var10000: Boolean;
            if (serializer is AbstractPolymorphicSerializer) {
               var10000 = `$this$encodePolymorphically$iv`.getJson().getConfiguration().getClassDiscriminatorMode() != ClassDiscriminatorMode.NONE;
            } else {
               switch (PolymorphicKt.WhenMappings.$EnumSwitchMapping$0[$this$encodePolymorphically$iv.getJson()
                  .getConfiguration()
                  .getClassDiscriminatorMode()
                  .ordinal()]) {
                  case 1:
                  case 2:
                     var10000 = false;
                     break;
                  case 3:
                     val `actual$iv`: SerialKind = serializer.getDescriptor().getKind();
                     var10000 = `actual$iv` == StructureKind.CLASS.INSTANCE || `actual$iv` == StructureKind.OBJECT.INSTANCE;
                     break;
                  default:
                     throw new NoWhenBranchMatchedException();
               }
            }

            val `baseClassDiscriminator$iv`: java.lang.String = if (var10000)
               PolymorphicKt.classDiscriminator(serializer.getDescriptor(), `$this$encodePolymorphically$iv`.getJson())
               else
               null;
            val var21: SerializationStrategy;
            if (`isPolymorphicSerializer$iv`) {
               val `casted$iv`: AbstractPolymorphicSerializer = serializer as AbstractPolymorphicSerializer;
               if (value == null) {
                  throw new IllegalArgumentException(
                     ("Value for serializer ${(serializer as AbstractPolymorphicSerializer).getDescriptor()} should always be non-null. Please report issue to the kotlinx.serialization tracker.")
                        .toString()
                  );
               }

               val var18: SerializationStrategy = PolymorphicSerializerKt.findPolymorphicSerializer(`casted$iv`, `$this$encodePolymorphically$iv`, value);
               if (`baseClassDiscriminator$iv` != null) {
                  PolymorphicKt.access$validateIfSealed(serializer, var18, `baseClassDiscriminator$iv`);
                  PolymorphicKt.checkKind(var18.getDescriptor().getKind());
               }

               var21 = var18;
            } else {
               var21 = serializer;
            }

            if (`baseClassDiscriminator$iv` != null) {
               val serialName: java.lang.String = var21.getDescriptor().getSerialName();
               this.polymorphicDiscriminator = `baseClassDiscriminator$iv`;
               this.polymorphicSerialName = serialName;
            }

            var21.serialize(`$this$encodePolymorphically$iv`, value);
         }
      }
   }

   protected open fun encodeTaggedDouble(tag: String, value: Double) {
      this.putElement(tag, JsonElementKt.JsonPrimitive(value));
      if (!this.configuration.getAllowSpecialFloatingPointValues() && !(Math.abs(value) <= java.lang.Double.MAX_VALUE)) {
         throw JsonExceptionsKt.InvalidFloatingPointEncoded(value, tag, this.getCurrent().toString());
      }
   }

   protected open fun encodeTaggedBoolean(tag: String, value: Boolean) {
      this.putElement(tag, JsonElementKt.JsonPrimitive(value));
   }

   protected open fun encodeTaggedChar(tag: String, value: Char) {
      this.putElement(tag, JsonElementKt.JsonPrimitive(java.lang.String.valueOf(value)));
   }

   protected open fun encodeTaggedString(tag: String, value: String) {
      this.putElement(tag, JsonElementKt.JsonPrimitive(value));
   }

   protected open fun encodeTaggedEnum(tag: String, enumDescriptor: SerialDescriptor, ordinal: Int) {
      this.putElement(tag, JsonElementKt.JsonPrimitive(enumDescriptor.getElementName(ordinal)));
   }

   protected open fun encodeTaggedValue(tag: String, value: Any) {
      this.putElement(tag, JsonElementKt.JsonPrimitive(value.toString()));
   }

   protected open fun encodeTaggedInline(tag: String, inlineDescriptor: SerialDescriptor): Encoder {
      return if (StreamingJsonEncoderKt.isUnsignedNumber(inlineDescriptor))
         this.inlineUnsignedNumberEncoder(tag)
         else
         (
            if (StreamingJsonEncoderKt.isUnquotedLiteral(inlineDescriptor))
               this.inlineUnquotedLiteralEncoder(tag, inlineDescriptor)
               else
               super.encodeTaggedInline(tag, inlineDescriptor)
         );
   }

   public override fun encodeInline(descriptor: SerialDescriptor): Encoder {
      val var10000: Encoder;
      if (this.getCurrentTagOrNull() != null) {
         if (this.polymorphicDiscriminator != null) {
            this.polymorphicSerialName = descriptor.getSerialName();
         }

         var10000 = super.encodeInline(descriptor);
      } else {
         var10000 = new JsonPrimitiveEncoder(this.json, this.nodeConsumer).encodeInline(descriptor);
      }

      return var10000;
   }

   @SuppressAnimalSniffer
   private fun inlineUnsignedNumberEncoder(tag: String): 1 {
      return new 1(this, tag);
   }

   private fun inlineUnquotedLiteralEncoder(tag: String, inlineDescriptor: SerialDescriptor): kotlinx.serialization.json.internal.AbstractJsonTreeEncoder.inlineUnquotedLiteralEncoder.1 {
      return new kotlinx.serialization.json.internal.AbstractJsonTreeEncoder.inlineUnquotedLiteralEncoder.1(this, tag, inlineDescriptor);
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
      val consumer: Function1 = if (this.getCurrentTagOrNull() == null) this.nodeConsumer else AbstractJsonTreeEncoder::beginStructure$lambda$2;
      val discriminator: SerialKind = descriptor.getKind();
      val var13: AbstractJsonTreeEncoder;
      if (discriminator == StructureKind.LIST.INSTANCE || discriminator is PolymorphicKind) {
         var13 = new JsonTreeListEncoder(this.json, consumer);
      } else if (discriminator == StructureKind.MAP.INSTANCE) {
         val `$this$selectMapMode$iv`: Json = this.json;
         val `keyDescriptor$iv`: SerialDescriptor = WriteModeKt.carrierDescriptor(
            descriptor.getElementDescriptor(0), `$this$selectMapMode$iv`.getSerializersModule()
         );
         val `keyKind$iv`: SerialKind = `keyDescriptor$iv`.getKind();
         val var10000: Any;
         if (`keyKind$iv` !is PrimitiveKind && !(`keyKind$iv` == SerialKind.ENUM.INSTANCE)) {
            if (!`$this$selectMapMode$iv`.getConfiguration().getAllowStructuredMapKeys()) {
               throw JsonExceptionsKt.InvalidKeyKindException(`keyDescriptor$iv`);
            }

            var10000 = new JsonTreeListEncoder(this.json, consumer);
         } else {
            var10000 = new JsonTreeMapEncoder(this.json, consumer);
         }

         var13 = var10000 as AbstractJsonTreeEncoder;
      } else {
         var13 = new JsonTreeEncoder(this.json, consumer);
      }

      if (this.polymorphicDiscriminator != null) {
         if (var13 is JsonTreeMapEncoder) {
            (var13 as JsonTreeMapEncoder).putElement("key", JsonElementKt.JsonPrimitive(this.polymorphicDiscriminator));
            val var14: JsonTreeMapEncoder = var13 as JsonTreeMapEncoder;
            var var10002: java.lang.String = this.polymorphicSerialName;
            if (this.polymorphicSerialName == null) {
               var10002 = descriptor.getSerialName();
            }

            var14.putElement("value", JsonElementKt.JsonPrimitive(var10002));
         } else {
            var var15: java.lang.String = this.polymorphicSerialName;
            if (this.polymorphicSerialName == null) {
               var15 = descriptor.getSerialName();
            }

            var13.putElement(this.polymorphicDiscriminator, JsonElementKt.JsonPrimitive(var15));
         }

         this.polymorphicDiscriminator = null;
         this.polymorphicSerialName = null;
      }

      return var13;
   }

   protected override fun endEncode(descriptor: SerialDescriptor) {
      this.nodeConsumer.invoke(this.getCurrent());
   }

   @JvmStatic
   fun `beginStructure$lambda$2`(`this$0`: AbstractJsonTreeEncoder, node: JsonElement): Unit {
      `this$0`.putElement(`this$0`.getCurrentTag(), node);
      return Unit.INSTANCE;
   }
}
