package io.ktor.client.plugins.contentnegotiation

import io.ktor.client.plugins.contentnegotiation.ContentNegotiationConfig.defaultMatcher.1
import io.ktor.http.ContentType
import io.ktor.http.ContentTypeMatcher
import io.ktor.serialization.Configuration
import io.ktor.serialization.ContentConverter
import io.ktor.utils.io.KtorDsl
import java.util.ArrayList
import kotlin.reflect.KClass

@KtorDsl
public class ContentNegotiationConfig : Configuration {
   internal final val ignoredTypes: MutableSet<KClass<*>> =
      CollectionsKt.toMutableSet(SetsKt.plus(DefaultIgnoredTypesJvmKt.getDefaultIgnoredTypes(), ContentNegotiationKt.getDefaultCommonIgnoredTypes()))
      internal final val registrations: MutableList<io.ktor.client.plugins.contentnegotiation.ContentNegotiationConfig.ConverterRegistration> =
      (new ArrayList()) as java.util.List
      public final var defaultAcceptHeaderQValue: Double?

   public override fun <T : ContentConverter> register(contentType: ContentType, converter: Any, configuration: (Any) -> Unit) {
      this.register(
         contentType,
         converter,
         if (contentType.match(ContentType.Application.INSTANCE.getJson())) JsonContentTypeMatcher.INSTANCE else this.defaultMatcher(contentType),
         configuration
      );
   }

   public fun <T : ContentConverter> register(
      contentTypeToSend: ContentType,
      converter: Any,
      contentTypeMatcher: ContentTypeMatcher,
      configuration: (Any) -> Unit
   ) {
      configuration.invoke(converter);
      this.registrations.add(new ContentNegotiationConfig.ConverterRegistration(converter, contentTypeToSend, contentTypeMatcher));
   }

   public fun removeIgnoredType(type: KClass<*>) {
      this.ignoredTypes.remove(type);
   }

   public fun ignoreType(type: KClass<*>) {
      this.ignoredTypes.add(type);
   }

   public fun clearIgnoredTypes() {
      this.ignoredTypes.clear();
   }

   private fun defaultMatcher(pattern: ContentType): ContentTypeMatcher {
      return new 1(pattern);
   }

   internal class ConverterRegistration(converter: ContentConverter, contentTypeToSend: ContentType, contentTypeMatcher: ContentTypeMatcher) {
      public final val converter: ContentConverter
      public final val contentTypeToSend: ContentType
      public final val contentTypeMatcher: ContentTypeMatcher

      init {
         this.converter = converter;
         this.contentTypeToSend = contentTypeToSend;
         this.contentTypeMatcher = contentTypeMatcher;
      }
   }
}
