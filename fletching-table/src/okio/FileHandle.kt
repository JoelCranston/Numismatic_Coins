package okio

import java.io.Closeable
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nFileHandle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileHandle.kt\nokio/FileHandle\n+ 2 -JvmPlatform.kt\nokio/_JvmPlatformKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 RealBufferedSource.kt\nokio/RealBufferedSource\n+ 5 RealBufferedSink.kt\nokio/RealBufferedSink\n+ 6 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,444:1\n40#2:445\n40#2:447\n40#2:448\n40#2:449\n40#2:450\n40#2:451\n40#2:452\n40#2:453\n40#2:457\n40#2:459\n1#3:446\n63#4:454\n63#4:455\n63#4:456\n51#5:458\n85#6:460\n85#6:461\n*S KotlinDebug\n*F\n+ 1 FileHandle.kt\nokio/FileHandle\n*L\n69#1:445\n81#1:447\n92#1:448\n105#1:449\n119#1:450\n129#1:451\n139#1:452\n151#1:453\n221#1:457\n287#1:459\n169#1:454\n195#1:455\n202#1:456\n248#1:458\n345#1:460\n374#1:461\n*E\n"])
public abstract class FileHandle : Closeable {
   public final val readWrite: Boolean
   private final var closed: Boolean
   private final var openStreamCount: Int
   public final val lock: ReentrantLock

   open fun FileHandle(readWrite: Boolean) {
      this.readWrite = readWrite;
      this.lock = _JvmPlatformKt.newLock();
   }

   @Throws(java/io/IOException::class)
   public fun read(fileOffset: Long, array: ByteArray, arrayOffset: Int, byteCount: Int): Int {
      label27: {
         val var8: Lock = this.lock;
         this.lock.lock();

         try {
            if (this.closed) {
               throw new IllegalStateException("closed".toString());
            }
         } catch (var11: java.lang.Throwable) {
            var8.unlock();
         }

         var8.unlock();
      }
   }

   @Throws(java/io/IOException::class)
   public fun read(fileOffset: Long, sink: Buffer, byteCount: Long): Long {
      label27: {
         val var8: Lock = this.lock;
         this.lock.lock();

         try {
            if (this.closed) {
               throw new IllegalStateException("closed".toString());
            }
         } catch (var11: java.lang.Throwable) {
            var8.unlock();
         }

         var8.unlock();
      }
   }

   @Throws(java/io/IOException::class)
   public fun size(): Long {
      label27: {
         val var3: Lock = this.lock;
         this.lock.lock();

         try {
            if (this.closed) {
               throw new IllegalStateException("closed".toString());
            }
         } catch (var6: java.lang.Throwable) {
            var3.unlock();
         }

         var3.unlock();
      }
   }

   @Throws(java/io/IOException::class)
   public fun resize(size: Long) {
      if (!this.readWrite) {
         throw new IllegalStateException("file handle is read-only".toString());
      } else {
         label50: {
            val var5: Lock = this.lock;
            this.lock.lock();

            try {
               if (this.closed) {
                  throw new IllegalStateException("closed".toString());
               }
            } catch (var8: java.lang.Throwable) {
               var5.unlock();
            }

            var5.unlock();
         }
      }
   }

   public fun write(fileOffset: Long, array: ByteArray, arrayOffset: Int, byteCount: Int) {
      if (!this.readWrite) {
         throw new IllegalStateException("file handle is read-only".toString());
      } else {
         label50: {
            val var8: Lock = this.lock;
            this.lock.lock();

            try {
               if (this.closed) {
                  throw new IllegalStateException("closed".toString());
               }
            } catch (var11: java.lang.Throwable) {
               var8.unlock();
            }

            var8.unlock();
         }
      }
   }

   @Throws(java/io/IOException::class)
   public fun write(fileOffset: Long, source: Buffer, byteCount: Long) {
      if (!this.readWrite) {
         throw new IllegalStateException("file handle is read-only".toString());
      } else {
         label50: {
            val var8: Lock = this.lock;
            this.lock.lock();

            try {
               if (this.closed) {
                  throw new IllegalStateException("closed".toString());
               }
            } catch (var11: java.lang.Throwable) {
               var8.unlock();
            }

            var8.unlock();
         }
      }
   }

   @Throws(java/io/IOException::class)
   public fun flush() {
      if (!this.readWrite) {
         throw new IllegalStateException("file handle is read-only".toString());
      } else {
         label50: {
            val var3: Lock = this.lock;
            this.lock.lock();

            try {
               if (this.closed) {
                  throw new IllegalStateException("closed".toString());
               }
            } catch (var6: java.lang.Throwable) {
               var3.unlock();
            }

            var3.unlock();
         }
      }
   }

   @Throws(java/io/IOException::class)
   public fun source(fileOffset: Long = 0L): Source {
      label27: {
         val var5: Lock = this.lock;
         this.lock.lock();

         try {
            if (this.closed) {
               throw new IllegalStateException("closed".toString());
            }

            val var8: Int = this.openStreamCount++;
         } catch (var9: java.lang.Throwable) {
            var5.unlock();
         }

         var5.unlock();
      }
   }

   @Throws(java/io/IOException::class)
   public fun position(source: Source): Long {
      var sourcex: Source = source;
      var bufferSize: Long = 0L;
      if (source is RealBufferedSource) {
         bufferSize = (source as RealBufferedSource).bufferField.size();
         sourcex = (source as RealBufferedSource).source;
      }

      if (sourcex !is FileHandle.FileHandleSource || (sourcex as FileHandle.FileHandleSource).getFileHandle() != this) {
         throw new IllegalArgumentException("source was not created by this FileHandle".toString());
      } else if ((sourcex as FileHandle.FileHandleSource).getClosed()) {
         throw new IllegalStateException("closed".toString());
      } else {
         return (sourcex as FileHandle.FileHandleSource).getPosition() - bufferSize;
      }
   }

   @Throws(java/io/IOException::class)
   public fun reposition(source: Source, position: Long) {
      if (source is RealBufferedSource) {
         val fileHandleSource: Source = (source as RealBufferedSource).source;
         if ((source as RealBufferedSource).source !is FileHandle.FileHandleSource
            || ((source as RealBufferedSource).source as FileHandle.FileHandleSource).getFileHandle() != this) {
            throw new IllegalArgumentException("source was not created by this FileHandle".toString());
         }

         if (((source as RealBufferedSource).source as FileHandle.FileHandleSource).getClosed()) {
            throw new IllegalStateException("closed".toString());
         }

         val var5: Long = (source as RealBufferedSource).bufferField.size();
         val var18: Long = position - ((fileHandleSource as FileHandle.FileHandleSource).getPosition() - var5);
         if (0L <= var18 && var18 < var5) {
            (source as RealBufferedSource).skip(var18);
         } else {
            (source as RealBufferedSource).bufferField.clear();
            (fileHandleSource as FileHandle.FileHandleSource).setPosition(position);
         }
      } else {
         if (source !is FileHandle.FileHandleSource || (source as FileHandle.FileHandleSource).getFileHandle() != this) {
            throw new IllegalArgumentException("source was not created by this FileHandle".toString());
         }

         if ((source as FileHandle.FileHandleSource).getClosed()) {
            throw new IllegalStateException("closed".toString());
         }

         (source as FileHandle.FileHandleSource).setPosition(position);
      }
   }

   @Throws(java/io/IOException::class)
   public fun sink(fileOffset: Long = 0L): Sink {
      if (!this.readWrite) {
         throw new IllegalStateException("file handle is read-only".toString());
      } else {
         label50: {
            val var5: Lock = this.lock;
            this.lock.lock();

            try {
               if (this.closed) {
                  throw new IllegalStateException("closed".toString());
               }

               val var8: Int = this.openStreamCount++;
            } catch (var9: java.lang.Throwable) {
               var5.unlock();
            }

            var5.unlock();
         }
      }
   }

   @Throws(java/io/IOException::class)
   public fun appendingSink(): Sink {
      return this.sink(this.size());
   }

   @Throws(java/io/IOException::class)
   public fun position(sink: Sink): Long {
      var sinkx: Sink = sink;
      var bufferSize: Long = 0L;
      if (sink is RealBufferedSink) {
         bufferSize = (sink as RealBufferedSink).bufferField.size();
         sinkx = (sink as RealBufferedSink).sink;
      }

      if (sinkx !is FileHandle.FileHandleSink || (sinkx as FileHandle.FileHandleSink).getFileHandle() != this) {
         throw new IllegalArgumentException("sink was not created by this FileHandle".toString());
      } else if ((sinkx as FileHandle.FileHandleSink).getClosed()) {
         throw new IllegalStateException("closed".toString());
      } else {
         return (sinkx as FileHandle.FileHandleSink).getPosition() + bufferSize;
      }
   }

   @Throws(java/io/IOException::class)
   public fun reposition(sink: Sink, position: Long) {
      if (sink is RealBufferedSink) {
         val fileHandleSink: Sink = (sink as RealBufferedSink).sink;
         if ((sink as RealBufferedSink).sink !is FileHandle.FileHandleSink
            || ((sink as RealBufferedSink).sink as FileHandle.FileHandleSink).getFileHandle() != this) {
            throw new IllegalArgumentException("sink was not created by this FileHandle".toString());
         }

         if (((sink as RealBufferedSink).sink as FileHandle.FileHandleSink).getClosed()) {
            throw new IllegalStateException("closed".toString());
         }

         (sink as RealBufferedSink).emit();
         (fileHandleSink as FileHandle.FileHandleSink).setPosition(position);
      } else {
         if (sink !is FileHandle.FileHandleSink || (sink as FileHandle.FileHandleSink).getFileHandle() != this) {
            throw new IllegalArgumentException("sink was not created by this FileHandle".toString());
         }

         if ((sink as FileHandle.FileHandleSink).getClosed()) {
            throw new IllegalStateException("closed".toString());
         }

         (sink as FileHandle.FileHandleSink).setPosition(position);
      }
   }

   @Throws(java/io/IOException::class)
   public override fun close() {
      label30: {
         val var3: Lock = this.lock;
         this.lock.lock();

         label27: {
            label26: {
               try {
                  if (this.closed) {
                     break label27;
                  }

                  this.closed = true;
                  if (this.openStreamCount != 0) {
                     break label26;
                  }
               } catch (var6: java.lang.Throwable) {
                  var3.unlock();
               }

               var3.unlock();
            }

            var3.unlock();
         }

         var3.unlock();
      }
   }

   @Throws(java/io/IOException::class)
   protected abstract fun protectedRead(fileOffset: Long, array: ByteArray, arrayOffset: Int, byteCount: Int): Int {
   }

   @Throws(java/io/IOException::class)
   protected abstract fun protectedWrite(fileOffset: Long, array: ByteArray, arrayOffset: Int, byteCount: Int) {
   }

   @Throws(java/io/IOException::class)
   protected abstract fun protectedFlush() {
   }

   @Throws(java/io/IOException::class)
   protected abstract fun protectedResize(size: Long) {
   }

   @Throws(java/io/IOException::class)
   protected abstract fun protectedSize(): Long {
   }

   @Throws(java/io/IOException::class)
   protected abstract fun protectedClose() {
   }

   private fun readNoCloseCheck(fileOffset: Long, sink: Buffer, byteCount: Long): Long {
      if (byteCount < 0L) {
         throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
      } else {
         var currentOffset: Long = fileOffset;
         val targetOffset: Long = fileOffset + byteCount;

         while (currentOffset < targetOffset) {
            val tail: Segment = sink.writableSegment$okio(1);
            val readByteCount: Int = this.protectedRead(
               currentOffset, tail.data, tail.limit, (int)Math.min(targetOffset - currentOffset, (long)(8192 - tail.limit))
            );
            if (readByteCount == -1) {
               if (tail.pos == tail.limit) {
                  sink.head = tail.pop();
                  SegmentPool.recycle(tail);
               }

               if (fileOffset == currentOffset) {
                  return -1L;
               }
               break;
            }

            tail.limit += readByteCount;
            currentOffset += readByteCount;
            sink.setSize$okio(sink.size() + (long)readByteCount);
         }

         return currentOffset - fileOffset;
      }
   }

   private fun writeNoCloseCheck(fileOffset: Long, source: Buffer, byteCount: Long) {
      -SegmentedByteString.checkOffsetAndCount(source.size(), 0L, byteCount);
      var currentOffset: Long = fileOffset;
      val targetOffset: Long = fileOffset + byteCount;

      while (currentOffset < targetOffset) {
         val var10000: Segment = source.head;
         val toCopy: Int = (int)Math.min(targetOffset - currentOffset, (long)(var10000.limit - var10000.pos));
         this.protectedWrite(currentOffset, var10000.data, var10000.pos, toCopy);
         var10000.pos += toCopy;
         currentOffset += toCopy;
         source.setSize$okio(source.size() - (long)toCopy);
         if (var10000.pos == var10000.limit) {
            source.head = var10000.pop();
            SegmentPool.recycle(var10000);
         }
      }
   }

   @SourceDebugExtension(["SMAP\nFileHandle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileHandle.kt\nokio/FileHandle$FileHandleSink\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 -JvmPlatform.kt\nokio/_JvmPlatformKt\n*L\n1#1,444:1\n1#2:445\n40#3:446\n*S KotlinDebug\n*F\n+ 1 FileHandle.kt\nokio/FileHandle$FileHandleSink\n*L\n410#1:446\n*E\n"])
   private class FileHandleSink(fileHandle: FileHandle, position: Long) : Sink {
      public final val fileHandle: FileHandle

      public final var position: Long
         internal set

      public final var closed: Boolean
         internal set

      init {
         this.fileHandle = fileHandle;
         this.position = position;
      }

      public override fun write(source: Buffer, byteCount: Long) {
         if (this.closed) {
            throw new IllegalStateException("closed".toString());
         } else {
            FileHandle.access$writeNoCloseCheck(this.fileHandle, this.position, source, byteCount);
            this.position += byteCount;
         }
      }

      public override fun flush() {
         if (this.closed) {
            throw new IllegalStateException("closed".toString());
         } else {
            this.fileHandle.protectedFlush();
         }
      }

      public override fun timeout(): Timeout {
         return Timeout.NONE;
      }

      public override fun close() {
         if (!this.closed) {
            label48: {
               this.closed = true;
               val var3: Lock = this.fileHandle.getLock();
               var3.lock();

               label25: {
                  try {
                     FileHandle.access$setOpenStreamCount$p(this.fileHandle, FileHandle.access$getOpenStreamCount$p(this.fileHandle) + -1);
                     if (FileHandle.access$getOpenStreamCount$p(this.fileHandle) != 0 || !FileHandle.access$getClosed$p(this.fileHandle)) {
                        break label25;
                     }
                  } catch (var7: java.lang.Throwable) {
                     var3.unlock();
                  }

                  var3.unlock();
               }

               var3.unlock();
            }
         }
      }
   }

   @SourceDebugExtension(["SMAP\nFileHandle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileHandle.kt\nokio/FileHandle$FileHandleSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 -JvmPlatform.kt\nokio/_JvmPlatformKt\n*L\n1#1,444:1\n1#2:445\n40#3:446\n*S KotlinDebug\n*F\n+ 1 FileHandle.kt\nokio/FileHandle$FileHandleSource\n*L\n436#1:446\n*E\n"])
   private class FileHandleSource(fileHandle: FileHandle, position: Long) : Source {
      public final val fileHandle: FileHandle

      public final var position: Long
         internal set

      public final var closed: Boolean
         internal set

      init {
         this.fileHandle = fileHandle;
         this.position = position;
      }

      public override fun read(sink: Buffer, byteCount: Long): Long {
         if (this.closed) {
            throw new IllegalStateException("closed".toString());
         } else {
            val result: Long = FileHandle.access$readNoCloseCheck(this.fileHandle, this.position, sink, byteCount);
            if (result != -1L) {
               this.position += result;
            }

            return result;
         }
      }

      public override fun timeout(): Timeout {
         return Timeout.NONE;
      }

      public override fun close() {
         if (!this.closed) {
            label48: {
               this.closed = true;
               val var3: Lock = this.fileHandle.getLock();
               var3.lock();

               label25: {
                  try {
                     FileHandle.access$setOpenStreamCount$p(this.fileHandle, FileHandle.access$getOpenStreamCount$p(this.fileHandle) + -1);
                     if (FileHandle.access$getOpenStreamCount$p(this.fileHandle) != 0 || !FileHandle.access$getClosed$p(this.fileHandle)) {
                        break label25;
                     }
                  } catch (var7: java.lang.Throwable) {
                     var3.unlock();
                  }

                  var3.unlock();
               }

               var3.unlock();
            }
         }
      }
   }
}
