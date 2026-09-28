@file:SourceDebugExtension(["SMAP\nLists.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Lists.kt\ndev/kikugie/commons/collections/ListsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Common.kt\ndev/kikugie/commons/collections/CommonKt\n*L\n1#1,43:1\n11#1:45\n24#1,5:47\n24#1,5:53\n1#2:44\n1#2:46\n7#3:52\n7#3:58\n*S KotlinDebug\n*F\n+ 1 Lists.kt\ndev/kikugie/commons/collections/ListsKt\n*L\n19#1:45\n33#1:47,5\n37#1:53,5\n19#1:46\n33#1:52\n41#1:58\n*E\n"])

package dev.kikugie.commons.collections

import java.util.NoSuchElementException
import kotlin.jvm.internal.SourceDebugExtension

public inline fun <T> List<T>.getOrDefault(index: Int, default: T): T {
   return (T)(if (0 <= index && index < `$this$getOrDefault`.size()) `$this$getOrDefault`.get(index) else var2);
}

public inline fun <T> List<T>.getOrThrow(index: Int, message: (List<T>, Int) -> String): T {
   if (0 <= index && index < `$this$getOrThrow`.size()) {
      return (T)`$this$getOrThrow`.get(index);
   } else {
      throw new IndexOutOfBoundsException(message.invoke(`$this$getOrThrow`, index) as java.lang.String);
   }
}

public inline fun <T> List<T>.getResult(index: Int): Result<T> {
   val var3: java.util.List = `$this$getResult`;

   var `$this$getResult_u24lambda_u242`: Any;
   try {
      `$this$getResult_u24lambda_u242` = Result.constructor-impl(var3.get(index));
   } catch (var6: java.lang.Throwable) {
      `$this$getResult_u24lambda_u242` = Result.constructor-impl(ResultKt.createFailure(var6));
   }

   return `$this$getResult_u24lambda_u242`;
}

public inline fun <T> List<T>.getResult(index: Int, message: (List<T>, Int) -> String): Result<T> {
   val var4: java.util.List = `$this$getResult`;

   var `$this$getResult_u24lambda_u243`: Any;
   try {
      if (0 > index || index >= var4.size()) {
         throw new IndexOutOfBoundsException(message.invoke(var4, index) as java.lang.String);
      }

      `$this$getResult_u24lambda_u243` = Result.constructor-impl(var4.get(index));
   } catch (var13: java.lang.Throwable) {
      `$this$getResult_u24lambda_u243` = Result.constructor-impl(ResultKt.createFailure(var13));
   }

   return `$this$getResult_u24lambda_u243`;
}

public inline fun <T, R : Any> List<T>.lastNotNullOfOrNull(transform: (T) -> R?): R? {
   for (int index = $this$lastNotNullOfOrNull.size() - 1; -1 < index; index--) {
      val element: Any = transform.invoke(`$this$lastNotNullOfOrNull`.get(index));
      if (element != null) {
         return (R)element;
      }
   }

   return null;
}

public inline fun <T, R : Any> List<T>.lastNotNullOf(transform: (T) -> R?): R {
   val `element$iv`: java.util.List = `$this$lastNotNullOf`;
   var var5: Int = `$this$lastNotNullOf`.size() - 1;

   var var10000: Any;
   while (true) {
      if (-1 >= var5) {
         var10000 = null;
         break;
      }

      val `element$ivx`: Any = transform.invoke(`element$iv`.get(var5));
      var10000 = `element$ivx`;
      if (`element$ivx` != null) {
         break;
      }

      var5--;
   }

   if (var10000 == null) {
      throw new NoSuchElementException("No element was transformed to a non-null value.");
   } else {
      return (R)var10000;
   }
}

public fun <T : Any> List<T>.lastNotNullOrNull(): T? {
   val `$this$lastNotNullOfOrNull$iv`: java.util.List = `$this$lastNotNullOrNull`;
   var `index$iv`: Int = `$this$lastNotNullOrNull`.size() - 1;

   var var10000: Any;
   while (true) {
      if (-1 >= `index$iv`) {
         var10000 = null;
         break;
      }

      val `element$iv`: Any = `$this$lastNotNullOfOrNull$iv`.get(`index$iv`);
      var10000 = `element$iv`;
      if (`element$iv` != null) {
         break;
      }

      `index$iv`--;
   }

   return (T)var10000;
}

public fun <T : Any> List<T>.lastNotNull(): T {
   val `element$iv`: Any = lastNotNullOrNull(`$this$lastNotNull`);
   if (`element$iv` == null) {
      throw new NoSuchElementException("No non-null element was found in the list");
   } else {
      return (T)`element$iv`;
   }
}
