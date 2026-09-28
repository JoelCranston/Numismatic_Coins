package io.ktor.http

import kotlin.collections.Map.Entry

private object EmptyHeaders : Headers {
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
      return "Headers ${this.entries()}";
   }
}
