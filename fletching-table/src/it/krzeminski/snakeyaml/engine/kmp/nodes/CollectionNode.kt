package it.krzeminski.snakeyaml.engine.kmp.nodes

import it.krzeminski.snakeyaml.engine.kmp.common.FlowStyle
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public sealed class CollectionNode<T> @JvmOverloads  protected constructor(tag: Tag,
   flowStyle: FlowStyle,
   startMark: Mark?,
   endMark: Mark?,
   resolved: Boolean = true
) : Node(tag, startMark, endMark, resolved) {
   public final var flowStyle: FlowStyle
      internal set

   public abstract val value: List<Any>?

   init {
      this.flowStyle = flowStyle;
   }

   public fun setEndMark(value: Mark?) {
      this.endMark = value;
   }

   @JvmOverloads
   fun CollectionNode(tag: Tag, flowStyle: FlowStyle, startMark: Mark, endMark: Mark) {
      this(tag, flowStyle, startMark, endMark, false, 16, null);
   }
}
