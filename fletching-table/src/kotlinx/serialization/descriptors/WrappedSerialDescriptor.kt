package kotlinx.serialization.descriptors

import kotlinx.serialization.internal.PluginGeneratedSerialDescriptorKt

internal class WrappedSerialDescriptor(serialName: String, original: SerialDescriptor) : SerialDescriptor {
   public open val serialName: String
   private final val original: SerialDescriptor

   public open val annotations: List<Annotation>
      public open get() {
         return this.original.getAnnotations();
      }


   public open val elementsCount: Int

   public open val isInline: Boolean
      public open get() {
         return this.original.isInline();
      }


   public open val isNullable: Boolean
      public open get() {
         return this.original.isNullable();
      }


   public open val kind: SerialKind

   init {
      this.serialName = serialName;
      this.original = original;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is WrappedSerialDescriptor) {
         return false;
      } else {
         return this.getSerialName() == (other as WrappedSerialDescriptor).getSerialName() && this.original == (other as WrappedSerialDescriptor).original;
      }
   }

   public override fun hashCode(): Int {
      return 31 * this.getSerialName().hashCode() + this.original.hashCode();
   }

   public override fun toString(): String {
      return PluginGeneratedSerialDescriptorKt.toStringImpl(this);
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
