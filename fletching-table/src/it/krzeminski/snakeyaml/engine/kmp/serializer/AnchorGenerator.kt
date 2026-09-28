package it.krzeminski.snakeyaml.engine.kmp.serializer

import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node

public fun interface AnchorGenerator {
   public abstract fun nextAnchor(node: Node): Anchor {
   }
}
