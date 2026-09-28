package io.ktor.util.collections

import io.ktor.utils.io.InternalAPI

@InternalAPI
public interface StringMapDelegate : StringMap {
   public val map: MutableMap<String, String>

   public override operator fun set(key: String, value: String) {
      this.getMap().put(key, value);
   }

   public override operator fun get(key: String): String? {
      return this.getMap().get(key);
   }

   public override fun remove(key: String): String? {
      return this.getMap().remove(key);
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun set(`$this`: StringMapDelegate, key: java.lang.String, value: java.lang.String) {
         StringMapDelegate.access$set$jd(`$this`, key, value);
      }

      @Deprecated
      @JvmStatic
      fun get(`$this`: StringMapDelegate, key: java.lang.String): java.lang.String {
         return StringMapDelegate.access$get$jd(`$this`, key);
      }

      @Deprecated
      @JvmStatic
      fun remove(`$this`: StringMapDelegate, key: java.lang.String): java.lang.String {
         return StringMapDelegate.access$remove$jd(`$this`, key);
      }
   }
}
