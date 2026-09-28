package io.ktor.http

import io.ktor.util.StringValues

public interface Headers : StringValues {
   public companion object {
      public final val Empty: Headers = EmptyHeaders.INSTANCE as Headers

      public inline fun build(builder: (HeadersBuilder) -> Unit): Headers {
         val var3: HeadersBuilder = new HeadersBuilder(0, 1, null);
         builder.invoke(var3);
         return var3.build();
      }
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun get(`$this`: Headers, name: java.lang.String): java.lang.String {
         return Headers.access$get$jd(`$this`, name);
      }

      @Deprecated
      @JvmStatic
      fun contains(`$this`: Headers, name: java.lang.String): Boolean {
         return Headers.access$contains$jd(`$this`, name);
      }

      @Deprecated
      @JvmStatic
      fun contains(`$this`: Headers, name: java.lang.String, value: java.lang.String): Boolean {
         return Headers.access$contains$jd(`$this`, name, value);
      }

      @Deprecated
      @JvmStatic
      fun forEach(`$this`: Headers, body: (java.lang.String?, MutableList<java.lang.String>?) -> Unit) {
         Headers.access$forEach$jd(`$this`, body);
      }
   }
}
