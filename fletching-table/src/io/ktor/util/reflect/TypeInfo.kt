package io.ktor.util.reflect

import java.lang.reflect.Type
import kotlin.reflect.KAnnotatedElement
import kotlin.reflect.KClass
import kotlin.reflect.KType

public class TypeInfo(type: KClass<*>, kotlinType: KType? = null) {
   public final val type: KClass<*>
   public final val kotlinType: KType?

   init {
      this.type = type;
      this.kotlinType = kotlinType;
   }

   @Deprecated(message = "Use constructor without reifiedType parameter.", replaceWith = @ReplaceWith(expression = "TypeInfo(type, kotlinType)", imports = []))
   public constructor(type: KClass<*>, reifiedType: Type, kotlinType: KType? = null) : this(type, kotlinType)
   public override fun hashCode(): Int {
      return if (this.kotlinType != null) this.kotlinType.hashCode() else this.type.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is TypeInfo) {
         return false;
      } else {
         return if (this.kotlinType == null && (other as TypeInfo).kotlinType == null)
            this.type == (other as TypeInfo).type
            else
            this.kotlinType == (other as TypeInfo).kotlinType;
      }
   }

   public override fun toString(): String {
      return "TypeInfo(${if (this.kotlinType != null) this.kotlinType else this.type as KAnnotatedElement})";
   }
}
