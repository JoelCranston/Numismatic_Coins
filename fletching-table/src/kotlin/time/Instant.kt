package kotlin.time

import java.io.InvalidObjectException
import java.io.ObjectInputStream
import java.io.Serializable
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension

@SinceKotlin(version = "2.1")
@ExperimentalTime
@SourceDebugExtension(["SMAP\nInstant.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Instant.kt\nkotlin/time/Instant\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Instant.kt\nkotlin/time/InstantKt\n+ 4 Duration.kt\nkotlin/time/Duration\n*L\n1#1,864:1\n1#2:865\n803#3,14:866\n786#3,6:880\n803#3,14:886\n786#3,6:900\n786#3,6:907\n548#4:906\n*S KotlinDebug\n*F\n+ 1 Instant.kt\nkotlin/time/Instant\n*L\n150#1:866,14\n153#1:880,6\n161#1:886,14\n164#1:900,6\n188#1:907,6\n184#1:906\n*E\n"])
public class Instant internal constructor(epochSeconds: Long, nanosecondsOfSecond: Int) : java.lang.Comparable<Instant>, Serializable {
   public final val epochSeconds: Long
   public final val nanosecondsOfSecond: Int

   init {
      this.epochSeconds = epochSeconds;
      this.nanosecondsOfSecond = nanosecondsOfSecond;
      if (-31557014167219200L > this.epochSeconds || this.epochSeconds >= 31556889864403200L) {
         throw new IllegalArgumentException("Instant exceeds minimum or maximum instant".toString());
      }
   }

   public fun toEpochMilliseconds(): Long {
      if (this.epochSeconds >= 0L) {
         val var25: Long;
         if (1000L == 1L) {
            var25 = this.epochSeconds;
         } else if (this.epochSeconds == 1L) {
            var25 = 1000L;
         } else if (this.epochSeconds != 0L && 1000L != 0L) {
            val var20: Long = this.epochSeconds * 1000L;
            if (this.epochSeconds * 1000L / 1000L != this.epochSeconds
               || this.epochSeconds == java.lang.Long.MIN_VALUE && 1000L == -1L
               || 1000L == java.lang.Long.MIN_VALUE && this.epochSeconds == -1L) {
               return java.lang.Long.MAX_VALUE;
            }

            var25 = var20;
         } else {
            var25 = 0L;
         }

         return if ((var25 xor var25 + this.nanosecondsOfSecond / 1000000) < 0L && (var25 xor this.nanosecondsOfSecond / 1000000) >= 0L)
            java.lang.Long.MAX_VALUE
            else
            var25 + this.nanosecondsOfSecond / 1000000;
      } else {
         val `a$iv`: Long = this.epochSeconds + 1L;
         val var10000: Long;
         if (1000L == 1L) {
            var10000 = `a$iv`;
         } else if (`a$iv` == 1L) {
            var10000 = 1000L;
         } else if (`a$iv` != 0L && 1000L != 0L) {
            val `sum$iv`: Long = `a$iv` * 1000L;
            if (`a$iv` * 1000L / 1000L != `a$iv` || `a$iv` == java.lang.Long.MIN_VALUE && 1000L == -1L || 1000L == java.lang.Long.MIN_VALUE && `a$iv` == -1L) {
               return java.lang.Long.MIN_VALUE;
            }

            var10000 = `sum$iv`;
         } else {
            var10000 = 0L;
         }

         return if ((var10000 xor var10000 + (this.nanosecondsOfSecond / 1000000 - 1000)) < 0L
               && (var10000 xor this.nanosecondsOfSecond / 1000000 - 1000) >= 0L)
            java.lang.Long.MIN_VALUE
            else
            var10000 + (this.nanosecondsOfSecond / 1000000 - 1000);
      }
   }

   public operator fun plus(duration: Duration): Instant {
      val nanosecondsToAdd: Int = Duration.getNanosecondsComponent-impl(var1);
      val secondsToAdd: Long = Duration.getInWholeSeconds-impl(var1);
      if (secondsToAdd == 0L && nanosecondsToAdd == 0) {
         return this;
      } else {
         val `sum$iv`: Long = this.epochSeconds + secondsToAdd;
         if ((this.epochSeconds xor this.epochSeconds + secondsToAdd) < 0L && (this.epochSeconds xor secondsToAdd) >= 0L) {
            return if (Duration.isPositive-impl(var1)) MAX else MIN;
         } else {
            return Companion.fromEpochSeconds(`sum$iv`, this.nanosecondsOfSecond + nanosecondsToAdd);
         }
      }
   }

   public operator fun minus(duration: Duration): Instant {
      return this.plus-LRDsOJo(Duration.unaryMinus-UwyO8pc(var1));
   }

   public operator fun minus(other: Instant): Duration {
      return Duration.plus-LRDsOJo(
         DurationKt.toDuration(this.epochSeconds - other.epochSeconds, DurationUnit.SECONDS),
         DurationKt.toDuration(this.nanosecondsOfSecond - other.nanosecondsOfSecond, DurationUnit.NANOSECONDS)
      );
   }

   public open operator fun compareTo(other: Instant): Int {
      val s: Int = Intrinsics.compare(this.epochSeconds, other.epochSeconds);
      return if (s != 0) s else Intrinsics.compare(this.nanosecondsOfSecond, other.nanosecondsOfSecond);
   }

   public override operator fun equals(other: Any?): Boolean {
      return this === other
         || other is Instant && this.epochSeconds == (other as Instant).epochSeconds && this.nanosecondsOfSecond == (other as Instant).nanosecondsOfSecond;
   }

   public override fun hashCode(): Int {
      return java.lang.Long.hashCode(this.epochSeconds) + 51 * this.nanosecondsOfSecond;
   }

   public override fun toString(): String {
      return InstantKt.access$formatIso(this);
   }

   private fun writeReplace(): Any {
      return InstantJvmKt.serializedInstant(this);
   }

   private fun readObject(input: ObjectInputStream) {
      throw new InvalidObjectException("Deserialization is supported via proxy only");
   }

   @SourceDebugExtension(["SMAP\nInstant.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Instant.kt\nkotlin/time/Instant$Companion\n+ 2 Instant.kt\nkotlin/time/InstantKt\n*L\n1#1,864:1\n786#2,6:865\n*S KotlinDebug\n*F\n+ 1 Instant.kt\nkotlin/time/Instant$Companion\n*L\n312#1:865,6\n*E\n"])
   public companion object {
      public final val DISTANT_PAST: Instant
         public final get() {
            return this.fromEpochSeconds(-3217862419201L, 999999999);
         }


      public final val DISTANT_FUTURE: Instant
         public final get() {
            return this.fromEpochSeconds(3093527980800L, 0);
         }


      internal final val MIN: Instant
      internal final val MAX: Instant

      @Deprecated(message = "Use Clock.System.now() instead", replaceWith = @ReplaceWith(expression = "Clock.System.now()", imports = ["kotlin.time.Clock"]), level = DeprecationLevel.ERROR)
      public fun now(): Instant {
         throw new NotImplementedError(null, 1, null);
      }

      public fun fromEpochMilliseconds(epochMilliseconds: Long): Instant {
         var var9: Long = epochMilliseconds / 1000L;
         if ((epochMilliseconds xor 1000L) < 0L && epochMilliseconds / 1000L * 1000L != epochMilliseconds) {
            var9 += -1L;
         }

         return if (var9 < -31557014167219200L)
            this.getMIN$kotlin_stdlib()
            else
            (
               if (var9 > 31556889864403199L)
                  this.getMAX$kotlin_stdlib()
                  else
                  this.fromEpochSeconds(
                     var9,
                     (int)(
                        (
                              epochMilliseconds % 1000L
                                 + (1000L and ((epochMilliseconds % 1000L xor 1000L) and (epochMilliseconds % 1000L or -(epochMilliseconds % 1000L))) shr 63)
                           )
                           * (long)1000000
                     )
                  )
            );
      }

      public fun fromEpochSeconds(epochSeconds: Long, nanosecondAdjustment: Long = 0L): Instant {
         var var13: Long = nanosecondAdjustment / 1000000000L;
         if ((nanosecondAdjustment xor 1000000000L) < 0L && nanosecondAdjustment / 1000000000L * 1000000000L != nanosecondAdjustment) {
            var13 += -1L;
         }

         val `sum$iv`: Long = epochSeconds + var13;
         if ((epochSeconds xor epochSeconds + var13) < 0L && (epochSeconds xor var13) >= 0L) {
            return if (epochSeconds > 0L) Instant.Companion.getMAX$kotlin_stdlib() else Instant.Companion.getMIN$kotlin_stdlib();
         } else {
            return if (`sum$iv` < -31557014167219200L)
               this.getMIN$kotlin_stdlib()
               else
               (
                  if (`sum$iv` > 31556889864403199L)
                     this.getMAX$kotlin_stdlib()
                     else
                     new Instant(
                        `sum$iv`,
                        (int)(
                           nanosecondAdjustment % 1000000000L
                              + (
                                 1000000000L and (
                                    (nanosecondAdjustment % 1000000000L xor 1000000000L) and (
                                       nanosecondAdjustment % 1000000000L or -(nanosecondAdjustment % 1000000000L)
                                    )
                                 ) shr 63
                              )
                        )
                     )
               );
         }
      }

      public fun fromEpochSeconds(epochSeconds: Long, nanosecondAdjustment: Int): Instant {
         return this.fromEpochSeconds(epochSeconds, (long)nanosecondAdjustment);
      }

      public fun parse(input: CharSequence): Instant {
         return InstantKt.access$parseIso(input).toInstant();
      }

      @SinceKotlin(version = "2.2")
      public fun parseOrNull(input: CharSequence): Instant? {
         return InstantKt.access$parseIso(input).toInstantOrNull();
      }
   }
}
