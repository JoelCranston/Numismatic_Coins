package io.ktor.http

import io.ktor.util.StringValues

public interface Parameters : StringValues {
   public companion object {
      public final val Empty: Parameters = EmptyParameters.INSTANCE as Parameters

      public inline fun build(builder: (ParametersBuilder) -> Unit): Parameters {
         val var3: ParametersBuilder = ParametersKt.ParametersBuilder$default(0, 1, null);
         builder.invoke(var3);
         return var3.build();
      }
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun get(`$this`: Parameters, name: java.lang.String): java.lang.String {
         return Parameters.access$get$jd(`$this`, name);
      }

      @Deprecated
      @JvmStatic
      fun contains(`$this`: Parameters, name: java.lang.String): Boolean {
         return Parameters.access$contains$jd(`$this`, name);
      }

      @Deprecated
      @JvmStatic
      fun contains(`$this`: Parameters, name: java.lang.String, value: java.lang.String): Boolean {
         return Parameters.access$contains$jd(`$this`, name, value);
      }

      @Deprecated
      @JvmStatic
      fun forEach(`$this`: Parameters, body: (java.lang.String?, MutableList<java.lang.String>?) -> Unit) {
         Parameters.access$forEach$jd(`$this`, body);
      }
   }
}
