package io.ktor.serialization.kotlinx.json

import io.ktor.serialization.kotlinx.KotlinxSerializationExtension
import io.ktor.serialization.kotlinx.KotlinxSerializationExtensionProvider
import kotlinx.serialization.SerialFormat
import kotlinx.serialization.json.Json

public class KotlinxSerializationJsonExtensionProvider : KotlinxSerializationExtensionProvider {
   public override fun extension(format: SerialFormat): KotlinxSerializationExtension? {
      return if (format !is Json) null else new KotlinxSerializationJsonExtensions(format as Json);
   }
}
