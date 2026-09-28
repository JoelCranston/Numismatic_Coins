package io.ktor.http

import io.ktor.http.parsing.Grammar
import io.ktor.http.parsing.Parser
import io.ktor.http.parsing.ParserDslKt
import io.ktor.http.parsing.PrimitivesKt
import io.ktor.http.parsing.regex.RegexParserGeneratorKt

private final val IPv4address: Grammar =
   ParserDslKt.then(
      ParserDslKt.then(
         ParserDslKt.then(
            ParserDslKt.then(ParserDslKt.then(ParserDslKt.then(PrimitivesKt.getDigits(), "."), PrimitivesKt.getDigits()), "."), PrimitivesKt.getDigits()
         ),
         "."
      ),
      PrimitivesKt.getDigits()
   )
   private final val IPv6address: Grammar = ParserDslKt.then(ParserDslKt.then("[", ParserDslKt.atLeastOne(ParserDslKt.or(PrimitivesKt.getHex(), ":"))), "]")
private final val IP_PARSER: Parser = RegexParserGeneratorKt.buildRegexParser(ParserDslKt.or(IPv4address, IPv6address))

public fun hostIsIp(host: String): Boolean {
   return IP_PARSER.match(host);
}
