package io.ktor.util

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nAttributes.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Attributes.kt\nio/ktor/util/Attributes\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,156:1\n1#2:157\n*E\n"])
public interface Attributes {
   public val allKeys: List<AttributeKey<*>>

   public open operator fun <T : Any> get(key: AttributeKey<Any>): Any {
      val var10000: Any = this.getOrNull(key);
      if (var10000 == null) {
         throw new IllegalStateException("No instance for key $key");
      } else {
         return (T)var10000;
      }
   }

   public abstract fun <T : Any> getOrNull(key: AttributeKey<Any>): Any? {
   }

   public abstract operator fun contains(key: AttributeKey<*>): Boolean {
   }

   public abstract fun <T : Any> put(key: AttributeKey<Any>, value: Any) {
   }

   public open operator fun <T : Any> set(key: AttributeKey<Any>, value: Any) {
      this.put(key, value);
   }

   public abstract fun <T : Any> remove(key: AttributeKey<Any>) {
   }

   public open fun <T : Any> take(key: AttributeKey<Any>): Any {
      val var2: Any = this.get(key);
      this.remove(key);
      return (T)var2;
   }

   public open fun <T : Any> takeOrNull(key: AttributeKey<Any>): Any? {
      val var2: Any = this.getOrNull(key);
      this.remove(key);
      return (T)var2;
   }

   public abstract fun <T : Any> computeIfAbsent(key: AttributeKey<Any>, block: () -> Any): Any {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun <T> get(`$this`: Attributes, key: AttributeKey<T>): T {
         return (T)Attributes.access$get$jd(`$this`, key);
      }

      @Deprecated
      @JvmStatic
      fun <T> set(`$this`: Attributes, key: AttributeKey<T>, value: T) {
         Attributes.access$set$jd(`$this`, key, value);
      }

      @Deprecated
      @JvmStatic
      fun <T> take(`$this`: Attributes, key: AttributeKey<T>): T {
         return (T)Attributes.access$take$jd(`$this`, key);
      }

      @Deprecated
      @JvmStatic
      fun <T> takeOrNull(`$this`: Attributes, key: AttributeKey<T>): T {
         return (T)Attributes.access$takeOrNull$jd(`$this`, key);
      }
   }
}
