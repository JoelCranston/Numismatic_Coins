package it.krzeminski.snakeyaml.engine.kmp.scanner

import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

internal class SimpleKey(tokenNumber: Int, isRequired: Boolean, index: Int, line: Int, column: Int, mark: Mark?) {
   public final val tokenNumber: Int
   public final val isRequired: Boolean
   public final val index: Int
   public final val line: Int
   public final val column: Int
   public final val mark: Mark?

   init {
      this.tokenNumber = tokenNumber;
      this.isRequired = isRequired;
      this.index = index;
      this.line = line;
      this.column = column;
      this.mark = mark;
   }

   public override fun toString(): String {
      return "SimpleKey - tokenNumber=${this.tokenNumber} required=${this.isRequired} index=${this.index} line=${this.line} column=${this.column}";
   }
}
