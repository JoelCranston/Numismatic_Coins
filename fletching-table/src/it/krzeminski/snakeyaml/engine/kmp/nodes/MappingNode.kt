package it.krzeminski.snakeyaml.engine.kmp.nodes

import it.krzeminski.snakeyaml.engine.kmp.common.FlowStyle
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public class MappingNode @JvmOverloads  public constructor(tag: Tag,
   value: List<NodeTuple>,
   flowStyle: FlowStyle,
   hasMergeTag: Boolean = false,
   resolved: Boolean = true,
   startMark: Mark? = null,
   endMark: Mark? = null
) : CollectionNode(tag, flowStyle, startMark, endMark, resolved) {
   public open var value: List<NodeTuple>
      internal final set

   public final var hasMergeTag: Boolean
      internal set

   public open val nodeType: NodeType
      public open get() {
         return NodeType.MAPPING;
      }


   init {
      this.value = value;
      this.hasMergeTag = hasMergeTag;
   }

   public override fun toString(): String {
      return "<${(this.getClass()::class).getSimpleName()} (tag=${this.getTag()}, values=${CollectionsKt.joinToString$default(
         this.getValue(), "", null, null, 0, null, MappingNode::toString$lambda$0, 30, null
      )})>";
   }

   @JvmOverloads
   fun MappingNode(tag: Tag, value: MutableList<NodeTuple>, flowStyle: FlowStyle, hasMergeTag: Boolean, resolved: Boolean, startMark: Mark?) {
      this(tag, value, flowStyle, hasMergeTag, resolved, startMark, null, 64, null);
   }

   @JvmOverloads
   fun MappingNode(tag: Tag, value: MutableList<NodeTuple>, flowStyle: FlowStyle, hasMergeTag: Boolean, resolved: Boolean) {
      this(tag, value, flowStyle, hasMergeTag, resolved, null, null, 96, null);
   }

   @JvmOverloads
   fun MappingNode(tag: Tag, value: MutableList<NodeTuple>, flowStyle: FlowStyle, hasMergeTag: Boolean) {
      this(tag, value, flowStyle, hasMergeTag, false, null, null, 112, null);
   }

   @JvmOverloads
   fun MappingNode(tag: Tag, value: MutableList<NodeTuple>, flowStyle: FlowStyle) {
      this(tag, value, flowStyle, false, false, null, null, 120, null);
   }

   @JvmStatic
   fun `toString$lambda$0`(node: NodeTuple): java.lang.CharSequence {
      val var2: java.lang.String;
      if (node.getValueNode() is CollectionNode) {
         val var10000: StringBuilder = new StringBuilder().append("CollectionNode(size:");
         val var10001: java.util.List = (node.getValueNode() as CollectionNode).getValue();
         var2 = var10000.append(if (var10001 != null) var10001.size() else null).append(')').toString();
      } else {
         var2 = node.toString();
      }

      return "{ key=${node.getKeyNode()}; value=$var2 }";
   }
}
