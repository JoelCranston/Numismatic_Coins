package okio

import java.io.Closeable
import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.InlineMarker

@JvmSynthetic
internal class Okio__OkioKt {
   @JvmStatic
   public fun Source.buffer(): BufferedSource {
      return new RealBufferedSource(`$this$buffer`);
   }

   @JvmStatic
   public fun Sink.buffer(): BufferedSink {
      return new RealBufferedSink(`$this$buffer`);
   }

   @JvmName(name = "blackhole")
   @JvmStatic
   public fun blackholeSink(): Sink {
      return new BlackholeSink();
   }

   @JvmStatic
   public inline fun <T : Closeable?, R> T.use(block: (T) -> R): R {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      label74: {
         var thrown: java.lang.Throwable = null;

         var var5: Any;
         label75: {
            label76: {
               try {
                  try {
                     break label76;
                  } catch (var12: java.lang.Throwable) {
                     thrown = var12;
                     var5 = null;
                  }
               } catch (var13: java.lang.Throwable) {
                  InlineMarker.finallyStart(1);

                  try {
                     if (`$this$use` != null) {
                        `$this$use`.close();
                     }
                  } catch (var10: java.lang.Throwable) {
                     if (thrown != null) {
                        ExceptionsKt.addSuppressed(thrown, var10);
                     }
                  }

                  InlineMarker.finallyEnd(1);
               }

               InlineMarker.finallyStart(1);

               try {
                  if (`$this$use` != null) {
                     `$this$use`.close();
                  }
               } catch (var11: java.lang.Throwable) {
                  if (thrown == null) {
                     thrown = var11;
                  } else {
                     ExceptionsKt.addSuppressed(thrown, var11);
                  }
               }

               InlineMarker.finallyEnd(1);
               break label75;
            }

            InlineMarker.finallyStart(1);

            try {
               if (`$this$use` != null) {
                  `$this$use`.close();
               }
            } catch (var9: java.lang.Throwable) {
               thrown = var9;
            }

            InlineMarker.finallyEnd(1);
         }

         if (thrown != null) {
            throw thrown;
         } else {
            return (R)var5;
         }
      }
   }
}
