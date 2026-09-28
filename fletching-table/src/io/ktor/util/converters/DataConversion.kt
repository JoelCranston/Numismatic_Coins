package io.ktor.util.converters

import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.KtorDsl
import java.util.LinkedHashMap
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.TypeIntrinsics
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KType

@SourceDebugExtension(["SMAP\nDataConversion.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DataConversion.kt\nio/ktor/util/converters/DataConversion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,137:1\n1#2:138\n*E\n"])
public class DataConversion(configuration: io.ktor.util.converters.DataConversion.Configuration) : ConversionService {
   private final val converters: Map<KClass<*>, ConversionService>

   init {
      this.converters = MapsKt.toMap(configuration.getConverters$ktor_utils());
   }

   public override fun fromValues(values: List<String>, type: TypeInfo): Any? {
      if (values.isEmpty()) {
         return null;
      } else {
         var var10000: ConversionService = this.converters.get(type.getType());
         if (var10000 == null) {
            var10000 = DefaultConversionService.INSTANCE;
         }

         return var10000.fromValues(values, type);
      }
   }

   public override fun toValues(value: Any?): List<String> {
      if (value != null) {
         var var10000: ConversionService = this.converters.get(value.getClass()::class);
         if (var10000 == null) {
            var10000 = DefaultConversionService.INSTANCE;
         }

         return var10000.toValues(value);
      } else {
         return CollectionsKt.emptyList();
      }
   }

   @KtorDsl
   public class Configuration {
      internal final val converters: MutableMap<KClass<*>, ConversionService> = (new LinkedHashMap()) as java.util.Map

      public fun convert(type: KClass<*>, convertor: ConversionService) {
         this.converters.put(type, convertor);
      }

      public fun <T : Any> convert(type: KType, configure: (DelegatingConversionService.Configuration<Any>) -> Unit) {
         val var10000: KClassifier = type.getClassifier();
         val klass: KClass = var10000 as KClass;
         val service: DelegatingConversionService.Configuration = new DelegatingConversionService.Configuration(var10000 as KClass);
         configure.invoke(service);
         this.convert(
            klass,
            new DelegatingConversionService(
               klass,
               service.getDecoder$ktor_utils(),
               TypeIntrinsics.beforeCheckcastToFunctionOfArity(service.getEncoder$ktor_utils(), 1) as (Any?) -> MutableList<java.lang.String>
            )
         );
      }
   }
}
