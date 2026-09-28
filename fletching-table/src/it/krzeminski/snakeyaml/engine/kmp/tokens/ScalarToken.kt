package it.krzeminski.snakeyaml.engine.kmp.tokens

import it.krzeminski.snakeyaml.engine.kmp.common.ScalarStyle
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import it.krzeminski.snakeyaml.engine.kmp.tokens.Token.ID

public class ScalarToken @JvmOverloads  public constructor(value: String,
   plain: Boolean,
   startMark: Mark?,
   endMark: Mark?,
   style: ScalarStyle = ScalarStyle.PLAIN
) : Token(startMark, endMark) {
   public final val value: String
   public final val plain: Boolean
   public final val style: ScalarStyle

   public open val tokenId: ID
      public open get() {
         return Token.ID.Scalar;
      }


   init {
      this.value = value;
      this.plain = plain;
      this.style = style;
   }

   public override fun toString(): String {
      return "${this.getTokenId()} plain=${this.plain} style=${this.style} value=${this.value}";
   }

   @JvmOverloads
   fun ScalarToken(value: java.lang.String, plain: Boolean, startMark: Mark?, endMark: Mark?) {
      this(value, plain, startMark, endMark, null, 16, null);
   }
}
