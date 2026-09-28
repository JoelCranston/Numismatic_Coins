package okio

import java.security.InvalidKeyException
import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

public class HashingSource : ForwardingSource, Source {
   private final val messageDigest: MessageDigest?
   private final val mac: Mac?

   public final val hash: ByteString
      public final get() {
         val var10000: ByteArray;
         if (this.messageDigest != null) {
            var10000 = this.messageDigest.digest();
         } else {
            val var2: Mac = this.mac;
            var10000 = var2.doFinal();
         }

         return new ByteString(var10000);
      }


   internal constructor(source: Source, digest: MessageDigest) : super(source) {
      this.messageDigest = digest;
      this.mac = null;
   }

   internal constructor(source: Source, algorithm: String)  {
      val var10002: MessageDigest = MessageDigest.getInstance(algorithm);
      this(source, var10002);
   }

   internal constructor(source: Source, mac: Mac) : super(source) {
      this.mac = mac;
      this.messageDigest = null;
   }

   internal constructor(source: Source, key: ByteString, algorithm: String)  {
      val var9: Source = source;
      val var8: HashingSource = this;

      var var14: Mac;
      var var10000: HashingSource;
      var var10001: Source;
      try {
         var14 = Mac.getInstance(algorithm);
         var14.init(new SecretKeySpec(key.toByteArray(), algorithm));
         var10000 = var8;
         var10001 = var9;
         var14 = var14;
      } catch (var13: InvalidKeyException) {
         throw new IllegalArgumentException(var13);
      }

      var10000./* $VF: Unable to resugar constructor */<init>(var10001, var14);
   }

   @Throws(java/io/IOException::class)
   public override fun read(sink: Buffer, byteCount: Long): Long {
      val result: Long = super.read(sink, byteCount);
      if (result != -1L) {
         var start: Long = sink.size() - result;
         var offset: Long = sink.size();
         var var10000: Segment = sink.head;

         var s: Segment;
         for (s = var10000; offset > start; offset -= var10000.limit - var10000.pos) {
            var10000 = s.prev;
            s = var10000;
         }

         while (offset < sink.size()) {
            val pos: Int = (int)(s.pos + start - offset);
            if (this.messageDigest != null) {
               this.messageDigest.update(s.data, pos, s.limit - pos);
            } else {
               val var13: Mac = this.mac;
               var13.update(s.data, pos, s.limit - pos);
            }

            offset += s.limit - s.pos;
            start = offset;
            var10000 = s.next;
            s = var10000;
         }
      }

      return result;
   }

   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "hash", imports = []), level = DeprecationLevel.ERROR)
   @JvmName(name = "-deprecated_hash")
   public fun hash(): ByteString {
      return this.hash();
   }

   public companion object {
      public fun md5(source: Source): HashingSource {
         return new HashingSource(source, "MD5");
      }

      public fun sha1(source: Source): HashingSource {
         return new HashingSource(source, "SHA-1");
      }

      public fun sha256(source: Source): HashingSource {
         return new HashingSource(source, "SHA-256");
      }

      public fun sha512(source: Source): HashingSource {
         return new HashingSource(source, "SHA-512");
      }

      public fun hmacSha1(source: Source, key: ByteString): HashingSource {
         return new HashingSource(source, key, "HmacSHA1");
      }

      public fun hmacSha256(source: Source, key: ByteString): HashingSource {
         return new HashingSource(source, key, "HmacSHA256");
      }

      public fun hmacSha512(source: Source, key: ByteString): HashingSource {
         return new HashingSource(source, key, "HmacSHA512");
      }
   }
}
