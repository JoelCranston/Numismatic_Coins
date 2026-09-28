package it.krzeminski.snakeyaml.engine.kmp.events

import it.krzeminski.snakeyaml.engine.kmp.comments.CommentType
import it.krzeminski.snakeyaml.engine.kmp.events.Event.ID
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public class CommentEvent(commentType: CommentType, value: String, startMark: Mark?, endMark: Mark?) : Event(startMark, endMark) {
   public final val commentType: CommentType
   public final val value: String

   public open val eventId: ID
      public open get() {
         return Event.ID.Comment;
      }


   init {
      this.commentType = commentType;
      this.value = value;
   }

   public override fun toString(): String {
      return "=COM ${this.commentType} ${this.value}";
   }
}
