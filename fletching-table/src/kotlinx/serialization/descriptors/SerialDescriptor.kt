package kotlinx.serialization.descriptors

import kotlinx.serialization.SealedSerializationApi

@SubclassOptInRequired(markerClass = [SealedSerializationApi::class])
public interface SerialDescriptor {
   public val serialName: String
   public val kind: SerialKind

   public open val isNullable: Boolean
      public open get() {
         return false;
      }


   public open val isInline: Boolean
      public open get() {
         return false;
      }


   public val elementsCount: Int

   public open val annotations: List<Annotation>
      public open get() {
         return CollectionsKt.emptyList();
      }


   public abstract fun getElementName(index: Int): String {
   }

   public abstract fun getElementIndex(name: String): Int {
   }

   public abstract fun getElementAnnotations(index: Int): List<Annotation> {
   }

   public abstract fun getElementDescriptor(index: Int): SerialDescriptor {
   }

   public abstract fun isElementOptional(index: Int): Boolean {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun isNullable(`$this`: SerialDescriptor): Boolean {
         return SerialDescriptor.access$isNullable$jd(`$this`);
      }

      @Deprecated
      @JvmStatic
      fun isInline(`$this`: SerialDescriptor): Boolean {
         return SerialDescriptor.access$isInline$jd(`$this`);
      }

      @Deprecated
      @JvmStatic
      fun getAnnotations(`$this`: SerialDescriptor): MutableList<java.lang.annotation.Annotation> {
         return SerialDescriptor.access$getAnnotations$jd(`$this`);
      }
   }
}
