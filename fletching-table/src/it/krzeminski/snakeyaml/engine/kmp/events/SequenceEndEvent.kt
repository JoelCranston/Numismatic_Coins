package it.krzeminski.snakeyaml.engine.kmp.events

import it.krzeminski.snakeyaml.engine.kmp.events.Event.ID
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public class SequenceEndEvent : CollectionEndEvent {
   public open val eventId: ID
      public open get() {
         return Event.ID.SequenceEnd;
      }


   public constructor(startMark: Mark?, endMark: Mark?) : super(startMark, endMark)

   public override fun toString(): String {
      return "-SEQ";
   }
}
