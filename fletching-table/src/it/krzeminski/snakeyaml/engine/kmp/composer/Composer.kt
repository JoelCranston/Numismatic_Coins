package it.krzeminski.snakeyaml.engine.kmp.composer

import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings
import it.krzeminski.snakeyaml.engine.kmp.comments.CommentEventsCollector
import it.krzeminski.snakeyaml.engine.kmp.comments.CommentLine
import it.krzeminski.snakeyaml.engine.kmp.comments.CommentType
import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.common.FlowStyle
import it.krzeminski.snakeyaml.engine.kmp.composer.Composer.mergeUtils.1
import it.krzeminski.snakeyaml.engine.kmp.events.AliasEvent
import it.krzeminski.snakeyaml.engine.kmp.events.Event
import it.krzeminski.snakeyaml.engine.kmp.events.MappingStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.NodeEvent
import it.krzeminski.snakeyaml.engine.kmp.events.ScalarEvent
import it.krzeminski.snakeyaml.engine.kmp.events.SequenceStartEvent
import it.krzeminski.snakeyaml.engine.kmp.exceptions.ComposerException
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import it.krzeminski.snakeyaml.engine.kmp.exceptions.YamlEngineException
import it.krzeminski.snakeyaml.engine.kmp.internal.utils.MergeUtils
import it.krzeminski.snakeyaml.engine.kmp.nodes.MappingNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.nodes.NodeTuple
import it.krzeminski.snakeyaml.engine.kmp.nodes.NodeType
import it.krzeminski.snakeyaml.engine.kmp.nodes.ScalarNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.SequenceNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.parser.Parser
import it.krzeminski.snakeyaml.engine.kmp.resolver.ScalarResolver
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.LinkedHashSet
import kotlin.jvm.internal.markers.KMappedMarker

public class Composer(settings: LoadSettings, parser: Parser) : java.util.Iterator<Node>, KMappedMarker {
   private final val settings: LoadSettings
   private final val parser: Parser
   private final val scalarResolver: ScalarResolver
   private final val anchors: MutableMap<Anchor, Node>
   private final val recursiveNodes: MutableSet<Node>
   private final val blockCommentsCollector: CommentEventsCollector
   private final val inlineCommentsCollector: CommentEventsCollector
   private final var nonScalarAliasesCount: Int
   private final val mergeUtils: MergeUtils

   init {
      this.settings = settings;
      this.parser = parser;
      this.scalarResolver = this.settings.getSchema().getScalarResolver();
      this.anchors = new LinkedHashMap<>();
      this.recursiveNodes = new LinkedHashSet<>();
      this.blockCommentsCollector = new CommentEventsCollector(this.parser, CommentType.BLANK_LINE, CommentType.BLOCK);
      this.inlineCommentsCollector = new CommentEventsCollector(this.parser, CommentType.IN_LINE);
      this.mergeUtils = new MergeUtils(new 1(this));
   }

   public override operator fun hasNext(): Boolean {
      if (this.parser.checkEvent(Event.ID.StreamStart)) {
         this.parser.next();
      }

      return !this.parser.checkEvent(Event.ID.StreamEnd);
   }

   public fun getSingleNode(): Node? {
      this.parser.next();
      val document: Node = if (!this.parser.checkEvent(Event.ID.StreamEnd)) this.next() else null;
      if (document != null) {
         document.setInLineComments(this.inlineCommentsCollector.collectEvents().consume());
         document.setBlockComments(this.blockCommentsCollector.collectEvents().consume());
      }

      if (!this.parser.checkEvent(Event.ID.StreamEnd)) {
         throw new ComposerException(
            "expected a single document in the stream",
            if (document != null) document.getStartMark() else null,
            "but found another document",
            this.parser.next().getStartMark()
         );
      } else {
         this.parser.next();
         return document;
      }
   }

   public open operator fun next(): Node {
      this.blockCommentsCollector.collectEvents();
      if (this.parser.checkEvent(Event.ID.StreamEnd)) {
         val var7: java.util.List = this.blockCommentsCollector.consume();
         val startMark: Mark = CollectionsKt.first(var7).startMark;
         val node: Node = new MappingNode(Tag.COMMENT, new ArrayList(), FlowStyle.BLOCK, false, false, startMark, null, 8, null);
         node.setBlockComments(var7);
         return node;
      } else {
         this.parser.next();
         val node: Node = this.composeNode(null);
         this.blockCommentsCollector.collectEvents();
         if (!this.blockCommentsCollector.isEmpty()) {
            node.setEndComments(this.blockCommentsCollector.consume());
         }

         this.parser.next();
         this.anchors.clear();
         this.recursiveNodes.clear();
         this.nonScalarAliasesCount = 0;
         return node;
      }
   }

   private fun composeNode(parent: Node?): Node {
      this.blockCommentsCollector.collectEvents();
      if (parent != null) {
         this.recursiveNodes.add(parent);
      }

      val var6: Node;
      if (this.parser.checkEvent(Event.ID.Alias)) {
         val var10000: Event = this.parser.next();
         val event: AliasEvent = var10000 as AliasEvent;
         val anchor: Anchor = (var10000 as AliasEvent).getAlias();
         val var9: Node = this.anchors.get(anchor);
         if (var9 == null) {
            throw new ComposerException("found undefined alias $anchor", event.getStartMark(), null, null, 12, null);
         }

         var6 = var9;
         if (var9.getNodeType() != NodeType.SCALAR) {
            val var5: Int = this.nonScalarAliasesCount++;
            if (this.nonScalarAliasesCount > this.settings.getMaxAliasesForCollections()) {
               throw new YamlEngineException("Number of aliases for non-scalar nodes exceeds the specified max=${this.settings.getMaxAliasesForCollections()}");
            }
         }

         if (this.recursiveNodes.remove(var9)) {
            var9.setRecursive(true);
         }

         this.blockCommentsCollector.consume();
         this.inlineCommentsCollector.collectEvents().consume();
      } else {
         val var10: Event = this.parser.peekEvent();
         val anchorx: Anchor = (var10 as NodeEvent).getAnchor();
         var6 = if (this.parser.checkEvent(Event.ID.Scalar))
            this.composeScalarNode(anchorx, this.blockCommentsCollector.consume())
            else
            (if (this.parser.checkEvent(Event.ID.SequenceStart)) this.composeSequenceNode(anchorx) else this.composeMappingNode(anchorx));
      }

      if (parent != null) {
         this.recursiveNodes.remove(parent);
      }

      return var6;
   }

   private fun registerAnchor(anchor: Anchor, node: Node) {
      this.anchors.put(anchor, node);
      node.setAnchor(anchor);
   }

   private fun composeScalarNode(anchor: Anchor?, blockComments: List<CommentLine>): ScalarNode {
      val var10000: Event = this.parser.next();
      val ev: ScalarEvent = var10000 as ScalarEvent;
      val tag: java.lang.String = (var10000 as ScalarEvent).getTag();
      val var12: Boolean;
      val var13: Tag;
      if (tag != null && !(tag == "!")) {
         var13 = new Tag(tag);
         var12 = false;
      } else {
         var13 = this.scalarResolver.resolve(ev.getValue(), ev.getImplicit().canOmitTagInPlainScalar());
         var12 = true;
      }

      val node: ScalarNode = new ScalarNode(var13, ev.getValue(), ev.getScalarStyle(), var12, ev.getStartMark(), ev.getEndMark());
      if (anchor != null) {
         this.registerAnchor(anchor, node);
      }

      node.setBlockComments(blockComments);
      node.setInLineComments(this.inlineCommentsCollector.collectEvents().consume());
      return node;
   }

   private fun composeSequenceNode(anchor: Anchor?): SequenceNode {
      val var10000: Event = this.parser.next();
      val startEvent: SequenceStartEvent = var10000 as SequenceStartEvent;
      val tag: java.lang.String = (var10000 as SequenceStartEvent).getTag();
      val var9: Tag;
      val var10: Boolean;
      if (tag != null && !(tag == "!")) {
         var9 = new Tag(tag);
         var10 = false;
      } else {
         var9 = Tag.SEQ;
         var10 = true;
      }

      val children: ArrayList = new ArrayList();
      val node: SequenceNode = new SequenceNode(var9, children, startEvent.getFlowStyle(), var10, startEvent.getStartMark(), null);
      if (startEvent.isFlow()) {
         node.setBlockComments(this.blockCommentsCollector.consume());
      }

      if (anchor != null) {
         this.registerAnchor(anchor, node);
      }

      while (!this.parser.checkEvent(Event.ID.SequenceEnd)) {
         this.blockCommentsCollector.collectEvents();
         if (this.parser.checkEvent(Event.ID.SequenceEnd)) {
            break;
         }

         children.add(this.composeNode(node));
      }

      if (startEvent.isFlow()) {
         node.setInLineComments(this.inlineCommentsCollector.collectEvents().consume());
      }

      node.setEndMark(this.parser.next().getEndMark());
      this.inlineCommentsCollector.collectEvents();
      if (!this.inlineCommentsCollector.isEmpty()) {
         node.setInLineComments(this.inlineCommentsCollector.consume());
      }

      return node;
   }

   private fun composeMappingNode(anchor: Anchor?): MappingNode {
      val var10000: Event = this.parser.next();
      val startEvent: MappingStartEvent = var10000 as MappingStartEvent;
      val tag: java.lang.String = (var10000 as MappingStartEvent).getTag();
      val var10: Tag;
      val var11: Boolean;
      if (tag != null && !(tag == "!")) {
         var10 = new Tag(tag);
         var11 = false;
      } else {
         var10 = Tag.MAP;
         var11 = true;
      }

      val children: java.util.List = new ArrayList();
      val node: MappingNode = new MappingNode(var10, children, startEvent.getFlowStyle(), false, var11, startEvent.getStartMark(), null, 8, null);
      if (startEvent.isFlow()) {
         node.setBlockComments(this.blockCommentsCollector.consume());
      }

      if (anchor != null) {
         this.registerAnchor(anchor, node);
      }

      while (!this.parser.checkEvent(Event.ID.MappingEnd)) {
         this.blockCommentsCollector.collectEvents();
         if (this.parser.checkEvent(Event.ID.MappingEnd)) {
            break;
         }

         this.composeMappingChildren(children, node);
      }

      if (startEvent.isFlow()) {
         node.setInLineComments(this.inlineCommentsCollector.collectEvents().consume());
      }

      node.setEndMark(this.parser.next().getEndMark());
      this.inlineCommentsCollector.collectEvents();
      if (!this.inlineCommentsCollector.isEmpty()) {
         node.setInLineComments(this.inlineCommentsCollector.consume());
      }

      if (node.getHasMergeTag()) {
         node.setValue(this.mergeUtils.flatten(node));
         node.setHasMergeTag(false);
      }

      return node;
   }

   private fun composeMappingChildren(children: MutableList<NodeTuple>, node: MappingNode) {
      val itemKey: Node = this.composeKeyNode(node);
      if (itemKey.getTag() == Tag.MERGE) {
         node.setHasMergeTag(true);
      }

      children.add(new NodeTuple(itemKey, this.composeValueNode(node)));
   }

   private fun asMappingNode(node: Node): MappingNode {
      if (node is MappingNode) {
         return node as MappingNode;
      } else {
         val ev: Node = this.anchors.get(node.getAnchor());
         if (ev is MappingNode) {
            return ev as MappingNode;
         } else {
            throw new ComposerException("Expected mapping node or an anchor referencing mapping", this.parser.peekEvent().getStartMark(), null, null, 12, null);
         }
      }
   }

   private fun composeKeyNode(node: MappingNode): Node {
      return this.composeNode(node);
   }

   private fun composeValueNode(node: MappingNode): Node {
      return this.composeNode(node);
   }

   override fun remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
