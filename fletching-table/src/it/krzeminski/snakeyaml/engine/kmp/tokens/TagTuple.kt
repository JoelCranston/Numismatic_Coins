package it.krzeminski.snakeyaml.engine.kmp.tokens

public class TagTuple(handle: String?, suffix: String) {
   public final val handle: String?
   public final val suffix: String

   init {
      this.handle = handle;
      this.suffix = suffix;
   }
}
