package kotlinx.io

import java.io.OutputStream
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.unsafe.UnsafeBufferOperations

@SourceDebugExtension(["SMAP\nJvmCore.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JvmCore.kt\nkotlinx/io/OutputStreamSink\n+ 2 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 -Util.kt\nkotlinx/io/_UtilKt\n*L\n1#1,112:1\n99#2:113\n100#2,2:115\n102#2,6:118\n1#3:114\n107#4:117\n*S KotlinDebug\n*F\n+ 1 JvmCore.kt\nkotlinx/io/OutputStreamSink\n*L\n48#1:113\n48#1:115,2\n48#1:118,6\n48#1:114\n49#1:117\n*E\n"])
private open class OutputStreamSink(out: OutputStream) : RawSink {
   private final val out: OutputStream

   init {
      this.out = out;
   }

   public override fun write(source: Buffer, byteCount: Long) {
      _UtilKt.checkOffsetAndCount(source.getSize(), 0L, byteCount);
      var var20: Long = byteCount;

      while (var20 > 0L) {
         val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         if (source.exhausted()) {
            throw new IllegalArgumentException("Buffer is empty".toString());
         }

         val var10000: Segment = source.getHead();
         val var21: ByteArray = var10000.dataAsByteArray(true);
         val pos: Int = var10000.getPos();
         val toCopy: Int = (int)Math.min(var20, (long)(var10000.getLimit() - pos));
         this.out.write(var21, pos, toCopy);
         var20 -= toCopy;
         if (toCopy != 0) {
            if (toCopy < 0) {
               throw new IllegalStateException("Returned negative read bytes count");
            }

            if (toCopy > var10000.getSize()) {
               throw new IllegalStateException("Returned too many bytes");
            }

            source.skip((long)toCopy);
         }
      }
   }

   public override fun flush() {
      this.out.flush();
   }

   public override fun close() {
      this.out.close();
   }

   public override fun toString(): String {
      return "RawSink(${this.out})";
   }
}
