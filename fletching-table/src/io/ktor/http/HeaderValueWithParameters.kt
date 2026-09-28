package io.ktor.http

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nHeaderValueWithParameters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeaderValueWithParameters.kt\nio/ktor/http/HeaderValueWithParameters\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 HeaderValueWithParameters.kt\nio/ktor/http/HeaderValueWithParametersKt\n*L\n1#1,165:1\n1#2:166\n97#3,5:167\n*S KotlinDebug\n*F\n+ 1 HeaderValueWithParameters.kt\nio/ktor/http/HeaderValueWithParameters\n*L\n57#1:167,5\n*E\n"])
public abstract class HeaderValueWithParameters {
   protected final val content: String
   public final val parameters: List<HeaderValueParam>

   open fun HeaderValueWithParameters(content: java.lang.String, parameters: MutableList<HeaderValueParam>) {
      this.content = content;
      this.parameters = parameters;
   }

   public fun parameter(name: String): String? {
      var index: Int = 0;
      val var3: Int = CollectionsKt.getLastIndex(this.parameters);
      if (0 <= var3) {
         while (true) {
            val parameter: HeaderValueParam = this.parameters.get(index);
            if (StringsKt.equals(parameter.getName(), name, true)) {
               return parameter.getValue();
            }

            if (index == var3) {
               break;
            }

            index++;
         }
      }

      return null;
   }

   public override fun toString(): String {
      val var10000: java.lang.String;
      if (this.parameters.isEmpty()) {
         var10000 = this.content;
      } else {
         val var21: Int = this.content.length();
         val var3: java.lang.Iterable = this.parameters;
         var `$this$toString_u24lambda_u241`: Int = 0;

         for (Object index : var3) {
            `$this$toString_u24lambda_u241` += (index as HeaderValueParam).getName().length() + (index as HeaderValueParam).getValue().length() + 3;
         }

         val var15: StringBuilder = new StringBuilder(var21 + `$this$toString_u24lambda_u241`);
         val var16: StringBuilder = var15;
         var15.append(this.content);
         var var18: Int = 0;
         val var19: Int = CollectionsKt.getLastIndex(this.parameters);
         if (0 <= var19) {
            while (true) {
               val var20: HeaderValueParam = this.parameters.get(var18);
               var16.append("; ");
               var16.append(var20.getName());
               var16.append("=");
               val `$this$escapeIfNeededTo$iv`: java.lang.String = var20.getValue();
               if (HeaderValueWithParametersKt.access$needQuotes(`$this$escapeIfNeededTo$iv`)) {
                  var16.append(HeaderValueWithParametersKt.quote(`$this$escapeIfNeededTo$iv`));
               } else {
                  var16.append(`$this$escapeIfNeededTo$iv`);
               }

               if (var18 == var19) {
                  break;
               }

               var18++;
            }
         }

         val var1: java.lang.String = var15.toString();
         var10000 = var1;
      }

      return var10000;
   }

   public companion object {
      public inline fun <R> parse(value: String, init: (String, List<HeaderValueParam>) -> Any): Any {
         val headerValue: HeaderValue = CollectionsKt.last(HttpHeaderValueParserKt.parseHeaderValue(value));
         return (R)init.invoke(headerValue.getValue(), headerValue.getParams());
      }
   }
}
