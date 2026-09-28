package kotlinx.serialization.internal

import kotlinx.serialization.descriptors.SerialDescriptor

internal class ArrayClassDesc(elementDesc: SerialDescriptor) : ListLikeDescriptor(elementDesc) {
   public open val serialName: String
      public open get() {
         return "kotlin.Array";
      }

}
