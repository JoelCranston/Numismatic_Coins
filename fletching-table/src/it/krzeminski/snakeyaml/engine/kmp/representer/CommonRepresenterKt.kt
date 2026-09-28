package it.krzeminski.snakeyaml.engine.kmp.representer

private fun Number.isInfinity(): Boolean {
   return if (`$this$isInfinity` is java.lang.Double)
      java.lang.Double.isInfinite(`$this$isInfinity`.doubleValue())
      else
      `$this$isInfinity` is java.lang.Float && java.lang.Float.isInfinite(`$this$isInfinity`.floatValue());
}

private fun Number.isPositive(): Boolean {
   val var10000: Boolean;
   if (`$this$isPositive` is java.lang.Double) {
      var10000 = `$this$isPositive`.doubleValue() > 0.0;
   } else {
      if (`$this$isPositive` !is java.lang.Float) {
         throw new IllegalStateException(("Unexpected number type: $`$this$isPositive`").toString());
      }

      var10000 = `$this$isPositive`.floatValue() > 0.0F;
   }

   return var10000;
}

private fun Number.isNotANumber(): Boolean {
   return if (`$this$isNotANumber` is java.lang.Double)
      java.lang.Double.isNaN(`$this$isNotANumber`.doubleValue())
      else
      `$this$isNotANumber` is java.lang.Float && java.lang.Float.isNaN(`$this$isNotANumber`.floatValue());
}

@JvmSynthetic
fun `access$isNotANumber`(`$receiver`: java.lang.Number): Boolean {
   return isNotANumber(`$receiver`);
}

@JvmSynthetic
fun `access$isInfinity`(`$receiver`: java.lang.Number): Boolean {
   return isInfinity(`$receiver`);
}

@JvmSynthetic
fun `access$isPositive`(`$receiver`: java.lang.Number): Boolean {
   return isPositive(`$receiver`);
}
