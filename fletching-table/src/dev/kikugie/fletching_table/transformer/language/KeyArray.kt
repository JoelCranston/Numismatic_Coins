package dev.kikugie.fletching_table.transformer.language

import dev.kikugie.commons.collections.PresentationKt
import java.util.ArrayDeque

@JvmInline
internal inline class KeyArray : KeyStack {
   public open val stack: ArrayDeque<Key>

   public open val path: String
      public open get() {
         return KeysKt.access$join(`$v$c$dev-kikugie-fletching_table-transformer-language-KeyArray$-this$0`);
      }


   override fun getPath(): java.lang.String {
      return getPath-impl(this.stack);
   }

   @JvmStatic
   public open fun toString(): String {
      return "KeyArray(stack=${PresentationKt.present$default(`$v$c$dev-kikugie-fletching_table-transformer-language-KeyArray$-this$0`, 0, 1, null)})";
   }

   override fun toString(): java.lang.String {
      return toString-impl(this.stack);
   }

   @JvmStatic
   public open fun hashCode(): Int {
      return `$v$c$dev-kikugie-fletching_table-transformer-language-KeyArray$-this$0`.hashCode();
   }

   override fun hashCode(): Int {
      return hashCode-impl(this.stack);
   }

   @JvmStatic
   public open operator fun equals(other: Any?): Boolean {
      if (other !is KeyArray) {
         return false;
      } else {
         return `$v$c$dev-kikugie-fletching_table-transformer-language-KeyArray$-this$0` == (other as KeyArray).unbox-impl();
      }
   }

   override fun equals(other: Any): Boolean {
      return equals-impl(this.stack, other);
   }

   @JvmStatic
   fun `constructor-impl`(stack: ArrayDeque<Key>): ArrayDeque<Key> {
      return stack;
   }

   @JvmStatic
   fun `equals-impl0`(p1: ArrayDeque<Key>, p2: ArrayDeque<Key>): Boolean {
      return p1 == p2;
   }
}
