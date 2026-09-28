package io.ktor.http

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nHttpHeaderValueParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpHeaderValueParser.kt\nio/ktor/http/HeaderValue\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,243:1\n295#2,2:244\n1#3:246\n*S KotlinDebug\n*F\n+ 1 HttpHeaderValueParser.kt\nio/ktor/http/HeaderValue\n*L\n46#1:244,2\n*E\n"])
public data class HeaderValue(value: String, params: List<HeaderValueParam> = CollectionsKt.emptyList()) {
   public final val value: String
   public final val params: List<HeaderValueParam>
   public final val quality: Double

   init {
      this.value = value;
      this.params = params;
      val var6: java.util.Iterator = this.params.iterator();

      var var10000: Any;
      while (true) {
         if (var6.hasNext()) {
            val `element$iv`: Any = var6.next();
            if (!((`element$iv` as HeaderValueParam).getName() == "q")) {
               continue;
            }

            var10000 = (HeaderValue)`element$iv`;
            break;
         }

         var10000 = null;
         break;
      }

      var var18: Double;
      label34: {
         var10000 = this;
         val var3: HeaderValueParam = var10000 as HeaderValueParam;
         if (var10000 as HeaderValueParam != null) {
            val var13: java.lang.String = var3.getValue();
            if (var13 != null) {
               val var14: java.lang.Double = StringsKt.toDoubleOrNull(var13);
               if (var14 != null) {
                  val var16: Double = var14.doubleValue();
                  val var12: Boolean = 0.0 <= var16 && var16 <= 1.0;
                  var10000 = this;
                  val var15: java.lang.Double = if (var12) var14 else null;
                  if ((if (var12) var14 else null) != null) {
                     var18 = var15;
                     break label34;
                  }
               }
            }
         }

         var18 = 1.0;
      }

      var10000.quality = var18;
   }

   public operator fun component1(): String {
      return this.value;
   }

   public operator fun component2(): List<HeaderValueParam> {
      return this.params;
   }

   public fun copy(value: String = this.value, params: List<HeaderValueParam> = this.params): HeaderValue {
      return new HeaderValue(value, params);
   }

   public override fun toString(): String {
      return "HeaderValue(value=${this.value}, params=${this.params})";
   }

   public override fun hashCode(): Int {
      return this.value.hashCode() * 31 + this.params.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is HeaderValue) {
         return false;
      } else {
         val var2: HeaderValue = other as HeaderValue;
         if (!(this.value == (other as HeaderValue).value)) {
            return false;
         } else {
            return this.params == var2.params;
         }
      }
   }
}
