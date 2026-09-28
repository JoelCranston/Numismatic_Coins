package it.krzeminski.snakeyaml.engine.kmp.constructor.json

import it.krzeminski.snakeyaml.engine.kmp.constructor.ConstructScalar
import it.krzeminski.snakeyaml.engine.kmp.exceptions.ConstructorException
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.nodes.NodeType
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.resolver.ScalarResolver
import java.util.Optional

public class ConstructOptionalClass(scalarResolver: ScalarResolver) : ConstructScalar {
   private final val scalarResolver: ScalarResolver

   init {
      this.scalarResolver = scalarResolver;
   }

   public open fun construct(node: Node?): Optional<Any> {
      if ((if (node != null) node.getNodeType() else null) != NodeType.SCALAR) {
         throw new ConstructorException("while constructing Optional", null, "found non scalar node", node.getStartMark(), null, 16, null);
      } else {
         val value: java.lang.String = this.constructScalar(node);
         val var10000: Optional;
         if (this.scalarResolver.resolve(value, true) == Tag.NULL) {
            var10000 = Optional.empty();
         } else {
            var10000 = Optional.of(value);
         }

         return var10000;
      }
   }
}
