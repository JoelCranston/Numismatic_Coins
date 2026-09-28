package kotlin.text

import kotlin.internal.InlineOnly

internal class StringsKt__AppendableKt {
   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T : Appendable> T.appendRange(value: CharSequence, startIndex: Int, endIndex: Int): T {
      val var10000: Appendable = `$this$appendRange`.append(value, startIndex, endIndex);
      return (T)var10000;
   }

   @JvmStatic
   public fun <T : Appendable> T.append(vararg value: CharSequence?): T {
      for (java.lang.CharSequence item : value) {
         `$this$append`.append(item);
      }

      return (T)`$this$append`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun Appendable.appendLine(): Appendable {
      return `$this$appendLine`.append('\n');
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun Appendable.appendLine(value: CharSequence?): Appendable {
      return `$this$appendLine`.append(value).append('\n');
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun Appendable.appendLine(value: Char): Appendable {
      return `$this$appendLine`.append(value).append('\n');
   }

   @JvmStatic
   internal fun <T> Appendable.appendElement(element: T, transform: ((T) -> CharSequence)?) {
      if (transform != null) {
         `$this$appendElement`.append(transform.invoke(element) as java.lang.CharSequence);
      } else if (element == null || element is java.lang.CharSequence) {
         `$this$appendElement`.append(element as java.lang.CharSequence);
      } else if (element is Character) {
         `$this$appendElement`.append(element as Character);
      } else {
         `$this$appendElement`.append(element.toString());
      }
   }

   open fun StringsKt__AppendableKt() {
   }
}
