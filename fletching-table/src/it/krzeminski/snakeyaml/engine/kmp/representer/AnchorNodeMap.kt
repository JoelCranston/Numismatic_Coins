package it.krzeminski.snakeyaml.engine.kmp.representer

import it.krzeminski.snakeyaml.engine.kmp.nodes.AnchorNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node

private class AnchorNodeMap : IdentityLikeMap<Node> {
   public open fun put(key: Any?, value: Node): Node? {
      return super.put(key, new AnchorNode(value));
   }
}
