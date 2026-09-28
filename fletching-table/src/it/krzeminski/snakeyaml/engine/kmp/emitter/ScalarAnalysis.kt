package it.krzeminski.snakeyaml.engine.kmp.emitter

public class ScalarAnalysis(scalar: String,
   empty: Boolean,
   multiline: Boolean,
   allowFlowPlain: Boolean,
   allowBlockPlain: Boolean,
   allowSingleQuoted: Boolean,
   allowBlock: Boolean
) {
   public final val scalar: String
   public final val empty: Boolean
   public final val multiline: Boolean
   public final val allowFlowPlain: Boolean
   public final val allowBlockPlain: Boolean
   public final val allowSingleQuoted: Boolean
   public final val allowBlock: Boolean

   init {
      this.scalar = scalar;
      this.empty = empty;
      this.multiline = multiline;
      this.allowFlowPlain = allowFlowPlain;
      this.allowBlockPlain = allowBlockPlain;
      this.allowSingleQuoted = allowSingleQuoted;
      this.allowBlock = allowBlock;
   }
}
