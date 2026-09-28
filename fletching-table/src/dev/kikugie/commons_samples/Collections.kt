package dev.kikugie.commons_samples

import dev.kikugie.commons.collections.FixedQueue
import dev.kikugie.commons.collections.PresentationKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.test.AssertionsKt
import org.junit.jupiter.api.Test

@SourceDebugExtension(["SMAP\nCollections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Collections.kt\ndev/kikugie/commons_samples/Collections\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,36:1\n1#2:37\n*E\n"])
private class Collections {
   @Test
   public fun fixedQueue() {
      val queue: FixedQueue = new FixedQueue(4);
      CollectionsKt.addAll(queue, CollectionsKt.listOf(new Integer[]{1, 2, 3, 4}));

      var var5: Any;
      try {
         queue.add(5);
         var5 = Result.constructor-impl(Unit.INSTANCE);
      } catch (var9: java.lang.Throwable) {
         var5 = Result.constructor-impl(ResultKt.createFailure(var9));
      }

      AssertionsKt.checkResultIsFailure(IndexOutOfBoundsException::class, null, var5);

      try {
         queue.remove(3);
         var5 = Result.constructor-impl(Unit.INSTANCE);
      } catch (var8: java.lang.Throwable) {
         var5 = Result.constructor-impl(ResultKt.createFailure(var8));
      }

      AssertionsKt.checkResultIsFailure(UnsupportedOperationException::class, null, var5);
      AssertionsKt.assertEquals$default(1, queue.remove(), null, 4, null);
   }

   @Test
   public fun collectionPresentation() {
      val items: java.util.Collection = CollectionsKt.listOf(new Integer[]{1, 2, 3});
      AssertionsKt.assertEquals$default("[1, 2, 3]", PresentationKt.present$default(items, 0, 1, null), null, 4, null);
      AssertionsKt.assertEquals$default("[1, ...]", PresentationKt.present(items, 1), null, 4, null);
      AssertionsKt.assertEquals$default(
         "['1', '2', '3']", PresentationKt.present$default(items, 0, Collections::collectionPresentation$lambda$2, 1, null), null, 4, null
      );
   }

   @Test
   public fun mapPresentation() {
      val entries: java.util.Map = MapsKt.mapOf(new Pair[]{TuplesKt.to("a", 1), TuplesKt.to("b", 2), TuplesKt.to("c", 3)});
      AssertionsKt.assertEquals$default("{a: 1, b: 2, c: 3}", PresentationKt.present$default(entries, 0, 1, null), null, 4, null);
      AssertionsKt.assertEquals$default("{a: 1, ...}", PresentationKt.present(entries, 1), null, 4, null);
      AssertionsKt.assertEquals$default(
         "{'a' = 0b1, 'b' = 0b10, 'c' = 0b11}", PresentationKt.present$default(entries, 0, Collections::mapPresentation$lambda$3, 1, null), null, 4, null
      );
   }

   @JvmStatic
   fun `collectionPresentation$lambda$2`(it: Int): java.lang.CharSequence {
      return "'$it'";
   }

   @JvmStatic
   fun `mapPresentation$lambda$3`(k: java.lang.String, v: Int): java.lang.CharSequence {
      val var10001: java.lang.String = Integer.toString(v, CharsKt.checkRadix(2));
      return "'$k' = 0b$var10001";
   }
}
