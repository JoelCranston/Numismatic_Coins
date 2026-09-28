package it.krzeminski.snakeyaml.engine.kmp.constructor.json

import it.krzeminski.snakeyaml.engine.kmp.constructor.ConstructScalar
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import java.util.UUID

public class ConstructUuidClass : ConstructScalar {
   public open fun construct(node: Node?): UUID {
      val var10000: UUID = UUID.fromString(this.constructScalar(node));
      return var10000;
   }
}
