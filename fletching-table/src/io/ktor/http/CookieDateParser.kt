package io.ktor.http

import io.ktor.util.date.GMTDate
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nCookieUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CookieUtils.kt\nio/ktor/http/CookieDateParser\n+ 2 CookieUtils.kt\nio/ktor/http/StringLexer\n*L\n1#1,349:1\n56#2,3:350\n*S KotlinDebug\n*F\n+ 1 CookieUtils.kt\nio/ktor/http/CookieDateParser\n*L\n294#1:350,3\n*E\n"])
internal class CookieDateParser {
   private fun <T> checkFieldNotNull(source: String, name: String, field: Any?) {
      if (field == null) {
         throw new InvalidCookieDateException(source, "Could not find $name");
      }
   }

   private fun checkRequirement(source: String, requirement: Boolean, msg: () -> String) {
      if (!requirement) {
         throw new InvalidCookieDateException(source, msg.invoke() as java.lang.String);
      }
   }

   public fun parse(source: String): GMTDate {
      val lexer: StringLexer = new StringLexer(source);
      val builder: CookieDateBuilder = new CookieDateBuilder();
      lexer.acceptWhile(CookieDateParser::parse$lambda$0);

      while (lexer.getHasRemaining()) {
         if (lexer.test(CookieDateParser::parse$lambda$1)) {
            val `start$iv`: Int = lexer.getIndex();
            lexer.acceptWhile(CookieDateParser::parse$lambda$2$0);
            val var10000: java.lang.String = lexer.getSource().substring(`start$iv`, lexer.getIndex());
            CookieUtilsKt.handleToken(builder, var10000);
            lexer.acceptWhile(CookieDateParser::parse$lambda$3);
         }
      }

      val var10: Int = builder.getYear();
      if (var10 != null && new IntRange(70, 99).contains(var10.intValue())) {
         val var10001: Int = builder.getYear();
         builder.setYear(var10001 + 1900);
      } else if (var10 != null && new IntRange(0, 69).contains(var10.intValue())) {
         val var14: Int = builder.getYear();
         builder.setYear(var14 + 2000);
      }

      this.checkFieldNotNull(source, "day-of-month", builder.getDayOfMonth());
      this.checkFieldNotNull(source, "month", builder.getMonth());
      this.checkFieldNotNull(source, "year", builder.getYear());
      this.checkFieldNotNull(source, "time", builder.getHours());
      this.checkFieldNotNull(source, "time", builder.getMinutes());
      this.checkFieldNotNull(source, "time", builder.getSeconds());
      val var11: IntRange = new IntRange(1, 31);
      val var13: Int = builder.getDayOfMonth();
      this.checkRequirement(source, var13 != null && var11.contains(var13.intValue()), CookieDateParser::parse$lambda$4);
      var var10002: Int = builder.getYear();
      this.checkRequirement(source, var10002 >= 1601, CookieDateParser::parse$lambda$5);
      var10002 = builder.getHours();
      this.checkRequirement(source, var10002 <= 23, CookieDateParser::parse$lambda$6);
      var10002 = builder.getMinutes();
      this.checkRequirement(source, var10002 <= 59, CookieDateParser::parse$lambda$7);
      var10002 = builder.getSeconds();
      this.checkRequirement(source, var10002 <= 59, CookieDateParser::parse$lambda$8);
      return builder.build();
   }

   @JvmStatic
   fun `parse$lambda$0`(it: Char): Boolean {
      return CookieUtilsKt.isDelimiter(it);
   }

   @JvmStatic
   fun `parse$lambda$1`(it: Char): Boolean {
      return CookieUtilsKt.isNonDelimiter(it);
   }

   @JvmStatic
   fun `parse$lambda$2$0`(it: Char): Boolean {
      return CookieUtilsKt.isNonDelimiter(it);
   }

   @JvmStatic
   fun `parse$lambda$3`(it: Char): Boolean {
      return CookieUtilsKt.isDelimiter(it);
   }

   @JvmStatic
   fun `parse$lambda$4`(): java.lang.String {
      return "day-of-month not in [1,31]";
   }

   @JvmStatic
   fun `parse$lambda$5`(): java.lang.String {
      return "year >= 1601";
   }

   @JvmStatic
   fun `parse$lambda$6`(): java.lang.String {
      return "hours > 23";
   }

   @JvmStatic
   fun `parse$lambda$7`(): java.lang.String {
      return "minutes > 59";
   }

   @JvmStatic
   fun `parse$lambda$8`(): java.lang.String {
      return "seconds > 59";
   }
}
