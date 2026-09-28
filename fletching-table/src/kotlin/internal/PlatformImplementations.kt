package kotlin.internal

import java.lang.reflect.Method
import java.util.regex.MatchResult
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.random.FallbackThreadLocalRandom
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@SourceDebugExtension(["SMAP\nPlatformImplementations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlatformImplementations.kt\nkotlin/internal/PlatformImplementations\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,86:1\n1#2:87\n*E\n"])
internal open class PlatformImplementations {
   public open fun addSuppressed(cause: Throwable, exception: Throwable) {
      if (PlatformImplementations.ReflectThrowable.addSuppressed != null) {
         PlatformImplementations.ReflectThrowable.addSuppressed.invoke(cause, exception);
      }
   }

   public open fun getSuppressed(exception: Throwable): List<Throwable> {
      if (PlatformImplementations.ReflectThrowable.getSuppressed != null) {
         var var10000: java.util.List = (java.util.List)PlatformImplementations.ReflectThrowable.getSuppressed.invoke(exception);
         if (var10000 != null) {
            var10000 = ArraysKt.asList(var10000 as Array<java.lang.Throwable>);
            if (var10000 != null) {
               return var10000;
            }
         }
      }

      return CollectionsKt.emptyList();
   }

   public open fun getMatchResultNamedGroup(matchResult: MatchResult, name: String): MatchGroup? {
      throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
   }

   public open fun defaultPlatformRandom(): Random {
      return new FallbackThreadLocalRandom();
   }

   @ExperimentalTime
   public open fun getSystemClock(): Clock {
      throw new UnsupportedOperationException("getSystemClock should not be called on the base PlatformImplementations.");
   }

   @SourceDebugExtension(["SMAP\nPlatformImplementations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlatformImplementations.kt\nkotlin/internal/PlatformImplementations$ReflectThrowable\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,86:1\n1#2:87\n*E\n"])
   private object ReflectThrowable {
      public final val addSuppressed: Method?
      public final val getSuppressed: Method?

      @JvmStatic
      fun {
         val throwableClass: Class = java.lang.Throwable::class.java;
         val throwableMethods: Array<Method> = java.lang.Throwable.class.getMethods();
         var var2: Array<Method> = throwableMethods;
         var var3: Int = 0;
         var var4: Int = throwableMethods.length;

         var var14: Method;
         while (true) {
            if (var3 >= var4) {
               var14 = null;
               break;
            }

            var var5: Method;
            label36: {
               var5 = var2[var3];
               if (var2[var3].getName() == "addSuppressed") {
                  val var10000: Array<Class> = var5.getParameterTypes();
                  if (ArraysKt.singleOrNull(var10000) == throwableClass) {
                     var13 = true;
                     break label36;
                  }
               }

               var13 = false;
            }

            if (var13) {
               var14 = var5;
               break;
            }

            var3++;
         }

         addSuppressed = var14;
         var2 = throwableMethods;
         var3 = 0;
         var4 = throwableMethods.length;

         while (true) {
            if (var3 >= var4) {
               var14 = null;
               break;
            }

            val var11: Method = var2[var3];
            if (var2[var3].getName() == "getSuppressed") {
               var14 = var11;
               break;
            }

            var3++;
         }

         getSuppressed = var14;
      }
   }
}
