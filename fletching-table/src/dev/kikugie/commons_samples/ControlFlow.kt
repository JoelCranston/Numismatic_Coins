package dev.kikugie.commons_samples

import kotlin.jvm.internal.SourceDebugExtension
import kotlin.test.AssertionsKt
import org.junit.jupiter.api.Test

@SourceDebugExtension(["SMAP\nControlFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ControlFlow.kt\ndev/kikugie/commons_samples/ControlFlow\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ControlFlow.kt\ndev/kikugie/commons/ControlFlowKt\n*L\n1#1,15:1\n1#2:16\n15#3:17\n*S KotlinDebug\n*F\n+ 1 ControlFlow.kt\ndev/kikugie/commons_samples/ControlFlow\n*L\n10#1:17\n*E\n"])
private class ControlFlow {
   @Test
   public fun then() {
      AssertionsKt.assertEquals$default(2, then$incrementChecked(1), null, 4, null);

      var var4: Any;
      try {
         then$incrementChecked(Integer.MAX_VALUE);
         var4 = Result.constructor-impl(Unit.INSTANCE);
      } catch (var7: java.lang.Throwable) {
         var4 = Result.constructor-impl(ResultKt.createFailure(var7));
      }

      AssertionsKt.checkResultIsFailure(IllegalArgumentException::class, null, var4);
   }

   @JvmStatic
   fun `then$incrementChecked`(value: Int): Int {
      if (value >= Integer.MAX_VALUE) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      } else {
         return value + 1;
      }
   }
}
