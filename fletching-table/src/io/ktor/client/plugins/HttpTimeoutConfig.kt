package io.ktor.client.plugins

import io.ktor.util.AttributeKey
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.KtorDsl
import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType

@KtorDsl
@SourceDebugExtension(["SMAP\nHttpTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpTimeout.kt\nio/ktor/client/plugins/HttpTimeoutConfig\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,314:1\n21#2:315\n69#3:316\n84#3,8:317\n*S KotlinDebug\n*F\n+ 1 HttpTimeout.kt\nio/ktor/client/plugins/HttpTimeoutConfig\n*L\n118#1:315\n118#1:316\n118#1:317,8\n*E\n"])
public class HttpTimeoutConfig {
   private final var _requestTimeoutMillis: Long? = 0L
   private final var _connectTimeoutMillis: Long? = 0L
   private final var _socketTimeoutMillis: Long? = 0L

   public final var requestTimeoutMillis: Long?
      public final get() {
         return this._requestTimeoutMillis;
      }

      public final set(value) {
         this._requestTimeoutMillis = this.checkTimeoutValue(value);
      }


   public final var connectTimeoutMillis: Long?
      public final get() {
         return this._connectTimeoutMillis;
      }

      public final set(value) {
         this._connectTimeoutMillis = this.checkTimeoutValue(value);
      }


   public final var socketTimeoutMillis: Long?
      public final get() {
         return this._socketTimeoutMillis;
      }

      public final set(value) {
         this._socketTimeoutMillis = this.checkTimeoutValue(value);
      }


   public constructor(requestTimeoutMillis: Long? = null, connectTimeoutMillis: Long? = null, socketTimeoutMillis: Long? = null) : this.setRequestTimeoutMillis(
         requestTimeoutMillis
      ) {
      this.setConnectTimeoutMillis(connectTimeoutMillis);
      this.setSocketTimeoutMillis(socketTimeoutMillis);
   }

   private fun checkTimeoutValue(value: Long?): Long? {
      if (value != null && value <= 0L) {
         throw new IllegalArgumentException(
            "Only positive timeout values are allowed, for infinite timeout use HttpTimeoutConfig.INFINITE_TIMEOUT_MS".toString()
         );
      } else {
         return value;
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other == null || this.getClass() != other.getClass()) {
         return false;
      } else if (!(this._requestTimeoutMillis == (other as HttpTimeoutConfig)._requestTimeoutMillis)) {
         return false;
      } else if (!(this._connectTimeoutMillis == (other as HttpTimeoutConfig)._connectTimeoutMillis)) {
         return false;
      } else {
         return this._socketTimeoutMillis == (other as HttpTimeoutConfig)._socketTimeoutMillis;
      }
   }

   public override fun hashCode(): Int {
      return 31
            * (
               31 * (if (this._requestTimeoutMillis != null) java.lang.Long.hashCode(this._requestTimeoutMillis) else 0)
                  + (if (this._connectTimeoutMillis != null) java.lang.Long.hashCode(this._connectTimeoutMillis) else 0)
            )
         + (if (this._socketTimeoutMillis != null) java.lang.Long.hashCode(this._socketTimeoutMillis) else 0);
   }

   @JvmStatic
   fun {
      var var6: KType;
      try {
         var6 = Reflection.typeOf(HttpTimeoutConfig.class);
      } catch (var12: java.lang.Throwable) {
         var6 = null;
      }

      key = new AttributeKey<>("TimeoutConfiguration", new TypeInfo(HttpTimeoutConfig::class, var6));
   }

   public companion object {
      public const val INFINITE_TIMEOUT_MS: Long
      public final val key: AttributeKey<HttpTimeoutConfig>
   }
}
