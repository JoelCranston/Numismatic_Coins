package it.krzeminski.snakeyaml.engine.kmp.constructor.core

import it.krzeminski.snakeyaml.engine.kmp.constructor.json.ConstructYamlJsonFloat
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.nodes.ScalarNode
import java.util.Locale

public class ConstructYamlCoreFloat : ConstructYamlJsonFloat {
   protected override fun constructScalar(node: Node?): String {
      val var10000: java.lang.String = (node as ScalarNode).getValue().toLowerCase(Locale.ROOT);
      return var10000;
   }
}
