package it.krzeminski.snakeyaml.engine.kmp.tokens

import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import it.krzeminski.snakeyaml.engine.kmp.tokens.Token.ID

public class TagToken(value: TagTuple, startMark: Mark?, endMark: Mark?) : Token(startMark, endMark) {
   public final val value: TagTuple

   public open val tokenId: ID
      public open get() {
         return Token.ID.Tag;
      }


   init {
      this.value = value;
   }
}
