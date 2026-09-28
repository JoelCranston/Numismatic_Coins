package it.krzeminski.snakeyaml.engine.kmp.nodes

import it.krzeminski.snakeyaml.engine.kmp.common.FlowStyle
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public class SequenceNode @JvmOverloads  public constructor(tag: Tag,
   value: List<Node>,
   flowStyle: FlowStyle,
   resolved: Boolean = true,
   startMark: Mark? = null,
   endMark: Mark? = null
) : CollectionNode(tag, flowStyle, startMark, endMark, resolved) {
   public open val value: List<Node>

   public open val nodeType: NodeType
      public open get() {
         return NodeType.SEQUENCE;
      }


   init {
      this.value = value;
   }

   public override fun toString(): String {
      return "<${(this.getClass()::class).getSimpleName()} (tag=${this.getTag()}, value=[${CollectionsKt.joinToString$default(
         this.getValue(), ",", null, null, 0, null, SequenceNode::toString$lambda$0, 30, null
      )}])>";
   }

   @JvmOverloads
   fun SequenceNode(tag: Tag, value: MutableList<Node>, flowStyle: FlowStyle, resolved: Boolean, startMark: Mark?) {
      this(tag, value, flowStyle, resolved, startMark, null, 32, null);
   }

   @JvmOverloads
   fun SequenceNode(tag: Tag, value: MutableList<Node>, flowStyle: FlowStyle, resolved: Boolean) {
      this(tag, value, flowStyle, resolved, null, null, 48, null);
   }

   @JvmOverloads
   fun SequenceNode(tag: Tag, value: MutableList<Node>, flowStyle: FlowStyle) {
      this(tag, value, flowStyle, false, null, null, 56, null);
   }

   @JvmStatic
   fun `toString$lambda$0`(node: Node): java.lang.CharSequence {
      val var1: java.lang.CharSequence;
      if (node is CollectionNode) {
         val var10000: StringBuilder = new StringBuilder().append("CollectionNode(size:");
         val var10001: java.util.List = (node as CollectionNode).getValue();
         var1 = var10000.append(if (var10001 != null) var10001.size() else null).append(')').toString();
      } else {
         var1 = node.toString();
      }

      return var1;
   }
}
