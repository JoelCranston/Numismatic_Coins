package net.peanuuutz.tomlkt.internal

import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.modules.SerializersModule

private object DummyDecoder : AbstractDecoder {
   public open lateinit var serializersModule: SerializersModule
      internal final set

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      throw new IllegalStateException("DummyDecoder should only be used to retrieve serializers via serializersModule".toString());
   }
}
