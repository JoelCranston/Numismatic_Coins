package it.krzeminski.snakeyaml.engine.kmp.events

import it.krzeminski.snakeyaml.engine.kmp.events.Event.ID
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public class DocumentEndEvent @JvmOverloads  public constructor(isExplicit: Boolean, startMark: Mark? = null, endMark: Mark? = null) : Event(startMark, endMark) {
   public final val isExplicit: Boolean

   public open val eventId: ID
      public open get() {
         return Event.ID.DocumentEnd;
      }


   init {
      this.isExplicit = isExplicit;
   }

   public override fun toString(): String {
      val var1: StringBuilder = new StringBuilder();
      var1.append("-DOC");
      if (this.isExplicit) {
         var1.append(" ...");
      }

      return var1.toString();
   }

   @JvmOverloads
   fun DocumentEndEvent(isExplicit: Boolean, startMark: Mark?) {
      this(isExplicit, startMark, null, 4, null);
   }

   @JvmOverloads
   fun DocumentEndEvent(isExplicit: Boolean) {
      this(isExplicit, null, null, 6, null);
   }
}
