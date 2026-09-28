package io.ktor.util

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nStringValues.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringValues.kt\nio/ktor/util/StringValues\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,524:1\n1869#2,2:525\n*S KotlinDebug\n*F\n+ 1 StringValues.kt\nio/ktor/util/StringValues\n*L\n93#1:525,2\n*E\n"])
public interface StringValues {
   public val caseInsensitiveName: Boolean

   public open operator fun get(name: String): String? {
      val var10000: java.util.List = this.getAll(name);
      return if (var10000 != null) kotlin.collections.CollectionsKt.firstOrNull(var10000) else null;
   }

   public abstract fun getAll(name: String): List<String>? {
   }

   public abstract fun names(): Set<String> {
   }

   public abstract fun entries(): Set<kotlin.collections.Map.Entry<String, List<String>>> {
   }

   public open operator fun contains(name: String): Boolean {
      return this.getAll(name) != null;
   }

   public open fun contains(name: String, value: String): Boolean {
      val var10000: java.util.List = this.getAll(name);
      return var10000 != null && var10000.contains(value);
   }

   public open fun forEach(body: (String, List<String>) -> Unit) {
      val `$this$forEach$iv`: java.lang.Iterable;
      for (Object element$iv : $this$forEach$iv) {
         body.invoke((`element$iv` as java.util.Map.Entry).getKey() as java.lang.String, (`element$iv` as java.util.Map.Entry).getValue() as java.util.List);
      }
   }

   public abstract fun isEmpty(): Boolean {
   }

   public companion object {
      public final val Empty: StringValues = (new StringValuesImpl(false, null, 3, null)) as StringValues

      public inline fun build(caseInsensitiveName: Boolean = false, builder: (StringValuesBuilder) -> Unit): StringValues {
         val var4: StringValuesBuilderImpl = new StringValuesBuilderImpl(caseInsensitiveName, 0, 2, null);
         builder.invoke(var4);
         return var4.build();
      }
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun get(`$this`: StringValues, name: java.lang.String): java.lang.String {
         return StringValues.access$get$jd(`$this`, name);
      }

      @Deprecated
      @JvmStatic
      fun contains(`$this`: StringValues, name: java.lang.String): Boolean {
         return StringValues.access$contains$jd(`$this`, name);
      }

      @Deprecated
      @JvmStatic
      fun contains(`$this`: StringValues, name: java.lang.String, value: java.lang.String): Boolean {
         return StringValues.access$contains$jd(`$this`, name, value);
      }

      @Deprecated
      @JvmStatic
      fun forEach(`$this`: StringValues, body: (java.lang.String?, MutableList<java.lang.String>?) -> Unit) {
         StringValues.access$forEach$jd(`$this`, body);
      }
   }
}
