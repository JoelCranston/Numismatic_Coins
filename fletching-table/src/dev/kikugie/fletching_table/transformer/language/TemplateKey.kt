package dev.kikugie.fletching_table.transformer.language

import dev.kikugie.commons.collections.PresentationKt
import java.util.ArrayDeque

internal data class TemplateKey(template: String, stack: ArrayDeque<Key> = new ArrayDeque()) : KeyStack, Key {
   public final val template: String
   public open val stack: ArrayDeque<Key>

   public open val path: String
      public open get() {
         return StringsKt.replace$default(this.template, "$", KeysKt.access$join(this.getStack()), false, 4, null);
      }


   init {
      this.template = template;
      this.stack = stack;
   }

   public override fun toString(): String {
      return "TemplateKey(template=${this.template}, stack=${PresentationKt.present$default(this.getStack(), 0, 1, null)})";
   }

   public operator fun component1(): String {
      return this.template;
   }

   public operator fun component2(): ArrayDeque<Key> {
      return this.stack;
   }

   public fun copy(template: String = this.template, stack: ArrayDeque<Key> = this.stack): TemplateKey {
      return new TemplateKey(template, stack);
   }

   public override fun hashCode(): Int {
      return this.template.hashCode() * 31 + this.stack.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is TemplateKey) {
         return false;
      } else {
         val var2: TemplateKey = other as TemplateKey;
         if (!(this.template == (other as TemplateKey).template)) {
            return false;
         } else {
            return this.stack == var2.stack;
         }
      }
   }
}
