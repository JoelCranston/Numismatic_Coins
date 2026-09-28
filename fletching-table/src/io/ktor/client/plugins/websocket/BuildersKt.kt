@file:SourceDebugExtension(["SMAP\nbuilders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 builders.kt\nio/ktor/client/plugins/websocket/BuildersKt\n+ 2 builders.kt\nio/ktor/client/request/BuildersKt\n+ 3 HttpStatement.kt\nio/ktor/client/statement/HttpStatement\n+ 4 HttpTimeout.kt\nio/ktor/client/plugins/HttpTimeoutKt\n+ 5 HttpClientCall.kt\nio/ktor/client/call/HttpClientCallKt\n+ 6 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,265:1\n93#2:266\n52#2:267\n93#2:268\n52#2:269\n132#3:270\n133#3,3:273\n136#3,3:286\n308#4,2:271\n310#4,2:289\n162#5:276\n69#6:277\n84#6,8:278\n*S KotlinDebug\n*F\n+ 1 builders.kt\nio/ktor/client/plugins/websocket/BuildersKt\n*L\n36#1:266\n36#1:267\n105#1:268\n105#1:269\n112#1:270\n112#1:273,3\n112#1:286,3\n112#1:271,2\n112#1:289,2\n112#1:276\n112#1:277\n112#1:278,8\n*E\n"])

package io.ktor.client.plugins.websocket

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpClientPluginKt
import io.ktor.client.plugins.websocket.BuildersKt.webSocketSession.2
import io.ktor.client.plugins.websocket.WebSockets.Config
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestKt
import io.ktor.client.request.UtilsKt
import io.ktor.client.statement.HttpStatement
import io.ktor.http.HttpMethod
import io.ktor.http.URLBuilder
import io.ktor.http.URLParserKt
import io.ktor.http.URLProtocol
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CompletableDeferredKt

public fun HttpClientConfig<*>.WebSockets(config: (Config) -> Unit) {
   `$this$WebSockets`.install(WebSockets.Plugin, BuildersKt::WebSockets$lambda$0);
}

public suspend fun HttpClient.webSocketSession(block: (HttpRequestBuilder) -> Unit): DefaultClientWebSocketSession {
   HttpClientPluginKt.plugin(`$this$webSocketSession`, WebSockets.Plugin);
   val sessionDeferred: CompletableDeferred = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
   val `builder$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv$iv`.url(BuildersKt::webSocketSession$lambda$0$0);
   block.invoke(`builder$iv$iv`);
   kotlinx.coroutines.BuildersKt.launch$default(
      `$this$webSocketSession`, null, null, new 2(new HttpStatement(`builder$iv$iv`, `$this$webSocketSession`), sessionDeferred, null), 3, null
   );
   return sessionDeferred.await(`$completion`);
}

public suspend fun HttpClient.webSocketSession(
   method: HttpMethod = ...,
   host: String? = ...,
   port: Int? = ...,
   path: String? = ...,
   block: (HttpRequestBuilder) -> Unit = ...
): DefaultClientWebSocketSession {
   return webSocketSession(`$this$webSocketSession`, BuildersKt::webSocketSession$lambda$2, `$completion`);
}

@JvmSynthetic
fun `webSocketSession$default`(
   var0: HttpClient, var1: HttpMethod, var2: java.lang.String, var3: Int, var4: java.lang.String, var5: Function1, var6: Continuation, var7: Int, var8: Any
): Any {
   if ((var7 and 1) != 0) {
      var1 = HttpMethod.Companion.getGet();
   }

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
      var5 = BuildersKt::webSocketSession$lambda$1;
   }

   return webSocketSession(var0, var1, var2, var3, var4, var5, var6);
}

public suspend fun HttpClient.webSocketSession(urlString: String, block: (HttpRequestBuilder) -> Unit = ...): DefaultClientWebSocketSession {
   return webSocketSession(`$this$webSocketSession`, BuildersKt::webSocketSession$lambda$4, `$completion`);
}

@JvmSynthetic
fun `webSocketSession$default`(var0: HttpClient, var1: java.lang.String, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersKt::webSocketSession$lambda$3;
   }

   return webSocketSession(var0, var1, var2, var3);
}

public suspend fun HttpClient.webSocket(request: (HttpRequestBuilder) -> Unit, block: (DefaultClientWebSocketSession, Continuation<Unit>) -> Any?) {
   // $VF: Couldn't be decompiled
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
   //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
   //
   // Bytecode:
   // 000: aload 3
   // 001: instanceof io/ktor/client/plugins/websocket/BuildersKt$webSocket$1
   // 004: ifeq 027
   // 007: aload 3
   // 008: checkcast io/ktor/client/plugins/websocket/BuildersKt$webSocket$1
   // 00b: astore 27
   // 00d: aload 27
   // 00f: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.label I
   // 012: ldc -2147483648
   // 014: iand
   // 015: ifeq 027
   // 018: aload 27
   // 01a: dup
   // 01b: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.label I
   // 01e: ldc -2147483648
   // 020: isub
   // 021: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.label I
   // 024: goto 031
   // 027: new io/ktor/client/plugins/websocket/BuildersKt$webSocket$1
   // 02a: dup
   // 02b: aload 3
   // 02c: invokespecial io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.<init> (Lkotlin/coroutines/Continuation;)V
   // 02f: astore 27
   // 031: aload 27
   // 033: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.result Ljava/lang/Object;
   // 036: astore 26
   // 038: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
   // 03b: astore 28
   // 03d: aload 27
   // 03f: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.label I
   // 042: tableswitch 1950 0 7 46 228 498 777 1035 1316 1595 1835
   // 070: aload 26
   // 072: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 075: aload 0
   // 076: getstatic io/ktor/client/plugins/websocket/WebSockets.Plugin Lio/ktor/client/plugins/websocket/WebSockets$Plugin;
   // 079: checkcast io/ktor/client/plugins/HttpClientPlugin
   // 07c: invokestatic io/ktor/client/plugins/HttpClientPluginKt.plugin (Lio/ktor/client/HttpClient;Lio/ktor/client/plugins/HttpClientPlugin;)Ljava/lang/Object;
   // 07f: pop
   // 080: aload 0
   // 081: astore 5
   // 083: bipush 0
   // 084: istore 6
   // 086: aload 5
   // 088: astore 7
   // 08a: new io/ktor/client/request/HttpRequestBuilder
   // 08d: dup
   // 08e: invokespecial io/ktor/client/request/HttpRequestBuilder.<init> ()V
   // 091: astore 8
   // 093: aload 8
   // 095: astore 9
   // 097: bipush 0
   // 098: istore 10
   // 09a: aload 9
   // 09c: invokedynamic invoke ()Lkotlin/jvm/functions/Function2; bsm=java/lang/invoke/LambdaMetafactory.metafactory (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite; args=[ (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, io/ktor/client/plugins/websocket/BuildersKt.webSocket$lambda$0$0 (Lio/ktor/http/URLBuilder;Lio/ktor/http/URLBuilder;)Lkotlin/Unit;, (Lio/ktor/http/URLBuilder;Lio/ktor/http/URLBuilder;)Lkotlin/Unit; ]
   // 0a1: invokevirtual io/ktor/client/request/HttpRequestBuilder.url (Lkotlin/jvm/functions/Function2;)V
   // 0a4: aload 1
   // 0a5: aload 9
   // 0a7: invokeinterface kotlin/jvm/functions/Function1.invoke (Ljava/lang/Object;)Ljava/lang/Object; 2
   // 0ac: pop
   // 0ad: nop
   // 0ae: aload 8
   // 0b0: astore 8
   // 0b2: nop
   // 0b3: bipush 0
   // 0b4: istore 11
   // 0b6: new io/ktor/client/statement/HttpStatement
   // 0b9: dup
   // 0ba: aload 8
   // 0bc: aload 7
   // 0be: invokespecial io/ktor/client/statement/HttpStatement.<init> (Lio/ktor/client/request/HttpRequestBuilder;Lio/ktor/client/HttpClient;)V
   // 0c1: nop
   // 0c2: astore 4
   // 0c4: aload 4
   // 0c6: astore 5
   // 0c8: bipush 0
   // 0c9: istore 6
   // 0cb: bipush 0
   // 0cc: istore 7
   // 0ce: nop
   // 0cf: bipush 0
   // 0d0: istore 8
   // 0d2: aload 5
   // 0d4: aload 27
   // 0d6: aload 27
   // 0d8: aload 0
   // 0d9: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 0dc: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 0df: aload 27
   // 0e1: aload 1
   // 0e2: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 0e5: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 0e8: aload 27
   // 0ea: aload 2
   // 0eb: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 0ee: aload 27
   // 0f0: aload 4
   // 0f2: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 0f5: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 0f8: aload 27
   // 0fa: aload 5
   // 0fc: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 0ff: aload 27
   // 101: iload 6
   // 103: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 106: aload 27
   // 108: iload 7
   // 10a: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 10d: aload 27
   // 10f: iload 8
   // 111: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 114: aload 27
   // 116: bipush 1
   // 117: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.label I
   // 11a: invokevirtual io/ktor/client/statement/HttpStatement.fetchStreamingResponse (Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 11d: dup
   // 11e: aload 28
   // 120: if_acmpne 172
   // 123: aload 28
   // 125: areturn
   // 126: aload 27
   // 128: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 12b: istore 8
   // 12d: aload 27
   // 12f: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 132: istore 7
   // 134: aload 27
   // 136: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 139: istore 6
   // 13b: aload 27
   // 13d: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 140: checkcast io/ktor/client/statement/HttpStatement
   // 143: astore 5
   // 145: aload 27
   // 147: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 14a: checkcast io/ktor/client/statement/HttpStatement
   // 14d: astore 4
   // 14f: aload 27
   // 151: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 154: checkcast kotlin/jvm/functions/Function2
   // 157: astore 2
   // 158: aload 27
   // 15a: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 15d: checkcast kotlin/jvm/functions/Function1
   // 160: astore 1
   // 161: aload 27
   // 163: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 166: checkcast io/ktor/client/HttpClient
   // 169: astore 0
   // 16a: nop
   // 16b: aload 26
   // 16d: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 170: aload 26
   // 172: checkcast io/ktor/client/statement/HttpResponse
   // 175: astore 9
   // 177: nop
   // 178: aload 9
   // 17a: astore 10
   // 17c: aload 27
   // 17e: astore 11
   // 180: bipush 0
   // 181: istore 12
   // 183: aload 10
   // 185: invokevirtual io/ktor/client/statement/HttpResponse.getCall ()Lio/ktor/client/call/HttpClientCall;
   // 188: astore 13
   // 18a: bipush 0
   // 18b: istore 14
   // 18d: ldc_w io/ktor/client/plugins/websocket/DefaultClientWebSocketSession
   // 190: invokestatic kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
   // 193: astore 15
   // 195: bipush 0
   // 196: istore 16
   // 198: nop
   // 199: ldc_w io/ktor/client/plugins/websocket/DefaultClientWebSocketSession
   // 19c: invokestatic kotlin/jvm/internal/Reflection.typeOf (Ljava/lang/Class;)Lkotlin/reflect/KType;
   // 19f: astore 17
   // 1a1: goto 1a9
   // 1a4: astore 18
   // 1a6: aconst_null
   // 1a7: astore 17
   // 1a9: aload 15
   // 1ab: nop
   // 1ac: aload 17
   // 1ae: astore 19
   // 1b0: astore 20
   // 1b2: new io/ktor/util/reflect/TypeInfo
   // 1b5: dup
   // 1b6: aload 20
   // 1b8: aload 19
   // 1ba: invokespecial io/ktor/util/reflect/TypeInfo.<init> (Lkotlin/reflect/KClass;Lkotlin/reflect/KType;)V
   // 1bd: aload 13
   // 1bf: swap
   // 1c0: aload 11
   // 1c2: aload 27
   // 1c4: aload 0
   // 1c5: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 1c8: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 1cb: aload 27
   // 1cd: aload 1
   // 1ce: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 1d1: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 1d4: aload 27
   // 1d6: aload 2
   // 1d7: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 1da: aload 27
   // 1dc: aload 4
   // 1de: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 1e1: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 1e4: aload 27
   // 1e6: aload 5
   // 1e8: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 1eb: aload 27
   // 1ed: aload 9
   // 1ef: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$5 Ljava/lang/Object;
   // 1f2: aload 27
   // 1f4: aload 10
   // 1f6: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 1f9: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$6 Ljava/lang/Object;
   // 1fc: aload 27
   // 1fe: aload 11
   // 200: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 203: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$7 Ljava/lang/Object;
   // 206: aload 27
   // 208: iload 6
   // 20a: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 20d: aload 27
   // 20f: iload 7
   // 211: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 214: aload 27
   // 216: iload 8
   // 218: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 21b: aload 27
   // 21d: iload 12
   // 21f: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$3 I
   // 222: aload 27
   // 224: bipush 2
   // 225: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.label I
   // 228: invokevirtual io/ktor/client/call/HttpClientCall.bodyNullable (Lio/ktor/util/reflect/TypeInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 22b: dup
   // 22c: aload 28
   // 22e: if_acmpne 2a5
   // 231: aload 28
   // 233: areturn
   // 234: aload 27
   // 236: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$3 I
   // 239: istore 12
   // 23b: aload 27
   // 23d: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 240: istore 8
   // 242: aload 27
   // 244: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 247: istore 7
   // 249: aload 27
   // 24b: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 24e: istore 6
   // 250: aload 27
   // 252: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$7 Ljava/lang/Object;
   // 255: checkcast io/ktor/client/plugins/websocket/BuildersKt$webSocket$1
   // 258: astore 11
   // 25a: aload 27
   // 25c: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$6 Ljava/lang/Object;
   // 25f: checkcast io/ktor/client/statement/HttpResponse
   // 262: astore 10
   // 264: aload 27
   // 266: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$5 Ljava/lang/Object;
   // 269: checkcast io/ktor/client/statement/HttpResponse
   // 26c: astore 9
   // 26e: aload 27
   // 270: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 273: checkcast io/ktor/client/statement/HttpStatement
   // 276: astore 5
   // 278: aload 27
   // 27a: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 27d: checkcast io/ktor/client/statement/HttpStatement
   // 280: astore 4
   // 282: aload 27
   // 284: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 287: checkcast kotlin/jvm/functions/Function2
   // 28a: astore 2
   // 28b: aload 27
   // 28d: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 290: checkcast kotlin/jvm/functions/Function1
   // 293: astore 1
   // 294: aload 27
   // 296: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 299: checkcast io/ktor/client/HttpClient
   // 29c: astore 0
   // 29d: nop
   // 29e: aload 26
   // 2a0: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 2a3: aload 26
   // 2a5: dup
   // 2a6: ifnonnull 2b4
   // 2a9: new java/lang/NullPointerException
   // 2ac: dup
   // 2ad: ldc_w "null cannot be cast to non-null type io.ktor.client.plugins.websocket.DefaultClientWebSocketSession"
   // 2b0: invokespecial java/lang/NullPointerException.<init> (Ljava/lang/String;)V
   // 2b3: athrow
   // 2b4: checkcast io/ktor/client/plugins/websocket/DefaultClientWebSocketSession
   // 2b7: astore 21
   // 2b9: aload 21
   // 2bb: aload 27
   // 2bd: checkcast kotlin/coroutines/Continuation
   // 2c0: astore 22
   // 2c2: astore 23
   // 2c4: bipush 0
   // 2c5: istore 24
   // 2c7: nop
   // 2c8: aload 2
   // 2c9: aload 23
   // 2cb: aload 27
   // 2cd: aload 27
   // 2cf: aload 0
   // 2d0: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 2d3: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 2d6: aload 27
   // 2d8: aload 1
   // 2d9: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 2dc: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 2df: aload 27
   // 2e1: aload 2
   // 2e2: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 2e5: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 2e8: aload 27
   // 2ea: aload 4
   // 2ec: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 2ef: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 2f2: aload 27
   // 2f4: aload 5
   // 2f6: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 2f9: aload 27
   // 2fb: aload 9
   // 2fd: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$5 Ljava/lang/Object;
   // 300: aload 27
   // 302: aload 21
   // 304: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 307: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$6 Ljava/lang/Object;
   // 30a: aload 27
   // 30c: aload 22
   // 30e: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 311: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$7 Ljava/lang/Object;
   // 314: aload 27
   // 316: aload 23
   // 318: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$8 Ljava/lang/Object;
   // 31b: aload 27
   // 31d: iload 6
   // 31f: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 322: aload 27
   // 324: iload 7
   // 326: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 329: aload 27
   // 32b: iload 8
   // 32d: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 330: aload 27
   // 332: iload 24
   // 334: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$3 I
   // 337: aload 27
   // 339: bipush 3
   // 33a: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.label I
   // 33d: invokeinterface kotlin/jvm/functions/Function2.invoke (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; 3
   // 342: dup
   // 343: aload 28
   // 345: if_acmpne 3c6
   // 348: aload 28
   // 34a: areturn
   // 34b: aload 27
   // 34d: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$3 I
   // 350: istore 24
   // 352: aload 27
   // 354: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 357: istore 8
   // 359: aload 27
   // 35b: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 35e: istore 7
   // 360: aload 27
   // 362: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 365: istore 6
   // 367: aload 27
   // 369: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$8 Ljava/lang/Object;
   // 36c: checkcast io/ktor/client/plugins/websocket/DefaultClientWebSocketSession
   // 36f: astore 23
   // 371: aload 27
   // 373: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$7 Ljava/lang/Object;
   // 376: checkcast kotlin/coroutines/Continuation
   // 379: astore 22
   // 37b: aload 27
   // 37d: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$6 Ljava/lang/Object;
   // 380: checkcast io/ktor/client/plugins/websocket/DefaultClientWebSocketSession
   // 383: astore 21
   // 385: aload 27
   // 387: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$5 Ljava/lang/Object;
   // 38a: checkcast io/ktor/client/statement/HttpResponse
   // 38d: astore 9
   // 38f: aload 27
   // 391: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 394: checkcast io/ktor/client/statement/HttpStatement
   // 397: astore 5
   // 399: aload 27
   // 39b: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 39e: checkcast io/ktor/client/statement/HttpStatement
   // 3a1: astore 4
   // 3a3: aload 27
   // 3a5: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 3a8: checkcast kotlin/jvm/functions/Function2
   // 3ab: astore 2
   // 3ac: aload 27
   // 3ae: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 3b1: checkcast kotlin/jvm/functions/Function1
   // 3b4: astore 1
   // 3b5: aload 27
   // 3b7: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 3ba: checkcast io/ktor/client/HttpClient
   // 3bd: astore 0
   // 3be: nop
   // 3bf: aload 26
   // 3c1: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 3c4: aload 26
   // 3c6: pop
   // 3c7: aload 23
   // 3c9: checkcast io/ktor/websocket/WebSocketSession
   // 3cc: aconst_null
   // 3cd: aload 27
   // 3cf: bipush 1
   // 3d0: aconst_null
   // 3d1: aload 27
   // 3d3: aload 0
   // 3d4: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 3d7: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 3da: aload 27
   // 3dc: aload 1
   // 3dd: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 3e0: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 3e3: aload 27
   // 3e5: aload 2
   // 3e6: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 3e9: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 3ec: aload 27
   // 3ee: aload 4
   // 3f0: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 3f3: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 3f6: aload 27
   // 3f8: aload 5
   // 3fa: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 3fd: aload 27
   // 3ff: aload 9
   // 401: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$5 Ljava/lang/Object;
   // 404: aload 27
   // 406: aload 21
   // 408: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 40b: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$6 Ljava/lang/Object;
   // 40e: aload 27
   // 410: aload 22
   // 412: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 415: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$7 Ljava/lang/Object;
   // 418: aload 27
   // 41a: aload 23
   // 41c: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$8 Ljava/lang/Object;
   // 41f: aload 27
   // 421: iload 6
   // 423: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 426: aload 27
   // 428: iload 7
   // 42a: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 42d: aload 27
   // 42f: iload 8
   // 431: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 434: aload 27
   // 436: iload 24
   // 438: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$3 I
   // 43b: aload 27
   // 43d: bipush 4
   // 43e: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.label I
   // 441: invokestatic io/ktor/websocket/WebSocketSessionKt.close$default (Lio/ktor/websocket/WebSocketSession;Lio/ktor/websocket/CloseReason;Lkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
   // 444: dup
   // 445: aload 28
   // 447: if_acmpne 4c8
   // 44a: aload 28
   // 44c: areturn
   // 44d: aload 27
   // 44f: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$3 I
   // 452: istore 24
   // 454: aload 27
   // 456: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 459: istore 8
   // 45b: aload 27
   // 45d: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 460: istore 7
   // 462: aload 27
   // 464: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 467: istore 6
   // 469: aload 27
   // 46b: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$8 Ljava/lang/Object;
   // 46e: checkcast io/ktor/client/plugins/websocket/DefaultClientWebSocketSession
   // 471: astore 23
   // 473: aload 27
   // 475: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$7 Ljava/lang/Object;
   // 478: checkcast kotlin/coroutines/Continuation
   // 47b: astore 22
   // 47d: aload 27
   // 47f: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$6 Ljava/lang/Object;
   // 482: checkcast io/ktor/client/plugins/websocket/DefaultClientWebSocketSession
   // 485: astore 21
   // 487: aload 27
   // 489: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$5 Ljava/lang/Object;
   // 48c: checkcast io/ktor/client/statement/HttpResponse
   // 48f: astore 9
   // 491: aload 27
   // 493: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 496: checkcast io/ktor/client/statement/HttpStatement
   // 499: astore 5
   // 49b: aload 27
   // 49d: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 4a0: checkcast io/ktor/client/statement/HttpStatement
   // 4a3: astore 4
   // 4a5: aload 27
   // 4a7: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 4aa: checkcast kotlin/jvm/functions/Function2
   // 4ad: astore 2
   // 4ae: aload 27
   // 4b0: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 4b3: checkcast kotlin/jvm/functions/Function1
   // 4b6: astore 1
   // 4b7: aload 27
   // 4b9: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 4bc: checkcast io/ktor/client/HttpClient
   // 4bf: astore 0
   // 4c0: nop
   // 4c1: aload 26
   // 4c3: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 4c6: aload 26
   // 4c8: pop
   // 4c9: aload 23
   // 4cb: invokevirtual io/ktor/client/plugins/websocket/DefaultClientWebSocketSession.getIncoming ()Lkotlinx/coroutines/channels/ReceiveChannel;
   // 4ce: aconst_null
   // 4cf: bipush 1
   // 4d0: aconst_null
   // 4d1: invokestatic kotlinx/coroutines/channels/ReceiveChannel$DefaultImpls.cancel$default (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V
   // 4d4: goto 5fa
   // 4d7: astore 25
   // 4d9: aload 23
   // 4db: checkcast io/ktor/websocket/WebSocketSession
   // 4de: aconst_null
   // 4df: aload 27
   // 4e1: bipush 1
   // 4e2: aconst_null
   // 4e3: aload 27
   // 4e5: aload 0
   // 4e6: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 4e9: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 4ec: aload 27
   // 4ee: aload 1
   // 4ef: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 4f2: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 4f5: aload 27
   // 4f7: aload 2
   // 4f8: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 4fb: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 4fe: aload 27
   // 500: aload 4
   // 502: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 505: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 508: aload 27
   // 50a: aload 5
   // 50c: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 50f: aload 27
   // 511: aload 9
   // 513: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$5 Ljava/lang/Object;
   // 516: aload 27
   // 518: aload 21
   // 51a: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 51d: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$6 Ljava/lang/Object;
   // 520: aload 27
   // 522: aload 22
   // 524: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 527: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$7 Ljava/lang/Object;
   // 52a: aload 27
   // 52c: aload 23
   // 52e: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$8 Ljava/lang/Object;
   // 531: aload 27
   // 533: aload 25
   // 535: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$9 Ljava/lang/Object;
   // 538: aload 27
   // 53a: iload 6
   // 53c: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 53f: aload 27
   // 541: iload 7
   // 543: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 546: aload 27
   // 548: iload 8
   // 54a: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 54d: aload 27
   // 54f: iload 24
   // 551: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$3 I
   // 554: aload 27
   // 556: bipush 5
   // 557: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.label I
   // 55a: invokestatic io/ktor/websocket/WebSocketSessionKt.close$default (Lio/ktor/websocket/WebSocketSession;Lio/ktor/websocket/CloseReason;Lkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
   // 55d: dup
   // 55e: aload 28
   // 560: if_acmpne 5eb
   // 563: aload 28
   // 565: areturn
   // 566: aload 27
   // 568: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$3 I
   // 56b: istore 24
   // 56d: aload 27
   // 56f: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 572: istore 8
   // 574: aload 27
   // 576: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 579: istore 7
   // 57b: aload 27
   // 57d: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 580: istore 6
   // 582: aload 27
   // 584: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$9 Ljava/lang/Object;
   // 587: checkcast java/lang/Throwable
   // 58a: astore 25
   // 58c: aload 27
   // 58e: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$8 Ljava/lang/Object;
   // 591: checkcast io/ktor/client/plugins/websocket/DefaultClientWebSocketSession
   // 594: astore 23
   // 596: aload 27
   // 598: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$7 Ljava/lang/Object;
   // 59b: checkcast kotlin/coroutines/Continuation
   // 59e: astore 22
   // 5a0: aload 27
   // 5a2: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$6 Ljava/lang/Object;
   // 5a5: checkcast io/ktor/client/plugins/websocket/DefaultClientWebSocketSession
   // 5a8: astore 21
   // 5aa: aload 27
   // 5ac: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$5 Ljava/lang/Object;
   // 5af: checkcast io/ktor/client/statement/HttpResponse
   // 5b2: astore 9
   // 5b4: aload 27
   // 5b6: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 5b9: checkcast io/ktor/client/statement/HttpStatement
   // 5bc: astore 5
   // 5be: aload 27
   // 5c0: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 5c3: checkcast io/ktor/client/statement/HttpStatement
   // 5c6: astore 4
   // 5c8: aload 27
   // 5ca: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 5cd: checkcast kotlin/jvm/functions/Function2
   // 5d0: astore 2
   // 5d1: aload 27
   // 5d3: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 5d6: checkcast kotlin/jvm/functions/Function1
   // 5d9: astore 1
   // 5da: aload 27
   // 5dc: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 5df: checkcast io/ktor/client/HttpClient
   // 5e2: astore 0
   // 5e3: nop
   // 5e4: aload 26
   // 5e6: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 5e9: aload 26
   // 5eb: pop
   // 5ec: aload 23
   // 5ee: invokevirtual io/ktor/client/plugins/websocket/DefaultClientWebSocketSession.getIncoming ()Lkotlinx/coroutines/channels/ReceiveChannel;
   // 5f1: aconst_null
   // 5f2: bipush 1
   // 5f3: aconst_null
   // 5f4: invokestatic kotlinx/coroutines/channels/ReceiveChannel$DefaultImpls.cancel$default (Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V
   // 5f7: aload 25
   // 5f9: athrow
   // 5fa: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 5fd: astore 10
   // 5ff: aload 5
   // 601: aload 9
   // 603: aload 27
   // 605: aload 27
   // 607: aload 0
   // 608: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 60b: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 60e: aload 27
   // 610: aload 1
   // 611: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 614: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 617: aload 27
   // 619: aload 2
   // 61a: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 61d: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 620: aload 27
   // 622: aload 4
   // 624: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 627: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 62a: aload 27
   // 62c: aload 5
   // 62e: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 631: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 634: aload 27
   // 636: aload 9
   // 638: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 63b: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$5 Ljava/lang/Object;
   // 63e: aload 27
   // 640: aload 10
   // 642: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$6 Ljava/lang/Object;
   // 645: aload 27
   // 647: aload 21
   // 649: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 64c: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$7 Ljava/lang/Object;
   // 64f: aload 27
   // 651: aconst_null
   // 652: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$8 Ljava/lang/Object;
   // 655: aload 27
   // 657: iload 6
   // 659: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 65c: aload 27
   // 65e: iload 7
   // 660: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 663: aload 27
   // 665: iload 8
   // 667: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 66a: aload 27
   // 66c: bipush 6
   // 66e: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.label I
   // 671: invokevirtual io/ktor/client/statement/HttpStatement.cleanup (Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 674: dup
   // 675: aload 28
   // 677: if_acmpne 6e7
   // 67a: aload 28
   // 67c: areturn
   // 67d: aload 27
   // 67f: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 682: istore 8
   // 684: aload 27
   // 686: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 689: istore 7
   // 68b: aload 27
   // 68d: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 690: istore 6
   // 692: aload 27
   // 694: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$7 Ljava/lang/Object;
   // 697: checkcast io/ktor/client/plugins/websocket/DefaultClientWebSocketSession
   // 69a: astore 21
   // 69c: aload 27
   // 69e: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$6 Ljava/lang/Object;
   // 6a1: checkcast kotlin/Unit
   // 6a4: astore 10
   // 6a6: aload 27
   // 6a8: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$5 Ljava/lang/Object;
   // 6ab: checkcast io/ktor/client/statement/HttpResponse
   // 6ae: astore 9
   // 6b0: aload 27
   // 6b2: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 6b5: checkcast io/ktor/client/statement/HttpStatement
   // 6b8: astore 5
   // 6ba: aload 27
   // 6bc: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 6bf: checkcast io/ktor/client/statement/HttpStatement
   // 6c2: astore 4
   // 6c4: aload 27
   // 6c6: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 6c9: checkcast kotlin/jvm/functions/Function2
   // 6cc: astore 2
   // 6cd: aload 27
   // 6cf: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 6d2: checkcast kotlin/jvm/functions/Function1
   // 6d5: astore 1
   // 6d6: aload 27
   // 6d8: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 6db: checkcast io/ktor/client/HttpClient
   // 6de: astore 0
   // 6df: nop
   // 6e0: aload 26
   // 6e2: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 6e5: aload 26
   // 6e7: pop
   // 6e8: goto 7dc
   // 6eb: astore 10
   // 6ed: aload 5
   // 6ef: aload 9
   // 6f1: aload 27
   // 6f3: aload 27
   // 6f5: aload 0
   // 6f6: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 6f9: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 6fc: aload 27
   // 6fe: aload 1
   // 6ff: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 702: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 705: aload 27
   // 707: aload 2
   // 708: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 70b: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 70e: aload 27
   // 710: aload 4
   // 712: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 715: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 718: aload 27
   // 71a: aload 5
   // 71c: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 71f: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 722: aload 27
   // 724: aload 9
   // 726: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 729: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$5 Ljava/lang/Object;
   // 72c: aload 27
   // 72e: aload 10
   // 730: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$6 Ljava/lang/Object;
   // 733: aload 27
   // 735: aconst_null
   // 736: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$7 Ljava/lang/Object;
   // 739: aload 27
   // 73b: aconst_null
   // 73c: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$8 Ljava/lang/Object;
   // 73f: aload 27
   // 741: aconst_null
   // 742: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$9 Ljava/lang/Object;
   // 745: aload 27
   // 747: iload 6
   // 749: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 74c: aload 27
   // 74e: iload 7
   // 750: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 753: aload 27
   // 755: iload 8
   // 757: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 75a: aload 27
   // 75c: bipush 7
   // 75e: putfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.label I
   // 761: invokevirtual io/ktor/client/statement/HttpStatement.cleanup (Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 764: dup
   // 765: aload 28
   // 767: if_acmpne 7cd
   // 76a: aload 28
   // 76c: areturn
   // 76d: aload 27
   // 76f: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$2 I
   // 772: istore 8
   // 774: aload 27
   // 776: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$1 I
   // 779: istore 7
   // 77b: aload 27
   // 77d: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.I$0 I
   // 780: istore 6
   // 782: aload 27
   // 784: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$6 Ljava/lang/Object;
   // 787: checkcast java/lang/Throwable
   // 78a: astore 10
   // 78c: aload 27
   // 78e: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$5 Ljava/lang/Object;
   // 791: checkcast io/ktor/client/statement/HttpResponse
   // 794: astore 9
   // 796: aload 27
   // 798: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$4 Ljava/lang/Object;
   // 79b: checkcast io/ktor/client/statement/HttpStatement
   // 79e: astore 5
   // 7a0: aload 27
   // 7a2: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$3 Ljava/lang/Object;
   // 7a5: checkcast io/ktor/client/statement/HttpStatement
   // 7a8: astore 4
   // 7aa: aload 27
   // 7ac: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$2 Ljava/lang/Object;
   // 7af: checkcast kotlin/jvm/functions/Function2
   // 7b2: astore 2
   // 7b3: aload 27
   // 7b5: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$1 Ljava/lang/Object;
   // 7b8: checkcast kotlin/jvm/functions/Function1
   // 7bb: astore 1
   // 7bc: aload 27
   // 7be: getfield io/ktor/client/plugins/websocket/BuildersKt$webSocket$1.L$0 Ljava/lang/Object;
   // 7c1: checkcast io/ktor/client/HttpClient
   // 7c4: astore 0
   // 7c5: nop
   // 7c6: aload 26
   // 7c8: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 7cb: aload 26
   // 7cd: pop
   // 7ce: aload 10
   // 7d0: athrow
   // 7d1: astore 23
   // 7d3: aload 23
   // 7d5: checkcast java/lang/Throwable
   // 7d8: invokestatic io/ktor/client/utils/ExceptionUtilsJvmKt.unwrapCancellationException (Ljava/lang/Throwable;)Ljava/lang/Throwable;
   // 7db: athrow
   // 7dc: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 7df: areturn
   // 7e0: new java/lang/IllegalStateException
   // 7e3: dup
   // 7e4: ldc_w "call to 'resume' before 'invoke' with coroutine"
   // 7e7: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 7ea: athrow
}

public suspend fun HttpClient.webSocket(
   method: HttpMethod = ...,
   host: String? = ...,
   port: Int? = ...,
   path: String? = ...,
   request: (HttpRequestBuilder) -> Unit = ...,
   block: (DefaultClientWebSocketSession, Continuation<Unit>) -> Any?
) {
   val var10000: Any = webSocket(`$this$webSocket`, BuildersKt::webSocket$lambda$3, block, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `webSocket$default`(
   var0: HttpClient,
   var1: HttpMethod,
   var2: java.lang.String,
   var3: Int,
   var4: java.lang.String,
   var5: Function1,
   var6: Function2,
   var7: Continuation,
   var8: Int,
   var9: Any
): Any {
   if ((var8 and 1) != 0) {
      var1 = HttpMethod.Companion.getGet();
   }

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
      var5 = BuildersKt::webSocket$lambda$2;
   }

   return webSocket(var0, var1, var2, var3, var4, var5, var6, var7);
}

public suspend fun HttpClient.webSocket(
   urlString: String,
   request: (HttpRequestBuilder) -> Unit = ...,
   block: (DefaultClientWebSocketSession, Continuation<Unit>) -> Any?
) {
   val var10000: Any = webSocket(`$this$webSocket`, HttpMethod.Companion.getGet(), null, null, null, BuildersKt::webSocket$lambda$5, block, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `webSocket$default`(var0: HttpClient, var1: java.lang.String, var2: Function1, var3: Function2, var4: Continuation, var5: Int, var6: Any): Any {
   if ((var5 and 2) != 0) {
      var2 = BuildersKt::webSocket$lambda$4;
   }

   return webSocket(var0, var1, var2, var3, var4);
}

public suspend fun HttpClient.ws(
   method: HttpMethod = ...,
   host: String? = ...,
   port: Int? = ...,
   path: String? = ...,
   request: (HttpRequestBuilder) -> Unit = ...,
   block: (DefaultClientWebSocketSession, Continuation<Unit>) -> Any?
) {
   val var10000: Any = webSocket(`$this$ws`, method, host, port, path, request, block, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `ws$default`(
   var0: HttpClient,
   var1: HttpMethod,
   var2: java.lang.String,
   var3: Int,
   var4: java.lang.String,
   var5: Function1,
   var6: Function2,
   var7: Continuation,
   var8: Int,
   var9: Any
): Any {
   if ((var8 and 1) != 0) {
      var1 = HttpMethod.Companion.getGet();
   }

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
      var5 = BuildersKt::ws$lambda$0;
   }

   return ws(var0, var1, var2, var3, var4, var5, var6, var7);
}

public suspend fun HttpClient.ws(request: (HttpRequestBuilder) -> Unit, block: (DefaultClientWebSocketSession, Continuation<Unit>) -> Any?) {
   val var10000: Any = webSocket(`$this$ws`, request, block, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun HttpClient.ws(
   urlString: String,
   request: (HttpRequestBuilder) -> Unit = ...,
   block: (DefaultClientWebSocketSession, Continuation<Unit>) -> Any?
) {
   val var10000: Any = webSocket(`$this$ws`, urlString, request, block, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `ws$default`(var0: HttpClient, var1: java.lang.String, var2: Function1, var3: Function2, var4: Continuation, var5: Int, var6: Any): Any {
   if ((var5 and 2) != 0) {
      var2 = BuildersKt::ws$lambda$1;
   }

   return ws(var0, var1, var2, var3, var4);
}

public suspend fun HttpClient.wss(request: (HttpRequestBuilder) -> Unit, block: (DefaultClientWebSocketSession, Continuation<Unit>) -> Any?) {
   val var10000: Any = webSocket(`$this$wss`, BuildersKt::wss$lambda$0, block, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun HttpClient.wss(
   urlString: String,
   request: (HttpRequestBuilder) -> Unit = ...,
   block: (DefaultClientWebSocketSession, Continuation<Unit>) -> Any?
) {
   val var10000: Any = wss(`$this$wss`, BuildersKt::wss$lambda$2, block, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `wss$default`(var0: HttpClient, var1: java.lang.String, var2: Function1, var3: Function2, var4: Continuation, var5: Int, var6: Any): Any {
   if ((var5 and 2) != 0) {
      var2 = BuildersKt::wss$lambda$1;
   }

   return wss(var0, var1, var2, var3, var4);
}

public suspend fun HttpClient.wss(
   method: HttpMethod = ...,
   host: String? = ...,
   port: Int? = ...,
   path: String? = ...,
   request: (HttpRequestBuilder) -> Unit = ...,
   block: (DefaultClientWebSocketSession, Continuation<Unit>) -> Any?
) {
   val var10000: Any = webSocket(`$this$wss`, method, host, port, path, BuildersKt::wss$lambda$4, block, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `wss$default`(
   var0: HttpClient,
   var1: HttpMethod,
   var2: java.lang.String,
   var3: Int,
   var4: java.lang.String,
   var5: Function1,
   var6: Function2,
   var7: Continuation,
   var8: Int,
   var9: Any
): Any {
   if ((var8 and 1) != 0) {
      var1 = HttpMethod.Companion.getGet();
   }

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
      var5 = BuildersKt::wss$lambda$3;
   }

   return wss(var0, var1, var2, var3, var4, var5, var6, var7);
}

fun `WebSockets$lambda$0`(`$config`: Function1, `$this$install`: WebSockets.Config): Unit {
   `$config`.invoke(`$this$install`);
   return Unit.INSTANCE;
}

fun URLBuilder.`webSocketSession$lambda$0$0`(it: URLBuilder): Unit {
   `$this$url`.setProtocol(URLProtocol.Companion.getWS());
   `$this$url`.setPort(`$this$url`.getProtocol().getDefaultPort());
   return Unit.INSTANCE;
}

fun `webSocketSession$lambda$1`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `webSocketSession$lambda$2`(
   `$method`: HttpMethod, `$host`: java.lang.String, `$port`: Int, `$path`: java.lang.String, `$block`: Function1, `$this$webSocketSession`: HttpRequestBuilder
): Unit {
   `$this$webSocketSession`.setMethod(`$method`);
   HttpRequestKt.url$default(`$this$webSocketSession`, "ws", `$host`, `$port`, `$path`, null, 16, null);
   `$block`.invoke(`$this$webSocketSession`);
   return Unit.INSTANCE;
}

fun `webSocketSession$lambda$3`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `webSocketSession$lambda$4`(`$urlString`: java.lang.String, `$block`: Function1, `$this$webSocketSession`: HttpRequestBuilder): Unit {
   URLParserKt.takeFrom(`$this$webSocketSession`.getUrl(), `$urlString`);
   `$block`.invoke(`$this$webSocketSession`);
   return Unit.INSTANCE;
}

fun URLBuilder.`webSocket$lambda$0$0`(it: URLBuilder): Unit {
   `$this$url`.setProtocol(URLProtocol.Companion.getWS());
   return Unit.INSTANCE;
}

fun `webSocket$lambda$2`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `webSocket$lambda$3`(
   `$method`: HttpMethod, `$host`: java.lang.String, `$port`: Int, `$path`: java.lang.String, `$request`: Function1, `$this$webSocket`: HttpRequestBuilder
): Unit {
   `$this$webSocket`.setMethod(`$method`);
   HttpRequestKt.url$default(`$this$webSocket`, "ws", `$host`, `$port`, `$path`, null, 16, null);
   `$request`.invoke(`$this$webSocket`);
   return Unit.INSTANCE;
}

fun `webSocket$lambda$4`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `webSocket$lambda$5`(`$urlString`: java.lang.String, `$request`: Function1, `$this$webSocket`: HttpRequestBuilder): Unit {
   `$this$webSocket`.getUrl().setProtocol(URLProtocol.Companion.getWS());
   `$this$webSocket`.getUrl().setPort(UtilsKt.getPort(`$this$webSocket`));
   URLParserKt.takeFrom(`$this$webSocket`.getUrl(), `$urlString`);
   `$request`.invoke(`$this$webSocket`);
   return Unit.INSTANCE;
}

fun `ws$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `ws$lambda$1`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `wss$lambda$0`(`$request`: Function1, `$this$webSocket`: HttpRequestBuilder): Unit {
   `$this$webSocket`.getUrl().setProtocol(URLProtocol.Companion.getWSS());
   `$this$webSocket`.getUrl().setPort(`$this$webSocket`.getUrl().getProtocol().getDefaultPort());
   `$request`.invoke(`$this$webSocket`);
   return Unit.INSTANCE;
}

fun `wss$lambda$1`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `wss$lambda$2`(`$urlString`: java.lang.String, `$request`: Function1, `$this$wss`: HttpRequestBuilder): Unit {
   URLParserKt.takeFrom(`$this$wss`.getUrl(), `$urlString`);
   `$request`.invoke(`$this$wss`);
   return Unit.INSTANCE;
}

fun `wss$lambda$3`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `wss$lambda$4`(`$port`: Int, `$request`: Function1, `$this$webSocket`: HttpRequestBuilder): Unit {
   `$this$webSocket`.getUrl().setProtocol(URLProtocol.Companion.getWSS());
   if (`$port` != null) {
      `$this$webSocket`.getUrl().setPort(`$port`);
   }

   `$request`.invoke(`$this$webSocket`);
   return Unit.INSTANCE;
}
