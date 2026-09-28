package io.ktor.client.request.forms

import io.ktor.http.Headers

public data class FormPart<T>(key: String, value: Any, headers: Headers = Headers.Companion.getEmpty()) {
   public final val key: String
   public final val value: Any
   public final val headers: Headers

   init {
      this.key = key;
      this.value = (T)value;
      this.headers = headers;
   }

   public operator fun component1(): String {
      return this.key;
   }

   public operator fun component2(): Any {
      return this.value;
   }

   public operator fun component3(): Headers {
      return this.headers;
   }

   public fun copy(key: String = this.key, value: Any = this.value, headers: Headers = this.headers): FormPart<Any> {
      return new FormPart<>(key, (T)value, headers);
   }

   public override fun toString(): String {
      return "FormPart(key=${this.key}, value=${this.value}, headers=${this.headers})";
   }

   public override fun hashCode(): Int {
      return (this.key.hashCode() * 31 + this.value.hashCode()) * 31 + this.headers.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is FormPart) {
         return false;
      } else {
         val var2: FormPart = other as FormPart;
         if (!(this.key == (other as FormPart).key)) {
            return false;
         } else if (!(this.value == var2.value)) {
            return false;
         } else {
            return this.headers == var2.headers;
         }
      }
   }
}
