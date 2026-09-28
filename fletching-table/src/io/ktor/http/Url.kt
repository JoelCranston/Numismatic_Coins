package io.ktor.http

import io.ktor.utils.io.JvmSerializable_jvmKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = UrlSerializer::class)
@SourceDebugExtension(["SMAP\nUrl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Url.kt\nio/ktor/http/Url\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,300:1\n1#2:301\n*E\n"])
public class Url internal constructor(protocol: URLProtocol?,
      host: String,
      specifiedPort: Int,
      pathSegments: List<String>,
      parameters: Parameters,
      fragment: String,
      user: String?,
      password: String?,
      trailingQuery: Boolean,
      urlString: String
   ) :
   java.io.Serializable {
   public final val host: String
   public final val specifiedPort: Int
   public final val parameters: Parameters
   public final val fragment: String
   public final val user: String?
   public final val password: String?
   public final val trailingQuery: Boolean
   private final val urlString: String

   @Deprecated(
      message = "\n        `pathSegments` is deprecated.\n\n        This property will contain an empty path segment at the beginning for URLs with a hostname,\n        and an empty path segment at the end for the URLs with a trailing slash. If you need to keep this behaviour please\n        use [rawSegments]. If you only need to access the meaningful parts of the path, consider using [segments] instead.\n             \n        Please decide if you need [rawSegments] or [segments] explicitly.\n        ",
      replaceWith = @ReplaceWith(
         expression = "rawSegments",
         imports = {}
      )
   )
   public final val pathSegments: List<String>

   public final val rawSegments: List<String>

   public final val segments: List<String>
      public final get() {
         return this.segments$delegate.getValue() as MutableList<java.lang.String>;
      }


   public final val protocolOrNull: URLProtocol?
   public final val protocol: URLProtocol

   public final val port: Int
      public final get() {
         val var1: Int = this.specifiedPort;
         val it: Int = var1.intValue();
         return if ((if (it != 0) var1 else null) != null) if (it != 0) var1 else null else this.protocol.getDefaultPort();
      }


   public final val encodedPath: String
      public final get() {
         return this.encodedPath$delegate.getValue() as java.lang.String;
      }


   public final val encodedQuery: String
      public final get() {
         return this.encodedQuery$delegate.getValue() as java.lang.String;
      }


   public final val encodedPathAndQuery: String
      public final get() {
         return this.encodedPathAndQuery$delegate.getValue() as java.lang.String;
      }


   public final val encodedUser: String?
      public final get() {
         return this.encodedUser$delegate.getValue() as java.lang.String;
      }


   public final val encodedPassword: String?
      public final get() {
         return this.encodedPassword$delegate.getValue() as java.lang.String;
      }


   public final val encodedFragment: String
      public final get() {
         return this.encodedFragment$delegate.getValue() as java.lang.String;
      }


   init {
      this.host = host;
      this.specifiedPort = specifiedPort;
      this.parameters = parameters;
      this.fragment = fragment;
      this.user = user;
      this.password = password;
      this.trailingQuery = trailingQuery;
      this.urlString = urlString;
      if (0 > this.specifiedPort || this.specifiedPort >= 65536) {
         throw new IllegalArgumentException(("Port must be between 0 and 65535, or 0 if not set. Provided: ${this.specifiedPort}").toString());
      } else {
         this.pathSegments = pathSegments;
         this.rawSegments = pathSegments;
         this.segments$delegate = LazyKt.lazy(Url::segments_delegate$lambda$0);
         this.protocolOrNull = protocol;
         var var10001: URLProtocol = this.protocolOrNull;
         if (this.protocolOrNull == null) {
            var10001 = URLProtocol.Companion.getHTTP();
         }

         this.protocol = var10001;
         this.encodedPath$delegate = LazyKt.lazy(Url::encodedPath_delegate$lambda$0);
         this.encodedQuery$delegate = LazyKt.lazy(Url::encodedQuery_delegate$lambda$0);
         this.encodedPathAndQuery$delegate = LazyKt.lazy(Url::encodedPathAndQuery_delegate$lambda$0);
         this.encodedUser$delegate = LazyKt.lazy(Url::encodedUser_delegate$lambda$0);
         this.encodedPassword$delegate = LazyKt.lazy(Url::encodedPassword_delegate$lambda$0);
         this.encodedFragment$delegate = LazyKt.lazy(Url::encodedFragment_delegate$lambda$0);
      }
   }

   public override fun toString(): String {
      return this.urlString;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         return other != null && this.getClass() === other.getClass() && this.urlString == (other as Url).urlString;
      }
   }

   public override fun hashCode(): Int {
      return this.urlString.hashCode();
   }

   private fun writeReplace(): Any {
      return JvmSerializable_jvmKt.JvmSerializerReplacement(UrlJvmSerializer.INSTANCE, this);
   }

   @JvmStatic
   fun `segments_delegate$lambda$0`(`$pathSegments`: java.util.List): java.util.List {
      return if (`$pathSegments`.isEmpty())
         CollectionsKt.emptyList()
         else
         `$pathSegments`.subList(
            if (CollectionsKt.<java.lang.CharSequence>first(`$pathSegments`).length() == 0 && `$pathSegments`.size() > 1) 1 else 0,
            if (CollectionsKt.<java.lang.CharSequence>last(`$pathSegments`).length() == 0)
               CollectionsKt.getLastIndex(`$pathSegments`)
               else
               CollectionsKt.getLastIndex(`$pathSegments`) + 1
         );
   }

   @JvmStatic
   fun `encodedPath_delegate$lambda$0`(`$pathSegments`: java.util.List, `this$0`: Url): java.lang.String {
      if (`$pathSegments`.isEmpty()) {
         return "";
      } else {
         val pathStartIndex: Int = StringsKt.indexOf$default(`this$0`.urlString, '/', `this$0`.protocol.getName().length() + 3, false, 4, null);
         if (pathStartIndex == -1) {
            return "";
         } else {
            val pathEndIndex: Int = StringsKt.indexOfAny$default(`this$0`.urlString, new char[]{'?', '#'}, pathStartIndex, false, 4, null);
            if (pathEndIndex == -1) {
               val var6: java.lang.String = `this$0`.urlString.substring(pathStartIndex);
               return var6;
            } else {
               val var5: java.lang.String = `this$0`.urlString.substring(pathStartIndex, pathEndIndex);
               return var5;
            }
         }
      }
   }

   @JvmStatic
   fun `encodedQuery_delegate$lambda$0`(`this$0`: Url): java.lang.String {
      val queryStart: Int = StringsKt.indexOf$default(`this$0`.urlString, '?', 0, false, 6, null) + 1;
      if (queryStart == 0) {
         return "";
      } else {
         val queryEnd: Int = StringsKt.indexOf$default(`this$0`.urlString, '#', queryStart, false, 4, null);
         if (queryEnd == -1) {
            val var3: java.lang.String = `this$0`.urlString.substring(queryStart);
            return var3;
         } else {
            val var10000: java.lang.String = `this$0`.urlString.substring(queryStart, queryEnd);
            return var10000;
         }
      }
   }

   @JvmStatic
   fun `encodedPathAndQuery_delegate$lambda$0`(`this$0`: Url): java.lang.String {
      val pathStart: Int = StringsKt.indexOf$default(`this$0`.urlString, '/', `this$0`.protocol.getName().length() + 3, false, 4, null);
      if (pathStart == -1) {
         return "";
      } else {
         val queryEnd: Int = StringsKt.indexOf$default(`this$0`.urlString, '#', pathStart, false, 4, null);
         if (queryEnd == -1) {
            val var3: java.lang.String = `this$0`.urlString.substring(pathStart);
            return var3;
         } else {
            val var10000: java.lang.String = `this$0`.urlString.substring(pathStart, queryEnd);
            return var10000;
         }
      }
   }

   @JvmStatic
   fun `encodedUser_delegate$lambda$0`(`this$0`: Url): java.lang.String {
      if (`this$0`.user == null) {
         return null;
      } else if (`this$0`.user.length() == 0) {
         return "";
      } else {
         val usernameStart: Int = `this$0`.protocol.getName().length() + 3;
         val var4: java.lang.String = `this$0`.urlString
            .substring(usernameStart, StringsKt.indexOfAny$default(`this$0`.urlString, new char[]{':', '@'}, usernameStart, false, 4, null));
         return var4;
      }
   }

   @JvmStatic
   fun `encodedPassword_delegate$lambda$0`(`this$0`: Url): java.lang.String {
      if (`this$0`.password == null) {
         return null;
      } else if (`this$0`.password.length() == 0) {
         return "";
      } else {
         val var10000: java.lang.String = `this$0`.urlString
            .substring(
               StringsKt.indexOf$default(`this$0`.urlString, ':', `this$0`.protocol.getName().length() + 3, false, 4, null) + 1,
               StringsKt.indexOf$default(`this$0`.urlString, '@', 0, false, 6, null)
            );
         return var10000;
      }
   }

   @JvmStatic
   fun `encodedFragment_delegate$lambda$0`(`this$0`: Url): java.lang.String {
      val fragmentStart: Int = StringsKt.indexOf$default(`this$0`.urlString, '#', 0, false, 6, null) + 1;
      if (fragmentStart == 0) {
         return "";
      } else {
         val var10000: java.lang.String = `this$0`.urlString.substring(fragmentStart);
         return var10000;
      }
   }

   public companion object {
      public fun serializer(): KSerializer<Url> {
         return UrlSerializer.INSTANCE;
      }
   }
}
