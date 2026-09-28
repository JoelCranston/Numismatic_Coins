package okio.internal

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket
import java.util.concurrent.atomic.AtomicInteger
import kotlin.jvm.internal.SourceDebugExtension
import okio.AsyncTimeout
import okio.Buffer
import okio.Segment
import okio.SegmentPool
import okio.Sink
import okio.Source

internal class DefaultSocket(socket: Socket) : okio.Socket {
   public final val socket: Socket
   private final var closeBits: AtomicInteger
   public open val source: Source
   public open val sink: Sink

   init {
      this.socket = socket;
      this.closeBits = new AtomicInteger();
      this.source = new DefaultSocket.SocketSource(this);
      this.sink = new DefaultSocket.SocketSink(this);
   }

   public override fun cancel() {
      this.socket.close();
   }

   public override fun toString(): String {
      val var10000: java.lang.String = this.socket.toString();
      return var10000;
   }

   @SourceDebugExtension(["SMAP\nDefaultSocket.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultSocket.kt\nokio/internal/DefaultSocket$SocketSink\n+ 2 Util.kt\nokio/-SegmentedByteString\n+ 3 AsyncTimeout.kt\nokio/AsyncTimeout\n*L\n1#1,176:1\n85#2:177\n195#3,11:178\n195#3,11:189\n195#3,11:200\n*S KotlinDebug\n*F\n+ 1 DefaultSocket.kt\nokio/internal/DefaultSocket$SocketSink\n*L\n60#1:177\n61#1:178,11\n77#1:189,11\n83#1:200,11\n*E\n"])
   public inner class SocketSink : Sink {
      private final val outputStream: OutputStream
      private final val timeout: SocketAsyncTimeout

      public override fun write(source: Buffer, byteCount: Long) {
         // $VF: Couldn't be decompiled
         // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
         // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
         //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
         //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
         //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
         //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
         //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
         //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.initExprents(IfStatement.java:276)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:189)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:148)
         //
         // Bytecode:
         // 000: aload 1
         // 001: ldc "source"
         // 003: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullParameter (Ljava/lang/Object;Ljava/lang/String;)V
         // 006: aload 1
         // 007: invokevirtual okio/Buffer.size ()J
         // 00a: lconst_0
         // 00b: lload 2
         // 00c: invokestatic okio/-SegmentedByteString.checkOffsetAndCount (JJJ)V
         // 00f: lload 2
         // 010: lstore 4
         // 012: lload 4
         // 014: lconst_0
         // 015: lcmp
         // 016: ifle 111
         // 019: aload 0
         // 01a: getfield okio/internal/DefaultSocket$SocketSink.timeout Lokio/internal/SocketAsyncTimeout;
         // 01d: invokevirtual okio/internal/SocketAsyncTimeout.throwIfReached ()V
         // 020: aload 1
         // 021: getfield okio/Buffer.head Lokio/Segment;
         // 024: dup
         // 025: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNull (Ljava/lang/Object;)V
         // 028: astore 6
         // 02a: lload 4
         // 02c: lstore 8
         // 02e: aload 6
         // 030: getfield okio/Segment.limit I
         // 033: aload 6
         // 035: getfield okio/Segment.pos I
         // 038: isub
         // 039: istore 10
         // 03b: bipush 0
         // 03c: istore 11
         // 03e: lload 8
         // 040: iload 10
         // 042: i2l
         // 043: invokestatic java/lang/Math.min (JJ)J
         // 046: nop
         // 047: l2i
         // 048: istore 7
         // 04a: aload 0
         // 04b: getfield okio/internal/DefaultSocket$SocketSink.timeout Lokio/internal/SocketAsyncTimeout;
         // 04e: checkcast okio/AsyncTimeout
         // 051: astore 8
         // 053: bipush 0
         // 054: istore 9
         // 056: bipush 0
         // 057: istore 10
         // 059: aload 8
         // 05b: invokevirtual okio/AsyncTimeout.enter ()V
         // 05e: nop
         // 05f: bipush 0
         // 060: istore 11
         // 062: aload 0
         // 063: getfield okio/internal/DefaultSocket$SocketSink.outputStream Ljava/io/OutputStream;
         // 066: aload 6
         // 068: getfield okio/Segment.data [B
         // 06b: aload 6
         // 06d: getfield okio/Segment.pos I
         // 070: iload 7
         // 072: invokevirtual java/io/OutputStream.write ([BII)V
         // 075: nop
         // 076: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
         // 079: astore 12
         // 07b: bipush 1
         // 07c: istore 10
         // 07e: aload 12
         // 080: astore 13
         // 082: aload 8
         // 084: invokevirtual okio/AsyncTimeout.exit ()Z
         // 087: istore 11
         // 089: iload 11
         // 08b: ifeq 095
         // 08e: aload 8
         // 090: aconst_null
         // 091: invokevirtual okio/AsyncTimeout.access$newTimeoutException (Ljava/io/IOException;)Ljava/io/IOException;
         // 094: athrow
         // 095: goto 0d2
         // 098: astore 13
         // 09a: aload 8
         // 09c: invokevirtual okio/AsyncTimeout.exit ()Z
         // 09f: ifne 0aa
         // 0a2: aload 13
         // 0a4: checkcast java/lang/Throwable
         // 0a7: goto 0b4
         // 0aa: aload 8
         // 0ac: aload 13
         // 0ae: invokevirtual okio/AsyncTimeout.access$newTimeoutException (Ljava/io/IOException;)Ljava/io/IOException;
         // 0b1: checkcast java/lang/Throwable
         // 0b4: athrow
         // 0b5: astore 13
         // 0b7: aload 8
         // 0b9: invokevirtual okio/AsyncTimeout.exit ()Z
         // 0bc: istore 11
         // 0be: iload 11
         // 0c0: ifeq 0cf
         // 0c3: iload 10
         // 0c5: ifeq 0cf
         // 0c8: aload 8
         // 0ca: aconst_null
         // 0cb: invokevirtual okio/AsyncTimeout.access$newTimeoutException (Ljava/io/IOException;)Ljava/io/IOException;
         // 0ce: athrow
         // 0cf: aload 13
         // 0d1: athrow
         // 0d2: aload 6
         // 0d4: aload 6
         // 0d6: getfield okio/Segment.pos I
         // 0d9: iload 7
         // 0db: iadd
         // 0dc: putfield okio/Segment.pos I
         // 0df: lload 4
         // 0e1: iload 7
         // 0e3: i2l
         // 0e4: lsub
         // 0e5: lstore 4
         // 0e7: aload 1
         // 0e8: aload 1
         // 0e9: invokevirtual okio/Buffer.size ()J
         // 0ec: iload 7
         // 0ee: i2l
         // 0ef: lsub
         // 0f0: invokevirtual okio/Buffer.setSize$okio (J)V
         // 0f3: aload 6
         // 0f5: getfield okio/Segment.pos I
         // 0f8: aload 6
         // 0fa: getfield okio/Segment.limit I
         // 0fd: if_icmpne 012
         // 100: aload 1
         // 101: aload 6
         // 103: invokevirtual okio/Segment.pop ()Lokio/Segment;
         // 106: putfield okio/Buffer.head Lokio/Segment;
         // 109: aload 6
         // 10b: invokestatic okio/SegmentPool.recycle (Lokio/Segment;)V
         // 10e: goto 012
         // 111: return
      }

      public override fun flush() {
         label38: {
            val `this_$iv`: AsyncTimeout = this.timeout;
            this.timeout.enter();

            try {
               try {
                  ;
               } catch (var7: IOException) {
                  throw if (!`this_$iv`.exit()) var7 as java.lang.Throwable else `this_$iv`.access$newTimeoutException(var7) as java.lang.Throwable;
               }
            } catch (var8: java.lang.Throwable) {
               if (`this_$iv`.exit() && false) {
                  throw `this_$iv`.access$newTimeoutException(null);
               }
            }

            if (`this_$iv`.exit()) {
               throw `this_$iv`.access$newTimeoutException(null);
            }
         }
      }

      public override fun close() {
         // $VF: Couldn't be decompiled
         // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
         // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
         //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
         //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
         //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
         //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
         //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
         //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SwitchStatement.initExprents(SwitchStatement.java:206)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:189)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:148)
         //
         // Bytecode:
         // 00: aload 0
         // 01: getfield okio/internal/DefaultSocket$SocketSink.timeout Lokio/internal/SocketAsyncTimeout;
         // 04: checkcast okio/AsyncTimeout
         // 07: astore 1
         // 08: aload 0
         // 09: getfield okio/internal/DefaultSocket$SocketSink.this$0 Lokio/internal/DefaultSocket;
         // 0c: astore 2
         // 0d: bipush 0
         // 0e: istore 3
         // 0f: bipush 0
         // 10: istore 4
         // 12: aload 1
         // 13: invokevirtual okio/AsyncTimeout.enter ()V
         // 16: nop
         // 17: bipush 0
         // 18: istore 5
         // 1a: aload 2
         // 1b: invokestatic okio/internal/DefaultSocket.access$getCloseBits$p (Lokio/internal/DefaultSocket;)Ljava/util/concurrent/atomic/AtomicInteger;
         // 1e: bipush 1
         // 1f: invokestatic okio/internal/_AtomicKt.setBitsOrZero (Ljava/util/concurrent/atomic/AtomicInteger;I)I
         // 22: tableswitch 49 0 3 30 49 49 39
         // 40: nop
         // 41: aload 1
         // 42: invokevirtual okio/AsyncTimeout.exit ()Z
         // 45: ifeq 48
         // 48: return
         // 49: aload 2
         // 4a: invokevirtual okio/internal/DefaultSocket.getSocket ()Ljava/net/Socket;
         // 4d: invokevirtual java/net/Socket.close ()V
         // 50: goto 8a
         // 53: aload 2
         // 54: invokevirtual okio/internal/DefaultSocket.getSocket ()Ljava/net/Socket;
         // 57: invokevirtual java/net/Socket.isClosed ()Z
         // 5a: ifne 67
         // 5d: aload 2
         // 5e: invokevirtual okio/internal/DefaultSocket.getSocket ()Ljava/net/Socket;
         // 61: invokevirtual java/net/Socket.isOutputShutdown ()Z
         // 64: ifeq 6f
         // 67: aload 1
         // 68: invokevirtual okio/AsyncTimeout.exit ()Z
         // 6b: ifeq 6e
         // 6e: return
         // 6f: aload 0
         // 70: getfield okio/internal/DefaultSocket$SocketSink.outputStream Ljava/io/OutputStream;
         // 73: invokevirtual java/io/OutputStream.flush ()V
         // 76: nop
         // 77: aload 2
         // 78: invokevirtual okio/internal/DefaultSocket.getSocket ()Ljava/net/Socket;
         // 7b: invokevirtual java/net/Socket.shutdownOutput ()V
         // 7e: goto 8a
         // 81: astore 6
         // 83: aload 0
         // 84: getfield okio/internal/DefaultSocket$SocketSink.outputStream Ljava/io/OutputStream;
         // 87: invokevirtual java/io/OutputStream.close ()V
         // 8a: nop
         // 8b: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
         // 8e: astore 7
         // 90: bipush 1
         // 91: istore 4
         // 93: aload 7
         // 95: astore 5
         // 97: aload 1
         // 98: invokevirtual okio/AsyncTimeout.exit ()Z
         // 9b: istore 6
         // 9d: iload 6
         // 9f: ifeq a8
         // a2: aload 1
         // a3: aconst_null
         // a4: invokevirtual okio/AsyncTimeout.access$newTimeoutException (Ljava/io/IOException;)Ljava/io/IOException;
         // a7: athrow
         // a8: goto e1
         // ab: astore 5
         // ad: aload 1
         // ae: invokevirtual okio/AsyncTimeout.exit ()Z
         // b1: ifne bc
         // b4: aload 5
         // b6: checkcast java/lang/Throwable
         // b9: goto c5
         // bc: aload 1
         // bd: aload 5
         // bf: invokevirtual okio/AsyncTimeout.access$newTimeoutException (Ljava/io/IOException;)Ljava/io/IOException;
         // c2: checkcast java/lang/Throwable
         // c5: athrow
         // c6: astore 5
         // c8: aload 1
         // c9: invokevirtual okio/AsyncTimeout.exit ()Z
         // cc: istore 6
         // ce: iload 6
         // d0: ifeq de
         // d3: iload 4
         // d5: ifeq de
         // d8: aload 1
         // d9: aconst_null
         // da: invokevirtual okio/AsyncTimeout.access$newTimeoutException (Ljava/io/IOException;)Ljava/io/IOException;
         // dd: athrow
         // de: aload 5
         // e0: athrow
         // e1: return
      }

      public open fun timeout(): SocketAsyncTimeout {
         return this.timeout;
      }

      public override fun toString(): String {
         return "sink(${this.this$0.getSocket()})";
      }
   }

   @SourceDebugExtension(["SMAP\nDefaultSocket.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultSocket.kt\nokio/internal/DefaultSocket$SocketSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokio/-SegmentedByteString\n+ 4 AsyncTimeout.kt\nokio/AsyncTimeout\n*L\n1#1,176:1\n1#2:177\n85#3:178\n195#4,11:179\n195#4,11:190\n*S KotlinDebug\n*F\n+ 1 DefaultSocket.kt\nokio/internal/DefaultSocket$SocketSource\n*L\n121#1:178\n123#1:179,11\n144#1:190,11\n*E\n"])
   public inner class SocketSource : Source {
      private final val inputStream: InputStream
      private final val timeout: SocketAsyncTimeout

      public override fun read(sink: Buffer, byteCount: Long): Long {
         if (byteCount == 0L) {
            return 0L;
         } else if (byteCount < 0L) {
            throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
         } else {
            label118: {
               this.timeout.throwIfReached();
               val tail: Segment = sink.writableSegment$okio(1);
               val maxToCopy: Int = (int)Math.min(byteCount, (long)(8192 - tail.limit));

               var `this_$iv`: AsyncTimeout;
               try {
                  `this_$iv` = this.timeout;
                  this.timeout.enter();

                  try {
                     try {
                        ;
                     } catch (var13: IOException) {
                        throw if (!`this_$iv`.exit()) var13 as java.lang.Throwable else `this_$iv`.access$newTimeoutException(var13) as java.lang.Throwable;
                     }
                  } catch (var14: java.lang.Throwable) {
                     if (`this_$iv`.exit() && false) {
                        throw `this_$iv`.access$newTimeoutException(null);
                     }
                  }

                  if (`this_$iv`.exit()) {
                     throw `this_$iv`.access$newTimeoutException(null);
                  }
               } catch (var15: AssertionError) {
                  if (_JavaIoKt.isAndroidGetsocknameError(var15)) {
                     throw new IOException(var15);
                  }

                  throw var15;
               }

               if (`this_$iv` == -1) {
                  if (tail.pos == tail.limit) {
                     sink.head = tail.pop();
                     SegmentPool.recycle(tail);
                  }

                  return -1L;
               } else {
                  tail.limit += `this_$iv`;
                  sink.setSize$okio(sink.size() + (long)`this_$iv`);
                  return (long)`this_$iv`;
               }
            }
         }
      }

      public override fun close() {
         // $VF: Couldn't be decompiled
         // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
         // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
         //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
         //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
         //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
         //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
         //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
         //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SwitchStatement.initExprents(SwitchStatement.java:206)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:189)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:148)
         //
         // Bytecode:
         // 00: aload 0
         // 01: getfield okio/internal/DefaultSocket$SocketSource.timeout Lokio/internal/SocketAsyncTimeout;
         // 04: checkcast okio/AsyncTimeout
         // 07: astore 1
         // 08: aload 0
         // 09: getfield okio/internal/DefaultSocket$SocketSource.this$0 Lokio/internal/DefaultSocket;
         // 0c: astore 2
         // 0d: bipush 0
         // 0e: istore 3
         // 0f: bipush 0
         // 10: istore 4
         // 12: aload 1
         // 13: invokevirtual okio/AsyncTimeout.enter ()V
         // 16: nop
         // 17: bipush 0
         // 18: istore 5
         // 1a: aload 2
         // 1b: invokestatic okio/internal/DefaultSocket.access$getCloseBits$p (Lokio/internal/DefaultSocket;)Ljava/util/concurrent/atomic/AtomicInteger;
         // 1e: bipush 2
         // 1f: invokestatic okio/internal/_AtomicKt.setBitsOrZero (Ljava/util/concurrent/atomic/AtomicInteger;I)I
         // 22: tableswitch 49 0 3 30 49 49 39
         // 40: nop
         // 41: aload 1
         // 42: invokevirtual okio/AsyncTimeout.exit ()Z
         // 45: ifeq 48
         // 48: return
         // 49: aload 2
         // 4a: invokevirtual okio/internal/DefaultSocket.getSocket ()Ljava/net/Socket;
         // 4d: invokevirtual java/net/Socket.close ()V
         // 50: goto 83
         // 53: aload 2
         // 54: invokevirtual okio/internal/DefaultSocket.getSocket ()Ljava/net/Socket;
         // 57: invokevirtual java/net/Socket.isClosed ()Z
         // 5a: ifne 67
         // 5d: aload 2
         // 5e: invokevirtual okio/internal/DefaultSocket.getSocket ()Ljava/net/Socket;
         // 61: invokevirtual java/net/Socket.isInputShutdown ()Z
         // 64: ifeq 6f
         // 67: aload 1
         // 68: invokevirtual okio/AsyncTimeout.exit ()Z
         // 6b: ifeq 6e
         // 6e: return
         // 6f: nop
         // 70: aload 2
         // 71: invokevirtual okio/internal/DefaultSocket.getSocket ()Ljava/net/Socket;
         // 74: invokevirtual java/net/Socket.shutdownInput ()V
         // 77: goto 83
         // 7a: astore 6
         // 7c: aload 0
         // 7d: getfield okio/internal/DefaultSocket$SocketSource.inputStream Ljava/io/InputStream;
         // 80: invokevirtual java/io/InputStream.close ()V
         // 83: nop
         // 84: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
         // 87: astore 7
         // 89: bipush 1
         // 8a: istore 4
         // 8c: aload 7
         // 8e: astore 5
         // 90: aload 1
         // 91: invokevirtual okio/AsyncTimeout.exit ()Z
         // 94: istore 6
         // 96: iload 6
         // 98: ifeq a1
         // 9b: aload 1
         // 9c: aconst_null
         // 9d: invokevirtual okio/AsyncTimeout.access$newTimeoutException (Ljava/io/IOException;)Ljava/io/IOException;
         // a0: athrow
         // a1: goto da
         // a4: astore 5
         // a6: aload 1
         // a7: invokevirtual okio/AsyncTimeout.exit ()Z
         // aa: ifne b5
         // ad: aload 5
         // af: checkcast java/lang/Throwable
         // b2: goto be
         // b5: aload 1
         // b6: aload 5
         // b8: invokevirtual okio/AsyncTimeout.access$newTimeoutException (Ljava/io/IOException;)Ljava/io/IOException;
         // bb: checkcast java/lang/Throwable
         // be: athrow
         // bf: astore 5
         // c1: aload 1
         // c2: invokevirtual okio/AsyncTimeout.exit ()Z
         // c5: istore 6
         // c7: iload 6
         // c9: ifeq d7
         // cc: iload 4
         // ce: ifeq d7
         // d1: aload 1
         // d2: aconst_null
         // d3: invokevirtual okio/AsyncTimeout.access$newTimeoutException (Ljava/io/IOException;)Ljava/io/IOException;
         // d6: athrow
         // d7: aload 5
         // d9: athrow
         // da: return
      }

      public open fun timeout(): SocketAsyncTimeout {
         return this.timeout;
      }

      public override fun toString(): String {
         return "source(${this.this$0.getSocket()})";
      }
   }
}
