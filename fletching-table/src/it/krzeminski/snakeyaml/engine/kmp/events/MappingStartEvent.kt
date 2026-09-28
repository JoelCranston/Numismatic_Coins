package it.krzeminski.snakeyaml.engine.kmp.events

import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.common.FlowStyle
import it.krzeminski.snakeyaml.engine.kmp.events.Event.ID
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public class MappingStartEvent @JvmOverloads  public constructor(anchor: Anchor?,
   tag: String?,
   implicit: Boolean,
   flowStyle: FlowStyle,
   startMark: Mark? = null,
   endMark: Mark? = null
) : CollectionStartEvent(anchor, tag, implicit, flowStyle, startMark, endMark) {
   public open val eventId: ID
      public open get() {
         return Event.ID.MappingStart;
      }


   public override fun toString(): String {
      val var1: StringBuilder = new StringBuilder();
      var1.append("+MAP");
      if (this.getFlowStyle() === FlowStyle.FLOW) {
         var1.append(" {}");
      }

      var1.append(super.toString());
      return var1.toString();
   }

   @JvmOverloads
   fun MappingStartEvent(anchor: Anchor?, tag: java.lang.String?, implicit: Boolean, flowStyle: FlowStyle, startMark: Mark?) {
      this(anchor, tag, implicit, flowStyle, startMark, null, 32, null);
   }

   @JvmOverloads
   fun MappingStartEvent(anchor: Anchor?, tag: java.lang.String?, implicit: Boolean, flowStyle: FlowStyle) {
      this(anchor, tag, implicit, flowStyle, null, null, 48, null);
   }
}
