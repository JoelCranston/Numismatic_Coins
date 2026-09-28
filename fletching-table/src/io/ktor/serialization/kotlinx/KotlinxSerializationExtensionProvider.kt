package io.ktor.serialization.kotlinx

import kotlinx.serialization.SerialFormat

public interface KotlinxSerializationExtensionProvider {
   public abstract fun extension(format: SerialFormat): KotlinxSerializationExtension? {
   }
}
