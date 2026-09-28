package it.krzeminski.snakeyaml.engine.kmp.comments

import it.krzeminski.snakeyaml.engine.kmp.events.CommentEvent
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public class CommentLine(startMark: Mark?, endMark: Mark?, value: String, commentType: CommentType) {
   public final val startMark: Mark?
   public final val endMark: Mark?
   public final val value: String
   public final val commentType: CommentType

   init {
      this.startMark = startMark;
      this.endMark = endMark;
      this.value = value;
      this.commentType = commentType;
   }

   public constructor(event: CommentEvent) : this(event.getStartMark(), event.getEndMark(), event.getValue(), event.getCommentType())
   public override fun toString(): String {
      return "<CommentLine (type=${this.commentType}, value=${this.value})>";
   }
}
