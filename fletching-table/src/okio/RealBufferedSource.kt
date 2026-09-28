package okio

import java.io.EOFException
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.charset.Charset
import kotlin.jvm.internal.SourceDebugExtension
import okio.RealBufferedSource.inputStream.1
import okio.internal.-Buffer
import okio.internal.-RealBufferedSource

@SourceDebugExtension(["SMAP\nRealBufferedSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealBufferedSource.kt\nokio/RealBufferedSource\n+ 2 RealBufferedSource.kt\nokio/internal/-RealBufferedSource\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 BufferedSource.kt\nokio/internal/-BufferedSource\n+ 5 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,207:1\n63#1:213\n63#1:224\n63#1:231\n63#1:237\n63#1:239\n63#1:243\n63#1:248\n63#1:266\n63#1:270\n63#1:277\n63#1:290\n63#1:299\n63#1:300\n63#1:301\n63#1:307\n63#1:315\n63#1:328\n63#1:332\n63#1:333\n63#1:334\n63#1:335\n63#1:340\n63#1:352\n63#1:368\n63#1:378\n63#1:381\n63#1:384\n63#1:387\n63#1:390\n63#1:393\n63#1:399\n63#1:416\n63#1:436\n63#1:451\n63#1:468\n63#1:495\n39#2:208\n40#2,3:210\n43#2,7:214\n53#2:221\n54#2:223\n58#2,2:225\n62#2:227\n63#2,2:229\n65#2,3:232\n71#2,2:235\n76#2:238\n77#2:240\n81#2,2:241\n86#2:244\n88#2,2:246\n90#2,13:249\n109#2:265\n110#2:267\n114#2,2:268\n119#2,6:271\n125#2,9:278\n136#2,3:287\n139#2,6:291\n145#2:298\n149#2,5:302\n154#2,5:308\n161#2,2:313\n163#2,11:316\n177#2:327\n178#2:329\n182#2,2:330\n187#2,4:336\n191#2,6:341\n201#2:347\n202#2,3:349\n205#2,8:353\n213#2,3:362\n220#2,3:365\n223#2,7:369\n233#2,2:376\n238#2,2:379\n243#2,2:382\n248#2,2:385\n253#2,2:388\n258#2,2:391\n263#2,5:394\n268#2,11:400\n282#2,5:411\n287#2,14:417\n304#2,2:431\n306#2,2:434\n308#2,7:437\n317#2,2:444\n319#2,4:447\n323#2,11:452\n421#2,2:463\n424#2,2:466\n426#2,7:469\n442#2:476\n444#2,12:478\n459#2:490\n463#2,4:491\n467#2:496\n469#2:497\n471#2:498\n1#3:209\n1#3:222\n1#3:228\n1#3:245\n1#3:348\n1#3:433\n1#3:446\n1#3:465\n1#3:477\n26#4,3:262\n88#5:297\n88#5:361\n*S KotlinDebug\n*F\n+ 1 RealBufferedSource.kt\nokio/RealBufferedSource\n*L\n67#1:213\n68#1:224\n70#1:231\n71#1:237\n72#1:239\n73#1:243\n74#1:248\n76#1:266\n77#1:270\n79#1:277\n81#1:290\n84#1:299\n85#1:300\n89#1:301\n93#1:307\n94#1:315\n95#1:328\n96#1:332\n99#1:333\n100#1:334\n105#1:335\n108#1:340\n110#1:352\n111#1:368\n112#1:378\n113#1:381\n114#1:384\n115#1:387\n116#1:390\n117#1:393\n118#1:399\n119#1:416\n120#1:436\n125#1:451\n135#1:468\n203#1:495\n67#1:208\n67#1:210,3\n67#1:214,7\n68#1:221\n68#1:223\n69#1:225,2\n70#1:227\n70#1:229,2\n70#1:232,3\n71#1:235,2\n72#1:238\n72#1:240\n73#1:241,2\n74#1:244\n74#1:246,2\n74#1:249,13\n76#1:265\n76#1:267\n77#1:268,2\n79#1:271,6\n79#1:278,9\n81#1:287,3\n81#1:291,6\n81#1:298\n93#1:302,5\n93#1:308,5\n94#1:313,2\n94#1:316,11\n95#1:327\n95#1:329\n96#1:330,2\n108#1:336,4\n108#1:341,6\n110#1:347\n110#1:349,3\n110#1:353,8\n110#1:362,3\n111#1:365,3\n111#1:369,7\n112#1:376,2\n113#1:379,2\n114#1:382,2\n115#1:385,2\n116#1:388,2\n117#1:391,2\n118#1:394,5\n118#1:400,11\n119#1:411,5\n119#1:417,14\n120#1:431,2\n120#1:434,2\n120#1:437,7\n125#1:444,2\n125#1:447,4\n125#1:452,11\n135#1:463,2\n135#1:466,2\n135#1:469,7\n149#1:476\n149#1:478,12\n151#1:490\n203#1:491,4\n203#1:496\n204#1:497\n205#1:498\n67#1:209\n68#1:222\n70#1:228\n74#1:245\n110#1:348\n120#1:433\n125#1:446\n135#1:465\n149#1:477\n75#1:262,3\n81#1:297\n110#1:361\n*E\n"])
internal class RealBufferedSource(source: Source) : BufferedSource {
   public final val source: Source
   public final val bufferField: Buffer

   public final var closed: Boolean
      private set

   public open val buffer: Buffer
      public open inline get() {
         return this.bufferField;
      }


   init {
      this.source = source;
      this.bufferField = new Buffer();
   }

   public override fun buffer(): Buffer {
      return this.bufferField;
   }

   public override fun read(sink: Buffer, byteCount: Long): Long {
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
      } else if (this.closed) {
         throw new IllegalStateException("closed".toString());
      } else {
         if (this.bufferField.size() == 0L) {
            if (byteCount == 0L) {
               return 0L;
            }

            if (this.source.read(this.bufferField, 8192L) == -1L) {
               return -1L;
            }
         }

         return this.bufferField.read(sink, Math.min(byteCount, this.bufferField.size()));
      }
   }

   public override fun exhausted(): Boolean {
      if (this.closed) {
         throw new IllegalStateException("closed".toString());
      } else {
         return this.bufferField.exhausted() && this.source.read(this.bufferField, 8192L) == -1L;
      }
   }

   public override fun require(byteCount: Long) {
      if (!this.request(byteCount)) {
         throw new EOFException();
      }
   }

   public override fun request(byteCount: Long): Boolean {
      val `$this$commonRequest$iv`: RealBufferedSource = this;
      val `byteCount$iv`: Long = byteCount;
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
      } else if (this.closed) {
         throw new IllegalStateException("closed".toString());
      } else {
         var var10000: Boolean;
         while (true) {
            if (`$this$commonRequest$iv`.bufferField.size() < `byteCount$iv`) {
               if (`$this$commonRequest$iv`.source.read(`$this$commonRequest$iv`.bufferField, 8192L) != -1L) {
                  continue;
               }

               var10000 = false;
               break;
            }

            var10000 = true;
            break;
         }

         return var10000;
      }
   }

   public override fun readByte(): Byte {
      this.require(1L);
      return this.bufferField.readByte();
   }

   public override fun readByteString(): ByteString {
      this.bufferField.writeAll(this.source);
      return this.bufferField.readByteString();
   }

   public override fun readByteString(byteCount: Long): ByteString {
      this.require(byteCount);
      return this.bufferField.readByteString(byteCount);
   }

   public override fun select(options: Options): Int {
      val `$this$commonSelect$iv`: RealBufferedSource = this;
      val `options$iv`: Options = options;
      if (this.closed) {
         throw new IllegalStateException("closed".toString());
      } else {
         while (true) {
            val `index$iv`: Int = -Buffer.selectPrefix(`$this$commonSelect$iv`.bufferField, `options$iv`, true);
            switch (index$iv) {
               case -2:
                  if (`$this$commonSelect$iv`.source.read(`$this$commonSelect$iv`.bufferField, 8192L) != -1L) {
                     break;
                  }

                  return -1;
               case -1:
                  return -1;
               default:
                  `$this$commonSelect$iv`.bufferField.skip((long)`options$iv`.getByteStrings$okio()[`index$iv`].size());
                  return `index$iv`;
            }
         }
      }
   }

   public override fun <T : Any> select(options: TypedOptions<T>): T? {
      val `index$iv`: Int = this.select(options.getOptions$okio());
      return (T)(if (`index$iv` == -1) null else options.get(`index$iv`));
   }

   public override fun readByteArray(): ByteArray {
      this.bufferField.writeAll(this.source);
      return this.bufferField.readByteArray();
   }

   public override fun readByteArray(byteCount: Long): ByteArray {
      this.require(byteCount);
      return this.bufferField.readByteArray(byteCount);
   }

   public override fun read(sink: ByteArray): Int {
      return this.read(sink, 0, sink.length);
   }

   public override fun readFully(sink: ByteArray) {
      val `$this$commonReadFully$iv`: RealBufferedSource = this;
      val `sink$iv`: ByteArray = sink;

      try {
         `$this$commonReadFully$iv`.require((long)`sink$iv`.length);
      } catch (var10: EOFException) {
         var `$i$f$getBuffer`: Int = 0;

         while (true) {
            if (`$this$commonReadFully$iv`.bufferField.size() <= 0L) {
               throw var10;
            }

            val `read$iv`: Int = `$this$commonReadFully$iv`.bufferField.read(`sink$iv`, `$i$f$getBuffer`, (int)`$this$commonReadFully$iv`.bufferField.size());
            if (`read$iv` == -1) {
               throw new AssertionError();
            }

            `$i$f$getBuffer` += `read$iv`;
         }
      }

      this.bufferField.readFully(sink);
   }

   public override fun read(sink: ByteArray, offset: Int, byteCount: Int): Int {
      -SegmentedByteString.checkOffsetAndCount((long)sink.length, (long)offset, (long)byteCount);
      if (this.bufferField.size() == 0L) {
         if (byteCount == 0) {
            return 0;
         }

         if (this.source.read(this.bufferField, 8192L) == -1L) {
            return -1;
         }
      }

      return this.bufferField.read(sink, offset, (int)Math.min((long)byteCount, this.bufferField.size()));
   }

   public override fun read(sink: ByteBuffer): Int {
      return if (this.bufferField.size() == 0L && this.source.read(this.bufferField, 8192L) == -1L) -1 else this.bufferField.read(sink);
   }

   public override fun readFully(sink: Buffer, byteCount: Long) {
      val `$this$commonReadFully$iv`: RealBufferedSource = this;
      val `byteCount$iv`: Long = byteCount;

      try {
         `$this$commonReadFully$iv`.require(`byteCount$iv`);
      } catch (var12: EOFException) {
         sink.writeAll(this.bufferField);
         throw var12;
      }

      this.bufferField.readFully(sink, byteCount);
   }

   public override fun readAll(sink: Sink): Long {
      val `$this$commonReadAll$iv`: RealBufferedSource = this;
      val `sink$iv`: Sink = sink;
      var `totalBytesWritten$iv`: Long = 0L;

      while (true) {
         if (`$this$commonReadAll$iv`.source.read(`$this$commonReadAll$iv`.bufferField, 8192L) == -1L) {
            if (`$this$commonReadAll$iv`.bufferField.size() > 0L) {
               `totalBytesWritten$iv` += `$this$commonReadAll$iv`.bufferField.size();
               `sink$iv`.write(`$this$commonReadAll$iv`.bufferField, `$this$commonReadAll$iv`.bufferField.size());
            }

            return `totalBytesWritten$iv`;
         }

         val `emitByteCount$iv`: Long = `$this$commonReadAll$iv`.bufferField.completeSegmentByteCount();
         if (`emitByteCount$iv` > 0L) {
            `totalBytesWritten$iv` += `emitByteCount$iv`;
            `sink$iv`.write(`$this$commonReadAll$iv`.bufferField, `emitByteCount$iv`);
         }
      }
   }

   public override fun readUtf8(): String {
      this.bufferField.writeAll(this.source);
      return this.bufferField.readUtf8();
   }

   public override fun readUtf8(byteCount: Long): String {
      this.require(byteCount);
      return this.bufferField.readUtf8(byteCount);
   }

   public override fun readString(charset: Charset): String {
      this.bufferField.writeAll(this.source);
      return this.bufferField.readString(charset);
   }

   public override fun readString(byteCount: Long, charset: Charset): String {
      this.require(byteCount);
      return this.bufferField.readString(byteCount, charset);
   }

   public override fun readUtf8Line(): String? {
      val `newline$iv`: Long = this.indexOf((byte)10);
      return if (`newline$iv` == -1L)
         (if (this.bufferField.size() != 0L) this.readUtf8(this.bufferField.size()) else null)
         else
         -Buffer.readUtf8Line(this.bufferField, `newline$iv`);
   }

   public override fun readUtf8LineStrict(): String {
      return this.readUtf8LineStrict(java.lang.Long.MAX_VALUE);
   }

   public override fun readUtf8LineStrict(limit: Long): String {
      if (limit < 0L) {
         throw new IllegalArgumentException(("limit < 0: $limit").toString());
      } else {
         label37: {
            val `scanLength$iv`: Long = if (limit == java.lang.Long.MAX_VALUE) java.lang.Long.MAX_VALUE else limit + 1L;
            val `newline$iv`: Long = this.indexOf((byte)10, 0L, if (limit == java.lang.Long.MAX_VALUE) java.lang.Long.MAX_VALUE else limit + 1L);
            val var10000: java.lang.String;
            if (`newline$iv` != -1L) {
               var10000 = -Buffer.readUtf8Line(this.bufferField, `newline$iv`);
            } else {
               if (`scanLength$iv` >= java.lang.Long.MAX_VALUE
                  || !this.request(`scanLength$iv`)
                  || this.bufferField.getByte(`scanLength$iv` - 1L) != 13
                  || !this.request(`scanLength$iv` + 1L)
                  || this.bufferField.getByte(`scanLength$iv`) != 10) {
                  break label37;
               }

               var10000 = -Buffer.readUtf8Line(this.bufferField, `scanLength$iv`);
            }

            return var10000;
         }

         val `data$iv`: Buffer = new Buffer();
         this.bufferField.copyTo(`data$iv`, 0L, Math.min((long)32, this.bufferField.size()));
         throw new EOFException("\\n not found: limit=${Math.min(this.bufferField.size(), limit)} content=${`data$iv`.readByteString().hex()}…");
      }
   }

   public override fun readUtf8CodePoint(): Int {
      this.require(1L);
      val `b0$iv`: Int = this.bufferField.getByte(0L);
      if ((`b0$iv` and 224) == 192) {
         this.require(2L);
      } else if ((`b0$iv` and 240) == 224) {
         this.require(3L);
      } else if ((`b0$iv` and 248) == 240) {
         this.require(4L);
      }

      return this.bufferField.readUtf8CodePoint();
   }

   public override fun readShort(): Short {
      this.require(2L);
      return this.bufferField.readShort();
   }

   public override fun readShortLe(): Short {
      this.require(2L);
      return this.bufferField.readShortLe();
   }

   public override fun readInt(): Int {
      this.require(4L);
      return this.bufferField.readInt();
   }

   public override fun readIntLe(): Int {
      this.require(4L);
      return this.bufferField.readIntLe();
   }

   public override fun readLong(): Long {
      this.require(8L);
      return this.bufferField.readLong();
   }

   public override fun readLongLe(): Long {
      this.require(8L);
      return this.bufferField.readLongLe();
   }

   public override fun readDecimalLong(): Long {
      val `$this$commonReadDecimalLong$iv`: RealBufferedSource = this;
      this.require(1L);

      for (long pos$iv = 0L; $this$commonReadDecimalLong$iv.request(pos$iv + 1L); pos$iv++) {
         val `this_$iv$iv`: Byte = `$this$commonReadDecimalLong$iv`.bufferField.getByte(`pos$iv`);
         if ((`this_$iv$iv` < 48 || `this_$iv$iv` > 57) && (`pos$iv` != 0L || `this_$iv$iv` != 45)) {
            if (`pos$iv` == 0L) {
               val var10002: StringBuilder = new StringBuilder().append("Expected a digit or '-' but was 0x");
               val var10003: java.lang.String = Integer.toString(`this_$iv$iv`, CharsKt.checkRadix(16));
               throw new NumberFormatException(var10002.append(var10003).toString());
            }
            break;
         }
      }

      return `$this$commonReadDecimalLong$iv`.bufferField.readDecimalLong();
   }

   public override fun readHexadecimalUnsignedLong(): Long {
      val `$this$commonReadHexadecimalUnsignedLong$iv`: RealBufferedSource = this;
      this.require(1L);

      for (int pos$iv = 0; $this$commonReadHexadecimalUnsignedLong$iv.request(pos$iv + 1); pos$iv++) {
         val `this_$iv$iv`: Byte = `$this$commonReadHexadecimalUnsignedLong$iv`.bufferField.getByte((long)`pos$iv`);
         if ((`this_$iv$iv` < 48 || `this_$iv$iv` > 57) && (`this_$iv$iv` < 97 || `this_$iv$iv` > 102) && (`this_$iv$iv` < 65 || `this_$iv$iv` > 70)) {
            if (`pos$iv` == 0) {
               val var10002: StringBuilder = new StringBuilder().append("Expected leading [0-9a-fA-F] character but was 0x");
               val var10003: java.lang.String = Integer.toString(`this_$iv$iv`, CharsKt.checkRadix(16));
               throw new NumberFormatException(var10002.append(var10003).toString());
            }
            break;
         }
      }

      return `$this$commonReadHexadecimalUnsignedLong$iv`.bufferField.readHexadecimalUnsignedLong();
   }

   public override fun skip(byteCount: Long) {
      val `$this$commonSkip$iv`: RealBufferedSource = this;
      var `byteCount$iv`: Long = byteCount;
      if (this.closed) {
         throw new IllegalStateException("closed".toString());
      } else {
         while (byteCount$iv > 0L) {
            if (`$this$commonSkip$iv`.bufferField.size() == 0L && `$this$commonSkip$iv`.source.read(`$this$commonSkip$iv`.bufferField, 8192L) == -1L) {
               throw new EOFException();
            }

            val `toSkip$iv`: Long = Math.min(`byteCount$iv`, `$this$commonSkip$iv`.bufferField.size());
            `$this$commonSkip$iv`.bufferField.skip(`toSkip$iv`);
            `byteCount$iv` -= `toSkip$iv`;
         }
      }
   }

   public override fun indexOf(b: Byte): Long {
      return this.indexOf(b, 0L, java.lang.Long.MAX_VALUE);
   }

   public override fun indexOf(b: Byte, fromIndex: Long): Long {
      return this.indexOf(b, fromIndex, java.lang.Long.MAX_VALUE);
   }

   public override fun indexOf(b: Byte, fromIndex: Long, toIndex: Long): Long {
      val `$this$commonIndexOf$iv`: RealBufferedSource = this;
      val `b$iv`: Byte = b;
      val `toIndex$iv`: Long = toIndex;
      var var28: Long = fromIndex;
      if (this.closed) {
         throw new IllegalStateException("closed".toString());
      } else if (0L > fromIndex || fromIndex > toIndex) {
         throw new IllegalArgumentException(("fromIndex=$fromIndex toIndex=$toIndex").toString());
      } else {
         var var10000: Long;
         while (true) {
            if (var28 < `toIndex$iv`) {
               val `result$iv`: Long = `$this$commonIndexOf$iv`.bufferField.indexOf(`b$iv`, var28, `toIndex$iv`);
               if (`result$iv` != -1L) {
                  var10000 = `result$iv`;
                  break;
               }

               val `lastBufferSize$iv`: Long = `$this$commonIndexOf$iv`.bufferField.size();
               if (`lastBufferSize$iv` < `toIndex$iv` && `$this$commonIndexOf$iv`.source.read(`$this$commonIndexOf$iv`.bufferField, 8192L) != -1L) {
                  var28 = Math.max(var28, `lastBufferSize$iv`);
                  continue;
               }

               var10000 = -1L;
               break;
            }

            var10000 = -1L;
            break;
         }

         return var10000;
      }
   }

   public override fun indexOf(bytes: ByteString): Long {
      return this.indexOf(bytes, 0L);
   }

   public override fun indexOf(bytes: ByteString, fromIndex: Long): Long {
      return this.indexOf(bytes, fromIndex, java.lang.Long.MAX_VALUE);
   }

   public override fun indexOf(bytes: ByteString, fromIndex: Long, toIndex: Long): Long {
      return -RealBufferedSource.commonIndexOf$default(this, bytes, 0, 0, fromIndex, toIndex, 6, null);
   }

   public override fun indexOfElement(targetBytes: ByteString): Long {
      return this.indexOfElement(targetBytes, 0L);
   }

   public override fun indexOfElement(targetBytes: ByteString, fromIndex: Long): Long {
      val `$this$commonIndexOfElement$iv`: RealBufferedSource = this;
      val `targetBytes$iv`: ByteString = targetBytes;
      var `fromIndex$iv`: Long = fromIndex;
      if (this.closed) {
         throw new IllegalStateException("closed".toString());
      } else {
         var var10000: Long;
         while (true) {
            val `result$iv`: Long = `$this$commonIndexOfElement$iv`.bufferField.indexOfElement(`targetBytes$iv`, `fromIndex$iv`);
            if (`result$iv` != -1L) {
               var10000 = `result$iv`;
               break;
            }

            val `lastBufferSize$iv`: Long = `$this$commonIndexOfElement$iv`.bufferField.size();
            if (`$this$commonIndexOfElement$iv`.source.read(`$this$commonIndexOfElement$iv`.bufferField, 8192L) == -1L) {
               var10000 = -1L;
               break;
            }

            `fromIndex$iv` = Math.max(`fromIndex$iv`, `lastBufferSize$iv`);
         }

         return var10000;
      }
   }

   public override fun rangeEquals(offset: Long, bytes: ByteString): Boolean {
      return this.rangeEquals(offset, bytes, 0, bytes.size());
   }

   public override fun rangeEquals(offset: Long, bytes: ByteString, bytesOffset: Int, byteCount: Int): Boolean {
      if (this.closed) {
         throw new IllegalStateException("closed".toString());
      } else {
         return byteCount >= 0
            && offset >= 0L
            && bytesOffset >= 0
            && bytesOffset + byteCount <= bytes.size()
            && (byteCount == 0 || -RealBufferedSource.commonIndexOf(this, bytes, bytesOffset, byteCount, offset, offset + 1L) != -1L);
      }
   }

   public override fun peek(): BufferedSource {
      return Okio.buffer(new PeekSource(this));
   }

   public override fun inputStream(): InputStream {
      return new 1(this);
   }

   public override fun isOpen(): Boolean {
      return !this.closed;
   }

   public override fun close() {
      if (!this.closed) {
         this.closed = true;
         this.source.close();
         this.bufferField.clear();
      }
   }

   public override fun timeout(): Timeout {
      return this.source.timeout();
   }

   public override fun toString(): String {
      return "buffer(${this.source})";
   }
}
