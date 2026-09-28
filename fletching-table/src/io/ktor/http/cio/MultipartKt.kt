package io.ktor.http.cio

import io.ktor.http.ContentType
import io.ktor.http.cio.MultipartKt.parsePartHeadersImpl.1
import io.ktor.http.cio.internals.CharArrayBuilder
import io.ktor.http.cio.internals.CharsKt
import io.ktor.http.cio.internals.UnsupportedMediaTypeExceptionCIO
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.core.StringsKt
import java.io.EOFException
import java.io.IOException
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.Ref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProduceKt
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.io.bytestring.ByteString
import kotlinx.io.bytestring.ByteStringKt

private final val CrLf: ByteString = new ByteString(StringsKt.toByteArray$default("\r\n", null, 1, null), 0, 0, 6, null)
private const val PrefixChar: Byte = 45
private final val PrefixString: ByteString = ByteStringKt.ByteString(45, 45)

private suspend fun parsePreambleImpl(boundary: ByteString, input: ByteReadChannel, output: ByteWriteChannel, limit: Long = ...): Long {
   return ByteReadChannelOperationsKt.readUntil(input, boundary, output, limit, true, `$completion`);
}

@JvmSynthetic
fun `parsePreambleImpl$default`(var0: ByteString, var1: ByteReadChannel, var2: ByteWriteChannel, var3: Long, var5: Continuation, var6: Int, var7: Any): Any {
   if ((var6 and 8) != 0) {
      var3 = java.lang.Long.MAX_VALUE;
   }

   return parsePreambleImpl(var0, var1, var2, var3, var5);
}

private suspend fun parsePartHeadersImpl(input: ByteReadChannel): HttpHeadersMap {
   var `$continuation`: Continuation;
   label39: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label39;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var builder: CharArrayBuilder;
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         builder = new CharArrayBuilder(null, 1, null);

         try {
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(input);
            `$continuation`.L$1 = builder;
            `$continuation`.label = 1;
            var10000 = HttpParserKt.parseHeaders$default(input, builder, null, `$continuation`, 4, null);
         } catch (var9: java.lang.Throwable) {
            builder.release();
            throw var9;
         }

         if (var10000 === var6) {
            return var6;
         }
         break;
      case 1:
         builder = `$continuation`.L$1 as CharArrayBuilder;
         input = `$continuation`.L$0 as ByteReadChannel;

         try {
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         } catch (var8: java.lang.Throwable) {
            builder.release();
            throw var8;
         }
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   try {
      var10000 = var10000 as HttpHeadersMap;
      if (var10000 as HttpHeadersMap == null) {
         throw new EOFException("Failed to parse multipart headers: unexpected end of stream");
      } else {
         return var10000;
      }
   } catch (var7: java.lang.Throwable) {
      builder.release();
      throw var7;
   }
}

private suspend fun parsePartBodyImpl(boundaryPrefixed: ByteString, input: ByteReadChannel, output: ByteWriteChannel, headers: HttpHeadersMap, limit: Long): Long {
   var `$continuation`: Continuation;
   label69: {
      if (`$completion` is io.ktor.http.cio.MultipartKt.parsePartBodyImpl.1) {
         `$continuation` = `$completion` as io.ktor.http.cio.MultipartKt.parsePartBodyImpl.1;
         if (((`$completion` as io.ktor.http.cio.MultipartKt.parsePartBodyImpl.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label69;
         }
      }

      `$continuation` = new io.ktor.http.cio.MultipartKt.parsePartBodyImpl.1(`$completion`);
   }

   var var17: Any;
   var var28: Long;
   label61: {
      label60: {
         var var12: Long;
         label59: {
            val `$result`: Any = `$continuation`.result;
            var17 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            var var23: java.lang.Long;
            var var25: Any;
            switch ($continuation.label) {
               case 0:
                  ResultKt.throwOnFailure(`$result`);
                  val var27: java.lang.CharSequence = headers.get("Content-Length");
                  var23 = if (var27 != null) Boxing.boxLong(CharsKt.parseDecLong(var27)) else null;
                  if (var23 == null) {
                     `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(boundaryPrefixed);
                     `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(input);
                     `$continuation`.L$2 = output;
                     `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(headers);
                     `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var23);
                     `$continuation`.J$0 = limit;
                     `$continuation`.label = 1;
                     var26 = ByteReadChannelOperationsKt.readUntil(input, boundaryPrefixed, output, limit, true, `$continuation`);
                     if (var26 === var17) {
                        return var17;
                     }
                     break label60;
                  }

                  val var10: Long = var23;
                  if (0L > var10 || var10 > limit) {
                     throwLimitExceeded(var23, limit);
                     throw new KotlinNothingValueException();
                  }

                  val var10002: Long = var23;
                  `$continuation`.L$0 = boundaryPrefixed;
                  `$continuation`.L$1 = input;
                  `$continuation`.L$2 = output;
                  `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(headers);
                  `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var23);
                  `$continuation`.J$0 = limit;
                  `$continuation`.label = 2;
                  var25 = ByteReadChannelOperationsKt.copyTo(input, output, var10002, `$continuation`);
                  if (var25 === var17) {
                     return var17;
                  }
                  break;
               case 1:
                  limit = `$continuation`.J$0;
                  var23 = `$continuation`.L$4 as java.lang.Long;
                  headers = `$continuation`.L$3 as HttpHeadersMap;
                  output = `$continuation`.L$2 as ByteWriteChannel;
                  input = `$continuation`.L$1 as ByteReadChannel;
                  boundaryPrefixed = `$continuation`.L$0 as ByteString;
                  ResultKt.throwOnFailure(`$result`);
                  var26 = `$result`;
                  break label60;
               case 2:
                  limit = `$continuation`.J$0;
                  var23 = `$continuation`.L$4 as java.lang.Long;
                  headers = `$continuation`.L$3 as HttpHeadersMap;
                  output = `$continuation`.L$2 as ByteWriteChannel;
                  input = `$continuation`.L$1 as ByteReadChannel;
                  boundaryPrefixed = `$continuation`.L$0 as ByteString;
                  ResultKt.throwOnFailure(`$result`);
                  var25 = `$result`;
                  break;
               case 3:
                  var12 = `$continuation`.J$1;
                  limit = `$continuation`.J$0;
                  var23 = `$continuation`.L$4 as java.lang.Long;
                  headers = `$continuation`.L$3 as HttpHeadersMap;
                  output = `$continuation`.L$2 as ByteWriteChannel;
                  input = `$continuation`.L$1 as ByteReadChannel;
                  boundaryPrefixed = `$continuation`.L$0 as ByteString;
                  ResultKt.throwOnFailure(`$result`);
                  var10000 = `$result`;
                  break label59;
               case 4:
                  val byteCount: Long = `$continuation`.J$1;
                  limit = `$continuation`.J$0;
                  headers = `$continuation`.L$3 as HttpHeadersMap;
                  output = `$continuation`.L$2 as ByteWriteChannel;
                  input = `$continuation`.L$1 as ByteReadChannel;
                  boundaryPrefixed = `$continuation`.L$0 as ByteString;
                  ResultKt.throwOnFailure(`$result`);
                  return Boxing.boxLong(byteCount);
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            var12 = (var25 as java.lang.Number).longValue();
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(boundaryPrefixed);
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(input);
            `$continuation`.L$2 = output;
            `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(headers);
            `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var23);
            `$continuation`.J$0 = limit;
            `$continuation`.J$1 = var12;
            `$continuation`.label = 3;
            var10000 = skipIfFoundReadCount(input, boundaryPrefixed, `$continuation`);
            if (var10000 === var17) {
               return var17;
            }
         }

         var28 = var12 + (var10000 as java.lang.Number).longValue();
         break label61;
      }

      var28 = (var26 as java.lang.Number).longValue();
   }

   `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(boundaryPrefixed);
   `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(input);
   `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(output);
   `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(headers);
   `$continuation`.L$4 = null;
   `$continuation`.J$0 = limit;
   `$continuation`.J$1 = var28;
   `$continuation`.label = 4;
   return if (output.flush(`$continuation`) === var17) var17 else Boxing.boxLong(var28);
}

private suspend fun ByteReadChannel.skipIfFoundReadCount(prefix: ByteString): Long {
   var `$continuation`: Continuation;
   label25: {
      if (`$completion` is io.ktor.http.cio.MultipartKt.skipIfFoundReadCount.1) {
         `$continuation` = `$completion` as io.ktor.http.cio.MultipartKt.skipIfFoundReadCount.1;
         if (((`$completion` as io.ktor.http.cio.MultipartKt.skipIfFoundReadCount.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label25;
         }
      }

      `$continuation` = new io.ktor.http.cio.MultipartKt.skipIfFoundReadCount.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$skipIfFoundReadCount`);
         `$continuation`.L$1 = prefix;
         `$continuation`.label = 1;
         var10000 = ByteReadChannelOperationsKt.skipIfFound(`$this$skipIfFoundReadCount`, prefix, `$continuation`);
         if (var10000 === var5) {
            return var5;
         }
         break;
      case 1:
         prefix = `$continuation`.L$1 as ByteString;
         `$this$skipIfFoundReadCount` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return Boxing.boxLong(if (var10000 as java.lang.Boolean) (long)prefix.getSize() else 0L);
}

public fun CoroutineScope.parseMultipart(input: ByteReadChannel, headers: HttpHeadersMap, maxPartSize: Long = java.lang.Long.MAX_VALUE): ReceiveChannel<
      MultipartEvent
   > {
   val var10000: java.lang.CharSequence = headers.get("Content-Type");
   if (var10000 == null) {
      throw new UnsupportedMediaTypeExceptionCIO("Failed to parse multipart: no Content-Type header");
   } else {
      val var7: java.lang.CharSequence = headers.get("Content-Length");
      return parseMultipart(`$this$parseMultipart`, input, var10000, if (var7 != null) CharsKt.parseDecLong(var7) else null, maxPartSize);
   }
}

@JvmSynthetic
fun `parseMultipart$default`(var0: CoroutineScope, var1: ByteReadChannel, var2: HttpHeadersMap, var3: Long, var5: Int, var6: Any): ReceiveChannel {
   if ((var5 and 4) != 0) {
      var3 = java.lang.Long.MAX_VALUE;
   }

   return parseMultipart(var0, var1, var2, var3);
}

public fun CoroutineScope.parseMultipart(input: ByteReadChannel, contentType: CharSequence, contentLength: Long?, maxPartSize: Long = java.lang.Long.MAX_VALUE): ReceiveChannel<
      MultipartEvent
   > {
   if (!ContentType.MultiPart.INSTANCE.contains(contentType)) {
      throw new UnsupportedMediaTypeExceptionCIO("Failed to parse multipart: Content-Type should be multipart/* but it is $contentType");
   } else {
      return parseMultipart(`$this$parseMultipart`, new ByteString(parseBoundaryInternal(contentType), 0, 0, 6, null), input, contentLength, maxPartSize);
   }
}

@JvmSynthetic
fun `parseMultipart$default`(var0: CoroutineScope, var1: ByteReadChannel, var2: java.lang.CharSequence, var3: java.lang.Long, var4: Long, var6: Int, var7: Any): ReceiveChannel {
   if ((var6 and 8) != 0) {
      var4 = java.lang.Long.MAX_VALUE;
   }

   return parseMultipart(var0, var1, var2, var3, var4);
}

private fun CoroutineScope.parseMultipart(boundaryPrefixed: ByteString, input: ByteReadChannel, totalLength: Long?, maxPartSize: Long): ReceiveChannel<
      MultipartEvent
   > {
   return ProduceKt.produce$default(
      `$this$parseMultipart`, null, 0, new io.ktor.http.cio.MultipartKt.parseMultipart.1(input, boundaryPrefixed, maxPartSize, totalLength, null), 3, null
   );
}

private fun findBoundary(contentType: CharSequence): Int {
   var state: Int = 0;
   var paramNameCount: Int = 0;
   var i: Int = 0;

   for (int var4 = contentType.length(); i < var4; i++) {
      val ch: Char = contentType.charAt(i);
      switch (state) {
         case 0:
            if (ch == ';') {
               state = 1;
               paramNameCount = 0;
            }
            break;
         case 1:
            if (ch == '=') {
               state = 2;
            } else if (ch == ';') {
               paramNameCount = 0;
            } else if (ch == ',') {
               state = 0;
            } else if (ch != ' ') {
               if (paramNameCount == 0 && kotlin.text.StringsKt.startsWith(contentType, "boundary=", i, true)) {
                  return i;
               }

               paramNameCount++;
            }
            break;
         case 2:
            switch (ch) {
               case '"':
                  state = 3;
                  continue;
               case ',':
                  state = 0;
                  continue;
               case ';':
                  state = 1;
                  paramNameCount = 0;
               default:
                  continue;
            }
         case 3:
            switch (ch) {
               case '"':
                  state = 1;
                  paramNameCount = 0;
                  continue;
               case '\\':
                  state = 4;
               default:
                  continue;
            }
         case 4:
            state = 3;
         default:
      }
   }

   return -1;
}

internal fun parseBoundaryInternal(contentType: CharSequence): ByteArray {
   val boundaryParameter: Int = findBoundary(contentType);
   if (boundaryParameter == -1) {
      throw new IOException("Failed to parse multipart: Content-Type's boundary parameter is missing");
   } else {
      val boundaryStart: Int = boundaryParameter + 9;
      val boundaryBytes: ByteArray = new byte[74];
      val position: Ref.IntRef = new Ref.IntRef();
      parseBoundaryInternal$put(position, boundaryBytes, (byte)13);
      parseBoundaryInternal$put(position, boundaryBytes, (byte)10);
      parseBoundaryInternal$put(position, boundaryBytes, (byte)45);
      parseBoundaryInternal$put(position, boundaryBytes, (byte)45);
      var state: Int = 0;
      var i: Int = boundaryStart;

      label47:
      for (int var7 = contentType.length(); i < var7; i++) {
         val ch: Char = contentType.charAt(i);
         val v: Int = ch and '\uffff';
         if ((ch and '\uffff' and '\uffff') > 127) {
            val var10002: StringBuilder = new StringBuilder().append("Failed to parse multipart: wrong boundary byte 0x");
            val var10003: java.lang.String = Integer.toString(v, kotlin.text.CharsKt.checkRadix(16));
            throw new IOException(var10002.append(var10003).append(" - should be 7bit character").toString());
         }

         switch (state) {
            case 0:
               switch (ch) {
                  case ' ':
                     continue;
                  case '"':
                     state = 2;
                     continue;
                  case ',':
                  case ';':
                     break label47;
                  default:
                     state = 1;
                     parseBoundaryInternal$put(position, boundaryBytes, (byte)v);
                     continue;
               }
            case 1:
               switch (ch) {
                  case ' ':
                  case ',':
                  case ';':
                     break label47;
                  default:
                     parseBoundaryInternal$put(position, boundaryBytes, (byte)v);
                     continue;
               }
            case 2:
               switch (ch) {
                  case '"':
                     break label47;
                  case '\\':
                     state = 3;
                     continue;
                  default:
                     parseBoundaryInternal$put(position, boundaryBytes, (byte)v);
                     continue;
               }
            case 3:
               parseBoundaryInternal$put(position, boundaryBytes, (byte)v);
               state = 2;
            default:
         }
      }

      if (position.element == 4) {
         throw new IOException("Empty multipart boundary is not allowed");
      } else {
         return ArraysKt.copyOfRange(boundaryBytes, 0, position.element);
      }
   }
}

private fun throwLimitExceeded(actual: Long, limit: Long): Nothing {
   throw new IOException("Multipart content length exceeds limit $actual > $limit; limit is defined using 'formFieldLimit' argument");
}

fun `parseBoundaryInternal$put`(position: Ref.IntRef, boundaryBytes: ByteArray, value: Byte) {
   if (position.element >= boundaryBytes.length) {
      throw new IOException("Failed to parse multipart: boundary shouldn't be longer than 70 characters");
   } else {
      boundaryBytes[position.element++] = value;
   }
}

@JvmSynthetic
fun `access$parsePreambleImpl`(boundary: ByteString, input: ByteReadChannel, output: ByteWriteChannel, limit: Long, `$completion`: Continuation): Any {
   return parsePreambleImpl(boundary, input, output, limit, `$completion`);
}

@JvmSynthetic
fun `access$parsePartHeadersImpl`(input: ByteReadChannel, `$completion`: Continuation): Any {
   return parsePartHeadersImpl(input, `$completion`);
}

@JvmSynthetic
fun `access$parsePartBodyImpl`(
   boundaryPrefixed: ByteString, input: ByteReadChannel, output: ByteWriteChannel, headers: HttpHeadersMap, limit: Long, `$completion`: Continuation
): Any {
   return parsePartBodyImpl(boundaryPrefixed, input, output, headers, limit, `$completion`);
}

@JvmSynthetic
fun `access$skipIfFoundReadCount`(`$receiver`: ByteReadChannel, prefix: ByteString, `$completion`: Continuation): Any {
   return skipIfFoundReadCount(`$receiver`, prefix, `$completion`);
}

@JvmSynthetic
fun `access$getPrefixString$p`(): ByteString {
   return PrefixString;
}

@JvmSynthetic
fun `access$getCrLf$p`(): ByteString {
   return CrLf;
}
