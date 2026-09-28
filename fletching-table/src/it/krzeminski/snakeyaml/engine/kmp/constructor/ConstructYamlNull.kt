package it.krzeminski.snakeyaml.engine.kmp.constructor

import it.krzeminski.snakeyaml.engine.kmp.nodes.Node

public class ConstructYamlNull : ConstructScalar {
   public override fun construct(node: Node?): Any? {
      return null;
   }
}
