package it.krzeminski.snakeyaml.engine.kmp.events

import it.krzeminski.snakeyaml.engine.kmp.common.SpecVersion
import it.krzeminski.snakeyaml.engine.kmp.events.Event.ID
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public class DocumentStartEvent @JvmOverloads  public constructor(explicit: Boolean,
   specVersion: SpecVersion?,
   tags: Map<String, String>,
   startMark: Mark? = null,
   endMark: Mark? = null
) : Event(startMark, endMark) {
   public final val explicit: Boolean
   public final val specVersion: SpecVersion?
   public final val tags: Map<String, String>

   public open val eventId: ID
      public open get() {
         return Event.ID.DocumentStart;
      }


   init {
      this.explicit = explicit;
      this.specVersion = specVersion;
      this.tags = tags;
   }

   public override fun toString(): String {
      val var1: StringBuilder = new StringBuilder();
      var1.append("+DOC");
      if (this.explicit) {
         var1.append(" ---");
      }

      return var1.toString();
   }

   @JvmOverloads
   fun DocumentStartEvent(explicit: Boolean, specVersion: SpecVersion?, tags: MutableMap<java.lang.String, java.lang.String>, startMark: Mark?) {
      this(explicit, specVersion, tags, startMark, null, 16, null);
   }

   @JvmOverloads
   fun DocumentStartEvent(explicit: Boolean, specVersion: SpecVersion?, tags: MutableMap<java.lang.String, java.lang.String>) {
      this(explicit, specVersion, tags, null, null, 24, null);
   }
}
