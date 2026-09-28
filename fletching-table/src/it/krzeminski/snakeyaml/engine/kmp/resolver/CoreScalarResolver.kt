package it.krzeminski.snakeyaml.engine.kmp.resolver

import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag

public class CoreScalarResolver(supportMerge: Boolean) : BaseScalarResolver(CoreScalarResolver::_init_$lambda$0) {
   @JvmStatic
   fun `_init_$lambda$0`(`$supportMerge`: Boolean, var1: BaseScalarResolver.ImplicitResolversBuilder): Unit {
      var1.addImplicitResolver(Tag.NULL, BaseScalarResolver.Companion.getEMPTY(), null);
      var1.addImplicitResolver(Tag.BOOL, BOOL, "tfTF");
      var1.addImplicitResolver(Tag.INT, INT, "-+0123456789");
      var1.addImplicitResolver(Tag.FLOAT, FLOAT, "-+0123456789.");
      var1.addImplicitResolver(Tag.NULL, NULL, "n\u0000");
      var1.addImplicitResolver(Tag.ENV_TAG, BaseScalarResolver.ENV_FORMAT, "$");
      if (`$supportMerge`) {
         var1.addImplicitResolver(Tag.MERGE, MERGE, "<");
      }

      return Unit.INSTANCE;
   }

   public companion object {
      public final val BOOL: Regex
      public final val FLOAT: Regex
      public final val MERGE: Regex
      public final val INT: Regex
      public final val NULL: Regex
   }
}
