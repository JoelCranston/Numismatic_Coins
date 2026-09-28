package it.krzeminski.snakeyaml.engine.kmp.representer

import it.krzeminski.snakeyaml.engine.kmp.api.DumpSettings
import it.krzeminski.snakeyaml.engine.kmp.api.RepresentToNode
import it.krzeminski.snakeyaml.engine.kmp.common.FlowStyle
import it.krzeminski.snakeyaml.engine.kmp.common.ScalarStyle
import it.krzeminski.snakeyaml.engine.kmp.exceptions.YamlEngineException
import it.krzeminski.snakeyaml.engine.kmp.nodes.MappingNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.nodes.NodeTuple
import it.krzeminski.snakeyaml.engine.kmp.nodes.ScalarNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.SequenceNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass

@SourceDebugExtension(["SMAP\nBaseRepresenter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseRepresenter.kt\nit/krzeminski/snakeyaml/engine/kmp/representer/BaseRepresenter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,267:1\n1634#2,3:268\n2746#2,3:271\n1634#2,3:275\n1761#2,3:278\n1#3:274\n*S KotlinDebug\n*F\n+ 1 BaseRepresenter.kt\nit/krzeminski/snakeyaml/engine/kmp/representer/BaseRepresenter\n*L\n164#1:268,3\n169#1:271,3\n211#1:275,3\n216#1:278,3\n*E\n"])
public abstract class BaseRepresenter : Representer {
   protected final val defaultScalarStyle: ScalarStyle
   protected final val defaultFlowStyle: FlowStyle
   protected final val representers: MutableMap<KClass<*>, RepresentToNode>
   protected final val parentClassRepresenters: MutableMap<KClass<*>, RepresentToNode>
   private final val representedObjects: MutableMap<Any?, Node>
   private final var objectToRepresent: Any?

   open fun BaseRepresenter(defaultScalarStyle: ScalarStyle, defaultFlowStyle: FlowStyle) {
      this.defaultScalarStyle = defaultScalarStyle;
      this.defaultFlowStyle = defaultFlowStyle;
      this.representers = new LinkedHashMap<>();
      this.parentClassRepresenters = new LinkedHashMap<>();
      this.representedObjects = new AnchorNodeMap();
   }

   open fun BaseRepresenter(settings: DumpSettings) {
      this(settings.getDefaultScalarStyle(), settings.getDefaultFlowStyle());
   }

   protected open fun nullRepresenter(): Node {
      return representScalar$default(this, Tag.NULL, "null", null, 4, null);
   }

   public override fun represent(data: Any?): Node {
      val node: Node = this.representData(data);
      this.representedObjects.clear();
      this.objectToRepresent = null;
      return node;
   }

   private fun representData(data: Any?): Node {
      this.objectToRepresent = data;
      var var10000: Any = this.representedObjects.get(this.objectToRepresent);
      if (var10000 == null) {
         var10000 = if (data == null) this.nullRepresenter() else this.findRepresenterFor(data).representData(data);
      }

      return var10000 as Node;
   }

   private fun findRepresenterFor(data: Any): RepresentToNode {
      val var10000: Any = this.representers.get(data.getClass()::class);
      if (var10000 == null) {
         for (Entry var5 : this.parentClassRepresenters.entrySet()) {
            val key: KClass = var5.getKey() as KClass;
            val value: RepresentToNode = var5.getValue() as RepresentToNode;
            if (key.isInstance(data)) {
               return value;
            }
         }

         throw new YamlEngineException("Representer is not defined for class ${(data.getClass()::class).getSimpleName()}");
      } else {
         return var10000 as RepresentToNode;
      }
   }

   protected fun representScalar(tag: Tag, value: String, style: ScalarStyle = ScalarStyle.PLAIN): ScalarNode {
      return new ScalarNode(tag, value, if (style === ScalarStyle.PLAIN) this.defaultScalarStyle else style, false, null, null, 56, null);
   }

   protected fun representSequence(tag: Tag, sequence: Iterable<*>, flowStyle: FlowStyle): SequenceNode {
      val value: ArrayList = new ArrayList(if ((sequence as? java.util.List) != null) (sequence as? java.util.List).size() else 10);
      val node: SequenceNode = new SequenceNode(tag, value, flowStyle, false, null, null, 56, null);
      this.representedObjects.put(this.objectToRepresent, node);
      val `$i$f$none`: java.util.Collection = value;

      for (Object item$iv : sequence) {
         `$i$f$none`.add(this.representData(it));
      }

      if (flowStyle === FlowStyle.AUTO) {
         var var10000: SequenceNode = node;
         val var10001: FlowStyle;
         if (this.defaultFlowStyle != FlowStyle.AUTO) {
            var10001 = this.defaultFlowStyle;
         } else {
            val `$this$none$iv`: java.lang.Iterable = value;
            var var20: Boolean;
            if (value is java.util.Collection && (value as java.util.Collection).isEmpty()) {
               var20 = true;
            } else {
               val var17: java.util.Iterator = `$this$none$iv`.iterator();

               while (true) {
                  if (!var17.hasNext()) {
                     var20 = true;
                     break;
                  }

                  val var19: Node = var17.next() as Node;
                  if (var19 is ScalarNode && (var19 as ScalarNode).isPlain()) {
                     var20 = false;
                     break;
                  }
               }
            }

            var10000 = node;
            var10001 = if (var20) FlowStyle.BLOCK else FlowStyle.FLOW;
         }

         var10000.setFlowStyle(var10001);
      }

      return node;
   }

   protected open fun kotlin.collections.Map.Entry<*, *>.toNodeTuple(): NodeTuple {
      return this.toNodeTupleImpl(`$this$toNodeTuple`);
   }

   @JvmName(name = "toNodeTupleImpl")
   protected fun toNodeTuple(entry: kotlin.collections.Map.Entry<*, *>): NodeTuple {
      return new NodeTuple(this.representData(entry.getKey()), this.representData(entry.getValue()));
   }

   protected fun representMapping(tag: Tag, mapping: Map<*, *>, flowStyle: FlowStyle): MappingNode {
      val value: ArrayList = new ArrayList(mapping.size());
      val node: MappingNode = new MappingNode(tag, value, flowStyle, false, false, null, null, 120, null);
      this.representedObjects.put(this.objectToRepresent, node);
      val `$this$mapTo$iv`: java.lang.Iterable = mapping.entrySet();
      val `destination$iv`: java.util.Collection = value;

      for (Object item$iv : $this$mapTo$iv) {
         `destination$iv`.add(this.toNodeTuple(`$i$f$any` as MutableMap.MutableEntry<*, *>));
      }

      if (flowStyle === FlowStyle.AUTO) {
         var var10000: MappingNode = node;
         val var10001: FlowStyle;
         if (BaseRepresenter.WhenMappings.$EnumSwitchMapping$0[this.defaultFlowStyle.ordinal()] != 1) {
            var10001 = this.defaultFlowStyle;
         } else {
            val var19: java.lang.Iterable = value;
            var var23: Boolean;
            if (value is java.util.Collection && (value as java.util.Collection).isEmpty()) {
               var23 = false;
            } else {
               val var21: java.util.Iterator = var19.iterator();

               while (true) {
                  if (!var21.hasNext()) {
                     var23 = false;
                     break;
                  }

                  val var13: NodeTuple = var21.next() as NodeTuple;
                  val keyNode: Node = var13.component1();
                  val valueNode: Node = var13.component2();
                  if (keyNode !is ScalarNode || !(keyNode as ScalarNode).isPlain() || valueNode !is ScalarNode || !(valueNode as ScalarNode).isPlain()) {
                     var23 = true;
                     break;
                  }
               }
            }

            var10000 = node;
            var10001 = if (var23) FlowStyle.BLOCK else FlowStyle.FLOW;
         }

         var10000.setFlowStyle(var10001);
      }

      return node;
   }

   open fun BaseRepresenter() {
      this(null, null, 3, null);
   }
}
