package net.peanuuutz.tomlkt.internal.decoder

import kotlinx.serialization.descriptors.SerialDescriptor
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlTable
import net.peanuuutz.tomlkt.internal.decoder.TomlElementMapDecoder.iterator.1

private class TomlElementMapDecoder(delegate: AbstractTomlElementDecoder, table: TomlTable) : AbstractTomlElementCompositeDecoder(delegate) {
   private final val table: TomlTable
   private final val iterator: Iterator<TomlElement>
   private final var currentElementIndex: Int

   public open lateinit var element: TomlElement
      internal final set

   init {
      this.table = table;
      this.iterator = SequencesKt.iterator(new 1(this, null));
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      return if (this.iterator.hasNext()) this.currentElementIndex else -1;
   }

   public override fun beginElement(descriptor: SerialDescriptor, index: Int) {
      this.setElement(this.iterator.next());
   }

   public override fun endElement(descriptor: SerialDescriptor, index: Int) {
      val var3: Int = this.currentElementIndex++;
   }

   public override fun decodeCollectionSize(descriptor: SerialDescriptor): Int {
      return this.table.size();
   }

   public override fun decodeSequentially(): Boolean {
      return true;
   }
}
