@file:SourceDebugExtension(["SMAP\nbuilders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 builders.kt\nio/ktor/client/plugins/sse/BuildersKt\n+ 2 builders.kt\nio/ktor/client/request/BuildersKt\n+ 3 Attributes.kt\nio/ktor/util/AttributesKt\n+ 4 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,1266:1\n1177#1,4:1267\n1181#1,7:1272\n1189#1:1280\n1200#1:1281\n1177#1,4:1282\n1181#1,7:1287\n1189#1:1295\n1200#1:1296\n93#2:1271\n52#2:1279\n93#2:1286\n52#2:1294\n93#2:1297\n52#2:1298\n21#3:1299\n21#3:1309\n21#3:1319\n21#3:1329\n21#3:1339\n21#3:1349\n69#4:1300\n84#4,8:1301\n69#4:1310\n84#4,8:1311\n69#4:1320\n84#4,8:1321\n69#4:1330\n84#4,8:1331\n69#4:1340\n84#4,8:1341\n69#4:1350\n84#4,8:1351\n*S KotlinDebug\n*F\n+ 1 builders.kt\nio/ktor/client/plugins/sse/BuildersKt\n*L\n80#1:1267,4\n80#1:1272,7\n80#1:1280\n80#1:1281\n554#1:1282,4\n554#1:1287,7\n554#1:1295\n554#1:1296\n80#1:1271\n80#1:1279\n554#1:1286\n554#1:1294\n1180#1:1297\n1180#1:1298\n22#1:1299\n23#1:1309\n24#1:1319\n25#1:1329\n26#1:1339\n27#1:1349\n22#1:1300\n22#1:1301,8\n23#1:1310\n23#1:1311,8\n24#1:1320\n24#1:1321,8\n25#1:1330\n25#1:1331,8\n26#1:1340\n26#1:1341,8\n27#1:1350\n27#1:1351,8\n*E\n"])

package io.ktor.client.plugins.sse

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.HttpClientCall
import io.ktor.client.call.SavedHttpCall
import io.ktor.client.call.SavedHttpResponse
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.HttpClientPluginKt
import io.ktor.client.plugins.api.ClientPluginInstance
import io.ktor.client.plugins.sse.BuildersKt.processSession.2
import io.ktor.client.plugins.sse.BuildersKt.serverSentEventsSession-i8z2VEo..inlined.processSession-rp2poPw.1
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestKt
import io.ktor.client.statement.HttpStatement
import io.ktor.http.URLParserKt
import io.ktor.util.AttributeKey
import io.ktor.util.reflect.TypeInfo
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.time.Duration
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CompletableDeferredKt
import kotlinx.coroutines.CoroutineScope

internal final val sseRequestAttr: AttributeKey<Boolean>
internal final val reconnectionTimeAttr: AttributeKey<Duration>
internal final val showCommentEventsAttr: AttributeKey<Boolean>
internal final val showRetryEventsAttr: AttributeKey<Boolean>
internal final val deserializerAttr: AttributeKey<(TypeInfo, String) -> Any?>
internal final val sseBufferPolicyAttr: AttributeKey<SSEBufferPolicy>

public fun HttpClientConfig<*>.SSE(config: (SSEConfig) -> Unit) {
   `$this$SSE`.install(SSEKt.getSSE() as HttpClientPlugin<? extends SSEConfig, ClientPluginInstance<SSEConfig>>, BuildersKt::SSE$lambda$0);
}

public suspend fun HttpClient.serverSentEventsSession(
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (HttpRequestBuilder) -> Unit
): ClientSSESession {
   HttpClientPluginKt.plugin(
      `$this$serverSentEventsSession_u2di8z2VEo`, SSEKt.getSSE() as HttpClientPlugin<? extends SSEConfig, ClientPluginInstance<SSEConfig>>
   );
   val `sessionDeferred$iv`: CompletableDeferred = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
   val `builder$iv$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   block.invoke(`builder$iv$iv$iv`);
   addAttribute(`builder$iv$iv$iv`, sseRequestAttr, Boxing.boxBoolean(true));
   addAttribute(`builder$iv$iv$iv`, reconnectionTimeAttr, reconnectionTime);
   addAttribute(`builder$iv$iv$iv`, showCommentEventsAttr, showCommentEvents);
   addAttribute(`builder$iv$iv$iv`, showRetryEventsAttr, showRetryEvents);
   kotlinx.coroutines.BuildersKt.launch$default(
      `$this$serverSentEventsSession_u2di8z2VEo`,
      null,
      null,
      new 1(
         new HttpStatement(`builder$iv$iv$iv`, `$this$serverSentEventsSession_u2di8z2VEo`),
         `sessionDeferred$iv`,
         `$this$serverSentEventsSession_u2di8z2VEo`,
         null
      ),
      3,
      null
   );
   return `sessionDeferred$iv`.await(`$completion`);
}

@JvmSynthetic
fun `serverSentEventsSession-i8z2VEo$default`(
   var0: HttpClient, var1: Duration, var2: java.lang.Boolean, var3: java.lang.Boolean, var4: Function1, var5: Continuation, var6: Int, var7: Any
): Any {
   if ((var6 and 1) != 0) {
      var1 = null;
   }

   if ((var6 and 2) != 0) {
      var2 = null;
   }

   if ((var6 and 4) != 0) {
      var3 = null;
   }

   return serverSentEventsSession-i8z2VEo(var0, var1, var2, var3, var4, var5);
}

public suspend fun HttpClient.serverSentEventsSession(
   scheme: String? = ...,
   host: String? = ...,
   port: Int? = ...,
   path: String? = ...,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (HttpRequestBuilder) -> Unit = ...
): ClientSSESession {
   return serverSentEventsSession-i8z2VEo(
      `$this$serverSentEventsSession_u2dxEWcMm4`,
      reconnectionTime,
      showCommentEvents,
      showRetryEvents,
      BuildersKt::serverSentEventsSession_xEWcMm4$lambda$1,
      `$completion`
   );
}

@JvmSynthetic
fun `serverSentEventsSession-xEWcMm4$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: java.lang.String,
   var3: Int,
   var4: java.lang.String,
   var5: Duration,
   var6: java.lang.Boolean,
   var7: java.lang.Boolean,
   var8: Function1,
   var9: Continuation,
   var10: Int,
   var11: Any
): Any {
   if ((var10 and 1) != 0) {
      var1 = null;
   }

   if ((var10 and 2) != 0) {
      var2 = null;
   }

   if ((var10 and 4) != 0) {
      var3 = null;
   }

   if ((var10 and 8) != 0) {
      var4 = null;
   }

   if ((var10 and 16) != 0) {
      var5 = null;
   }

   if ((var10 and 32) != 0) {
      var6 = null;
   }

   if ((var10 and 64) != 0) {
      var7 = null;
   }

   if ((var10 and 128) != 0) {
      var8 = BuildersKt::serverSentEventsSession_xEWcMm4$lambda$0;
   }

   return serverSentEventsSession-xEWcMm4(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9);
}

public suspend fun HttpClient.serverSentEventsSession(
   urlString: String,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (HttpRequestBuilder) -> Unit = ...
): ClientSSESession {
   return serverSentEventsSession-i8z2VEo(
      `$this$serverSentEventsSession_u2dmY9Nd3A`,
      reconnectionTime,
      showCommentEvents,
      showRetryEvents,
      BuildersKt::serverSentEventsSession_mY9Nd3A$lambda$1,
      `$completion`
   );
}

@JvmSynthetic
fun `serverSentEventsSession-mY9Nd3A$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: Duration,
   var3: java.lang.Boolean,
   var4: java.lang.Boolean,
   var5: Function1,
   var6: Continuation,
   var7: Int,
   var8: Any
): Any {
   if ((var7 and 2) != 0) {
      var2 = null;
   }

   if ((var7 and 4) != 0) {
      var3 = null;
   }

   if ((var7 and 8) != 0) {
      var4 = null;
   }

   if ((var7 and 16) != 0) {
      var5 = BuildersKt::serverSentEventsSession_mY9Nd3A$lambda$0;
   }

   return serverSentEventsSession-mY9Nd3A(var0, var1, var2, var3, var4, var5, var6);
}

public suspend fun HttpClient.serverSentEvents(
   request: (HttpRequestBuilder) -> Unit,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (ClientSSESession, Continuation<Unit>) -> Any?
) {
   // $VF: Couldn't be decompiled
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
   //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
   //
   // Bytecode:
   // 000: aload 6
   // 002: instanceof io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1
   // 005: ifeq 02b
   // 008: aload 6
   // 00a: checkcast io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1
   // 00d: astore 10
   // 00f: aload 10
   // 011: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.label I
   // 014: ldc_w -2147483648
   // 017: iand
   // 018: ifeq 02b
   // 01b: aload 10
   // 01d: dup
   // 01e: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.label I
   // 021: ldc_w -2147483648
   // 024: isub
   // 025: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.label I
   // 028: goto 036
   // 02b: new io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1
   // 02e: dup
   // 02f: aload 6
   // 031: invokespecial io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.<init> (Lkotlin/coroutines/Continuation;)V
   // 034: astore 10
   // 036: aload 10
   // 038: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.result Ljava/lang/Object;
   // 03b: astore 9
   // 03d: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
   // 040: astore 11
   // 042: aload 10
   // 044: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.label I
   // 047: tableswitch 394 0 2 25 106 261
   // 060: aload 9
   // 062: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 065: aload 0
   // 066: aload 2
   // 067: aload 3
   // 068: aload 4
   // 06a: aload 1
   // 06b: aload 10
   // 06d: aload 10
   // 06f: aload 0
   // 070: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$0 Ljava/lang/Object;
   // 073: aload 10
   // 075: aload 1
   // 076: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 079: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$1 Ljava/lang/Object;
   // 07c: aload 10
   // 07e: aload 2
   // 07f: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 082: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$2 Ljava/lang/Object;
   // 085: aload 10
   // 087: aload 3
   // 088: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 08b: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$3 Ljava/lang/Object;
   // 08e: aload 10
   // 090: aload 4
   // 092: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 095: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$4 Ljava/lang/Object;
   // 098: aload 10
   // 09a: aload 5
   // 09c: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$5 Ljava/lang/Object;
   // 09f: aload 10
   // 0a1: bipush 1
   // 0a2: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.label I
   // 0a5: invokestatic io/ktor/client/plugins/sse/BuildersKt.serverSentEventsSession-i8z2VEo (Lio/ktor/client/HttpClient;Lkotlin/time/Duration;Ljava/lang/Boolean;Ljava/lang/Boolean;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 0a8: dup
   // 0a9: aload 11
   // 0ab: if_acmpne 0f0
   // 0ae: aload 11
   // 0b0: areturn
   // 0b1: aload 10
   // 0b3: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$5 Ljava/lang/Object;
   // 0b6: checkcast kotlin/jvm/functions/Function2
   // 0b9: astore 5
   // 0bb: aload 10
   // 0bd: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$4 Ljava/lang/Object;
   // 0c0: checkcast java/lang/Boolean
   // 0c3: astore 4
   // 0c5: aload 10
   // 0c7: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$3 Ljava/lang/Object;
   // 0ca: checkcast java/lang/Boolean
   // 0cd: astore 3
   // 0ce: aload 10
   // 0d0: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$2 Ljava/lang/Object;
   // 0d3: checkcast kotlin/time/Duration
   // 0d6: astore 2
   // 0d7: aload 10
   // 0d9: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$1 Ljava/lang/Object;
   // 0dc: checkcast kotlin/jvm/functions/Function1
   // 0df: astore 1
   // 0e0: aload 10
   // 0e2: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$0 Ljava/lang/Object;
   // 0e5: checkcast io/ktor/client/HttpClient
   // 0e8: astore 0
   // 0e9: aload 9
   // 0eb: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 0ee: aload 9
   // 0f0: checkcast io/ktor/client/plugins/sse/ClientSSESession
   // 0f3: astore 7
   // 0f5: nop
   // 0f6: aload 5
   // 0f8: aload 7
   // 0fa: aload 10
   // 0fc: aload 10
   // 0fe: aload 0
   // 0ff: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$0 Ljava/lang/Object;
   // 102: aload 10
   // 104: aload 1
   // 105: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 108: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$1 Ljava/lang/Object;
   // 10b: aload 10
   // 10d: aload 2
   // 10e: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 111: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$2 Ljava/lang/Object;
   // 114: aload 10
   // 116: aload 3
   // 117: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 11a: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$3 Ljava/lang/Object;
   // 11d: aload 10
   // 11f: aload 4
   // 121: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 124: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$4 Ljava/lang/Object;
   // 127: aload 10
   // 129: aload 5
   // 12b: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 12e: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$5 Ljava/lang/Object;
   // 131: aload 10
   // 133: aload 7
   // 135: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$6 Ljava/lang/Object;
   // 138: aload 10
   // 13a: bipush 2
   // 13b: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.label I
   // 13e: invokeinterface kotlin/jvm/functions/Function2.invoke (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; 3
   // 143: dup
   // 144: aload 11
   // 146: if_acmpne 196
   // 149: aload 11
   // 14b: areturn
   // 14c: aload 10
   // 14e: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$6 Ljava/lang/Object;
   // 151: checkcast io/ktor/client/plugins/sse/ClientSSESession
   // 154: astore 7
   // 156: aload 10
   // 158: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$5 Ljava/lang/Object;
   // 15b: checkcast kotlin/jvm/functions/Function2
   // 15e: astore 5
   // 160: aload 10
   // 162: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$4 Ljava/lang/Object;
   // 165: checkcast java/lang/Boolean
   // 168: astore 4
   // 16a: aload 10
   // 16c: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$3 Ljava/lang/Object;
   // 16f: checkcast java/lang/Boolean
   // 172: astore 3
   // 173: aload 10
   // 175: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$2 Ljava/lang/Object;
   // 178: checkcast kotlin/time/Duration
   // 17b: astore 2
   // 17c: aload 10
   // 17e: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$1 Ljava/lang/Object;
   // 181: checkcast kotlin/jvm/functions/Function1
   // 184: astore 1
   // 185: aload 10
   // 187: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$1.L$0 Ljava/lang/Object;
   // 18a: checkcast io/ktor/client/HttpClient
   // 18d: astore 0
   // 18e: nop
   // 18f: aload 9
   // 191: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 194: aload 9
   // 196: pop
   // 197: aload 7
   // 199: checkcast kotlinx/coroutines/CoroutineScope
   // 19c: aconst_null
   // 19d: bipush 1
   // 19e: aconst_null
   // 19f: invokestatic kotlinx/coroutines/CoroutineScopeKt.cancel$default (Lkotlinx/coroutines/CoroutineScope;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V
   // 1a2: goto 1cd
   // 1a5: astore 8
   // 1a7: aload 8
   // 1a9: athrow
   // 1aa: astore 8
   // 1ac: aload 0
   // 1ad: aload 7
   // 1af: invokevirtual io/ktor/client/plugins/sse/ClientSSESession.getCall ()Lio/ktor/client/call/HttpClientCall;
   // 1b2: aload 7
   // 1b4: invokevirtual io/ktor/client/plugins/sse/ClientSSESession.bodyBuffer ()[B
   // 1b7: aload 8
   // 1b9: invokestatic io/ktor/client/plugins/sse/BuildersKt.mapToSSEException (Lio/ktor/client/HttpClient;Lio/ktor/client/call/HttpClientCall;[BLjava/lang/Throwable;)Ljava/lang/Throwable;
   // 1bc: athrow
   // 1bd: astore 8
   // 1bf: aload 7
   // 1c1: checkcast kotlinx/coroutines/CoroutineScope
   // 1c4: aconst_null
   // 1c5: bipush 1
   // 1c6: aconst_null
   // 1c7: invokestatic kotlinx/coroutines/CoroutineScopeKt.cancel$default (Lkotlinx/coroutines/CoroutineScope;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V
   // 1ca: aload 8
   // 1cc: athrow
   // 1cd: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 1d0: areturn
   // 1d1: new java/lang/IllegalStateException
   // 1d4: dup
   // 1d5: ldc_w "call to 'resume' before 'invoke' with coroutine"
   // 1d8: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 1db: athrow
}

@JvmSynthetic
fun `serverSentEvents-mY9Nd3A$default`(
   var0: HttpClient,
   var1: Function1,
   var2: Duration,
   var3: java.lang.Boolean,
   var4: java.lang.Boolean,
   var5: Function2,
   var6: Continuation,
   var7: Int,
   var8: Any
): Any {
   if ((var7 and 2) != 0) {
      var2 = null;
   }

   if ((var7 and 4) != 0) {
      var3 = null;
   }

   if ((var7 and 8) != 0) {
      var4 = null;
   }

   return serverSentEvents-mY9Nd3A(var0, var1, var2, var3, var4, var5, var6);
}

public suspend fun HttpClient.serverSentEvents(
   scheme: String? = ...,
   host: String? = ...,
   port: Int? = ...,
   path: String? = ...,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   request: (HttpRequestBuilder) -> Unit = ...,
   block: (ClientSSESession, Continuation<Unit>) -> Any?
) {
   val var10000: Any = serverSentEvents-mY9Nd3A(
      `$this$serverSentEvents_u2d1wIb_u2d0I`,
      BuildersKt::serverSentEvents_1wIb_0I$lambda$1,
      reconnectionTime,
      showCommentEvents,
      showRetryEvents,
      block,
      `$completion`
   );
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `serverSentEvents-1wIb-0I$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: java.lang.String,
   var3: Int,
   var4: java.lang.String,
   var5: Duration,
   var6: java.lang.Boolean,
   var7: java.lang.Boolean,
   var8: Function1,
   var9: Function2,
   var10: Continuation,
   var11: Int,
   var12: Any
): Any {
   if ((var11 and 1) != 0) {
      var1 = null;
   }

   if ((var11 and 2) != 0) {
      var2 = null;
   }

   if ((var11 and 4) != 0) {
      var3 = null;
   }

   if ((var11 and 8) != 0) {
      var4 = null;
   }

   if ((var11 and 16) != 0) {
      var5 = null;
   }

   if ((var11 and 32) != 0) {
      var6 = null;
   }

   if ((var11 and 64) != 0) {
      var7 = null;
   }

   if ((var11 and 128) != 0) {
      var8 = BuildersKt::serverSentEvents_1wIb_0I$lambda$0;
   }

   return serverSentEvents-1wIb-0I(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
}

public suspend fun HttpClient.serverSentEvents(
   urlString: String,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   request: (HttpRequestBuilder) -> Unit = ...,
   block: (ClientSSESession, Continuation<Unit>) -> Any?
) {
   val var10000: Any = serverSentEvents-mY9Nd3A(
      `$this$serverSentEvents_u2d3bFjkrY`,
      BuildersKt::serverSentEvents_3bFjkrY$lambda$1,
      reconnectionTime,
      showCommentEvents,
      showRetryEvents,
      block,
      `$completion`
   );
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `serverSentEvents-3bFjkrY$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: Duration,
   var3: java.lang.Boolean,
   var4: java.lang.Boolean,
   var5: Function1,
   var6: Function2,
   var7: Continuation,
   var8: Int,
   var9: Any
): Any {
   if ((var8 and 2) != 0) {
      var2 = null;
   }

   if ((var8 and 4) != 0) {
      var3 = null;
   }

   if ((var8 and 8) != 0) {
      var4 = null;
   }

   if ((var8 and 16) != 0) {
      var5 = BuildersKt::serverSentEvents_3bFjkrY$lambda$0;
   }

   return serverSentEvents-3bFjkrY(var0, var1, var2, var3, var4, var5, var6, var7);
}

public suspend fun HttpClient.sseSession(
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (HttpRequestBuilder) -> Unit
): ClientSSESession {
   return serverSentEventsSession-i8z2VEo(`$this$sseSession_u2di8z2VEo`, reconnectionTime, showCommentEvents, showRetryEvents, block, `$completion`);
}

@JvmSynthetic
fun `sseSession-i8z2VEo$default`(
   var0: HttpClient, var1: Duration, var2: java.lang.Boolean, var3: java.lang.Boolean, var4: Function1, var5: Continuation, var6: Int, var7: Any
): Any {
   if ((var6 and 1) != 0) {
      var1 = null;
   }

   if ((var6 and 2) != 0) {
      var2 = null;
   }

   if ((var6 and 4) != 0) {
      var3 = null;
   }

   return sseSession-i8z2VEo(var0, var1, var2, var3, var4, var5);
}

public suspend fun HttpClient.sseSession(
   scheme: String? = ...,
   host: String? = ...,
   port: Int? = ...,
   path: String? = ...,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (HttpRequestBuilder) -> Unit = ...
): ClientSSESession {
   return serverSentEventsSession-xEWcMm4(
      `$this$sseSession_u2dxEWcMm4`, scheme, host, port, path, reconnectionTime, showCommentEvents, showRetryEvents, block, `$completion`
   );
}

@JvmSynthetic
fun `sseSession-xEWcMm4$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: java.lang.String,
   var3: Int,
   var4: java.lang.String,
   var5: Duration,
   var6: java.lang.Boolean,
   var7: java.lang.Boolean,
   var8: Function1,
   var9: Continuation,
   var10: Int,
   var11: Any
): Any {
   if ((var10 and 1) != 0) {
      var1 = null;
   }

   if ((var10 and 2) != 0) {
      var2 = null;
   }

   if ((var10 and 4) != 0) {
      var3 = null;
   }

   if ((var10 and 8) != 0) {
      var4 = null;
   }

   if ((var10 and 16) != 0) {
      var5 = null;
   }

   if ((var10 and 32) != 0) {
      var6 = null;
   }

   if ((var10 and 64) != 0) {
      var7 = null;
   }

   if ((var10 and 128) != 0) {
      var8 = BuildersKt::sseSession_xEWcMm4$lambda$0;
   }

   return sseSession-xEWcMm4(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9);
}

public suspend fun HttpClient.sseSession(
   urlString: String,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (HttpRequestBuilder) -> Unit = ...
): ClientSSESession {
   return serverSentEventsSession-mY9Nd3A(`$this$sseSession_u2dmY9Nd3A`, urlString, reconnectionTime, showCommentEvents, showRetryEvents, block, `$completion`);
}

@JvmSynthetic
fun `sseSession-mY9Nd3A$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: Duration,
   var3: java.lang.Boolean,
   var4: java.lang.Boolean,
   var5: Function1,
   var6: Continuation,
   var7: Int,
   var8: Any
): Any {
   if ((var7 and 2) != 0) {
      var2 = null;
   }

   if ((var7 and 4) != 0) {
      var3 = null;
   }

   if ((var7 and 8) != 0) {
      var4 = null;
   }

   if ((var7 and 16) != 0) {
      var5 = BuildersKt::sseSession_mY9Nd3A$lambda$0;
   }

   return sseSession-mY9Nd3A(var0, var1, var2, var3, var4, var5, var6);
}

public suspend fun HttpClient.sse(
   request: (HttpRequestBuilder) -> Unit,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (ClientSSESession, Continuation<Unit>) -> Any?
) {
   val var10000: Any = serverSentEvents-mY9Nd3A(`$this$sse_u2dmY9Nd3A`, request, reconnectionTime, showCommentEvents, showRetryEvents, block, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `sse-mY9Nd3A$default`(
   var0: HttpClient,
   var1: Function1,
   var2: Duration,
   var3: java.lang.Boolean,
   var4: java.lang.Boolean,
   var5: Function2,
   var6: Continuation,
   var7: Int,
   var8: Any
): Any {
   if ((var7 and 2) != 0) {
      var2 = null;
   }

   if ((var7 and 4) != 0) {
      var3 = null;
   }

   if ((var7 and 8) != 0) {
      var4 = null;
   }

   return sse-mY9Nd3A(var0, var1, var2, var3, var4, var5, var6);
}

public suspend fun HttpClient.sse(
   scheme: String? = ...,
   host: String? = ...,
   port: Int? = ...,
   path: String? = ...,
   request: (HttpRequestBuilder) -> Unit = ...,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (ClientSSESession, Continuation<Unit>) -> Any?
) {
   val var10000: Any = serverSentEvents-1wIb-0I(
      `$this$sse_u2dtL6_L_u2dA`, scheme, host, port, path, reconnectionTime, showCommentEvents, showRetryEvents, request, block, `$completion`
   );
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `sse-tL6_L-A$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: java.lang.String,
   var3: Int,
   var4: java.lang.String,
   var5: Function1,
   var6: Duration,
   var7: java.lang.Boolean,
   var8: java.lang.Boolean,
   var9: Function2,
   var10: Continuation,
   var11: Int,
   var12: Any
): Any {
   if ((var11 and 1) != 0) {
      var1 = null;
   }

   if ((var11 and 2) != 0) {
      var2 = null;
   }

   if ((var11 and 4) != 0) {
      var3 = null;
   }

   if ((var11 and 8) != 0) {
      var4 = null;
   }

   if ((var11 and 16) != 0) {
      var5 = BuildersKt::sse_tL6_L_A$lambda$0;
   }

   if ((var11 and 32) != 0) {
      var6 = null;
   }

   if ((var11 and 64) != 0) {
      var7 = null;
   }

   if ((var11 and 128) != 0) {
      var8 = null;
   }

   return sse-tL6_L-A(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
}

public suspend fun HttpClient.sse(
   urlString: String,
   request: (HttpRequestBuilder) -> Unit = ...,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (ClientSSESession, Continuation<Unit>) -> Any?
) {
   val var10000: Any = serverSentEvents-3bFjkrY(
      `$this$sse_u2dMswn_u2d_c`, urlString, reconnectionTime, showCommentEvents, showRetryEvents, request, block, `$completion`
   );
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `sse-Mswn-_c$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: Function1,
   var3: Duration,
   var4: java.lang.Boolean,
   var5: java.lang.Boolean,
   var6: Function2,
   var7: Continuation,
   var8: Int,
   var9: Any
): Any {
   if ((var8 and 2) != 0) {
      var2 = BuildersKt::sse_Mswn__c$lambda$0;
   }

   if ((var8 and 4) != 0) {
      var3 = null;
   }

   if ((var8 and 8) != 0) {
      var4 = null;
   }

   if ((var8 and 16) != 0) {
      var5 = null;
   }

   return sse-Mswn-_c(var0, var1, var2, var3, var4, var5, var6, var7);
}

public suspend fun HttpClient.serverSentEventsSession(
   deserialize: (TypeInfo, String) -> Any?,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (HttpRequestBuilder) -> Unit
): ClientSSESessionWithDeserialization {
   HttpClientPluginKt.plugin(
      `$this$serverSentEventsSession_u2dmY9Nd3A`, SSEKt.getSSE() as HttpClientPlugin<? extends SSEConfig, ClientPluginInstance<SSEConfig>>
   );
   val `sessionDeferred$iv`: CompletableDeferred = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
   val `builder$iv$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   block.invoke(`builder$iv$iv$iv`);
   addAttribute(`builder$iv$iv$iv`, sseRequestAttr, Boxing.boxBoolean(true));
   addAttribute(`builder$iv$iv$iv`, reconnectionTimeAttr, reconnectionTime);
   addAttribute(`builder$iv$iv$iv`, showCommentEventsAttr, showCommentEvents);
   addAttribute(`builder$iv$iv$iv`, showRetryEventsAttr, showRetryEvents);
   addAttribute(`builder$iv$iv$iv`, deserializerAttr, deserialize);
   kotlinx.coroutines.BuildersKt.launch$default(
      `$this$serverSentEventsSession_u2dmY9Nd3A`,
      null,
      null,
      new io.ktor.client.plugins.sse.BuildersKt.serverSentEventsSession-mY9Nd3A..inlined.processSession-rp2poPw.1(
         new HttpStatement(`builder$iv$iv$iv`, `$this$serverSentEventsSession_u2dmY9Nd3A`),
         `sessionDeferred$iv`,
         `$this$serverSentEventsSession_u2dmY9Nd3A`,
         null
      ),
      3,
      null
   );
   return `sessionDeferred$iv`.await(`$completion`);
}

@JvmSynthetic
fun `serverSentEventsSession-mY9Nd3A$default`(
   var0: HttpClient,
   var1: Function2,
   var2: Duration,
   var3: java.lang.Boolean,
   var4: java.lang.Boolean,
   var5: Function1,
   var6: Continuation,
   var7: Int,
   var8: Any
): Any {
   if ((var7 and 2) != 0) {
      var2 = null;
   }

   if ((var7 and 4) != 0) {
      var3 = null;
   }

   if ((var7 and 8) != 0) {
      var4 = null;
   }

   return serverSentEventsSession-mY9Nd3A(var0, var1, var2, var3, var4, var5, var6);
}

public suspend fun HttpClient.serverSentEventsSession(
   scheme: String? = ...,
   host: String? = ...,
   port: Int? = ...,
   path: String? = ...,
   deserialize: (TypeInfo, String) -> Any?,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (HttpRequestBuilder) -> Unit = ...
): ClientSSESessionWithDeserialization {
   return serverSentEventsSession-mY9Nd3A(
      `$this$serverSentEventsSession_u2dtL6_L_u2dA`,
      deserialize,
      reconnectionTime,
      showCommentEvents,
      showRetryEvents,
      BuildersKt::serverSentEventsSession_tL6_L_A$lambda$1,
      `$completion`
   );
}

@JvmSynthetic
fun `serverSentEventsSession-tL6_L-A$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: java.lang.String,
   var3: Int,
   var4: java.lang.String,
   var5: Function2,
   var6: Duration,
   var7: java.lang.Boolean,
   var8: java.lang.Boolean,
   var9: Function1,
   var10: Continuation,
   var11: Int,
   var12: Any
): Any {
   if ((var11 and 1) != 0) {
      var1 = null;
   }

   if ((var11 and 2) != 0) {
      var2 = null;
   }

   if ((var11 and 4) != 0) {
      var3 = null;
   }

   if ((var11 and 8) != 0) {
      var4 = null;
   }

   if ((var11 and 32) != 0) {
      var6 = null;
   }

   if ((var11 and 64) != 0) {
      var7 = null;
   }

   if ((var11 and 128) != 0) {
      var8 = null;
   }

   if ((var11 and 256) != 0) {
      var9 = BuildersKt::serverSentEventsSession_tL6_L_A$lambda$0;
   }

   return serverSentEventsSession-tL6_L-A(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
}

public suspend fun HttpClient.serverSentEventsSession(
   urlString: String,
   deserialize: (TypeInfo, String) -> Any?,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (HttpRequestBuilder) -> Unit = ...
): ClientSSESessionWithDeserialization {
   return serverSentEventsSession-mY9Nd3A(
      `$this$serverSentEventsSession_u2dMswn_u2d_c`,
      deserialize,
      reconnectionTime,
      showCommentEvents,
      showRetryEvents,
      BuildersKt::serverSentEventsSession_Mswn__c$lambda$1,
      `$completion`
   );
}

@JvmSynthetic
fun `serverSentEventsSession-Mswn-_c$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: Function2,
   var3: Duration,
   var4: java.lang.Boolean,
   var5: java.lang.Boolean,
   var6: Function1,
   var7: Continuation,
   var8: Int,
   var9: Any
): Any {
   if ((var8 and 4) != 0) {
      var3 = null;
   }

   if ((var8 and 8) != 0) {
      var4 = null;
   }

   if ((var8 and 16) != 0) {
      var5 = null;
   }

   if ((var8 and 32) != 0) {
      var6 = BuildersKt::serverSentEventsSession_Mswn__c$lambda$0;
   }

   return serverSentEventsSession-Mswn-_c(var0, var1, var2, var3, var4, var5, var6, var7);
}

public suspend fun HttpClient.serverSentEvents(
   request: (HttpRequestBuilder) -> Unit,
   deserialize: (TypeInfo, String) -> Any?,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (ClientSSESessionWithDeserialization, Continuation<Unit>) -> Any?
) {
   // $VF: Couldn't be decompiled
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
   //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
   //
   // Bytecode:
   // 000: aload 7
   // 002: instanceof io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8
   // 005: ifeq 02b
   // 008: aload 7
   // 00a: checkcast io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8
   // 00d: astore 11
   // 00f: aload 11
   // 011: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.label I
   // 014: ldc_w -2147483648
   // 017: iand
   // 018: ifeq 02b
   // 01b: aload 11
   // 01d: dup
   // 01e: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.label I
   // 021: ldc_w -2147483648
   // 024: isub
   // 025: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.label I
   // 028: goto 036
   // 02b: new io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8
   // 02e: dup
   // 02f: aload 7
   // 031: invokespecial io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.<init> (Lkotlin/coroutines/Continuation;)V
   // 034: astore 11
   // 036: aload 11
   // 038: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.result Ljava/lang/Object;
   // 03b: astore 10
   // 03d: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
   // 040: astore 12
   // 042: aload 11
   // 044: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.label I
   // 047: tableswitch 436 0 2 25 118 293
   // 060: aload 10
   // 062: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 065: aload 0
   // 066: aload 2
   // 067: aload 3
   // 068: aload 4
   // 06a: aload 5
   // 06c: aload 1
   // 06d: aload 11
   // 06f: aload 11
   // 071: aload 0
   // 072: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$0 Ljava/lang/Object;
   // 075: aload 11
   // 077: aload 1
   // 078: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 07b: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$1 Ljava/lang/Object;
   // 07e: aload 11
   // 080: aload 2
   // 081: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 084: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$2 Ljava/lang/Object;
   // 087: aload 11
   // 089: aload 3
   // 08a: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 08d: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$3 Ljava/lang/Object;
   // 090: aload 11
   // 092: aload 4
   // 094: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 097: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$4 Ljava/lang/Object;
   // 09a: aload 11
   // 09c: aload 5
   // 09e: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 0a1: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$5 Ljava/lang/Object;
   // 0a4: aload 11
   // 0a6: aload 6
   // 0a8: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$6 Ljava/lang/Object;
   // 0ab: aload 11
   // 0ad: bipush 1
   // 0ae: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.label I
   // 0b1: invokestatic io/ktor/client/plugins/sse/BuildersKt.serverSentEventsSession-mY9Nd3A (Lio/ktor/client/HttpClient;Lkotlin/jvm/functions/Function2;Lkotlin/time/Duration;Ljava/lang/Boolean;Ljava/lang/Boolean;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 0b4: dup
   // 0b5: aload 12
   // 0b7: if_acmpne 106
   // 0ba: aload 12
   // 0bc: areturn
   // 0bd: aload 11
   // 0bf: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$6 Ljava/lang/Object;
   // 0c2: checkcast kotlin/jvm/functions/Function2
   // 0c5: astore 6
   // 0c7: aload 11
   // 0c9: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$5 Ljava/lang/Object;
   // 0cc: checkcast java/lang/Boolean
   // 0cf: astore 5
   // 0d1: aload 11
   // 0d3: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$4 Ljava/lang/Object;
   // 0d6: checkcast java/lang/Boolean
   // 0d9: astore 4
   // 0db: aload 11
   // 0dd: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$3 Ljava/lang/Object;
   // 0e0: checkcast kotlin/time/Duration
   // 0e3: astore 3
   // 0e4: aload 11
   // 0e6: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$2 Ljava/lang/Object;
   // 0e9: checkcast kotlin/jvm/functions/Function2
   // 0ec: astore 2
   // 0ed: aload 11
   // 0ef: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$1 Ljava/lang/Object;
   // 0f2: checkcast kotlin/jvm/functions/Function1
   // 0f5: astore 1
   // 0f6: aload 11
   // 0f8: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$0 Ljava/lang/Object;
   // 0fb: checkcast io/ktor/client/HttpClient
   // 0fe: astore 0
   // 0ff: aload 10
   // 101: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 104: aload 10
   // 106: checkcast io/ktor/client/plugins/sse/ClientSSESessionWithDeserialization
   // 109: astore 8
   // 10b: nop
   // 10c: aload 6
   // 10e: aload 8
   // 110: aload 11
   // 112: aload 11
   // 114: aload 0
   // 115: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$0 Ljava/lang/Object;
   // 118: aload 11
   // 11a: aload 1
   // 11b: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 11e: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$1 Ljava/lang/Object;
   // 121: aload 11
   // 123: aload 2
   // 124: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 127: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$2 Ljava/lang/Object;
   // 12a: aload 11
   // 12c: aload 3
   // 12d: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 130: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$3 Ljava/lang/Object;
   // 133: aload 11
   // 135: aload 4
   // 137: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 13a: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$4 Ljava/lang/Object;
   // 13d: aload 11
   // 13f: aload 5
   // 141: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 144: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$5 Ljava/lang/Object;
   // 147: aload 11
   // 149: aload 6
   // 14b: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 14e: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$6 Ljava/lang/Object;
   // 151: aload 11
   // 153: aload 8
   // 155: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$7 Ljava/lang/Object;
   // 158: aload 11
   // 15a: bipush 2
   // 15b: putfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.label I
   // 15e: invokeinterface kotlin/jvm/functions/Function2.invoke (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; 3
   // 163: dup
   // 164: aload 12
   // 166: if_acmpne 1c0
   // 169: aload 12
   // 16b: areturn
   // 16c: aload 11
   // 16e: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$7 Ljava/lang/Object;
   // 171: checkcast io/ktor/client/plugins/sse/ClientSSESessionWithDeserialization
   // 174: astore 8
   // 176: aload 11
   // 178: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$6 Ljava/lang/Object;
   // 17b: checkcast kotlin/jvm/functions/Function2
   // 17e: astore 6
   // 180: aload 11
   // 182: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$5 Ljava/lang/Object;
   // 185: checkcast java/lang/Boolean
   // 188: astore 5
   // 18a: aload 11
   // 18c: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$4 Ljava/lang/Object;
   // 18f: checkcast java/lang/Boolean
   // 192: astore 4
   // 194: aload 11
   // 196: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$3 Ljava/lang/Object;
   // 199: checkcast kotlin/time/Duration
   // 19c: astore 3
   // 19d: aload 11
   // 19f: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$2 Ljava/lang/Object;
   // 1a2: checkcast kotlin/jvm/functions/Function2
   // 1a5: astore 2
   // 1a6: aload 11
   // 1a8: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$1 Ljava/lang/Object;
   // 1ab: checkcast kotlin/jvm/functions/Function1
   // 1ae: astore 1
   // 1af: aload 11
   // 1b1: getfield io/ktor/client/plugins/sse/BuildersKt$serverSentEvents$8.L$0 Ljava/lang/Object;
   // 1b4: checkcast io/ktor/client/HttpClient
   // 1b7: astore 0
   // 1b8: nop
   // 1b9: aload 10
   // 1bb: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 1be: aload 10
   // 1c0: pop
   // 1c1: aload 8
   // 1c3: checkcast kotlinx/coroutines/CoroutineScope
   // 1c6: aconst_null
   // 1c7: bipush 1
   // 1c8: aconst_null
   // 1c9: invokestatic kotlinx/coroutines/CoroutineScopeKt.cancel$default (Lkotlinx/coroutines/CoroutineScope;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V
   // 1cc: goto 1f7
   // 1cf: astore 9
   // 1d1: aload 9
   // 1d3: athrow
   // 1d4: astore 9
   // 1d6: aload 0
   // 1d7: aload 8
   // 1d9: invokevirtual io/ktor/client/plugins/sse/ClientSSESessionWithDeserialization.getCall ()Lio/ktor/client/call/HttpClientCall;
   // 1dc: aload 8
   // 1de: invokevirtual io/ktor/client/plugins/sse/ClientSSESessionWithDeserialization.bodyBuffer ()[B
   // 1e1: aload 9
   // 1e3: invokestatic io/ktor/client/plugins/sse/BuildersKt.mapToSSEException (Lio/ktor/client/HttpClient;Lio/ktor/client/call/HttpClientCall;[BLjava/lang/Throwable;)Ljava/lang/Throwable;
   // 1e6: athrow
   // 1e7: astore 9
   // 1e9: aload 8
   // 1eb: checkcast kotlinx/coroutines/CoroutineScope
   // 1ee: aconst_null
   // 1ef: bipush 1
   // 1f0: aconst_null
   // 1f1: invokestatic kotlinx/coroutines/CoroutineScopeKt.cancel$default (Lkotlinx/coroutines/CoroutineScope;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V
   // 1f4: aload 9
   // 1f6: athrow
   // 1f7: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 1fa: areturn
   // 1fb: new java/lang/IllegalStateException
   // 1fe: dup
   // 1ff: ldc_w "call to 'resume' before 'invoke' with coroutine"
   // 202: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 205: athrow
}

@JvmSynthetic
fun `serverSentEvents-Mswn-_c$default`(
   var0: HttpClient,
   var1: Function1,
   var2: Function2,
   var3: Duration,
   var4: java.lang.Boolean,
   var5: java.lang.Boolean,
   var6: Function2,
   var7: Continuation,
   var8: Int,
   var9: Any
): Any {
   if ((var8 and 4) != 0) {
      var3 = null;
   }

   if ((var8 and 8) != 0) {
      var4 = null;
   }

   if ((var8 and 16) != 0) {
      var5 = null;
   }

   return serverSentEvents-Mswn-_c(var0, var1, var2, var3, var4, var5, var6, var7);
}

public suspend fun HttpClient.serverSentEvents(
   scheme: String? = ...,
   host: String? = ...,
   port: Int? = ...,
   path: String? = ...,
   deserialize: (TypeInfo, String) -> Any?,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   request: (HttpRequestBuilder) -> Unit = ...,
   block: (ClientSSESessionWithDeserialization, Continuation<Unit>) -> Any?
) {
   val var10000: Any = serverSentEvents-Mswn-_c(
      `$this$serverSentEvents_u2dBqdlHlk`,
      BuildersKt::serverSentEvents_BqdlHlk$lambda$1,
      deserialize,
      reconnectionTime,
      showCommentEvents,
      showRetryEvents,
      block,
      `$completion`
   );
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `serverSentEvents-BqdlHlk$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: java.lang.String,
   var3: Int,
   var4: java.lang.String,
   var5: Function2,
   var6: Duration,
   var7: java.lang.Boolean,
   var8: java.lang.Boolean,
   var9: Function1,
   var10: Function2,
   var11: Continuation,
   var12: Int,
   var13: Any
): Any {
   if ((var12 and 1) != 0) {
      var1 = null;
   }

   if ((var12 and 2) != 0) {
      var2 = null;
   }

   if ((var12 and 4) != 0) {
      var3 = null;
   }

   if ((var12 and 8) != 0) {
      var4 = null;
   }

   if ((var12 and 32) != 0) {
      var6 = null;
   }

   if ((var12 and 64) != 0) {
      var7 = null;
   }

   if ((var12 and 128) != 0) {
      var8 = null;
   }

   if ((var12 and 256) != 0) {
      var9 = BuildersKt::serverSentEvents_BqdlHlk$lambda$0;
   }

   return serverSentEvents-BqdlHlk(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
}

public suspend fun HttpClient.serverSentEvents(
   urlString: String,
   deserialize: (TypeInfo, String) -> Any?,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   request: (HttpRequestBuilder) -> Unit = ...,
   block: (ClientSSESessionWithDeserialization, Continuation<Unit>) -> Any?
) {
   val var10000: Any = serverSentEvents-Mswn-_c(
      `$this$serverSentEvents_u2dpTj2aPc`,
      BuildersKt::serverSentEvents_pTj2aPc$lambda$1,
      deserialize,
      reconnectionTime,
      showCommentEvents,
      showRetryEvents,
      block,
      `$completion`
   );
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `serverSentEvents-pTj2aPc$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: Function2,
   var3: Duration,
   var4: java.lang.Boolean,
   var5: java.lang.Boolean,
   var6: Function1,
   var7: Function2,
   var8: Continuation,
   var9: Int,
   var10: Any
): Any {
   if ((var9 and 4) != 0) {
      var3 = null;
   }

   if ((var9 and 8) != 0) {
      var4 = null;
   }

   if ((var9 and 16) != 0) {
      var5 = null;
   }

   if ((var9 and 32) != 0) {
      var6 = BuildersKt::serverSentEvents_pTj2aPc$lambda$0;
   }

   return serverSentEvents-pTj2aPc(var0, var1, var2, var3, var4, var5, var6, var7, var8);
}

public suspend fun HttpClient.sseSession(
   deserialize: (TypeInfo, String) -> Any?,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (HttpRequestBuilder) -> Unit
): ClientSSESessionWithDeserialization {
   return serverSentEventsSession-mY9Nd3A(
      `$this$sseSession_u2dmY9Nd3A`, deserialize, reconnectionTime, showCommentEvents, showRetryEvents, block, `$completion`
   );
}

@JvmSynthetic
fun `sseSession-mY9Nd3A$default`(
   var0: HttpClient,
   var1: Function2,
   var2: Duration,
   var3: java.lang.Boolean,
   var4: java.lang.Boolean,
   var5: Function1,
   var6: Continuation,
   var7: Int,
   var8: Any
): Any {
   if ((var7 and 2) != 0) {
      var2 = null;
   }

   if ((var7 and 4) != 0) {
      var3 = null;
   }

   if ((var7 and 8) != 0) {
      var4 = null;
   }

   return sseSession-mY9Nd3A(var0, var1, var2, var3, var4, var5, var6);
}

public suspend fun HttpClient.sseSession(
   scheme: String? = ...,
   host: String? = ...,
   port: Int? = ...,
   path: String? = ...,
   deserialize: (TypeInfo, String) -> Any?,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (HttpRequestBuilder) -> Unit = ...
): ClientSSESessionWithDeserialization {
   return serverSentEventsSession-tL6_L-A(
      `$this$sseSession_u2dtL6_L_u2dA`, scheme, host, port, path, deserialize, reconnectionTime, showCommentEvents, showRetryEvents, block, `$completion`
   );
}

@JvmSynthetic
fun `sseSession-tL6_L-A$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: java.lang.String,
   var3: Int,
   var4: java.lang.String,
   var5: Function2,
   var6: Duration,
   var7: java.lang.Boolean,
   var8: java.lang.Boolean,
   var9: Function1,
   var10: Continuation,
   var11: Int,
   var12: Any
): Any {
   if ((var11 and 1) != 0) {
      var1 = null;
   }

   if ((var11 and 2) != 0) {
      var2 = null;
   }

   if ((var11 and 4) != 0) {
      var3 = null;
   }

   if ((var11 and 8) != 0) {
      var4 = null;
   }

   if ((var11 and 32) != 0) {
      var6 = null;
   }

   if ((var11 and 64) != 0) {
      var7 = null;
   }

   if ((var11 and 128) != 0) {
      var8 = null;
   }

   if ((var11 and 256) != 0) {
      var9 = BuildersKt::sseSession_tL6_L_A$lambda$0;
   }

   return sseSession-tL6_L-A(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
}

public suspend fun HttpClient.sseSession(
   urlString: String,
   deserialize: (TypeInfo, String) -> Any?,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (HttpRequestBuilder) -> Unit = ...
): ClientSSESessionWithDeserialization {
   return serverSentEventsSession-Mswn-_c(
      `$this$sseSession_u2dMswn_u2d_c`, urlString, deserialize, reconnectionTime, showCommentEvents, showRetryEvents, block, `$completion`
   );
}

@JvmSynthetic
fun `sseSession-Mswn-_c$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: Function2,
   var3: Duration,
   var4: java.lang.Boolean,
   var5: java.lang.Boolean,
   var6: Function1,
   var7: Continuation,
   var8: Int,
   var9: Any
): Any {
   if ((var8 and 4) != 0) {
      var3 = null;
   }

   if ((var8 and 8) != 0) {
      var4 = null;
   }

   if ((var8 and 16) != 0) {
      var5 = null;
   }

   if ((var8 and 32) != 0) {
      var6 = BuildersKt::sseSession_Mswn__c$lambda$0;
   }

   return sseSession-Mswn-_c(var0, var1, var2, var3, var4, var5, var6, var7);
}

public suspend fun HttpClient.sse(
   request: (HttpRequestBuilder) -> Unit,
   deserialize: (TypeInfo, String) -> Any?,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (ClientSSESessionWithDeserialization, Continuation<Unit>) -> Any?
) {
   val var10000: Any = serverSentEvents-Mswn-_c(
      `$this$sse_u2dMswn_u2d_c`, request, deserialize, reconnectionTime, showCommentEvents, showRetryEvents, block, `$completion`
   );
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `sse-Mswn-_c$default`(
   var0: HttpClient,
   var1: Function1,
   var2: Function2,
   var3: Duration,
   var4: java.lang.Boolean,
   var5: java.lang.Boolean,
   var6: Function2,
   var7: Continuation,
   var8: Int,
   var9: Any
): Any {
   if ((var8 and 4) != 0) {
      var3 = null;
   }

   if ((var8 and 8) != 0) {
      var4 = null;
   }

   if ((var8 and 16) != 0) {
      var5 = null;
   }

   return sse-Mswn-_c(var0, var1, var2, var3, var4, var5, var6, var7);
}

public suspend fun HttpClient.sse(
   scheme: String? = ...,
   host: String? = ...,
   port: Int? = ...,
   path: String? = ...,
   request: (HttpRequestBuilder) -> Unit = ...,
   deserialize: (TypeInfo, String) -> Any?,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (ClientSSESessionWithDeserialization, Continuation<Unit>) -> Any?
) {
   val var10000: Any = serverSentEvents-BqdlHlk(
      `$this$sse_u2dBAHpl2s`, scheme, host, port, path, deserialize, reconnectionTime, showCommentEvents, showRetryEvents, request, block, `$completion`
   );
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `sse-BAHpl2s$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: java.lang.String,
   var3: Int,
   var4: java.lang.String,
   var5: Function1,
   var6: Function2,
   var7: Duration,
   var8: java.lang.Boolean,
   var9: java.lang.Boolean,
   var10: Function2,
   var11: Continuation,
   var12: Int,
   var13: Any
): Any {
   if ((var12 and 1) != 0) {
      var1 = null;
   }

   if ((var12 and 2) != 0) {
      var2 = null;
   }

   if ((var12 and 4) != 0) {
      var3 = null;
   }

   if ((var12 and 8) != 0) {
      var4 = null;
   }

   if ((var12 and 16) != 0) {
      var5 = BuildersKt::sse_BAHpl2s$lambda$0;
   }

   if ((var12 and 64) != 0) {
      var7 = null;
   }

   if ((var12 and 128) != 0) {
      var8 = null;
   }

   if ((var12 and 256) != 0) {
      var9 = null;
   }

   return sse-BAHpl2s(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
}

public suspend fun HttpClient.sse(
   urlString: String,
   request: (HttpRequestBuilder) -> Unit = ...,
   deserialize: (TypeInfo, String) -> Any?,
   reconnectionTime: Duration? = ...,
   showCommentEvents: Boolean? = ...,
   showRetryEvents: Boolean? = ...,
   block: (ClientSSESessionWithDeserialization, Continuation<Unit>) -> Any?
) {
   val var10000: Any = serverSentEvents-pTj2aPc(
      `$this$sse_u2dQ9yt8Vw`, urlString, deserialize, reconnectionTime, showCommentEvents, showRetryEvents, request, block, `$completion`
   );
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `sse-Q9yt8Vw$default`(
   var0: HttpClient,
   var1: java.lang.String,
   var2: Function1,
   var3: Function2,
   var4: Duration,
   var5: java.lang.Boolean,
   var6: java.lang.Boolean,
   var7: Function2,
   var8: Continuation,
   var9: Int,
   var10: Any
): Any {
   if ((var9 and 2) != 0) {
      var2 = BuildersKt::sse_Q9yt8Vw$lambda$0;
   }

   if ((var9 and 8) != 0) {
      var4 = null;
   }

   if ((var9 and 16) != 0) {
      var5 = null;
   }

   if ((var9 and 32) != 0) {
      var6 = null;
   }

   return sse-Q9yt8Vw(var0, var1, var2, var3, var4, var5, var6, var7, var8);
}

@JvmSynthetic
private suspend inline fun <reified T> HttpClient.processSession(
   reconnectionTime: Duration?,
   showCommentEvents: Boolean?,
   showRetryEvents: Boolean?,
   block: (HttpRequestBuilder) -> Unit,
   additionalAttributes: (HttpRequestBuilder) -> Unit
): Any {
   HttpClientPluginKt.plugin(`$this$processSession_u2drp2poPw`, SSEKt.getSSE() as HttpClientPlugin<? extends SSEConfig, ClientPluginInstance<SSEConfig>>);
   val sessionDeferred: CompletableDeferred = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
   val `builder$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   val `$this$processSession_rp2poPw_u24lambda_u240`: HttpRequestBuilder = `builder$iv$iv`;
   block.invoke(`builder$iv$iv`);
   addAttribute(`$this$processSession_rp2poPw_u24lambda_u240`, sseRequestAttr, true);
   addAttribute(`$this$processSession_rp2poPw_u24lambda_u240`, reconnectionTimeAttr, reconnectionTime);
   addAttribute(`$this$processSession_rp2poPw_u24lambda_u240`, showCommentEventsAttr, showCommentEvents);
   addAttribute(`$this$processSession_rp2poPw_u24lambda_u240`, showRetryEventsAttr, showRetryEvents);
   additionalAttributes.invoke(`$this$processSession_rp2poPw_u24lambda_u240`);
   val statement: HttpStatement = new HttpStatement(`builder$iv$iv`, `$this$processSession_u2drp2poPw`);
   var var10000: CoroutineScope = `$this$processSession_u2drp2poPw`;
   Intrinsics.needClassReification();
   kotlinx.coroutines.BuildersKt.launch$default(var10000, null, null, new 2(statement, sessionDeferred, `$this$processSession_u2drp2poPw`, null), 3, null);
   InlineMarker.mark(0);
   var10000 = (CoroutineScope)sessionDeferred.await(`$completion`);
   InlineMarker.mark(1);
   return var10000;
}

private fun <T : Any> HttpRequestBuilder.addAttribute(attributeKey: AttributeKey<Any>, value: Any?) {
   if (value != null) {
      `$this$addAttribute`.getAttributes().put(attributeKey, value);
   }
}

private fun HttpClient.mapToSSEException(call: HttpClientCall?, body: ByteArray?, cause: Throwable): Throwable {
   val var10000: SavedHttpResponse;
   if (call == null) {
      var10000 = null;
   } else {
      var var8: ByteArray = body;
      if (body == null) {
         var8 = new byte[0];
      }

      val savedCall: SavedHttpCall = new SavedHttpCall(`$this$mapToSSEException`, call.getRequest(), call.getResponse(), var8);
      savedCall.getAttributes().remove(HttpClientCall.Companion.getCustomResponse$ktor_client_core());
      savedCall.getAttributes().remove(sseRequestAttr);
      val response: SavedHttpResponse = new SavedHttpResponse(savedCall, var8, call.getResponse());
      savedCall.setResponse$ktor_client_core(response);
      var10000 = response;
   }

   return if (cause is SSEClientException && (cause as SSEClientException).getResponse() != null)
      cause
      else
      new SSEClientException(var10000, cause, cause.getMessage());
}

public fun HttpRequestBuilder.bufferPolicy(policy: SSEBufferPolicy) {
   `$this$bufferPolicy`.getAttributes().put(sseBufferPolicyAttr, policy);
}

fun `SSE$lambda$0`(`$config`: Function1, `$this$install`: SSEConfig): Unit {
   `$config`.invoke(`$this$install`);
   return Unit.INSTANCE;
}

fun `serverSentEventsSession_xEWcMm4$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `serverSentEventsSession_xEWcMm4$lambda$1`(
   `$scheme`: java.lang.String,
   `$host`: java.lang.String,
   `$port`: Int,
   `$path`: java.lang.String,
   `$block`: Function1,
   `$this$serverSentEventsSession`: HttpRequestBuilder
): Unit {
   HttpRequestKt.url$default(`$this$serverSentEventsSession`, `$scheme`, `$host`, `$port`, `$path`, null, 16, null);
   `$block`.invoke(`$this$serverSentEventsSession`);
   return Unit.INSTANCE;
}

fun `serverSentEventsSession_mY9Nd3A$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `serverSentEventsSession_mY9Nd3A$lambda$1`(`$urlString`: java.lang.String, `$block`: Function1, `$this$serverSentEventsSession`: HttpRequestBuilder): Unit {
   URLParserKt.takeFrom(`$this$serverSentEventsSession`.getUrl(), `$urlString`);
   `$block`.invoke(`$this$serverSentEventsSession`);
   return Unit.INSTANCE;
}

fun `serverSentEvents_1wIb_0I$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `serverSentEvents_1wIb_0I$lambda$1`(
   `$scheme`: java.lang.String,
   `$host`: java.lang.String,
   `$port`: Int,
   `$path`: java.lang.String,
   `$request`: Function1,
   `$this$serverSentEvents`: HttpRequestBuilder
): Unit {
   HttpRequestKt.url$default(`$this$serverSentEvents`, `$scheme`, `$host`, `$port`, `$path`, null, 16, null);
   `$request`.invoke(`$this$serverSentEvents`);
   return Unit.INSTANCE;
}

fun `serverSentEvents_3bFjkrY$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `serverSentEvents_3bFjkrY$lambda$1`(`$urlString`: java.lang.String, `$request`: Function1, `$this$serverSentEvents`: HttpRequestBuilder): Unit {
   URLParserKt.takeFrom(`$this$serverSentEvents`.getUrl(), `$urlString`);
   `$request`.invoke(`$this$serverSentEvents`);
   return Unit.INSTANCE;
}

fun `sseSession_xEWcMm4$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `sseSession_mY9Nd3A$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `sse_tL6_L_A$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `sse_Mswn__c$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `serverSentEventsSession_tL6_L_A$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `serverSentEventsSession_tL6_L_A$lambda$1`(
   `$scheme`: java.lang.String,
   `$host`: java.lang.String,
   `$port`: Int,
   `$path`: java.lang.String,
   `$block`: Function1,
   `$this$serverSentEventsSession`: HttpRequestBuilder
): Unit {
   HttpRequestKt.url$default(`$this$serverSentEventsSession`, `$scheme`, `$host`, `$port`, `$path`, null, 16, null);
   `$block`.invoke(`$this$serverSentEventsSession`);
   return Unit.INSTANCE;
}

fun `serverSentEventsSession_Mswn__c$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `serverSentEventsSession_Mswn__c$lambda$1`(`$urlString`: java.lang.String, `$block`: Function1, `$this$serverSentEventsSession`: HttpRequestBuilder): Unit {
   URLParserKt.takeFrom(`$this$serverSentEventsSession`.getUrl(), `$urlString`);
   `$block`.invoke(`$this$serverSentEventsSession`);
   return Unit.INSTANCE;
}

fun `serverSentEvents_BqdlHlk$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `serverSentEvents_BqdlHlk$lambda$1`(
   `$scheme`: java.lang.String,
   `$host`: java.lang.String,
   `$port`: Int,
   `$path`: java.lang.String,
   `$request`: Function1,
   `$this$serverSentEvents`: HttpRequestBuilder
): Unit {
   HttpRequestKt.url$default(`$this$serverSentEvents`, `$scheme`, `$host`, `$port`, `$path`, null, 16, null);
   `$request`.invoke(`$this$serverSentEvents`);
   return Unit.INSTANCE;
}

fun `serverSentEvents_pTj2aPc$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `serverSentEvents_pTj2aPc$lambda$1`(`$urlString`: java.lang.String, `$request`: Function1, `$this$serverSentEvents`: HttpRequestBuilder): Unit {
   URLParserKt.takeFrom(`$this$serverSentEvents`.getUrl(), `$urlString`);
   `$request`.invoke(`$this$serverSentEvents`);
   return Unit.INSTANCE;
}

fun `sseSession_tL6_L_A$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `sseSession_Mswn__c$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `sse_BAHpl2s$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `sse_Q9yt8Vw$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$mapToSSEException`(`$receiver`: HttpClient, call: HttpClientCall, body: ByteArray, cause: java.lang.Throwable): java.lang.Throwable {
   return mapToSSEException(`$receiver`, call, body, cause);
}
