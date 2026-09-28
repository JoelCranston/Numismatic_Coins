package kotlinx.serialization.internal

import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection

private class KTypeWrapper(origin: KType) : KType {
   private final val origin: KType

   public open val annotations: List<Annotation>
      public open get() {
         return this.origin.getAnnotations();
      }


   public open val arguments: List<KTypeProjection>
      public open get() {
         return this.origin.getArguments();
      }


   public open val classifier: KClassifier?
      public open get() {
         return this.origin.getClassifier();
      }


   public open val isMarkedNullable: Boolean
      public open get() {
         return this.origin.isMarkedNullable();
      }


   init {
      this.origin = origin;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (other == null) {
         return false;
      } else if (!(this.origin == (if ((other as? KTypeWrapper) != null) (other as? KTypeWrapper).origin else null))) {
         return false;
      } else {
         val kClassifier: KClassifier = this.getClassifier();
         if (kClassifier is KClass) {
            val otherClassifier: KClassifier = if ((other as? KType) != null) (other as? KType).getClassifier() else null;
            return otherClassifier != null
               && otherClassifier is KClass
               && JvmClassMappingKt.getJavaClass(kClassifier as KClass) == JvmClassMappingKt.getJavaClass(otherClassifier as KClass);
         } else {
            return false;
         }
      }
   }

   public override fun hashCode(): Int {
      return this.origin.hashCode();
   }

   public override fun toString(): String {
      return "KTypeWrapper: ${this.origin}";
   }
}
