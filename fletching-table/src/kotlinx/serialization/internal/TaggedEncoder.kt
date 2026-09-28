package kotlinx.serialization.internal

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuildersKt

@InternalSerializationApi
@SourceDebugExtension(["SMAP\nTagged.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Tagged.kt\nkotlinx/serialization/internal/TaggedEncoder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,342:1\n1#2:343\n*E\n"])
public abstract class TaggedEncoder<Tag> : Encoder, CompositeEncoder {
   public open val serializersModule: SerializersModule
      public open get() {
         return SerializersModuleBuildersKt.EmptySerializersModule();
      }


   private final val tagStack: ArrayList<Any> = new ArrayList()

   protected final val currentTag: Any
      protected final get() {
         return CollectionsKt.last(this.tagStack);
      }


   protected final val currentTagOrNull: Any?
      protected final get() {
         return CollectionsKt.lastOrNull(this.tagStack);
      }


   protected abstract fun SerialDescriptor.getTag(index: Int): Any {
   }

   protected open fun encodeTaggedValue(tag: Any, value: Any) {
      throw new SerializationException("Non-serializable ${value.getClass()::class} is not supported by ${this.getClass()::class} encoder");
   }

   protected open fun encodeTaggedNonNullMark(tag: Any) {
   }

   protected open fun encodeTaggedNull(tag: Any) {
      throw new SerializationException("null is not supported");
   }

   protected open fun encodeTaggedInt(tag: Any, value: Int) {
      this.encodeTaggedValue((Tag)tag, value);
   }

   protected open fun encodeTaggedByte(tag: Any, value: Byte) {
      this.encodeTaggedValue((Tag)tag, value);
   }

   protected open fun encodeTaggedShort(tag: Any, value: Short) {
      this.encodeTaggedValue((Tag)tag, value);
   }

   protected open fun encodeTaggedLong(tag: Any, value: Long) {
      this.encodeTaggedValue((Tag)tag, value);
   }

   protected open fun encodeTaggedFloat(tag: Any, value: Float) {
      this.encodeTaggedValue((Tag)tag, value);
   }

   protected open fun encodeTaggedDouble(tag: Any, value: Double) {
      this.encodeTaggedValue((Tag)tag, value);
   }

   protected open fun encodeTaggedBoolean(tag: Any, value: Boolean) {
      this.encodeTaggedValue((Tag)tag, value);
   }

   protected open fun encodeTaggedChar(tag: Any, value: Char) {
      this.encodeTaggedValue((Tag)tag, value);
   }

   protected open fun encodeTaggedString(tag: Any, value: String) {
      this.encodeTaggedValue((Tag)tag, value);
   }

   protected open fun encodeTaggedEnum(tag: Any, enumDescriptor: SerialDescriptor, ordinal: Int) {
      this.encodeTaggedValue((Tag)tag, ordinal);
   }

   protected open fun encodeTaggedInline(tag: Any, inlineDescriptor: SerialDescriptor): Encoder {
      this.pushTag((Tag)tag);
      return this;
   }

   public override fun encodeInline(descriptor: SerialDescriptor): Encoder {
      return this.encodeTaggedInline(this.popTag(), descriptor);
   }

   private fun encodeElement(desc: SerialDescriptor, index: Int): Boolean {
      this.pushTag(this.getTag(desc, index));
      return true;
   }

   public override fun encodeNotNullMark() {
      this.encodeTaggedNonNullMark(this.getCurrentTag());
   }

   public override fun encodeNull() {
      this.encodeTaggedNull(this.popTag());
   }

   public override fun encodeBoolean(value: Boolean) {
      this.encodeTaggedBoolean(this.popTag(), value);
   }

   public override fun encodeByte(value: Byte) {
      this.encodeTaggedByte(this.popTag(), value);
   }

   public override fun encodeShort(value: Short) {
      this.encodeTaggedShort(this.popTag(), value);
   }

   public override fun encodeInt(value: Int) {
      this.encodeTaggedInt(this.popTag(), value);
   }

   public override fun encodeLong(value: Long) {
      this.encodeTaggedLong(this.popTag(), value);
   }

   public override fun encodeFloat(value: Float) {
      this.encodeTaggedFloat(this.popTag(), value);
   }

   public override fun encodeDouble(value: Double) {
      this.encodeTaggedDouble(this.popTag(), value);
   }

   public override fun encodeChar(value: Char) {
      this.encodeTaggedChar(this.popTag(), value);
   }

   public override fun encodeString(value: String) {
      this.encodeTaggedString(this.popTag(), value);
   }

   public override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
      this.encodeTaggedEnum(this.popTag(), enumDescriptor, index);
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
      return this;
   }

   public override fun endStructure(descriptor: SerialDescriptor) {
      if (!this.tagStack.isEmpty()) {
         this.popTag();
      }

      this.endEncode(descriptor);
   }

   protected open fun endEncode(descriptor: SerialDescriptor) {
   }

   public override fun encodeBooleanElement(descriptor: SerialDescriptor, index: Int, value: Boolean) {
      this.encodeTaggedBoolean(this.getTag(descriptor, index), value);
   }

   public override fun encodeByteElement(descriptor: SerialDescriptor, index: Int, value: Byte) {
      this.encodeTaggedByte(this.getTag(descriptor, index), value);
   }

   public override fun encodeShortElement(descriptor: SerialDescriptor, index: Int, value: Short) {
      this.encodeTaggedShort(this.getTag(descriptor, index), value);
   }

   public override fun encodeIntElement(descriptor: SerialDescriptor, index: Int, value: Int) {
      this.encodeTaggedInt(this.getTag(descriptor, index), value);
   }

   public override fun encodeLongElement(descriptor: SerialDescriptor, index: Int, value: Long) {
      this.encodeTaggedLong(this.getTag(descriptor, index), value);
   }

   public override fun encodeFloatElement(descriptor: SerialDescriptor, index: Int, value: Float) {
      this.encodeTaggedFloat(this.getTag(descriptor, index), value);
   }

   public override fun encodeDoubleElement(descriptor: SerialDescriptor, index: Int, value: Double) {
      this.encodeTaggedDouble(this.getTag(descriptor, index), value);
   }

   public override fun encodeCharElement(descriptor: SerialDescriptor, index: Int, value: Char) {
      this.encodeTaggedChar(this.getTag(descriptor, index), value);
   }

   public override fun encodeStringElement(descriptor: SerialDescriptor, index: Int, value: String) {
      this.encodeTaggedString(this.getTag(descriptor, index), value);
   }

   public override fun encodeInlineElement(descriptor: SerialDescriptor, index: Int): Encoder {
      return this.encodeTaggedInline(this.getTag(descriptor, index), descriptor.getElementDescriptor(index));
   }

   public override fun <T : Any?> encodeSerializableElement(descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<T>, value: T) {
      if (this.encodeElement(descriptor, index)) {
         this.encodeSerializableValue(serializer, value);
      }
   }

   public override fun <T : Any> encodeNullableSerializableElement(descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<T>, value: T?) {
      if (this.encodeElement(descriptor, index)) {
         this.encodeNullableSerializableValue(serializer, value);
      }
   }

   protected fun pushTag(name: Any) {
      this.tagStack.add((Tag)name);
   }

   protected fun popTag(): Any {
      if (!this.tagStack.isEmpty()) {
         return this.tagStack.remove(CollectionsKt.getLastIndex(this.tagStack));
      } else {
         throw new SerializationException("No tag in stack for requested element");
      }
   }

   override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
      return Encoder.super.beginCollection(descriptor, collectionSize);
   }

   override fun <T> encodeSerializableValue(serializer: SerializationStrategy<? super T>, value: T) {
      Encoder.super.encodeSerializableValue(serializer, value);
   }

   @ExperimentalSerializationApi
   override fun <T> encodeNullableSerializableValue(serializer: SerializationStrategy<? super T>, value: T?) {
      Encoder.super.encodeNullableSerializableValue(serializer, value);
   }

   @ExperimentalSerializationApi
   override fun shouldEncodeElementDefault(descriptor: SerialDescriptor, index: Int): Boolean {
      return CompositeEncoder.super.shouldEncodeElementDefault(descriptor, index);
   }
}
