package dev.kikugie.fletching_table.transformer.language

import java.util.Deque

internal sealed interface KeyStack {
   public val path: String
   public val stack: Deque<Key>

   public open fun push(key: Key) {
      val top: Key = if (this.getStack().isEmpty()) null else this.getStack().peekLast();
      if (top is TemplateKey) {
         (top as TemplateKey).push(key);
      } else {
         this.getStack().addLast(key);
      }
   }

   public open fun pop(): Key {
      val top: Key = if (this.getStack().isEmpty()) null else this.getStack().peekLast();
      val var2: Key;
      if (top is TemplateKey && !(top as TemplateKey).getStack().isEmpty()) {
         var2 = (top as TemplateKey).pop();
      } else {
         val var10000: Any = this.getStack().removeLast();
         var2 = var10000 as Key;
      }

      return var2;
   }
}
