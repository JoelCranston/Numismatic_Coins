package kotlinx.serialization.internal

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuildersKt

@InternalSerializationApi
@SourceDebugExtension(["SMAP\nTagged.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Tagged.kt\nkotlinx/serialization/internal/TaggedDecoder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n*L\n1#1,342:1\n1#2:343\n271#3,2:344\n*S KotlinDebug\n*F\n+ 1 Tagged.kt\nkotlinx/serialization/internal/TaggedDecoder\n*L\n287#1:344,2\n*E\n"])
public abstract class TaggedDecoder<Tag> : Decoder, CompositeDecoder {
   public open val serializersModule: SerializersModule
      public open get() {
         return SerializersModuleBuildersKt.EmptySerializersModule();
      }


   internal final val tagStack: ArrayList<Any> = new ArrayList()

   protected final val currentTag: Any
      protected final get() {
         return CollectionsKt.last(this.tagStack);
      }


   protected final val currentTagOrNull: Any?
      protected final get() {
         return CollectionsKt.lastOrNull(this.tagStack);
      }


   private final var flag: Boolean

   protected abstract fun SerialDescriptor.getTag(index: Int): Any {
   }

   protected open fun decodeTaggedValue(tag: Any): Any {
      throw new SerializationException("${this.getClass()::class} can't retrieve untyped values");
   }

   protected open fun decodeTaggedNotNullMark(tag: Any): Boolean {
      return true;
   }

   protected open fun decodeTaggedNull(tag: Any): Nothing? {
      return null;
   }

   protected open fun decodeTaggedBoolean(tag: Any): Boolean {
      val var10000: Any = this.decodeTaggedValue((Tag)tag);
      return var10000 as java.lang.Boolean;
   }

   protected open fun decodeTaggedByte(tag: Any): Byte {
      val var10000: Any = this.decodeTaggedValue((Tag)tag);
      return var10000 as java.lang.Byte;
   }

   protected open fun decodeTaggedShort(tag: Any): Short {
      val var10000: Any = this.decodeTaggedValue((Tag)tag);
      return var10000 as java.lang.Short;
   }

   protected open fun decodeTaggedInt(tag: Any): Int {
      val var10000: Any = this.decodeTaggedValue((Tag)tag);
      return var10000 as Int;
   }

   protected open fun decodeTaggedLong(tag: Any): Long {
      val var10000: Any = this.decodeTaggedValue((Tag)tag);
      return var10000 as java.lang.Long;
   }

   protected open fun decodeTaggedFloat(tag: Any): Float {
      val var10000: Any = this.decodeTaggedValue((Tag)tag);
      return var10000 as java.lang.Float;
   }

   protected open fun decodeTaggedDouble(tag: Any): Double {
      val var10000: Any = this.decodeTaggedValue((Tag)tag);
      return var10000 as java.lang.Double;
   }

   protected open fun decodeTaggedChar(tag: Any): Char {
      val var10000: Any = this.decodeTaggedValue((Tag)tag);
      return var10000 as Character;
   }

   protected open fun decodeTaggedString(tag: Any): String {
      val var10000: Any = this.decodeTaggedValue((Tag)tag);
      return var10000 as java.lang.String;
   }

   protected open fun decodeTaggedEnum(tag: Any, enumDescriptor: SerialDescriptor): Int {
      val var10000: Any = this.decodeTaggedValue((Tag)tag);
      return var10000 as Int;
   }

   protected open fun decodeTaggedInline(tag: Any, inlineDescriptor: SerialDescriptor): Decoder {
      this.pushTag((Tag)tag);
      return this;
   }

   protected open fun <T : Any?> decodeSerializableValue(deserializer: DeserializationStrategy<T>, previousValue: T?): T {
      return (T)this.decodeSerializableValue(deserializer);
   }

   public override fun decodeInline(descriptor: SerialDescriptor): Decoder {
      return this.decodeTaggedInline(this.popTag(), descriptor);
   }

   public override fun decodeNotNullMark(): Boolean {
      val var10000: Any = this.getCurrentTagOrNull();
      return var10000 != null && this.decodeTaggedNotNullMark(var10000);
   }

   public override fun decodeNull(): Nothing? {
      return null;
   }

   public override fun decodeBoolean(): Boolean {
      return this.decodeTaggedBoolean(this.popTag());
   }

   public override fun decodeByte(): Byte {
      return this.decodeTaggedByte(this.popTag());
   }

   public override fun decodeShort(): Short {
      return this.decodeTaggedShort(this.popTag());
   }

   public override fun decodeInt(): Int {
      return this.decodeTaggedInt(this.popTag());
   }

   public override fun decodeLong(): Long {
      return this.decodeTaggedLong(this.popTag());
   }

   public override fun decodeFloat(): Float {
      return this.decodeTaggedFloat(this.popTag());
   }

   public override fun decodeDouble(): Double {
      return this.decodeTaggedDouble(this.popTag());
   }

   public override fun decodeChar(): Char {
      return this.decodeTaggedChar(this.popTag());
   }

   public override fun decodeString(): String {
      return this.decodeTaggedString(this.popTag());
   }

   public override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
      return this.decodeTaggedEnum(this.popTag(), enumDescriptor);
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      return this;
   }

   public override fun endStructure(descriptor: SerialDescriptor) {
   }

   public override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean {
      return this.decodeTaggedBoolean(this.getTag(descriptor, index));
   }

   public override fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte {
      return this.decodeTaggedByte(this.getTag(descriptor, index));
   }

   public override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
      return this.decodeTaggedShort(this.getTag(descriptor, index));
   }

   public override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
      return this.decodeTaggedInt(this.getTag(descriptor, index));
   }

   public override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
      return this.decodeTaggedLong(this.getTag(descriptor, index));
   }

   public override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float {
      return this.decodeTaggedFloat(this.getTag(descriptor, index));
   }

   public override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double {
      return this.decodeTaggedDouble(this.getTag(descriptor, index));
   }

   public override fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char {
      return this.decodeTaggedChar(this.getTag(descriptor, index));
   }

   public override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
      return this.decodeTaggedString(this.getTag(descriptor, index));
   }

   public override fun decodeInlineElement(descriptor: SerialDescriptor, index: Int): Decoder {
      return this.decodeTaggedInline(this.getTag(descriptor, index), descriptor.getElementDescriptor(index));
   }

   public override fun <T : Any?> decodeSerializableElement(
      descriptor: SerialDescriptor,
      index: Int,
      deserializer: DeserializationStrategy<T>,
      previousValue: T?
   ): T {
      return (T)this.tagBlock(this.getTag(descriptor, index), TaggedDecoder::decodeSerializableElement$lambda$1);
   }

   public override fun <T : Any> decodeNullableSerializableElement(
      descriptor: SerialDescriptor,
      index: Int,
      deserializer: DeserializationStrategy<T?>,
      previousValue: T?
   ): T? {
      return (T)this.tagBlock(this.getTag(descriptor, index), TaggedDecoder::decodeNullableSerializableElement$lambda$3);
   }

   private fun <E> tagBlock(tag: Any, block: () -> E): E {
      this.pushTag((Tag)tag);
      val r: Any = block.invoke();
      if (!this.flag) {
         this.popTag();
      }

      this.flag = false;
      return (E)r;
   }

   protected fun pushTag(name: Any) {
      this.tagStack.add((Tag)name);
   }

   protected fun copyTagsTo(other: TaggedDecoder<Any>) {
      other.tagStack.addAll(this.tagStack);
   }

   protected fun popTag(): Any {
      val r: Any = this.tagStack.remove(CollectionsKt.getLastIndex(this.tagStack));
      this.flag = true;
      return (Tag)r;
   }

   override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<? extends T>): T {
      return Decoder.super.decodeSerializableValue(deserializer);
   }

   @ExperimentalSerializationApi
   override fun <T> decodeNullableSerializableValue(deserializer: DeserializationStrategy<? extends T>): T {
      return Decoder.super.decodeNullableSerializableValue(deserializer);
   }

   @ExperimentalSerializationApi
   override fun decodeSequentially(): Boolean {
      return CompositeDecoder.super.decodeSequentially();
   }

   override fun decodeCollectionSize(descriptor: SerialDescriptor): Int {
      return CompositeDecoder.super.decodeCollectionSize(descriptor);
   }

   @JvmStatic
   fun `decodeSerializableElement$lambda$1`(`this$0`: TaggedDecoder, `$deserializer`: DeserializationStrategy, `$previousValue`: Any): Any {
      return `this$0`.decodeSerializableValue(`$deserializer`, `$previousValue`);
   }

   @JvmStatic
   fun `decodeNullableSerializableElement$lambda$3`(`this$0`: TaggedDecoder, `$deserializer`: DeserializationStrategy, `$previousValue`: Any): Any {
      return if (!`$deserializer`.getDescriptor().isNullable() && !`this$0`.decodeNotNullMark())
         `this$0`.decodeNull()
         else
         `this$0`.decodeSerializableValue(`$deserializer`, `$previousValue`);
   }
}
