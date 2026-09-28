package it.krzeminski.snakeyaml.engine.kmp.representer

import it.krzeminski.snakeyaml.engine.kmp.nodes.Node

public interface Representer {
   public abstract fun represent(data: Any?): Node {
   }
}
