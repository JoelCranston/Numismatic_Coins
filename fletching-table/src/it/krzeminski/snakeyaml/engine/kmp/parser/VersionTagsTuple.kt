package it.krzeminski.snakeyaml.engine.kmp.parser

import it.krzeminski.snakeyaml.engine.kmp.common.SpecVersion

internal data class VersionTagsTuple(specVersion: SpecVersion?, tags: Map<String, String>) {
   public final val specVersion: SpecVersion?
   public final val tags: Map<String, String>

   init {
      this.specVersion = specVersion;
      this.tags = tags;
   }

   public override fun toString(): String {
      return "VersionTagsTuple<${this.specVersion}, ${this.tags}>";
   }

   public operator fun component1(): SpecVersion? {
      return this.specVersion;
   }

   public operator fun component2(): Map<String, String> {
      return this.tags;
   }

   public fun copy(specVersion: SpecVersion? = this.specVersion, tags: Map<String, String> = this.tags): VersionTagsTuple {
      return new VersionTagsTuple(specVersion, tags);
   }

   public override fun hashCode(): Int {
      return (if (this.specVersion == null) 0 else this.specVersion.hashCode()) * 31 + this.tags.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is VersionTagsTuple) {
         return false;
      } else {
         val var2: VersionTagsTuple = other as VersionTagsTuple;
         if (!(this.specVersion == (other as VersionTagsTuple).specVersion)) {
            return false;
         } else {
            return this.tags == var2.tags;
         }
      }
   }
}
