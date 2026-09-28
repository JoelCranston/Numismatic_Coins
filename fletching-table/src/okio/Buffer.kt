package okio

import java.io.Closeable
import java.io.EOFException
import java.io.InputStream
import java.io.OutputStream
import java.nio.ByteBuffer
import java.nio.channels.ByteChannel
import java.nio.charset.Charset
import java.security.InvalidKeyException
import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.jvm.internal.SourceDebugExtension
import okio.Buffer.outputStream.1
import okio.internal.-Buffer

@SourceDebugExtension(["SMAP\nBuffer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Buffer.kt\nokio/Buffer\n+ 2 Util.kt\nokio/-SegmentedByteString\n+ 3 Buffer.kt\nokio/internal/-Buffer\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 BufferedSource.kt\nokio/internal/-BufferedSource\n*L\n1#1,649:1\n88#2:650\n85#2:683\n85#2:685\n73#2:745\n73#2:771\n82#2:810\n76#2:821\n88#2:1014\n73#2:1029\n85#2:1133\n242#3,32:651\n277#3,10:686\n290#3,18:696\n412#3,2:714\n110#3:716\n414#3:717\n112#3,18:718\n311#3,9:736\n320#3,15:746\n338#3,10:761\n348#3,3:772\n346#3,25:775\n374#3,10:800\n384#3:811\n382#3,9:812\n391#3,7:822\n389#3,20:829\n652#3,60:849\n715#3,56:909\n773#3:965\n776#3:966\n777#3,6:968\n787#3,7:974\n797#3,6:984\n805#3,5:990\n837#3,6:995\n847#3:1001\n848#3,11:1003\n859#3,5:1015\n868#3,9:1020\n878#3,61:1030\n603#3:1091\n606#3:1092\n607#3,5:1094\n614#3:1099\n617#3,7:1100\n626#3,20:1107\n418#3:1127\n421#3,5:1128\n426#3,10:1134\n437#3,7:1144\n442#3,2:1151\n943#3:1153\n944#3,87:1155\n1034#3,48:1242\n573#3:1290\n580#3,21:1291\n1085#3,7:1312\n1095#3,7:1319\n1105#3,4:1326\n1112#3,8:1330\n1123#3,10:1338\n1136#3,14:1348\n447#3,35:1362\n513#3,40:1397\n556#3:1437\n558#3,13:1439\n1153#3:1452\n1204#3:1453\n1205#3,39:1455\n1246#3,2:1494\n1248#3,4:1497\n1255#3,3:1501\n1259#3,4:1505\n110#3:1509\n1263#3,22:1510\n112#3,18:1532\n1338#3,2:1550\n1341#3:1553\n110#3:1554\n1342#3,50:1555\n112#3,18:1605\n1401#3,12:1623\n1416#3,32:1635\n1451#3,12:1667\n1466#3,18:1679\n1488#3:1697\n1489#3:1699\n1494#3,34:1700\n1#4:684\n1#4:967\n1#4:1002\n1#4:1093\n1#4:1154\n1#4:1438\n1#4:1454\n1#4:1496\n1#4:1504\n1#4:1552\n1#4:1698\n26#5,3:981\n*S KotlinDebug\n*F\n+ 1 Buffer.kt\nokio/Buffer\n*L\n167#1:650\n197#1:683\n235#1:685\n261#1:745\n264#1:771\n267#1:810\n267#1:821\n337#1:1014\n340#1:1029\n376#1:1133\n181#1:651,32\n252#1:686,10\n255#1:696,18\n258#1:714,2\n258#1:716\n258#1:717\n258#1:718,18\n261#1:736,9\n261#1:746,15\n264#1:761,10\n264#1:772,3\n264#1:775,25\n267#1:800,10\n267#1:811\n267#1:812,9\n267#1:822,7\n267#1:829,20\n279#1:849,60\n282#1:909,56\n284#1:965\n287#1:966\n287#1:968,6\n289#1:974,7\n294#1:984,6\n297#1:990,5\n331#1:995,6\n337#1:1001\n337#1:1003,11\n337#1:1015,5\n340#1:1020,9\n340#1:1030,61\n342#1:1091\n345#1:1092\n345#1:1094,5\n347#1:1099\n350#1:1100,7\n353#1:1107,20\n373#1:1127\n376#1:1128,5\n376#1:1134,10\n378#1:1144,7\n381#1:1151,2\n386#1:1153\n386#1:1155,87\n389#1:1242,48\n412#1:1290\n418#1:1291,21\n439#1:1312,7\n443#1:1319,7\n445#1:1326,4\n447#1:1330,8\n451#1:1338,10\n455#1:1348,14\n459#1:1362,35\n462#1:1397,40\n465#1:1437\n465#1:1439,13\n467#1:1452\n467#1:1453\n467#1:1455,39\n469#1:1494,2\n469#1:1497,4\n480#1:1501,3\n480#1:1505,4\n480#1:1509\n480#1:1510,22\n480#1:1532,18\n496#1:1550,2\n496#1:1553\n496#1:1554\n496#1:1555,50\n496#1:1605,18\n506#1:1623,12\n576#1:1635,32\n578#1:1667,12\n586#1:1679,18\n594#1:1697\n594#1:1699\n596#1:1700,34\n287#1:967\n337#1:1002\n345#1:1093\n386#1:1154\n465#1:1438\n467#1:1454\n469#1:1496\n480#1:1504\n496#1:1552\n594#1:1698\n291#1:981,3\n*E\n"])
public class Buffer : BufferedSource, BufferedSink, Cloneable, ByteChannel {
   internal final var head: Segment?
      private set

   public final var size: Long
      public final set(value) {
         this.size = var1;
      }


   public open val buffer: Buffer
      public open get() {
         return this;
      }


   public override fun buffer(): Buffer {
      return this;
   }

   public override fun outputStream(): OutputStream {
      return new 1(this);
   }

   public open fun emitCompleteSegments(): Buffer {
      return this;
   }

   public open fun emit(): Buffer {
      return this;
   }

   public override fun exhausted(): Boolean {
      return this.size == 0L;
   }

   @Throws(java/io/EOFException::class)
   public override fun require(byteCount: Long) {
      if (this.size < byteCount) {
         throw new EOFException();
      }
   }

   public override fun request(byteCount: Long): Boolean {
      return this.size >= byteCount;
   }

   public override fun peek(): BufferedSource {
      return Okio.buffer(new PeekSource(this));
   }

   public override fun inputStream(): InputStream {
      return new okio.Buffer.inputStream.1(this);
   }

   @JvmOverloads
   @Throws(java/io/IOException::class)
   public fun copyTo(out: OutputStream, offset: Long = 0L, byteCount: Long = this.size - offset): Buffer {
      var offsetx: Long = offset;
      var byteCountx: Long = byteCount;
      -SegmentedByteString.checkOffsetAndCount(this.size, offset, byteCount);
      if (byteCount == 0L) {
         return this;
      } else {
         var s: Segment = this.head;

         while (true) {
            if (offsetx < s.limit - s.pos) {
               while (byteCountx > 0L) {
                  val pos: Int = (int)(s.pos + offsetx);
                  val toCopy: Int = (int)Math.min((long)(s.limit - (int)((long)s.pos + offsetx)), byteCountx);
                  out.write(s.data, pos, toCopy);
                  byteCountx -= toCopy;
                  offsetx = 0L;
                  s = s.next;
               }

               return this;
            }

            offsetx -= s.limit - s.pos;
            s = s.next;
         }
      }
   }

   public fun copyTo(out: Buffer, offset: Long = 0L, byteCount: Long): Buffer {
      val `out$iv`: Buffer = out;
      var `offset$iv`: Long = offset;
      var `byteCount$iv`: Long = byteCount;
      -SegmentedByteString.checkOffsetAndCount(this.size(), offset, byteCount);
      val var10000: Buffer;
      if (byteCount == 0L) {
         var10000 = this;
      } else {
         out.setSize$okio(out.size() + byteCount);
         var `s$iv`: Segment = this.head;

         while (true) {
            if (`offset$iv` < `s$iv`.limit - `s$iv`.pos) {
               while (byteCount$iv > 0L) {
                  val `copy$iv`: Segment = `s$iv`.sharedCopy();
                  `copy$iv`.pos += (int)`offset$iv`;
                  `copy$iv`.limit = Math.min(`copy$iv`.pos + (int)`byteCount$iv`, `copy$iv`.limit);
                  if (`out$iv`.head == null) {
                     `copy$iv`.prev = `copy$iv`;
                     `copy$iv`.next = `copy$iv`.prev;
                     `out$iv`.head = `copy$iv`.next;
                  } else {
                     val var19: Segment = `out$iv`.head;
                     val var20: Segment = var19.prev;
                     var20.push(`copy$iv`);
                  }

                  `byteCount$iv` -= `copy$iv`.limit - `copy$iv`.pos;
                  `offset$iv` = 0L;
                  `s$iv` = `s$iv`.next;
               }

               var10000 = this;
               break;
            }

            `offset$iv` -= `s$iv`.limit - `s$iv`.pos;
            `s$iv` = `s$iv`.next;
         }
      }

      return var10000;
   }

   public fun copyTo(out: Buffer, offset: Long = 0L): Buffer {
      return this.copyTo(out, offset, this.size - offset);
   }

   @JvmOverloads
   @Throws(java/io/IOException::class)
   public fun writeTo(out: OutputStream, byteCount: Long = this.size): Buffer {
      var byteCountx: Long = byteCount;
      -SegmentedByteString.checkOffsetAndCount(this.size, 0L, byteCount);
      var s: Segment = this.head;

      while (byteCountx > 0L) {
         val toCopy: Int = (int)Math.min(byteCountx, (long)(s.limit - s.pos));
         out.write(s.data, s.pos, toCopy);
         s.pos += toCopy;
         this.size -= toCopy;
         byteCountx -= toCopy;
         if (s.pos == s.limit) {
            val toRecycle: Segment = s;
            s = s.pop();
            this.head = s;
            SegmentPool.recycle(toRecycle);
         }
      }

      return this;
   }

   @Throws(java/io/IOException::class)
   public fun readFrom(input: InputStream): Buffer {
      this.readFrom(input, java.lang.Long.MAX_VALUE, true);
      return this;
   }

   @Throws(java/io/IOException::class)
   public fun readFrom(input: InputStream, byteCount: Long): Buffer {
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
      } else {
         this.readFrom(input, byteCount, false);
         return this;
      }
   }

   @Throws(java/io/IOException::class)
   private fun readFrom(input: InputStream, byteCount: Long, forever: Boolean) {
      var byteCountx: Long = byteCount;

      while (byteCountx > 0L || forever) {
         val tail: Segment = this.writableSegment$okio(1);
         val bytesRead: Int = input.read(tail.data, tail.limit, (int)Math.min(byteCountx, (long)(8192 - tail.limit)));
         if (bytesRead == -1) {
            if (tail.pos == tail.limit) {
               this.head = tail.pop();
               SegmentPool.recycle(tail);
            }

            if (forever) {
               return;
            }

            throw new EOFException();
         }

         tail.limit += bytesRead;
         this.size += bytesRead;
         byteCountx -= bytesRead;
      }
   }

   public fun completeSegmentByteCount(): Long {
      var `result$iv`: Long = this.size();
      val var10000: Long;
      if (`result$iv` == 0L) {
         var10000 = 0L;
      } else {
         val var6: Segment = this.head;
         val var7: Segment = var6.prev;
         if (var7.limit < 8192 && var7.owner) {
            `result$iv` -= var7.limit - var7.pos;
         }

         var10000 = `result$iv`;
      }

      return var10000;
   }

   @Throws(java/io/EOFException::class)
   public override fun readByte(): Byte {
      if (this.size() == 0L) {
         throw new EOFException();
      } else {
         val var10000: Segment = this.head;
         val `limit$iv`: Int = var10000.limit;
         val `b$iv`: Byte = var10000.data[var10000.pos++];
         this.setSize$okio(this.size() - 1L);
         val `pos$iv`: Int;
         if (`pos$iv` == `limit$iv`) {
            this.head = var10000.pop();
            SegmentPool.recycle(var10000);
         } else {
            var10000.pos = `pos$iv`;
         }

         return `b$iv`;
      }
   }

   @JvmName(name = "getByte")
   public operator fun get(pos: Long): Byte {
      -SegmentedByteString.checkOffsetAndCount(this.size(), pos, 1L);
      val `fromIndex$iv$iv`: Long = pos;
      val var10000: Byte;
      if (this.head == null) {
         var10000 = ((Segment)null).data[(int)(((Segment)null).pos + pos - -1L)];
      } else {
         var `s$iv$iv`: Segment = this.head;
         if (this.size() - pos < pos) {
            var `offset$iv$iv`: Long;
            for (offset$iv$iv = this.size(); offset$iv$iv > fromIndex$iv$iv; offset$iv$iv -= var27.limit - var27.pos) {
               var27 = `s$iv$iv`.prev;
               `s$iv$iv` = var27;
            }

            var10000 = `s$iv$iv`.data[(int)(`s$iv$iv`.pos + pos - `offset$iv$iv`)];
         } else {
            var var26: Long = 0L;

            while (true) {
               val var25: Long = var26 + (`s$iv$iv`.limit - `s$iv$iv`.pos);
               if (var26 + (`s$iv$iv`.limit - `s$iv$iv`.pos) > `fromIndex$iv$iv`) {
                  var10000 = `s$iv$iv`.data[(int)(`s$iv$iv`.pos + pos - var26)];
                  break;
               }

               val var28: Segment = `s$iv$iv`.next;
               `s$iv$iv` = var28;
               var26 = var25;
            }
         }
      }

      return var10000;
   }

   @Throws(java/io/EOFException::class)
   public override fun readShort(): Short {
      if (this.size() < 2L) {
         throw new EOFException();
      } else {
         val var10000: Segment = this.head;
         val `limit$iv`: Int = var10000.limit;
         val var24: Short;
         if (var10000.limit - var10000.pos < 2) {
            var24 = (short)((this.readByte() and 255) shl 8 or this.readByte() and 255);
         } else {
            val `pos$iv`: Int;
            val var14: Int = (var10000.data[var10000.pos++] and 255) shl 8 or var10000.data[`pos$iv`++] and 255;
            this.setSize$okio(this.size() - 2L);
            if (`pos$iv` == `limit$iv`) {
               this.head = var10000.pop();
               SegmentPool.recycle(var10000);
            } else {
               var10000.pos = `pos$iv`;
            }

            var24 = (short)var14;
         }

         return var24;
      }
   }

   @Throws(java/io/EOFException::class)
   public override fun readInt(): Int {
      if (this.size() < 4L) {
         throw new EOFException();
      } else {
         val var10000: Segment = this.head;
         val `limit$iv`: Int = var10000.limit;
         val var39: Int;
         if (var10000.limit - var10000.pos < 4L) {
            var39 = (this.readByte() and 255) shl 24 or (this.readByte() and 255) shl 16 or (this.readByte() and 255) shl 8 or this.readByte() and 255;
         } else {
            var `pos$iv`: Int;
            val var22: Int = (var10000.data[var10000.pos++] and 255) shl 24 or (var10000.data[`pos$iv`++] and 255) shl 16 or (var10000.data[`pos$iv`++] and 255) shl 8 or var10000.data[`pos$iv`++] and 255;
            this.setSize$okio(this.size() - 4L);
            if (`pos$iv` == `limit$iv`) {
               this.head = var10000.pop();
               SegmentPool.recycle(var10000);
            } else {
               var10000.pos = `pos$iv`;
            }

            var39 = var22;
         }

         return var39;
      }
   }

   @Throws(java/io/EOFException::class)
   public override fun readLong(): Long {
      if (this.size() < 8L) {
         throw new EOFException();
      } else {
         val var10000: Segment = this.head;
         val `limit$iv`: Int = var10000.limit;
         val var49: Long;
         if (var10000.limit - var10000.pos < 8L) {
            var49 = (this.readInt() and 4294967295L) shl 32 or this.readInt() and 4294967295L;
         } else {
            var `pos$iv`: Int;
            val var24: Long = (var10000.data[var10000.pos++] and 255L) shl 56 or (var10000.data[`pos$iv`++] and 255L) shl 48 or (
               var10000.data[`pos$iv`++] and 255L
            ) shl 40 or (var10000.data[`pos$iv`++] and 255L) shl 32 or (var10000.data[`pos$iv`++] and 255L) shl 24 or (var10000.data[`pos$iv`++] and 255L) shl 16 or (
               var10000.data[`pos$iv`++] and 255L
            ) shl 8 or var10000.data[`pos$iv`++] and 255L;
            this.setSize$okio(this.size() - 8L);
            if (`pos$iv` == `limit$iv`) {
               this.head = var10000.pop();
               SegmentPool.recycle(var10000);
            } else {
               var10000.pos = `pos$iv`;
            }

            var49 = var24;
         }

         return var49;
      }
   }

   @Throws(java/io/EOFException::class)
   public override fun readShortLe(): Short {
      return -SegmentedByteString.reverseBytes(this.readShort());
   }

   @Throws(java/io/EOFException::class)
   public override fun readIntLe(): Int {
      return -SegmentedByteString.reverseBytes(this.readInt());
   }

   @Throws(java/io/EOFException::class)
   public override fun readLongLe(): Long {
      return -SegmentedByteString.reverseBytes(this.readLong());
   }

   @Throws(java/io/EOFException::class)
   public override fun readDecimalLong(): Long {
      val `$this$commonReadDecimalLong$iv`: Buffer = this;
      if (this.size() == 0L) {
         throw new EOFException();
      } else {
         var `value$iv`: Long = 0L;
         var `seen$iv`: Int = 0;
         var `negative$iv`: Boolean = false;
         var `done$iv`: Boolean = false;
         var `overflowDigit$iv`: Long = -7L;

         while (true) {
            val var10000: Segment = `$this$commonReadDecimalLong$iv`.head;
            val `expected$iv`: ByteArray = var10000.data;
            var `pos$iv`: Int = var10000.pos;
            val `limit$iv`: Int = var10000.limit;

            while (true) {
               label86: {
                  if (`pos$iv` < `limit$iv`) {
                     val `b$iv`: Byte = `expected$iv`[`pos$iv`];
                     if (`expected$iv`[`pos$iv`] >= 48 && `expected$iv`[`pos$iv`] <= 57) {
                        val `digit$iv`: Int = 48 - `b$iv`;
                        if (`value$iv` < -922337203685477580L || `value$iv` == -922337203685477580L && 48 - `b$iv` < `overflowDigit$iv`) {
                           val `buffer$iv`: Buffer = new Buffer().writeDecimalLong(`value$iv`).writeByte(`b$iv`);
                           if (!`negative$iv`) {
                              `buffer$iv`.readByte();
                           }

                           throw new NumberFormatException("Number too large: ${`buffer$iv`.readUtf8()}");
                        }

                        `value$iv` = `value$iv` * 10L + `digit$iv`;
                        break label86;
                     }

                     if (`b$iv` == 45 && `seen$iv` == 0) {
                        `negative$iv` = true;
                        `overflowDigit$iv`--;
                        break label86;
                     }

                     `done$iv` = true;
                  }

                  if (`pos$iv` == `limit$iv`) {
                     `$this$commonReadDecimalLong$iv`.head = var10000.pop();
                     SegmentPool.recycle(var10000);
                  } else {
                     var10000.pos = `pos$iv`;
                  }

                  if (!`done$iv` && `$this$commonReadDecimalLong$iv`.head != null) {
                     break;
                  }

                  `$this$commonReadDecimalLong$iv`.setSize$okio(`$this$commonReadDecimalLong$iv`.size() - (long)`seen$iv`);
                  if (`seen$iv` < (if (`negative$iv`) 2 else 1)) {
                     if (`$this$commonReadDecimalLong$iv`.size() == 0L) {
                        throw new EOFException();
                     }

                     throw new NumberFormatException(
                        "${if (`negative$iv`) "Expected a digit" else "Expected a digit or '-'"} but was 0x${-SegmentedByteString.toHexString(
                           `$this$commonReadDecimalLong$iv`.getByte(0L)
                        )}"
                     );
                  }

                  return if (`negative$iv`) `value$iv` else -`value$iv`;
               }

               `pos$iv`++;
               `seen$iv`++;
            }
         }
      }
   }

   @Throws(java/io/EOFException::class)
   public override fun readHexadecimalUnsignedLong(): Long {
      val `$this$commonReadHexadecimalUnsignedLong$iv`: Buffer = this;
      if (this.size() == 0L) {
         throw new EOFException();
      } else {
         var `value$iv`: Long = 0L;
         var `seen$iv`: Int = 0;
         var `done$iv`: Boolean = false;

         do {
            val var10000: Segment = `$this$commonReadHexadecimalUnsignedLong$iv`.head;
            val `data$iv`: ByteArray = var10000.data;
            var `pos$iv`: Int = var10000.pos;

            val `limit$iv`: Int;
            for (limit$iv = var10000.limit; pos$iv < limit$iv; seen$iv++) {
               val `b$iv`: Byte = `data$iv`[`pos$iv`];
               val var15: Int;
               if (`data$iv`[`pos$iv`] >= 48 && `data$iv`[`pos$iv`] <= 57) {
                  var15 = `b$iv` - 48;
               } else if (`b$iv` >= 97 && `b$iv` <= 102) {
                  var15 = `b$iv` - 97 + 10;
               } else {
                  if (`b$iv` < 65 || `b$iv` > 70) {
                     if (`seen$iv` == 0) {
                        throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x${-SegmentedByteString.toHexString(`b$iv`)}");
                     }

                     `done$iv` = true;
                     break;
                  }

                  var15 = `b$iv` - 65 + 10;
               }

               if ((`value$iv` and -1152921504606846976L) != 0L) {
                  throw new NumberFormatException("Number too large: ${new Buffer().writeHexadecimalUnsignedLong(`value$iv`).writeByte(`b$iv`).readUtf8()}");
               }

               `value$iv` = `value$iv` shl 4 or var15;
               `pos$iv`++;
            }

            if (`pos$iv` == `limit$iv`) {
               `$this$commonReadHexadecimalUnsignedLong$iv`.head = var10000.pop();
               SegmentPool.recycle(var10000);
            } else {
               var10000.pos = `pos$iv`;
            }
         } while (!done$iv && $this$commonReadHexadecimalUnsignedLong$iv.head != null);

         `$this$commonReadHexadecimalUnsignedLong$iv`.setSize$okio(`$this$commonReadHexadecimalUnsignedLong$iv`.size() - (long)`seen$iv`);
         return `value$iv`;
      }
   }

   public override fun readByteString(): ByteString {
      return this.readByteString(this.size());
   }

   @Throws(java/io/EOFException::class)
   public override fun readByteString(byteCount: Long): ByteString {
      if (byteCount < 0L || byteCount > 2147483647L) {
         throw new IllegalArgumentException(("byteCount: $byteCount").toString());
      } else if (this.size() < byteCount) {
         throw new EOFException();
      } else {
         val var10000: ByteString;
         if (byteCount >= 4096L) {
            val var8: ByteString = this.snapshot((int)byteCount);
            this.skip(byteCount);
            var10000 = var8;
         } else {
            var10000 = new ByteString(this.readByteArray(byteCount));
         }

         return var10000;
      }
   }

   public override fun select(options: Options): Int {
      val `index$iv`: Int = -Buffer.selectPrefix$default(this, options, false, 2, null);
      val var10000: Int;
      if (`index$iv` == -1) {
         var10000 = -1;
      } else {
         this.skip((long)options.getByteStrings$okio()[`index$iv`].size());
         var10000 = `index$iv`;
      }

      return var10000;
   }

   public override fun <T : Any> select(options: TypedOptions<T>): T? {
      val `index$iv`: Int = this.select(options.getOptions$okio());
      return (T)(if (`index$iv` == -1) null else options.get(`index$iv`));
   }

   @Throws(java/io/EOFException::class)
   public override fun readFully(sink: Buffer, byteCount: Long) {
      if (this.size() < byteCount) {
         sink.write(this, this.size());
         throw new EOFException();
      } else {
         sink.write(this, byteCount);
      }
   }

   @Throws(java/io/IOException::class)
   public override fun readAll(sink: Sink): Long {
      val `byteCount$iv`: Long = this.size();
      if (`byteCount$iv` > 0L) {
         sink.write(this, `byteCount$iv`);
      }

      return `byteCount$iv`;
   }

   public override fun readUtf8(): String {
      return this.readString(this.size, Charsets.UTF_8);
   }

   @Throws(java/io/EOFException::class)
   public override fun readUtf8(byteCount: Long): String {
      return this.readString(byteCount, Charsets.UTF_8);
   }

   public override fun readString(charset: Charset): String {
      return this.readString(this.size, charset);
   }

   @Throws(java/io/EOFException::class)
   public override fun readString(byteCount: Long, charset: Charset): String {
      if (byteCount < 0L || byteCount > 2147483647L) {
         throw new IllegalArgumentException(("byteCount: $byteCount").toString());
      } else if (this.size < byteCount) {
         throw new EOFException();
      } else if (byteCount == 0L) {
         return "";
      } else {
         val var10000: Segment = this.head;
         if (var10000.pos + byteCount > var10000.limit) {
            return new java.lang.String(this.readByteArray(byteCount), charset);
         } else {
            val result: java.lang.String = new java.lang.String(var10000.data, var10000.pos, (int)byteCount, charset);
            var10000.pos += (int)byteCount;
            this.size -= byteCount;
            if (var10000.pos == var10000.limit) {
               this.head = var10000.pop();
               SegmentPool.recycle(var10000);
            }

            return result;
         }
      }
   }

   @Throws(java/io/EOFException::class)
   public override fun readUtf8Line(): String? {
      val `newline$iv`: Long = this.indexOf((byte)10);
      return if (`newline$iv` != -1L) -Buffer.readUtf8Line(this, `newline$iv`) else (if (this.size() != 0L) this.readUtf8(this.size()) else null);
   }

   @Throws(java/io/EOFException::class)
   public override fun readUtf8LineStrict(): String {
      return this.readUtf8LineStrict(java.lang.Long.MAX_VALUE);
   }

   @Throws(java/io/EOFException::class)
   public override fun readUtf8LineStrict(limit: Long): String {
      if (limit < 0L) {
         throw new IllegalArgumentException(("limit < 0: $limit").toString());
      } else {
         val `scanLength$iv`: Long = if (limit == java.lang.Long.MAX_VALUE) java.lang.Long.MAX_VALUE else limit + 1L;
         val `newline$iv`: Long = this.indexOf((byte)10, 0L, if (limit == java.lang.Long.MAX_VALUE) java.lang.Long.MAX_VALUE else limit + 1L);
         val var10000: java.lang.String;
         if (`newline$iv` != -1L) {
            var10000 = -Buffer.readUtf8Line(this, `newline$iv`);
         } else {
            if (`scanLength$iv` >= this.size() || this.getByte(`scanLength$iv` - 1L) != 13 || this.getByte(`scanLength$iv`) != 10) {
               val `data$iv`: Buffer = new Buffer();
               this.copyTo(`data$iv`, 0L, Math.min((long)32, this.size()));
               throw new EOFException("\\n not found: limit=${Math.min(this.size(), limit)} content=${`data$iv`.readByteString().hex()}…");
            }

            var10000 = -Buffer.readUtf8Line(this, `scanLength$iv`);
         }

         return var10000;
      }
   }

   @Throws(java/io/EOFException::class)
   public override fun readUtf8CodePoint(): Int {
      val `$this$commonReadUtf8CodePoint$iv`: Buffer = this;
      if (this.size() == 0L) {
         throw new EOFException();
      } else {
         val `b0$iv`: Byte = this.getByte(0L);
         var var12: Int;
         val var14: Byte;
         val var15: Int;
         if ((`b0$iv` and 128) == 0) {
            var12 = `b0$iv` and 127;
            var14 = 1;
            var15 = 0;
         } else if ((`b0$iv` and 224) == 192) {
            var12 = `b0$iv` and 31;
            var14 = 2;
            var15 = 128;
         } else if ((`b0$iv` and 240) == 224) {
            var12 = `b0$iv` and 15;
            var14 = 3;
            var15 = 2048;
         } else {
            if ((`b0$iv` and 248) != 240) {
               this.skip(1L);
               return 65533;
            }

            var12 = `b0$iv` and 7;
            var14 = 4;
            var15 = 65536;
         }

         if (this.size() < var14) {
            throw new EOFException("size < $var14: ${this.size()} (to read code point prefixed 0x${-SegmentedByteString.toHexString(`b0$iv`)}${41}");
         } else {
            var `i$iv`: Int = 1;

            var var10000: Int;
            while (true) {
               if (`i$iv` >= var14) {
                  `$this$commonReadUtf8CodePoint$iv`.skip((long)var14);
                  var10000 = if (var12 > 1114111) '�' else (if (55296 <= var12 && var12 < 57344) '�' else (if (var12 < var15) '�' else var12));
                  break;
               }

               val var23: Byte = `$this$commonReadUtf8CodePoint$iv`.getByte((long)`i$iv`);
               if ((var23 and 192) != 128) {
                  `$this$commonReadUtf8CodePoint$iv`.skip((long)`i$iv`);
                  var10000 = 65533;
                  break;
               }

               var12 = var12 shl 6 or var23 and 63;
               `i$iv`++;
            }

            return var10000;
         }
      }
   }

   public override fun readByteArray(): ByteArray {
      return this.readByteArray(this.size());
   }

   @Throws(java/io/EOFException::class)
   public override fun readByteArray(byteCount: Long): ByteArray {
      if (byteCount < 0L || byteCount > 2147483647L) {
         throw new IllegalArgumentException(("byteCount: $byteCount").toString());
      } else if (this.size() < byteCount) {
         throw new EOFException();
      } else {
         val `result$iv`: ByteArray = new byte[(int)byteCount];
         this.readFully(`result$iv`);
         return `result$iv`;
      }
   }

   public override fun read(sink: ByteArray): Int {
      return this.read(sink, 0, sink.length);
   }

   @Throws(java/io/EOFException::class)
   public override fun readFully(sink: ByteArray) {
      val `$this$commonReadFully$iv`: Buffer = this;
      val `sink$iv`: ByteArray = sink;
      var `offset$iv`: Int = 0;

      while (offset$iv < sink$iv.length) {
         val `read$iv`: Int = `$this$commonReadFully$iv`.read(`sink$iv`, `offset$iv`, `sink$iv`.length - `offset$iv`);
         if (`read$iv` == -1) {
            throw new EOFException();
         }

         `offset$iv` += `read$iv`;
      }
   }

   public override fun read(sink: ByteArray, offset: Int, byteCount: Int): Int {
      -SegmentedByteString.checkOffsetAndCount((long)sink.length, (long)offset, (long)byteCount);
      val var10000: Int;
      if (this.head == null) {
         var10000 = -1;
      } else {
         val `s$iv`: Segment = this.head;
         val `toCopy$iv`: Int = Math.min(byteCount, this.head.limit - this.head.pos);
         ArraysKt.copyInto(`s$iv`.data, sink, offset, `s$iv`.pos, `s$iv`.pos + `toCopy$iv`);
         `s$iv`.pos += `toCopy$iv`;
         this.setSize$okio(this.size() - (long)`toCopy$iv`);
         if (`s$iv`.pos == `s$iv`.limit) {
            this.head = `s$iv`.pop();
            SegmentPool.recycle(`s$iv`);
         }

         var10000 = `toCopy$iv`;
      }

      return var10000;
   }

   @Throws(java/io/IOException::class)
   public override fun read(sink: ByteBuffer): Int {
      if (this.head == null) {
         return -1;
      } else {
         val s: Segment = this.head;
         val toCopy: Int = Math.min(sink.remaining(), s.limit - s.pos);
         sink.put(s.data, s.pos, toCopy);
         s.pos += toCopy;
         this.size -= toCopy;
         if (s.pos == s.limit) {
            this.head = s.pop();
            SegmentPool.recycle(s);
         }

         return toCopy;
      }
   }

   public fun clear() {
      this.skip(this.size());
   }

   @Throws(java/io/EOFException::class)
   public override fun skip(byteCount: Long) {
      val `$this$commonSkip$iv`: Buffer = this;
      var `byteCount$iv`: Long = byteCount;

      while (byteCount$iv > 0L) {
         if (`$this$commonSkip$iv`.head == null) {
            throw new EOFException();
         }

         val `head$iv`: Segment = `$this$commonSkip$iv`.head;
         val `toSkip$iv`: Int = (int)Math.min(`byteCount$iv`, (long)(`$this$commonSkip$iv`.head.limit - `$this$commonSkip$iv`.head.pos));
         `$this$commonSkip$iv`.setSize$okio(`$this$commonSkip$iv`.size() - (long)`toSkip$iv`);
         `byteCount$iv` -= `toSkip$iv`;
         `head$iv`.pos += `toSkip$iv`;
         if (`head$iv`.pos == `head$iv`.limit) {
            `$this$commonSkip$iv`.head = `head$iv`.pop();
            SegmentPool.recycle(`head$iv`);
         }
      }
   }

   public open fun write(byteString: ByteString): Buffer {
      byteString.write$okio(this, 0, byteString.size());
      return this;
   }

   public open fun write(byteString: ByteString, offset: Int, byteCount: Int): Buffer {
      byteString.write$okio(this, offset, byteCount);
      return this;
   }

   public open fun writeUtf8(string: String): Buffer {
      return this.writeUtf8(string, 0, string.length());
   }

   public open fun writeUtf8(string: String, beginIndex: Int, endIndex: Int): Buffer {
      val `$this$commonWriteUtf8$iv`: Buffer = this;
      val `string$iv`: java.lang.String = string;
      val `endIndex$iv`: Int = endIndex;
      if (beginIndex < 0) {
         throw new IllegalArgumentException(("beginIndex < 0: $beginIndex").toString());
      } else if (endIndex < beginIndex) {
         throw new IllegalArgumentException(("endIndex < beginIndex: $endIndex < $beginIndex").toString());
      } else if (endIndex > string.length()) {
         throw new IllegalArgumentException(("endIndex > string.length: $endIndex > ${string.length()}").toString());
      } else {
         var `i$iv`: Int = beginIndex;

         while (i$iv < endIndex$iv) {
            val `c$iv`: Int = `string$iv`.charAt(`i$iv`);
            if (`c$iv` < 128) {
               val var25: Segment = `$this$commonWriteUtf8$iv`.writableSegment$okio(1);
               val var26: ByteArray = var25.data;
               val var27: Int = var25.limit - `i$iv`;
               val `runLimit$iv`: Int = Math.min(`endIndex$iv`, 8192 - (var25.limit - `i$iv`));
               var26[var27 + `i$iv`++] = (byte)`c$iv`;

               while (i$iv < runLimit$iv) {
                  val var16: Char = `string$iv`.charAt(`i$iv`);
                  if (var16 >= 128) {
                     break;
                  }

                  var26[var27 + `i$iv`++] = (byte)var16;
               }

               val `runSize$iv`: Int = `i$iv` + var27 - var25.limit;
               var25.limit = var25.limit + (`i$iv` + var27 - var25.limit);
               `$this$commonWriteUtf8$iv`.setSize$okio(`$this$commonWriteUtf8$iv`.size() + (long)`runSize$iv`);
            } else if (`c$iv` < 2048) {
               val var24: Segment = `$this$commonWriteUtf8$iv`.writableSegment$okio(2);
               var24.data[var24.limit] = (byte)(`c$iv` shr 6 or 192);
               var24.data[var24.limit + 1] = (byte)(`c$iv` and 63 or 128);
               var24.limit += 2;
               `$this$commonWriteUtf8$iv`.setSize$okio(`$this$commonWriteUtf8$iv`.size() + 2L);
               `i$iv`++;
            } else if (`c$iv` >= 55296 && `c$iv` <= 57343) {
               val var23: Int = if (`i$iv` + 1 < `endIndex$iv`) `string$iv`.charAt(`i$iv` + 1) else 0;
               if (`c$iv` <= 56319 && 56320 <= var23 && var23 < 57344) {
                  val `codePoint$iv`: Int = 65536 + ((`c$iv` and 1023) shl 10 or var23 and 1023);
                  val `tail$iv`: Segment = `$this$commonWriteUtf8$iv`.writableSegment$okio(4);
                  `tail$iv`.data[`tail$iv`.limit] = (byte)(`codePoint$iv` shr 18 or 240);
                  `tail$iv`.data[`tail$iv`.limit + 1] = (byte)(`codePoint$iv` shr 12 and 63 or 128);
                  `tail$iv`.data[`tail$iv`.limit + 2] = (byte)(`codePoint$iv` shr 6 and 63 or 128);
                  `tail$iv`.data[`tail$iv`.limit + 3] = (byte)(`codePoint$iv` and 63 or 128);
                  `tail$iv`.limit += 4;
                  `$this$commonWriteUtf8$iv`.setSize$okio(`$this$commonWriteUtf8$iv`.size() + 4L);
                  `i$iv` += 2;
               } else {
                  `$this$commonWriteUtf8$iv`.writeByte(63);
                  `i$iv`++;
               }
            } else {
               val `low$iv`: Segment = `$this$commonWriteUtf8$iv`.writableSegment$okio(3);
               `low$iv`.data[`low$iv`.limit] = (byte)(`c$iv` shr 12 or 224);
               `low$iv`.data[`low$iv`.limit + 1] = (byte)(`c$iv` shr 6 and 63 or 128);
               `low$iv`.data[`low$iv`.limit + 2] = (byte)(`c$iv` and 63 or 128);
               `low$iv`.limit += 3;
               `$this$commonWriteUtf8$iv`.setSize$okio(`$this$commonWriteUtf8$iv`.size() + 3L);
               `i$iv`++;
            }
         }

         return `$this$commonWriteUtf8$iv`;
      }
   }

   public open fun writeUtf8CodePoint(codePoint: Int): Buffer {
      if (codePoint < 128) {
         this.writeByte(codePoint);
      } else if (codePoint < 2048) {
         val `tail$iv`: Segment = this.writableSegment$okio(2);
         `tail$iv`.data[`tail$iv`.limit] = (byte)(codePoint shr 6 or 192);
         `tail$iv`.data[`tail$iv`.limit + 1] = (byte)(codePoint and 63 or 128);
         `tail$iv`.limit += 2;
         this.setSize$okio(this.size() + 2L);
      } else if (55296 <= codePoint && codePoint < 57344) {
         this.writeByte(63);
      } else if (codePoint < 65536) {
         val var6: Segment = this.writableSegment$okio(3);
         var6.data[var6.limit] = (byte)(codePoint shr 12 or 224);
         var6.data[var6.limit + 1] = (byte)(codePoint shr 6 and 63 or 128);
         var6.data[var6.limit + 2] = (byte)(codePoint and 63 or 128);
         var6.limit += 3;
         this.setSize$okio(this.size() + 3L);
      } else {
         if (codePoint > 1114111) {
            throw new IllegalArgumentException("Unexpected code point: 0x${-SegmentedByteString.toHexString(codePoint)}");
         }

         val var7: Segment = this.writableSegment$okio(4);
         var7.data[var7.limit] = (byte)(codePoint shr 18 or 240);
         var7.data[var7.limit + 1] = (byte)(codePoint shr 12 and 63 or 128);
         var7.data[var7.limit + 2] = (byte)(codePoint shr 6 and 63 or 128);
         var7.data[var7.limit + 3] = (byte)(codePoint and 63 or 128);
         var7.limit += 4;
         this.setSize$okio(this.size() + 4L);
      }

      return this;
   }

   public open fun writeString(string: String, charset: Charset): Buffer {
      return this.writeString(string, 0, string.length(), charset);
   }

   public open fun writeString(string: String, beginIndex: Int, endIndex: Int, charset: Charset): Buffer {
      if (beginIndex < 0) {
         throw new IllegalArgumentException(("beginIndex < 0: $beginIndex").toString());
      } else if (endIndex < beginIndex) {
         throw new IllegalArgumentException(("endIndex < beginIndex: $endIndex < $beginIndex").toString());
      } else if (endIndex > string.length()) {
         throw new IllegalArgumentException(("endIndex > string.length: $endIndex > ${string.length()}").toString());
      } else if (charset == Charsets.UTF_8) {
         return this.writeUtf8(string, beginIndex, endIndex);
      } else {
         val var10000: java.lang.String = string.substring(beginIndex, endIndex);
         val var12: ByteArray = var10000.getBytes(charset);
         return this.write(var12, 0, var12.length);
      }
   }

   public open fun write(source: ByteArray): Buffer {
      return this.write(source, 0, source.length);
   }

   public open fun write(source: ByteArray, offset: Int, byteCount: Int): Buffer {
      val `$this$commonWrite$iv`: Buffer = this;
      val `source$iv`: ByteArray = source;
      var `offset$iv`: Int = offset;
      -SegmentedByteString.checkOffsetAndCount((long)source.length, (long)offset, (long)byteCount);
      val `limit$iv`: Int = offset + byteCount;

      while (offset$iv < limit$iv) {
         val `tail$iv`: Segment = `$this$commonWrite$iv`.writableSegment$okio(1);
         val `toCopy$iv`: Int = Math.min(`limit$iv` - `offset$iv`, 8192 - `tail$iv`.limit);
         ArraysKt.copyInto(`source$iv`, `tail$iv`.data, `tail$iv`.limit, `offset$iv`, `offset$iv` + `toCopy$iv`);
         `offset$iv` += `toCopy$iv`;
         `tail$iv`.limit += `toCopy$iv`;
      }

      `$this$commonWrite$iv`.setSize$okio(`$this$commonWrite$iv`.size() + (long)byteCount);
      return `$this$commonWrite$iv`;
   }

   @Throws(java/io/IOException::class)
   public override fun write(source: ByteBuffer): Int {
      val byteCount: Int = source.remaining();
      var remaining: Int = byteCount;

      while (remaining > 0) {
         val tail: Segment = this.writableSegment$okio(1);
         val toCopy: Int = Math.min(remaining, 8192 - tail.limit);
         source.get(tail.data, tail.limit, toCopy);
         remaining -= toCopy;
         tail.limit += toCopy;
      }

      this.size += byteCount;
      return byteCount;
   }

   @Throws(java/io/IOException::class)
   public override fun writeAll(source: Source): Long {
      val `$this$commonWriteAll$iv`: Buffer = this;
      val `source$iv`: Source = source;
      var `totalBytesRead$iv`: Long = 0L;

      while (true) {
         val `readCount$iv`: Long = `source$iv`.read(`$this$commonWriteAll$iv`, 8192L);
         if (`readCount$iv` == -1L) {
            return `totalBytesRead$iv`;
         }

         `totalBytesRead$iv` += `readCount$iv`;
      }
   }

   @Throws(java/io/IOException::class)
   public open fun write(source: Source, byteCount: Long): Buffer {
      val `$this$commonWrite$iv`: Buffer = this;
      val `source$iv`: Source = source;
      var `byteCount$iv`: Long = byteCount;

      while (byteCount$iv > 0L) {
         val `read$iv`: Long = `source$iv`.read(`$this$commonWrite$iv`, `byteCount$iv`);
         if (`read$iv` == -1L) {
            throw new EOFException();
         }

         `byteCount$iv` -= `read$iv`;
      }

      return `$this$commonWrite$iv`;
   }

   public open fun writeByte(b: Int): Buffer {
      val `tail$iv`: Segment = this.writableSegment$okio(1);
      `tail$iv`.data[`tail$iv`.limit++] = (byte)b;
      this.setSize$okio(this.size() + 1L);
      return this;
   }

   public open fun writeShort(s: Int): Buffer {
      val `tail$iv`: Segment = this.writableSegment$okio(2);
      `tail$iv`.data[`tail$iv`.limit++] = (byte)(s ushr 8 and 255);
      val `limit$iv`: Int;
      `tail$iv`.data[`limit$iv`++] = (byte)(s and 255);
      `tail$iv`.limit = `limit$iv`;
      this.setSize$okio(this.size() + 2L);
      return this;
   }

   public open fun writeShortLe(s: Int): Buffer {
      return this.writeShort(-SegmentedByteString.reverseBytes((short)s));
   }

   public open fun writeInt(i: Int): Buffer {
      val `tail$iv`: Segment = this.writableSegment$okio(4);
      `tail$iv`.data[`tail$iv`.limit++] = (byte)(i ushr 24 and 255);
      var `limit$iv`: Int;
      `tail$iv`.data[`limit$iv`++] = (byte)(i ushr 16 and 255);
      `tail$iv`.data[`limit$iv`++] = (byte)(i ushr 8 and 255);
      `tail$iv`.data[`limit$iv`++] = (byte)(i and 255);
      `tail$iv`.limit = `limit$iv`;
      this.setSize$okio(this.size() + 4L);
      return this;
   }

   public open fun writeIntLe(i: Int): Buffer {
      return this.writeInt(-SegmentedByteString.reverseBytes(i));
   }

   public open fun writeLong(v: Long): Buffer {
      val `tail$iv`: Segment = this.writableSegment$okio(8);
      `tail$iv`.data[`tail$iv`.limit++] = (byte)(v ushr 56 and 255L);
      var `limit$iv`: Int;
      `tail$iv`.data[`limit$iv`++] = (byte)(v ushr 48 and 255L);
      `tail$iv`.data[`limit$iv`++] = (byte)(v ushr 40 and 255L);
      `tail$iv`.data[`limit$iv`++] = (byte)(v ushr 32 and 255L);
      `tail$iv`.data[`limit$iv`++] = (byte)(v ushr 24 and 255L);
      `tail$iv`.data[`limit$iv`++] = (byte)(v ushr 16 and 255L);
      `tail$iv`.data[`limit$iv`++] = (byte)(v ushr 8 and 255L);
      `tail$iv`.data[`limit$iv`++] = (byte)(v and 255L);
      `tail$iv`.limit = `limit$iv`;
      this.setSize$okio(this.size() + 8L);
      return this;
   }

   public open fun writeLongLe(v: Long): Buffer {
      return this.writeLong(-SegmentedByteString.reverseBytes(v));
   }

   public open fun writeDecimalLong(v: Long): Buffer {
      var `v$iv`: Long = v;
      val var10000: Buffer;
      if (v == 0L) {
         var10000 = this.writeByte(48);
      } else {
         var `negative$iv`: Boolean = false;
         if (v < 0L) {
            `v$iv` = -v;
            if (-v < 0L) {
               return this.writeUtf8("-9223372036854775808");
            }

            `negative$iv` = true;
         }

         var `width$iv`: Int = -Buffer.access$countDigitsIn(`v$iv`);
         if (`negative$iv`) {
            `width$iv`++;
         }

         val `tail$iv`: Segment = this.writableSegment$okio(`width$iv`);
         val `data$iv`: ByteArray = `tail$iv`.data;

         var `pos$iv`: Int;
         for (pos$iv = tail$iv.limit + width$iv; v$iv != 0L; v$iv /= 10) {
            `data$iv`[--`pos$iv`] = -Buffer.getHEX_DIGIT_BYTES()[(int)(`v$iv` % 10)];
         }

         if (`negative$iv`) {
            `data$iv`[--`pos$iv`] = 45;
         }

         `tail$iv`.limit += `width$iv`;
         this.setSize$okio(this.size() + (long)`width$iv`);
         var10000 = this;
      }

      return var10000;
   }

   public open fun writeHexadecimalUnsignedLong(v: Long): Buffer {
      var `v$iv`: Long = v;
      val var10000: Buffer;
      if (v == 0L) {
         var10000 = this.writeByte(48);
      } else {
         val `width$iv`: Int = (int)(
            (
                  (
                        (
                              (
                                    (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) ushr 4
                                 )
                                 + (
                                    (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       )
                                       - (
                                          (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          ) ushr 1 and 6148914691236517205L
                                       ) ushr 2 and 3689348814741910323L
                                 )
                                 + (
                                    (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       )
                                       - (
                                          (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          ) ushr 1 and 6148914691236517205L
                                       ) and 3689348814741910323L
                                 ) and 1085102592571150095L
                           )
                           + (
                              (
                                 (
                                       (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) ushr 4
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) ushr 2 and 3689348814741910323L
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) and 3689348814741910323L
                                    ) and 1085102592571150095L
                              ) ushr 8
                           )
                           + (
                              (
                                    (
                                          (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) ushr 2 and 3689348814741910323L
                                             )
                                             + (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) and 3689348814741910323L
                                             ) ushr 4
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) and 1085102592571150095L
                                 )
                                 + (
                                    (
                                       (
                                             (
                                                   (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      )
                                                      - (
                                                         (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         ) ushr 1 and 6148914691236517205L
                                                      ) ushr 2 and 3689348814741910323L
                                                )
                                                + (
                                                   (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      )
                                                      - (
                                                         (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         ) ushr 1 and 6148914691236517205L
                                                      ) and 3689348814741910323L
                                                ) ushr 4
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) and 1085102592571150095L
                                    ) ushr 8
                                 ) ushr 16
                           ) and 63L
                     )
                     + (
                        (
                              (
                                    (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) ushr 4
                                 )
                                 + (
                                    (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       )
                                       - (
                                          (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          ) ushr 1 and 6148914691236517205L
                                       ) ushr 2 and 3689348814741910323L
                                 )
                                 + (
                                    (
                                          v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                          ) ushr 8 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8
                                          ) ushr 16 or (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16
                                          ) ushr 32
                                       )
                                       - (
                                          (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          ) ushr 1 and 6148914691236517205L
                                       ) and 3689348814741910323L
                                 ) and 1085102592571150095L
                           )
                           + (
                              (
                                 (
                                       (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) ushr 4
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) ushr 2 and 3689348814741910323L
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) and 3689348814741910323L
                                    ) and 1085102592571150095L
                              ) ushr 8
                           )
                           + (
                              (
                                    (
                                          (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) ushr 2 and 3689348814741910323L
                                             )
                                             + (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) and 3689348814741910323L
                                             ) ushr 4
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) and 1085102592571150095L
                                 )
                                 + (
                                    (
                                       (
                                             (
                                                   (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      )
                                                      - (
                                                         (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         ) ushr 1 and 6148914691236517205L
                                                      ) ushr 2 and 3689348814741910323L
                                                )
                                                + (
                                                   (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      )
                                                      - (
                                                         (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         ) ushr 1 and 6148914691236517205L
                                                      ) and 3689348814741910323L
                                                ) ushr 4
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) and 1085102592571150095L
                                    ) ushr 8
                                 ) ushr 16
                           ) ushr 32 and 63L
                     )
                     + 3
               )
               / 4
         );
         val `tail$iv`: Segment = this.writableSegment$okio(
            (int)(
               (
                     (
                           (
                                 (
                                       (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) ushr 4
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) ushr 2 and 3689348814741910323L
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) and 3689348814741910323L
                                    ) and 1085102592571150095L
                              )
                              + (
                                 (
                                    (
                                          (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) ushr 2 and 3689348814741910323L
                                             )
                                             + (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) and 3689348814741910323L
                                             ) ushr 4
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) and 1085102592571150095L
                                 ) ushr 8
                              )
                              + (
                                 (
                                       (
                                             (
                                                   (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      )
                                                      - (
                                                         (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         ) ushr 1 and 6148914691236517205L
                                                      ) ushr 2 and 3689348814741910323L
                                                )
                                                + (
                                                   (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      )
                                                      - (
                                                         (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         ) ushr 1 and 6148914691236517205L
                                                      ) and 3689348814741910323L
                                                ) ushr 4
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) and 1085102592571150095L
                                    )
                                    + (
                                       (
                                          (
                                                (
                                                      (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         )
                                                         - (
                                                            (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                        v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                     ) ushr 8
                                                                  ) ushr 16
                                                               ) ushr 32
                                                            ) ushr 1 and 6148914691236517205L
                                                         ) ushr 2 and 3689348814741910323L
                                                   )
                                                   + (
                                                      (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         )
                                                         - (
                                                            (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                        v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                     ) ushr 8
                                                                  ) ushr 16
                                                               ) ushr 32
                                                            ) ushr 1 and 6148914691236517205L
                                                         ) and 3689348814741910323L
                                                   ) ushr 4
                                             )
                                             + (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) ushr 2 and 3689348814741910323L
                                             )
                                             + (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) and 3689348814741910323L
                                             ) and 1085102592571150095L
                                       ) ushr 8
                                    ) ushr 16
                              ) and 63L
                        )
                        + (
                           (
                                 (
                                       (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) ushr 4
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) ushr 2 and 3689348814741910323L
                                    )
                                    + (
                                       (
                                             v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                             ) ushr 8 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8
                                             ) ushr 16 or (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16
                                             ) ushr 32
                                          )
                                          - (
                                             (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             ) ushr 1 and 6148914691236517205L
                                          ) and 3689348814741910323L
                                    ) and 1085102592571150095L
                              )
                              + (
                                 (
                                    (
                                          (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) ushr 2 and 3689348814741910323L
                                             )
                                             + (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) and 3689348814741910323L
                                             ) ushr 4
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) ushr 2 and 3689348814741910323L
                                       )
                                       + (
                                          (
                                                v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                ) ushr 8 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8
                                                ) ushr 16 or (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16
                                                ) ushr 32
                                             )
                                             - (
                                                (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                ) ushr 1 and 6148914691236517205L
                                             ) and 3689348814741910323L
                                       ) and 1085102592571150095L
                                 ) ushr 8
                              )
                              + (
                                 (
                                       (
                                             (
                                                   (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      )
                                                      - (
                                                         (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         ) ushr 1 and 6148914691236517205L
                                                      ) ushr 2 and 3689348814741910323L
                                                )
                                                + (
                                                   (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      )
                                                      - (
                                                         (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         ) ushr 1 and 6148914691236517205L
                                                      ) and 3689348814741910323L
                                                ) ushr 4
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) ushr 2 and 3689348814741910323L
                                          )
                                          + (
                                             (
                                                   v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                   ) ushr 8 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8
                                                   ) ushr 16 or (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16
                                                   ) ushr 32
                                                )
                                                - (
                                                   (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   ) ushr 1 and 6148914691236517205L
                                                ) and 3689348814741910323L
                                          ) and 1085102592571150095L
                                    )
                                    + (
                                       (
                                          (
                                                (
                                                      (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         )
                                                         - (
                                                            (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                        v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                     ) ushr 8
                                                                  ) ushr 16
                                                               ) ushr 32
                                                            ) ushr 1 and 6148914691236517205L
                                                         ) ushr 2 and 3689348814741910323L
                                                   )
                                                   + (
                                                      (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16
                                                            ) ushr 32
                                                         )
                                                         - (
                                                            (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8
                                                               ) ushr 16 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                  ) ushr 8 or (
                                                                     v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                        v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                                     ) ushr 8
                                                                  ) ushr 16
                                                               ) ushr 32
                                                            ) ushr 1 and 6148914691236517205L
                                                         ) and 3689348814741910323L
                                                   ) ushr 4
                                             )
                                             + (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) ushr 2 and 3689348814741910323L
                                             )
                                             + (
                                                (
                                                      v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                      ) ushr 8 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8
                                                      ) ushr 16 or (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16
                                                      ) ushr 32
                                                   )
                                                   - (
                                                      (
                                                         v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                         ) ushr 8 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8
                                                         ) ushr 16 or (
                                                            v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                            ) ushr 8 or (
                                                               v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4 or (
                                                                  v or v ushr 1 or (v or v ushr 1) ushr 2 or (v or v ushr 1 or (v or v ushr 1) ushr 2) ushr 4
                                                               ) ushr 8
                                                            ) ushr 16
                                                         ) ushr 32
                                                      ) ushr 1 and 6148914691236517205L
                                                   ) and 3689348814741910323L
                                             ) and 1085102592571150095L
                                       ) ushr 8
                                    ) ushr 16
                              ) ushr 32 and 63L
                        )
                        + (long)3
                  )
                  / (long)4
            )
         );
         val `data$iv`: ByteArray = `tail$iv`.data;
         var `pos$iv`: Int = `tail$iv`.limit + `width$iv` - 1;

         for (int start$iv = tail$iv.limit; pos$iv >= start$iv; pos$iv--) {
            `data$iv`[`pos$iv`] = -Buffer.getHEX_DIGIT_BYTES()[(int)(`v$iv` and 15L)];
            `v$iv` >>>= 4;
         }

         `tail$iv`.limit += `width$iv`;
         this.setSize$okio(this.size() + (long)`width$iv`);
         var10000 = this;
      }

      return var10000;
   }

   internal fun writableSegment(minimumCapacity: Int): Segment {
      if (minimumCapacity < 1 || minimumCapacity > 8192) {
         throw new IllegalArgumentException("unexpected capacity".toString());
      } else {
         var var10000: Segment;
         if (this.head == null) {
            val `tail$iv`: Segment = SegmentPool.take();
            this.head = `tail$iv`;
            `tail$iv`.prev = `tail$iv`;
            `tail$iv`.next = `tail$iv`;
            var10000 = `tail$iv`;
         } else {
            var10000 = this.head;
            var var8: Segment = var10000.prev;
            if (var8.limit + minimumCapacity > 8192 || !var8.owner) {
               var8 = var8.push(SegmentPool.take());
            }

            var10000 = var8;
         }

         return var10000;
      }
   }

   public override fun write(source: Buffer, byteCount: Long) {
      val `$this$commonWrite$iv`: Buffer = this;
      val `source$iv`: Buffer = source;
      var `byteCount$iv`: Long = byteCount;
      if (source === this) {
         throw new IllegalArgumentException("source == this".toString());
      } else {
         -SegmentedByteString.checkOffsetAndCount(source.size(), 0L, byteCount);

         while (byteCount$iv > 0L) {
            var var10001: Segment = `source$iv`.head;
            val var22: Int = var10001.limit;
            val var10002: Segment = `source$iv`.head;
            if (`byteCount$iv` < var22 - var10002.pos) {
               var var19: Segment;
               if (`$this$commonWrite$iv`.head != null) {
                  var19 = `$this$commonWrite$iv`.head;
                  var19 = var19.prev;
               } else {
                  var19 = null;
               }

               if (var19 != null && var19.owner && `byteCount$iv` + var19.limit - (if (var19.shared) 0 else var19.pos) <= 8192L) {
                  val var21: Segment = `source$iv`.head;
                  var21.writeTo(var19, (int)`byteCount$iv`);
                  `source$iv`.setSize$okio(`source$iv`.size() - `byteCount$iv`);
                  `$this$commonWrite$iv`.setSize$okio(`$this$commonWrite$iv`.size() + `byteCount$iv`);
                  break;
               }

               var10001 = `source$iv`.head;
               `source$iv`.head = var10001.split((int)`byteCount$iv`);
            }

            val var17: Segment = `source$iv`.head;
            val `movedByteCount$iv`: Long = var17.limit - var17.pos;
            `source$iv`.head = var17.pop();
            if (`$this$commonWrite$iv`.head == null) {
               `$this$commonWrite$iv`.head = var17;
               var17.prev = var17;
               var17.next = var17.prev;
            } else {
               val var20: Segment = `$this$commonWrite$iv`.head;
               val `tail$iv`: Segment = var20.prev;
               `tail$iv`.push(var17).compact();
            }

            `source$iv`.setSize$okio(`source$iv`.size() - `movedByteCount$iv`);
            `$this$commonWrite$iv`.setSize$okio(`$this$commonWrite$iv`.size() + `movedByteCount$iv`);
            `byteCount$iv` -= `movedByteCount$iv`;
         }
      }
   }

   public override fun read(sink: Buffer, byteCount: Long): Long {
      var var13: Long = byteCount;
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
      } else {
         val var10000: Long;
         if (this.size() == 0L) {
            var10000 = -1L;
         } else {
            if (byteCount > this.size()) {
               var13 = this.size();
            }

            sink.write(this, var13);
            var10000 = var13;
         }

         return var10000;
      }
   }

   public override fun indexOf(b: Byte): Long {
      return this.indexOf(b, 0L, java.lang.Long.MAX_VALUE);
   }

   public override fun indexOf(b: Byte, fromIndex: Long): Long {
      return this.indexOf(b, fromIndex, java.lang.Long.MAX_VALUE);
   }

   public override fun indexOf(b: Byte, fromIndex: Long, toIndex: Long): Long {
      val `b$iv`: Byte = b;
      var var50: Long = fromIndex;
      var var51: Long = toIndex;
      if (0L > fromIndex || fromIndex > toIndex) {
         throw new IllegalArgumentException(("size=${this.size()} fromIndex=$fromIndex toIndex=$toIndex").toString());
      } else {
         if (toIndex > this.size()) {
            var51 = this.size();
         }

         val var10000: Long;
         if (fromIndex == var51) {
            var10000 = -1L;
         } else {
            val `fromIndex$iv$iv`: Long = fromIndex;
            if (this.head == null) {
               var10000 = -1L;
            } else {
               var `s$iv$iv`: Segment = this.head;
               if (this.size() - fromIndex < fromIndex) {
                  var `offset$iv$iv`: Long;
                  for (offset$iv$iv = this.size(); offset$iv$iv > fromIndex$iv$iv; offset$iv$iv -= var52.limit - var52.pos) {
                     var52 = `s$iv$iv`.prev;
                     `s$iv$iv` = var52;
                  }

                  if (`s$iv$iv` == null) {
                     var10000 = -1L;
                  } else {
                     var `s$iv`: Segment = `s$iv$iv`;
                     var `offset$iv`: Long = `offset$iv$iv`;

                     while (offset$iv < var51) {
                        val `data$iv`: ByteArray = `s$iv`.data;
                        val `limit$iv`: Int = (int)Math.min((long)`s$iv`.limit, (long)`s$iv`.pos + var51 - `offset$iv`);

                        for (int pos$iv = (int)(s$iv.pos + var50 - offset$iv); pos$iv < limit$iv; pos$iv++) {
                           if (`data$iv`[`data$iv`] == `b$iv`) {
                              return `data$iv` - `s$iv`.pos + `offset$iv`;
                           }
                        }

                        `offset$iv` += `s$iv`.limit - `s$iv`.pos;
                        var50 = `offset$iv`;
                        val var53: Segment = `s$iv`.next;
                        `s$iv` = var53;
                     }

                     var10000 = -1L;
                  }
               } else {
                  var `offset$iv$ivx`: Long = 0L;

                  while (true) {
                     val `nextOffset$iv$iv`: Long = `offset$iv$ivx` + (`s$iv$iv`.limit - `s$iv$iv`.pos);
                     if (`offset$iv$ivx` + (`s$iv$iv`.limit - `s$iv$iv`.pos) > `fromIndex$iv$iv`) {
                        if (`s$iv$iv` == null) {
                           var10000 = -1L;
                        } else {
                           var `s$iv`: Segment = `s$iv$iv`;
                           var `offset$iv`: Long = `offset$iv$ivx`;

                           while (offset$iv < var51) {
                              val var48: ByteArray = `s$iv`.data;
                              val `limit$iv`: Int = (int)Math.min((long)`s$iv`.limit, (long)`s$iv`.pos + var51 - `offset$iv`);

                              for (int pos$ivx = (int)(s$iv.pos + var50 - offset$iv); pos$ivx < limit$iv; pos$ivx++) {
                                 if (var48[`pos$ivx`] == `b$iv`) {
                                    return `pos$ivx` - `s$iv`.pos + `offset$iv`;
                                 }
                              }

                              `offset$iv` += `s$iv`.limit - `s$iv`.pos;
                              var50 = `offset$iv`;
                              val var55: Segment = `s$iv`.next;
                              `s$iv` = var55;
                           }

                           var10000 = -1L;
                        }
                        break;
                     }

                     val var54: Segment = `s$iv$iv`.next;
                     `s$iv$iv` = var54;
                     `offset$iv$ivx` = `nextOffset$iv$iv`;
                  }
               }
            }
         }

         return var10000;
      }
   }

   @Throws(java/io/IOException::class)
   public override fun indexOf(bytes: ByteString): Long {
      return this.indexOf(bytes, 0L);
   }

   @Throws(java/io/IOException::class)
   public override fun indexOf(bytes: ByteString, fromIndex: Long): Long {
      return this.indexOf(bytes, fromIndex, java.lang.Long.MAX_VALUE);
   }

   @Throws(java/io/IOException::class)
   public override fun indexOf(bytes: ByteString, fromIndex: Long, toIndex: Long): Long {
      return -Buffer.commonIndexOf$default(this, bytes, fromIndex, toIndex, 0, 0, 24, null);
   }

   public override fun indexOfElement(targetBytes: ByteString): Long {
      return this.indexOfElement(targetBytes, 0L);
   }

   public override fun indexOfElement(targetBytes: ByteString, fromIndex: Long): Long {
      val `$this$commonIndexOfElement$iv`: Buffer = this;
      var var66: Long = fromIndex;
      if (fromIndex < 0L) {
         throw new IllegalArgumentException(("fromIndex < 0: $fromIndex").toString());
      } else {
         val `fromIndex$iv$iv`: Long = fromIndex;
         val var10000: Long;
         if (this.head == null) {
            var10000 = -1L;
         } else {
            var `s$iv$iv`: Segment = this.head;
            if (this.size() - fromIndex < fromIndex) {
               var `offset$iv$iv`: Long;
               for (offset$iv$iv = this.size(); offset$iv$iv > fromIndex$iv$iv; offset$iv$iv -= var67.limit - var67.pos) {
                  var67 = `s$iv$iv`.prev;
                  `s$iv$iv` = var67;
               }

               if (`s$iv$iv` == null) {
                  var10000 = -1L;
               } else {
                  var `s$iv`: Segment = `s$iv$iv`;
                  var `offset$iv`: Long = `offset$iv$iv`;
                  if (targetBytes.size() != 2) {
                     val var47: ByteArray = targetBytes.internalArray$okio();

                     while (offset$iv < $this$commonIndexOfElement$iv.size()) {
                        val var48: ByteArray = `s$iv`.data;
                        var var49: Int = (int)(`s$iv`.pos + var66 - `offset$iv`);

                        for (int limit$iv = s$iv.limit; pos$iv < limit$iv; pos$iv++) {
                           val var55: Int = var48[var49];

                           for (byte t$iv : targetByteArray$iv) {
                              if (var55 == `b$iv`) {
                                 return var49 - `s$iv`.pos + `offset$iv`;
                              }
                           }
                        }

                        `offset$iv` += `s$iv`.limit - `s$iv`.pos;
                        var66 = `offset$iv`;
                        val var69: Segment = `s$iv`.next;
                        `s$iv` = var69;
                     }
                  } else {
                     val `targetByteArray$iv`: Byte = targetBytes.getByte(0);
                     val `data$iv`: Byte = targetBytes.getByte(1);

                     while (offset$iv < $this$commonIndexOfElement$iv.size()) {
                        val `targetByteArray$ivx`: ByteArray = `s$iv`.data;
                        var `data$ivx`: Int = (int)(`s$iv`.pos + var66 - `offset$iv`);

                        for (int limit$iv = s$iv.limit; data$ivx < limit$iv; data$ivx++) {
                           if (`targetByteArray$ivx`[`data$ivx`] == `targetByteArray$iv` || `targetByteArray$ivx`[`data$ivx`] == `data$iv`) {
                              return `data$ivx` - `s$iv`.pos + `offset$iv`;
                           }
                        }

                        `offset$iv` += `s$iv`.limit - `s$iv`.pos;
                        var66 = `offset$iv`;
                        val var68: Segment = `s$iv`.next;
                        `s$iv` = var68;
                     }
                  }

                  var10000 = -1L;
               }
            } else {
               var `offset$iv$ivx`: Long = 0L;

               while (true) {
                  val `nextOffset$iv$iv`: Long = `offset$iv$ivx` + (`s$iv$iv`.limit - `s$iv$iv`.pos);
                  if (`offset$iv$ivx` + (`s$iv$iv`.limit - `s$iv$iv`.pos) > `fromIndex$iv$iv`) {
                     if (`s$iv$iv` == null) {
                        var10000 = -1L;
                     } else {
                        var `s$iv`: Segment = `s$iv$iv`;
                        var `offset$iv`: Long = `offset$iv$ivx`;
                        if (targetBytes.size() != 2) {
                           val var51: ByteArray = targetBytes.internalArray$okio();

                           while (offset$iv < $this$commonIndexOfElement$iv.size()) {
                              val var54: ByteArray = `s$iv`.data;
                              var var57: Int = (int)(`s$iv`.pos + var66 - `offset$iv`);

                              for (int limit$ivx = s$iv.limit; pos$iv < limit$ivx; pos$iv++) {
                                 val var62: Int = var54[var57];

                                 for (byte t$ivx : targetByteArray$iv) {
                                    if (var62 == `t$ivx`) {
                                       return var57 - `s$iv`.pos + `offset$iv`;
                                    }
                                 }
                              }

                              `offset$iv` += `s$iv`.limit - `s$iv`.pos;
                              var66 = `offset$iv`;
                              val var72: Segment = `s$iv`.next;
                              `s$iv` = var72;
                           }
                        } else {
                           val var50: Byte = targetBytes.getByte(0);
                           val var53: Byte = targetBytes.getByte(1);

                           while (offset$iv < $this$commonIndexOfElement$iv.size()) {
                              val var56: ByteArray = `s$iv`.data;
                              var var59: Int = (int)(`s$iv`.pos + var66 - `offset$iv`);

                              for (int limit$ivx = s$iv.limit; pos$iv < limit$ivx; pos$iv++) {
                                 if (var56[var59] == var50 || var56[var59] == var53) {
                                    return var59 - `s$iv`.pos + `offset$iv`;
                                 }
                              }

                              `offset$iv` += `s$iv`.limit - `s$iv`.pos;
                              var66 = `offset$iv`;
                              val var71: Segment = `s$iv`.next;
                              `s$iv` = var71;
                           }
                        }

                        var10000 = -1L;
                     }
                     break;
                  }

                  val var70: Segment = `s$iv$iv`.next;
                  `s$iv$iv` = var70;
                  `offset$iv$ivx` = `nextOffset$iv$iv`;
               }
            }
         }

         return var10000;
      }
   }

   public override fun rangeEquals(offset: Long, bytes: ByteString): Boolean {
      return this.rangeEquals(offset, bytes, 0, bytes.size());
   }

   public override fun rangeEquals(offset: Long, bytes: ByteString, bytesOffset: Int, byteCount: Int): Boolean {
      return byteCount >= 0
         && offset >= 0L
         && offset + byteCount <= this.size()
         && bytesOffset >= 0
         && bytesOffset + byteCount <= bytes.size()
         && (byteCount == 0 || -Buffer.commonIndexOf(this, bytes, offset, offset + 1L, bytesOffset, byteCount) != -1L);
   }

   public override fun flush() {
   }

   public override fun isOpen(): Boolean {
      return true;
   }

   public override fun close() {
   }

   public override fun timeout(): Timeout {
      return Timeout.NONE;
   }

   public fun md5(): ByteString {
      return this.digest("MD5");
   }

   public fun sha1(): ByteString {
      return this.digest("SHA-1");
   }

   public fun sha256(): ByteString {
      return this.digest("SHA-256");
   }

   public fun sha512(): ByteString {
      return this.digest("SHA-512");
   }

   private fun digest(algorithm: String): ByteString {
      val messageDigest: MessageDigest = MessageDigest.getInstance(algorithm);
      if (this.head != null) {
         val head: Segment = this.head;
         messageDigest.update(this.head.data, this.head.pos, this.head.limit - this.head.pos);
         var var10000: Segment = head.next;
         var s: Segment = var10000;

         while (s != head) {
            messageDigest.update(s.data, s.pos, s.limit - s.pos);
            var10000 = s.next;
            s = var10000;
         }
      }

      val var10002: ByteArray = messageDigest.digest();
      return new ByteString(var10002);
   }

   public fun hmacSha1(key: ByteString): ByteString {
      return this.hmac("HmacSHA1", key);
   }

   public fun hmacSha256(key: ByteString): ByteString {
      return this.hmac("HmacSHA256", key);
   }

   public fun hmacSha512(key: ByteString): ByteString {
      return this.hmac("HmacSHA512", key);
   }

   private fun hmac(algorithm: String, key: ByteString): ByteString {
      try {
         val mac: Mac = Mac.getInstance(algorithm);
         mac.init(new SecretKeySpec(key.internalArray$okio(), algorithm));
         if (this.head != null) {
            val head: Segment = this.head;
            mac.update(this.head.data, this.head.pos, this.head.limit - this.head.pos);
            var var10000: Segment = head.next;
            var s: Segment = var10000;

            while (s != head) {
               mac.update(s.data, s.pos, s.limit - s.pos);
               var10000 = s.next;
               s = var10000;
            }
         }

         val var10002: ByteArray = mac.doFinal();
         return new ByteString(var10002);
      } catch (var8: InvalidKeyException) {
         throw new IllegalArgumentException(var8);
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      val `$this$commonEquals$iv`: Buffer = this;
      val var10000: Boolean;
      if (this === other) {
         var10000 = true;
      } else if (other !is Buffer) {
         var10000 = false;
      } else if (this.size() != (other as Buffer).size()) {
         var10000 = false;
      } else if (this.size() == 0L) {
         var10000 = true;
      } else {
         val var18: Segment = this.head;
         var `sa$iv`: Segment = var18;
         val var19: Segment = (other as Buffer).head;
         var `sb$iv`: Segment = var19;
         var `posA$iv`: Int = var18.pos;
         var `posB$iv`: Int = var19.pos;
         var `pos$iv`: Long = 0L;

         while (pos$iv < $this$commonEquals$iv.size()) {
            val var17: Long = Math.min(`sa$iv`.limit - `posA$iv`, `sb$iv`.limit - `posB$iv`);
            var `i$iv`: Long = 0L;

            for (long var15 = var17; i$iv < var15; i$iv++) {
               if (`sa$iv`.data[`posA$iv`++] != `sb$iv`.data[`posB$iv`++]) {
                  return false;
               }
            }

            if (`posA$iv` == `sa$iv`.limit) {
               val var21: Segment = `sa$iv`.next;
               `sa$iv` = var21;
               `posA$iv` = var21.pos;
            }

            if (`posB$iv` == `sb$iv`.limit) {
               val var22: Segment = `sb$iv`.next;
               `sb$iv` = var22;
               `posB$iv` = var22.pos;
            }

            `pos$iv` += var17;
         }

         var10000 = true;
      }

      return var10000;
   }

   public override fun hashCode(): Int {
      val `$this$commonHashCode$iv`: Buffer = this;
      val var10000: Int;
      if (this.head == null) {
         var10000 = 0;
      } else {
         var `s$iv`: Segment = this.head;
         var `result$iv`: Int = 1;

         do {
            var `pos$iv`: Int = `s$iv`.pos;

            for (int limit$iv = s$iv.limit; pos$iv < limit$iv; pos$iv++) {
               `result$iv` = 31 * `result$iv` + `s$iv`.data[`pos$iv`];
            }

            var7 = `s$iv`.next;
            `s$iv` = var7;
         } while (var7 != $this$commonHashCode$iv.head);

         var10000 = `result$iv`;
      }

      return var10000;
   }

   public override fun toString(): String {
      return this.snapshot().toString();
   }

   public fun copy(): Buffer {
      val `result$iv`: Buffer = new Buffer();
      val var10000: Buffer;
      if (this.size() == 0L) {
         var10000 = `result$iv`;
      } else {
         val var7: Segment = this.head;
         val `head$iv`: Segment = var7;
         val `headCopy$iv`: Segment = var7.sharedCopy();
         `result$iv`.head = `headCopy$iv`;
         `headCopy$iv`.prev = `result$iv`.head;
         `headCopy$iv`.next = `headCopy$iv`.prev;

         for (Segment s$iv = var7.next; s$iv != head$iv; s$iv = s$iv.next) {
            val var8: Segment = `headCopy$iv`.prev;
            var8.push(`s$iv`.sharedCopy());
         }

         `result$iv`.setSize$okio(this.size());
         var10000 = `result$iv`;
      }

      return var10000;
   }

   public open fun clone(): Buffer {
      return this.copy();
   }

   public fun snapshot(): ByteString {
      if (this.size() > 2147483647L) {
         throw new IllegalStateException(("size > Int.MAX_VALUE: ${this.size()}").toString());
      } else {
         return this.snapshot((int)this.size());
      }
   }

   public fun snapshot(byteCount: Int): ByteString {
      val `byteCount$iv`: Int = byteCount;
      val var10000: ByteString;
      if (byteCount == 0) {
         var10000 = ByteString.EMPTY;
      } else {
         -SegmentedByteString.checkOffsetAndCount(this.size(), 0L, (long)byteCount);
         var `offset$iv`: Int = 0;
         var `segmentCount$iv`: Int = 0;

         for (Segment s$iv = this.head; offset$iv < byteCount$iv; s$iv = s$iv.next) {
            if (`s$iv`.limit == `s$iv`.pos) {
               throw new AssertionError("s.limit == s.pos");
            }

            `offset$iv` += `s$iv`.limit - `s$iv`.pos;
            `segmentCount$iv`++;
         }

         val `segments$iv`: Array<ByteArray> = new byte[`segmentCount$iv`][];
         val `directory$iv`: IntArray = new int[`segmentCount$iv` * 2];
         `offset$iv` = 0;
         `segmentCount$iv` = 0;

         for (Segment var12 = this.head; offset$iv < byteCount$iv; var12 = var12.next) {
            `segments$iv`[`segmentCount$iv`] = var12.data;
            `offset$iv` += var12.limit - var12.pos;
            `directory$iv`[`segmentCount$iv`] = Math.min(`offset$iv`, `byteCount$iv`);
            `directory$iv`[`segmentCount$iv` + (`segments$iv` as Array<Any>).length] = var12.pos;
            var12.shared = true;
            `segmentCount$iv`++;
         }

         var10000 = new SegmentedByteString(`segments$iv`, `directory$iv`);
      }

      return var10000;
   }

   @JvmOverloads
   public fun readUnsafe(unsafeCursor: okio.Buffer.UnsafeCursor = -SegmentedByteString.getDEFAULT__new_UnsafeCursor()): okio.Buffer.UnsafeCursor {
      return -Buffer.commonReadUnsafe(this, unsafeCursor);
   }

   @JvmOverloads
   public fun readAndWriteUnsafe(unsafeCursor: okio.Buffer.UnsafeCursor = -SegmentedByteString.getDEFAULT__new_UnsafeCursor()): okio.Buffer.UnsafeCursor {
      return -Buffer.commonReadAndWriteUnsafe(this, unsafeCursor);
   }

   @Deprecated(message = "moved to operator function", replaceWith = @ReplaceWith(expression = "this[index]", imports = []), level = DeprecationLevel.ERROR)
   @JvmName(name = "-deprecated_getByte")
   public fun getByte(index: Long): Byte {
      return this.getByte(index);
   }

   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = []), level = DeprecationLevel.ERROR)
   @JvmName(name = "-deprecated_size")
   public fun size(): Long {
      return this.size;
   }

   @JvmOverloads
   @Throws(java/io/IOException::class)
   fun copyTo(out: OutputStream, offset: Long): Buffer {
      return copyTo$default(this, out, offset, 0L, 4, null);
   }

   @JvmOverloads
   @Throws(java/io/IOException::class)
   fun copyTo(out: OutputStream): Buffer {
      return copyTo$default(this, out, 0L, 0L, 6, null);
   }

   @JvmOverloads
   @Throws(java/io/IOException::class)
   fun writeTo(out: OutputStream): Buffer {
      return writeTo$default(this, out, 0L, 2, null);
   }

   @JvmOverloads
   fun readUnsafe(): Buffer.UnsafeCursor {
      return readUnsafe$default(this, null, 1, null);
   }

   @JvmOverloads
   fun readAndWriteUnsafe(): Buffer.UnsafeCursor {
      return readAndWriteUnsafe$default(this, null, 1, null);
   }

   @SourceDebugExtension(["SMAP\nBuffer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Buffer.kt\nokio/Buffer$UnsafeCursor\n+ 2 Buffer.kt\nokio/internal/-Buffer\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,649:1\n1549#2:650\n1550#2:652\n1554#2:653\n1555#2,68:655\n1626#2:723\n1627#2,32:725\n1659#2,18:758\n1680#2:776\n1681#2,18:778\n1703#2:796\n1705#2,7:798\n1#3:651\n1#3:654\n1#3:724\n1#3:777\n1#3:797\n85#4:757\n*S KotlinDebug\n*F\n+ 1 Buffer.kt\nokio/Buffer$UnsafeCursor\n*L\n636#1:650\n636#1:652\n638#1:653\n638#1:655,68\n640#1:723\n640#1:725,32\n640#1:758,18\n642#1:776\n642#1:778,18\n645#1:796\n645#1:798,7\n636#1:651\n638#1:654\n640#1:724\n642#1:777\n645#1:797\n640#1:757\n*E\n"])
   public class UnsafeCursor : Closeable {
      public final var buffer: Buffer?
         private set

      public final var readWrite: Boolean
         private set

      internal final var segment: Segment?

      public final var offset: Long = -1L
         private set

      public final var data: ByteArray?
         private set

      public final var start: Int = -1
         private set

      public final var end: Int = -1
         private set

      public fun next(): Int {
         val var10000: Long = this.offset;
         val var10001: Buffer = this.buffer;
         if (var10000 == var10001.size()) {
            throw new IllegalStateException("no more bytes".toString());
         } else {
            return if (this.offset == -1L) this.seek(0L) else this.seek(this.offset + (long)(this.end - this.start));
         }
      }

      public fun seek(offset: Long): Int {
         val `offset$iv`: Long = offset;
         if (this.buffer == null) {
            throw new IllegalStateException("not attached to a buffer".toString());
         } else {
            val `buffer$iv`: Buffer = this.buffer;
            if (offset >= -1L && offset <= this.buffer.size()) {
               val var10000: Int;
               if (offset != -1L && offset != `buffer$iv`.size()) {
                  var `min$iv`: Long = 0L;
                  var `max$iv`: Long = `buffer$iv`.size();
                  var `head$iv`: Segment = `buffer$iv`.head;
                  var `tail$iv`: Segment = `buffer$iv`.head;
                  if (this.getSegment$okio() != null) {
                     val var24: Long = this.offset;
                     val var10001: Int = this.start;
                     val var10002: Segment = this.getSegment$okio();
                     val `segmentOffset$iv`: Long = var24 - (var10001 - var10002.pos);
                     if (var24 - (var10001 - var10002.pos) > offset) {
                        `max$iv` = `segmentOffset$iv`;
                        `tail$iv` = this.getSegment$okio();
                     } else {
                        `min$iv` = `segmentOffset$iv`;
                        `head$iv` = this.getSegment$okio();
                     }
                  }

                  var var22: Segment;
                  var var23: Long;
                  if (`max$iv` - offset > offset - `min$iv`) {
                     var22 = `head$iv`;
                     var23 = `min$iv`;

                     while (true) {
                        if (`offset$iv` < var23 + (var22.limit - var22.pos)) {
                           break;
                        }

                        var23 += var22.limit - var22.pos;
                        var22 = var22.next;
                     }
                  } else {
                     var22 = `tail$iv`;

                     for (var23 = max$iv; var23 > offset$iv; var23 -= var22.limit - var22.pos) {
                        var22 = var22.prev;
                     }
                  }

                  if (this.readWrite) {
                     if (var22.shared) {
                        val `unsharedNext$iv`: Segment = var22.unsharedCopy();
                        if (`buffer$iv`.head === var22) {
                           `buffer$iv`.head = `unsharedNext$iv`;
                        }

                        var22 = var22.push(`unsharedNext$iv`);
                        val var25: Segment = var22.prev;
                        var25.pop();
                     }
                  }

                  this.setSegment$okio(var22);
                  this.offset = `offset$iv`;
                  this.data = var22.data;
                  this.start = var22.pos + (int)(`offset$iv` - var23);
                  this.end = var22.limit;
                  var10000 = this.end - this.start;
               } else {
                  this.setSegment$okio(null);
                  this.offset = offset;
                  this.data = null;
                  this.start = -1;
                  this.end = -1;
                  var10000 = -1;
               }

               return var10000;
            } else {
               throw new ArrayIndexOutOfBoundsException("offset=$offset > size=${`buffer$iv`.size()}");
            }
         }
      }

      public fun resizeBuffer(newSize: Long): Long {
         val `$this$commonResizeBuffer$iv`: Buffer.UnsafeCursor = this;
         if (this.buffer == null) {
            throw new IllegalStateException("not attached to a buffer".toString());
         } else {
            val `buffer$iv`: Buffer = this.buffer;
            if (!this.readWrite) {
               throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers".toString());
            } else {
               val `oldSize$iv`: Long = this.buffer.size();
               if (newSize <= `oldSize$iv`) {
                  if (newSize < 0L) {
                     throw new IllegalArgumentException(("newSize < 0: $newSize").toString());
                  }

                  var `bytesToSubtract$iv`: Long = `oldSize$iv` - newSize;

                  while (bytesToSubtract$iv > 0L) {
                     val var10000: Segment = `buffer$iv`.head;
                     val `tail$iv`: Segment = var10000.prev;
                     val `tail$ivx`: Int = `tail$iv`.limit - `tail$iv`.pos;
                     if (`tail$iv`.limit - `tail$iv`.pos > `bytesToSubtract$iv`) {
                        `tail$iv`.limit -= (int)`bytesToSubtract$iv`;
                        break;
                     }

                     `buffer$iv`.head = `tail$iv`.pop();
                     SegmentPool.recycle(`tail$iv`);
                     `bytesToSubtract$iv` -= `tail$ivx`;
                  }

                  this.setSegment$okio(null);
                  this.offset = newSize;
                  this.data = null;
                  this.start = -1;
                  this.end = -1;
               } else if (newSize > `oldSize$iv`) {
                  var `needsToSeek$iv`: Boolean = true;
                  var `bytesToAdd$iv`: Long = newSize - `oldSize$iv`;

                  while (bytesToAdd$iv > 0L) {
                     val var28: Segment = `buffer$iv`.writableSegment$okio(1);
                     val `segmentBytesToAdd$iv`: Int = (int)Math.min(`bytesToAdd$iv`, (long)(8192 - var28.limit));
                     var28.limit += `segmentBytesToAdd$iv`;
                     `bytesToAdd$iv` -= `segmentBytesToAdd$iv`;
                     if (`needsToSeek$iv`) {
                        `$this$commonResizeBuffer$iv`.setSegment$okio(var28);
                        `$this$commonResizeBuffer$iv`.offset = `oldSize$iv`;
                        `$this$commonResizeBuffer$iv`.data = var28.data;
                        `$this$commonResizeBuffer$iv`.start = var28.limit - `segmentBytesToAdd$iv`;
                        `$this$commonResizeBuffer$iv`.end = var28.limit;
                        `needsToSeek$iv` = false;
                     }
                  }
               }

               `buffer$iv`.setSize$okio(newSize);
               return `oldSize$iv`;
            }
         }
      }

      public fun expandBuffer(minByteCount: Int): Long {
         if (minByteCount <= 0) {
            throw new IllegalArgumentException(("minByteCount <= 0: $minByteCount").toString());
         } else if (minByteCount > 8192) {
            throw new IllegalArgumentException(("minByteCount > Segment.SIZE: $minByteCount").toString());
         } else if (this.buffer == null) {
            throw new IllegalStateException("not attached to a buffer".toString());
         } else {
            val `buffer$iv`: Buffer = this.buffer;
            if (!this.readWrite) {
               throw new IllegalStateException("expandBuffer() only permitted for read/write buffers".toString());
            } else {
               val `oldSize$iv`: Long = this.buffer.size();
               val `tail$iv`: Segment = `buffer$iv`.writableSegment$okio(minByteCount);
               val `result$iv`: Int = 8192 - `tail$iv`.limit;
               `tail$iv`.limit = 8192;
               `buffer$iv`.setSize$okio(`oldSize$iv` + (long)`result$iv`);
               this.setSegment$okio(`tail$iv`);
               this.offset = `oldSize$iv`;
               this.data = `tail$iv`.data;
               this.start = 8192 - `result$iv`;
               this.end = 8192;
               return `result$iv`;
            }
         }
      }

      public override fun close() {
         if (this.buffer == null) {
            throw new IllegalStateException("not attached to a buffer".toString());
         } else {
            this.buffer = null;
            this.setSegment$okio(null);
            this.offset = -1L;
            this.data = null;
            this.start = -1;
            this.end = -1;
         }
      }
   }
}
