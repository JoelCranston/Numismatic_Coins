package it.krzeminski.snakeyaml.engine.kmp.constructor.core

import it.krzeminski.snakeyaml.engine.kmp.constructor.ConstructScalar
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import java.util.Locale

public class ConstructYamlCoreBool : ConstructScalar {
   public open fun construct(node: Node?): Boolean? {
      val scalar: java.lang.String = this.constructScalar(node);
      val var10000: java.util.Map = ConstructScalar.BOOL_VALUES;
      val var10001: java.lang.String = scalar.toLowerCase(Locale.ROOT);
      return var10000.get(var10001) as java.lang.Boolean;
   }
}
