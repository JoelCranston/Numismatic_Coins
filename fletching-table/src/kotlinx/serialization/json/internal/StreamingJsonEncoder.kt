package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.PolymorphicSerializerKt
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import kotlinx.serialization.json.ClassDiscriminatorMode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementSerializer
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.modules.SerializersModule

@SourceDebugExtension(["SMAP\nStreamingJsonEncoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamingJsonEncoder.kt\nkotlinx/serialization/json/internal/StreamingJsonEncoder\n+ 2 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,232:1\n178#1,2:261\n178#1,2:263\n21#2,12:233\n35#2,15:246\n1#3:245\n1#3:265\n*S KotlinDebug\n*F\n+ 1 StreamingJsonEncoder.kt\nkotlinx/serialization/json/internal/StreamingJsonEncoder\n*L\n168#1:261,2\n169#1:263,2\n68#1:233,12\n68#1:246,15\n68#1:245\n*E\n"])
internal class StreamingJsonEncoder(composer: Composer, json: Json, mode: WriteMode, vararg modeReuseCache: Any) : AbstractEncoder, JsonEncoder {
   private final val composer: Composer
   public open val json: Json
   private final val mode: WriteMode
   private final val modeReuseCache: Array<JsonEncoder?>?
   public open val serializersModule: SerializersModule
   private final val configuration: JsonConfiguration
   private final var forceQuoting: Boolean
   private final var polymorphicDiscriminator: String?
   private final var polymorphicSerialName: String?

   init {
      this.composer = composer;
      this.json = json;
      this.mode = mode;
      this.modeReuseCache = modeReuseCache;
      this.serializersModule = this.getJson().getSerializersModule();
      this.configuration = this.getJson().getConfiguration();
      val i: Int = this.mode.ordinal();
      if (this.modeReuseCache != null && (this.modeReuseCache[i] != null || this.modeReuseCache[i] != this)) {
         this.modeReuseCache[i] = this;
      }
   }

   internal constructor(output: InternalJsonWriter, json: Json, mode: WriteMode, vararg modeReuseCache: Any) : this(
         ComposersKt.Composer(output, json), json, mode, modeReuseCache
      )
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

   public override fun <T> encodeSerializableValue(serializer: SerializationStrategy<T>, value: T) {
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
         val var20: SerializationStrategy;
         if (`isPolymorphicSerializer$iv`) {
            val `casted$iv`: AbstractPolymorphicSerializer = serializer as AbstractPolymorphicSerializer;
            if (value == null) {
               throw new IllegalArgumentException(
                  ("Value for serializer ${(serializer as AbstractPolymorphicSerializer).getDescriptor()} should always be non-null. Please report issue to the kotlinx.serialization tracker.")
                     .toString()
               );
            }

            val var17: SerializationStrategy = PolymorphicSerializerKt.findPolymorphicSerializer(`casted$iv`, `$this$encodePolymorphically$iv`, value);
            if (`baseClassDiscriminator$iv` != null) {
               PolymorphicKt.access$validateIfSealed(serializer, var17, `baseClassDiscriminator$iv`);
               PolymorphicKt.checkKind(var17.getDescriptor().getKind());
            }

            var20 = var17;
         } else {
            var20 = serializer;
         }

         if (`baseClassDiscriminator$iv` != null) {
            val serialName: java.lang.String = var20.getDescriptor().getSerialName();
            this.polymorphicDiscriminator = `baseClassDiscriminator$iv`;
            this.polymorphicSerialName = serialName;
         }

         var20.serialize(`$this$encodePolymorphically$iv`, value);
      }
   }

   private fun encodeTypeInfo(discriminator: String, serialName: String) {
      this.composer.nextItem();
      this.encodeString(discriminator);
      this.composer.print(':');
      this.composer.space();
      this.encodeString(serialName);
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
      val newMode: WriteMode = WriteModeKt.switchMode(this.getJson(), descriptor);
      if (newMode.begin != 0) {
         this.composer.print(newMode.begin);
         this.composer.indent();
      }

      if (this.polymorphicDiscriminator != null) {
         var var10002: java.lang.String = this.polymorphicSerialName;
         if (this.polymorphicSerialName == null) {
            var10002 = descriptor.getSerialName();
         }

         this.encodeTypeInfo(this.polymorphicDiscriminator, var10002);
         this.polymorphicDiscriminator = null;
         this.polymorphicSerialName = null;
      }

      if (this.mode === newMode) {
         return this;
      } else {
         if (this.modeReuseCache != null) {
            val var10000: JsonEncoder = this.modeReuseCache[newMode.ordinal()];
            if (var10000 != null) {
               return var10000;
            }
         }

         return new StreamingJsonEncoder(this.composer, this.getJson(), newMode, this.modeReuseCache);
      }
   }

   public override fun endStructure(descriptor: SerialDescriptor) {
      if (this.mode.end != 0) {
         this.composer.unIndent();
         this.composer.nextItemIfNotFirst();
         this.composer.print(this.mode.end);
      }
   }

   public override fun encodeElement(descriptor: SerialDescriptor, index: Int): Boolean {
      switch (StreamingJsonEncoder.WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()]) {
         case 1:
            if (!this.composer.getWritingFirst()) {
               this.composer.print(',');
            }

            this.composer.nextItem();
            break;
         case 2:
            if (!this.composer.getWritingFirst()) {
               val var10001: Boolean;
               if (index % 2 == 0) {
                  this.composer.print(',');
                  this.composer.nextItem();
                  var10001 = true;
               } else {
                  this.composer.print(':');
                  this.composer.space();
                  var10001 = false;
               }

               this.forceQuoting = var10001;
            } else {
               this.forceQuoting = true;
               this.composer.nextItem();
            }
            break;
         case 3:
            if (index == 0) {
               this.forceQuoting = true;
            }

            if (index == 1) {
               this.composer.print(',');
               this.composer.space();
               this.forceQuoting = false;
            }
            break;
         default:
            if (!this.composer.getWritingFirst()) {
               this.composer.print(',');
            }

            this.composer.nextItem();
            this.encodeString(JsonNamesMapKt.getJsonElementName(descriptor, this.getJson(), index));
            this.composer.print(':');
            this.composer.space();
      }

      return true;
   }

   public override fun <T : Any> encodeNullableSerializableElement(descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<T>, value: T?) {
      if (value != null || this.configuration.getExplicitNulls()) {
         super.encodeNullableSerializableElement(descriptor, index, serializer, value);
      }
   }

   public override fun encodeInline(descriptor: SerialDescriptor): Encoder {
      val var21: Encoder;
      if (StreamingJsonEncoderKt.isUnsignedNumber(descriptor)) {
         var21 = new StreamingJsonEncoder(
            if (this.composer is ComposerForUnsignedNumbers) this.composer else new ComposerForUnsignedNumbers(this.composer.writer, this.forceQuoting),
            this.getJson(),
            this.mode,
            null
         );
      } else if (StreamingJsonEncoderKt.isUnquotedLiteral(descriptor)) {
         var21 = new StreamingJsonEncoder(
            if (this.composer is ComposerForUnquotedLiterals) this.composer else new ComposerForUnquotedLiterals(this.composer.writer, this.forceQuoting),
            this.getJson(),
            this.mode,
            null
         );
      } else if (this.polymorphicDiscriminator != null) {
         this.polymorphicSerialName = descriptor.getSerialName();
         var21 = this;
      } else {
         var21 = super.encodeInline(descriptor);
      }

      return var21;
   }

   public override fun encodeNull() {
      this.composer.print("null");
   }

   public override fun encodeBoolean(value: Boolean) {
      if (this.forceQuoting) {
         this.encodeString(java.lang.String.valueOf(value));
      } else {
         this.composer.print(value);
      }
   }

   public override fun encodeByte(value: Byte) {
      if (this.forceQuoting) {
         this.encodeString(java.lang.String.valueOf((int)value));
      } else {
         this.composer.print(value);
      }
   }

   public override fun encodeShort(value: Short) {
      if (this.forceQuoting) {
         this.encodeString(java.lang.String.valueOf((int)value));
      } else {
         this.composer.print(value);
      }
   }

   public override fun encodeInt(value: Int) {
      if (this.forceQuoting) {
         this.encodeString(java.lang.String.valueOf(value));
      } else {
         this.composer.print(value);
      }
   }

   public override fun encodeLong(value: Long) {
      if (this.forceQuoting) {
         this.encodeString(java.lang.String.valueOf(value));
      } else {
         this.composer.print(value);
      }
   }

   public override fun encodeFloat(value: Float) {
      if (this.forceQuoting) {
         this.encodeString(java.lang.String.valueOf(value));
      } else {
         this.composer.print(value);
      }

      if (!this.configuration.getAllowSpecialFloatingPointValues() && !(Math.abs(value) <= java.lang.Float.MAX_VALUE)) {
         throw JsonExceptionsKt.InvalidFloatingPointEncoded(value, this.composer.writer.toString());
      }
   }

   public override fun encodeDouble(value: Double) {
      if (this.forceQuoting) {
         this.encodeString(java.lang.String.valueOf(value));
      } else {
         this.composer.print(value);
      }

      if (!this.configuration.getAllowSpecialFloatingPointValues() && !(Math.abs(value) <= java.lang.Double.MAX_VALUE)) {
         throw JsonExceptionsKt.InvalidFloatingPointEncoded(value, this.composer.writer.toString());
      }
   }

   public override fun encodeChar(value: Char) {
      this.encodeString(java.lang.String.valueOf(value));
   }

   public override fun encodeString(value: String) {
      this.composer.printQuoted(value);
   }

   public override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
      this.encodeString(enumDescriptor.getElementName(index));
   }
}
