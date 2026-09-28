package kotlin.text

import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly

internal class StringsKt__StringBuilderKt : StringsKt__StringBuilderJVMKt {
   @Deprecated(message = "Use append(value: Any?) instead", replaceWith = @ReplaceWith(expression = "append(value = obj)", imports = []), level = DeprecationLevel.WARNING)
   @InlineOnly
   @JvmStatic
   public inline fun StringBuilder.append(obj: Any?): StringBuilder {
      return `$this$append`.append(obj);
   }

   @InlineOnly
   @JvmStatic
   public inline fun buildString(builderAction: (StringBuilder) -> Unit): String {
      contract {
         callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE)
      }

      val var1: StringBuilder = new StringBuilder();
      builderAction.invoke(var1);
      return var1.toString();
   }

   @SinceKotlin(version = "1.1")
   @InlineOnly
   @JvmStatic
   public inline fun buildString(capacity: Int, builderAction: (StringBuilder) -> Unit): String {
      contract {
         callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE)
      }

      val var2: StringBuilder = new StringBuilder(capacity);
      builderAction.invoke(var2);
      return var2.toString();
   }

   @JvmStatic
   public fun StringBuilder.append(vararg value: String?): StringBuilder {
      for (java.lang.String item : value) {
         `$this$append`.append(item);
      }

      return `$this$append`;
   }

   @JvmStatic
   public fun StringBuilder.append(vararg value: Any?): StringBuilder {
      for (Object item : value) {
         `$this$append`.append(item);
      }

      return `$this$append`;
   }

   @Deprecated(message = "Use appendRange instead.", replaceWith = @ReplaceWith(expression = "this.appendRange(str, offset, offset + len)", imports = []), level = DeprecationLevel.ERROR)
   @InlineOnly
   @JvmStatic
   public inline fun StringBuilder.append(str: CharArray, offset: Int, len: Int): StringBuilder {
      throw new NotImplementedError(null, 1, null);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun StringBuilder.appendLine(): StringBuilder {
      return `$this$appendLine`.append('\n');
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun StringBuilder.appendLine(value: CharSequence?): StringBuilder {
      return `$this$appendLine`.append(value).append('\n');
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun StringBuilder.appendLine(value: String?): StringBuilder {
      return `$this$appendLine`.append(value).append('\n');
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun StringBuilder.appendLine(value: Any?): StringBuilder {
      return `$this$appendLine`.append(value).append('\n');
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun StringBuilder.appendLine(value: CharArray): StringBuilder {
      return `$this$appendLine`.append(value).append('\n');
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun StringBuilder.appendLine(value: Char): StringBuilder {
      return `$this$appendLine`.append(value).append('\n');
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun StringBuilder.appendLine(value: Boolean): StringBuilder {
      return `$this$appendLine`.append(value).append('\n');
   }

   open fun StringsKt__StringBuilderKt() {
   }
}
