@file:SourceDebugExtension(["SMAP\nAwait.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitKt\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,121:1\n37#2:122\n36#2,3:123\n13402#3,2:126\n1863#4,2:128\n*S KotlinDebug\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitKt\n*L\n36#1:122\n36#1:123,3\n47#1:126,2\n58#1:128,2\n*E\n"])

package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.AwaitKt.joinAll.1
import kotlinx.coroutines.AwaitKt.joinAll.3

public suspend fun <T> awaitAll(vararg deferreds: Deferred<T>): List<T> {
   return if (deferreds.length == 0) CollectionsKt.emptyList() else new AwaitAll(deferreds).await(`$completion`);
}

public suspend fun <T> Collection<Deferred<T>>.awaitAll(): List<T> {
   return if (`$this$awaitAll`.isEmpty()) CollectionsKt.emptyList() else new AwaitAll(`$this$awaitAll`.toArray(new Deferred[0])).await(`$completion`);
}

public suspend fun joinAll(vararg jobs: Job) {
   var `$continuation`: Continuation;
   label33: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label33;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var11: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var `$this$forEach$iv`: Array<Any>;
   var var4: Int;
   var var5: Int;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$this$forEach$iv` = jobs;
         var4 = 0;
         var5 = jobs.length;
         break;
      case 1:
         var5 = `$continuation`.I$1;
         var4 = `$continuation`.I$0;
         `$this$forEach$iv` = `$continuation`.L$0 as Array<Job>;
         ResultKt.throwOnFailure(`$result`);
         var4++;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (var4 < var5) {
      val `element$iv`: Any = `$this$forEach$iv`[var4];
      `$continuation`.L$0 = `$this$forEach$iv`;
      `$continuation`.I$0 = var4;
      `$continuation`.I$1 = var5;
      `$continuation`.label = 1;
      if (((Job)`element$iv`).join(`$continuation`) === var11) {
         return var11;
      }

      var4++;
   }

   return Unit.INSTANCE;
}

public suspend fun Collection<Job>.joinAll() {
   var `$continuation`: Continuation;
   label33: {
      if (`$completion` is 3) {
         `$continuation` = `$completion` as 3;
         if (((`$completion` as 3).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label33;
         }
      }

      `$continuation` = new 3(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var4: java.util.Iterator;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         var4 = `$this$joinAll`.iterator();
         break;
      case 1:
         var4 = `$continuation`.L$0 as java.util.Iterator;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (var4.hasNext()) {
      val it: Job = var4.next() as Job;
      `$continuation`.L$0 = var4;
      `$continuation`.label = 1;
      if (it.join(`$continuation`) === var10) {
         return var10;
      }
   }

   return Unit.INSTANCE;
}
