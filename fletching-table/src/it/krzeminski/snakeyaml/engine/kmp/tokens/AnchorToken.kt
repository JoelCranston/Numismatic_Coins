package it.krzeminski.snakeyaml.engine.kmp.tokens

import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import it.krzeminski.snakeyaml.engine.kmp.tokens.Token.ID

public class AnchorToken(value: Anchor, startMark: Mark?, endMark: Mark?) : Token(startMark, endMark) {
   public final val value: Anchor

   public open val tokenId: ID
      public open get() {
         return Token.ID.Anchor;
      }


   init {
      this.value = value;
   }
}
