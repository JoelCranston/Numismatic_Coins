package kotlinx.io

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nPeekSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PeekSource.kt\nkotlinx/io/PeekSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 -Util.kt\nkotlinx/io/_UtilKt\n*L\n1#1,76:1\n1#2:77\n1#2:79\n52#3:78\n53#3:80\n*S KotlinDebug\n*F\n+ 1 PeekSource.kt\nkotlinx/io/PeekSource\n*L\n46#1:79\n46#1:78\n46#1:80\n*E\n"])
internal class PeekSource(upstream: Source) : RawSource {
   private final val upstream: Source
   private final val buffer: Buffer
   private final var expectedSegment: Segment?
   private final var expectedPos: Int
   private final var closed: Boolean
   private final var pos: Long

   init {
      this.upstream = upstream;
      this.buffer = this.upstream.getBuffer();
      this.expectedSegment = this.buffer.getHead();
      val var10001: Segment = this.buffer.getHead();
      this.expectedPos = if (var10001 != null) var10001.getPos() else -1;
   }

   public override fun readAtMostTo(sink: Buffer, byteCount: Long): Long {
      if (this.closed) {
         throw new IllegalStateException("Source is closed.".toString());
      } else if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount ($byteCount) < 0").toString());
      } else {
         var var12: Int;
         label65: {
            label47:
            if (this.expectedSegment != null) {
               if (this.expectedSegment === this.buffer.getHead()) {
                  var12 = this.expectedPos;
                  val var10001: Segment = this.buffer.getHead();
                  if (var12 == var10001.getPos()) {
                     break label47;
                  }
               }

               var12 = 0;
               break label65;
            }

            var12 = 1;
         }

         if (!var12) {
            throw new IllegalStateException("Peek source is invalid because upstream source was used".toString());
         } else if (byteCount == 0L) {
            return 0L;
         } else if (!this.upstream.request(this.pos + 1L)) {
            return -1L;
         } else {
            if (this.expectedSegment == null && this.buffer.getHead() != null) {
               this.expectedSegment = this.buffer.getHead();
               val var13: Segment = this.buffer.getHead();
               this.expectedPos = var13.getPos();
            }

            val var6: Long = Math.min(byteCount, this.buffer.getSize() - this.pos);
            this.buffer.copyTo(sink, this.pos, this.pos + var6);
            this.pos += var6;
            return var6;
         }
      }
   }

   public override fun close() {
      this.closed = true;
   }
}
