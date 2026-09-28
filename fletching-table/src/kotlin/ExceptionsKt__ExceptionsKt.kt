package kotlin

import java.io.PrintStream
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.internal.HidesMembers
import kotlin.internal.InlineOnly
import kotlin.internal.PlatformImplementationsKt

internal class ExceptionsKt__ExceptionsKt {
   public final val stackTrace: Array<StackTraceElement>
      public final get() {
         val var10000: Array<StackTraceElement> = `$this$stackTrace`.getStackTrace();
         return var10000;
      }


   @SinceKotlin(
      version = "1.4"
   )
   public final val suppressedExceptions: List<Throwable>
      public final get() {
         return PlatformImplementationsKt.IMPLEMENTATIONS.getSuppressed(`$this$suppressedExceptions`);
      }


   @InlineOnly
   @JvmStatic
   public inline fun Throwable.printStackTrace() {
      `$this$printStackTrace`.printStackTrace();
   }

   @InlineOnly
   @JvmStatic
   public inline fun Throwable.printStackTrace(writer: PrintWriter) {
      `$this$printStackTrace`.printStackTrace(writer);
   }

   @InlineOnly
   @JvmStatic
   public inline fun Throwable.printStackTrace(stream: PrintStream) {
      `$this$printStackTrace`.printStackTrace(stream);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun Throwable.stackTraceToString(): String {
      val sw: StringWriter = new StringWriter();
      val pw: PrintWriter = new PrintWriter(sw);
      `$this$stackTraceToString`.printStackTrace(pw);
      pw.flush();
      val var10000: java.lang.String = sw.toString();
      return var10000;
   }

   @SinceKotlin(version = "1.1")
   @HidesMembers
   @JvmStatic
   public fun Throwable.addSuppressed(exception: Throwable) {
      if (`$this$addSuppressed` != exception) {
         PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed(`$this$addSuppressed`, exception);
      }
   }

   open fun ExceptionsKt__ExceptionsKt() {
   }
}
