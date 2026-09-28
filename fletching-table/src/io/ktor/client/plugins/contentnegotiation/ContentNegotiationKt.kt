@file:SourceDebugExtension(["SMAP\nContentNegotiation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ContentNegotiation.kt\nio/ktor/client/plugins/contentnegotiation/ContentNegotiationKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Attributes.kt\nio/ktor/util/AttributesKt\n+ 5 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,317:1\n774#2:318\n865#2:319\n2746#2,3:320\n866#2:323\n1869#2:324\n2746#2,3:325\n1870#2:328\n1761#2,3:329\n774#2:332\n865#2,2:333\n774#2:336\n865#2,2:337\n1563#2:339\n1634#2,3:340\n1#3:335\n21#4:343\n69#5:344\n84#5,8:345\n*S KotlinDebug\n*F\n+ 1 ContentNegotiation.kt\nio/ktor/client/plugins/contentnegotiation/ContentNegotiationKt\n*L\n181#1:318\n181#1:319\n181#1:320,3\n181#1:323\n187#1:324\n188#1:325,3\n187#1:328\n200#1:329,3\n218#1:332\n218#1:333,2\n273#1:336\n273#1:337,2\n274#1:339\n274#1:340,3\n40#1:343\n40#1:344\n40#1:345,8\n*E\n"])

package io.ktor.client.plugins.contentnegotiation

import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.ClientPluginBuilder
import io.ktor.client.plugins.contentnegotiation.ContentNegotiationKt.ContentNegotiation.2.1
import io.ktor.client.plugins.contentnegotiation.ContentNegotiationKt.ContentNegotiation.2.2
import io.ktor.client.plugins.sse.ClientSSESession
import io.ktor.client.plugins.sse.ClientSSESessionWithDeserialization
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.UtilsKt
import io.ktor.client.utils.EmptyContent
import io.ktor.http.ContentType
import io.ktor.http.ContentTypesKt
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMessagePropertiesKt
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.content.NullBody
import io.ktor.http.content.OutgoingContent
import io.ktor.serialization.ContentConverter
import io.ktor.serialization.ContentConverterKt
import io.ktor.util.AttributeKey
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteReadChannel
import java.nio.charset.Charset
import java.util.ArrayList
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import org.slf4j.Logger

private final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.contentnegotiation.ContentNegotiation")
internal final val DefaultCommonIgnoredTypes: Set<KClass<*>> =
   SetsKt.setOf(
      new KClass[]{
         ByteArray::class,
         java.lang.String::class,
         HttpStatusCode::class,
         ByteReadChannel::class,
         OutgoingContent::class,
         ClientSSESession::class,
         ClientSSESessionWithDeserialization::class
      }
   )
   internal final val ExcludedContentTypes: AttributeKey<List<ContentType>>
public final val ContentNegotiation: ClientPlugin<ContentNegotiationConfig>

public fun HttpRequestBuilder.exclude(vararg contentType: ContentType) {
   var var10000: java.util.List = `$this$exclude`.getAttributes().getOrNull(ExcludedContentTypes);
   if (var10000 == null) {
      var10000 = CollectionsKt.emptyList();
   }

   `$this$exclude`.getAttributes().put(ExcludedContentTypes, CollectionsKt.plus(var10000, contentType));
}

fun ClientPluginBuilder.`ContentNegotiation$lambda$0`(): Unit {
   val registrations: java.util.List = (`$this$createClientPlugin`.getPluginConfig() as ContentNegotiationConfig)
      .getRegistrations$ktor_client_content_negotiation();
   val ignoredTypes: java.util.Set = (`$this$createClientPlugin`.getPluginConfig() as ContentNegotiationConfig)
      .getIgnoredTypes$ktor_client_content_negotiation();
   `$this$createClientPlugin`.transformRequestBody(new 1(registrations, ignoredTypes, `$this$createClientPlugin`, null));
   `$this$createClientPlugin`.transformResponseBody(new 2(ignoredTypes, registrations, `$this$createClientPlugin`, null));
   return Unit.INSTANCE;
}

// $VF: Irreducible bytecode was duplicated to produce valid code
fun `ContentNegotiation$lambda$0$convertRequest`(
   registrations: MutableList<ContentNegotiationConfig.ConverterRegistration>,
   ignoredTypes: MutableSet<KClass<?>>,
   `$this_createClientPlugin`: ClientPluginBuilder<ContentNegotiationConfig>,
   request: HttpRequestBuilder,
   body: Any,
   `$completion`: Continuation<? super OutgoingContent>
): Any {
   var `$continuation`: Continuation;
   label207: {
      if (`$completion` is io.ktor.client.plugins.contentnegotiation.ContentNegotiationKt.ContentNegotiation.2.convertRequest.1) {
         `$continuation` = `$completion` as io.ktor.client.plugins.contentnegotiation.ContentNegotiationKt.ContentNegotiation.2.convertRequest.1;
         if ((
               (`$completion` as io.ktor.client.plugins.contentnegotiation.ContentNegotiationKt.ContentNegotiation.2.convertRequest.1).label and Integer.MIN_VALUE
            )
            != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label207;
         }
      }

      `$continuation` = new io.ktor.client.plugins.contentnegotiation.ContentNegotiationKt.ContentNegotiation.2.convertRequest.1(`$completion`);
   }

   label210: {
      var contentType: ContentType;
      var matchingRegistrations: java.util.List;
      var var10000: OutgoingContent;
      label200: {
         val `$result`: Any = `$continuation`.result;
         val var27: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var requestRegistrations: java.util.List;
         var acceptHeaders: java.util.List;
         var var13: java.util.Iterator;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               val var74: java.util.List;
               if (!request.getAttributes().contains(ExcludedContentTypes)) {
                  var74 = registrations;
               } else {
                  acceptHeaders = request.getAttributes().get(ExcludedContentTypes);
                  val var29: java.lang.Iterable = registrations;
                  val `$this$filter$iv`: java.util.Collection = new ArrayList();

                  for (Object element$iv$iv : $this$filter$iv) {
                     label182: {
                        val var55: ContentNegotiationConfig.ConverterRegistration = var50 as ContentNegotiationConfig.ConverterRegistration;
                        val it: java.lang.Iterable = acceptHeaders;
                        val var19: java.util.Iterator;
                        if (acceptHeaders is java.util.Collection) {
                           if ((it as java.util.Collection).isEmpty()) {
                              var73 = true;
                              break label182;
                           }

                           var19 = it.iterator();
                        } else {
                           var19 = it.iterator();
                        }

                        while (true) {
                           if (!var19.hasNext()) {
                              var73 = true;
                              break;
                           }

                           if (var55.getContentTypeToSend().match(var19.next() as ContentType)) {
                              var73 = false;
                              break;
                           }
                        }
                     }

                     if (var73) {
                        `$this$filter$iv`.add(var50);
                     }
                  }

                  var74 = `$this$filter$iv` as java.util.List;
               }

               requestRegistrations = var74;
               var var75: java.util.List = request.getHeaders().getAll(HttpHeaders.INSTANCE.getAccept());
               if (var75 == null) {
                  var75 = CollectionsKt.emptyList();
               }

               acceptHeaders = var75;

               for (Object element$iv : var30) {
                  val var41: ContentNegotiationConfig.ConverterRegistration = var37 as ContentNegotiationConfig.ConverterRegistration;
                  val var51: java.lang.Iterable = acceptHeaders;
                  var var76: Boolean;
                  if (acceptHeaders is java.util.Collection && (acceptHeaders as java.util.Collection).isEmpty()) {
                     var76 = true;
                  } else {
                     val var60: java.util.Iterator = var51.iterator();

                     while (true) {
                        if (!var60.hasNext()) {
                           var76 = true;
                           break;
                        }

                        if (ContentType.Companion.parse(var60.next() as java.lang.String).match(var41.getContentTypeToSend())) {
                           var76 = false;
                           break;
                        }
                     }
                  }

                  if (var76) {
                     val var57: java.lang.Double = (`$this_createClientPlugin`.getPluginConfig() as ContentNegotiationConfig).getDefaultAcceptHeaderQValue();
                     val var52: ContentType = if (var57 == null)
                        var41.getContentTypeToSend()
                        else
                        var41.getContentTypeToSend().withParameter("q", java.lang.String.valueOf(var57.doubleValue()));
                     LOGGER.trace("Adding Accept=$var52 header for ${request.getUrl()}");
                     UtilsKt.accept(request, var52);
                  }
               }

               if (body is OutgoingContent) {
                  break label210;
               }

               val var31: java.lang.Iterable = ignoredTypes;
               var var77: Boolean;
               if (ignoredTypes is java.util.Collection && (ignoredTypes as java.util.Collection).isEmpty()) {
                  var77 = false;
               } else {
                  val var35: java.util.Iterator = var31.iterator();

                  while (true) {
                     if (var35.hasNext()) {
                        if (!(var35.next() as KClass).isInstance(body)) {
                           continue;
                        }

                        var77 = true;
                        break;
                     }

                     var77 = false;
                     break;
                  }
               }

               if (var77) {
                  break label210;
               }

               val var78: ContentType = HttpMessagePropertiesKt.contentType(request);
               if (var78 == null) {
                  LOGGER.trace("Request doesn't have Content-Type header. Skipping ContentNegotiation for ${request.getUrl()}.");
                  return null;
               }

               contentType = var78;
               if (body is Unit) {
                  LOGGER.trace("Sending empty body for ${request.getUrl()}");
                  request.getHeaders().remove(HttpHeaders.INSTANCE.getContentType());
                  return EmptyContent.INSTANCE;
               }

               val var39: java.lang.Iterable = registrations;
               val var53: java.util.Collection = new ArrayList();

               for (Object element$iv$iv : $this$filter$iv) {
                  if ((var63 as ContentNegotiationConfig.ConverterRegistration).getContentTypeMatcher().contains(contentType)) {
                     var53.add(var63);
                  }
               }

               val var79: java.util.List = if (!(var53 as java.util.List).isEmpty()) var53 as java.util.List else null;
               if (var79 == null) {
                  LOGGER.trace(
                     "None of the registered converters match request Content-Type=$contentType. Skipping ContentNegotiation for ${request.getUrl()}."
                  );
                  return null;
               }

               matchingRegistrations = var79;
               if (request.getBodyType() == null) {
                  LOGGER.trace("Request has unknown body type. Skipping ContentNegotiation for ${request.getUrl()}.");
                  return null;
               }

               request.getHeaders().remove(HttpHeaders.INSTANCE.getContentType());
               var13 = var79.iterator();
               break;
            case 1:
               val var15: Int = `$continuation`.I$0;
               val registration: ContentNegotiationConfig.ConverterRegistration = `$continuation`.L$10 as ContentNegotiationConfig.ConverterRegistration;
               var13 = `$continuation`.L$9 as java.util.Iterator;
               matchingRegistrations = `$continuation`.L$8 as java.util.List;
               contentType = `$continuation`.L$7 as ContentType;
               acceptHeaders = `$continuation`.L$6 as java.util.List;
               requestRegistrations = `$continuation`.L$5 as java.util.List;
               body = `$continuation`.L$4;
               request = `$continuation`.L$3 as HttpRequestBuilder;
               `$this_createClientPlugin` = `$continuation`.L$2 as ClientPluginBuilder;
               ignoredTypes = `$continuation`.L$1 as java.util.Set;
               registrations = `$continuation`.L$0 as java.util.List;
               ResultKt.throwOnFailure(`$result`);
               val result: OutgoingContent = `$result` as OutgoingContent;
               if (`$result` as OutgoingContent != null) {
                  LOGGER.trace("Converted request body using ${registration.getConverter()} for ${request.getUrl()}");
               }

               if (result != null) {
                  var10000 = result;
                  break label200;
               }
               break;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         while (true) {
            if (!var13.hasNext()) {
               var10000 = null;
               break;
            }

            val registrationx: ContentNegotiationConfig.ConverterRegistration = var13.next() as ContentNegotiationConfig.ConverterRegistration;
            val var80: ContentConverter = registrationx.getConverter();
            var var10002: Charset = ContentTypesKt.charset(contentType);
            if (var10002 == null) {
               var10002 = Charsets.UTF_8;
            }

            val var10003: TypeInfo = request.getBodyType();
            val var10004: Any = if (!(body == NullBody.INSTANCE)) body else null;
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(registrations);
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(ignoredTypes);
            `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(`$this_createClientPlugin`);
            `$continuation`.L$3 = request;
            `$continuation`.L$4 = body;
            `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(requestRegistrations);
            `$continuation`.L$6 = SpillingKt.nullOutSpilledVariable(acceptHeaders);
            `$continuation`.L$7 = contentType;
            `$continuation`.L$8 = matchingRegistrations;
            `$continuation`.L$9 = var13;
            `$continuation`.L$10 = registrationx;
            `$continuation`.I$0 = 0;
            `$continuation`.label = 1;
            var10000 = (OutgoingContent)var80.serialize(contentType, var10002, var10003, var10004, `$continuation`);
            if (var10000 === var27) {
               return var27;
            }

            val var72: OutgoingContent = var10000;
            if (var10000 != null) {
               LOGGER.trace("Converted request body using ${registrationx.getConverter()} for ${request.getUrl()}");
            }

            if (var72 != null) {
               var10000 = var72;
               break;
            }
         }
      }

      if (var10000 == null) {
         throw new ContentConverterException(
            "Can't convert $body with contentType $contentType using converters ${CollectionsKt.joinToString$default(
               matchingRegistrations, null, null, null, 0, null, ContentNegotiationKt::ContentNegotiation$lambda$0$convertRequest$8, 31, null
            )}"
         );
      }

      return var10000;
   }

   LOGGER.trace("Body type ${body.getClass()::class} is in ignored types. Skipping ContentNegotiation for ${request.getUrl()}.");
   return null;
}

fun `ContentNegotiation$lambda$0$convertRequest$8`(it: ContentNegotiationConfig.ConverterRegistration): java.lang.CharSequence {
   return it.getConverter().toString();
}

fun `ContentNegotiation$lambda$0$convertResponse`(
   ignoredTypes: MutableSet<KClass<?>>,
   registrations: MutableList<ContentNegotiationConfig.ConverterRegistration>,
   `$this_createClientPlugin`: ClientPluginBuilder<ContentNegotiationConfig>,
   requestUrl: Url,
   info: TypeInfo,
   body: Any,
   responseContentType: ContentType,
   charset: Charset,
   `$completion`: Continuation<Object>
): Any {
   var `$continuation`: Continuation;
   label66: {
      if (`$completion` is io.ktor.client.plugins.contentnegotiation.ContentNegotiationKt.ContentNegotiation.2.convertResponse.1) {
         `$continuation` = `$completion` as io.ktor.client.plugins.contentnegotiation.ContentNegotiationKt.ContentNegotiation.2.convertResponse.1;
         if ((
               (`$completion` as io.ktor.client.plugins.contentnegotiation.ContentNegotiationKt.ContentNegotiation.2.convertResponse.1).label and Integer.MIN_VALUE
            )
            != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label66;
         }
      }

      `$continuation` = new io.ktor.client.plugins.contentnegotiation.ContentNegotiationKt.ContentNegotiation.2.convertResponse.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var23: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         if (body !is ByteReadChannel) {
            LOGGER.trace("Response body is already transformed. Skipping ContentNegotiation for $requestUrl.");
            return null;
         }

         if (ignoredTypes.contains(info.getType())) {
            LOGGER.trace("Response body type ${info.getType()} is in ignored types. Skipping ContentNegotiation for $requestUrl.");
            return null;
         }

         var `$this$map$iv`: java.lang.Iterable = registrations;
         var `destination$iv$iv`: java.util.Collection = new ArrayList();

         for (Object element$iv$iv : $this$map$iv) {
            if ((`item$iv$iv` as ContentNegotiationConfig.ConverterRegistration).getContentTypeMatcher().contains(responseContentType)) {
               `destination$iv$iv`.add(`item$iv$iv`);
            }
         }

         `$this$map$iv` = `destination$iv$iv` as java.util.List;
         `destination$iv$iv` = new ArrayList(CollectionsKt.collectionSizeOrDefault(`destination$iv$iv` as java.util.List, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$iv`.add((var39 as ContentNegotiationConfig.ConverterRegistration).getConverter());
         }

         var10000 = if (!(`destination$iv$iv` as java.util.List).isEmpty()) `destination$iv$iv` as java.util.List else null;
         if (var10000 == null) {
            LOGGER.trace(
               "None of the registered converters match response with Content-Type=$responseContentType. Skipping ContentNegotiation for $requestUrl."
            );
            return null;
         }

         val var10001: ByteReadChannel = body as ByteReadChannel;
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(ignoredTypes);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(registrations);
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(`$this_createClientPlugin`);
         `$continuation`.L$3 = requestUrl;
         `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(info);
         `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(body);
         `$continuation`.L$6 = SpillingKt.nullOutSpilledVariable(responseContentType);
         `$continuation`.L$7 = SpillingKt.nullOutSpilledVariable(charset);
         `$continuation`.L$8 = SpillingKt.nullOutSpilledVariable(var10000);
         `$continuation`.label = 1;
         var10000 = ContentConverterKt.deserialize((java.util.List<? extends ContentConverter>)var10000, var10001, info, charset, `$continuation`);
         if (var10000 === var23) {
            return var23;
         }
         break;
      case 1:
         val suitableConverters: java.util.List = `$continuation`.L$8 as java.util.List;
         charset = `$continuation`.L$7 as Charset;
         responseContentType = `$continuation`.L$6 as ContentType;
         body = `$continuation`.L$5;
         info = `$continuation`.L$4 as TypeInfo;
         requestUrl = `$continuation`.L$3 as Url;
         `$this_createClientPlugin` = `$continuation`.L$2 as ClientPluginBuilder;
         registrations = `$continuation`.L$1 as java.util.List;
         ignoredTypes = `$continuation`.L$0 as java.util.Set;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   if (var10000 !is ByteReadChannel) {
      LOGGER.trace("Response body was converted to ${var10000.getClass()::class} for $requestUrl.");
   }

   return var10000;
}

@JvmSynthetic
fun `ContentNegotiation$lambda$0$convertResponse$default`(
   var0: java.util.Set,
   var1: java.util.List,
   var2: ClientPluginBuilder,
   var3: Url,
   var4: TypeInfo,
   var5: Any,
   var6: ContentType,
   var7: Charset,
   var8: Continuation,
   var9: Int,
   var10: Any
): Any {
   if ((var9 and 128) != 0) {
      var7 = Charsets.UTF_8;
   }

   return ContentNegotiation$lambda$0$convertResponse(var0, var1, var2, var3, var4, var5, var6, var7, var8);
}

@JvmSynthetic
fun `access$ContentNegotiation$lambda$0$convertRequest`(
   registrations: java.util.List,
   ignoredTypes: java.util.Set,
   `$this_createClientPlugin`: ClientPluginBuilder,
   request: HttpRequestBuilder,
   body: Any,
   `$completion`: Continuation
): Any {
   return ContentNegotiation$lambda$0$convertRequest(registrations, ignoredTypes, `$this_createClientPlugin`, request, body, `$completion`);
}

@JvmSynthetic
fun `access$ContentNegotiation$lambda$0$convertResponse`(
   ignoredTypes: java.util.Set,
   registrations: java.util.List,
   `$this_createClientPlugin`: ClientPluginBuilder,
   requestUrl: Url,
   info: TypeInfo,
   body: Any,
   responseContentType: ContentType,
   charset: Charset,
   `$completion`: Continuation
): Any {
   return ContentNegotiation$lambda$0$convertResponse(
      ignoredTypes, registrations, `$this_createClientPlugin`, requestUrl, info, body, responseContentType, charset, `$completion`
   );
}
