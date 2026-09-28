package net.peanuuutz.tomlkt.internal.encoder

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModule
import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlEncoder
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.internal.TomlSerializationExceptionsKt

@SourceDebugExtension(["SMAP\nAbstractTomlEncoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractTomlEncoder.kt\nnet/peanuuutz/tomlkt/internal/encoder/AbstractTomlInlineElementEncoder\n+ 2 AbstractTomlEncoder.kt\nnet/peanuuutz/tomlkt/internal/encoder/AbstractTomlEncoderKt\n*L\n1#1,302:1\n228#2,4:303\n*S KotlinDebug\n*F\n+ 1 AbstractTomlEncoder.kt\nnet/peanuuutz/tomlkt/internal/encoder/AbstractTomlInlineElementEncoder\n*L\n289#1:303,4\n*E\n"])
internal abstract class AbstractTomlInlineElementEncoder<E extends TomlCompositeEncoder> : TomlEncoder {
   protected final val parentDescriptor: SerialDescriptor
   protected final val elementIndex: Int
   protected final val delegate: Any

   public final val toml: Toml
      public final get() {
         return this.delegate.getToml();
      }


   public final val serializersModule: SerializersModule
      public final get() {
         return this.delegate.getSerializersModule();
      }


   open fun AbstractTomlInlineElementEncoder(parentDescriptor: SerialDescriptor, elementIndex: Int, delegate: E) {
      this.parentDescriptor = parentDescriptor;
      this.elementIndex = elementIndex;
      this.delegate = (E)delegate;
   }

   public override fun encodeBoolean(value: Boolean) {
      this.delegate.encodeBooleanElement(this.parentDescriptor, this.elementIndex, value);
   }

   public override fun encodeFloat(value: Float) {
      this.delegate.encodeFloatElement(this.parentDescriptor, this.elementIndex, value);
   }

   public override fun encodeDouble(value: Double) {
      this.delegate.encodeDoubleElement(this.parentDescriptor, this.elementIndex, value);
   }

   public override fun encodeChar(value: Char) {
      this.delegate.encodeCharElement(this.parentDescriptor, this.elementIndex, value);
   }

   public override fun encodeString(value: String) {
      this.delegate.encodeStringElement(this.parentDescriptor, this.elementIndex, value);
   }

   public override fun encodeNull() {
      this.delegate.encodeSerializableElement(this.parentDescriptor, this.elementIndex, TomlNull.INSTANCE.serializer(), TomlNull.INSTANCE);
   }

   public override fun encodeTomlElement(value: TomlElement) {
      this.delegate.encodeSerializableElement(this.parentDescriptor, this.elementIndex, TomlElement.Companion.serializer(), value);
   }

   public override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
      val `$this$encodeElement$iv`: TomlCompositeEncoder = this.delegate;
      val `descriptor$iv`: SerialDescriptor = this.parentDescriptor;
      val `index$iv`: Int = this.elementIndex;
      this.delegate.beginElement(this.parentDescriptor, this.elementIndex);
      this.delegate.encodeEnum(enumDescriptor, index);
      `$this$encodeElement$iv`.endElement(`descriptor$iv`, `index$iv`);
   }

   public override fun encodeInline(descriptor: SerialDescriptor): Encoder {
      return this;
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
      TomlSerializationExceptionsKt.throwUnsupportedSerialKind("Cannot encode structures in AbstractTomlInlineElementEncoder");
      throw new KotlinNothingValueException();
   }

   @ExperimentalSerializationApi
   override fun encodeNotNullMark() {
      TomlEncoder.super.encodeNotNullMark();
   }

   override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
      return TomlEncoder.super.beginCollection(descriptor, collectionSize);
   }

   override fun <T> encodeSerializableValue(serializer: SerializationStrategy<? super T>, value: T) {
      TomlEncoder.super.encodeSerializableValue(serializer, value);
   }

   @ExperimentalSerializationApi
   override fun <T> encodeNullableSerializableValue(serializer: SerializationStrategy<? super T>, value: T?) {
      TomlEncoder.super.encodeNullableSerializableValue(serializer, value);
   }
}
