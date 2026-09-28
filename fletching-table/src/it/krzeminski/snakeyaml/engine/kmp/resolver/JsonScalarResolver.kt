package it.krzeminski.snakeyaml.engine.kmp.resolver

import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag

public class JsonScalarResolver : BaseScalarResolver(JsonScalarResolver::_init_$lambda$0) {
   @JvmStatic
   fun `_init_$lambda$0`(var0: BaseScalarResolver.ImplicitResolversBuilder): Unit {
      var0.addImplicitResolver(Tag.NULL, BaseScalarResolver.Companion.getEMPTY(), null);
      var0.addImplicitResolver(Tag.BOOL, BOOL, "tf");
      var0.addImplicitResolver(Tag.INT, INT, "-0123456789");
      var0.addImplicitResolver(Tag.FLOAT, FLOAT, "-0123456789.");
      var0.addImplicitResolver(Tag.NULL, NULL, "n\u0000");
      var0.addImplicitResolver(Tag.ENV_TAG, BaseScalarResolver.ENV_FORMAT, "$");
      return Unit.INSTANCE;
   }

   public companion object {
      public final val BOOL: Regex
      public final val FLOAT: Regex
      public final val INT: Regex
      public final val NULL: Regex
   }
}
