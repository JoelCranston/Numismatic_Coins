package it.krzeminski.snakeyaml.engine.kmp.constructor

import it.krzeminski.snakeyaml.engine.kmp.api.ConstructNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.nodes.ScalarNode

public abstract class ConstructScalar : ConstructNode {
   protected open fun constructScalar(node: Node?): String {
      return (node as ScalarNode).getValue();
   }

   override fun constructRecursive(node: Node, `object`: Any) {
      ConstructNode.super.constructRecursive(node, `object`);
   }

   public companion object {
      internal final val BOOL_VALUES: Map<String, Boolean>
   }
}
