package it.krzeminski.snakeyaml.engine.kmp.events

import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.events.Event.ID
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public class AliasEvent @JvmOverloads  public constructor(anchor: Anchor?, startMark: Mark? = null, endMark: Mark? = null) : NodeEvent(
      anchor, startMark, endMark
   ) {
   public final val alias: Anchor

   public open val eventId: ID
      public open get() {
         return Event.ID.Alias;
      }


   public override fun toString(): String {
      return "=ALI *${this.alias}";
   }

   @JvmOverloads
   fun AliasEvent(anchor: Anchor?, startMark: Mark?) {
      this(anchor, startMark, null, 4, null);
   }

   @JvmOverloads
   fun AliasEvent(anchor: Anchor?) {
      this(anchor, null, null, 6, null);
   }
}
