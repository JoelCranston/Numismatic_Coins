package it.krzeminski.snakeyaml.engine.kmp.events

import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public abstract class CollectionEndEvent : Event {
   open fun CollectionEndEvent(startMark: Mark?, endMark: Mark?) {
      super(startMark, endMark);
   }

   open fun CollectionEndEvent() {
      super(null, null, 3, null);
   }
}
