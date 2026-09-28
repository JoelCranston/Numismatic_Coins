package kotlinx.serialization.internal

import kotlinx.serialization.descriptors.SerialDescriptor

internal class LinkedHashSetClassDesc(elementDesc: SerialDescriptor) : ListLikeDescriptor(elementDesc) {
   public open val serialName: String
      public open get() {
         return "kotlin.collections.LinkedHashSet";
      }

}
