package kotlinx.serialization.json.internal

import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.internal.ElementMarker
import kotlinx.serialization.json.internal.JsonElementMarker.origin.1

internal class JsonElementMarker(descriptor: SerialDescriptor) {
   private final val origin: ElementMarker

   internal final var isUnmarkedNull: Boolean
      private set

   init {
      this.origin = new ElementMarker(descriptor, new 1(this));
   }

   internal fun mark(index: Int) {
      this.origin.mark(index);
   }

   internal fun nextUnmarkedIndex(): Int {
      return this.origin.nextUnmarkedIndex();
   }

   private fun readIfAbsent(descriptor: SerialDescriptor, index: Int): Boolean {
      this.isUnmarkedNull = !descriptor.isElementOptional(index) && descriptor.getElementDescriptor(index).isNullable();
      return this.isUnmarkedNull;
   }
}
