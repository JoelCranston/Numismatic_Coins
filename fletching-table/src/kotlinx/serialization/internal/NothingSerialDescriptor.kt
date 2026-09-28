package kotlinx.serialization.internal

import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind

internal object NothingSerialDescriptor : SerialDescriptor {
   public open val kind: SerialKind = StructureKind.OBJECT.INSTANCE as SerialKind
   public open val serialName: String = "kotlin.Nothing"

   public open val elementsCount: Int
      public open get() {
         return 0;
      }


   public override fun getElementName(index: Int): String {
      this.error();
      throw new KotlinNothingValueException();
   }

   public override fun getElementIndex(name: String): Int {
      this.error();
      throw new KotlinNothingValueException();
   }

   public override fun isElementOptional(index: Int): Boolean {
      this.error();
      throw new KotlinNothingValueException();
   }

   public override fun getElementDescriptor(index: Int): SerialDescriptor {
      this.error();
      throw new KotlinNothingValueException();
   }

   public override fun getElementAnnotations(index: Int): List<Annotation> {
      this.error();
      throw new KotlinNothingValueException();
   }

   public override fun toString(): String {
      return "NothingSerialDescriptor";
   }

   public override operator fun equals(other: Any?): Boolean {
      return this === other;
   }

   public override fun hashCode(): Int {
      return this.getSerialName().hashCode() + 31 * this.getKind().hashCode();
   }

   private fun error(): Nothing {
      throw new IllegalStateException("Descriptor for type `kotlin.Nothing` does not have elements");
   }

   override fun isNullable(): Boolean {
      return SerialDescriptor.super.isNullable();
   }

   override fun isInline(): Boolean {
      return SerialDescriptor.super.isInline();
   }

   override fun getAnnotations(): MutableList<java.lang.annotation.Annotation> {
      return SerialDescriptor.super.getAnnotations();
   }
}
