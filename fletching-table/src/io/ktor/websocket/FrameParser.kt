package io.ktor.websocket

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.concurrent.atomic.AtomicReference
import kotlin.enums.EnumEntries
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nFrameParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameParser.kt\nio/ktor/websocket/FrameParser\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,155:1\n1#2:156\n*E\n"])
public class FrameParser {
   private final val state: AtomicReference<io.ktor.websocket.FrameParser.State> = new AtomicReference(FrameParser.State.HEADER0)

   public final var fin: Boolean
      private set

   public final var rsv1: Boolean
      private set

   public final var rsv2: Boolean
      private set

   public final var rsv3: Boolean
      private set

   public final var mask: Boolean
      private set

   private final var opcode: Int
   private final var lastOpcode: Int
   private final var lengthLength: Int

   public final var length: Long
      private set

   public final var maskKey: Int?
      private set

   public final val frameType: FrameType
      public final get() {
         val var10000: FrameType = FrameType.Companion.get(this.opcode);
         if (var10000 == null) {
            throw new ProtocolViolationException("Unsupported opcode ${Integer.toHexString(this.opcode)}");
         } else {
            return var10000;
         }
      }


   public final val bodyReady: Boolean
      public final get() {
         return this.state.get() === FrameParser.State.BODY;
      }


   public fun bodyComplete() {
      if (!this.state.compareAndSet(FrameParser.State.BODY, FrameParser.State.HEADER0)) {
         throw new IllegalStateException("It should be state BODY but it is ${this.state.get()}");
      } else {
         this.opcode = 0;
         this.length = 0L;
         this.lengthLength = 0;
         this.maskKey = null;
      }
   }

   public fun frame(bb: ByteBuffer) {
      if (!(bb.order() == ByteOrder.BIG_ENDIAN)) {
         throw new IllegalArgumentException(("Buffer order should be BIG_ENDIAN but it is ${bb.order()}").toString());
      } else {
         while (this.handleStep(bb)) {
         }
      }
   }

   private fun handleStep(bb: ByteBuffer): Boolean {
      val var10000: Any = this.state.get();
      var var2: Boolean;
      switch (FrameParser.WhenMappings.$EnumSwitchMapping$0[((FrameParser.State)var10000).ordinal()]) {
         case 1:
            var2 = this.parseHeader1(bb);
            break;
         case 2:
            var2 = this.parseLength(bb);
            break;
         case 3:
            var2 = this.parseMaskKey(bb);
            break;
         case 4:
            var2 = false;
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      return var2;
   }

   private fun parseHeader1(bb: ByteBuffer): Boolean {
      if (bb.remaining() < 2) {
         return false;
      } else {
         val flagsAndOpcode: Int = bb.get();
         val maskAndLength1: Int = bb.get();
         this.fin = (flagsAndOpcode and 128) != 0;
         this.rsv1 = (flagsAndOpcode and 64) != 0;
         this.rsv2 = (flagsAndOpcode and 32) != 0;
         this.rsv3 = (flagsAndOpcode and 16) != 0;
         this.opcode = flagsAndOpcode and 15;
         if (this.opcode == 0 && this.lastOpcode == 0) {
            throw new ProtocolViolationException("Can't continue finished frames");
         } else {
            if (this.opcode == 0) {
               this.opcode = this.lastOpcode;
            } else if (this.lastOpcode != 0 && !this.getFrameType().getControlFrame()) {
               throw new ProtocolViolationException("Can't start new data frame before finishing previous one");
            }

            if (!this.getFrameType().getControlFrame()) {
               this.lastOpcode = if (this.fin) 0 else this.opcode;
            } else if (!this.fin) {
               throw new ProtocolViolationException("control frames can't be fragmented");
            }

            this.mask = (maskAndLength1 and 128) != 0;
            val length1: Int = maskAndLength1 and 127;
            if (this.getFrameType().getControlFrame() && (maskAndLength1 and 127) > 125) {
               throw new ProtocolViolationException("control frames can't be larger than 125 bytes");
            } else {
               var var10001: Byte;
               switch (length1) {
                  case 126:
                     var10001 = 2;
                     break;
                  case 127:
                     var10001 = 8;
                     break;
                  default:
                     var10001 = 0;
               }

               this.lengthLength = var10001;
               this.length = if (this.lengthLength == 0) length1 else 0L;
               if (this.lengthLength > 0) {
                  this.state.set(FrameParser.State.LENGTH);
               } else if (this.mask) {
                  this.state.set(FrameParser.State.MASK_KEY);
               } else {
                  this.state.set(FrameParser.State.BODY);
               }

               return true;
            }
         }
      }
   }

   private fun parseLength(bb: ByteBuffer): Boolean {
      if (bb.remaining() < this.lengthLength) {
         return false;
      } else {
         var var10001: Long;
         switch (this.lengthLength) {
            case 2:
               var10001 = bb.getShort() and 65535L;
               break;
            case 8:
               var10001 = bb.getLong();
               break;
            default:
               throw new IllegalStateException();
         }

         this.length = var10001;
         this.state.set(if (this.mask) FrameParser.State.MASK_KEY else FrameParser.State.BODY);
         return true;
      }
   }

   private fun parseMaskKey(bb: ByteBuffer): Boolean {
      if (bb.remaining() < 4) {
         return false;
      } else {
         this.maskKey = bb.getInt();
         this.state.set(FrameParser.State.BODY);
         return true;
      }
   }

   public enum class State {
      HEADER0,
      LENGTH,
      MASK_KEY,
      BODY
      @JvmStatic
      fun getEntries(): EnumEntries<FrameParser.State> {
         return $ENTRIES;
      }
   }
}
