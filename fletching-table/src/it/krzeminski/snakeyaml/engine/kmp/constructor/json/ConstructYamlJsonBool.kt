package it.krzeminski.snakeyaml.engine.kmp.constructor.json

import it.krzeminski.snakeyaml.engine.kmp.constructor.ConstructScalar
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node

public class ConstructYamlJsonBool : ConstructScalar {
   public open fun construct(node: Node?): Boolean? {
      return ConstructScalar.BOOL_VALUES.get(this.constructScalar(node));
   }
}
