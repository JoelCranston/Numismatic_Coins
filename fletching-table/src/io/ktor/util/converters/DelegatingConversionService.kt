package io.ktor.util.converters

import io.ktor.util.reflect.TypeInfo
import kotlin.reflect.KClass

public class DelegatingConversionService(klass: KClass<*>, decoder: ((List<String>) -> Any?)?, encoder: ((Any?) -> List<String>)?) : ConversionService {
   private final val klass: KClass<*>
   private final val decoder: ((List<String>) -> Any?)?
   private final val encoder: ((Any?) -> List<String>)?

   init {
      this.klass = klass;
      this.decoder = decoder;
      this.encoder = encoder;
   }

   public override fun fromValues(values: List<String>, type: TypeInfo): Any? {
      if (this.decoder == null) {
         throw new IllegalStateException("Decoder was not specified for type '${this.klass}'");
      } else {
         return this.decoder.invoke(values);
      }
   }

   public override fun toValues(value: Any?): List<String> {
      if (this.encoder == null) {
         throw new IllegalStateException("Encoder was not specified for type '${this.klass}'");
      } else {
         return this.encoder.invoke(value);
      }
   }

   public class Configuration<T> @PublishedApi  internal constructor(klass: KClass<Any>) {
      internal final val klass: KClass<Any>
      internal final var decoder: ((List<String>) -> Any)?
      internal final var encoder: ((Any) -> List<String>)?

      init {
         this.klass = klass;
      }

      public fun decode(converter: (List<String>) -> Any) {
         if (this.decoder != null) {
            throw new IllegalStateException("Decoder has already been set for type '${this.klass}'");
         } else {
            this.decoder = converter;
         }
      }

      public fun encode(converter: (Any) -> List<String>) {
         if (this.encoder != null) {
            throw new IllegalStateException("Encoder has already been set for type '${this.klass}'");
         } else {
            this.encoder = converter;
         }
      }
   }
}
