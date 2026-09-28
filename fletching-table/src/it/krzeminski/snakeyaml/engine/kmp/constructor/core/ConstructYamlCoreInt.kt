package it.krzeminski.snakeyaml.engine.kmp.constructor.core

import it.krzeminski.snakeyaml.engine.kmp.constructor.ConstructScalar
import it.krzeminski.snakeyaml.engine.kmp.exceptions.ConstructorException
import it.krzeminski.snakeyaml.engine.kmp.internal.MathKt
import it.krzeminski.snakeyaml.engine.kmp.internal.Math_jvmKt
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node

public class ConstructYamlCoreInt : ConstructScalar {
   public open fun construct(node: Node?): Number {
      val value: java.lang.String = this.constructScalar(node);
      if (value.length() == 0) {
         throw new ConstructorException("while constructing an int", node.getStartMark(), "found empty value", node.getStartMark(), null, 16, null);
      } else {
         return this.createIntNumber(value);
      }
   }

   private fun createIntNumber(value: String): Number {
      var var10000: Pair;
      label36: {
         val sign: Character = StringsKt.firstOrNull(value);
         if (sign != null) {
            if (sign == '-') {
               val var12: Int = -1;
               val var16: java.lang.String = value.substring(1);
               var10000 = TuplesKt.to(var12, var16);
               break label36;
            }
         }

         if (sign != null) {
            if (sign == '+') {
               val var11: Int = 1;
               val var10001: java.lang.String = value.substring(1);
               var10000 = TuplesKt.to(var11, var10001);
               break label36;
            }
         }

         var10000 = TuplesKt.to(1, value);
      }

      val var8: Int = (var10000.component1() as java.lang.Number).intValue();
      val var10: java.lang.String = var10000.component2() as java.lang.String;
      if (var10 == "0") {
         return 0;
      } else {
         if (StringsKt.startsWith$default(var10, "0x", false, 2, null)) {
            val var13: Int = 16;
            val var17: java.lang.String = var10.substring(2);
            var10000 = TuplesKt.to(var13, var17);
         } else if (StringsKt.startsWith$default(var10, "0o", false, 2, null)) {
            val var15: Int = 8;
            val var18: java.lang.String = var10.substring(2);
            var10000 = TuplesKt.to(var15, var18);
         } else {
            var10000 = TuplesKt.to(10, var10);
         }

         return this.createNumber(var8, var10000.component2() as java.lang.String, (var10000.component1() as java.lang.Number).intValue());
      }
   }

   private fun createNumber(sign: Int, numeric: String, radix: Int): Number {
      val len: Int = numeric.length();
      val number: java.lang.String = if (sign < 0) "-$numeric" else numeric;
      val maxArr: IntArray = if (radix < (RADIX_MAX as Array<Any>).length) RADIX_MAX[radix] else null;
      if ((if (radix < (RADIX_MAX as Array<Any>).length) RADIX_MAX[radix] else null) != null
         && len > ArraysKt.first(if (radix < (RADIX_MAX as Array<Any>).length) RADIX_MAX[radix] else null)) {
         return if (len > maxArr[1])
            Math_jvmKt.createBigInteger(number, radix)
            else
            ConstructYamlCoreInt.Companion.access$toLongOrBigInteger(Companion, number, radix);
      } else {
         val var10000: Int = StringsKt.toIntOrNull(number, radix);
         return if (var10000 != null) var10000 else ConstructYamlCoreInt.Companion.access$toLongOrBigInteger(Companion, number, radix);
      }
   }

   @JvmStatic
   fun {
      var radixList: Int = 0;

      val var1: Array<IntArray>;
      for (var1 = new int[17][]; radixList < 17; radixList++) {
         var1[radixList] = new int[2];
      }

      RADIX_MAX = var1;

      for (int radix : var1) {
         val var10000: Array<IntArray> = RADIX_MAX;
         val var4: IntArray = new int[2];
         var var10004: java.lang.String = Integer.toString(Integer.MAX_VALUE, CharsKt.checkRadix(radix));
         var4[0] = var10004.length();
         var10004 = java.lang.Long.toString(java.lang.Long.MAX_VALUE, CharsKt.checkRadix(radix));
         var4[1] = var10004.length();
         var10000[radix] = var4;
      }
   }

   public companion object {
      private final val RADIX_MAX: Array<IntArray>

      private fun String.toLongOrBigInteger(radix: Int): Number {
         val var10000: java.lang.Long = StringsKt.toLongOrNull(`$this$toLongOrBigInteger`, radix);
         return if (var10000 != null) var10000 else MathKt.toBigInteger(`$this$toLongOrBigInteger`, radix);
      }
   }
}
