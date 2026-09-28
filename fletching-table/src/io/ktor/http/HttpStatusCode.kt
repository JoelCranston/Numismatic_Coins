package io.ktor.http

import java.util.LinkedHashMap
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nHttpStatusCode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpStatusCode.kt\nio/ktor/http/HttpStatusCode\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,196:1\n1208#2,2:197\n1236#2,4:199\n*S KotlinDebug\n*F\n+ 1 HttpStatusCode.kt\nio/ktor/http/HttpStatusCode\n*L\n119#1:197,2\n119#1:199,4\n*E\n"])
public data class HttpStatusCode(value: Int, description: String) : java.lang.Comparable<HttpStatusCode> {
   public final val value: Int
   public final val description: String

   init {
      this.value = value;
      this.description = description;
   }

   public override fun toString(): String {
      return "${this.value} ${this.description}";
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is HttpStatusCode && (other as HttpStatusCode).value == this.value;
   }

   public override fun hashCode(): Int {
      return Integer.hashCode(this.value);
   }

   public fun description(value: String): HttpStatusCode {
      return copy$default(this, 0, value, 1, null);
   }

   public open operator fun compareTo(other: HttpStatusCode): Int {
      return this.value - other.value;
   }

   public operator fun component1(): Int {
      return this.value;
   }

   public operator fun component2(): String {
      return this.description;
   }

   public fun copy(value: Int = this.value, description: String = this.description): HttpStatusCode {
      return new HttpStatusCode(value, description);
   }

   @JvmStatic
   fun {
      val `$this$associateBy$iv`: java.lang.Iterable = allStatusCodes;
      val `destination$iv$iv`: java.util.Map = new LinkedHashMap(
         kotlin.ranges.RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(allStatusCodes, 10)), 16)
      );

      for (Object element$iv$iv : $this$associateBy$iv) {
         `destination$iv$iv`.put((`element$iv$iv` as HttpStatusCode).value, `element$iv$iv`);
      }

      statusCodesMap = `destination$iv$iv`;
   }

   public companion object {
      public final val Continue: HttpStatusCode
      public final val SwitchingProtocols: HttpStatusCode
      public final val Processing: HttpStatusCode
      public final val OK: HttpStatusCode
      public final val Created: HttpStatusCode
      public final val Accepted: HttpStatusCode
      public final val NonAuthoritativeInformation: HttpStatusCode
      public final val NoContent: HttpStatusCode
      public final val ResetContent: HttpStatusCode
      public final val PartialContent: HttpStatusCode
      public final val MultiStatus: HttpStatusCode
      public final val MultipleChoices: HttpStatusCode
      public final val MovedPermanently: HttpStatusCode
      public final val Found: HttpStatusCode
      public final val SeeOther: HttpStatusCode
      public final val NotModified: HttpStatusCode
      public final val UseProxy: HttpStatusCode
      public final val SwitchProxy: HttpStatusCode
      public final val TemporaryRedirect: HttpStatusCode
      public final val PermanentRedirect: HttpStatusCode
      public final val BadRequest: HttpStatusCode
      public final val Unauthorized: HttpStatusCode
      public final val PaymentRequired: HttpStatusCode
      public final val Forbidden: HttpStatusCode
      public final val NotFound: HttpStatusCode
      public final val MethodNotAllowed: HttpStatusCode
      public final val NotAcceptable: HttpStatusCode
      public final val ProxyAuthenticationRequired: HttpStatusCode
      public final val RequestTimeout: HttpStatusCode
      public final val Conflict: HttpStatusCode
      public final val Gone: HttpStatusCode
      public final val LengthRequired: HttpStatusCode
      public final val PreconditionFailed: HttpStatusCode
      public final val PayloadTooLarge: HttpStatusCode
      public final val RequestURITooLong: HttpStatusCode
      public final val UnsupportedMediaType: HttpStatusCode
      public final val RequestedRangeNotSatisfiable: HttpStatusCode
      public final val ExpectationFailed: HttpStatusCode
      public final val UnprocessableEntity: HttpStatusCode
      public final val Locked: HttpStatusCode
      public final val FailedDependency: HttpStatusCode
      public final val TooEarly: HttpStatusCode
      public final val UpgradeRequired: HttpStatusCode
      public final val TooManyRequests: HttpStatusCode
      public final val RequestHeaderFieldTooLarge: HttpStatusCode
      public final val InternalServerError: HttpStatusCode
      public final val NotImplemented: HttpStatusCode
      public final val BadGateway: HttpStatusCode
      public final val ServiceUnavailable: HttpStatusCode
      public final val GatewayTimeout: HttpStatusCode
      public final val VersionNotSupported: HttpStatusCode
      public final val VariantAlsoNegotiates: HttpStatusCode
      public final val InsufficientStorage: HttpStatusCode
      public final val allStatusCodes: List<HttpStatusCode>
      private final val statusCodesMap: Map<Int, HttpStatusCode>

      public fun fromValue(value: Int): HttpStatusCode {
         var var10000: HttpStatusCode = HttpStatusCode.access$getStatusCodesMap$cp().get(value) as HttpStatusCode;
         if (var10000 == null) {
            var10000 = new HttpStatusCode(value, "Unknown Status Code");
         }

         return var10000;
      }
   }
}
