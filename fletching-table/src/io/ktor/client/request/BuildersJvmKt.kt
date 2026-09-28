@file:SourceDebugExtension(["SMAP\nbuildersJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 buildersJvm.kt\nio/ktor/client/request/BuildersJvmKt\n+ 2 builders.kt\nio/ktor/client/request/BuildersKt\n*L\n1#1,240:1\n85#2:241\n43#2:242\n359#2:243\n205#2,2:244\n43#2:246\n369#2,6:247\n43#2:253\n385#2,6:254\n43#2:260\n433#2,6:261\n43#2:267\n417#2,6:268\n43#2:274\n449#2,6:275\n43#2:281\n401#2,6:282\n43#2:288\n93#2:289\n52#2:290\n463#2:291\n287#2,2:292\n52#2:294\n471#2,3:295\n52#2:298\n482#2,3:299\n52#2:302\n515#2,3:303\n52#2:306\n504#2,3:307\n52#2:310\n526#2,3:311\n52#2:314\n493#2,3:315\n52#2:318\n*S KotlinDebug\n*F\n+ 1 buildersJvm.kt\nio/ktor/client/request/BuildersJvmKt\n*L\n22#1:241\n22#1:242\n38#1:243\n38#1:244,2\n38#1:246\n52#1:247,6\n52#1:253\n66#1:254,6\n66#1:260\n80#1:261,6\n80#1:267\n94#1:268,6\n94#1:274\n108#1:275,6\n108#1:281\n122#1:282,6\n122#1:288\n136#1:289\n136#1:290\n152#1:291\n152#1:292,2\n152#1:294\n166#1:295,3\n166#1:298\n180#1:299,3\n180#1:302\n194#1:303,3\n194#1:306\n208#1:307,3\n208#1:310\n222#1:311,3\n222#1:314\n236#1:315,3\n236#1:318\n*E\n"])

package io.ktor.client.request

import io.ktor.client.HttpClient
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpStatement
import io.ktor.http.HttpMethod
import io.ktor.http.URLUtilsJvmKt
import java.net.URL
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension

public suspend fun HttpClient.request(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   URLUtilsJvmKt.takeFrom(`builder$iv$iv`.getUrl(), url);
   block.invoke(`builder$iv$iv`);
   return new HttpStatement(`builder$iv$iv`, `$this$request`).execute(`$completion`);
}

@JvmSynthetic
fun `request$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::request$lambda$0;
   }

   return request(var0, var1, var2, var3);
}

public suspend fun HttpClient.get(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   URLUtilsJvmKt.takeFrom(`builder$iv$iv`.getUrl(), url);
   block.invoke(`builder$iv$iv`);
   `builder$iv$iv`.setMethod(HttpMethod.Companion.getGet());
   return new HttpStatement(`builder$iv$iv`, `$this$get`).execute(`$completion`);
}

@JvmSynthetic
fun `get$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::get$lambda$0;
   }

   return get(var0, var1, var2, var3);
}

public suspend fun HttpClient.post(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPost());
   URLUtilsJvmKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getPost());
   return new HttpStatement(`builder$iv`, `$this$post`).execute(`$completion`);
}

@JvmSynthetic
fun `post$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::post$lambda$0;
   }

   return post(var0, var1, var2, var3);
}

public suspend fun HttpClient.put(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPut());
   URLUtilsJvmKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getPut());
   return new HttpStatement(`builder$iv`, `$this$put`).execute(`$completion`);
}

@JvmSynthetic
fun `put$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::put$lambda$0;
   }

   return put(var0, var1, var2, var3);
}

public suspend fun HttpClient.patch(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPatch());
   URLUtilsJvmKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getPatch());
   return new HttpStatement(`builder$iv`, `$this$patch`).execute(`$completion`);
}

@JvmSynthetic
fun `patch$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::patch$lambda$0;
   }

   return patch(var0, var1, var2, var3);
}

public suspend fun HttpClient.options(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getOptions());
   URLUtilsJvmKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getOptions());
   return new HttpStatement(`builder$iv`, `$this$options`).execute(`$completion`);
}

@JvmSynthetic
fun `options$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::options$lambda$0;
   }

   return options(var0, var1, var2, var3);
}

public suspend fun HttpClient.head(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getHead());
   URLUtilsJvmKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getHead());
   return new HttpStatement(`builder$iv`, `$this$head`).execute(`$completion`);
}

@JvmSynthetic
fun `head$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::head$lambda$0;
   }

   return head(var0, var1, var2, var3);
}

public suspend fun HttpClient.delete(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getDelete());
   URLUtilsJvmKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getDelete());
   return new HttpStatement(`builder$iv`, `$this$delete`).execute(`$completion`);
}

@JvmSynthetic
fun `delete$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::delete$lambda$0;
   }

   return delete(var0, var1, var2, var3);
}

public suspend fun HttpClient.prepareRequest(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   URLUtilsJvmKt.takeFrom(`builder$iv$iv`.getUrl(), url);
   block.invoke(`builder$iv$iv`);
   return new HttpStatement(`builder$iv$iv`, `$this$prepareRequest`);
}

@JvmSynthetic
fun `prepareRequest$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::prepareRequest$lambda$0;
   }

   return prepareRequest(var0, var1, var2, var3);
}

public suspend fun HttpClient.prepareGet(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   URLUtilsJvmKt.takeFrom(`builder$iv$iv`.getUrl(), url);
   block.invoke(`builder$iv$iv`);
   `builder$iv$iv`.setMethod(HttpMethod.Companion.getGet());
   return new HttpStatement(`builder$iv$iv`, `$this$prepareGet`);
}

@JvmSynthetic
fun `prepareGet$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::prepareGet$lambda$0;
   }

   return prepareGet(var0, var1, var2, var3);
}

public suspend fun HttpClient.preparePost(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPost());
   URLUtilsJvmKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$preparePost`);
}

@JvmSynthetic
fun `preparePost$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::preparePost$lambda$0;
   }

   return preparePost(var0, var1, var2, var3);
}

public suspend fun HttpClient.preparePut(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPut());
   URLUtilsJvmKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$preparePut`);
}

@JvmSynthetic
fun `preparePut$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::preparePut$lambda$0;
   }

   return preparePut(var0, var1, var2, var3);
}

public suspend fun HttpClient.preparePatch(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPatch());
   URLUtilsJvmKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$preparePatch`);
}

@JvmSynthetic
fun `preparePatch$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::preparePatch$lambda$0;
   }

   return preparePatch(var0, var1, var2, var3);
}

public suspend fun HttpClient.prepareOptions(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getOptions());
   URLUtilsJvmKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$prepareOptions`);
}

@JvmSynthetic
fun `prepareOptions$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::prepareOptions$lambda$0;
   }

   return prepareOptions(var0, var1, var2, var3);
}

public suspend fun HttpClient.prepareHead(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getHead());
   URLUtilsJvmKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$prepareHead`);
}

@JvmSynthetic
fun `prepareHead$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::prepareHead$lambda$0;
   }

   return prepareHead(var0, var1, var2, var3);
}

public suspend fun HttpClient.prepareDelete(url: URL, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getDelete());
   URLUtilsJvmKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$prepareDelete`);
}

@JvmSynthetic
fun `prepareDelete$default`(var0: HttpClient, var1: URL, var2: Function1, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = BuildersJvmKt::prepareDelete$lambda$0;
   }

   return prepareDelete(var0, var1, var2, var3);
}

fun `request$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `get$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `post$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `put$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `patch$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `options$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `head$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `delete$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `prepareRequest$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `prepareGet$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `preparePost$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `preparePut$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `preparePatch$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `prepareOptions$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `prepareHead$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}

fun `prepareDelete$lambda$0`(var0: HttpRequestBuilder): Unit {
   return Unit.INSTANCE;
}
