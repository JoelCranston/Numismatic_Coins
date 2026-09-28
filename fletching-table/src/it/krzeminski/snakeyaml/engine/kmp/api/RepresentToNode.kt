package it.krzeminski.snakeyaml.engine.kmp.api

import it.krzeminski.snakeyaml.engine.kmp.nodes.Node

public fun interface RepresentToNode {
   public abstract fun representData(data: Any): Node {
   }
}
