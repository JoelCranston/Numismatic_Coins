package kotlin.reflect

import java.lang.reflect.Type
import java.lang.reflect.WildcardType
import java.util.Arrays

@ExperimentalStdlibApi
private class WildcardTypeImpl(upperBound: Type?, lowerBound: Type?) : WildcardType, TypeImpl {
   private final val upperBound: Type?
   private final val lowerBound: Type?

   init {
      this.upperBound = upperBound;
      this.lowerBound = lowerBound;
   }

   public override fun getUpperBounds(): Array<Type> {
      val var1: Array<Type> = new Type[1];
      var var10002: Type = this.upperBound;
      if (this.upperBound == null) {
         var10002 = Object::class.java;
      }

      var1[0] = var10002;
      return var1;
   }

   public override fun getLowerBounds(): Array<Type> {
      return if (this.lowerBound == null) new Type[0] else new Type[]{this.lowerBound};
   }

   public override fun getTypeName(): String {
      return if (this.lowerBound != null)
         "? super ${TypesJVMKt.access$typeToString(this.lowerBound)}"
         else
         (if (this.upperBound != null && !(this.upperBound == Object::class.java)) "? extends ${TypesJVMKt.access$typeToString(this.upperBound)}" else "?");
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is WildcardType
         && Arrays.equals((Object[])this.getUpperBounds(), (Object[])(other as WildcardType).getUpperBounds())
         && Arrays.equals((Object[])this.getLowerBounds(), (Object[])(other as WildcardType).getLowerBounds());
   }

   public override fun hashCode(): Int {
      return Arrays.hashCode((Object[])this.getUpperBounds()) xor Arrays.hashCode((Object[])this.getLowerBounds());
   }

   public override fun toString(): String {
      return this.getTypeName();
   }

   public companion object {
      public final val STAR: WildcardTypeImpl
   }
}
