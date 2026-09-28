@file:SourceDebugExtension(["SMAP\nbuildersWithUrl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 buildersWithUrl.kt\nio/ktor/client/request/BuildersWithUrlKt\n+ 2 builders.kt\nio/ktor/client/request/BuildersKt\n*L\n1#1,229:1\n359#2:230\n205#2,2:231\n43#2:233\n205#2,2:234\n43#2:236\n205#2,2:237\n43#2:239\n463#2:240\n287#2,2:241\n52#2:243\n463#2:244\n287#2,2:245\n52#2:247\n463#2:248\n287#2,2:249\n52#2:251\n369#2,6:252\n43#2:258\n369#2,6:259\n43#2:265\n369#2,6:266\n43#2:272\n471#2,3:273\n52#2:276\n471#2,3:277\n52#2:280\n471#2,3:281\n52#2:284\n385#2,6:285\n43#2:291\n385#2,6:292\n43#2:298\n385#2,6:299\n43#2:305\n482#2,3:306\n52#2:309\n482#2,3:310\n52#2:313\n482#2,3:314\n52#2:317\n433#2,6:318\n43#2:324\n433#2,6:325\n43#2:331\n433#2,6:332\n43#2:338\n515#2,3:339\n52#2:342\n515#2,3:343\n52#2:346\n515#2,3:347\n52#2:350\n417#2,6:351\n43#2:357\n417#2,6:358\n43#2:364\n417#2,6:365\n43#2:371\n504#2,3:372\n52#2:375\n504#2,3:376\n52#2:379\n504#2,3:380\n52#2:383\n449#2,6:384\n43#2:390\n449#2,6:391\n43#2:397\n449#2,6:398\n43#2:404\n526#2,3:405\n52#2:408\n526#2,3:409\n52#2:412\n526#2,3:413\n52#2:416\n401#2,6:417\n43#2:423\n401#2,6:424\n43#2:430\n401#2,6:431\n43#2:437\n493#2,3:438\n52#2:441\n493#2,3:442\n52#2:445\n493#2,3:446\n52#2:449\n*S KotlinDebug\n*F\n+ 1 buildersWithUrl.kt\nio/ktor/client/request/BuildersWithUrlKt\n*L\n22#1:230\n22#1:231,2\n22#1:233\n22#1:234,2\n22#1:236\n22#1:237,2\n22#1:239\n36#1:240\n36#1:241,2\n36#1:243\n36#1:244\n36#1:245,2\n36#1:247\n36#1:248\n36#1:249,2\n36#1:251\n52#1:252,6\n52#1:258\n52#1:259,6\n52#1:265\n52#1:266,6\n52#1:272\n66#1:273,3\n66#1:276\n66#1:277,3\n66#1:280\n66#1:281,3\n66#1:284\n82#1:285,6\n82#1:291\n82#1:292,6\n82#1:298\n82#1:299,6\n82#1:305\n96#1:306,3\n96#1:309\n96#1:310,3\n96#1:313\n96#1:314,3\n96#1:317\n112#1:318,6\n112#1:324\n112#1:325,6\n112#1:331\n112#1:332,6\n112#1:338\n126#1:339,3\n126#1:342\n126#1:343,3\n126#1:346\n126#1:347,3\n126#1:350\n142#1:351,6\n142#1:357\n142#1:358,6\n142#1:364\n142#1:365,6\n142#1:371\n156#1:372,3\n156#1:375\n156#1:376,3\n156#1:379\n156#1:380,3\n156#1:383\n172#1:384,6\n172#1:390\n172#1:391,6\n172#1:397\n172#1:398,6\n172#1:404\n186#1:405,3\n186#1:408\n186#1:409,3\n186#1:412\n186#1:413,3\n186#1:416\n202#1:417,6\n202#1:423\n202#1:424,6\n202#1:430\n202#1:431,6\n202#1:437\n216#1:438,3\n216#1:441\n216#1:442,3\n216#1:445\n216#1:446,3\n216#1:449\n*E\n"])

package io.ktor.client.request

import io.ktor.client.HttpClient
import io.ktor.client.request.BuildersWithUrlKt.get.2
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpStatement
import io.ktor.http.HttpMethod
import io.ktor.http.URLUtilsKt
import io.ktor.http.Url
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension

public suspend inline fun HttpClient.get(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   URLUtilsKt.takeFrom(`builder$iv$iv`.getUrl(), url);
   block.invoke(`builder$iv$iv`);
   `builder$iv$iv`.setMethod(HttpMethod.Companion.getGet());
   return new HttpStatement(`builder$iv$iv`, `$this$get`).execute(`$completion`);
}

fun HttpClient.`get$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpResponse>): Any {
   val `builder$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   val `$this$get_u24lambda_u240`: HttpRequestBuilder = `builder$iv$iv`;
   URLUtilsKt.takeFrom(`builder$iv$iv`.getUrl(), url);
   block.invoke(`$this$get_u24lambda_u240`);
   val var15: HttpRequestBuilder = `builder$iv$iv`;
   `builder$iv$iv`.setMethod(HttpMethod.Companion.getGet());
   val var10000: HttpStatement = new HttpStatement(var15, `$this$get`);
   InlineMarker.mark(0);
   val var16: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var16;
}

@JvmSynthetic
fun HttpClient.`get$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$get`: Int, `$this$get$iv`: Any): Any {
   if ((`$i$f$get` and 2) != 0) {
      block = 2.INSTANCE;
   }

   val `builder$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   URLUtilsKt.takeFrom(`builder$iv$iv`.getUrl(), url);
   block.invoke(`builder$iv$iv`);
   `builder$iv$iv`.setMethod(HttpMethod.Companion.getGet());
   val var10000: HttpStatement = new HttpStatement(`builder$iv$iv`, `$this$get_u24default`);
   InlineMarker.mark(0);
   val var16: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var16;
}

public suspend inline fun HttpClient.prepareGet(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   URLUtilsKt.takeFrom(`builder$iv$iv`.getUrl(), url);
   block.invoke(`builder$iv$iv`);
   `builder$iv$iv`.setMethod(HttpMethod.Companion.getGet());
   return new HttpStatement(`builder$iv$iv`, `$this$prepareGet`);
}

fun HttpClient.`prepareGet$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpStatement>): Any {
   val `builder$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   val `$this$prepareGet_u24lambda_u240`: HttpRequestBuilder = `builder$iv$iv`;
   URLUtilsKt.takeFrom(`builder$iv$iv`.getUrl(), url);
   block.invoke(`$this$prepareGet_u24lambda_u240`);
   val var15: HttpRequestBuilder = `builder$iv$iv`;
   `builder$iv$iv`.setMethod(HttpMethod.Companion.getGet());
   return new HttpStatement(var15, `$this$prepareGet`);
}

@JvmSynthetic
fun HttpClient.`prepareGet$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$prepareGet`: Int, `$this$prepareGet$iv`: Any): Any {
   if ((`$i$f$prepareGet` and 2) != 0) {
      block = io.ktor.client.request.BuildersWithUrlKt.prepareGet.2.INSTANCE;
   }

   val `builder$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   URLUtilsKt.takeFrom(`builder$iv$iv`.getUrl(), url);
   block.invoke(`builder$iv$iv`);
   `builder$iv$iv`.setMethod(HttpMethod.Companion.getGet());
   return new HttpStatement(`builder$iv$iv`, `$this$prepareGet_u24default`);
}

public suspend inline fun HttpClient.post(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPost());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getPost());
   return new HttpStatement(`builder$iv`, `$this$post`).execute(`$completion`);
}

fun HttpClient.`post$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpResponse>): Any {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPost());
   val `$this$post_u24lambda_u240`: HttpRequestBuilder = `builder$iv`;
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`$this$post_u24lambda_u240`);
   `builder$iv`.setMethod(HttpMethod.Companion.getPost());
   val var10000: HttpStatement = new HttpStatement(`builder$iv`, `$this$post`);
   InlineMarker.mark(0);
   val var13: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var13;
}

@JvmSynthetic
fun HttpClient.`post$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$post`: Int, `$this$post$iv`: Any): Any {
   if ((`$i$f$post` and 2) != 0) {
      block = io.ktor.client.request.BuildersWithUrlKt.post.2.INSTANCE;
   }

   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPost());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getPost());
   val var10000: HttpStatement = new HttpStatement(`builder$iv`, `$this$post_u24default`);
   InlineMarker.mark(0);
   val var14: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var14;
}

public suspend inline fun HttpClient.preparePost(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPost());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$preparePost`);
}

fun HttpClient.`preparePost$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpStatement>): Any {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPost());
   val `$this$preparePost_u24lambda_u240`: HttpRequestBuilder = `builder$iv`;
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`$this$preparePost_u24lambda_u240`);
   return new HttpStatement(`builder$iv`, `$this$preparePost`);
}

@JvmSynthetic
fun HttpClient.`preparePost$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$preparePost`: Int, `$this$preparePost$iv`: Any): Any {
   if ((`$i$f$preparePost` and 2) != 0) {
      block = io.ktor.client.request.BuildersWithUrlKt.preparePost.2.INSTANCE;
   }

   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPost());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$preparePost_u24default`);
}

public suspend inline fun HttpClient.put(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPut());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getPut());
   return new HttpStatement(`builder$iv`, `$this$put`).execute(`$completion`);
}

fun HttpClient.`put$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpResponse>): Any {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPut());
   val `$this$put_u24lambda_u240`: HttpRequestBuilder = `builder$iv`;
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`$this$put_u24lambda_u240`);
   `builder$iv`.setMethod(HttpMethod.Companion.getPut());
   val var10000: HttpStatement = new HttpStatement(`builder$iv`, `$this$put`);
   InlineMarker.mark(0);
   val var13: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var13;
}

@JvmSynthetic
fun HttpClient.`put$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$put`: Int, `$this$put$iv`: Any): Any {
   if ((`$i$f$put` and 2) != 0) {
      block = io.ktor.client.request.BuildersWithUrlKt.put.2.INSTANCE;
   }

   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPut());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getPut());
   val var10000: HttpStatement = new HttpStatement(`builder$iv`, `$this$put_u24default`);
   InlineMarker.mark(0);
   val var14: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var14;
}

public suspend inline fun HttpClient.preparePut(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPut());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$preparePut`);
}

fun HttpClient.`preparePut$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpStatement>): Any {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPut());
   val `$this$preparePut_u24lambda_u240`: HttpRequestBuilder = `builder$iv`;
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`$this$preparePut_u24lambda_u240`);
   return new HttpStatement(`builder$iv`, `$this$preparePut`);
}

@JvmSynthetic
fun HttpClient.`preparePut$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$preparePut`: Int, `$this$preparePut$iv`: Any): Any {
   if ((`$i$f$preparePut` and 2) != 0) {
      block = io.ktor.client.request.BuildersWithUrlKt.preparePut.2.INSTANCE;
   }

   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPut());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$preparePut_u24default`);
}

public suspend inline fun HttpClient.patch(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPatch());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getPatch());
   return new HttpStatement(`builder$iv`, `$this$patch`).execute(`$completion`);
}

fun HttpClient.`patch$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpResponse>): Any {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPatch());
   val `$this$patch_u24lambda_u240`: HttpRequestBuilder = `builder$iv`;
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`$this$patch_u24lambda_u240`);
   `builder$iv`.setMethod(HttpMethod.Companion.getPatch());
   val var10000: HttpStatement = new HttpStatement(`builder$iv`, `$this$patch`);
   InlineMarker.mark(0);
   val var13: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var13;
}

@JvmSynthetic
fun HttpClient.`patch$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$patch`: Int, `$this$patch$iv`: Any): Any {
   if ((`$i$f$patch` and 2) != 0) {
      block = io.ktor.client.request.BuildersWithUrlKt.patch.2.INSTANCE;
   }

   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPatch());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getPatch());
   val var10000: HttpStatement = new HttpStatement(`builder$iv`, `$this$patch_u24default`);
   InlineMarker.mark(0);
   val var14: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var14;
}

public suspend inline fun HttpClient.preparePatch(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPatch());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$preparePatch`);
}

fun HttpClient.`preparePatch$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpStatement>): Any {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPatch());
   val `$this$preparePatch_u24lambda_u240`: HttpRequestBuilder = `builder$iv`;
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`$this$preparePatch_u24lambda_u240`);
   return new HttpStatement(`builder$iv`, `$this$preparePatch`);
}

@JvmSynthetic
fun HttpClient.`preparePatch$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$preparePatch`: Int, `$this$preparePatch$iv`: Any): Any {
   if ((`$i$f$preparePatch` and 2) != 0) {
      block = io.ktor.client.request.BuildersWithUrlKt.preparePatch.2.INSTANCE;
   }

   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getPatch());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$preparePatch_u24default`);
}

public suspend inline fun HttpClient.options(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getOptions());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getOptions());
   return new HttpStatement(`builder$iv`, `$this$options`).execute(`$completion`);
}

fun HttpClient.`options$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpResponse>): Any {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getOptions());
   val `$this$options_u24lambda_u240`: HttpRequestBuilder = `builder$iv`;
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`$this$options_u24lambda_u240`);
   `builder$iv`.setMethod(HttpMethod.Companion.getOptions());
   val var10000: HttpStatement = new HttpStatement(`builder$iv`, `$this$options`);
   InlineMarker.mark(0);
   val var13: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var13;
}

@JvmSynthetic
fun HttpClient.`options$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$options`: Int, `$this$options$iv`: Any): Any {
   if ((`$i$f$options` and 2) != 0) {
      block = io.ktor.client.request.BuildersWithUrlKt.options.2.INSTANCE;
   }

   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getOptions());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getOptions());
   val var10000: HttpStatement = new HttpStatement(`builder$iv`, `$this$options_u24default`);
   InlineMarker.mark(0);
   val var14: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var14;
}

public suspend inline fun HttpClient.prepareOptions(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getOptions());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$prepareOptions`);
}

fun HttpClient.`prepareOptions$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpStatement>): Any {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getOptions());
   val `$this$prepareOptions_u24lambda_u240`: HttpRequestBuilder = `builder$iv`;
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`$this$prepareOptions_u24lambda_u240`);
   return new HttpStatement(`builder$iv`, `$this$prepareOptions`);
}

@JvmSynthetic
fun HttpClient.`prepareOptions$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$prepareOptions`: Int, `$this$prepareOptions$iv`: Any): Any {
   if ((`$i$f$prepareOptions` and 2) != 0) {
      block = io.ktor.client.request.BuildersWithUrlKt.prepareOptions.2.INSTANCE;
   }

   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getOptions());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$prepareOptions_u24default`);
}

public suspend inline fun HttpClient.head(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getHead());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getHead());
   return new HttpStatement(`builder$iv`, `$this$head`).execute(`$completion`);
}

fun HttpClient.`head$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpResponse>): Any {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getHead());
   val `$this$head_u24lambda_u240`: HttpRequestBuilder = `builder$iv`;
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`$this$head_u24lambda_u240`);
   `builder$iv`.setMethod(HttpMethod.Companion.getHead());
   val var10000: HttpStatement = new HttpStatement(`builder$iv`, `$this$head`);
   InlineMarker.mark(0);
   val var13: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var13;
}

@JvmSynthetic
fun HttpClient.`head$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$head`: Int, `$this$head$iv`: Any): Any {
   if ((`$i$f$head` and 2) != 0) {
      block = io.ktor.client.request.BuildersWithUrlKt.head.2.INSTANCE;
   }

   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getHead());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getHead());
   val var10000: HttpStatement = new HttpStatement(`builder$iv`, `$this$head_u24default`);
   InlineMarker.mark(0);
   val var14: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var14;
}

public suspend inline fun HttpClient.prepareHead(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getHead());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$prepareHead`);
}

fun HttpClient.`prepareHead$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpStatement>): Any {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getHead());
   val `$this$prepareHead_u24lambda_u240`: HttpRequestBuilder = `builder$iv`;
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`$this$prepareHead_u24lambda_u240`);
   return new HttpStatement(`builder$iv`, `$this$prepareHead`);
}

@JvmSynthetic
fun HttpClient.`prepareHead$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$prepareHead`: Int, `$this$prepareHead$iv`: Any): Any {
   if ((`$i$f$prepareHead` and 2) != 0) {
      block = io.ktor.client.request.BuildersWithUrlKt.prepareHead.2.INSTANCE;
   }

   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getHead());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$prepareHead_u24default`);
}

public suspend inline fun HttpClient.delete(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpResponse {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getDelete());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getDelete());
   return new HttpStatement(`builder$iv`, `$this$delete`).execute(`$completion`);
}

fun HttpClient.`delete$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpResponse>): Any {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getDelete());
   val `$this$delete_u24lambda_u240`: HttpRequestBuilder = `builder$iv`;
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`$this$delete_u24lambda_u240`);
   `builder$iv`.setMethod(HttpMethod.Companion.getDelete());
   val var10000: HttpStatement = new HttpStatement(`builder$iv`, `$this$delete`);
   InlineMarker.mark(0);
   val var13: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var13;
}

@JvmSynthetic
fun HttpClient.`delete$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$delete`: Int, `$this$delete$iv`: Any): Any {
   if ((`$i$f$delete` and 2) != 0) {
      block = io.ktor.client.request.BuildersWithUrlKt.delete.2.INSTANCE;
   }

   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getDelete());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   `builder$iv`.setMethod(HttpMethod.Companion.getDelete());
   val var10000: HttpStatement = new HttpStatement(`builder$iv`, `$this$delete_u24default`);
   InlineMarker.mark(0);
   val var14: Any = var10000.execute(`$completion`);
   InlineMarker.mark(1);
   return var14;
}

public suspend inline fun HttpClient.prepareDelete(url: Url, block: (HttpRequestBuilder) -> Unit = ...): HttpStatement {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getDelete());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$prepareDelete`);
}

fun HttpClient.`prepareDelete$$forInline`(url: Url, block: (HttpRequestBuilder?) -> Unit, `$completion`: Continuation<? super HttpStatement>): Any {
   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getDelete());
   val `$this$prepareDelete_u24lambda_u240`: HttpRequestBuilder = `builder$iv`;
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`$this$prepareDelete_u24lambda_u240`);
   return new HttpStatement(`builder$iv`, `$this$prepareDelete`);
}

@JvmSynthetic
fun HttpClient.`prepareDelete$default`(url: Url, block: Function1, `$completion`: Continuation, `$i$f$prepareDelete`: Int, `$this$prepareDelete$iv`: Any): Any {
   if ((`$i$f$prepareDelete` and 2) != 0) {
      block = io.ktor.client.request.BuildersWithUrlKt.prepareDelete.2.INSTANCE;
   }

   val `builder$iv`: HttpRequestBuilder = new HttpRequestBuilder();
   `builder$iv`.setMethod(HttpMethod.Companion.getDelete());
   URLUtilsKt.takeFrom(`builder$iv`.getUrl(), url);
   block.invoke(`builder$iv`);
   return new HttpStatement(`builder$iv`, `$this$prepareDelete_u24default`);
}

public fun HttpRequestBuilder.url(url: Url) {
   URLUtilsKt.takeFrom(`$this$url`.getUrl(), url);
}
