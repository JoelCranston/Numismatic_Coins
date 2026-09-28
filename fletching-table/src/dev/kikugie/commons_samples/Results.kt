package dev.kikugie.commons_samples

import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.test.AssertionsKt
import org.junit.jupiter.api.Test

@SourceDebugExtension(["SMAP\nResults.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Results.kt\ndev/kikugie/commons_samples/Results\n+ 2 Mapping.kt\ndev/kikugie/commons/result/MappingKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,44:1\n16#2,2:45\n16#2,2:47\n37#2:49\n46#2:52\n46#2:54\n1#3:50\n1#3:51\n1#3:53\n1#3:55\n1#3:56\n*S KotlinDebug\n*F\n+ 1 Results.kt\ndev/kikugie/commons_samples/Results\n*L\n13#1:45,2\n14#1:47,2\n25#1:49\n38#1:52\n41#1:54\n25#1:50\n38#1:53\n41#1:55\n*E\n"])
private class Results {
   @Test
   public fun notNullResult() {
      var `value$iv`: Any = notNullResult$getNullable("any");
      AssertionsKt.assertTrue$default(
         Result.isSuccess-impl(
            if (`value$iv` != null) Result.constructor-impl(`value$iv`) else Result.constructor-impl(ResultKt.createFailure(new NullPointerException()))
         ),
         null,
         2,
         null
      );
      `value$iv` = notNullResult$getNullable("null");
      AssertionsKt.assertTrue$default(
         Result.isFailure-impl(
            if (`value$iv` != null) Result.constructor-impl(`value$iv`) else Result.constructor-impl(ResultKt.createFailure(new NullPointerException()))
         ),
         null,
         2,
         null
      );
   }

   @Test
   public fun mapException() {
      val result: Any = mapException$inverse(this, 0);
      var `$this$mapException$iv`: java.lang.Throwable = Result.exceptionOrNull-impl(result);
      AssertionsKt.assertIsOfType(`$this$mapException$iv`, Reflection.typeOf(ArithmeticException.class), `$this$mapException$iv` is ArithmeticException, null);
      if (`$this$mapException$iv` == null) {
         throw new NullPointerException("null cannot be cast to non-null type java.lang.ArithmeticException");
      } else {
         `$this$mapException$iv` = Result.exceptionOrNull-impl(
            if (Result.exceptionOrNull-impl(result) != null)
               Result.constructor-impl(ResultKt.createFailure(new IllegalArgumentException("Zero is not a valid divisor")))
               else
               result
         );
         AssertionsKt.assertIsOfType(
            `$this$mapException$iv`, Reflection.typeOf(IllegalArgumentException.class), `$this$mapException$iv` is IllegalArgumentException, null
         );
         if (`$this$mapException$iv` == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.IllegalArgumentException");
         }
      }
   }

   @Test
   public fun mapResult() {
      val succeeded: Results = this;

      var `$this$mapResult$iv`: Results;
      try {
         `$this$mapResult$iv` = succeeded;
         `$this$mapResult$iv` = (Results)Result.constructor-impl(Result.box-impl(mapResult$example(this, false)));
      } catch (var11: java.lang.Throwable) {
         `$this$mapResult$iv` = (Results)Result.constructor-impl(ResultKt.createFailure(var11));
      }

      var var10000: Any;
      if (Result.exceptionOrNull-impl(`$this$mapResult$iv`) != null) {
         var10000 = `$this$mapResult$iv`;
      } else {
         ResultKt.throwOnFailure(`$this$mapResult$iv`);
         val it: Any = (`$this$mapResult$iv` as Result).unbox-impl();
         var10000 = mapResult$example(this, true);
      }

      val var13: java.lang.Throwable = Result.exceptionOrNull-impl(var10000);
      AssertionsKt.assertIsOfType(var13, Reflection.typeOf(IllegalStateException.class), var13 is IllegalStateException, null);
      if (var13 == null) {
         throw new NullPointerException("null cannot be cast to non-null type java.lang.IllegalStateException");
      } else {
         `$this$mapResult$iv` = this;

         var var21: Results;
         try {
            var21 = `$this$mapResult$iv`;
            var21 = (Results)Result.constructor-impl(Result.box-impl(mapResult$example(this, false)));
         } catch (var10: java.lang.Throwable) {
            var21 = (Results)Result.constructor-impl(ResultKt.createFailure(var10));
         }

         if (Result.exceptionOrNull-impl(var21) != null) {
            var10000 = var21;
         } else {
            ResultKt.throwOnFailure(var21);
            val var26: Any = (var21 as Result).unbox-impl();
            var10000 = mapResult$example(this, false);
         }

         `$this$mapResult$iv` = (Results)(if (Result.isFailure-impl(var10000)) null else var10000);
         AssertionsKt.assertIsOfType(`$this$mapResult$iv`, Reflection.typeOf(java.lang.String.class), `$this$mapResult$iv` is java.lang.String, null);
         if (`$this$mapResult$iv` == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
         }
      }
   }

   @JvmStatic
   fun `notNullResult$getNullable`(str: java.lang.String): java.lang.String {
      return if (str == "null") null else str;
   }

   @JvmStatic
   fun `mapException$inverse`(`this$0`: Results, num: Int): Any {
      var `$this$mapException_u24inverse_u24lambda_u240`: Any;
      try {
         `$this$mapException_u24inverse_u24lambda_u240` = Result.constructor-impl(1 / num);
      } catch (var5: java.lang.Throwable) {
         `$this$mapException_u24inverse_u24lambda_u240` = Result.constructor-impl(ResultKt.createFailure(var5));
      }

      return `$this$mapException_u24inverse_u24lambda_u240`;
   }

   @JvmStatic
   fun `mapResult$example`(`this$0`: Results, fail: Boolean): Any {
      var `$this$mapResult_u24example_u24lambda_u242`: Any;
      try {
         if (fail) {
            throw new IllegalStateException("Something went wrong");
         }

         `$this$mapResult_u24example_u24lambda_u242` = Result.constructor-impl("Hello world!");
      } catch (var5: java.lang.Throwable) {
         `$this$mapResult_u24example_u24lambda_u242` = Result.constructor-impl(ResultKt.createFailure(var5));
      }

      return `$this$mapResult_u24example_u24lambda_u242`;
   }
}
