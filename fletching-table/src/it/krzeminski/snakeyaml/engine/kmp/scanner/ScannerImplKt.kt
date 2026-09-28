package it.krzeminski.snakeyaml.engine.kmp.scanner

private fun Chomping(indicatorCodePoint: Int?, increment: Int?): Chomping? {
   if (indicatorCodePoint != null) {
      if (indicatorCodePoint == 43) {
         return Chomping.Keep.box-impl(Chomping.Keep.constructor-impl(increment));
      }
   }

   if (indicatorCodePoint != null) {
      if (indicatorCodePoint == 45) {
         return Chomping.Strip.box-impl(Chomping.Strip.constructor-impl(increment));
      }
   }

   return if (indicatorCodePoint == null) Chomping.Clip.box-impl(Chomping.Clip.constructor-impl(increment)) else null;
}

@JvmSynthetic
fun `access$Chomping`(indicatorCodePoint: Int, increment: Int): Chomping {
   return Chomping(indicatorCodePoint, increment);
}
