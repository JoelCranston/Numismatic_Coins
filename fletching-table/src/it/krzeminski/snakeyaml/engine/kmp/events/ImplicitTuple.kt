package it.krzeminski.snakeyaml.engine.kmp.events

public class ImplicitTuple(plain: Boolean, nonPlain: Boolean) {
   private final val plain: Boolean
   private final val nonPlain: Boolean

   init {
      this.plain = plain;
      this.nonPlain = nonPlain;
   }

   public fun canOmitTagInPlainScalar(): Boolean {
      return this.plain;
   }

   public fun canOmitTagInNonPlainScalar(): Boolean {
      return this.nonPlain;
   }

   public fun bothFalse(): Boolean {
      return !this.plain && !this.nonPlain;
   }

   public override fun toString(): String {
      return "implicit=[${this.plain}, ${this.nonPlain}]";
   }
}
