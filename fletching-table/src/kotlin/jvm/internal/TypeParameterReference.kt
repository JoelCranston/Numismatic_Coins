package kotlin.jvm.internal

import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter
import kotlin.reflect.KVariance

@SinceKotlin(version = "1.4")
@SourceDebugExtension(["SMAP\nTypeParameterReference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TypeParameterReference.kt\nkotlin/jvm/internal/TypeParameterReference\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,58:1\n1#2:59\n*E\n"])
public class TypeParameterReference(container: Any?, name: String, variance: KVariance, isReified: Boolean) : KTypeParameter {
   private final val container: Any?
   public open val name: String
   public open val variance: KVariance
   public open val isReified: Boolean
   private final var bounds: List<KType>?

   public open val upperBounds: List<KType>
      public open get() {
         var var10000: java.util.List = this.bounds;
         if (this.bounds == null) {
            val var1: java.util.List = CollectionsKt.listOf(Reflection.nullableTypeOf(Object.class));
            this.bounds = var1;
            var10000 = var1;
         }

         return var10000;
      }


   init {
      this.container = container;
      this.name = name;
      this.variance = variance;
      this.isReified = isReified;
   }

   public fun setUpperBounds(upperBounds: List<KType>) {
      if (this.bounds != null) {
         throw new IllegalStateException(("Upper bounds of type parameter '$this' have already been initialized.").toString());
      } else {
         this.bounds = upperBounds;
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is TypeParameterReference
         && this.container == (other as TypeParameterReference).container
         && this.getName() == (other as TypeParameterReference).getName();
   }

   public override fun hashCode(): Int {
      return (if (this.container != null) this.container.hashCode() else 0) * 31 + this.getName().hashCode();
   }

   public override fun toString(): String {
      return Companion.toString(this);
   }

   public companion object {
      public fun toString(typeParameter: KTypeParameter): String {
         val var2: StringBuilder = new StringBuilder();
         switch (TypeParameterReference.Companion.WhenMappings.$EnumSwitchMapping$0[typeParameter.getVariance().ordinal()]) {
            case 1:
               break;
            case 2:
               var2.append("in ");
               break;
            case 3:
               var2.append("out ");
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         var2.append(typeParameter.getName());
         return var2.toString();
      }
   }
}
