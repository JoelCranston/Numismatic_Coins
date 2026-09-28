package kotlinx.serialization.internal

import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor

internal class PrimitiveSerialDescriptor(serialName: String, kind: PrimitiveKind) : SerialDescriptor {
   public open val serialName: String
   public open val kind: PrimitiveKind

   public open val elementsCount: Int
      public open get() {
         return 0;
      }


   init {
      this.serialName = serialName;
      this.kind = kind;
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
      return "PrimitiveDescriptor(${this.getSerialName()})";
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is PrimitiveSerialDescriptor) {
         return false;
      } else {
         return this.getSerialName() == (other as PrimitiveSerialDescriptor).getSerialName()
            && this.getKind() == (other as PrimitiveSerialDescriptor).getKind();
      }
   }

   public override fun hashCode(): Int {
      return this.getSerialName().hashCode() + 31 * this.getKind().hashCode();
   }

   private fun error(): Nothing {
      throw new IllegalStateException("Primitive descriptor ${this.getSerialName()} does not have elements");
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
