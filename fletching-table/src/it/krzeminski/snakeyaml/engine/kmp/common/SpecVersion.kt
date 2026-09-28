package it.krzeminski.snakeyaml.engine.kmp.common

public class SpecVersion(major: Int, minor: Int) {
   public final val major: Int
   public final val minor: Int

   public final val representation: String
      public final get() {
         return "${this.major}.${this.minor}";
      }


   init {
      this.major = major;
      this.minor = minor;
   }

   public override fun toString(): String {
      return "Version{major=${this.major}, minor=${this.minor}}";
   }
}
