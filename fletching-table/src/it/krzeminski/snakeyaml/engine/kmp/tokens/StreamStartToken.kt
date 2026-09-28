package it.krzeminski.snakeyaml.engine.kmp.tokens

import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import it.krzeminski.snakeyaml.engine.kmp.tokens.Token.ID

public class StreamStartToken(startMark: Mark?, endMark: Mark?) : Token(startMark, endMark) {
   public open val tokenId: ID
      public open get() {
         return Token.ID.StreamStart;
      }

}
