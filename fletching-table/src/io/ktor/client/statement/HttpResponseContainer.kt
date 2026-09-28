package io.ktor.client.statement

import io.ktor.util.reflect.TypeInfo

public data class HttpResponseContainer(expectedType: TypeInfo, response: Any) {
   public final val expectedType: TypeInfo
   public final val response: Any

   init {
      this.expectedType = expectedType;
      this.response = response;
   }

   public operator fun component1(): TypeInfo {
      return this.expectedType;
   }

   public operator fun component2(): Any {
      return this.response;
   }

   public fun copy(expectedType: TypeInfo = this.expectedType, response: Any = this.response): HttpResponseContainer {
      return new HttpResponseContainer(expectedType, response);
   }

   public override fun toString(): String {
      return "HttpResponseContainer(expectedType=${this.expectedType}, response=${this.response})";
   }

   public override fun hashCode(): Int {
      return this.expectedType.hashCode() * 31 + this.response.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is HttpResponseContainer) {
         return false;
      } else {
         val var2: HttpResponseContainer = other as HttpResponseContainer;
         if (!(this.expectedType == (other as HttpResponseContainer).expectedType)) {
            return false;
         } else {
            return this.response == var2.response;
         }
      }
   }
}
