package net.peanuuutz.tomlkt.internal.decoder

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import net.peanuuutz.tomlkt.TomlDecoder

@SourceDebugExtension(["SMAP\nAbstractTomlDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractTomlDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/TomlCompositeDecoder\n+ 2 AbstractTomlDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/AbstractTomlDecoderKt\n*L\n1#1,295:1\n168#2,4:296\n168#2,4:300\n168#2,4:304\n168#2,4:308\n168#2,4:312\n168#2,4:316\n168#2,4:320\n168#2,4:324\n168#2,4:328\n168#2,4:332\n*S KotlinDebug\n*F\n+ 1 AbstractTomlDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/TomlCompositeDecoder\n*L\n98#1:296,4\n104#1:300,4\n110#1:304,4\n116#1:308,4\n122#1:312,4\n128#1:316,4\n134#1:320,4\n140#1:324,4\n146#1:328,4\n157#1:332,4\n*E\n"])
internal interface TomlCompositeDecoder : TomlDecoder, CompositeDecoder {
   public abstract fun beginElement(descriptor: SerialDescriptor, index: Int) {
   }

   public abstract fun endElement(descriptor: SerialDescriptor, index: Int) {
   }

   public override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean {
      this.beginElement(descriptor, index);
      val `value$iv`: Boolean = this.decodeBoolean();
      this.endElement(descriptor, index);
      return `value$iv`;
   }

   public override fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte {
      this.beginElement(descriptor, index);
      val `value$iv`: Byte = this.decodeByte();
      this.endElement(descriptor, index);
      return `value$iv`;
   }

   public override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
      this.beginElement(descriptor, index);
      val `value$iv`: Short = this.decodeShort();
      this.endElement(descriptor, index);
      return `value$iv`;
   }

   public override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
      this.beginElement(descriptor, index);
      val `value$iv`: Int = this.decodeInt();
      this.endElement(descriptor, index);
      return `value$iv`;
   }

   public override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
      this.beginElement(descriptor, index);
      val `value$iv`: Long = this.decodeLong();
      this.endElement(descriptor, index);
      return `value$iv`;
   }

   public override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float {
      this.beginElement(descriptor, index);
      val `value$iv`: Float = this.decodeFloat();
      this.endElement(descriptor, index);
      return `value$iv`;
   }

   public override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double {
      this.beginElement(descriptor, index);
      val `value$iv`: Double = this.decodeDouble();
      this.endElement(descriptor, index);
      return `value$iv`;
   }

   public override fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char {
      this.beginElement(descriptor, index);
      val `value$iv`: Char = this.decodeChar();
      this.endElement(descriptor, index);
      return `value$iv`;
   }

   public override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
      this.beginElement(descriptor, index);
      val `value$iv`: Any = this.decodeString();
      this.endElement(descriptor, index);
      return (java.lang.String)`value$iv`;
   }

   public override fun <T> decodeSerializableElement(descriptor: SerialDescriptor, index: Int, deserializer: DeserializationStrategy<T>, previousValue: T?): T {
      this.beginElement(descriptor, index);
      val `value$iv`: Any = this.decodeSerializableValue(deserializer);
      this.endElement(descriptor, index);
      return (T)`value$iv`;
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun decodeBooleanElement(`$this`: TomlCompositeDecoder, descriptor: SerialDescriptor, index: Int): Boolean {
         return TomlCompositeDecoder.access$decodeBooleanElement$jd(`$this`, descriptor, index);
      }

      @Deprecated
      @JvmStatic
      fun decodeByteElement(`$this`: TomlCompositeDecoder, descriptor: SerialDescriptor, index: Int): Byte {
         return TomlCompositeDecoder.access$decodeByteElement$jd(`$this`, descriptor, index);
      }

      @Deprecated
      @JvmStatic
      fun decodeShortElement(`$this`: TomlCompositeDecoder, descriptor: SerialDescriptor, index: Int): Short {
         return TomlCompositeDecoder.access$decodeShortElement$jd(`$this`, descriptor, index);
      }

      @Deprecated
      @JvmStatic
      fun decodeIntElement(`$this`: TomlCompositeDecoder, descriptor: SerialDescriptor, index: Int): Int {
         return TomlCompositeDecoder.access$decodeIntElement$jd(`$this`, descriptor, index);
      }

      @Deprecated
      @JvmStatic
      fun decodeLongElement(`$this`: TomlCompositeDecoder, descriptor: SerialDescriptor, index: Int): Long {
         return TomlCompositeDecoder.access$decodeLongElement$jd(`$this`, descriptor, index);
      }

      @Deprecated
      @JvmStatic
      fun decodeFloatElement(`$this`: TomlCompositeDecoder, descriptor: SerialDescriptor, index: Int): Float {
         return TomlCompositeDecoder.access$decodeFloatElement$jd(`$this`, descriptor, index);
      }

      @Deprecated
      @JvmStatic
      fun decodeDoubleElement(`$this`: TomlCompositeDecoder, descriptor: SerialDescriptor, index: Int): Double {
         return TomlCompositeDecoder.access$decodeDoubleElement$jd(`$this`, descriptor, index);
      }

      @Deprecated
      @JvmStatic
      fun decodeCharElement(`$this`: TomlCompositeDecoder, descriptor: SerialDescriptor, index: Int): Char {
         return TomlCompositeDecoder.access$decodeCharElement$jd(`$this`, descriptor, index);
      }

      @Deprecated
      @JvmStatic
      fun decodeStringElement(`$this`: TomlCompositeDecoder, descriptor: SerialDescriptor, index: Int): java.lang.String {
         return TomlCompositeDecoder.access$decodeStringElement$jd(`$this`, descriptor, index);
      }

      @Deprecated
      @JvmStatic
      fun <T> decodeSerializableElement(
         `$this`: TomlCompositeDecoder, descriptor: SerialDescriptor, index: Int, deserializer: DeserializationStrategy<? extends T>, previousValue: T?
      ): T {
         return (T)TomlCompositeDecoder.access$decodeSerializableElement$jd(`$this`, descriptor, index, deserializer, previousValue);
      }

      @Deprecated
      @JvmStatic
      fun <T> decodeSerializableValue(`$this`: TomlCompositeDecoder, deserializer: DeserializationStrategy<? extends T>): T {
         return (T)TomlCompositeDecoder.access$decodeSerializableValue$jd(`$this`, deserializer);
      }

      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun <T> decodeNullableSerializableValue(`$this`: TomlCompositeDecoder, deserializer: DeserializationStrategy<? extends T>): T {
         return (T)TomlCompositeDecoder.access$decodeNullableSerializableValue$jd(`$this`, deserializer);
      }

      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun decodeSequentially(`$this`: TomlCompositeDecoder): Boolean {
         return TomlCompositeDecoder.access$decodeSequentially$jd(`$this`);
      }

      @Deprecated
      @JvmStatic
      fun decodeCollectionSize(`$this`: TomlCompositeDecoder, descriptor: SerialDescriptor): Int {
         return TomlCompositeDecoder.access$decodeCollectionSize$jd(`$this`, descriptor);
      }
   }
}
