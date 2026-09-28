package io.ktor.client.call

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall.bodyNullable.1
import io.ktor.client.plugins.DoubleReceivePluginKt
import io.ktor.client.request.DefaultHttpRequest
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.client.statement.DefaultHttpResponse
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.http.content.NullBody
import io.ktor.util.AttributeKey
import io.ktor.util.Attributes
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.TypeInfoJvmKt
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.InternalAPI
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineScopeKt

@SourceDebugExtension(["SMAP\nHttpClientCall.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpClientCall.kt\nio/ktor/client/call/HttpClientCall\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Attributes.kt\nio/ktor/util/AttributesKt\n+ 4 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,223:1\n1#2:224\n21#3:225\n69#4:226\n84#4,8:227\n*S KotlinDebug\n*F\n+ 1 HttpClientCall.kt\nio/ktor/client/call/HttpClientCall\n*L\n138#1:225\n138#1:226\n138#1:227,8\n*E\n"])
public open class HttpClientCall(client: HttpClient) : CoroutineScope {
   public final val client: HttpClient

   public open val coroutineContext: CoroutineContext
      public open get() {
         return this.getResponse().getCoroutineContext();
      }


   public final val attributes: Attributes
      public final get() {
         return this.getRequest().getAttributes();
      }


   public final lateinit var request: HttpRequest
      public final set(value) {
         this.request = var1;
      }


   public final lateinit var response: HttpResponse
      public final set(value) {
         this.response = var1;
      }


   protected open val allowDoubleReceive: Boolean

   init {
      this.client = client;
      this.received = 0;
   }

   @InternalAPI
   public constructor(client: HttpClient, requestData: HttpRequestData, responseData: HttpResponseData) : this(client) {
      this.setRequest(new DefaultHttpRequest(this, requestData));
      this.setResponse(new DefaultHttpResponse(this, responseData));
      this.getAttributes().remove(CustomResponse);
      if (responseData.getBody() !is ByteReadChannel) {
         this.getAttributes().put(CustomResponse, responseData.getBody());
      }
   }

   protected open suspend fun getResponseContent(): ByteReadChannel {
      return getResponseContent$suspendImpl(this, `$completion`);
   }

   public suspend fun bodyNullable(info: TypeInfo): Any? {
      var `$continuation`: Continuation;
      label91: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label91;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      var var10000: Any;
      label95: {
         val `$result`: Any = `$continuation`.result;
         val var11: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);

               try {
                  if (TypeInfoJvmKt.instanceOf(this.getResponse(), info.getType())) {
                     return this.getResponse();
                  }

                  if (!this.getAllowDoubleReceive() && !DoubleReceivePluginKt.isSaved(this.getResponse()) && !received$FU.compareAndSet(this, 0, 1)) {
                     throw new DoubleReceiveException(this);
                  }

                  var10000 = this.getAttributes().getOrNull(CustomResponse);
                  if (var10000 != null) {
                     break;
                  }

                  `$continuation`.L$0 = info;
                  `$continuation`.label = 1;
                  var10000 = this.getResponseContent(`$continuation`);
               } catch (var16: java.lang.Throwable) {
                  CoroutineScopeKt.cancel(this.getResponse(), "Receive failed", var16);
                  throw var16;
               }

               if (var10000 === var11) {
                  return var11;
               }
               break;
            case 1:
               info = `$continuation`.L$0 as TypeInfo;

               try {
                  ResultKt.throwOnFailure(`$result`);
                  var10000 = `$result`;
                  break;
               } catch (var15: java.lang.Throwable) {
                  CoroutineScopeKt.cancel(this.getResponse(), "Receive failed", var15);
                  throw var15;
               }
            case 2:
               val cause: HttpResponseContainer = `$continuation`.L$2 as HttpResponseContainer;
               val responseData: Any = `$continuation`.L$1;
               info = `$continuation`.L$0 as TypeInfo;

               try {
                  ResultKt.throwOnFailure(`$result`);
                  var10000 = `$result`;
                  break label95;
               } catch (var13: java.lang.Throwable) {
                  CoroutineScopeKt.cancel(this.getResponse(), "Receive failed", var13);
                  throw var13;
               }
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            val var18: HttpResponseContainer = new HttpResponseContainer(info, var10000);
            val var21: HttpResponsePipeline = this.client.getResponsePipeline();
            `$continuation`.L$0 = info;
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var10000);
            `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var18);
            `$continuation`.label = 2;
            var10000 = var21.execute(this, var18, `$continuation`);
         } catch (var14: java.lang.Throwable) {
            CoroutineScopeKt.cancel(this.getResponse(), "Receive failed", var14);
            throw var14;
         }

         if (var10000 === var11) {
            return var11;
         }
      }

      try {
         val from: Any = (var10000 as HttpResponseContainer).getResponse();
         val result: Any = if (!(from == NullBody.INSTANCE)) from else null;
         if (result != null && !TypeInfoJvmKt.instanceOf(result, info.getType())) {
            throw new NoTransformationFoundException(this.getResponse(), result.getClass()::class, info.getType());
         } else {
            return result;
         }
      } catch (var12: java.lang.Throwable) {
         CoroutineScopeKt.cancel(this.getResponse(), "Receive failed", var12);
         throw var12;
      }
   }

   public suspend fun body(info: TypeInfo): Any {
      var `$continuation`: Continuation;
      label20: {
         if (`$completion` is io.ktor.client.call.HttpClientCall.body.1) {
            `$continuation` = `$completion` as io.ktor.client.call.HttpClientCall.body.1;
            if (((`$completion` as io.ktor.client.call.HttpClientCall.body.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         `$continuation` = new io.ktor.client.call.HttpClientCall.body.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(info);
            `$continuation`.label = 1;
            var10000 = this.bodyNullable(info, `$continuation`);
            if (var10000 === var5) {
               return var5;
            }
            break;
         case 1:
            info = `$continuation`.L$0 as TypeInfo;
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      return var10000;
   }

   public override fun toString(): String {
      return "HttpClientCall[${this.getRequest().getUrl()}, ${this.getResponse().getStatus()}]";
   }

   internal fun setResponse(response: HttpResponse) {
      this.setResponse(response);
   }

   internal fun setRequest(request: HttpRequest) {
      this.setRequest(request);
   }

   @JvmStatic
   fun {
      var var6: KType;
      try {
         var6 = Reflection.typeOf(Object.class);
      } catch (var12: java.lang.Throwable) {
         var6 = null;
      }

      CustomResponse = new AttributeKey<>("CustomResponse", new TypeInfo(Any::class, var6));
      received$FU = AtomicIntegerFieldUpdater.newUpdater(HttpClientCall.class, "received");
   }

   public companion object {
      internal final val CustomResponse: AttributeKey<Any>
   }
}
