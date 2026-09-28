package kotlinx.io

import java.io.EOFException
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nRealSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealSink.kt\nkotlinx/io/RealSink\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 -Util.kt\nkotlinx/io/_UtilKt\n*L\n1#1,158:1\n155#1:159\n156#1:161\n155#1:163\n156#1:165\n155#1:167\n156#1:169\n155#1:170\n156#1:172\n155#1:174\n156#1:176\n155#1:177\n156#1:179\n155#1:180\n156#1:182\n155#1:183\n156#1:185\n155#1:186\n156#1:188\n155#1:189\n156#1:191\n155#1:192\n156#1:194\n1#2:160\n1#2:162\n1#2:164\n1#2:168\n1#2:171\n1#2:173\n1#2:175\n1#2:178\n1#2:181\n1#2:184\n1#2:187\n1#2:190\n1#2:193\n1#2:195\n38#3:166\n*S KotlinDebug\n*F\n+ 1 RealSink.kt\nkotlinx/io/RealSink\n*L\n39#1:159\n39#1:161\n46#1:163\n46#1:165\n53#1:167\n53#1:169\n65#1:170\n65#1:172\n82#1:174\n82#1:176\n88#1:177\n88#1:179\n94#1:180\n94#1:182\n100#1:183\n100#1:185\n107#1:186\n107#1:188\n113#1:189\n113#1:191\n119#1:192\n119#1:194\n39#1:160\n46#1:164\n53#1:168\n65#1:171\n82#1:175\n88#1:178\n94#1:181\n100#1:184\n107#1:187\n113#1:190\n119#1:193\n47#1:166\n*E\n"])
internal class RealSink(sink: RawSink) : Sink {
   public final val sink: RawSink

   public final var closed: Boolean
      private set

   private final val bufferField: Buffer

   @DelicateIoApi
   public open val buffer: Buffer
      public open get() {
         return this.bufferField;
      }


   init {
      this.sink = sink;
      this.bufferField = new Buffer();
   }

   public override fun write(source: Buffer, byteCount: Long) {
      if (this.closed) {
         throw new IllegalStateException("Sink is closed.".toString());
      } else if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount: $byteCount").toString());
      } else {
         this.bufferField.write(source, byteCount);
         this.hintEmit();
      }
   }

   public override fun write(source: ByteArray, startIndex: Int, endIndex: Int) {
      if (this.closed) {
         throw new IllegalStateException("Sink is closed.".toString());
      } else {
         _UtilKt.checkBounds((long)source.length, (long)startIndex, (long)endIndex);
         this.bufferField.write(source, startIndex, endIndex);
         this.hintEmit();
      }
   }

   public override fun transferFrom(source: RawSource): Long {
      if (this.closed) {
         throw new IllegalStateException("Sink is closed.".toString());
      } else {
         var totalBytesRead: Long = 0L;

         while (true) {
            val readCount: Long = source.readAtMostTo(this.bufferField, 8192L);
            if (readCount == -1L) {
               return totalBytesRead;
            }

            totalBytesRead += readCount;
            this.hintEmit();
         }
      }
   }

   public override fun write(source: RawSource, byteCount: Long) {
      if (this.closed) {
         throw new IllegalStateException("Sink is closed.".toString());
      } else if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount: $byteCount").toString());
      } else {
         var remainingByteCount: Long = byteCount;

         while (remainingByteCount > 0L) {
            val read: Long = source.readAtMostTo(this.bufferField, remainingByteCount);
            if (read == -1L) {
               throw new EOFException("Source exhausted before reading $byteCount bytes from it (number of bytes read: ${byteCount - remainingByteCount}).");
            }

            remainingByteCount -= read;
            this.hintEmit();
         }
      }
   }

   public override fun writeByte(byte: Byte) {
      if (this.closed) {
         throw new IllegalStateException("Sink is closed.".toString());
      } else {
         this.bufferField.writeByte(var1);
         this.hintEmit();
      }
   }

   public override fun writeShort(short: Short) {
      if (this.closed) {
         throw new IllegalStateException("Sink is closed.".toString());
      } else {
         this.bufferField.writeShort(var1);
         this.hintEmit();
      }
   }

   public override fun writeInt(int: Int) {
      if (this.closed) {
         throw new IllegalStateException("Sink is closed.".toString());
      } else {
         this.bufferField.writeInt(var1);
         this.hintEmit();
      }
   }

   public override fun writeLong(long: Long) {
      if (this.closed) {
         throw new IllegalStateException("Sink is closed.".toString());
      } else {
         this.bufferField.writeLong(var1);
         this.hintEmit();
      }
   }

   @InternalIoApi
   public override fun hintEmit() {
      if (this.closed) {
         throw new IllegalStateException("Sink is closed.".toString());
      } else {
         val byteCount: Long = this.bufferField.completeSegmentByteCount$kotlinx_io_core();
         if (byteCount > 0L) {
            this.sink.write(this.bufferField, byteCount);
         }
      }
   }

   public override fun emit() {
      if (this.closed) {
         throw new IllegalStateException("Sink is closed.".toString());
      } else {
         val byteCount: Long = this.bufferField.getSize();
         if (byteCount > 0L) {
            this.sink.write(this.bufferField, byteCount);
         }
      }
   }

   public override fun flush() {
      if (this.closed) {
         throw new IllegalStateException("Sink is closed.".toString());
      } else {
         if (this.bufferField.getSize() > 0L) {
            this.sink.write(this.bufferField, this.bufferField.getSize());
         }

         this.sink.flush();
      }
   }

   public override fun close() {
      if (!this.closed) {
         var thrown: java.lang.Throwable = null;

         try {
            if (this.bufferField.getSize() > 0L) {
               this.sink.write(this.bufferField, this.bufferField.getSize());
            }
         } catch (var3: java.lang.Throwable) {
            thrown = var3;
         }

         try {
            this.sink.close();
         } catch (var4: java.lang.Throwable) {
            if (thrown == null) {
               thrown = var4;
            }
         }

         this.closed = true;
         if (thrown != null) {
            throw thrown;
         }
      }
   }

   public override fun toString(): String {
      return "buffered(${this.sink})";
   }

   private inline fun checkNotClosed() {
      if (this.closed) {
         throw new IllegalStateException("Sink is closed.".toString());
      }
   }
}
