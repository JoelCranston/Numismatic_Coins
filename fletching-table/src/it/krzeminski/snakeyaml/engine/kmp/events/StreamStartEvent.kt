package it.krzeminski.snakeyaml.engine.kmp.events

import it.krzeminski.snakeyaml.engine.kmp.events.Event.ID
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public class StreamStartEvent : Event {
   public open val eventId: ID
      public open get() {
         return Event.ID.StreamStart;
      }


   public constructor(startMark: Mark?, endMark: Mark?) : super(startMark, endMark)
   public constructor() : super(null, null, 3, null)
   public override fun toString(): String {
      return "+STR";
   }
}
