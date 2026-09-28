@file:SourceDebugExtension(["SMAP\nFileContentType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileContentType.kt\nio/ktor/http/FileContentTypeKt\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,114:1\n996#2:115\n1025#2,3:116\n1028#2,3:126\n382#3,7:119\n463#3:129\n413#3:130\n1252#4,2:131\n1563#4:133\n1634#4,3:134\n1255#4:137\n1#5:138\n*S KotlinDebug\n*F\n+ 1 FileContentType.kt\nio/ktor/http/FileContentTypeKt\n*L\n106#1:115\n106#1:116,3\n106#1:126,3\n106#1:119,7\n107#1:129\n107#1:130\n107#1:131,2\n107#1:133\n107#1:134,3\n107#1:137\n*E\n"])

package io.ktor.http

import io.ktor.http.ContentType.Companion
import io.ktor.util.CharsetKt
import io.ktor.util.TextKt
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension

private final val contentTypesByExtensions: Map<String, List<ContentType>> by LazyKt.lazy(FileContentTypeKt::contentTypesByExtensions_delegate$lambda$0)
   private final get() {
      return contentTypesByExtensions$delegate.getValue() as MutableMap<java.lang.String, MutableList<ContentType>>;
   }


private final val extensionsByContentType: Map<ContentType, List<String>> by LazyKt.lazy(FileContentTypeKt::extensionsByContentType_delegate$lambda$0)
   private final get() {
      return extensionsByContentType$delegate.getValue() as MutableMap<ContentType, MutableList<java.lang.String>>;
   }


public fun Companion.defaultForFileExtension(extension: String): ContentType {
   return selectDefault(fromFileExtension(ContentType.Companion, extension));
}

public fun Companion.defaultForFilePath(path: String): ContentType {
   return selectDefault(fromFilePath(ContentType.Companion, path));
}

public fun Companion.fromFilePath(path: String): List<ContentType> {
   val index: Int = StringsKt.indexOf$default(
      path, '.', StringsKt.lastIndexOfAny$default(path, CharsetKt.toCharArray("/\\"), 0, false, 6, null) + 1, false, 4, null
   );
   if (index == -1) {
      return CollectionsKt.emptyList();
   } else {
      val var10001: java.lang.String = path.substring(index + 1);
      return fromFileExtension(`$this$fromFilePath`, var10001);
   }
}

public fun Companion.fromFileExtension(ext: String): List<ContentType> {
   for (java.lang.String current = TextKt.toLowerCasePreservingASCIIRules(StringsKt.removePrefix(ext, "."));
      current.length() > 0;
      current = StringsKt.substringAfter(current, ".", "")
   ) {
      val type: java.util.List = getContentTypesByExtensions().get(current);
      if (type != null) {
         return type;
      }
   }

   return CollectionsKt.emptyList();
}

public fun ContentType.fileExtensions(): List<String> {
   var var10000: java.util.List = getExtensionsByContentType().get(`$this$fileExtensions`);
   if (var10000 == null) {
      var10000 = getExtensionsByContentType().get(`$this$fileExtensions`.withoutParameters());
      if (var10000 == null) {
         var10000 = CollectionsKt.emptyList();
      }
   }

   return var10000;
}

internal fun List<ContentType>.selectDefault(): ContentType {
   var var10000: ContentType = CollectionsKt.firstOrNull(`$this$selectDefault`);
   if (var10000 == null) {
      var10000 = ContentType.Application.INSTANCE.getOctetStream();
   }

   return if (var10000.match(ContentType.Text.INSTANCE.getAny()))
      withCharsetUTF8IfNeeded(var10000)
      else
      (
         if (var10000.match(ContentType.Image.INSTANCE.getSVG()))
            withCharsetUTF8IfNeeded(var10000)
            else
            (if (matchApplicationTypeWithCharset(var10000)) withCharsetUTF8IfNeeded(var10000) else var10000)
      );
}

private fun ContentType.matchApplicationTypeWithCharset(): Boolean {
   if (!`$this$matchApplicationTypeWithCharset`.match(ContentType.Application.INSTANCE.getAny())) {
      return false;
   } else {
      return `$this$matchApplicationTypeWithCharset`.match(ContentType.Application.INSTANCE.getAtom())
         || `$this$matchApplicationTypeWithCharset`.match(ContentType.Application.INSTANCE.getJavaScript())
         || `$this$matchApplicationTypeWithCharset`.match(ContentType.Application.INSTANCE.getRss())
         || `$this$matchApplicationTypeWithCharset`.match(ContentType.Application.INSTANCE.getXml())
         || `$this$matchApplicationTypeWithCharset`.match(ContentType.Application.INSTANCE.getXml_Dtd());
   }
}

private fun ContentType.withCharsetUTF8IfNeeded(): ContentType {
   return if (ContentTypesKt.charset(`$this$withCharsetUTF8IfNeeded`) != null)
      `$this$withCharsetUTF8IfNeeded`
      else
      ContentTypesKt.withCharset(`$this$withCharsetUTF8IfNeeded`, Charsets.UTF_8);
}

internal fun <A, B> Sequence<Pair<Any, Any>>.groupByPairs(): Map<Any, List<Any>> {
   val `destination$iv$iv`: java.util.Map = new LinkedHashMap();

   for (Object element$iv$iv : $this$groupByPairs) {
      val `element$iv$iv$iv`: Any = (`destination$iv$iv$iv` as Pair).getFirst();
      val e: Any = `destination$iv$iv`.get(`element$iv$iv$iv`);
      val var10000: Any;
      if (e == null) {
         val var40: Any = new ArrayList();
         `destination$iv$iv`.put(`element$iv$iv$iv`, var40);
         var10000 = var40;
      } else {
         var10000 = e;
      }

      (var10000 as java.util.List).add(`destination$iv$iv$iv`);
   }

   val `destination$iv$ivx`: java.util.Map = new LinkedHashMap(MapsKt.mapCapacity(`destination$iv$iv`.size()));
   val var32: java.lang.Iterable = `destination$iv$iv`.entrySet();
   val var33: java.util.Map = `destination$iv$ivx`;

   for (Object element$iv$iv$iv : var32) {
      val var10001: Any = (var37 as Entry).getKey();
      val `$this$map$iv`: java.lang.Iterable = (var37 as Entry).getValue() as java.lang.Iterable;
      val `destination$iv$ivx`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$map$iv`, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$ivx`.add((`item$iv$iv` as Pair).getSecond());
      }

      var33.put(var10001, `destination$iv$ivx` as java.util.List);
   }

   return var33;
}

internal fun String.toContentType(): ContentType {
   try {
      return ContentType.Companion.parse(`$this$toContentType`);
   } catch (var3: java.lang.Throwable) {
      throw new IllegalArgumentException("Failed to parse $`$this$toContentType`", var3);
   }
}

fun `contentTypesByExtensions_delegate$lambda$0`(): java.util.Map {
   val var0: java.util.Map = io.ktor.util.CollectionsKt.caseInsensitiveMap();
   var0.putAll(groupByPairs(CollectionsKt.asSequence(MimesKt.getMimes())));
   return var0;
}

fun `extensionsByContentType_delegate$lambda$0`(): java.util.Map {
   return groupByPairs(SequencesKt.map(CollectionsKt.asSequence(MimesKt.getMimes()), FileContentTypeKt::extensionsByContentType_delegate$lambda$0$0));
}

fun `extensionsByContentType_delegate$lambda$0$0`(var0: Pair): Pair {
   return TuplesKt.to(var0.component2() as ContentType, var0.component1() as java.lang.String);
}
