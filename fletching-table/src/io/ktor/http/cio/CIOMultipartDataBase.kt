package io.ktor.http.cio

import io.ktor.http.ContentDisposition
import io.ktor.http.cio.CIOMultipartDataBase.readPart.1
import io.ktor.http.cio.MultipartEvent.MultipartPart
import io.ktor.http.content.MultiPartData
import io.ktor.http.content.PartData
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.DeprecationKt
import io.ktor.utils.io.InternalAPI
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jdk7.AutoCloseableKt
import kotlin.jvm.functions.Function0
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.io.Source

@InternalAPI
@SourceDebugExtension(["SMAP\nCIOMultipartDataBase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CIOMultipartDataBase.kt\nio/ktor/http/cio/CIOMultipartDataBase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,95:1\n1#2:96\n*E\n"])
public class CIOMultipartDataBase(coroutineContext: CoroutineContext,
      channel: ByteReadChannel,
      contentType: CharSequence,
      contentLength: Long?,
      formFieldLimit: Long = 65536L
   ) :
   MultiPartData,
   CoroutineScope {
   public open val coroutineContext: CoroutineContext
   private final var previousPart: PartData?
   private final val events: ReceiveChannel<MultipartEvent>

   init {
      this.coroutineContext = coroutineContext;
      this.events = MultipartKt.parseMultipart(this, channel, contentType, contentLength, formFieldLimit);
   }

   public override suspend fun readPart(): PartData? {
      var `$continuation`: Continuation;
      label58: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label58;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            if (this.previousPart != null) {
               val var12: Function0 = this.previousPart.getDispose();
               if (var12 != null) {
                  var12.invoke();
               }
            }
            break;
         case 1:
            val event: MultipartEvent = `$continuation`.L$0 as MultipartEvent;
            ResultKt.throwOnFailure(`$result`);
            val var11: PartData = `$result` as PartData;
            if (`$result` as PartData != null) {
               this.previousPart = var11;
               return var11;
            }
            break;
         case 2:
            ResultKt.throwOnFailure(`$result`);
            return `$result`;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      var var14: Any;
      do {
         var14 = ChannelResult.getOrNull-impl(this.events.tryReceive-PtdJZtk()) as MultipartEvent;
         if (var14 == null) {
            `$continuation`.L$0 = null;
            `$continuation`.label = 2;
            var14 = this.readPartSuspend(`$continuation`);
            if (var14 === var7) {
               return var7;
            }

            return var14;
         }

         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(var14);
         `$continuation`.label = 1;
         var14 = this.eventToData((MultipartEvent)var14, `$continuation`);
         if (var14 === var7) {
            return var7;
         }
      } while ((PartData)var14 == null);

      this.previousPart = var14 as PartData;
      return var14 as PartData;
   }

   private suspend fun readPartSuspend(): PartData? {
      var `$continuation`: Continuation;
      label84: {
         if (`$completion` is io.ktor.http.cio.CIOMultipartDataBase.readPartSuspend.1) {
            `$continuation` = `$completion` as io.ktor.http.cio.CIOMultipartDataBase.readPartSuspend.1;
            if (((`$completion` as io.ktor.http.cio.CIOMultipartDataBase.readPartSuspend.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label84;
            }
         }

         `$continuation` = new io.ktor.http.cio.CIOMultipartDataBase.readPartSuspend.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);

            try {
               break;
            } catch (var11: ClosedReceiveChannelException) {
               return null;
            }
         case 1:
            var var24: Any;
            try {
               ResultKt.throwOnFailure(`$result`);
               var24 = `$result`;
            } catch (var12: ClosedReceiveChannelException) {
               return null;
            }

            try {
               val var17: MultipartEvent = var24 as MultipartEvent;
               `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(var24 as MultipartEvent);
               `$continuation`.label = 2;
               var24 = this.eventToData(var17, `$continuation`);
            } catch (var9: ClosedReceiveChannelException) {
               return null;
            }

            if (var24 === var7) {
               return var7;
            }

            try {
               var24 = var24 as PartData;
               if (var24 as PartData != null) {
                  return var24;
               }
               break;
            } catch (var14: ClosedReceiveChannelException) {
               return null;
            }
         case 2:
            val var2: MultipartEvent = `$continuation`.L$0 as MultipartEvent;

            var var10000: Any;
            try {
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
            } catch (var13: ClosedReceiveChannelException) {
               return null;
            }

            try {
               var10000 = var10000 as PartData;
               if (var10000 as PartData != null) {
                  return var10000;
               }
               break;
            } catch (var8: ClosedReceiveChannelException) {
               return null;
            }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while (true) {
         var var28: ReceiveChannel;
         try {
            var28 = this.events;
            `$continuation`.L$0 = null;
            `$continuation`.label = 1;
            var28 = (ReceiveChannel)var28.receive(`$continuation`);
         } catch (var10: ClosedReceiveChannelException) {
            return null;
         }

         if (var28 === var7) {
            return var7;
         }

         try {
            val var18: MultipartEvent = var28 as MultipartEvent;
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(var28 as MultipartEvent);
            `$continuation`.label = 2;
            var28 = (ReceiveChannel)this.eventToData(var18, `$continuation`);
         } catch (var16: ClosedReceiveChannelException) {
            return null;
         }

         if (var28 === var7) {
            return var7;
         }

         try {
            val var30: PartData = var28 as PartData;
            if (var28 as PartData != null) {
               return var30;
            }
         } catch (var15: ClosedReceiveChannelException) {
            return null;
         }
      }
   }

   private suspend fun eventToData(event: MultipartEvent): PartData? {
      var `$continuation`: Continuation;
      label54: {
         if (`$completion` is io.ktor.http.cio.CIOMultipartDataBase.eventToData.1) {
            `$continuation` = `$completion` as io.ktor.http.cio.CIOMultipartDataBase.eventToData.1;
            if (((`$completion` as io.ktor.http.cio.CIOMultipartDataBase.eventToData.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label54;
            }
         }

         `$continuation` = new io.ktor.http.cio.CIOMultipartDataBase.eventToData.1(this, `$completion`);
      }

      var var13: PartData;
      label48: {
         label47: {
            val `$result`: Any = `$continuation`.result;
            val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch ($continuation.label) {
               case 0:
                  ResultKt.throwOnFailure(`$result`);

                  try {
                     if (event !is MultipartEvent.MultipartPart) {
                        break label47;
                     }

                     val var10001: MultipartEvent.MultipartPart = event as MultipartEvent.MultipartPart;
                     `$continuation`.L$0 = event;
                     `$continuation`.label = 1;
                     var13 = (PartData)this.partToData(var10001, `$continuation`);
                  } catch (var12: java.lang.Throwable) {
                     event.release();
                     throw var12;
                  }

                  if (var13 === var7) {
                     return var7;
                  }
                  break;
               case 1:
                  event = `$continuation`.L$0 as MultipartEvent;

                  try {
                     ResultKt.throwOnFailure(`$result`);
                     var13 = (PartData)`$result`;
                     break;
                  } catch (var11: java.lang.Throwable) {
                     (`$continuation`.L$0 as MultipartEvent).release();
                     throw var11;
                  }
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            try {
               var13 = var13;
               break label48;
            } catch (var10: java.lang.Throwable) {
               event.release();
               throw var10;
            }
         }

         try {
            event.release();
            var13 = null;
         } catch (var9: java.lang.Throwable) {
            event.release();
            throw var9;
         }
      }

      try {
         return var13;
      } catch (var8: java.lang.Throwable) {
         event.release();
         throw var8;
      }
   }

   private suspend fun partToData(part: MultipartPart): PartData {
      label95: {
         var `$continuation`: Continuation;
         label57: {
            if (`$completion` is io.ktor.http.cio.CIOMultipartDataBase.partToData.1) {
               `$continuation` = `$completion` as io.ktor.http.cio.CIOMultipartDataBase.partToData.1;
               if (((`$completion` as io.ktor.http.cio.CIOMultipartDataBase.partToData.1).label and Integer.MIN_VALUE) != 0) {
                  `$continuation`.label -= Integer.MIN_VALUE;
                  break label57;
               }
            }

            `$continuation` = new io.ktor.http.cio.CIOMultipartDataBase.partToData.1(this, `$completion`);
         }

         var headers: HttpHeadersMap;
         var var10000: Deferred;
         label61: {
            val `$result`: Any = `$continuation`.result;
            val var15: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch ($continuation.label) {
               case 0:
                  ResultKt.throwOnFailure(`$result`);
                  var10000 = part.getHeaders();
                  `$continuation`.L$0 = part;
                  `$continuation`.label = 1;
                  var10000 = var10000.await(`$continuation`);
                  if (var10000 === var15) {
                     return var15;
                  }
                  break;
               case 1:
                  part = `$continuation`.L$0 as MultipartEvent.MultipartPart;
                  ResultKt.throwOnFailure(`$result`);
                  var10000 = `$result`;
                  break;
               case 2:
                  val body: ByteReadChannel = `$continuation`.L$4 as ByteReadChannel;
                  val filename: java.lang.String = `$continuation`.L$3 as java.lang.String;
                  val contentDisposition: ContentDisposition = `$continuation`.L$2 as ContentDisposition;
                  headers = `$continuation`.L$1 as HttpHeadersMap;
                  part = `$continuation`.L$0 as MultipartEvent.MultipartPart;
                  ResultKt.throwOnFailure(`$result`);
                  var10000 = (Deferred)`$result`;
                  break label61;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            headers = var10000 as HttpHeadersMap;
            val var27: java.lang.CharSequence = (var10000 as HttpHeadersMap).get("Content-Disposition");
            val var28: ContentDisposition = if (var27 != null) ContentDisposition.Companion.parse(var27.toString()) else null;
            val var21: java.lang.String = if (var28 != null) var28.parameter("filename") else null;
            val var22: ByteReadChannel = part.getBody();
            if (var21 != null) {
               return new PartData.FileItem(CIOMultipartDataBase::partToData$lambda$2, CIOMultipartDataBase::partToData$lambda$3, new CIOHeaders(headers));
            }

            `$continuation`.L$0 = part;
            `$continuation`.L$1 = headers;
            `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var28);
            `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(var21);
            `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var22);
            `$continuation`.label = 2;
            var10000 = (Deferred)ByteReadChannelOperationsKt.readRemaining(var22, `$continuation`);
            if (var10000 === var15) {
               return var15;
            }
         }

         val var24: AutoCloseable = var10000 as Source;
         var var9: java.lang.Throwable = null;

         try {
            try {
               new PartData.FormItem(DeprecationKt.readText(var24 as Source), CIOMultipartDataBase::partToData$lambda$1$0, new CIOHeaders(headers));
            } catch (var16: java.lang.Throwable) {
               var9 = var16;
               throw var16;
            }
         } catch (var17: java.lang.Throwable) {
            AutoCloseableKt.closeFinally(var24, var9);
         }

         AutoCloseableKt.closeFinally(var24, null);
      }
   }

   @JvmStatic
   fun `partToData$lambda$1$0`(`$part`: MultipartEvent.MultipartPart): Unit {
      `$part`.release();
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `partToData$lambda$2`(`$part`: MultipartEvent.MultipartPart): ByteReadChannel {
      return `$part`.getBody();
   }

   @JvmStatic
   fun `partToData$lambda$3`(`$part`: MultipartEvent.MultipartPart): Unit {
      `$part`.release();
      return Unit.INSTANCE;
   }
}
