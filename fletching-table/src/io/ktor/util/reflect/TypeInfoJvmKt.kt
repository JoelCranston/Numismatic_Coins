package io.ktor.util.reflect

import java.lang.reflect.Type
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.TypesJVMKt

public final val reifiedType: Type
   public final get() {
      val var10000: KType = `$this$reifiedType`.getKotlinType();
      if (var10000 != null) {
         val var1: Type = TypesJVMKt.getJavaType(var10000);
         if (var1 != null) {
            return var1;
         }
      }

      return JvmClassMappingKt.getJavaClass(`$this$reifiedType`.getType());
   }


@Deprecated(
   message = "Use KType.javaType instead.",
   replaceWith = @ReplaceWith(
      expression = "this.javaType",
      imports = {"kotlin.reflect.javaType"}
   )
)
public final val platformType: Type
   public final get() {
      return TypesJVMKt.getJavaType(`$this$platformType`);
   }


@Deprecated(message = "Use TypeInfo constructor instead.", replaceWith = @ReplaceWith(expression = "TypeInfo(kClass, kType)", imports = []))
public fun typeInfoImpl(reifiedType: Type, kClass: KClass<*>, kType: KType?): TypeInfo {
   return new TypeInfo(kClass, kType);
}

public fun Any.instanceOf(type: KClass<*>): Boolean {
   return JvmClassMappingKt.getJavaClass(type).isInstance(`$this$instanceOf`);
}

/** @deprecated */
@Deprecated(message = "Not used anymore in common code as it was needed only for JVM target.")
@JvmSynthetic
fun `Type$annotations`() {
}
