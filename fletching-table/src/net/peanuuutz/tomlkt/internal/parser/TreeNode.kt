package net.peanuuutz.tomlkt.internal.parser

internal sealed class TreeNode protected constructor(key: String) {
   public final val key: String

   init {
      this.key = key;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         return other != null && this.getClass() === other.getClass() && this.key == (other as TreeNode).key;
      }
   }

   public override fun hashCode(): Int {
      return this.key.hashCode();
   }
}
