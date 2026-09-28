package kotlin

@SinceKotlin(version = "1.1")
public class KotlinVersion(major: Int, minor: Int, patch: Int) : java.lang.Comparable<KotlinVersion> {
   public final val major: Int
   public final val minor: Int
   public final val patch: Int
   private final val version: Int

   init {
      this.major = major;
      this.minor = minor;
      this.patch = patch;
      this.version = this.versionOf(this.major, this.minor, this.patch);
   }

   public constructor(major: Int, minor: Int) : this(major, minor, 0)
   private fun versionOf(major: Int, minor: Int, patch: Int): Int {
      if (0 > major || major >= 256 || 0 > minor || minor >= 256 || 0 > patch || patch >= 256) {
         throw new IllegalArgumentException(("Version components are out of range: $major${46}$minor${46}$patch").toString());
      } else {
         return (major shl 16) + (minor shl 8) + patch;
      }
   }

   public override fun toString(): String {
      return "${this.major}.${this.minor}.${this.patch}";
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         val var10000: KotlinVersion = other as? KotlinVersion;
         if ((other as? KotlinVersion) == null) {
            return false;
         } else {
            return this.version == var10000.version;
         }
      }
   }

   public override fun hashCode(): Int {
      return this.version;
   }

   public open operator fun compareTo(other: KotlinVersion): Int {
      return this.version - other.version;
   }

   public fun isAtLeast(major: Int, minor: Int): Boolean {
      return this.major > major || this.major == major && this.minor >= minor;
   }

   public fun isAtLeast(major: Int, minor: Int, patch: Int): Boolean {
      return this.major > major || this.major == major && (this.minor > minor || this.minor == minor && this.patch >= patch);
   }

   public companion object {
      public const val MAX_COMPONENT_VALUE: Int
      public final val CURRENT: KotlinVersion
   }
}
