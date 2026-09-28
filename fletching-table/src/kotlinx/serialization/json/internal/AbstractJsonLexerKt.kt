package kotlinx.serialization.json.internal

internal const val lenientHint: String = "Use 'isLenient = true' in 'Json {}' builder to accept non-compliant JSON."
internal const val coerceInputValuesHint: String = "Use 'coerceInputValues = true' in 'Json {}' builder to coerce nulls if property has a default value."
internal const val specialFlowingValuesHint: String = "It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'"
internal const val ignoreUnknownKeysHint: String =
   "Use 'ignoreUnknownKeys = true' in 'Json {}' builder or '@JsonIgnoreUnknownKeys' annotation to ignore unknown keys."
   internal const val allowStructuredMapKeysHint: String =
   "Use 'allowStructuredMapKeys = true' in 'Json {}' builder to convert such maps to [key1, value1, key2, value2,...] arrays."
   internal const val NULL: String = "null"
internal const val COMMA: Char = ','
internal const val COLON: Char = ':'
internal const val BEGIN_OBJ: Char = '{'
internal const val END_OBJ: Char = '}'
internal const val BEGIN_LIST: Char = '['
internal const val END_LIST: Char = ']'
internal const val STRING: Char = '"'
internal const val STRING_ESC: Char = '\\'
internal const val INVALID: Char = '\u0000'
internal const val UNICODE_ESC: Char = 'u'
internal const val TC_OTHER: Byte = 0
internal const val TC_STRING: Byte = 1
internal const val TC_STRING_ESC: Byte = 2
internal const val TC_WHITESPACE: Byte = 3
internal const val TC_COMMA: Byte = 4
internal const val TC_COLON: Byte = 5
internal const val TC_BEGIN_OBJ: Byte = 6
internal const val TC_END_OBJ: Byte = 7
internal const val TC_BEGIN_LIST: Byte = 8
internal const val TC_END_LIST: Byte = 9
internal const val TC_EOF: Byte = 10
internal const val TC_INVALID: Byte = 127
private const val CTC_MAX: Int = 126
private const val ESC2C_MAX: Int = 117
internal const val asciiCaseMask: Int = 32

internal fun tokenDescription(token: Byte): String {
   return if (token == 1)
      "quotation mark '\"'"
      else
      (
         if (token == 2)
            "string escape sequence '\\'"
            else
            (
               if (token == 4)
                  "comma ','"
                  else
                  (
                     if (token == 5)
                        "colon ':'"
                        else
                        (
                           if (token == 6)
                              "start of the object '{'"
                              else
                              (
                                 if (token == 7)
                                    "end of the object '}'"
                                    else
                                    (
                                       if (token == 8)
                                          "start of the array '['"
                                          else
                                          (
                                             if (token == 9)
                                                "end of the array ']'"
                                                else
                                                (if (token == 10) "end of the input" else (if (token == 127) "invalid token" else "valid token"))
                                          )
                                    )
                              )
                        )
                  )
            )
      );
}

internal fun charToTokenClass(c: Char): Byte {
   return if (c < '~') CharMappings.CHAR_TO_TOKEN[c] else 0;
}

internal fun escapeToChar(c: Int): Char {
   return if (c < 117) CharMappings.ESCAPE_2_CHAR[c] else '\u0000';
}
