package it.krzeminski.snakeyaml.engine.kmp.events

import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public abstract class NodeEvent : Event {
   public final val anchor: Anchor?

   open fun NodeEvent(anchor: Anchor?, startMark: Mark?, endMark: Mark?) {
      super(startMark, endMark);
      this.anchor = anchor;
   }
}
