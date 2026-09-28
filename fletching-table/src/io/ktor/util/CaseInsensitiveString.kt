package io.ktor.util

internal class CaseInsensitiveString(content: String) {
   public final val content: String
   private final val hash: Int

   init {
      this.content = content;
      var temp: Int = 0;
      val var3: java.lang.String = this.content;
      var var4: Int = 0;

      for (int var5 = this.content.length(); var4 < var5; var4++) {
         temp = temp * 31 + Character.toLowerCase(var3.charAt(var4));
      }

      this.hash = temp;
   }

   public override operator fun equals(other: Any?): Boolean {
      return (other as? CaseInsensitiveString) != null
         && (other as? CaseInsensitiveString).content != null
         && StringsKt.equals((other as? CaseInsensitiveString).content, this.content, true);
   }

   public override fun hashCode(): Int {
      return this.hash;
   }

   public override fun toString(): String {
      return this.content;
   }
}
