package io.ktor.http.parsing

internal final val lowAlpha: Grammar
   internal final get() {
      return ParserDslKt.to('a', 'z');
   }


internal final val alpha: Grammar
   internal final get() {
      return ParserDslKt.or(ParserDslKt.to('a', 'z'), ParserDslKt.to('A', 'Z'));
   }


internal final val digit: RawGrammar
   internal final get() {
      return new RawGrammar("\\d");
   }


internal final val hex: Grammar
   internal final get() {
      return ParserDslKt.or(ParserDslKt.or(getDigit(), ParserDslKt.to('A', 'F')), ParserDslKt.to('a', 'f'));
   }


internal final val alphaDigit: Grammar
   internal final get() {
      return ParserDslKt.or(getAlpha(), getDigit());
   }


internal final val alphas: Grammar
   internal final get() {
      return ParserDslKt.atLeastOne(getAlpha());
   }


internal final val digits: Grammar
   internal final get() {
      return ParserDslKt.atLeastOne(getDigit());
   }

