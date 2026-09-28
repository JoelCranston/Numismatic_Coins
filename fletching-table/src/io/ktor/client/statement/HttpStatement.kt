package io.ktor.client.statement

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.call.SavedCallKt
import io.ktor.client.plugins.DoubleReceivePluginKt
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpStatement.fetchStreamingResponse.1
import io.ktor.client.utils.ExceptionUtilsJvmKt
import io.ktor.utils.io.ByteReadChannelKt
import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt

@SourceDebugExtension(["SMAP\nHttpStatement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpStatement.kt\nio/ktor/client/statement/HttpStatement\n+ 2 HttpTimeout.kt\nio/ktor/client/plugins/HttpTimeoutKt\n+ 3 HttpClientCall.kt\nio/ktor/client/call/HttpClientCallKt\n+ 4 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,196:1\n308#2,4:197\n308#2,2:201\n310#2,2:213\n308#2,2:215\n310#2,2:227\n308#2,4:229\n308#2,4:233\n162#3:203\n162#3:217\n69#4:204\n84#4,8:205\n69#4:218\n84#4,8:219\n*S KotlinDebug\n*F\n+ 1 HttpStatement.kt\nio/ktor/client/statement/HttpStatement\n*L\n54#1:197,4\n92#1:201,2\n92#1:213,2\n132#1:215,2\n132#1:227,2\n147#1:229,4\n160#1:233,4\n95#1:203\n135#1:217\n95#1:204\n95#1:205,8\n135#1:218\n135#1:219,8\n*E\n"])
public class HttpStatement(builder: HttpRequestBuilder, client: HttpClient) {
   private final val builder: HttpRequestBuilder

   @PublishedApi
   internal final val client: HttpClient

   init {
      this.builder = builder;
      this.client = client;
   }

   public suspend fun <T> execute(block: (HttpResponse, Continuation<Any>) -> Any?): Any {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
      //
      // Bytecode:
      // 000: aload 2
      // 001: instanceof io/ktor/client/statement/HttpStatement$execute$1
      // 004: ifeq 027
      // 007: aload 2
      // 008: checkcast io/ktor/client/statement/HttpStatement$execute$1
      // 00b: astore 10
      // 00d: aload 10
      // 00f: getfield io/ktor/client/statement/HttpStatement$execute$1.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 10
      // 01a: dup
      // 01b: getfield io/ktor/client/statement/HttpStatement$execute$1.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield io/ktor/client/statement/HttpStatement$execute$1.label I
      // 024: goto 032
      // 027: new io/ktor/client/statement/HttpStatement$execute$1
      // 02a: dup
      // 02b: aload 0
      // 02c: aload 2
      // 02d: invokespecial io/ktor/client/statement/HttpStatement$execute$1.<init> (Lio/ktor/client/statement/HttpStatement;Lkotlin/coroutines/Continuation;)V
      // 030: astore 10
      // 032: aload 10
      // 034: getfield io/ktor/client/statement/HttpStatement$execute$1.result Ljava/lang/Object;
      // 037: astore 9
      // 039: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03c: astore 11
      // 03e: aload 10
      // 040: getfield io/ktor/client/statement/HttpStatement$execute$1.label I
      // 043: tableswitch 458 0 4 33 84 174 278 393
      // 064: aload 9
      // 066: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 069: bipush 0
      // 06a: istore 3
      // 06b: nop
      // 06c: bipush 0
      // 06d: istore 4
      // 06f: aload 0
      // 070: aload 10
      // 072: aload 10
      // 074: aload 1
      // 075: putfield io/ktor/client/statement/HttpStatement$execute$1.L$0 Ljava/lang/Object;
      // 078: aload 10
      // 07a: iload 3
      // 07b: putfield io/ktor/client/statement/HttpStatement$execute$1.I$0 I
      // 07e: aload 10
      // 080: iload 4
      // 082: putfield io/ktor/client/statement/HttpStatement$execute$1.I$1 I
      // 085: aload 10
      // 087: bipush 1
      // 088: putfield io/ktor/client/statement/HttpStatement$execute$1.label I
      // 08b: invokevirtual io/ktor/client/statement/HttpStatement.fetchStreamingResponse (Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
      // 08e: dup
      // 08f: aload 11
      // 091: if_acmpne 0b5
      // 094: aload 11
      // 096: areturn
      // 097: aload 10
      // 099: getfield io/ktor/client/statement/HttpStatement$execute$1.I$1 I
      // 09c: istore 4
      // 09e: aload 10
      // 0a0: getfield io/ktor/client/statement/HttpStatement$execute$1.I$0 I
      // 0a3: istore 3
      // 0a4: aload 10
      // 0a6: getfield io/ktor/client/statement/HttpStatement$execute$1.L$0 Ljava/lang/Object;
      // 0a9: checkcast kotlin/jvm/functions/Function2
      // 0ac: astore 1
      // 0ad: nop
      // 0ae: aload 9
      // 0b0: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 0b3: aload 9
      // 0b5: checkcast io/ktor/client/statement/HttpResponse
      // 0b8: astore 5
      // 0ba: nop
      // 0bb: aload 1
      // 0bc: aload 5
      // 0be: aload 10
      // 0c0: aload 10
      // 0c2: aload 1
      // 0c3: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 0c6: putfield io/ktor/client/statement/HttpStatement$execute$1.L$0 Ljava/lang/Object;
      // 0c9: aload 10
      // 0cb: aload 5
      // 0cd: putfield io/ktor/client/statement/HttpStatement$execute$1.L$1 Ljava/lang/Object;
      // 0d0: aload 10
      // 0d2: iload 3
      // 0d3: putfield io/ktor/client/statement/HttpStatement$execute$1.I$0 I
      // 0d6: aload 10
      // 0d8: iload 4
      // 0da: putfield io/ktor/client/statement/HttpStatement$execute$1.I$1 I
      // 0dd: aload 10
      // 0df: bipush 2
      // 0e0: putfield io/ktor/client/statement/HttpStatement$execute$1.label I
      // 0e3: invokeinterface kotlin/jvm/functions/Function2.invoke (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; 3
      // 0e8: dup
      // 0e9: aload 11
      // 0eb: if_acmpne 119
      // 0ee: aload 11
      // 0f0: areturn
      // 0f1: aload 10
      // 0f3: getfield io/ktor/client/statement/HttpStatement$execute$1.I$1 I
      // 0f6: istore 4
      // 0f8: aload 10
      // 0fa: getfield io/ktor/client/statement/HttpStatement$execute$1.I$0 I
      // 0fd: istore 3
      // 0fe: aload 10
      // 100: getfield io/ktor/client/statement/HttpStatement$execute$1.L$1 Ljava/lang/Object;
      // 103: checkcast io/ktor/client/statement/HttpResponse
      // 106: astore 5
      // 108: aload 10
      // 10a: getfield io/ktor/client/statement/HttpStatement$execute$1.L$0 Ljava/lang/Object;
      // 10d: checkcast kotlin/jvm/functions/Function2
      // 110: astore 1
      // 111: nop
      // 112: aload 9
      // 114: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 117: aload 9
      // 119: astore 6
      // 11b: aload 0
      // 11c: aload 5
      // 11e: aload 10
      // 120: aload 10
      // 122: aload 1
      // 123: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 126: putfield io/ktor/client/statement/HttpStatement$execute$1.L$0 Ljava/lang/Object;
      // 129: aload 10
      // 12b: aload 5
      // 12d: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 130: putfield io/ktor/client/statement/HttpStatement$execute$1.L$1 Ljava/lang/Object;
      // 133: aload 10
      // 135: aload 6
      // 137: putfield io/ktor/client/statement/HttpStatement$execute$1.L$2 Ljava/lang/Object;
      // 13a: aload 10
      // 13c: iload 3
      // 13d: putfield io/ktor/client/statement/HttpStatement$execute$1.I$0 I
      // 140: aload 10
      // 142: iload 4
      // 144: putfield io/ktor/client/statement/HttpStatement$execute$1.I$1 I
      // 147: aload 10
      // 149: bipush 3
      // 14a: putfield io/ktor/client/statement/HttpStatement$execute$1.label I
      // 14d: invokevirtual io/ktor/client/statement/HttpStatement.cleanup (Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
      // 150: dup
      // 151: aload 11
      // 153: if_acmpne 188
      // 156: aload 11
      // 158: areturn
      // 159: aload 10
      // 15b: getfield io/ktor/client/statement/HttpStatement$execute$1.I$1 I
      // 15e: istore 4
      // 160: aload 10
      // 162: getfield io/ktor/client/statement/HttpStatement$execute$1.I$0 I
      // 165: istore 3
      // 166: aload 10
      // 168: getfield io/ktor/client/statement/HttpStatement$execute$1.L$2 Ljava/lang/Object;
      // 16b: astore 6
      // 16d: aload 10
      // 16f: getfield io/ktor/client/statement/HttpStatement$execute$1.L$1 Ljava/lang/Object;
      // 172: checkcast io/ktor/client/statement/HttpResponse
      // 175: astore 5
      // 177: aload 10
      // 179: getfield io/ktor/client/statement/HttpStatement$execute$1.L$0 Ljava/lang/Object;
      // 17c: checkcast kotlin/jvm/functions/Function2
      // 17f: astore 1
      // 180: nop
      // 181: aload 9
      // 183: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 186: aload 9
      // 188: pop
      // 189: aload 6
      // 18b: areturn
      // 18c: astore 7
      // 18e: aload 0
      // 18f: aload 5
      // 191: aload 10
      // 193: aload 10
      // 195: aload 1
      // 196: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 199: putfield io/ktor/client/statement/HttpStatement$execute$1.L$0 Ljava/lang/Object;
      // 19c: aload 10
      // 19e: aload 5
      // 1a0: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 1a3: putfield io/ktor/client/statement/HttpStatement$execute$1.L$1 Ljava/lang/Object;
      // 1a6: aload 10
      // 1a8: aload 7
      // 1aa: putfield io/ktor/client/statement/HttpStatement$execute$1.L$2 Ljava/lang/Object;
      // 1ad: aload 10
      // 1af: iload 3
      // 1b0: putfield io/ktor/client/statement/HttpStatement$execute$1.I$0 I
      // 1b3: aload 10
      // 1b5: iload 4
      // 1b7: putfield io/ktor/client/statement/HttpStatement$execute$1.I$1 I
      // 1ba: aload 10
      // 1bc: bipush 4
      // 1bd: putfield io/ktor/client/statement/HttpStatement$execute$1.label I
      // 1c0: invokevirtual io/ktor/client/statement/HttpStatement.cleanup (Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
      // 1c3: dup
      // 1c4: aload 11
      // 1c6: if_acmpne 1fe
      // 1c9: aload 11
      // 1cb: areturn
      // 1cc: aload 10
      // 1ce: getfield io/ktor/client/statement/HttpStatement$execute$1.I$1 I
      // 1d1: istore 4
      // 1d3: aload 10
      // 1d5: getfield io/ktor/client/statement/HttpStatement$execute$1.I$0 I
      // 1d8: istore 3
      // 1d9: aload 10
      // 1db: getfield io/ktor/client/statement/HttpStatement$execute$1.L$2 Ljava/lang/Object;
      // 1de: checkcast java/lang/Throwable
      // 1e1: astore 7
      // 1e3: aload 10
      // 1e5: getfield io/ktor/client/statement/HttpStatement$execute$1.L$1 Ljava/lang/Object;
      // 1e8: checkcast io/ktor/client/statement/HttpResponse
      // 1eb: astore 5
      // 1ed: aload 10
      // 1ef: getfield io/ktor/client/statement/HttpStatement$execute$1.L$0 Ljava/lang/Object;
      // 1f2: checkcast kotlin/jvm/functions/Function2
      // 1f5: astore 1
      // 1f6: nop
      // 1f7: aload 9
      // 1f9: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 1fc: aload 9
      // 1fe: pop
      // 1ff: aload 7
      // 201: athrow
      // 202: astore 8
      // 204: aload 8
      // 206: checkcast java/lang/Throwable
      // 209: invokestatic io/ktor/client/utils/ExceptionUtilsJvmKt.unwrapCancellationException (Ljava/lang/Throwable;)Ljava/lang/Throwable;
      // 20c: athrow
      // 20d: new java/lang/IllegalStateException
      // 210: dup
      // 211: ldc "call to 'resume' before 'invoke' with coroutine"
      // 213: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 216: athrow
   }

   public suspend fun execute(): HttpResponse {
      return this.fetchResponse(`$completion`);
   }

   @PublishedApi
   internal suspend fun fetchStreamingResponse(): HttpResponse {
      var `$continuation`: Continuation;
      label35: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label35;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            val var13: Int = 0;

            try {
               val var15: HttpRequestBuilder = new HttpRequestBuilder().takeFromWithExecutionContext(this.builder);
               DoubleReceivePluginKt.skipSaveBody(var15);
               var10000 = this.client;
               `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(var15);
               `$continuation`.I$0 = var13;
               `$continuation`.I$1 = 0;
               `$continuation`.label = 1;
               var10000 = (HttpClient)var10000.execute$ktor_client_core(var15, `$continuation`);
            } catch (var12: CancellationException) {
               throw ExceptionUtilsJvmKt.unwrapCancellationException(var12);
            }

            if (var10000 === var9) {
               return var9;
            }
            break;
         case 1:
            val var3: Int = `$continuation`.I$1;
            val `$i$f$unwrapRequestTimeoutException`: Int = `$continuation`.I$0;
            val builder: HttpRequestBuilder = `$continuation`.L$0 as HttpRequestBuilder;

            try {
               ResultKt.throwOnFailure(`$result`);
               var10000 = (HttpClient)`$result`;
               break;
            } catch (var11: CancellationException) {
               throw ExceptionUtilsJvmKt.unwrapCancellationException(var11);
            }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      try {
         return (var10000 as HttpClientCall).getResponse();
      } catch (var10: CancellationException) {
         throw ExceptionUtilsJvmKt.unwrapCancellationException(var10);
      }
   }

   @PublishedApi
   internal suspend fun fetchResponse(): HttpResponse {
      var `$continuation`: Continuation;
      label72: {
         if (`$completion` is io.ktor.client.statement.HttpStatement.fetchResponse.1) {
            `$continuation` = `$completion` as io.ktor.client.statement.HttpStatement.fetchResponse.1;
            if (((`$completion` as io.ktor.client.statement.HttpStatement.fetchResponse.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label72;
            }
         }

         `$continuation` = new io.ktor.client.statement.HttpStatement.fetchResponse.1(this, `$completion`);
      }

      var result: HttpResponse;
      label76: {
         var var10: Any;
         var var18: Int;
         var var19: Int;
         var var20: HttpRequestBuilder;
         var var21: HttpClientCall;
         var var10000: HttpClient;
         label77: {
            val `$result`: Any = `$continuation`.result;
            var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch ($continuation.label) {
               case 0:
                  ResultKt.throwOnFailure(`$result`);
                  var18 = 0;

                  try {
                     var19 = 0;
                     var20 = new HttpRequestBuilder().takeFromWithExecutionContext(this.builder);
                     var10000 = this.client;
                     `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(var20);
                     `$continuation`.I$0 = var18;
                     `$continuation`.I$1 = 0;
                     `$continuation`.label = 1;
                     var10000 = var10000.execute$ktor_client_core(var20, `$continuation`);
                  } catch (var17: CancellationException) {
                     throw ExceptionUtilsJvmKt.unwrapCancellationException(var17);
                  }

                  if (var10000 === var10) {
                     return var10;
                  }
                  break;
               case 1:
                  var19 = `$continuation`.I$1;
                  var18 = `$continuation`.I$0;
                  var20 = `$continuation`.L$0 as HttpRequestBuilder;

                  try {
                     ResultKt.throwOnFailure(`$result`);
                     var10000 = `$result`;
                     break;
                  } catch (var16: CancellationException) {
                     throw ExceptionUtilsJvmKt.unwrapCancellationException(var16);
                  }
               case 2:
                  var19 = `$continuation`.I$1;
                  var18 = `$continuation`.I$0;
                  var21 = `$continuation`.L$1 as HttpClientCall;
                  var20 = `$continuation`.L$0 as HttpRequestBuilder;

                  try {
                     ResultKt.throwOnFailure(`$result`);
                     var10000 = (HttpClient)`$result`;
                     break label77;
                  } catch (var14: CancellationException) {
                     throw ExceptionUtilsJvmKt.unwrapCancellationException(var14);
                  }
               case 3:
                  var19 = `$continuation`.I$1;
                  var18 = `$continuation`.I$0;
                  result = `$continuation`.L$2 as HttpResponse;
                  var21 = `$continuation`.L$1 as HttpClientCall;
                  var20 = `$continuation`.L$0 as HttpRequestBuilder;

                  try {
                     ResultKt.throwOnFailure(`$result`);
                     break label76;
                  } catch (var12: CancellationException) {
                     throw ExceptionUtilsJvmKt.unwrapCancellationException(var12);
                  }
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            try {
               var21 = var10000 as HttpClientCall;
               `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(var20);
               `$continuation`.L$1 = var21;
               `$continuation`.I$0 = var18;
               `$continuation`.I$1 = var19;
               `$continuation`.label = 2;
               var10000 = (HttpClient)SavedCallKt.save(var21, `$continuation`);
            } catch (var15: CancellationException) {
               throw ExceptionUtilsJvmKt.unwrapCancellationException(var15);
            }

            if (var10000 === var10) {
               return var10;
            }
         }

         try {
            result = (var10000 as HttpClientCall).getResponse();
            val var10001: HttpResponse = var21.getResponse();
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(var20);
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var21);
            `$continuation`.L$2 = result;
            `$continuation`.I$0 = var18;
            `$continuation`.I$1 = var19;
            `$continuation`.label = 3;
            var10000 = (HttpClient)this.cleanup(var10001, `$continuation`);
         } catch (var13: CancellationException) {
            throw ExceptionUtilsJvmKt.unwrapCancellationException(var13);
         }

         if (var10000 === var10) {
            return var10;
         }
      }

      try {
         return result;
      } catch (var11: CancellationException) {
         throw ExceptionUtilsJvmKt.unwrapCancellationException(var11);
      }
   }

   @PublishedApi
   internal suspend fun HttpResponse.cleanup() {
      var `$continuation`: Continuation;
      label29: {
         if (`$completion` is io.ktor.client.statement.HttpStatement.cleanup.1) {
            `$continuation` = `$completion` as io.ktor.client.statement.HttpStatement.cleanup.1;
            if (((`$completion` as io.ktor.client.statement.HttpStatement.cleanup.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label29;
            }
         }

         `$continuation` = new io.ktor.client.statement.HttpStatement.cleanup.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0: {
            ResultKt.throwOnFailure(`$result`);
            val var10000: Job = JobKt.getJob(`$this$cleanup`.getCoroutineContext());
            val var13: CompletableJob = var10000 as CompletableJob;
            (var10000 as CompletableJob).complete();
            if (!DoubleReceivePluginKt.isSaved(`$this$cleanup`)) {
               try {
                  ByteReadChannelKt.cancel(`$this$cleanup`.getRawContent());
               } catch (var11: java.lang.Throwable) {
               }
            }

            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$cleanup`);
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var13);
            `$continuation`.L$2 = var13;
            `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(var13);
            `$continuation`.I$0 = 0;
            `$continuation`.label = 1;
            if (var13.join(`$continuation`) === var10) {
               return var10;
            }
            break;
         }
         case 1: {
            val var6: Int = `$continuation`.I$0;
            val `$this$cleanup_u24lambda_u240`: CompletableJob = `$continuation`.L$3 as CompletableJob;
            val var4: CompletableJob = `$continuation`.L$2 as CompletableJob;
            val job: CompletableJob = `$continuation`.L$1 as CompletableJob;
            `$this$cleanup` = `$continuation`.L$0 as HttpResponse;
            ResultKt.throwOnFailure(`$result`);
            break;
         }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      return Unit.INSTANCE;
   }

   public override fun toString(): String {
      return "HttpStatement[${this.builder.getUrl()}]";
   }
}
