package io.ktor.http

import java.util.Locale
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nContentTypes.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ContentTypes.kt\nio/ktor/http/ContentType\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,438:1\n1#2:439\n1761#3,3:440\n1761#3,3:443\n*S KotlinDebug\n*F\n+ 1 ContentTypes.kt\nio/ktor/http/ContentType\n*L\n49#1:440,3\n89#1:443,3\n*E\n"])
public class ContentType private constructor(contentType: String,
   contentSubtype: String,
   existingContent: String,
   parameters: List<HeaderValueParam> = CollectionsKt.emptyList()
) : HeaderValueWithParameters(existingContent, parameters) {
   public final val contentType: String
   public final val contentSubtype: String

   init {
      this.contentType = contentType;
      this.contentSubtype = contentSubtype;
   }

   public constructor(contentType: String, contentSubtype: String, parameters: List<HeaderValueParam> = CollectionsKt.emptyList()) : this(
         contentType, contentSubtype, "$contentType/$contentSubtype", parameters
      )
   public fun withParameter(name: String, value: String): ContentType {
      return if (this.hasParameter(name, value))
         this
         else
         new ContentType(this.contentType, this.contentSubtype, this.getContent(), CollectionsKt.plus(this.getParameters(), new HeaderValueParam(name, value)));
   }

   private fun hasParameter(name: String, value: String): Boolean {
      var var10000: Boolean;
      switch (this.getParameters().size()) {
         case 0:
            var10000 = false;
            break;
         case 1:
            val var9: HeaderValueParam = this.getParameters().get(0);
            var10000 = StringsKt.equals(var9.getName(), name, true) && StringsKt.equals(var9.getValue(), value, true);
            break;
         default:
            val `$this$any$iv`: java.lang.Iterable = this.getParameters();
            if (`$this$any$iv` is java.util.Collection && (`$this$any$iv` as java.util.Collection).isEmpty()) {
               var10000 = false;
            } else {
               val var5: java.util.Iterator = `$this$any$iv`.iterator();

               while (true) {
                  if (!var5.hasNext()) {
                     var10000 = false;
                     break;
                  }

                  val it: HeaderValueParam = var5.next() as HeaderValueParam;
                  if (StringsKt.equals(it.getName(), name, true) && StringsKt.equals(it.getValue(), value, true)) {
                     var10000 = true;
                     break;
                  }
               }
            }
      }

      return var10000;
   }

   public fun withoutParameters(): ContentType {
      return if (this.getParameters().isEmpty()) this else new ContentType(this.contentType, this.contentSubtype, null, 4, null);
   }

   public fun match(pattern: ContentType): Boolean {
      if (!(pattern.contentType == "*") && !StringsKt.equals(pattern.contentType, this.contentType, true)) {
         return false;
      } else if (!(pattern.contentSubtype == "*") && !StringsKt.equals(pattern.contentSubtype, this.contentSubtype, true)) {
         return false;
      } else {
         for (HeaderValueParam var3 : pattern.getParameters()) {
            val patternName: java.lang.String = var3.component1();
            val patternValue: java.lang.String = var3.component2();
            var var10000: Boolean;
            if (patternName == "*") {
               if (patternValue == "*") {
                  var10000 = true;
               } else {
                  val `$this$any$iv`: java.lang.Iterable = this.getParameters();
                  if (`$this$any$iv` is java.util.Collection && (`$this$any$iv` as java.util.Collection).isEmpty()) {
                     var10000 = false;
                  } else {
                     val var10: java.util.Iterator = `$this$any$iv`.iterator();

                     while (true) {
                        if (!var10.hasNext()) {
                           var10000 = false;
                           break;
                        }

                        if (StringsKt.equals((var10.next() as HeaderValueParam).getValue(), patternValue, true)) {
                           var10000 = true;
                           break;
                        }
                     }
                  }
               }
            } else {
               val value: java.lang.String = this.parameter(patternName);
               var10000 = if (patternValue == "*") value != null else StringsKt.equals(value, patternValue, true);
            }

            if (!var10000) {
               return false;
            }
         }

         return true;
      }
   }

   public fun match(pattern: String): Boolean {
      return this.match(Companion.parse(pattern));
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is ContentType
         && StringsKt.equals(this.contentType, (other as ContentType).contentType, true)
         && StringsKt.equals(this.contentSubtype, (other as ContentType).contentSubtype, true)
         && this.getParameters() == (other as ContentType).getParameters();
   }

   public override fun hashCode(): Int {
      val var10000: java.lang.String = this.contentType.toLowerCase(Locale.ROOT);
      val result: Int = var10000.hashCode();
      val var10001: Int = 31 * result;
      val var10002: java.lang.String = this.contentSubtype.toLowerCase(Locale.ROOT);
      return result + var10001 + var10002.hashCode() + 31 * this.getParameters().hashCode();
   }

   public object Application {
      public const val TYPE: String = "application"
      public final val Any: ContentType = new ContentType("application", "*", null, 4, null)
      public final val Atom: ContentType = new ContentType("application", "atom+xml", null, 4, null)
      public final val Cbor: ContentType = new ContentType("application", "cbor", null, 4, null)
      public final val Json: ContentType = new ContentType("application", "json", null, 4, null)
      public final val HalJson: ContentType = new ContentType("application", "hal+json", null, 4, null)
      public final val JavaScript: ContentType = new ContentType("application", "javascript", null, 4, null)
      public final val OctetStream: ContentType = new ContentType("application", "octet-stream", null, 4, null)
      public final val Rss: ContentType = new ContentType("application", "rss+xml", null, 4, null)
      public final val Soap: ContentType = new ContentType("application", "soap+xml", null, 4, null)
      public final val Xml: ContentType = new ContentType("application", "xml", null, 4, null)
      public final val Xml_Dtd: ContentType = new ContentType("application", "xml-dtd", null, 4, null)
      public final val Yaml: ContentType = new ContentType("application", "yaml", null, 4, null)
      public final val Zip: ContentType = new ContentType("application", "zip", null, 4, null)
      public final val GZip: ContentType = new ContentType("application", "gzip", null, 4, null)
      public final val FormUrlEncoded: ContentType = new ContentType("application", "x-www-form-urlencoded", null, 4, null)
      public final val Pdf: ContentType = new ContentType("application", "pdf", null, 4, null)
      public final val Xlsx: ContentType = new ContentType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet", null, 4, null)
      public final val Docx: ContentType = new ContentType("application", "vnd.openxmlformats-officedocument.wordprocessingml.document", null, 4, null)
      public final val Pptx: ContentType = new ContentType("application", "vnd.openxmlformats-officedocument.presentationml.presentation", null, 4, null)
      public final val ProtoBuf: ContentType = new ContentType("application", "protobuf", null, 4, null)
      public final val Wasm: ContentType = new ContentType("application", "wasm", null, 4, null)
      public final val ProblemJson: ContentType = new ContentType("application", "problem+json", null, 4, null)
      public final val ProblemXml: ContentType = new ContentType("application", "problem+xml", null, 4, null)

      public operator fun contains(contentType: CharSequence): Boolean {
         return StringsKt.startsWith(contentType, "application/", true);
      }

      public operator fun contains(contentType: ContentType): Boolean {
         return contentType.match(Any);
      }
   }

   public object Audio {
      public const val TYPE: String = "audio"
      public final val Any: ContentType = new ContentType("audio", "*", null, 4, null)
      public final val MP4: ContentType = new ContentType("audio", "mp4", null, 4, null)
      public final val MPEG: ContentType = new ContentType("audio", "mpeg", null, 4, null)
      public final val OGG: ContentType = new ContentType("audio", "ogg", null, 4, null)

      public operator fun contains(contentType: CharSequence): Boolean {
         return StringsKt.startsWith(contentType, "audio/", true);
      }

      public operator fun contains(contentType: ContentType): Boolean {
         return contentType.match(Any);
      }
   }

   @SourceDebugExtension(["SMAP\nContentTypes.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ContentTypes.kt\nio/ktor/http/ContentType$Companion\n+ 2 HeaderValueWithParameters.kt\nio/ktor/http/HeaderValueWithParameters$Companion\n*L\n1#1,438:1\n70#2,2:439\n*S KotlinDebug\n*F\n+ 1 ContentTypes.kt\nio/ktor/http/ContentType$Companion\n*L\n138#1:439,2\n*E\n"])
   public companion object {
      public final val Any: ContentType

      public fun parse(value: String): ContentType {
         if (StringsKt.isBlank(value)) {
            return this.getAny();
         } else {
            val `this_$iv`: HeaderValueWithParameters.Companion = HeaderValueWithParameters.Companion;
            val `headerValue$iv`: HeaderValue = CollectionsKt.last(HttpHeaderValueParserKt.parseHeaderValue(value));
            val parameters: java.util.List = `headerValue$iv`.getParams();
            val parts: java.lang.String = `headerValue$iv`.getValue();
            val slash: Int = StringsKt.indexOf$default(parts, '/', 0, false, 6, null);
            if (slash == -1) {
               if (StringsKt.trim(parts).toString() == "*") {
                  return ContentType.Companion.getAny();
               } else {
                  throw new BadContentTypeFormatException(value);
               }
            } else {
               var var10000: java.lang.String = parts.substring(0, slash);
               val type: java.lang.String = StringsKt.trim(var10000).toString();
               if (type.length() == 0) {
                  throw new BadContentTypeFormatException(value);
               } else {
                  var10000 = parts.substring(slash + 1);
                  val subtype: java.lang.String = StringsKt.trim(var10000).toString();
                  if (StringsKt.contains$default(type, ' ', false, 2, null) || StringsKt.contains$default(subtype, ' ', false, 2, null)) {
                     throw new BadContentTypeFormatException(value);
                  } else if (subtype.length() != 0 && !StringsKt.contains$default(subtype, '/', false, 2, null)) {
                     return new ContentType(type, subtype, parameters);
                  } else {
                     throw new BadContentTypeFormatException(value);
                  }
               }
            }
         }
      }
   }

   public object Font {
      public const val TYPE: String = "font"
      public final val Any: ContentType = new ContentType("font", "*", null, 4, null)
      public final val Collection: ContentType = new ContentType("font", "collection", null, 4, null)
      public final val Otf: ContentType = new ContentType("font", "otf", null, 4, null)
      public final val Sfnt: ContentType = new ContentType("font", "sfnt", null, 4, null)
      public final val Ttf: ContentType = new ContentType("font", "ttf", null, 4, null)
      public final val Woff: ContentType = new ContentType("font", "woff", null, 4, null)
      public final val Woff2: ContentType = new ContentType("font", "woff2", null, 4, null)

      public operator fun contains(contentType: CharSequence): Boolean {
         return StringsKt.startsWith(contentType, "font/", true);
      }

      public operator fun contains(contentType: ContentType): Boolean {
         return contentType.match(Any);
      }
   }

   public object Image {
      public const val TYPE: String = "image"
      public final val Any: ContentType = new ContentType("image", "*", null, 4, null)
      public final val APNG: ContentType = new ContentType("image", "apng", null, 4, null)
      public final val AVIF: ContentType = new ContentType("image", "avif", null, 4, null)
      public final val BMP: ContentType = new ContentType("image", "bmp", null, 4, null)
      public final val GIF: ContentType = new ContentType("image", "gif", null, 4, null)
      public final val HEIC: ContentType = new ContentType("image", "heic", null, 4, null)
      public final val HEIF: ContentType = new ContentType("image", "heif", null, 4, null)
      public final val JPEG: ContentType = new ContentType("image", "jpeg", null, 4, null)
      public final val JXL: ContentType = new ContentType("image", "jxl", null, 4, null)
      public final val PNG: ContentType = new ContentType("image", "png", null, 4, null)
      public final val SVG: ContentType = new ContentType("image", "svg+xml", null, 4, null)
      public final val TIFF: ContentType = new ContentType("image", "tiff", null, 4, null)
      public final val WEBP: ContentType = new ContentType("image", "webp", null, 4, null)
      public final val XIcon: ContentType = new ContentType("image", "x-icon", null, 4, null)

      public operator fun contains(contentSubtype: String): Boolean {
         return StringsKt.startsWith(contentSubtype, "image/", true);
      }

      public operator fun contains(contentType: ContentType): Boolean {
         return contentType.match(Any);
      }
   }

   public object Message {
      public const val TYPE: String = "message"
      public final val Any: ContentType = new ContentType("message", "*", null, 4, null)
      public final val Http: ContentType = new ContentType("message", "http", null, 4, null)

      public operator fun contains(contentSubtype: String): Boolean {
         return StringsKt.startsWith(contentSubtype, "message/", true);
      }

      public operator fun contains(contentType: ContentType): Boolean {
         return contentType.match(Any);
      }
   }

   public object MultiPart {
      public const val TYPE: String = "multipart"
      public final val Any: ContentType = new ContentType("multipart", "*", null, 4, null)
      public final val Mixed: ContentType = new ContentType("multipart", "mixed", null, 4, null)
      public final val Alternative: ContentType = new ContentType("multipart", "alternative", null, 4, null)
      public final val Related: ContentType = new ContentType("multipart", "related", null, 4, null)
      public final val FormData: ContentType = new ContentType("multipart", "form-data", null, 4, null)
      public final val Signed: ContentType = new ContentType("multipart", "signed", null, 4, null)
      public final val Encrypted: ContentType = new ContentType("multipart", "encrypted", null, 4, null)
      public final val ByteRanges: ContentType = new ContentType("multipart", "byteranges", null, 4, null)

      public operator fun contains(contentType: CharSequence): Boolean {
         return StringsKt.startsWith(contentType, "multipart/", true);
      }

      public operator fun contains(contentType: ContentType): Boolean {
         return contentType.match(Any);
      }
   }

   public object Text {
      public const val TYPE: String = "text"
      public final val Any: ContentType = new ContentType("text", "*", null, 4, null)
      public final val Plain: ContentType = new ContentType("text", "plain", null, 4, null)
      public final val CSS: ContentType = new ContentType("text", "css", null, 4, null)
      public final val CSV: ContentType = new ContentType("text", "csv", null, 4, null)
      public final val Html: ContentType = new ContentType("text", "html", null, 4, null)
      public final val JavaScript: ContentType = new ContentType("text", "javascript", null, 4, null)
      public final val VCard: ContentType = new ContentType("text", "vcard", null, 4, null)
      public final val Xml: ContentType = new ContentType("text", "xml", null, 4, null)
      public final val EventStream: ContentType = new ContentType("text", "event-stream", null, 4, null)

      public operator fun contains(contentType: CharSequence): Boolean {
         return StringsKt.startsWith(contentType, "text/", true);
      }

      public operator fun contains(contentType: ContentType): Boolean {
         return contentType.match(Any);
      }
   }

   public object Video {
      public const val TYPE: String = "video"
      public final val Any: ContentType = new ContentType("video", "*", null, 4, null)
      public final val MPEG: ContentType = new ContentType("video", "mpeg", null, 4, null)
      public final val MP4: ContentType = new ContentType("video", "mp4", null, 4, null)
      public final val OGG: ContentType = new ContentType("video", "ogg", null, 4, null)
      public final val QuickTime: ContentType = new ContentType("video", "quicktime", null, 4, null)

      public operator fun contains(contentType: CharSequence): Boolean {
         return StringsKt.startsWith(contentType, "video/", true);
      }

      public operator fun contains(contentType: ContentType): Boolean {
         return contentType.match(Any);
      }
   }
}
