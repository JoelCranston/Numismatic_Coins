package net.peanuuutz.tomlkt.internal.decoder

import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorKt
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.TomlTable
import net.peanuuutz.tomlkt.internal.TomlSerializationExceptionsKt

private class TomlElementClassDecoder(delegate: AbstractTomlElementDecoder, table: TomlTable, discriminator: String?) : AbstractTomlElementCompositeDecoder(
      delegate
   ) {
   private final val table: TomlTable
   private final val discriminator: String?
   private final var allowImplicitNull: Boolean
   private final var currentElementIndex: Int

   public open lateinit var element: TomlElement
      internal final set

   init {
      this.table = table;
      this.discriminator = discriminator;
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      while (this.currentElementIndex < descriptor.getElementsCount()) {
         val index: Int = this.currentElementIndex;
         val key: java.lang.String = descriptor.getElementName(this.currentElementIndex);
         this.allowImplicitNull = false;
         val var4: Int = this.currentElementIndex++;
         if (this.table.containsKey(key)) {
            val var10001: Any = this.table.get((Object)key);
            this.setElement(var10001 as TomlElement);
            return index;
         }

         if (this.allowImplicitNull(descriptor, index)) {
            this.setElement(TomlNull.INSTANCE);
            return index;
         }
      }

      return -1;
   }

   private fun allowImplicitNull(descriptor: SerialDescriptor, index: Int): Boolean {
      val allow: Boolean = !this.getToml().getConfig().getExplicitNulls()
         && !descriptor.isElementOptional(index)
         && descriptor.getElementDescriptor(index).isNullable();
      this.allowImplicitNull = allow;
      return allow;
   }

   public override fun beginElement(descriptor: SerialDescriptor, index: Int) {
   }

   public override fun endElement(descriptor: SerialDescriptor, index: Int) {
   }

   public override fun endStructure(descriptor: SerialDescriptor) {
      if (!this.getToml().getConfig().getIgnoreUnknownKeys()) {
         val expectedKeys: java.util.Set = CollectionsKt.toSet(SerialDescriptorKt.getElementNames(descriptor));

         for (java.lang.String key : this.table.keySet()) {
            if (!expectedKeys.contains(key) && !(key == this.discriminator)) {
               TomlSerializationExceptionsKt.throwUnknownKey(key);
               throw new KotlinNothingValueException();
            }
         }
      }
   }
}
