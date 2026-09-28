package it.krzeminski.snakeyaml.engine.kmp.comments

import it.krzeminski.snakeyaml.engine.kmp.events.CommentEvent
import it.krzeminski.snakeyaml.engine.kmp.events.Event
import it.krzeminski.snakeyaml.engine.kmp.parser.Parser
import java.util.ArrayList

public class CommentEventsCollector private constructor(eventSource: EventQueue, vararg expectedCommentTypes: Any) {
   private final val eventSource: EventQueue
   private final val expectedCommentTypes: Array<out CommentType>
   private final val commentLineList: MutableList<CommentLine>

   init {
      this.eventSource = eventSource;
      this.expectedCommentTypes = expectedCommentTypes;
      this.commentLineList = new ArrayList<>();
   }

   public constructor(parser: Parser, vararg expectedCommentTypes: CommentType) : this(new EventQueue(parser), expectedCommentTypes)
   public constructor(eventSource: ArrayDeque<Event>, vararg expectedCommentTypes: CommentType) : this(new EventQueue(eventSource), expectedCommentTypes)
   private fun isEventExpected(event: Event?): Boolean {
      return event != null
         && event.getEventId() === Event.ID.Comment
         && event is CommentEvent
         && ArraysKt.contains(this.expectedCommentTypes, (event as CommentEvent).getCommentType());
   }

   public fun collectEvents(): CommentEventsCollector {
      this.collectEvents(null);
      return this;
   }

   public fun collectEvents(event: Event?): Event? {
      if (event != null) {
         if (!this.isEventExpected(event)) {
            return event;
         }

         this.commentLineList.add(new CommentLine(event as CommentEvent));
      }

      while (this.isEventExpected(this.eventSource.peek())) {
         val e: Event = this.eventSource.poll();
         val var10000: java.util.List = this.commentLineList;
         var10000.add(new CommentLine(e as CommentEvent));
      }

      return null;
   }

   public fun collectEventsAndPoll(event: Event?): Event {
      val nextEvent: Event = this.collectEvents(event);
      var var10000: Event = nextEvent;
      if (nextEvent == null) {
         var10000 = this.eventSource.poll();
      }

      return var10000;
   }

   public fun consume(): List<CommentLine> {
      label12: {
         try {
            val var1: java.util.List = CollectionsKt.toList(this.commentLineList);
         } catch (var3: java.lang.Throwable) {
            this.commentLineList.clear();
         }

         this.commentLineList.clear();
      }
   }

   public fun isEmpty(): Boolean {
      return this.commentLineList.isEmpty();
   }
}
