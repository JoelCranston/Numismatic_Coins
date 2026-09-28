package kotlinx.serialization.json

import kotlin.enums.EnumEntries
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
public enum class DecodeSequenceMode {
   WHITESPACE_SEPARATED,
   ARRAY_WRAPPED,
   AUTO_DETECT
   @JvmStatic
   fun getEntries(): EnumEntries<DecodeSequenceMode> {
      return $ENTRIES;
   }
}
