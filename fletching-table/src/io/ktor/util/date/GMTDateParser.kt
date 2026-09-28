package io.ktor.util.date

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nGMTDateParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GMTDateParser.kt\nio/ktor/util/date/GMTDateParser\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,134:1\n1#2:135\n1069#3,2:136\n*S KotlinDebug\n*F\n+ 1 GMTDateParser.kt\nio/ktor/util/date/GMTDateParser\n*L\n93#1:136,2\n*E\n"])
public class GMTDateParser(pattern: String) {
   private final val pattern: String

   init {
      this.pattern = pattern;
      if (this.pattern.length() <= 0) {
         throw new IllegalStateException("Date parser pattern shouldn't be empty.".toString());
      }
   }

   public fun parse(dateString: String): GMTDate {
      val builder: GMTDateBuilder = new GMTDateBuilder();
      var start: Int = 0;
      var current: Char = this.pattern.charAt(0);
      var chunkStart: Int = 0;
      var index: Int = 1;

      try {
         while (index < this.pattern.length()) {
            if (this.pattern.charAt(index) == current) {
               index++;
            } else {
               val var7: Int = chunkStart + index - start;
               val var10003: java.lang.String = dateString.substring(chunkStart, chunkStart + index - start);
               this.handleToken(builder, current, var10003);
               chunkStart = var7;
               start = index;
               current = this.pattern.charAt(index);
               index++;
            }
         }

         if (chunkStart < dateString.length()) {
            val var9: java.lang.String = dateString.substring(chunkStart);
            this.handleToken(builder, current, var9);
         }
      } catch (var8: java.lang.Throwable) {
         throw new InvalidDateStringException(dateString, chunkStart, this.pattern);
      }

      return builder.build();
   }

   private fun GMTDateBuilder.handleToken(type: Char, chunk: String) {
      switch (type) {
         case '*':
            break;
         case 'M':
            `$this$handleToken`.setMonth(Month.Companion.from(chunk));
            break;
         case 'Y':
            `$this$handleToken`.setYear(Integer.parseInt(chunk));
            break;
         case 'd':
            `$this$handleToken`.setDayOfMonth(Integer.parseInt(chunk));
            break;
         case 'h':
            `$this$handleToken`.setHours(Integer.parseInt(chunk));
            break;
         case 'm':
            `$this$handleToken`.setMinutes(Integer.parseInt(chunk));
            break;
         case 's':
            `$this$handleToken`.setSeconds(Integer.parseInt(chunk));
            break;
         case 'z':
            if (!(chunk == "GMT")) {
               throw new IllegalStateException("Check failed.");
            }
            break;
         default:
            val `$this$all$iv`: java.lang.CharSequence = chunk;

            var var10000: Boolean;
            label37: {
               for (int var6 = 0; var6 < $this$all$iv.length(); var6++) {
                  if (`$this$all$iv`.charAt(var6) != type) {
                     var10000 = false;
                     break label37;
                  }
               }

               var10000 = true;
            }

            if (!var10000) {
               throw new IllegalStateException("Check failed.");
            }
      }
   }

   public companion object {
      public const val SECONDS: Char
      public const val MINUTES: Char
      public const val HOURS: Char
      public const val DAY_OF_MONTH: Char
      public const val MONTH: Char
      public const val YEAR: Char
      public const val ZONE: Char
      public const val ANY: Char
   }
}
