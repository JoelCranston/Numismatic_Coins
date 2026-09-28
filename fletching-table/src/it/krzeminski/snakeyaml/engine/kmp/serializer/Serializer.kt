package it.krzeminski.snakeyaml.engine.kmp.serializer

import it.krzeminski.snakeyaml.engine.kmp.api.DumpSettings
import it.krzeminski.snakeyaml.engine.kmp.comments.CommentLine
import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.emitter.Emitable
import it.krzeminski.snakeyaml.engine.kmp.events.AliasEvent
import it.krzeminski.snakeyaml.engine.kmp.events.CommentEvent
import it.krzeminski.snakeyaml.engine.kmp.events.DocumentEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.DocumentStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.ImplicitTuple
import it.krzeminski.snakeyaml.engine.kmp.events.MappingEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.MappingStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.ScalarEvent
import it.krzeminski.snakeyaml.engine.kmp.events.SequenceEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.SequenceStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.StreamEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.StreamStartEvent
import it.krzeminski.snakeyaml.engine.kmp.exceptions.YamlEngineException
import it.krzeminski.snakeyaml.engine.kmp.internal.utils.MergeUtils
import it.krzeminski.snakeyaml.engine.kmp.nodes.AnchorNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.MappingNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.nodes.NodeTuple
import it.krzeminski.snakeyaml.engine.kmp.nodes.ScalarNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.SequenceNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import java.util.LinkedHashMap
import java.util.LinkedHashSet
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Serializer.kt\nit/krzeminski/snakeyaml/engine/kmp/serializer/Serializer\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,232:1\n382#2,7:233\n*S KotlinDebug\n*F\n+ 1 Serializer.kt\nit/krzeminski/snakeyaml/engine/kmp/serializer/Serializer\n*L\n89#1:233,7\n*E\n"])
public class Serializer(settings: DumpSettings, emitable: Emitable) {
   private final val settings: DumpSettings
   private final val emitable: Emitable
   private final val serializedNodes: MutableSet<Node>
   private final val anchors: MutableMap<Node, Anchor?>
   private final val isDereferenceAliases: Boolean
   private final val recursive: IdentitySet<Node>
   private final val mergeUtils: MergeUtils

   init {
      this.settings = settings;
      this.emitable = emitable;
      this.serializedNodes = new LinkedHashSet<>();
      this.anchors = new LinkedHashMap<>();
      this.isDereferenceAliases = this.settings.isDereferenceAliases();
      this.recursive = new IdentitySet<>();
      this.mergeUtils = new MergeUtils(Serializer::mergeUtils$lambda$0);
   }

   public fun serializeDocument(node: Node) {
      this.emitable
         .emit(new DocumentStartEvent(this.settings.isExplicitStart(), this.settings.getYamlDirective(), this.settings.getTagDirective(), null, null, 24, null));
      this.anchorNode(node);
      if (this.settings.getExplicitRootTag() != null) {
         node.setTag(this.settings.getExplicitRootTag());
      }

      this.serializeNode(node);
      this.emitable.emit(new DocumentEndEvent(this.settings.isExplicitEnd(), null, null, 6, null));
      this.serializedNodes.clear();
      this.anchors.clear();
      this.recursive.clear();
   }

   public fun emitStreamStart() {
      this.emitable.emit(new StreamStartEvent());
   }

   public fun emitStreamEnd() {
      this.emitable.emit(new StreamEndEvent());
   }

   private fun anchorNode(node: Node) {
      val realNode: Node = if (node is AnchorNode) (node as AnchorNode).getRealNode() else node;
      if (this.anchors.containsKey(realNode)) {
         val `$this$getOrPut$iv`: java.util.Map = this.anchors;
         if (this.anchors.get(realNode) == null) {
            `$this$getOrPut$iv`.put(realNode, this.settings.getAnchorGenerator().nextAnchor(realNode));
         }
      } else {
         this.anchors.put(realNode, if (realNode.getAnchor() != null) this.settings.getAnchorGenerator().nextAnchor(realNode) else null);
         switch (Serializer.WhenMappings.$EnumSwitchMapping$0[realNode.getNodeType().ordinal()]) {
            case 1:
               if (realNode !is SequenceNode) {
                  throw new IllegalArgumentException("Failed requirement.".toString());
               }

               for (Node item : ((SequenceNode)realNode).getValue()) {
                  this.anchorNode(var13);
               }
               break;
            case 2:
               if (realNode !is MappingNode) {
                  throw new IllegalArgumentException("Failed requirement.".toString());
               }

               for (NodeTuple var11 : ((MappingNode)realNode).getValue()) {
                  val var16: Node = var11.component2();
                  this.anchorNode(var11.component1());
                  this.anchorNode(var16);
               }
            case 3:
            case 4:
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }
      }
   }

   private fun serializeNode(node: Node) {
      val realNode: Node = if (node is AnchorNode) (node as AnchorNode).getRealNode() else node;
      if (this.isDereferenceAliases && this.recursive.contains(node)) {
         throw new YamlEngineException("Cannot dereference aliases for recursive structures.");
      } else {
         this.recursive.add(node);
         val tAlias: Anchor = if (!this.isDereferenceAliases) this.anchors.get(realNode) else null;
         if (!this.isDereferenceAliases && this.serializedNodes.contains(realNode)) {
            this.emitable.emit(new AliasEvent(tAlias, null, null, 6, null));
         } else {
            this.serializedNodes.add(realNode);
            switch (Serializer.WhenMappings.$EnumSwitchMapping$0[realNode.getNodeType().ordinal()]) {
               case 1:
                  if (realNode !is SequenceNode) {
                     throw new IllegalArgumentException("Failed requirement.".toString());
                  }

                  this.serializeComments(realNode.getBlockComments());
                  this.emitable
                     .emit(
                        new SequenceStartEvent(
                           tAlias, realNode.getTag().getValue(), realNode.getTag() == Tag.SEQ, (realNode as SequenceNode).getFlowStyle(), null, null, 48, null
                        )
                     );

                  for (Node item : ((SequenceNode)realNode).getValue()) {
                     this.serializeNode(var17);
                  }

                  this.emitable.emit(new SequenceEndEvent());
                  this.serializeComments(realNode.getInLineComments());
                  this.serializeComments(realNode.getEndComments());
                  break;
               case 2:
                  if (realNode !is MappingNode) {
                     throw new IllegalArgumentException("Failed requirement.".toString());
                  }

                  this.serializeComments(realNode.getBlockComments());
                  if (!(realNode.getTag() == Tag.COMMENT)) {
                     var var9: java.util.List = (realNode as MappingNode).getValue();
                     if (this.isDereferenceAliases && (realNode as MappingNode).getHasMergeTag()) {
                        var9 = this.mergeUtils.flatten(realNode as MappingNode);
                     }

                     this.emitable
                        .emit(
                           new MappingStartEvent(
                              tAlias, realNode.getTag().getValue(), realNode.getTag() == Tag.MAP, (realNode as MappingNode).getFlowStyle(), null, null
                           )
                        );

                     for (NodeTuple var16 : map) {
                        val value: Node = var16.component2();
                        this.serializeNode(var16.component1());
                        this.serializeNode(value);
                     }

                     this.emitable.emit(new MappingEndEvent());
                     this.serializeComments(realNode.getInLineComments());
                     this.serializeComments(realNode.getEndComments());
                  }
                  break;
               case 3:
                  if (realNode !is ScalarNode) {
                     throw new IllegalArgumentException("Failed requirement.".toString());
                  }

                  this.serializeComments(realNode.getBlockComments());
                  this.emitable
                     .emit(
                        new ScalarEvent(
                           tAlias,
                           realNode.getTag().getValue(),
                           new ImplicitTuple(
                              realNode.getTag() == this.settings.getSchema().getScalarResolver().resolve((realNode as ScalarNode).getValue(), true),
                              realNode.getTag() == this.settings.getSchema().getScalarResolver().resolve((realNode as ScalarNode).getValue(), false)
                           ),
                           (realNode as ScalarNode).getValue(),
                           (realNode as ScalarNode).getScalarStyle(),
                           null,
                           null,
                           96,
                           null
                        )
                     );
                  this.serializeComments(realNode.getInLineComments());
                  this.serializeComments(realNode.getEndComments());
               case 4:
                  break;
               default:
                  throw new NoWhenBranchMatchedException();
            }
         }

         this.recursive.remove(node);
      }
   }

   private fun serializeComments(comments: List<CommentLine>?) {
      if (this.settings.getDumpComments() && comments != null) {
         for (CommentLine line : comments) {
            this.emitable.emit(new CommentEvent(line.commentType, line.value, line.startMark, line.endMark));
         }
      }
   }

   @JvmStatic
   fun `mergeUtils$lambda$0`(node: Node): MappingNode {
      if (node is MappingNode) {
         return node as MappingNode;
      } else {
         throw new YamlEngineException("expecting MappingNode while processing merge.");
      }
   }
}
