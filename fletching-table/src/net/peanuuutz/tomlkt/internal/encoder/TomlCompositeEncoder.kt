package net.peanuuutz.tomlkt.internal.encoder

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeEncoder
import net.peanuuutz.tomlkt.TomlEncoder
import net.peanuuutz.tomlkt.TomlNull

@SourceDebugExtension(["SMAP\nAbstractTomlEncoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractTomlEncoder.kt\nnet/peanuuutz/tomlkt/internal/encoder/TomlCompositeEncoder\n+ 2 AbstractTomlEncoder.kt\nnet/peanuuutz/tomlkt/internal/encoder/AbstractTomlEncoderKt\n*L\n1#1,302:1\n228#2,4:303\n228#2,4:307\n228#2,4:311\n228#2,4:315\n228#2,4:319\n228#2,4:323\n228#2,4:327\n228#2,4:331\n228#2,4:335\n228#2,4:339\n*S KotlinDebug\n*F\n+ 1 AbstractTomlEncoder.kt\nnet/peanuuutz/tomlkt/internal/encoder/TomlCompositeEncoder\n*L\n113#1:303,4\n119#1:307,4\n125#1:311,4\n131#1:315,4\n137#1:319,4\n143#1:323,4\n149#1:327,4\n155#1:331,4\n161#1:335,4\n215#1:339,4\n*E\n"])
internal interface TomlCompositeEncoder : TomlEncoder, CompositeEncoder {
   public abstract fun encodeDiscriminatorElement(discriminator: String, serialName: String, isEmptyStructure: Boolean) {
   }

   public abstract fun beginElement(descriptor: SerialDescriptor, index: Int) {
   }

   public abstract fun endElement(descriptor: SerialDescriptor, index: Int) {
   }

   public override fun encodeBooleanElement(descriptor: SerialDescriptor, index: Int, value: Boolean) {
      this.beginElement(descriptor, index);
      this.encodeBoolean(value);
      this.endElement(descriptor, index);
   }

   public override fun encodeByteElement(descriptor: SerialDescriptor, index: Int, value: Byte) {
      this.beginElement(descriptor, index);
      this.encodeByte(value);
      this.endElement(descriptor, index);
   }

   public override fun encodeShortElement(descriptor: SerialDescriptor, index: Int, value: Short) {
      this.beginElement(descriptor, index);
      this.encodeShort(value);
      this.endElement(descriptor, index);
   }

   public override fun encodeIntElement(descriptor: SerialDescriptor, index: Int, value: Int) {
      this.beginElement(descriptor, index);
      this.encodeInt(value);
      this.endElement(descriptor, index);
   }

   public override fun encodeLongElement(descriptor: SerialDescriptor, index: Int, value: Long) {
      this.beginElement(descriptor, index);
      this.encodeLong(value);
      this.endElement(descriptor, index);
   }

   public override fun encodeFloatElement(descriptor: SerialDescriptor, index: Int, value: Float) {
      this.beginElement(descriptor, index);
      this.encodeFloat(value);
      this.endElement(descriptor, index);
   }

   public override fun encodeDoubleElement(descriptor: SerialDescriptor, index: Int, value: Double) {
      this.beginElement(descriptor, index);
      this.encodeDouble(value);
      this.endElement(descriptor, index);
   }

   public override fun encodeCharElement(descriptor: SerialDescriptor, index: Int, value: Char) {
      this.beginElement(descriptor, index);
      this.encodeChar(value);
      this.endElement(descriptor, index);
   }

   public override fun encodeStringElement(descriptor: SerialDescriptor, index: Int, value: String) {
      this.beginElement(descriptor, index);
      this.encodeString(value);
      this.endElement(descriptor, index);
   }

   public override fun <T : Any> encodeNullableSerializableElement(descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<T>, value: T?) {
      if (value == null) {
         this.encodeSerializableElement(descriptor, index, TomlNull.INSTANCE.serializer(), TomlNull.INSTANCE);
      } else if (value is java.lang.Boolean) {
         this.encodeBooleanElement(descriptor, index, value as java.lang.Boolean);
      } else if (value is java.lang.Byte) {
         this.encodeByteElement(descriptor, index, (value as java.lang.Number).byteValue());
      } else if (value is java.lang.Short) {
         this.encodeShortElement(descriptor, index, (value as java.lang.Number).shortValue());
      } else if (value is Int) {
         this.encodeIntElement(descriptor, index, (value as java.lang.Number).intValue());
      } else if (value is java.lang.Long) {
         this.encodeLongElement(descriptor, index, (value as java.lang.Number).longValue());
      } else if (value is java.lang.Float) {
         this.encodeFloatElement(descriptor, index, (value as java.lang.Number).floatValue());
      } else if (value is java.lang.Double) {
         this.encodeDoubleElement(descriptor, index, (value as java.lang.Number).doubleValue());
      } else if (value is Character) {
         this.encodeCharElement(descriptor, index, value as Character);
      } else if (value is java.lang.String) {
         this.encodeStringElement(descriptor, index, value as java.lang.String);
      } else {
         this.encodeSerializableElement(descriptor, index, serializer, value);
      }
   }

   public override fun <T> encodeSerializableElement(descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<T>, value: T) {
      this.beginElement(descriptor, index);
      this.encodeSerializableValue(serializer, value);
      this.endElement(descriptor, index);
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun encodeBooleanElement(`$this`: TomlCompositeEncoder, descriptor: SerialDescriptor, index: Int, value: Boolean) {
         TomlCompositeEncoder.access$encodeBooleanElement$jd(`$this`, descriptor, index, value);
      }

      @Deprecated
      @JvmStatic
      fun encodeByteElement(`$this`: TomlCompositeEncoder, descriptor: SerialDescriptor, index: Int, value: Byte) {
         TomlCompositeEncoder.access$encodeByteElement$jd(`$this`, descriptor, index, value);
      }

      @Deprecated
      @JvmStatic
      fun encodeShortElement(`$this`: TomlCompositeEncoder, descriptor: SerialDescriptor, index: Int, value: Short) {
         TomlCompositeEncoder.access$encodeShortElement$jd(`$this`, descriptor, index, value);
      }

      @Deprecated
      @JvmStatic
      fun encodeIntElement(`$this`: TomlCompositeEncoder, descriptor: SerialDescriptor, index: Int, value: Int) {
         TomlCompositeEncoder.access$encodeIntElement$jd(`$this`, descriptor, index, value);
      }

      @Deprecated
      @JvmStatic
      fun encodeLongElement(`$this`: TomlCompositeEncoder, descriptor: SerialDescriptor, index: Int, value: Long) {
         TomlCompositeEncoder.access$encodeLongElement$jd(`$this`, descriptor, index, value);
      }

      @Deprecated
      @JvmStatic
      fun encodeFloatElement(`$this`: TomlCompositeEncoder, descriptor: SerialDescriptor, index: Int, value: Float) {
         TomlCompositeEncoder.access$encodeFloatElement$jd(`$this`, descriptor, index, value);
      }

      @Deprecated
      @JvmStatic
      fun encodeDoubleElement(`$this`: TomlCompositeEncoder, descriptor: SerialDescriptor, index: Int, value: Double) {
         TomlCompositeEncoder.access$encodeDoubleElement$jd(`$this`, descriptor, index, value);
      }

      @Deprecated
      @JvmStatic
      fun encodeCharElement(`$this`: TomlCompositeEncoder, descriptor: SerialDescriptor, index: Int, value: Char) {
         TomlCompositeEncoder.access$encodeCharElement$jd(`$this`, descriptor, index, value);
      }

      @Deprecated
      @JvmStatic
      fun encodeStringElement(`$this`: TomlCompositeEncoder, descriptor: SerialDescriptor, index: Int, value: java.lang.String) {
         TomlCompositeEncoder.access$encodeStringElement$jd(`$this`, descriptor, index, value);
      }

      @Deprecated
      @JvmStatic
      fun <T> encodeNullableSerializableElement(
         `$this`: TomlCompositeEncoder, descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<? super T>, value: T?
      ) {
         TomlCompositeEncoder.access$encodeNullableSerializableElement$jd(`$this`, descriptor, index, serializer, value);
      }

      @Deprecated
      @JvmStatic
      fun <T> encodeSerializableElement(
         `$this`: TomlCompositeEncoder, descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<? super T>, value: T
      ) {
         TomlCompositeEncoder.access$encodeSerializableElement$jd(`$this`, descriptor, index, serializer, value);
      }

      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun encodeNotNullMark(`$this`: TomlCompositeEncoder) {
         TomlCompositeEncoder.access$encodeNotNullMark$jd(`$this`);
      }

      @Deprecated
      @JvmStatic
      fun beginCollection(`$this`: TomlCompositeEncoder, descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
         return TomlCompositeEncoder.access$beginCollection$jd(`$this`, descriptor, collectionSize);
      }

      @Deprecated
      @JvmStatic
      fun <T> encodeSerializableValue(`$this`: TomlCompositeEncoder, serializer: SerializationStrategy<? super T>, value: T) {
         TomlCompositeEncoder.access$encodeSerializableValue$jd(`$this`, serializer, value);
      }

      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun <T> encodeNullableSerializableValue(`$this`: TomlCompositeEncoder, serializer: SerializationStrategy<? super T>, value: T?) {
         TomlCompositeEncoder.access$encodeNullableSerializableValue$jd(`$this`, serializer, value);
      }

      @ExperimentalSerializationApi
      @Deprecated
      @JvmStatic
      fun shouldEncodeElementDefault(`$this`: TomlCompositeEncoder, descriptor: SerialDescriptor, index: Int): Boolean {
         return TomlCompositeEncoder.access$shouldEncodeElementDefault$jd(`$this`, descriptor, index);
      }
   }
}
