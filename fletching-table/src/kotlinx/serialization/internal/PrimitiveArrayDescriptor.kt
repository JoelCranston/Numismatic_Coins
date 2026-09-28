package kotlinx.serialization.internal

import kotlinx.serialization.descriptors.SerialDescriptor

internal class PrimitiveArrayDescriptor internal constructor(primitive: SerialDescriptor) : ListLikeDescriptor(primitive) {
   public open val serialName: String

   init {
      this.serialName = "${primitive.getSerialName()}Array";
   }
}
