@file:SourceDebugExtension(["SMAP\nCookieUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CookieUtils.kt\nio/ktor/http/CookieUtilsKt\n+ 2 CookieUtils.kt\nio/ktor/http/StringLexer\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,349:1\n106#1,2:352\n106#1,2:355\n106#1,2:359\n106#1,2:362\n106#1,2:366\n106#1,2:371\n106#1,2:377\n115#1,3:380\n118#1:385\n106#1,2:386\n119#1,2:388\n122#1:391\n106#1,2:392\n124#1,2:394\n106#1,2:396\n126#1,4:398\n106#1,2:402\n131#1,2:404\n106#1,2:406\n133#1,9:408\n168#1,3:417\n171#1:422\n106#1,2:423\n172#1,2:425\n175#1,6:428\n149#1,12:434\n188#1,3:446\n191#1:451\n106#1,2:453\n192#1,2:455\n195#1,6:458\n56#2,2:350\n58#2:354\n56#2,2:357\n58#2:361\n56#2,2:364\n58#2:368\n56#2,2:369\n58#2:373\n56#2,2:374\n58#2:379\n56#2,2:383\n58#2:390\n56#2,2:420\n58#2:427\n56#2,2:449\n58#2:457\n1#3:376\n1#3:452\n*S KotlinDebug\n*F\n+ 1 CookieUtils.kt\nio/ktor/http/CookieUtilsKt\n*L\n118#1:352,2\n122#1:355,2\n125#1:359,2\n129#1:362,2\n132#1:366,2\n171#1:371,2\n191#1:377,2\n209#1:380,3\n209#1:385\n209#1:386,2\n209#1:388,2\n209#1:391\n209#1:392,2\n209#1:394,2\n209#1:396,2\n209#1:398,4\n209#1:402,2\n209#1:404,2\n209#1:406,2\n209#1:408,9\n220#1:417,3\n220#1:422\n220#1:423,2\n220#1:425,2\n220#1:428,6\n229#1:434,12\n238#1:446,3\n238#1:451\n238#1:453,2\n238#1:455,2\n238#1:458,6\n117#1:350,2\n117#1:354\n124#1:357,2\n124#1:361\n131#1:364,2\n131#1:368\n170#1:369,2\n170#1:373\n190#1:374,2\n190#1:379\n209#1:383,2\n209#1:390\n220#1:420,2\n220#1:427\n238#1:449,2\n238#1:457\n238#1:452\n*E\n"])

package io.ktor.http

import io.ktor.http.CookieUtilsKt.tryParseDayOfMonth.2
import io.ktor.http.CookieUtilsKt.tryParseTime.5
import io.ktor.http.CookieUtilsKt.tryParseTime.6
import io.ktor.http.CookieUtilsKt.tryParseTime.hour.1.1
import io.ktor.http.CookieUtilsKt.tryParseTime.hour.1.3
import io.ktor.util.date.Month
import kotlin.jvm.internal.SourceDebugExtension

internal fun Char.isDelimiter(): Boolean {
   return `$this$isDelimiter` == '\t'
      || ' ' <= `$this$isDelimiter` && `$this$isDelimiter` < '0'
      || ';' <= `$this$isDelimiter` && `$this$isDelimiter` < 'A'
      || '[' <= `$this$isDelimiter` && `$this$isDelimiter` < 'a'
      || '{' <= `$this$isDelimiter` && `$this$isDelimiter` < 127;
}

internal fun Char.isNonDelimiter(): Boolean {
   return 0 <= `$this$isNonDelimiter` && `$this$isNonDelimiter` < '\t'
      || '\n' <= `$this$isNonDelimiter` && `$this$isNonDelimiter` < ' '
      || '0' <= `$this$isNonDelimiter` && `$this$isNonDelimiter` < ':'
      || `$this$isNonDelimiter` == ':'
      || 'a' <= `$this$isNonDelimiter` && `$this$isNonDelimiter` < '{'
      || 'A' <= `$this$isNonDelimiter` && `$this$isNonDelimiter` < '['
      || 127 <= `$this$isNonDelimiter` && `$this$isNonDelimiter` < 256;
}

internal fun Char.isOctet(): Boolean {
   return 0 <= `$this$isOctet` && `$this$isOctet` < 256;
}

internal fun Char.isNonDigit(): Boolean {
   return 0 <= `$this$isNonDigit` && `$this$isNonDigit` < '0' || 'J' <= `$this$isNonDigit` && `$this$isNonDigit` < 256;
}

internal fun Char.isDigit(): Boolean {
   return '0' <= `$this$isDigit` && `$this$isDigit` < ':';
}

internal inline fun Boolean.otherwise(block: () -> Unit) {
   if (!`$this$otherwise`) {
      block.invoke();
   }
}

internal inline fun String.tryParseTime(success: (Int, Int, Int) -> Unit) {
   val lexer: StringLexer = new StringLexer(`$this$tryParseTime`);
   val `this_$iv`: Int = lexer.getIndex();
   if (lexer.accept(1.INSTANCE)) {
      lexer.accept(3.INSTANCE);
      var var10000: java.lang.String = lexer.getSource().substring(`this_$iv`, lexer.getIndex());
      val hour: Int = Integer.parseInt(var10000);
      if (lexer.accept(io.ktor.http.CookieUtilsKt.tryParseTime.1.INSTANCE)) {
         val `start$ivx`: Int = lexer.getIndex();
         if (lexer.accept(io.ktor.http.CookieUtilsKt.tryParseTime.minute.1.1.INSTANCE)) {
            lexer.accept(io.ktor.http.CookieUtilsKt.tryParseTime.minute.1.3.INSTANCE);
            var10000 = lexer.getSource().substring(`start$ivx`, lexer.getIndex());
            val var15: Int = Integer.parseInt(var10000);
            if (lexer.accept(io.ktor.http.CookieUtilsKt.tryParseTime.3.INSTANCE)) {
               val `start$ivxx`: Int = lexer.getIndex();
               if (lexer.accept(io.ktor.http.CookieUtilsKt.tryParseTime.second.1.1.INSTANCE)) {
                  lexer.accept(io.ktor.http.CookieUtilsKt.tryParseTime.second.1.3.INSTANCE);
                  var10000 = lexer.getSource().substring(`start$ivxx`, lexer.getIndex());
                  val var18: Int = Integer.parseInt(var10000);
                  if (lexer.accept(5.INSTANCE)) {
                     lexer.acceptWhile(6.INSTANCE);
                  }

                  success.invoke(hour, var15, var18);
               }
            }
         }
      }
   }
}

internal inline fun String.tryParseMonth(success: (Month) -> Unit) {
   if (`$this$tryParseMonth`.length() >= 3) {
      for (Month month : Month.getEntries()) {
         if (StringsKt.startsWith(`$this$tryParseMonth`, month.getValue(), true)) {
            success.invoke(month);
            return;
         }
      }
   }
}

internal inline fun String.tryParseDayOfMonth(success: (Int) -> Unit) {
   val lexer: StringLexer = new StringLexer(`$this$tryParseDayOfMonth`);
   val `start$iv`: Int = lexer.getIndex();
   if (lexer.accept(io.ktor.http.CookieUtilsKt.tryParseDayOfMonth.day.1.1.INSTANCE)) {
      lexer.accept(io.ktor.http.CookieUtilsKt.tryParseDayOfMonth.day.1.3.INSTANCE);
      val var10000: java.lang.String = lexer.getSource().substring(`start$iv`, lexer.getIndex());
      val day: Int = Integer.parseInt(var10000);
      if (lexer.accept(io.ktor.http.CookieUtilsKt.tryParseDayOfMonth.1.INSTANCE)) {
         lexer.acceptWhile(2.INSTANCE);
      }

      success.invoke(day);
   }
}

internal inline fun String.tryParseYear(success: (Int) -> Unit) {
   val lexer: StringLexer = new StringLexer(`$this$tryParseYear`);
   val `start$iv`: Int = lexer.getIndex();
   val `$this$tryParseYear_u24lambda_u240`: StringLexer = lexer;
   var var10: Byte = 2;

   for (int var11 = 0; var11 < var10; var11++) {
      if (!`$this$tryParseYear_u24lambda_u240`.accept(io.ktor.http.CookieUtilsKt.tryParseYear.year.1.1.1.INSTANCE)) {
         return;
      }
   }

   var10 = 2;

   for (int var18 = 0; var18 < var10; var18++) {
      `$this$tryParseYear_u24lambda_u240`.accept(io.ktor.http.CookieUtilsKt.tryParseYear.year.1.2.1.INSTANCE);
   }

   val var10000: java.lang.String = lexer.getSource().substring(`start$iv`, lexer.getIndex());
   val year: Int = Integer.parseInt(var10000);
   if (lexer.accept(io.ktor.http.CookieUtilsKt.tryParseYear.1.INSTANCE)) {
      lexer.acceptWhile(io.ktor.http.CookieUtilsKt.tryParseYear.2.INSTANCE);
   }

   success.invoke(year);
}

internal fun CookieDateBuilder.handleToken(token: String) {
   if (`$this$handleToken`.getHours() == null || `$this$handleToken`.getMinutes() == null || `$this$handleToken`.getSeconds() == null) {
      val `lexer$iv`: StringLexer = new StringLexer(token);
      val `start$iv$iv`: Int = `lexer$iv`.getIndex();
      if (`lexer$iv`.accept(1.INSTANCE)) {
         `lexer$iv`.accept(3.INSTANCE);
         var var10000: java.lang.String = `lexer$iv`.getSource().substring(`start$iv$iv`, `lexer$iv`.getIndex());
         val var13: Int = Integer.parseInt(var10000);
         if (`lexer$iv`.accept(io.ktor.http.CookieUtilsKt.tryParseTime.1.INSTANCE)) {
            val `start$iv$ivx`: Int = `lexer$iv`.getIndex();
            if (`lexer$iv`.accept(io.ktor.http.CookieUtilsKt.tryParseTime.minute.1.1.INSTANCE)) {
               `lexer$iv`.accept(io.ktor.http.CookieUtilsKt.tryParseTime.minute.1.3.INSTANCE);
               var10000 = `lexer$iv`.getSource().substring(`start$iv$ivx`, `lexer$iv`.getIndex());
               val var26: Int = Integer.parseInt(var10000);
               if (`lexer$iv`.accept(io.ktor.http.CookieUtilsKt.tryParseTime.3.INSTANCE)) {
                  val `start$iv$ivxx`: Int = `lexer$iv`.getIndex();
                  if (`lexer$iv`.accept(io.ktor.http.CookieUtilsKt.tryParseTime.second.1.1.INSTANCE)) {
                     `lexer$iv`.accept(io.ktor.http.CookieUtilsKt.tryParseTime.second.1.3.INSTANCE);
                     var10000 = `lexer$iv`.getSource().substring(`start$iv$ivxx`, `lexer$iv`.getIndex());
                     val var32: Int = Integer.parseInt(var10000);
                     if (`lexer$iv`.accept(5.INSTANCE)) {
                        `lexer$iv`.acceptWhile(6.INSTANCE);
                     }

                     `$this$handleToken`.setHours(var13);
                     `$this$handleToken`.setMinutes(var26);
                     `$this$handleToken`.setSeconds(var32);
                     return;
                  }
               }
            }
         }
      }
   }

   if (`$this$handleToken`.getDayOfMonth() == null) {
      val var23: StringLexer = new StringLexer(token);
      val var36: Int = var23.getIndex();
      if (var23.accept(io.ktor.http.CookieUtilsKt.tryParseDayOfMonth.day.1.1.INSTANCE)) {
         var23.accept(io.ktor.http.CookieUtilsKt.tryParseDayOfMonth.day.1.3.INSTANCE);
         val var67: java.lang.String = var23.getSource().substring(var36, var23.getIndex());
         val var59: Int = Integer.parseInt(var67);
         if (var23.accept(io.ktor.http.CookieUtilsKt.tryParseDayOfMonth.1.INSTANCE)) {
            var23.acceptWhile(2.INSTANCE);
         }

         `$this$handleToken`.setDayOfMonth(var59);
         return;
      }
   }

   if (`$this$handleToken`.getMonth() == null) {
      val `$this$tryParseYear$iv`: java.lang.String = token;
      if (token.length() >= 3) {
         for (Month month$iv : Month.getEntries()) {
            if (StringsKt.startsWith(`$this$tryParseYear$iv`, var27.getValue(), true)) {
               `$this$handleToken`.setMonth(var27);
               return;
            }
         }
      }
   }

   if (`$this$handleToken`.getYear() == null) {
      val var25: StringLexer = new StringLexer(token);
      val var38: Int = var25.getIndex();
      val var41: StringLexer = var25;
      var var47: Byte = 2;
      var var52: Int = 0;

      while (true) {
         if (var52 >= var47) {
            var47 = 2;

            for (int var53 = 0; var53 < var47; var53++) {
               var41.accept(io.ktor.http.CookieUtilsKt.tryParseYear.year.1.2.1.INSTANCE);
            }

            val var66: java.lang.String = var25.getSource().substring(var38, var25.getIndex());
            val `year$iv`: Int = Integer.parseInt(var66);
            if (var25.accept(io.ktor.http.CookieUtilsKt.tryParseYear.1.INSTANCE)) {
               var25.acceptWhile(io.ktor.http.CookieUtilsKt.tryParseYear.2.INSTANCE);
            }

            `$this$handleToken`.setYear(`year$iv`);
            return;
         }

         if (!var41.accept(io.ktor.http.CookieUtilsKt.tryParseYear.year.1.1.1.INSTANCE)) {
            break;
         }

         var52++;
      }
   }
}
