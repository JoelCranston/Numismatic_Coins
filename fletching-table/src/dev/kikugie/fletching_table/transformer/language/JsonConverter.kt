package dev.kikugie.fletching_table.transformer.language

import java.io.Reader
import kotlinx.serialization.json.JsonElement

public fun interface JsonConverter {
   public abstract fun read(input: Reader): JsonElement {
   }
}
