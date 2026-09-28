package it.krzeminski.snakeyaml.engine.kmp.tokens

import it.krzeminski.snakeyaml.engine.kmp.comments.CommentType
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import it.krzeminski.snakeyaml.engine.kmp.tokens.Token.ID

public class CommentToken(commentType: CommentType, value: String, startMark: Mark?, endMark: Mark?) : Token(startMark, endMark) {
   public final val commentType: CommentType
   public final val value: String

   public open val tokenId: ID
      public open get() {
         return Token.ID.Comment;
      }


   init {
      this.commentType = commentType;
      this.value = value;
   }
}
