package io.ktor.utils.io

import io.ktor.utils.io.ReaderJob.flushAndClose.1
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt

@SourceDebugExtension(["SMAP\nByteReadChannelOperations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteReadChannelOperations.kt\nio/ktor/utils/io/ReaderJob\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,621:1\n1321#2,2:622\n*S KotlinDebug\n*F\n+ 1 ByteReadChannelOperations.kt\nio/ktor/utils/io/ReaderJob\n*L\n309#1:622,2\n*E\n"])
public class ReaderJob internal constructor(channel: ByteWriteChannel, job: Job) : ChannelJob {
   public final val channel: ByteWriteChannel
   public open val job: Job

   init {
      this.channel = channel;
      this.job = job;
   }

   @InternalAPI
   public suspend fun flushAndClose() {
      var `$continuation`: Continuation;
      label39: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label39;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var `$this$forEach$iv`: Sequence;
      var `$i$f$forEach`: Int;
      var var4: java.util.Iterator;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            JobKt.cancelChildren$default(this.getJob(), null, 1, null);
            `$this$forEach$iv` = this.getJob().getChildren();
            `$i$f$forEach` = 0;
            var4 = `$this$forEach$iv`.iterator();
            break;
         case 1:
            val var7: Int = `$continuation`.I$1;
            `$i$f$forEach` = `$continuation`.I$0;
            val it: Job = `$continuation`.L$3 as Job;
            val `element$iv`: Any = `$continuation`.L$2;
            var4 = `$continuation`.L$1 as java.util.Iterator;
            `$this$forEach$iv` = `$continuation`.L$0 as Sequence;
            ResultKt.throwOnFailure(`$result`);
            break;
         case 2:
            ResultKt.throwOnFailure(`$result`);
            return Unit.INSTANCE;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while (var4.hasNext()) {
         val var11: Any = var4.next();
         val var12: Job = var11 as Job;
         Job.DefaultImpls.cancel$default(var11 as Job, null, 1, null);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$forEach$iv`);
         `$continuation`.L$1 = var4;
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var11);
         `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(var12);
         `$continuation`.I$0 = `$i$f$forEach`;
         `$continuation`.I$1 = 0;
         `$continuation`.label = 1;
         if (var12.join(`$continuation`) === var10) {
            return var10;
         }
      }

      val var10000: ByteWriteChannel = this.channel;
      `$continuation`.L$0 = null;
      `$continuation`.L$1 = null;
      `$continuation`.L$2 = null;
      `$continuation`.L$3 = null;
      `$continuation`.label = 2;
      return if (var10000.flushAndClose(`$continuation`) === var10) var10 else Unit.INSTANCE;
   }
}
