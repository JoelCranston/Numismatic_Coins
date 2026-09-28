package dev.kikugie.commons.collections

import java.util.Map.Entry
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function2

public fun <T> Collection<T>.present(limit: Int = -1): String {
   return kotlin.collections.CollectionsKt.joinToString$default(`$this$present`, null, "[", "]", limit, null, null, 49, null);
}

@JvmSynthetic
fun `present$default`(var0: java.util.Collection, var1: Int, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 1) != 0) {
      var1 = -1;
   }

   return present(var0, var1);
}

public fun <T> Collection<T>.present(limit: Int = -1, format: (T) -> CharSequence): String {
   return kotlin.collections.CollectionsKt.joinToString$default(`$this$present`, null, "[", "]", limit, null, format, 17, null);
}

@JvmSynthetic
fun `present$default`(var0: java.util.Collection, var1: Int, var2: Function1, var3: Int, var4: Any): java.lang.String {
   if ((var3 and 1) != 0) {
      var1 = -1;
   }

   return present(var0, var1, var2);
}

public fun <K, V> Map<K, V>.present(limit: Int = -1): String {
   return kotlin.collections.CollectionsKt.joinToString$default(
      `$this$present`.entrySet(), null, "{", "}", limit, null, PresentationKt::present$lambda$0, 17, null
   );
}

@JvmSynthetic
fun `present$default`(var0: java.util.Map, var1: Int, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 1) != 0) {
      var1 = -1;
   }

   return present(var0, var1);
}

public fun <K, V> Map<K, V>.present(limit: Int = -1, format: (K, V) -> CharSequence): String {
   return kotlin.collections.CollectionsKt.joinToString$default(
      `$this$present`.entrySet(), null, "{", "}", limit, null, PresentationKt::present$lambda$1, 17, null
   );
}

@JvmSynthetic
fun `present$default`(var0: java.util.Map, var1: Int, var2: Function2, var3: Int, var4: Any): java.lang.String {
   if ((var3 and 1) != 0) {
      var1 = -1;
   }

   return present(var0, var1, var2);
}

fun `present$lambda$0`(var0: Entry): java.lang.CharSequence {
   return "${var0.getKey()}: ${var0.getValue()}";
}

fun `present$lambda$1`(`$format`: Function2, var1: Entry): java.lang.CharSequence {
   return `$format`.invoke(var1.getKey(), var1.getValue()) as java.lang.CharSequence;
}
