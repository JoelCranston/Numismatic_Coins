package net.peanuuutz.tomlkt.internal.decoder

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.SerializersModule
import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlDecoder
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.internal.TomlSerializationExceptionsKt

@SourceDebugExtension(["SMAP\nAbstractTomlDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractTomlDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/AbstractTomlInlineElementDecoder\n+ 2 AbstractTomlDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/AbstractTomlDecoderKt\n*L\n1#1,295:1\n168#2,4:296\n168#2,4:300\n168#2,4:304\n168#2,4:308\n*S KotlinDebug\n*F\n+ 1 AbstractTomlDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/AbstractTomlInlineElementDecoder\n*L\n241#1:296,4\n253#1:300,4\n265#1:304,4\n277#1:308,4\n*E\n"])
internal abstract class AbstractTomlInlineElementDecoder<D extends TomlCompositeDecoder> : TomlDecoder {
   protected final val parentDescriptor: SerialDescriptor
   protected final val elementIndex: Int
   protected final val delegate: Any

   protected final var decodedNotNullMark: Boolean
      internal set

   public final val toml: Toml
      public final get() {
         return this.delegate.getToml();
      }


   public final val serializersModule: SerializersModule
      public final get() {
         return this.delegate.getSerializersModule();
      }


   open fun AbstractTomlInlineElementDecoder(parentDescriptor: SerialDescriptor, elementIndex: Int, delegate: D) {
      this.parentDescriptor = parentDescriptor;
      this.elementIndex = elementIndex;
      this.delegate = (D)delegate;
   }

   public override fun decodeBoolean(): Boolean {
      val var10000: Boolean;
      if (!this.decodedNotNullMark) {
         var10000 = this.delegate.decodeBooleanElement(this.parentDescriptor, this.elementIndex);
      } else {
         val value: Boolean = this.delegate.decodeBoolean();
         this.decodedNotNullMark = false;
         var10000 = value;
      }

      return var10000;
   }

   public override fun decodeFloat(): Float {
      val var10000: Float;
      if (!this.decodedNotNullMark) {
         var10000 = this.delegate.decodeFloatElement(this.parentDescriptor, this.elementIndex);
      } else {
         val value: Float = this.delegate.decodeFloat();
         this.decodedNotNullMark = false;
         var10000 = value;
      }

      return var10000;
   }

   public override fun decodeDouble(): Double {
      val var10000: Double;
      if (!this.decodedNotNullMark) {
         var10000 = this.delegate.decodeDoubleElement(this.parentDescriptor, this.elementIndex);
      } else {
         val value: Double = this.delegate.decodeDouble();
         this.decodedNotNullMark = false;
         var10000 = value;
      }

      return var10000;
   }

   public override fun decodeChar(): Char {
      val var10000: Char;
      if (!this.decodedNotNullMark) {
         var10000 = this.delegate.decodeCharElement(this.parentDescriptor, this.elementIndex);
      } else {
         val value: Char = this.delegate.decodeChar();
         this.decodedNotNullMark = false;
         var10000 = value;
      }

      return var10000;
   }

   public override fun decodeString(): String {
      val var10000: java.lang.String;
      if (!this.decodedNotNullMark) {
         var10000 = this.delegate.decodeStringElement(this.parentDescriptor, this.elementIndex);
      } else {
         val value: java.lang.String = this.delegate.decodeString();
         this.decodedNotNullMark = false;
         var10000 = value;
      }

      return var10000;
   }

   public override fun decodeNull(): Nothing? {
      val var10000: Any;
      if (!this.decodedNotNullMark) {
         val value: TomlCompositeDecoder = this.delegate;
         val `descriptor$iv`: SerialDescriptor = this.parentDescriptor;
         val `index$iv`: Int = this.elementIndex;
         this.delegate.beginElement(this.parentDescriptor, this.elementIndex);
         val `value$iv`: Any = this.delegate.decodeNull();
         value.endElement(`descriptor$iv`, `index$iv`);
         var10000 = `value$iv`;
      } else {
         val var7: Void = this.delegate.decodeNull();
         this.decodedNotNullMark = false;
         var10000 = var7;
      }

      return (Void)var10000;
   }

   public override fun decodeNotNullMark(): Boolean {
      val var10000: Boolean;
      if (!this.decodedNotNullMark) {
         val `$this$decodeElement$iv`: TomlCompositeDecoder = this.delegate;
         val `descriptor$iv`: SerialDescriptor = this.parentDescriptor;
         val `index$iv`: Int = this.elementIndex;
         this.delegate.beginElement(this.parentDescriptor, this.elementIndex);
         val `value$iv`: Boolean = this.delegate.decodeNotNullMark();
         `$this$decodeElement$iv`.endElement(`descriptor$iv`, `index$iv`);
         this.decodedNotNullMark = true;
         var10000 = `value$iv`;
      } else {
         var10000 = this.delegate.decodeNotNullMark();
      }

      return var10000;
   }

   public override fun decodeTomlElement(): TomlElement {
      val var10000: Any;
      if (!this.decodedNotNullMark) {
         val value: TomlCompositeDecoder = this.delegate;
         val `descriptor$iv`: SerialDescriptor = this.parentDescriptor;
         val `index$iv`: Int = this.elementIndex;
         this.delegate.beginElement(this.parentDescriptor, this.elementIndex);
         val `value$iv`: Any = this.delegate.decodeTomlElement();
         value.endElement(`descriptor$iv`, `index$iv`);
         var10000 = `value$iv`;
      } else {
         val var7: TomlElement = this.delegate.decodeTomlElement();
         this.decodedNotNullMark = false;
         var10000 = var7;
      }

      return (TomlElement)var10000;
   }

   public override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
      val var10000: Int;
      if (!this.decodedNotNullMark) {
         val value: TomlCompositeDecoder = this.delegate;
         val `descriptor$iv`: SerialDescriptor = this.parentDescriptor;
         val `index$iv`: Int = this.elementIndex;
         this.delegate.beginElement(this.parentDescriptor, this.elementIndex);
         val `value$iv`: Int = this.delegate.decodeEnum(enumDescriptor);
         value.endElement(`descriptor$iv`, `index$iv`);
         var10000 = `value$iv`;
      } else {
         val var8: Int = this.delegate.decodeEnum(enumDescriptor);
         this.decodedNotNullMark = false;
         var10000 = var8;
      }

      return var10000;
   }

   public override fun decodeInline(descriptor: SerialDescriptor): Decoder {
      return this;
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      TomlSerializationExceptionsKt.throwUnsupportedSerialKind("Cannot decode structures in AbstractTomlInlineElementDecoder");
      throw new KotlinNothingValueException();
   }

   override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<? extends T>): T {
      return TomlDecoder.super.decodeSerializableValue(deserializer);
   }

   @ExperimentalSerializationApi
   override fun <T> decodeNullableSerializableValue(deserializer: DeserializationStrategy<? extends T>): T {
      return TomlDecoder.super.decodeNullableSerializableValue(deserializer);
   }
}
