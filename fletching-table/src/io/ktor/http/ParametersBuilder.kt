package io.ktor.http

import io.ktor.util.StringValuesBuilder

public interface ParametersBuilder : StringValuesBuilder {
   public abstract fun build(): Parameters {
   }
}
