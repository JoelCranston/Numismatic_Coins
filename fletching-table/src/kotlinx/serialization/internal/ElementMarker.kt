package kotlinx.serialization.internal

import kotlinx.serialization.descriptors.SerialDescriptor

@CoreFriendModuleApi
public class ElementMarker(descriptor: SerialDescriptor, readIfAbsent: (SerialDescriptor, Int) -> Boolean) {
   private final val descriptor: SerialDescriptor
   private final val readIfAbsent: (SerialDescriptor, Int) -> Boolean
   private final var lowerMarks: Long
   private final val highMarksArray: LongArray

   init {
      this.descriptor = descriptor;
      this.readIfAbsent = readIfAbsent;
      val elementsCount: Int = this.descriptor.getElementsCount();
      if (elementsCount <= 64) {
         this.lowerMarks = if (elementsCount == 64) 0L else -1L shl elementsCount;
         this.highMarksArray = EMPTY_HIGH_MARKS;
      } else {
         this.lowerMarks = 0L;
         this.highMarksArray = this.prepareHighMarksArray(elementsCount);
      }
   }

   public fun mark(index: Int) {
      if (index < 64) {
         this.lowerMarks |= 1L shl index;
      } else {
         this.markHigh(index);
      }
   }

   public fun nextUnmarkedIndex(): Int {
      while (this.lowerMarks != -1L) {
         val index: Int = java.lang.Long.numberOfTrailingZeros(this.lowerMarks.inv());
         this.lowerMarks |= 1L shl index;
         if (this.readIfAbsent.invoke(this.descriptor, index)) {
            return index;
         }
      }

      return if (this.descriptor.getElementsCount() > 64) this.nextUnmarkedHighIndex() else -1;
   }

   private fun prepareHighMarksArray(elementsCount: Int): LongArray {
      val slotsCount: Int = elementsCount - 1 ushr 6;
      val elementsInLastSlot: Int = elementsCount and 63;
      val highMarks: LongArray = new long[slotsCount];
      if (elementsInLastSlot != 0) {
         highMarks[ArraysKt.getLastIndex(highMarks)] = -1L shl elementsCount;
      }

      return highMarks;
   }

   private fun markHigh(index: Int) {
      this.highMarksArray[(index ushr 6) - 1] = this.highMarksArray[(index ushr 6) - 1] or 1L shl (index and 63);
   }

   private fun nextUnmarkedHighIndex(): Int {
      var slot: Int = 0;

      for (int var2 = this.highMarksArray.length; slot < var2; slot++) {
         val slotOffset: Int = (slot + 1) * 64;
         var slotMarks: Long = this.highMarksArray[slot];

         while (slotMarks != -1L) {
            val indexInSlot: Int = java.lang.Long.numberOfTrailingZeros(slotMarks.inv());
            slotMarks |= 1L shl indexInSlot;
            val index: Int = slotOffset + indexInSlot;
            if (this.readIfAbsent.invoke(this.descriptor, slotOffset + indexInSlot)) {
               this.highMarksArray[slot] = slotMarks;
               return index;
            }
         }

         this.highMarksArray[slot] = slotMarks;
      }

      return -1;
   }

   private companion object {
      private final val EMPTY_HIGH_MARKS: LongArray
   }
}
