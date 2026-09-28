package io.ktor.http.auth

import io.ktor.http.CodecsKt
import io.ktor.http.HeaderValueParam
import io.ktor.http.HeaderValueWithParametersKt
import io.ktor.http.parsing.ParseException
import io.ktor.util.CryptoKt
import io.ktor.util.Hash
import io.ktor.utils.io.charsets.CharsetJVMKt
import java.nio.charset.Charset
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.Locale
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension

public sealed class HttpAuthHeader protected constructor(authScheme: String) {
   public final val authScheme: String

   init {
      this.authScheme = authScheme;
      val var2: java.lang.CharSequence = this.authScheme;
      if (!HttpAuthHeaderKt.access$getToken68Pattern$p().matches(var2)) {
         throw new ParseException("Invalid authScheme value: it should be token, but instead it is ${this.authScheme}", null, 2, null);
      }
   }

   public abstract fun render(encoding: HeaderValueEncoding): String {
   }

   public abstract fun render(): String {
   }

   public override fun toString(): String {
      return this.render();
   }

   public companion object {
      public fun basicAuthChallenge(realm: String, charset: Charset?): io.ktor.http.auth.HttpAuthHeader.Parameterized {
         val var3: LinkedHashMap = new LinkedHashMap();
         var3.put("realm", realm);
         if (charset != null) {
            var3.put("charset", CharsetJVMKt.getName(charset));
         }

         return new HttpAuthHeader.Parameterized("Basic", var3, null, 4, null);
      }

      public fun bearerAuthChallenge(scheme: String, realm: String? = null): HttpAuthHeader {
         return new HttpAuthHeader.Parameterized(scheme, if (realm == null) MapsKt.emptyMap() else MapsKt.mapOf(TuplesKt.to("realm", realm)), null, 4, null);
      }

      public fun digestAuthChallenge(
         realm: String,
         nonce: String = CryptoKt.generateNonce(),
         domain: List<String> = CollectionsKt.emptyList(),
         opaque: String? = null,
         stale: Boolean? = null,
         algorithm: String = "MD5"
      ): io.ktor.http.auth.HttpAuthHeader.Parameterized {
         val var7: LinkedHashMap = new LinkedHashMap();
         var7.put("realm", HeaderValueWithParametersKt.quote(realm));
         var7.put("nonce", HeaderValueWithParametersKt.quote(nonce));
         if (!domain.isEmpty()) {
            var7.put("domain", HeaderValueWithParametersKt.quote(CollectionsKt.joinToString$default(domain, " ", null, null, 0, null, null, 62, null)));
         }

         if (opaque != null) {
            var7.put("opaque", HeaderValueWithParametersKt.quote(opaque));
         }

         if (stale != null) {
            var7.put("stale", java.lang.String.valueOf(stale.booleanValue()));
         }

         var7.put("algorithm", algorithm);
         return new HttpAuthHeader.Parameterized("Digest", var7, HeaderValueEncoding.QUOTED_WHEN_REQUIRED);
      }
   }

   @SourceDebugExtension(["SMAP\nHttpAuthHeader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpAuthHeader.kt\nio/ktor/http/auth/HttpAuthHeader$Parameterized\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,499:1\n1869#2,2:500\n1563#2:502\n1634#2,3:503\n360#2,7:506\n1617#2,9:513\n1869#2:522\n1870#2:524\n1626#2:525\n295#2,2:526\n1#3:523\n*S KotlinDebug\n*F\n+ 1 HttpAuthHeader.kt\nio/ktor/http/auth/HttpAuthHeader$Parameterized\n*L\n294#1:500,2\n291#1:502\n291#1:503,3\n318#1:506,7\n322#1:513,9\n322#1:522\n322#1:524\n322#1:525\n347#1:526,2\n322#1:523\n*E\n"])
   public class Parameterized(authScheme: String, parameters: List<HeaderValueParam>, encoding: HeaderValueEncoding = HeaderValueEncoding.QUOTED_WHEN_REQUIRED) : HttpAuthHeader(
         authScheme
      ) {
      public final val parameters: List<HeaderValueParam>
      public final val encoding: HeaderValueEncoding

      init {
         this.parameters = parameters;
         this.encoding = encoding;

         val `$this$forEach$iv`: java.lang.Iterable;
         for (Object element$iv : $this$forEach$iv) {
            if (!HttpAuthHeaderKt.access$getToken68Pattern$p().matches((`element$iv` as HeaderValueParam).getName())) {
               throw new ParseException("Parameter name should be a token", null, 2, null);
            }
         }
      }

      public constructor(authScheme: String, parameters: Map<String, String>, encoding: HeaderValueEncoding = HeaderValueEncoding.QUOTED_WHEN_REQUIRED)  {
         val `$this$map$iv`: java.lang.Iterable = parameters.entrySet();
         val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$map$iv`, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$iv`.add(
               new HeaderValueParam((`item$iv$iv` as Entry).getKey() as java.lang.String, (`item$iv$iv` as Entry).getValue() as java.lang.String)
            );
         }

         this(authScheme, `destination$iv$iv` as MutableList<HeaderValueParam>, encoding);
      }

      public fun withParameter(name: String, value: String): io.ktor.http.auth.HttpAuthHeader.Parameterized {
         return new HttpAuthHeader.Parameterized(this.getAuthScheme(), CollectionsKt.plus(this.parameters, new HeaderValueParam(name, value)), this.encoding);
      }

      public fun withReplacedParameter(name: String, value: String): io.ktor.http.auth.HttpAuthHeader.Parameterized {
         var `$this$mapNotNull$iv`: Int = 0;
         val `$i$f$mapNotNull`: java.util.Iterator = this.parameters.iterator();

         var var10000: Int;
         while (true) {
            if (!`$i$f$mapNotNull`.hasNext()) {
               var10000 = -1;
               break;
            }

            if ((`$i$f$mapNotNull`.next() as HeaderValueParam).getName() == name) {
               var10000 = `$this$mapNotNull$iv`;
               break;
            }

            `$this$mapNotNull$iv`++;
         }

         if (var10000 == -1) {
            return this.withParameter(name, value);
         } else {
            var var21: Boolean = false;
            val var23: java.lang.Iterable = this.parameters;
            val var25: java.util.Collection = new ArrayList();

            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
               val it: HeaderValueParam = `element$iv$iv$iv` as HeaderValueParam;
               val var27: HeaderValueParam;
               if (!((`element$iv$iv$iv` as HeaderValueParam).getName() == name)) {
                  var27 = it;
               } else if (!var21) {
                  var21 = true;
                  var27 = new HeaderValueParam(name, value);
               } else {
                  var27 = null;
               }

               if (var27 != null) {
                  var25.add(var27);
               }
            }

            return new HttpAuthHeader.Parameterized(this.getAuthScheme(), var25 as MutableList<HeaderValueParam>, this.encoding);
         }
      }

      public override fun render(encoding: HeaderValueEncoding): String {
         return if (this.parameters.isEmpty())
            this.getAuthScheme()
            else
            CollectionsKt.joinToString$default(
               this.parameters, ", ", "${this.getAuthScheme()} ", null, 0, null, HttpAuthHeader.Parameterized::render$lambda$0, 28, null
            );
      }

      public fun parameter(name: String): String? {
         val var4: java.util.Iterator = this.parameters.iterator();

         var var10000: Any;
         while (true) {
            if (var4.hasNext()) {
               val `element$iv`: Any = var4.next();
               if (!((`element$iv` as HeaderValueParam).getName() == name)) {
                  continue;
               }

               var10000 = `element$iv`;
               break;
            }

            var10000 = null;
            break;
         }

         return if (var10000 as HeaderValueParam != null) (var10000 as HeaderValueParam).getValue() else null;
      }

      private fun String.encode(encoding: HeaderValueEncoding): String {
         var var10000: java.lang.String;
         switch (HttpAuthHeader.Parameterized.WhenMappings.$EnumSwitchMapping$0[encoding.ordinal()]) {
            case 1:
               var10000 = HeaderValueWithParametersKt.escapeIfNeeded(`$this$encode`);
               break;
            case 2:
               var10000 = HeaderValueWithParametersKt.quote(`$this$encode`);
               break;
            case 3:
               var10000 = CodecsKt.encodeURLParameter$default(`$this$encode`, false, 1, null);
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         return var10000;
      }

      public override fun render(): String {
         return this.render(this.encoding);
      }

      public override operator fun equals(other: Any?): Boolean {
         if (other !is HttpAuthHeader.Parameterized) {
            return false;
         } else {
            return StringsKt.equals((other as HttpAuthHeader.Parameterized).getAuthScheme(), this.getAuthScheme(), true)
               && (other as HttpAuthHeader.Parameterized).parameters == this.parameters;
         }
      }

      public override fun hashCode(): Int {
         val var10000: Hash = Hash.INSTANCE;
         val var1: Array<Any> = new Object[2];
         val var10003: java.lang.String = this.getAuthScheme().toLowerCase(Locale.ROOT);
         var1[0] = var10003;
         var1[1] = this.parameters;
         return var10000.combine(var1);
      }

      @JvmStatic
      fun `render$lambda$0`(`this$0`: HttpAuthHeader.Parameterized, `$encoding`: HeaderValueEncoding, it: HeaderValueParam): java.lang.CharSequence {
         return "${it.getName()}=${`this$0`.encode(it.getValue(), `$encoding`)}";
      }
   }

   public object Parameters {
      public const val Realm: String = "realm"
      public const val Charset: String = "charset"
      public const val OAuthCallback: String = "oauth_callback"
      public const val OAuthConsumerKey: String = "oauth_consumer_key"
      public const val OAuthNonce: String = "oauth_nonce"
      public const val OAuthToken: String = "oauth_token"
      public const val OAuthTokenSecret: String = "oauth_token_secret"
      public const val OAuthVerifier: String = "oauth_verifier"
      public const val OAuthSignatureMethod: String = "oauth_signature_method"
      public const val OAuthTimestamp: String = "oauth_timestamp"
      public const val OAuthVersion: String = "oauth_version"
      public const val OAuthSignature: String = "oauth_signature"
      public const val OAuthCallbackConfirmed: String = "oauth_callback_confirmed"
   }

   public class Single(authScheme: String, blob: String) : HttpAuthHeader(authScheme) {
      public final val blob: String

      init {
         this.blob = blob;
         val var3: java.lang.CharSequence = this.blob;
         if (!HttpAuthHeaderKt.access$getToken68Pattern$p().matches(var3)) {
            throw new ParseException("Invalid blob value: it should be token68", null, 2, null);
         }
      }

      public override fun render(): String {
         return "${this.getAuthScheme()} ${this.blob}";
      }

      public override fun render(encoding: HeaderValueEncoding): String {
         return this.render();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (other !is HttpAuthHeader.Single) {
            return false;
         } else {
            return StringsKt.equals((other as HttpAuthHeader.Single).getAuthScheme(), this.getAuthScheme(), true)
               && StringsKt.equals((other as HttpAuthHeader.Single).blob, this.blob, true);
         }
      }

      public override fun hashCode(): Int {
         val var10000: Hash = Hash.INSTANCE;
         val var1: Array<Any> = new Object[2];
         var var10003: java.lang.String = this.getAuthScheme().toLowerCase(Locale.ROOT);
         var1[0] = var10003;
         var10003 = this.blob.toLowerCase(Locale.ROOT);
         var1[1] = var10003;
         return var10000.combine(var1);
      }
   }
}
