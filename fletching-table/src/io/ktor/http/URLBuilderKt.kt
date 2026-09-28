@file:SourceDebugExtension(["SMAP\nURLBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 URLBuilder.kt\nio/ktor/http/URLBuilderKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,372:1\n1374#2:373\n1460#2,5:374\n1563#2:379\n1634#2,3:380\n11561#3:383\n11896#3,3:384\n*S KotlinDebug\n*F\n+ 1 URLBuilder.kt\nio/ktor/http/URLBuilderKt\n*L\n244#1:373\n244#1:374,5\n245#1:379\n245#1:380,3\n273#1:383\n273#1:384,3\n*E\n"])

package io.ktor.http

import java.util.ArrayList
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension

public const val DEFAULT_PORT: Int = 0

internal final val encodedUserAndPassword: String
   internal final get() {
      val var1: StringBuilder = new StringBuilder();
      URLUtilsKt.appendUserAndPassword(var1, `$this$encodedUserAndPassword`.getEncodedUser(), `$this$encodedUserAndPassword`.getEncodedPassword());
      return var1.toString();
   }


public final val authority: String
   public final get() {
      val var1: StringBuilder = new StringBuilder();
      var1.append(getEncodedUserAndPassword(`$this$authority`));
      var1.append(`$this$authority`.getHost());
      if (`$this$authority`.getPort() != 0 && `$this$authority`.getPort() != `$this$authority`.getProtocol().getDefaultPort()) {
         var1.append(":");
         var1.append(java.lang.String.valueOf(`$this$authority`.getPort()));
      }

      return var1.toString();
   }


public final var encodedPath: String
   public final get() {
      return joinPath(`$this$encodedPath`.getEncodedPathSegments());
   }

   public final set(value) {
      `$this$encodedPath`.setEncodedPathSegments(
         if (StringsKt.isBlank(value))
            CollectionsKt.emptyList()
            else
            (if (value == "/") URLParserKt.getROOT_PATH() else CollectionsKt.toMutableList(StringsKt.split$default(value, new char[]{'/'}, false, 0, 6, null)))
      );
   }


private fun <A : Appendable> URLBuilder.appendTo(out: Any): Any {
   out.append(`$this$appendTo`.getProtocol().getName());
   val var2: java.lang.String = `$this$appendTo`.getProtocol().getName();
   switch (var2.hashCode()) {
      case -1081572750:
         if (var2.equals("mailto")) {
            appendMailto(out, getEncodedUserAndPassword(`$this$appendTo`), `$this$appendTo`.getHost());
            return (A)out;
         }
         break;
      case 114715:
         if (var2.equals("tel")) {
            appendPayload(out, `$this$appendTo`.getHost());
            return (A)out;
         }
         break;
      case 3076010:
         if (var2.equals("data")) {
            appendPayload(out, `$this$appendTo`.getHost());
            return (A)out;
         }
         break;
      case 3143036:
         if (var2.equals("file")) {
            appendFile(out, `$this$appendTo`.getHost(), getEncodedPath(`$this$appendTo`));
            return (A)out;
         }
         break;
      case 92611469:
         if (var2.equals("about")) {
            appendPayload(out, `$this$appendTo`.getHost());
            return (A)out;
         }
      default:
   }

   out.append("://");
   out.append(getAuthority(`$this$appendTo`));
   URLUtilsKt.appendUrlFullPath(out, getEncodedPath(`$this$appendTo`), `$this$appendTo`.getEncodedParameters(), `$this$appendTo`.getTrailingQuery());
   if (`$this$appendTo`.getEncodedFragment().length() > 0) {
      out.append('#');
      out.append(`$this$appendTo`.getEncodedFragment());
   }

   return (A)out;
}

private fun Appendable.appendMailto(encodedUser: String, host: String) {
   `$this$appendMailto`.append(":");
   `$this$appendMailto`.append(encodedUser);
   `$this$appendMailto`.append(host);
}

private fun Appendable.appendFile(host: String, encodedPath: String) {
   `$this$appendFile`.append("://");
   `$this$appendFile`.append(host);
   if (!StringsKt.startsWith$default(encodedPath, '/', false, 2, null)) {
      `$this$appendFile`.append('/');
   }

   `$this$appendFile`.append(encodedPath);
}

private fun Appendable.appendPayload(host: String) {
   `$this$appendPayload`.append(":");
   `$this$appendPayload`.append(host);
}

public fun URLBuilder.clone(): URLBuilder {
   return URLUtilsKt.takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), `$this$clone`);
}

public fun URLBuilder.appendPathSegments(segments: List<String>, encodeSlash: Boolean = false): URLBuilder {
   val var24: java.util.List;
   if (!encodeSlash) {
      val encodedSegments: java.lang.Iterable = segments;
      val `$this$mapTo$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$flatMap$iv) {
         CollectionsKt.addAll(`$this$mapTo$iv$iv`, StringsKt.split$default(`element$iv$iv` as java.lang.String, new char[]{'/'}, false, 0, 6, null));
      }

      var24 = `$this$mapTo$iv$iv` as java.util.List;
   } else {
      var24 = segments;
   }

   val var16: java.lang.Iterable = var24;
   val var17: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var24, 10));

   for (Object item$iv$iv : $this$map$iv) {
      var17.add(CodecsKt.encodeURLPathPart(var21 as java.lang.String));
   }

   appendEncodedPathSegments(`$this$appendPathSegments`, var17 as MutableList<java.lang.String>);
   return `$this$appendPathSegments`;
}

@JvmSynthetic
fun `appendPathSegments$default`(var0: URLBuilder, var1: java.util.List, var2: Boolean, var3: Int, var4: Any): URLBuilder {
   if ((var3 and 2) != 0) {
      var2 = false;
   }

   return appendPathSegments(var0, var1, var2);
}

public fun URLBuilder.appendPathSegments(vararg components: String, encodeSlash: Boolean = false): URLBuilder {
   return appendPathSegments(`$this$appendPathSegments`, ArraysKt.toList(components), encodeSlash);
}

@JvmSynthetic
fun `appendPathSegments$default`(var0: URLBuilder, var1: Array<java.lang.String>, var2: Boolean, var3: Int, var4: Any): URLBuilder {
   if ((var3 and 2) != 0) {
      var2 = false;
   }

   return appendPathSegments(var0, var1, var2);
}

public fun URLBuilder.path(vararg path: String) {
   val `destination$iv$iv`: java.util.Collection = new ArrayList(path.length);

   for (Object item$iv$iv : path) {
      `destination$iv$iv`.add(CodecsKt.encodeURLPath$default((java.lang.String)`item$iv$iv`, false, false, 3, null));
   }

   `$this$path`.setEncodedPathSegments(`destination$iv$iv` as MutableList<java.lang.String>);
}

public fun URLBuilder.appendEncodedPathSegments(segments: List<String>): URLBuilder {
   val endsWithSlash: Boolean = `$this$appendEncodedPathSegments`.getEncodedPathSegments().size() > 1
      && CollectionsKt.last(`$this$appendEncodedPathSegments`.getEncodedPathSegments()).length() == 0
      && !segments.isEmpty();
   val startWithSlash: Boolean = segments.size() > 1
      && CollectionsKt.<java.lang.CharSequence>first(segments).length() == 0
      && !`$this$appendEncodedPathSegments`.getEncodedPathSegments().isEmpty();
   `$this$appendEncodedPathSegments`.setEncodedPathSegments(
      if (endsWithSlash && startWithSlash)
         CollectionsKt.plus(CollectionsKt.dropLast(`$this$appendEncodedPathSegments`.getEncodedPathSegments(), 1), CollectionsKt.drop(segments, 1))
         else
         (
            if (endsWithSlash)
               CollectionsKt.plus(CollectionsKt.dropLast(`$this$appendEncodedPathSegments`.getEncodedPathSegments(), 1), segments)
               else
               (
                  if (startWithSlash)
                     CollectionsKt.plus(`$this$appendEncodedPathSegments`.getEncodedPathSegments(), CollectionsKt.drop(segments, 1))
                     else
                     CollectionsKt.plus(`$this$appendEncodedPathSegments`.getEncodedPathSegments(), segments)
               )
         )
   );
   return `$this$appendEncodedPathSegments`;
}

public fun URLBuilder.appendEncodedPathSegments(vararg components: String): URLBuilder {
   return appendEncodedPathSegments(`$this$appendEncodedPathSegments`, ArraysKt.toList(components));
}

private fun List<String>.joinPath(): String {
   if (`$this$joinPath`.isEmpty()) {
      return "";
   } else if (`$this$joinPath`.size() == 1) {
      return if (CollectionsKt.<java.lang.CharSequence>first(`$this$joinPath`).length() == 0) "/" else CollectionsKt.first(`$this$joinPath`);
   } else {
      return CollectionsKt.joinToString$default(`$this$joinPath`, "/", null, null, 0, null, null, 62, null);
   }
}

public fun URLBuilder.set(
   scheme: String? = null,
   host: String? = null,
   port: Int? = null,
   path: String? = null,
   block: (URLBuilder) -> Unit = URLBuilderKt::set$lambda$0
) {
   if (scheme != null) {
      `$this$set`.setProtocol(URLProtocol.Companion.createOrDefault(scheme));
   }

   if (host != null) {
      `$this$set`.setHost(host);
   }

   if (port != null) {
      `$this$set`.setPort(port);
   }

   if (path != null) {
      setEncodedPath(`$this$set`, path);
   }

   block.invoke(`$this$set`);
}

@JvmSynthetic
fun `set$default`(var0: URLBuilder, var1: java.lang.String, var2: java.lang.String, var3: Int, var4: java.lang.String, var5: Function1, var6: Int, var7: Any) {
   if ((var6 and 1) != 0) {
      var1 = null;
   }

   if ((var6 and 2) != 0) {
      var2 = null;
   }

   if ((var6 and 4) != 0) {
      var3 = null;
   }

   if ((var6 and 8) != 0) {
      var4 = null;
   }

   if ((var6 and 16) != 0) {
      var5 = URLBuilderKt::set$lambda$0;
   }

   set(var0, var1, var2, var3, var4, var5);
}

@Deprecated(message = "Please use appendPathSegments method", replaceWith = @ReplaceWith(expression = "this.appendPathSegments(components", imports = []), level = DeprecationLevel.ERROR)
public fun URLBuilder.pathComponents(vararg components: String): URLBuilder {
   return appendPathSegments$default(`$this$pathComponents`, ArraysKt.toList(components), false, 2, null);
}

@Deprecated(message = "Please use appendPathSegments method", replaceWith = @ReplaceWith(expression = "this.appendPathSegments(components", imports = []), level = DeprecationLevel.ERROR)
public fun URLBuilder.pathComponents(components: List<String>): URLBuilder {
   return appendPathSegments$default(`$this$pathComponents`, components, false, 2, null);
}

fun `set$lambda$0`(var0: URLBuilder): Unit {
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$appendTo`(`$receiver`: URLBuilder, out: Appendable): Appendable {
   return appendTo(`$receiver`, out);
}
