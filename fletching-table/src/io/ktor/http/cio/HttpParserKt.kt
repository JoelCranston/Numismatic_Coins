@file:SourceDebugExtension(["SMAP\nHttpParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpParser.kt\nio/ktor/http/cio/HttpParserKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,322:1\n1088#2,2:323\n1#3:325\n*S KotlinDebug\n*F\n+ 1 HttpParser.kt\nio/ktor/http/cio/HttpParserKt\n*L\n162#1:323,2\n*E\n"])

package io.ktor.http.cio

import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.cio.HttpParserKt.parseHeaders.2
import io.ktor.http.cio.HttpParserKt.parseRequest.1
import io.ktor.http.cio.internals.AsciiCharTree
import io.ktor.http.cio.internals.CharArrayBuilder
import io.ktor.http.cio.internals.CharsKt
import io.ktor.http.cio.internals.MutableRange
import io.ktor.http.cio.internals.TokenizerKt
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.LineEndingMode
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension

private const val HTTP_LINE_LIMIT: Int = 8192
private const val HTTP_STATUS_CODE_MIN_RANGE: Int = 100
private const val HTTP_STATUS_CODE_MAX_RANGE: Int = 999
private final val hostForbiddenSymbols: Set<Char> = SetsKt.setOf(new Character[]{'/', '?', '#', '@'})
internal final val httpLineEndings: LineEndingMode
private final val versions: AsciiCharTree<String> = AsciiCharTree.Companion.build(CollectionsKt.listOf(new java.lang.String[]{"HTTP/1.0", "HTTP/1.1"}))
private int httpLineEndings = LineEndingMode.plus-1Ter-O4(LineEndingMode.Companion.getCRLF-f0jXZW8(), LineEndingMode.Companion.getLF-f0jXZW8());

public suspend fun parseRequest(input: ByteReadChannel): Request? {
   var `$continuation`: Continuation;
   label103: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label103;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   var builder: CharArrayBuilder;
   var t: HttpMethod;
   var uri: java.lang.CharSequence;
   var version: java.lang.CharSequence;
   var var10000: Any;
   label107: {
      val `$result`: Any = `$continuation`.result;
      val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var19: MutableRange;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            builder = new CharArrayBuilder(null, 1, null);
            var19 = new MutableRange(0, 0);

            try {
               ;
            } catch (var15: java.lang.Throwable) {
               builder.release();
               throw var15;
            }

            try {
               val var10001: Appendable = builder;
               val var10003: Int = httpLineEndings;
               `$continuation`.L$0 = input;
               `$continuation`.L$1 = builder;
               `$continuation`.L$2 = var19;
               `$continuation`.label = 1;
               var10000 = ByteReadChannelOperationsKt.readUTF8LineTo-RRvyBJ8(input, var10001, 8192, var10003, `$continuation`);
            } catch (var17: java.lang.Throwable) {
               builder.release();
               throw var17;
            }

            if (var10000 === var10) {
               return var10;
            }
            break;
         case 1:
            var19 = `$continuation`.L$2 as MutableRange;
            builder = `$continuation`.L$1 as CharArrayBuilder;
            input = `$continuation`.L$0 as ByteReadChannel;

            try {
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break;
            } catch (var13: java.lang.Throwable) {
               builder.release();
               throw var13;
            }
         case 2:
            version = `$continuation`.L$5 as java.lang.CharSequence;
            uri = `$continuation`.L$4 as java.lang.CharSequence;
            t = `$continuation`.L$3 as HttpMethod;
            var19 = `$continuation`.L$2 as MutableRange;
            builder = `$continuation`.L$1 as CharArrayBuilder;
            input = `$continuation`.L$0 as ByteReadChannel;

            try {
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break label107;
            } catch (var12: java.lang.Throwable) {
               builder.release();
               throw var12;
            }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while (true) {
         try {
            if (!var10000 as java.lang.Boolean) {
               return null;
            }

            var19.setEnd(builder.length());
            if (var19.getStart() != var19.getEnd()) {
               t = parseHttpMethod(builder, var19);
               uri = parseUri(builder, var19);
               version = parseVersion(builder, var19);
               TokenizerKt.skipSpaces(builder, var19);
               if (var19.getStart() != var19.getEnd()) {
                  throw new ParserException("Extra characters in request line: ${builder.subSequence(var19.getStart(), var19.getEnd()).toString()}");
               }

               if (uri.length() == 0) {
                  throw new ParserException("URI is not specified");
               }

               if (version.length() == 0) {
                  throw new ParserException("HTTP version is not specified");
               }

               `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(input);
               `$continuation`.L$1 = builder;
               `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var19);
               `$continuation`.L$3 = t;
               `$continuation`.L$4 = uri;
               `$continuation`.L$5 = version;
               `$continuation`.label = 2;
               var10000 = parseHeaders(input, builder, var19, `$continuation`);
               break;
            }
         } catch (var16: java.lang.Throwable) {
            builder.release();
            throw var16;
         }

         try {
            val var22: Appendable = builder;
            val var23: Int = httpLineEndings;
            `$continuation`.L$0 = input;
            `$continuation`.L$1 = builder;
            `$continuation`.L$2 = var19;
            `$continuation`.label = 1;
            var10000 = ByteReadChannelOperationsKt.readUTF8LineTo-RRvyBJ8(input, var22, 8192, var23, `$continuation`);
         } catch (var14: java.lang.Throwable) {
            builder.release();
            throw var14;
         }

         if (var10000 === var10) {
            return var10;
         }
      }

      if (var10000 === var10) {
         return var10;
      }
   }

   label52:
   try {
      return if (var10000 as HttpHeadersMap == null) null else new Request(t, uri, version, var10000 as HttpHeadersMap, builder);
   } catch (var11: java.lang.Throwable) {
      builder.release();
      throw var11;
   }
}

public suspend fun parseResponse(input: ByteReadChannel): Response? {
   var `$continuation`: Continuation;
   label65: {
      if (`$completion` is io.ktor.http.cio.HttpParserKt.parseResponse.1) {
         `$continuation` = `$completion` as io.ktor.http.cio.HttpParserKt.parseResponse.1;
         if (((`$completion` as io.ktor.http.cio.HttpParserKt.parseResponse.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label65;
         }
      }

      `$continuation` = new io.ktor.http.cio.HttpParserKt.parseResponse.1(`$completion`);
   }

   var builder: CharArrayBuilder;
   var version: java.lang.CharSequence;
   var t: Int;
   var statusText: java.lang.CharSequence;
   var var10000: Any;
   label68: {
      val `$result`: Any = `$continuation`.result;
      val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var17: MutableRange;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            builder = new CharArrayBuilder(null, 1, null);
            var17 = new MutableRange(0, 0);

            try {
               val var10001: Appendable = builder;
               val var10003: Int = httpLineEndings;
               `$continuation`.L$0 = input;
               `$continuation`.L$1 = builder;
               `$continuation`.L$2 = var17;
               `$continuation`.label = 1;
               var10000 = ByteReadChannelOperationsKt.readUTF8LineTo-RRvyBJ8(input, var10001, 8192, var10003, `$continuation`);
            } catch (var14: java.lang.Throwable) {
               builder.release();
               throw var14;
            }

            if (var10000 === var10) {
               return var10;
            }
            break;
         case 1:
            var17 = `$continuation`.L$2 as MutableRange;
            builder = `$continuation`.L$1 as CharArrayBuilder;
            input = `$continuation`.L$0 as ByteReadChannel;

            try {
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break;
            } catch (var13: java.lang.Throwable) {
               builder.release();
               throw var13;
            }
         case 2:
            t = `$continuation`.I$0;
            statusText = `$continuation`.L$4 as java.lang.CharSequence;
            version = `$continuation`.L$3 as java.lang.CharSequence;
            var17 = `$continuation`.L$2 as MutableRange;
            builder = `$continuation`.L$1 as CharArrayBuilder;
            input = `$continuation`.L$0 as ByteReadChannel;

            try {
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break label68;
            } catch (var12: java.lang.Throwable) {
               builder.release();
               throw var12;
            }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      try {
         if (!var10000 as java.lang.Boolean) {
            return null;
         }

         var17.setEnd(builder.length());
         version = parseVersion(builder, var17);
         t = parseStatusCode(builder, var17);
         TokenizerKt.skipSpaces(builder, var17);
         statusText = builder.subSequence(var17.getStart(), var17.getEnd());
         var17.setStart(var17.getEnd());
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(input);
         `$continuation`.L$1 = builder;
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
         `$continuation`.L$3 = version;
         `$continuation`.L$4 = statusText;
         `$continuation`.I$0 = t;
         `$continuation`.label = 2;
         var10000 = parseHeaders(input, builder, var17, `$continuation`);
      } catch (var15: java.lang.Throwable) {
         builder.release();
         throw var15;
      }

      if (var10000 === var10) {
         return var10;
      }
   }

   try {
      var10000 = var10000 as HttpHeadersMap;
      if (var10000 as HttpHeadersMap == null) {
         var10000 = new HttpHeadersMap(builder);
      }

      return new Response(version, t, statusText, (HttpHeadersMap)var10000, builder);
   } catch (var11: java.lang.Throwable) {
      builder.release();
      throw var11;
   }
}

public suspend fun parseHeaders(input: ByteReadChannel): HttpHeadersMap {
   var `$continuation`: Continuation;
   label24: {
      if (`$completion` is io.ktor.http.cio.HttpParserKt.parseHeaders.1) {
         `$continuation` = `$completion` as io.ktor.http.cio.HttpParserKt.parseHeaders.1;
         if (((`$completion` as io.ktor.http.cio.HttpParserKt.parseHeaders.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label24;
         }
      }

      `$continuation` = new io.ktor.http.cio.HttpParserKt.parseHeaders.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var builder: CharArrayBuilder;
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         builder = new CharArrayBuilder(null, 1, null);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(input);
         `$continuation`.L$1 = builder;
         `$continuation`.label = 1;
         var10000 = parseHeaders$default(input, builder, null, `$continuation`, 4, null);
         if (var10000 === var5) {
            return var5;
         }
         break;
      case 1:
         builder = `$continuation`.L$1 as CharArrayBuilder;
         input = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   var10000 = var10000 as HttpHeadersMap;
   if (var10000 as HttpHeadersMap == null) {
      var10000 = new HttpHeadersMap(builder);
   }

   return var10000;
}

internal suspend fun parseHeaders(input: ByteReadChannel, builder: CharArrayBuilder, range: MutableRange = ...): HttpHeadersMap? {
   var `$continuation`: Continuation;
   label68: {
      if (`$completion` is 2) {
         `$continuation` = `$completion` as 2;
         if (((`$completion` as 2).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label68;
         }
      }

      `$continuation` = new 2(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var13: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var headers: HttpHeadersMap;
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         headers = new HttpHeadersMap(builder);

         try {
            ;
         } catch (var17: java.lang.Throwable) {
            headers.release();
            throw var17;
         }

         try {
            val var10001: Appendable = builder;
            val var10003: Int = httpLineEndings;
            `$continuation`.L$0 = input;
            `$continuation`.L$1 = builder;
            `$continuation`.L$2 = range;
            `$continuation`.L$3 = headers;
            `$continuation`.label = 1;
            var10000 = ByteReadChannelOperationsKt.readUTF8LineTo-RRvyBJ8(input, var10001, 8192, var10003, `$continuation`);
         } catch (var15: java.lang.Throwable) {
            headers.release();
            throw var15;
         }

         if (var10000 === var13) {
            return var13;
         }
         break;
      case 1:
         headers = `$continuation`.L$3 as HttpHeadersMap;
         range = `$continuation`.L$2 as MutableRange;
         builder = `$continuation`.L$1 as CharArrayBuilder;
         input = `$continuation`.L$0 as ByteReadChannel;

         try {
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         } catch (var14: java.lang.Throwable) {
            headers.release();
            throw var14;
         }
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   do {
      try {
         if (!var10000 as java.lang.Boolean) {
            headers.release();
            return null;
         }

         range.setEnd(builder.length());
         val host: Int = range.getEnd() - range.getStart();
         if (host == 0) {
            val var19: java.lang.CharSequence = headers.get(HttpHeaders.INSTANCE.getHost());
            if (var19 != null) {
               validateHostHeader(var19);
            }

            return headers;
         }

         if (host >= 8192) {
            throw new IllegalStateException("Header line length limit exceeded".toString());
         }

         val t: Int = range.getStart();
         val nameEnd: Int = parseHeaderName(builder, range);
         val headerEnd: Int = range.getEnd();
         parseHeaderValue(builder, range);
         val valueStart: Int = range.getStart();
         val valueEnd: Int = range.getEnd();
         range.setStart(headerEnd);
         headers.put(t, nameEnd, valueStart, valueEnd);
      } catch (var16: java.lang.Throwable) {
         headers.release();
         throw var16;
      }

      try {
         val var20: Appendable = builder;
         val var21: Int = httpLineEndings;
         `$continuation`.L$0 = input;
         `$continuation`.L$1 = builder;
         `$continuation`.L$2 = range;
         `$continuation`.L$3 = headers;
         `$continuation`.label = 1;
         var10000 = ByteReadChannelOperationsKt.readUTF8LineTo-RRvyBJ8(input, var20, 8192, var21, `$continuation`);
      } catch (var18: java.lang.Throwable) {
         headers.release();
         throw var18;
      }
   } while (var10000 != var13);

   return var13;
}

@JvmSynthetic
fun `parseHeaders$default`(var0: ByteReadChannel, var1: CharArrayBuilder, var2: MutableRange, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 4) != 0) {
      var2 = new MutableRange(0, 0);
   }

   return parseHeaders(var0, var1, var2, var3);
}

private fun validateHostHeader(host: CharSequence) {
   if (StringsKt.endsWith$default(host, ":", false, 2, null)) {
      throw new ParserException("Host header with ':' should contains port: $host");
   } else {
      val `$this$any$iv`: java.lang.CharSequence = host;
      var var3: Int = 0;

      var var10000: Boolean;
      while (true) {
         if (var3 >= `$this$any$iv`.length()) {
            var10000 = false;
            break;
         }

         if (hostForbiddenSymbols.contains(`$this$any$iv`.charAt(var3))) {
            var10000 = true;
            break;
         }

         var3++;
      }

      if (var10000) {
         throw new ParserException("Host cannot contain any of the following symbols: ${hostForbiddenSymbols}");
      }
   }
}

private fun parseHttpMethod(text: CharSequence, range: MutableRange): HttpMethod {
   TokenizerKt.skipSpaces(text, range);
   val exact: HttpMethod = CollectionsKt.singleOrNull(
      AsciiCharTree.search$default(
         CharsKt.getDefaultHttpMethods(), text, range.getStart(), range.getEnd(), false, HttpParserKt::parseHttpMethod$lambda$0, 8, null
      )
   );
   if (exact != null) {
      range.setStart(range.getStart() + exact.getValue().length());
      return exact;
   } else {
      return parseHttpMethodFull(text, range);
   }
}

private fun parseHttpMethodFull(text: CharSequence, range: MutableRange): HttpMethod {
   return new HttpMethod(TokenizerKt.nextToken(text, range).toString());
}

private fun parseUri(text: CharSequence, range: MutableRange): CharSequence {
   TokenizerKt.skipSpaces(text, range);
   val start: Int = range.getStart();
   val spaceOrEnd: Int = TokenizerKt.findSpaceOrEnd(text, range);
   val length: Int = spaceOrEnd - start;
   if (spaceOrEnd - start <= 0) {
      return "";
   } else if (length == 1 && text.charAt(start) == '/') {
      range.setStart(spaceOrEnd);
      return "/";
   } else {
      val s: java.lang.CharSequence = text.subSequence(start, spaceOrEnd);
      range.setStart(spaceOrEnd);
      return s;
   }
}

private fun parseVersion(text: CharSequence, range: MutableRange): CharSequence {
   TokenizerKt.skipSpaces(text, range);
   if (range.getStart() >= range.getEnd()) {
      throw new IllegalStateException(("Failed to parse version: $text").toString());
   } else {
      val exact: java.lang.String = CollectionsKt.singleOrNull(
         AsciiCharTree.search$default(versions, text, range.getStart(), range.getEnd(), false, HttpParserKt::parseVersion$lambda$1, 8, null)
      );
      if (exact != null) {
         range.setStart(range.getStart() + exact.length());
         return exact;
      } else {
         unsupportedHttpVersion(TokenizerKt.nextToken(text, range));
         throw new KotlinNothingValueException();
      }
   }
}

private fun parseStatusCode(text: CharSequence, range: MutableRange): Int {
   TokenizerKt.skipSpaces(text, range);
   var status: Int = 0;
   var newStart: Int = range.getEnd();
   var idx: Int = range.getStart();

   for (int var5 = range.getEnd(); idx < var5; idx++) {
      val ch: Char = text.charAt(idx);
      if (ch == ' ') {
         if (statusOutOfRange(status)) {
            throw new ParserException("Status-code must be 3-digit. Status received: $status${46}");
         }

         newStart = idx;
         break;
      }

      if ('0' > ch || ch >= ':') {
         throw new NumberFormatException(
            "Illegal digit $ch in status code ${text.subSequence(range.getStart(), TokenizerKt.findSpaceOrEnd(text, range)).toString()}"
         );
      }

      status = status * 10 + (ch - '0');
   }

   range.setStart(newStart);
   return status;
}

private fun statusOutOfRange(code: Int): Boolean {
   return code < 100 || code > 999;
}

internal fun parseHeaderName(text: CharArrayBuilder, range: MutableRange): Int {
   var index: Int = range.getStart();

   for (int end = range.getEnd(); index < end; index++) {
      val ch: Char = text.charAt(index);
      if (ch == ':' && index != range.getStart()) {
         range.setStart(index + 1);
         return index;
      }

      if (isDelimiter(ch)) {
         parseHeaderNameFailed(text, index, range.getStart(), ch);
         throw new KotlinNothingValueException();
      }
   }

   noColonFound(text, range);
   throw new KotlinNothingValueException();
}

private fun parseHeaderNameFailed(text: CharArrayBuilder, index: Int, start: Int, ch: Char): Nothing {
   if (ch == ':') {
      throw new ParserException("Empty header names are not allowed as per RFC7230.");
   } else if (index == start) {
      throw new ParserException("Multiline headers via line folding is not supported since it is deprecated as per RFC7230.");
   } else {
      characterIsNotAllowed(text, ch);
      throw new KotlinNothingValueException();
   }
}

internal fun parseHeaderValue(text: CharArrayBuilder, range: MutableRange) {
   val end: Int = range.getEnd();
   var index: Int = TokenizerKt.skipSpacesAndHorizontalTabs(text, range.getStart(), end);
   if (index >= end) {
      range.setStart(end);
   } else {
      val valueStart: Int = index;

      var valueLastIndex: Int;
      for (valueLastIndex = index; index < end; index++) {
         val ch: Char = text.charAt(index);
         switch (ch) {
            case '\n':
            case '\r':
               characterIsNotAllowed(text, ch);
               throw new KotlinNothingValueException();
            default:
               valueLastIndex = index;
               break;
            case '\t':
            case ' ':
         }
      }

      range.setStart(valueStart);
      range.setEnd(valueLastIndex + 1);
   }
}

private fun noColonFound(text: CharSequence, range: MutableRange): Nothing {
   throw new ParserException("No colon in HTTP header in ${text.subSequence(range.getStart(), range.getEnd()).toString()} in builder: \n$text");
}

private fun characterIsNotAllowed(text: CharSequence, ch: Char): Nothing {
   throw new ParserException("Character with code ${ch and 255} is not allowed in header names, \n$text");
}

private fun isDelimiter(ch: Char): Boolean {
   return Intrinsics.compare(ch, 32) <= 0 || StringsKt.contains$default("\"(),/:;<=>?@[\\]{}", ch, false, 2, null);
}

private fun unsupportedHttpVersion(result: CharSequence): Nothing {
   throw new ParserException("Unsupported HTTP version: $result");
}

fun `parseHttpMethod$lambda$0`(ch: Char, var1: Int): Boolean {
   return ch == ' ';
}

fun `parseVersion$lambda$1`(ch: Char, var1: Int): Boolean {
   return ch == ' ';
}
