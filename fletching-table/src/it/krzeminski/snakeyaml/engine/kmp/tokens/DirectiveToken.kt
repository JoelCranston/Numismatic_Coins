package it.krzeminski.snakeyaml.engine.kmp.tokens

import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import it.krzeminski.snakeyaml.engine.kmp.tokens.Token.ID

public class DirectiveToken(value: it.krzeminski.snakeyaml.engine.kmp.tokens.DirectiveToken.TokenValue?, startMark: Mark?, endMark: Mark?) : Token(
      startMark, endMark
   ) {
   public final val value: it.krzeminski.snakeyaml.engine.kmp.tokens.DirectiveToken.TokenValue?

   public open val tokenId: ID
      public open get() {
         return Token.ID.Directive;
      }


   init {
      this.value = value;
   }

   public companion object {
      public const val YAML_DIRECTIVE: String
      public const val TAG_DIRECTIVE: String
   }

   public data class TagDirective(handle: String, prefix: String) : DirectiveToken.TokenValue {
      public final val handle: String
      public final val prefix: String

      public open val name: String
         public open get() {
            return "TAG";
         }


      init {
         this.handle = handle;
         this.prefix = prefix;
      }

      public operator fun component1(): String {
         return this.handle;
      }

      public operator fun component2(): String {
         return this.prefix;
      }

      public fun copy(handle: String = this.handle, prefix: String = this.prefix): it.krzeminski.snakeyaml.engine.kmp.tokens.DirectiveToken.TagDirective {
         return new DirectiveToken.TagDirective(handle, prefix);
      }

      public override fun toString(): String {
         return "TagDirective(handle=${this.handle}, prefix=${this.prefix})";
      }

      public override fun hashCode(): Int {
         return this.handle.hashCode() * 31 + this.prefix.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is DirectiveToken.TagDirective) {
            return false;
         } else {
            val var2: DirectiveToken.TagDirective = other as DirectiveToken.TagDirective;
            if (!(this.handle == (other as DirectiveToken.TagDirective).handle)) {
               return false;
            } else {
               return this.prefix == var2.prefix;
            }
         }
      }
   }

   public sealed interface TokenValue {
      public val name: String
   }

   public data class YamlDirective(major: Int, minor: Int) : DirectiveToken.TokenValue {
      public final val major: Int
      public final val minor: Int

      public open val name: String
         public open get() {
            return "YAML";
         }


      init {
         this.major = major;
         this.minor = minor;
      }

      public operator fun component1(): Int {
         return this.major;
      }

      public operator fun component2(): Int {
         return this.minor;
      }

      public fun copy(major: Int = this.major, minor: Int = this.minor): it.krzeminski.snakeyaml.engine.kmp.tokens.DirectiveToken.YamlDirective {
         return new DirectiveToken.YamlDirective(major, minor);
      }

      public override fun toString(): String {
         return "YamlDirective(major=${this.major}, minor=${this.minor})";
      }

      public override fun hashCode(): Int {
         return Integer.hashCode(this.major) * 31 + Integer.hashCode(this.minor);
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is DirectiveToken.YamlDirective) {
            return false;
         } else {
            val var2: DirectiveToken.YamlDirective = other as DirectiveToken.YamlDirective;
            if (this.major != (other as DirectiveToken.YamlDirective).major) {
               return false;
            } else {
               return this.minor == var2.minor;
            }
         }
      }
   }
}
