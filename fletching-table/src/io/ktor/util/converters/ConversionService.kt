package io.ktor.util.converters

import io.ktor.util.reflect.TypeInfo

public interface ConversionService {
   public abstract fun fromValues(values: List<String>, type: TypeInfo): Any? {
   }

   public abstract fun toValues(value: Any?): List<String> {
   }
}
