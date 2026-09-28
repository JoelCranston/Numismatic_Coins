package net.peanuuutz.tomlkt.internal.decoder

import kotlinx.serialization.descriptors.SerialDescriptor
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlElement

private class TomlElementArrayDecoder(delegate: AbstractTomlElementDecoder, array: TomlArray) : AbstractTomlElementCompositeDecoder(delegate) {
   private final val array: TomlArray
   private final var currentElementIndex: Int

   public open lateinit var element: TomlElement
      internal final set

   init {
      this.array = array;
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      return if (this.currentElementIndex != this.array.size()) this.currentElementIndex else -1;
   }

   public override fun beginElement(descriptor: SerialDescriptor, index: Int) {
      this.setElement(this.array.get(this.currentElementIndex));
   }

   public override fun endElement(descriptor: SerialDescriptor, index: Int) {
      val var3: Int = this.currentElementIndex++;
   }

   public override fun decodeCollectionSize(descriptor: SerialDescriptor): Int {
      return this.array.size();
   }

   public override fun decodeSequentially(): Boolean {
      return true;
   }
}
