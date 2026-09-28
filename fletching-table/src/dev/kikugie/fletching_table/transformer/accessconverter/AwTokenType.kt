package dev.kikugie.fletching_table.transformer.accessconverter

import kotlin.enums.EnumEntries

internal enum class AwTokenType {
   HEADER_INTRO,
   HEADER_VERSION,
   HEADER_NAMESPACE,
   TRANSITIVE,
   ACCESSIBLE,
   EXTENDABLE,
   MUTABLE,
   CLASS,
   METHOD,
   FIELD,
   CLASS_NAME,
   ELEMENT_NAME,
   LB,
   RB,
   REFERENCE,
   PRIMITIVE,
   WHITESPACE,
   LINE_BREAK,
   INVALID,
   EOF
   @JvmStatic
   fun getEntries(): EnumEntries<AwTokenType> {
      return $ENTRIES;
   }
}
