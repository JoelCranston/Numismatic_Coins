package kotlinx.serialization.json.internal

import java.io.InputStream
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.nio.charset.CharsetDecoder
import java.nio.charset.CoderResult
import java.nio.charset.CodingErrorAction

internal class CharsetReader(inputStream: InputStream, charset: Charset) {
   private final val inputStream: InputStream
   private final val charset: Charset
   private final val decoder: CharsetDecoder
   private final val byteBuffer: ByteBuffer
   private final var hasLeftoverPotentiallySurrogateChar: Boolean
   private final var leftoverChar: Char

   init {
      this.inputStream = inputStream;
      this.charset = charset;
      val var10001: CharsetDecoder = this.charset.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
      this.decoder = var10001;
      val var3: ByteBuffer = ByteBuffer.wrap(ByteArrayPool8k.INSTANCE.take());
      this.byteBuffer = var3;
      ((Buffer)this.byteBuffer).flip();
   }

   public fun read(array: CharArray, offset: Int, length: Int): Int {
      if (length == 0) {
         return 0;
      } else if (0 > offset || offset >= array.length || length < 0 || offset + length > array.length) {
         throw new IllegalArgumentException(("Unexpected arguments: $offset, $length, ${array.length}").toString());
      } else {
         var offsetx: Int = offset;
         var lengthx: Int = length;
         var bytesRead: Int = 0;
         if (this.hasLeftoverPotentiallySurrogateChar) {
            array[offset] = this.leftoverChar;
            offsetx = offset + 1;
            lengthx = length - 1;
            this.hasLeftoverPotentiallySurrogateChar = false;
            bytesRead = 1;
            if (lengthx == 0) {
               return 1;
            }
         }

         if (lengthx == 1) {
            val c: Int = this.oneShotReadSlowPath();
            if (c == -1) {
               return if (bytesRead == 0) -1 else bytesRead;
            } else {
               array[offsetx] = (char)c;
               return bytesRead + 1;
            }
         } else {
            return this.doRead(array, offsetx, lengthx) + bytesRead;
         }
      }
   }

   private fun doRead(array: CharArray, offset: Int, length: Int): Int {
      var charBuffer: CharBuffer = CharBuffer.wrap(array, offset, length);
      if (charBuffer.position() != 0) {
         charBuffer = charBuffer.slice();
      }

      var isEof: Boolean = false;

      while (true) {
         val cr: CoderResult = this.decoder.decode(this.byteBuffer, charBuffer, isEof);
         if (cr.isUnderflow()) {
            if (isEof || !charBuffer.hasRemaining()) {
               break;
            }

            if (this.fillByteBuffer() < 0) {
               isEof = true;
               if (charBuffer.position() == 0 && !this.byteBuffer.hasRemaining()) {
                  break;
               }

               this.decoder.reset();
            }
         } else {
            if (cr.isOverflow()) {
               if (_Assertions.ENABLED && charBuffer.position() <= 0) {
                  throw new AssertionError("Assertion failed");
               }
               break;
            }

            cr.throwException();
         }
      }

      if (isEof) {
         this.decoder.reset();
      }

      return if (charBuffer.position() == 0) -1 else charBuffer.position();
   }

   private fun fillByteBuffer(): Int {
      label31: {
         this.byteBuffer.compact();

         label28: {
            try {
               val limit: Int = this.byteBuffer.limit();
               val position: Int = this.byteBuffer.position();
               val bytesRead: Int = this.inputStream
                  .read(this.byteBuffer.array(), this.byteBuffer.arrayOffset() + position, if (position <= limit) limit - position else 0);
               if (bytesRead < 0) {
                  break label28;
               }

               val var10000: ByteBuffer = this.byteBuffer;
               var10000.position(position + bytesRead);
            } catch (var6: java.lang.Throwable) {
               ((Buffer)this.byteBuffer).flip();
            }

            ((Buffer)this.byteBuffer).flip();
         }

         ((Buffer)this.byteBuffer).flip();
      }
   }

   private fun oneShotReadSlowPath(): Int {
      if (this.hasLeftoverPotentiallySurrogateChar) {
         this.hasLeftoverPotentiallySurrogateChar = false;
         return this.leftoverChar;
      } else {
         val array: CharArray = new char[2];
         val bytesRead: Int = this.read(array, 0, 2);
         var var10000: Int;
         switch (bytesRead) {
            case -1:
               var10000 = -1;
               break;
            case 0:
            default:
               throw new IllegalStateException(("Unreachable state: $bytesRead").toString());
            case 1:
               var10000 = array[0];
               break;
            case 2:
               this.leftoverChar = array[1];
               this.hasLeftoverPotentiallySurrogateChar = true;
               var10000 = array[0];
         }

         return var10000;
      }
   }

   public fun release() {
      val var10000: ByteArrayPool8k = ByteArrayPool8k.INSTANCE;
      val var10001: ByteArray = this.byteBuffer.array();
      var10000.release(var10001);
   }
}
