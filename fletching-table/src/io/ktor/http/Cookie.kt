package io.ktor.http

import io.ktor.util.date.GMTDate
import io.ktor.utils.io.JvmSerializable_jvmKt
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
public data class Cookie(name: String,
      value: String,
      encoding: CookieEncoding = CookieEncoding.URI_ENCODING,
      maxAge: Int? = null,
      expires: GMTDate? = null,
      domain: String? = null,
      path: String? = null,
      secure: Boolean = false,
      httpOnly: Boolean = false,
      extensions: Map<String, String?> = MapsKt.emptyMap()
   ) :
   java.io.Serializable {
   public final val name: String
   public final val value: String
   public final val encoding: CookieEncoding
   public final val maxAge: Int?
   public final val expires: GMTDate?
   public final val domain: String?
   public final val path: String?
   public final val secure: Boolean
   public final val httpOnly: Boolean
   public final val extensions: Map<String, String?>

   init {
      this.name = name;
      this.value = value;
      this.encoding = encoding;
      this.maxAge = maxAge;
      this.expires = expires;
      this.domain = domain;
      this.path = path;
      this.secure = secure;
      this.httpOnly = httpOnly;
      this.extensions = extensions;
   }

   private fun writeReplace(): Any {
      return JvmSerializable_jvmKt.JvmSerializerReplacement(CookieJvmSerializer.INSTANCE, this);
   }

   public operator fun component1(): String {
      return this.name;
   }

   public operator fun component2(): String {
      return this.value;
   }

   public operator fun component3(): CookieEncoding {
      return this.encoding;
   }

   public operator fun component4(): Int? {
      return this.maxAge;
   }

   public operator fun component5(): GMTDate? {
      return this.expires;
   }

   public operator fun component6(): String? {
      return this.domain;
   }

   public operator fun component7(): String? {
      return this.path;
   }

   public operator fun component8(): Boolean {
      return this.secure;
   }

   public operator fun component9(): Boolean {
      return this.httpOnly;
   }

   public operator fun component10(): Map<String, String?> {
      return this.extensions;
   }

   public fun copy(
      name: String = this.name,
      value: String = this.value,
      encoding: CookieEncoding = this.encoding,
      maxAge: Int? = this.maxAge,
      expires: GMTDate? = this.expires,
      domain: String? = this.domain,
      path: String? = this.path,
      secure: Boolean = this.secure,
      httpOnly: Boolean = this.httpOnly,
      extensions: Map<String, String?> = this.extensions
   ): Cookie {
      return new Cookie(name, value, encoding, maxAge, expires, domain, path, secure, httpOnly, extensions);
   }

   public override fun toString(): String {
      return "Cookie(name=${this.name}, value=${this.value}, encoding=${this.encoding}, maxAge=${this.maxAge}, expires=${this.expires}, domain=${this.domain}, path=${this.path}, secure=${this.secure}, httpOnly=${this.httpOnly}, extensions=${this.extensions})";
   }

   public override fun hashCode(): Int {
      return (
               (
                        (
                                 (
                                          (
                                                   (
                                                            ((this.name.hashCode() * 31 + this.value.hashCode()) * 31 + this.encoding.hashCode()) * 31
                                                               + (if (this.maxAge == null) 0 else this.maxAge.hashCode())
                                                         )
                                                         * 31
                                                      + (if (this.expires == null) 0 else this.expires.hashCode())
                                                )
                                                * 31
                                             + (if (this.domain == null) 0 else this.domain.hashCode())
                                       )
                                       * 31
                                    + (if (this.path == null) 0 else this.path.hashCode())
                              )
                              * 31
                           + java.lang.Boolean.hashCode(this.secure)
                     )
                     * 31
                  + java.lang.Boolean.hashCode(this.httpOnly)
            )
            * 31
         + this.extensions.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is Cookie) {
         return false;
      } else {
         val var2: Cookie = other as Cookie;
         if (!(this.name == (other as Cookie).name)) {
            return false;
         } else if (!(this.value == var2.value)) {
            return false;
         } else if (this.encoding != var2.encoding) {
            return false;
         } else if (!(this.maxAge == var2.maxAge)) {
            return false;
         } else if (!(this.expires == var2.expires)) {
            return false;
         } else if (!(this.domain == var2.domain)) {
            return false;
         } else if (!(this.path == var2.path)) {
            return false;
         } else if (this.secure != var2.secure) {
            return false;
         } else if (this.httpOnly != var2.httpOnly) {
            return false;
         } else {
            return this.extensions == var2.extensions;
         }
      }
   }

   public companion object {
      public fun serializer(): KSerializer<Cookie> {
         return Cookie.$serializer.INSTANCE;
      }
   }
}
