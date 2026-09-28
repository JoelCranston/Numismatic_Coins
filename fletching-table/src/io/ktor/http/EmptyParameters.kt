package io.ktor.http

import kotlin.collections.Map.Entry

internal object EmptyParameters : Parameters {
   public open val caseInsensitiveName: Boolean
      public open get() {
         return true;
      }


   public override fun getAll(name: String): List<String>? {
      return null;
   }

   public override fun names(): Set<String> {
      return SetsKt.emptySet();
   }

   public override fun entries(): Set<Entry<String, List<String>>> {
      return SetsKt.emptySet();
   }

   public override fun isEmpty(): Boolean {
      return true;
   }

   public override fun toString(): String {
      return "Parameters ${this.entries()}";
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is Parameters && (other as Parameters).isEmpty();
   }
}
