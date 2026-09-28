package it.krzeminski.snakeyaml.engine.kmp.serializer

import it.krzeminski.snakeyaml.engine.kmp.internal.IdentityHashCode
import it.krzeminski.snakeyaml.engine.kmp.internal.SystemKt
import java.util.LinkedHashSet

internal class IdentitySet<T> {
   private final val contents: MutableSet<IdentityHashCode> = (new LinkedHashSet()) as java.util.Set

   public fun add(obj: Any) {
      this.contents.add(IdentityHashCode.box-impl(SystemKt.identityHashCode(obj)));
   }

   public fun contains(obj: Any): Boolean {
      return this.contents.contains(IdentityHashCode.box-impl(SystemKt.identityHashCode(obj)));
   }

   public fun clear() {
      this.contents.clear();
   }

   public fun remove(obj: Any) {
      this.contents.remove(IdentityHashCode.box-impl(SystemKt.identityHashCode(obj)));
   }
}
