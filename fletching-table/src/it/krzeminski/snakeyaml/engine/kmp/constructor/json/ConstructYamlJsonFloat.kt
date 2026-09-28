package it.krzeminski.snakeyaml.engine.kmp.constructor.json

import it.krzeminski.snakeyaml.engine.kmp.constructor.ConstructScalar
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node

public open class ConstructYamlJsonFloat : ConstructScalar {
   public open fun construct(node: Node?): Double {
      val value: java.lang.String = this.constructScalar(node);
      switch (value.hashCode()) {
         case 1474803:
            if (value.equals(".inf")) {
               return java.lang.Double.POSITIVE_INFINITY;
            }
            break;
         case 1479213:
            if (value.equals(".nan")) {
               return java.lang.Double.NaN;
            }
            break;
         case 43033248:
            if (value.equals("-.inf")) {
               return java.lang.Double.NEGATIVE_INFINITY;
            }
         default:
      }

      return this.constructFromString(value);
   }

   private fun constructFromString(value: String): Double {
      var var10000: Pair;
      label23: {
         val sign: Character = StringsKt.firstOrNull(value);
         if (sign != null) {
            if (sign == '-') {
               val var11: Int = -1;
               val var12: java.lang.String = value.substring(1);
               var10000 = TuplesKt.to(var11, var12);
               break label23;
            }
         }

         if (sign != null) {
            if (sign == '+') {
               val var10: Int = 1;
               val var10001: java.lang.String = value.substring(1);
               var10000 = TuplesKt.to(var10, var10001);
               break label23;
            }
         }

         var10000 = TuplesKt.to(1, value);
      }

      return java.lang.Double.parseDouble(var10000.component2() as java.lang.String) * (var10000.component1() as java.lang.Number).intValue();
   }
}
