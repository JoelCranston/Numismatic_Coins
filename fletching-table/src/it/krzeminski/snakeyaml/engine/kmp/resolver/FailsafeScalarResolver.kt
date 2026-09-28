package it.krzeminski.snakeyaml.engine.kmp.resolver

import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag

public class FailsafeScalarResolver : BaseScalarResolver(FailsafeScalarResolver::_init_$lambda$0) {
   @JvmStatic
   fun `_init_$lambda$0`(var0: BaseScalarResolver.ImplicitResolversBuilder): Unit {
      var0.addImplicitResolver(Tag.NULL, BaseScalarResolver.Companion.getEMPTY(), null);
      return Unit.INSTANCE;
   }
}
