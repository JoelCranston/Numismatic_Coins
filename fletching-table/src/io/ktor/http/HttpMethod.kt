package io.ktor.http

public data class HttpMethod(value: String) {
   public final val value: String

   init {
      this.value = value;
   }

   public override fun toString(): String {
      return this.value;
   }

   public operator fun component1(): String {
      return this.value;
   }

   public fun copy(value: String = this.value): HttpMethod {
      return new HttpMethod(value);
   }

   public override fun hashCode(): Int {
      return this.value.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is HttpMethod) {
         return false;
      } else {
         return this.value == (other as HttpMethod).value;
      }
   }

   public companion object {
      public final val Get: HttpMethod
      public final val Post: HttpMethod
      public final val Put: HttpMethod
      public final val Patch: HttpMethod
      public final val Delete: HttpMethod
      public final val Head: HttpMethod
      public final val Options: HttpMethod
      public final val DefaultMethods: List<HttpMethod>

      public fun parse(method: String): HttpMethod {
         return if (method == this.getGet().getValue())
            this.getGet()
            else
            (
               if (method == this.getPost().getValue())
                  this.getPost()
                  else
                  (
                     if (method == this.getPut().getValue())
                        this.getPut()
                        else
                        (
                           if (method == this.getPatch().getValue())
                              this.getPatch()
                              else
                              (
                                 if (method == this.getDelete().getValue())
                                    this.getDelete()
                                    else
                                    (
                                       if (method == this.getHead().getValue())
                                          this.getHead()
                                          else
                                          (if (method == this.getOptions().getValue()) this.getOptions() else new HttpMethod(method))
                                    )
                              )
                        )
                  )
            );
      }
   }
}
