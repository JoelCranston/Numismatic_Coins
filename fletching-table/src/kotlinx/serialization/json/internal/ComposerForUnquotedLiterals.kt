package kotlinx.serialization.json.internal

@SuppressAnimalSniffer
internal class ComposerForUnquotedLiterals(writer: InternalJsonWriter, forceQuoting: Boolean) : Composer(writer) {
   private final val forceQuoting: Boolean

   init {
      this.forceQuoting = forceQuoting;
   }

   public override fun printQuoted(value: String) {
      if (this.forceQuoting) {
         super.printQuoted(value);
      } else {
         super.print(value);
      }
   }
}
