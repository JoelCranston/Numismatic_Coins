package io.ktor.websocket

public class WebSocketExtensionHeader(name: String, parameters: List<String>) {
   public final val name: String
   public final val parameters: List<String>

   init {
      this.name = name;
      this.parameters = parameters;
   }

   public fun parseParameters(): Sequence<Pair<String, String>> {
      return SequencesKt.map(CollectionsKt.asSequence(this.parameters), WebSocketExtensionHeader::parseParameters$lambda$0);
   }

   public override fun toString(): String {
      return "${this.name} ${this.parametersToString()}";
   }

   private fun parametersToString(): String {
      return if (this.parameters.isEmpty()) "" else "; ${CollectionsKt.joinToString$default(this.parameters, ";", null, null, 0, null, null, 62, null)}";
   }

   @JvmStatic
   fun `parseParameters$lambda$0`(it: java.lang.String): Pair {
      val equalsIndex: Int = StringsKt.indexOf$default(it, '=', 0, false, 6, null);
      if (equalsIndex < 0) {
         return TuplesKt.to(it, "");
      } else {
         val key: java.lang.String = StringsKt.substring(it, RangesKt.until(0, equalsIndex));
         val var10000: java.lang.String;
         if (equalsIndex + 1 < it.length()) {
            var10000 = it.substring(equalsIndex + 1);
         } else {
            var10000 = "";
         }

         return TuplesKt.to(key, var10000);
      }
   }
}
