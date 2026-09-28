package it.krzeminski.snakeyaml.engine.kmp.constructor.json

import it.krzeminski.snakeyaml.engine.kmp.constructor.ConstructScalar
import it.krzeminski.snakeyaml.engine.kmp.internal.MathKt
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node

public class ConstructYamlJsonInt : ConstructScalar {
   public open fun construct(node: Node?): Number {
      return this.createIntNumber(this.constructScalar(node));
   }

   private fun createIntNumber(number: String): Number {
      val var10000: Int = StringsKt.toIntOrNull(number);
      val var2: java.lang.Number;
      if (var10000 != null) {
         var2 = var10000;
      } else {
         val var3: java.lang.Long = StringsKt.toLongOrNull(number);
         var2 = if (var3 != null) var3 else MathKt.toBigInteger$default(number, 0, 1, null);
      }

      return var2;
   }
}
