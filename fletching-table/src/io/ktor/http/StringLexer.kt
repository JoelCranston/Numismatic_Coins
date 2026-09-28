package io.ktor.http

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nCookieUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CookieUtils.kt\nio/ktor/http/StringLexer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,349:1\n1#2:350\n*E\n"])
internal class StringLexer(source: String) {
   public final val source: String
   public final var index: Int

   public final val hasRemaining: Boolean
      public final get() {
         return this.index < this.source.length();
      }


   init {
      this.source = source;
   }

   public fun test(predicate: (Char) -> Boolean): Boolean {
      return this.index < this.source.length() && predicate.invoke(this.source.charAt(this.index)) as java.lang.Boolean;
   }

   public fun accept(predicate: (Char) -> Boolean): Boolean {
      val var2: Boolean = this.test(predicate);
      if (var2) {
         val var5: Int = this.index++;
      }

      return var2;
   }

   public fun acceptWhile(predicate: (Char) -> Boolean): Boolean {
      if (!this.test(predicate)) {
         return false;
      } else {
         while (this.test(predicate)) {
            val var2: Int = this.index++;
         }

         return true;
      }
   }

   public inline fun capture(block: (StringLexer) -> Unit): String {
      val start: Int = this.getIndex();
      block.invoke(this);
      val var10000: java.lang.String = this.getSource().substring(start, this.getIndex());
      return var10000;
   }
}
