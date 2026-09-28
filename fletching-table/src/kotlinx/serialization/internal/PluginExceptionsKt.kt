package kotlinx.serialization.internal

import java.util.ArrayList
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.descriptors.SerialDescriptor

@InternalSerializationApi
public fun throwMissingFieldException(seen: Int, goldenMask: Int, descriptor: SerialDescriptor) {
   val missingFields: java.util.List = new ArrayList();
   var missingFieldsBits: Int = goldenMask and seen.inv();

   for (int i = 0; i < 32; i++) {
      if ((missingFieldsBits and 1) != 0) {
         missingFields.add(descriptor.getElementName(i));
      }

      missingFieldsBits >>>= 1;
   }

   throw new MissingFieldException(missingFields, descriptor.getSerialName());
}

@InternalSerializationApi
public fun throwArrayMissingFieldException(seenArray: IntArray, goldenMaskArray: IntArray, descriptor: SerialDescriptor) {
   val missingFields: java.util.List = new ArrayList();
   var maskSlot: Int = 0;

   for (int var5 = goldenMaskArray.length; maskSlot < var5; maskSlot++) {
      var missingFieldsBits: Int = goldenMaskArray[maskSlot] and seenArray[maskSlot].inv();
      if ((goldenMaskArray[maskSlot] and seenArray[maskSlot].inv()) != 0) {
         for (int i = 0; i < 32; i++) {
            if ((missingFieldsBits and 1) != 0) {
               missingFields.add(descriptor.getElementName(maskSlot * 32 + i));
            }

            missingFieldsBits >>>= 1;
         }
      }
   }

   throw new MissingFieldException(missingFields, descriptor.getSerialName());
}
