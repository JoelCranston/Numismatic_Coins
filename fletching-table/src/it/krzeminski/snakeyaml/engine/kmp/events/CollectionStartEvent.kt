package it.krzeminski.snakeyaml.engine.kmp.events

import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.common.FlowStyle
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public abstract class CollectionStartEvent : NodeEvent {
   public final val tag: String?
   public final val implicit: Boolean
   public final val flowStyle: FlowStyle

   open fun CollectionStartEvent(anchor: Anchor?, tag: java.lang.String?, implicit: Boolean, flowStyle: FlowStyle, startMark: Mark?, endMark: Mark?) {
      super(anchor, startMark, endMark);
      this.tag = tag;
      this.implicit = implicit;
      this.flowStyle = flowStyle;
   }

   public fun isFlow(): Boolean {
      return FlowStyle.FLOW === this.flowStyle;
   }

   public fun isImplicit(): Boolean {
      return this.implicit;
   }

   public override fun toString(): String {
      val var1: StringBuilder = new StringBuilder();
      if (this.getAnchor() != null) {
         var1.append(" &${this.getAnchor()}");
      }

      if (!this.implicit && this.tag != null) {
         var1.append(" <${this.tag}>");
      }

      return var1.toString();
   }
}
