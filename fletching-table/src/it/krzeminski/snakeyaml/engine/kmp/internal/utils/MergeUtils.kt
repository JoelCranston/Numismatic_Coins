package it.krzeminski.snakeyaml.engine.kmp.internal.utils

import it.krzeminski.snakeyaml.engine.kmp.nodes.MappingNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.nodes.NodeTuple
import it.krzeminski.snakeyaml.engine.kmp.nodes.ScalarNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.SequenceNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import java.util.ArrayList
import java.util.HashSet

internal class MergeUtils(asMappingNode: (Node) -> MappingNode) {
   private final val asMappingNode: (Node) -> MappingNode

   init {
      this.asMappingNode = asMappingNode;
   }

   public fun flatten(node: MappingNode): List<NodeTuple> {
      var toProcess: java.util.List = node.getValue();
      var result: java.util.List = toProcess;
      var process: Boolean = true;

      while (process) {
         process = false;
         val updated: ArrayList = new ArrayList(toProcess.size());
         val keys: HashSet = new HashSet(toProcess.size());
         val merges: ArrayList = new ArrayList(2);

         for (NodeTuple tuple : toProcess) {
            val valueNode: Node = tuple.getKeyNode();
            if (valueNode.getTag() == Tag.MERGE) {
               merges.add(tuple);
            } else {
               updated.add(tuple);
               if (valueNode is ScalarNode) {
                  keys.add((valueNode as ScalarNode).getValue());
               }
            }
         }

         var var10000: java.util.Iterator = merges.iterator();
         val var15: java.util.Iterator = var10000;

         while (var15.hasNext()) {
            var10000 = (java.util.Iterator)var15.next();
            val var17: Node = (var10000 as NodeTuple).getValueNode();
            if (var17 is SequenceNode) {
               for (Node ref : ((SequenceNode)valueNode).getValue()) {
                  val mergable: MappingNode = this.asMappingNode.invoke(var19);
                  process = process || mergable.getHasMergeTag();
                  val filtered: Pair = this.filter(mergable.getValue(), keys);
                  CollectionsKt.addAll(updated, filtered.getFirst() as java.lang.Iterable);
                  CollectionsKt.addAll(keys, filtered.getSecond() as java.lang.Iterable);
               }
            } else {
               val mergable: MappingNode = this.asMappingNode.invoke(var17);
               process = process || mergable.getHasMergeTag();
               val filtered: Pair = this.filter(mergable.getValue(), keys);
               CollectionsKt.addAll(updated, filtered.getFirst() as java.lang.Iterable);
               CollectionsKt.addAll(keys, filtered.getSecond() as java.lang.Iterable);
            }
         }

         result = updated;
         if (process) {
            toProcess = updated;
         }
      }

      return result;
   }

   private fun filter(mergables: List<NodeTuple>, filter: Set<String>): Pair<List<NodeTuple>, Set<String>> {
      val size: Int = mergables.size();
      val keys: HashSet = new HashSet(size);
      val result: ArrayList = new ArrayList(size);

      for (NodeTuple tuple : mergables) {
         val key: Node = tuple.getKeyNode();
         if (key is ScalarNode) {
            val nodeValue: java.lang.String = (key as ScalarNode).getValue();
            if (!filter.contains(nodeValue)) {
               result.add(tuple);
               keys.add(nodeValue);
            }
         } else {
            result.add(tuple);
         }
      }

      return new Pair<>(result, keys);
   }
}
