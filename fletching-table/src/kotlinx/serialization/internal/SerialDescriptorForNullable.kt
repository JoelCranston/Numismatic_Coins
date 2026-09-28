package kotlinx.serialization.internal

import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind

internal class SerialDescriptorForNullable(original: SerialDescriptor) : SerialDescriptor, CachedNames {
   internal final val original: SerialDescriptor
   public open val serialName: String
   public open val serialNames: Set<String>

   public open val isNullable: Boolean
      public open get() {
         return true;
      }


   public open val annotations: List<Annotation>
      public open get() {
         return this.original.getAnnotations();
      }


   public open val elementsCount: Int

   public open val isInline: Boolean
      public open get() {
         return this.original.isInline();
      }


   public open val kind: SerialKind

   init {
      this.original = original;
      this.serialName = "${this.original.getSerialName()}?";
      this.serialNames = Platform_commonKt.cachedSerialNames(this.original);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is SerialDescriptorForNullable) {
         return false;
      } else {
         return this.original == (other as SerialDescriptorForNullable).original;
      }
   }

   public override fun toString(): String {
      return "${this.original}?";
   }

   public override fun hashCode(): Int {
      return this.original.hashCode() * 31;
   }

   public override fun getElementName(index: Int): String {
      return this.original.getElementName(index);
   }

   public override fun getElementIndex(name: String): Int {
      return this.original.getElementIndex(name);
   }

   public override fun getElementAnnotations(index: Int): List<Annotation> {
      return this.original.getElementAnnotations(index);
   }

   public override fun getElementDescriptor(index: Int): SerialDescriptor {
      return this.original.getElementDescriptor(index);
   }

   public override fun isElementOptional(index: Int): Boolean {
      return this.original.isElementOptional(index);
   }
}
