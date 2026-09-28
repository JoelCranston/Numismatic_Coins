package io.ktor.http

import io.ktor.util.StringValuesBuilderImpl

public class ParametersBuilderImpl(size: Int = 8) : StringValuesBuilderImpl(true, size), ParametersBuilder {
   public override fun build(): Parameters {
      return new ParametersImpl(this.getValues());
   }

   fun ParametersBuilderImpl() {
      this(0, 1, null);
   }
}
