package it.krzeminski.snakeyaml.engine.kmp.common

import it.krzeminski.snakeyaml.engine.kmp.exceptions.EmitterException
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nAnchor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Anchor.kt\nit/krzeminski/snakeyaml/engine/kmp/common/Anchor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,60:1\n1#2:61\n*E\n"])
public class Anchor(value: String) {
   public final val value: String

   init {
      this.value = value;
      if (this.value.length() <= 0) {
         throw new IllegalArgumentException("Empty anchor.".toString());
      } else {
         val var2: java.lang.String = this.value;
         var var3: Int = 0;

         for (int var4 = this.value.length(); var3 < var4; var3++) {
            val element: Char = var2.charAt(var3);
            if (INVALID_ANCHOR.contains(element)) {
               throw new EmitterException("Invalid character '$element' in the anchor: ${this.value}");
            }
         }

         if (SPACES_PATTERN.containsMatchIn(this.value)) {
            throw new EmitterException("Anchor may not contain spaces: ${this.value}");
         }
      }
   }

   public override fun toString(): String {
      return this.value;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         return other is Anchor && this.value == (other as Anchor).value;
      }
   }

   public override fun hashCode(): Int {
      return this.value.hashCode();
   }

   public companion object {
      private final val INVALID_ANCHOR: Set<Char>
      private final val SPACES_PATTERN: Regex
   }
}
