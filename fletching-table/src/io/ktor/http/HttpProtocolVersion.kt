package io.ktor.http

public data class HttpProtocolVersion(name: String, major: Int, minor: Int) {
   public final val name: String
   public final val major: Int
   public final val minor: Int

   init {
      this.name = name;
      this.major = major;
      this.minor = minor;
   }

   public override fun toString(): String {
      return "${this.name}/${this.major}.${this.minor}";
   }

   public operator fun component1(): String {
      return this.name;
   }

   public operator fun component2(): Int {
      return this.major;
   }

   public operator fun component3(): Int {
      return this.minor;
   }

   public fun copy(name: String = this.name, major: Int = this.major, minor: Int = this.minor): HttpProtocolVersion {
      return new HttpProtocolVersion(name, major, minor);
   }

   public override fun hashCode(): Int {
      return (this.name.hashCode() * 31 + Integer.hashCode(this.major)) * 31 + Integer.hashCode(this.minor);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is HttpProtocolVersion) {
         return false;
      } else {
         val var2: HttpProtocolVersion = other as HttpProtocolVersion;
         if (!(this.name == (other as HttpProtocolVersion).name)) {
            return false;
         } else if (this.major != var2.major) {
            return false;
         } else {
            return this.minor == var2.minor;
         }
      }
   }

   public companion object {
      public final val HTTP_3_0: HttpProtocolVersion
      public final val HTTP_2_0: HttpProtocolVersion
      public final val HTTP_1_1: HttpProtocolVersion
      public final val HTTP_1_0: HttpProtocolVersion
      public final val SPDY_3: HttpProtocolVersion
      public final val QUIC: HttpProtocolVersion

      public fun fromValue(name: String, major: Int, minor: Int): HttpProtocolVersion {
         return if (name == "HTTP" && major == 1 && minor == 0)
            this.getHTTP_1_0()
            else
            (
               if (name == "HTTP" && major == 1 && minor == 1)
                  this.getHTTP_1_1()
                  else
                  (
                     if (name == "HTTP" && major == 2 && minor == 0)
                        this.getHTTP_2_0()
                        else
                        (if (name == "HTTP" && major == 3 && minor == 0) this.getHTTP_3_0() else new HttpProtocolVersion(name, major, minor))
                  )
            );
      }

      public fun parse(value: CharSequence): HttpProtocolVersion {
         val var7: java.util.List = StringsKt.split$default(value, new java.lang.String[]{"/", "."}, false, 0, 6, null);
         if (var7.size() != 3) {
            throw new IllegalStateException(("Failed to parse HttpProtocolVersion. Expected format: protocol/major.minor, but actual: $value").toString());
         } else {
            return this.fromValue(
               var7.get(0) as java.lang.String, Integer.parseInt(var7.get(1) as java.lang.String), Integer.parseInt(var7.get(2) as java.lang.String)
            );
         }
      }
   }
}
