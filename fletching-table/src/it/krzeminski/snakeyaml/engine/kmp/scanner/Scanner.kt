package it.krzeminski.snakeyaml.engine.kmp.scanner

import it.krzeminski.snakeyaml.engine.kmp.tokens.Token
import it.krzeminski.snakeyaml.engine.kmp.tokens.Token.ID
import kotlin.jvm.internal.markers.KMappedMarker

public interface Scanner : java.util.Iterator<Token>, KMappedMarker {
   public abstract fun checkToken(vararg choices: ID): Boolean {
   }

   public open fun checkToken(choice: ID): Boolean {
      return this.checkToken(choice);
   }

   public abstract fun peekToken(): Token {
   }

   public abstract operator fun next(): Token {
   }

   public abstract fun resetDocumentIndex() {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun checkToken(`$this`: Scanner, choice: Token.ID): Boolean {
         return Scanner.access$checkToken$jd(`$this`, choice);
      }
   }
}
