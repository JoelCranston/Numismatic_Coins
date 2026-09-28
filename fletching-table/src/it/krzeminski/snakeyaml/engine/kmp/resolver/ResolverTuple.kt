package it.krzeminski.snakeyaml.engine.kmp.resolver

import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag

internal data class ResolverTuple(tag: Tag, regexp: Regex) {
   public final val tag: Tag
   public final val regexp: Regex

   init {
      this.tag = tag;
      this.regexp = regexp;
   }

   public override fun toString(): String {
      return "Tuple tag=${this.tag} regexp=${this.regexp}";
   }

   public operator fun component1(): Tag {
      return this.tag;
   }

   public operator fun component2(): Regex {
      return this.regexp;
   }

   public fun copy(tag: Tag = this.tag, regexp: Regex = this.regexp): ResolverTuple {
      return new ResolverTuple(tag, regexp);
   }

   public override fun hashCode(): Int {
      return this.tag.hashCode() * 31 + this.regexp.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is ResolverTuple) {
         return false;
      } else {
         val var2: ResolverTuple = other as ResolverTuple;
         if (!(this.tag == (other as ResolverTuple).tag)) {
            return false;
         } else {
            return this.regexp == var2.regexp;
         }
      }
   }
}
