package kotlinx.serialization.descriptors

import kotlin.reflect.KClass

private class ContextDescriptor(original: SerialDescriptor, kClass: KClass<*>) : SerialDescriptor {
   private final val original: SerialDescriptor
   public final val kClass: KClass<*>
   public open val serialName: String

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
      this.original = original;
      this.kClass = kClass;
      this.serialName = "${this.original.getSerialName()}<${this.kClass.getSimpleName()}>";
   }

   public override operator fun equals(other: Any?): Boolean {
      val var10000: ContextDescriptor = other as? ContextDescriptor;
      if ((other as? ContextDescriptor) == null) {
         return false;
      } else {
         return this.original == var10000.original && var10000.kClass == this.kClass;
      }
   }

   public override fun hashCode(): Int {
      return 31 * this.kClass.hashCode() + this.getSerialName().hashCode();
   }

   public override fun toString(): String {
      return "ContextDescriptor(kClass: ${this.kClass}, original: ${this.original})";
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
