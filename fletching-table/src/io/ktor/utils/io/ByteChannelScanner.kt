package io.ktor.utils.io

import io.ktor.utils.io.ByteChannelScanner.findNext.1
import java.io.IOException
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.SourcesKt
import kotlinx.io.bytestring.ByteString
import kotlinx.io.bytestring.ByteStringKt

internal class ByteChannelScanner(channel: ByteReadChannel, matchString: ByteString, writeChannel: ByteWriteChannel, limit: Long = java.lang.Long.MAX_VALUE) {
   private final val channel: ByteReadChannel
   private final val matchString: ByteString
   private final val writeChannel: ByteWriteChannel
   private final val limit: Long
   private final val input: Source
   private final val partialMatchTable: IntArray
   private final val partialMatchBuffer: Buffer
   private final var bytesRead: Long
   private final var matchIndex: Int

   init {
      this.channel = channel;
      this.matchString = matchString;
      this.writeChannel = writeChannel;
      this.limit = limit;
      if (this.matchString.getSize() <= 0) {
         throw new IllegalArgumentException("Empty match string not permitted for scanning".toString());
      } else {
         this.input = this.channel.getReadBuffer();
         this.partialMatchTable = this.buildPartialMatchTable();
         this.partialMatchBuffer = new Buffer();
      }
   }

   internal suspend fun findNext(ignoreMissing: Boolean = ...): Long {
      var `$continuation`: Continuation;
      label107: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label107;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            this.bytesRead = 0L;
            break;
         case 1:
            ignoreMissing = `$continuation`.Z$0;
            ResultKt.throwOnFailure(`$result`);
            if (!`$result` as java.lang.Boolean) {
               if (!ignoreMissing) {
                  throw new IOException("Expected \"${this.toSingleLineString(this.matchString)}\" but encountered end of input");
               }

               this.bytesRead = this.bytesRead + this.partialMatchBuffer.transferTo(this.writeChannel.getWriteBuffer());
               val var8: ByteWriteChannel = this.writeChannel;
               `$continuation`.Z$0 = ignoreMissing;
               `$continuation`.label = 4;
               if (var8.flush(`$continuation`) === var5) {
                  return var5;
               }

               return Boxing.boxLong(this.bytesRead);
            }

            `$continuation`.Z$0 = ignoreMissing;
            `$continuation`.label = 2;
            if (this.advanceToNextPotentialMatch(`$continuation`) === var5) {
               return var5;
            }

            `$continuation`.Z$0 = ignoreMissing;
            `$continuation`.label = 3;
            val var7: Any = this.checkFullMatch(`$continuation`);
            if (var7 === var5) {
               return var5;
            }

            if (var7 as java.lang.Boolean) {
               return Boxing.boxLong(this.bytesRead);
            }
            break;
         case 2:
            ignoreMissing = `$continuation`.Z$0;
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.Z$0 = ignoreMissing;
            `$continuation`.label = 3;
            val var10000: Any = this.checkFullMatch(`$continuation`);
            if (var10000 === var5) {
               return var5;
            }

            if (var10000 as java.lang.Boolean) {
               return Boxing.boxLong(this.bytesRead);
            }
            break;
         case 3:
            ignoreMissing = `$continuation`.Z$0;
            ResultKt.throwOnFailure(`$result`);
            if (`$result` as java.lang.Boolean) {
               return Boxing.boxLong(this.bytesRead);
            }
            break;
         case 4:
            ignoreMissing = `$continuation`.Z$0;
            ResultKt.throwOnFailure(`$result`);
            return Boxing.boxLong(this.bytesRead);
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      var var12: Any;
      do {
         while (!this.input.exhausted()) {
            `$continuation`.Z$0 = ignoreMissing;
            `$continuation`.label = 2;
            if (this.advanceToNextPotentialMatch(`$continuation`) === var5) {
               return var5;
            }

            `$continuation`.Z$0 = ignoreMissing;
            `$continuation`.label = 3;
            var12 = this.checkFullMatch(`$continuation`);
            if (var12 === var5) {
               return var5;
            }

            if (var12 as java.lang.Boolean) {
               return Boxing.boxLong(this.bytesRead);
            }
         }

         var12 = this.channel;
         `$continuation`.Z$0 = ignoreMissing;
         `$continuation`.label = 1;
         var12 = ByteReadChannel.awaitContent$default((ByteReadChannel)var12, 0, `$continuation`, 1, null);
         if (var12 === var5) {
            return var5;
         }

         if (!var12 as java.lang.Boolean) {
            if (!ignoreMissing) {
               throw new IOException("Expected \"${this.toSingleLineString(this.matchString)}\" but encountered end of input");
            }

            this.bytesRead = this.bytesRead + this.partialMatchBuffer.transferTo(this.writeChannel.getWriteBuffer());
            var12 = this.writeChannel;
            `$continuation`.Z$0 = ignoreMissing;
            `$continuation`.label = 4;
            if (((ByteWriteChannel)var12).flush(`$continuation`) === var5) {
               return var5;
            }

            return Boxing.boxLong(this.bytesRead);
         }

         `$continuation`.Z$0 = ignoreMissing;
         `$continuation`.label = 2;
         if (this.advanceToNextPotentialMatch(`$continuation`) === var5) {
            return var5;
         }

         `$continuation`.Z$0 = ignoreMissing;
         `$continuation`.label = 3;
         var12 = this.checkFullMatch(`$continuation`);
         if (var12 === var5) {
            return var5;
         }
      } while (!(java.lang.Boolean)var12);

      return Boxing.boxLong(this.bytesRead);
   }

   private fun buildPartialMatchTable(): IntArray {
      val table: IntArray = new int[this.matchString.getSize()];
      var j: Int = 0;
      var i: Int = 1;

      for (int var4 = this.matchString.getSize(); i < var4; i++) {
         while (j > 0 && this.matchString.get(i) != this.matchString.get(j)) {
            j = table[j - 1];
         }

         if (this.matchString.get(i) == this.matchString.get(j)) {
            j++;
         }

         table[i] = j;
      }

      return table;
   }

   private suspend fun advanceToNextPotentialMatch() {
      var `$continuation`: Continuation;
      label77: {
         if (`$completion` is io.ktor.utils.io.ByteChannelScanner.advanceToNextPotentialMatch.1) {
            `$continuation` = `$completion` as io.ktor.utils.io.ByteChannelScanner.advanceToNextPotentialMatch.1;
            if (((`$completion` as io.ktor.utils.io.ByteChannelScanner.advanceToNextPotentialMatch.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label77;
            }
         }

         `$continuation` = new io.ktor.utils.io.ByteChannelScanner.advanceToNextPotentialMatch.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            if (this.input.exhausted()) {
               var var10: ByteReadChannel = this.channel;
               `$continuation`.label = 1;
               var10 = (ByteReadChannel)ByteReadChannel.awaitContent$default(var10, 0, `$continuation`, 1, null);
               if (var10 === var6) {
                  return var6;
               }

               if (!var10 as java.lang.Boolean) {
                  return Unit.INSTANCE;
               }
            }
            break;
         case 1:
            ResultKt.throwOnFailure(`$result`);
            if (!`$result` as java.lang.Boolean) {
               return Unit.INSTANCE;
            }
            break;
         case 2:
            val var7: Long = `$continuation`.J$0;
            ResultKt.throwOnFailure(`$result`);
            if (this.input.exhausted()) {
               var var10000: ByteReadChannel = this.channel;
               `$continuation`.label = 1;
               var10000 = (ByteReadChannel)ByteReadChannel.awaitContent$default(var10000, 0, `$continuation`, 1, null);
               if (var10000 === var6) {
                  return var6;
               }

               if (!var10000 as java.lang.Boolean) {
                  return Unit.INSTANCE;
               }
            }
            break;
         case 3:
            val nextMatchx: Long = `$continuation`.J$0;
            ResultKt.throwOnFailure(`$result`);
            return Unit.INSTANCE;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while (true) {
         val var8: Long = SourcesKt.indexOf$default(this.input, this.matchString.get(0), 0L, 0L, 6, null);
         if (var8 != -1L) {
            this.checkBounds(var8);
            val var16: Long = this.bytesRead;
            val var10002: Source = this.input;
            val var10003: Sink = this.writeChannel.getWriteBuffer();
            this.bytesRead = var16 + var10002.readAtMostTo(var10003 as Buffer, var8);
            val var15: ByteWriteChannel = this.writeChannel;
            `$continuation`.J$0 = var8;
            `$continuation`.label = 3;
            if (ByteWriteChannelKt.flushIfNeeded(var15, `$continuation`) === var6) {
               return var6;
            }

            return Unit.INSTANCE;
         }

         val var10001: Source = this.input;
         this.checkBounds((var10001 as Buffer).getSize());
         this.bytesRead = this.bytesRead + (this.input as Buffer).transferTo(this.writeChannel.getWriteBuffer());
         var var12: ByteWriteChannel = this.writeChannel;
         `$continuation`.J$0 = var8;
         `$continuation`.label = 2;
         if (ByteWriteChannelKt.flushIfNeeded(var12, `$continuation`) === var6) {
            return var6;
         }

         if (this.input.exhausted()) {
            val var13: ByteReadChannel = this.channel;
            `$continuation`.label = 1;
            var12 = (ByteWriteChannel)ByteReadChannel.awaitContent$default(var13, 0, `$continuation`, 1, null);
            if (var12 === var6) {
               return var6;
            }

            if (!var12 as java.lang.Boolean) {
               return Unit.INSTANCE;
            }
         }
      }
   }

   private suspend fun checkFullMatch(): Boolean {
      var `$continuation`: Continuation;
      label85: {
         if (`$completion` is io.ktor.utils.io.ByteChannelScanner.checkFullMatch.1) {
            `$continuation` = `$completion` as io.ktor.utils.io.ByteChannelScanner.checkFullMatch.1;
            if (((`$completion` as io.ktor.utils.io.ByteChannelScanner.checkFullMatch.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label85;
            }
         }

         `$continuation` = new io.ktor.utils.io.ByteChannelScanner.checkFullMatch.1(this, `$completion`);
      }

      label78: {
         val `$result`: Any = `$continuation`.result;
         val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               if (this.input.exhausted()) {
                  var var10000: ByteReadChannel = this.channel;
                  `$continuation`.label = 1;
                  var10000 = (ByteReadChannel)ByteReadChannel.awaitContent$default(var10000, 0, `$continuation`, 1, null);
                  if (var10000 === var10) {
                     return var10;
                  }

                  if (!var10000 as java.lang.Boolean) {
                     return Boxing.boxBoolean(false);
                  }
               }
               break;
            case 1:
               ResultKt.throwOnFailure(`$result`);
               if (!`$result` as java.lang.Boolean) {
                  return Boxing.boxBoolean(false);
               }
               break;
            case 2:
               val retained: Long = `$continuation`.J$0;
               val oldMatchIndex: Int = `$continuation`.I$0;
               val var2: Byte = `$continuation`.B$0;
               ResultKt.throwOnFailure(`$result`);
               break label78;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         while (true) {
            val var11: Byte = this.input.readByte();
            if (this.matchIndex > 0 && var11 != this.matchString.get(this.matchIndex)) {
               val var12: Int = this.matchIndex;

               while (this.matchIndex > 0 && byte != this.matchString.get(this.matchIndex)) {
                  this.matchIndex = this.partialMatchTable[this.matchIndex - 1];
               }

               val var13: Long = var12 - this.matchIndex;
               this.checkBounds((long)(var12 - this.matchIndex));
               val var10001: Long = this.bytesRead;
               val var10002: Buffer = this.partialMatchBuffer;
               val var10003: Sink = this.writeChannel.getWriteBuffer();
               this.bytesRead = var10001 + var10002.readAtMostTo(var10003 as Buffer, var13);
               if (this.matchIndex == 0 && var11 != this.matchString.get(this.matchIndex)) {
                  val var17: ByteWriteChannel = this.writeChannel;
                  val var18: Byte = (byte)var11;
                  `$continuation`.B$0 = var11;
                  `$continuation`.I$0 = var12;
                  `$continuation`.J$0 = var13;
                  `$continuation`.label = 2;
                  if (ByteWriteChannelOperationsKt.writeByte(var17, var18, `$continuation`) === var10) {
                     return var10;
                  }
                  break;
               }
            }

            this.matchIndex++;
            if (this.matchIndex == this.matchString.getSize()) {
               return Boxing.boxBoolean(true);
            }

            this.partialMatchBuffer.writeByte((byte)var11);
            if (this.input.exhausted()) {
               var var15: ByteReadChannel = this.channel;
               `$continuation`.label = 1;
               var15 = (ByteReadChannel)ByteReadChannel.awaitContent$default(var15, 0, `$continuation`, 1, null);
               if (var15 === var10) {
                  return var10;
               }

               if (!var15 as java.lang.Boolean) {
                  return Boxing.boxBoolean(false);
               }
            }
         }
      }

      val var6: Int = this.bytesRead++;
      return Boxing.boxBoolean(false);
   }

   private fun checkBounds(extra: Long) {
      if (this.bytesRead + extra > this.limit) {
         throw new IOException("Limit of ${this.limit} bytes exceeded while searching for \"${this.toSingleLineString(this.matchString)}"");
      }
   }

   private fun ByteString.toSingleLineString(): String {
      return StringsKt.replace$default(ByteStringKt.decodeToString(`$this$toSingleLineString`), "\n", "\\n", false, 4, null);
   }
}
