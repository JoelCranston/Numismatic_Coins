package io.ktor.http

import io.ktor.util.StringValuesBuilderImpl

public class HeadersBuilder(size: Int = 8) : StringValuesBuilderImpl(true, size) {
   public open fun build(): Headers {
      return new HeadersImpl(this.getValues());
   }

   protected override fun validateName(name: String) {
      super.validateName(name);
      HttpHeaders.INSTANCE.checkHeaderName(name);
   }

   protected override fun validateValue(value: String) {
      super.validateValue(value);
      HttpHeaders.INSTANCE.checkHeaderValue(value);
   }

   fun HeadersBuilder() {
      this(0, 1, null);
   }
}
