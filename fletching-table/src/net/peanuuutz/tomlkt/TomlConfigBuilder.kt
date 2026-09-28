package net.peanuuutz.tomlkt

import kotlinx.serialization.modules.SerializersModule

public class TomlConfigBuilder @PublishedApi  internal constructor(from: TomlConfig) {
   public final var serializersModule: SerializersModule
      internal set

   public final var explicitNulls: Boolean
      internal set

   public final var classDiscriminator: String
      internal set

   public final var indentation: TomlIndentation
      internal set

   public final var itemsPerLineInBlockArray: Int
      internal set

   public final var uppercaseInteger: Boolean
      internal set

   public final var ignoreUnknownKeys: Boolean
      internal set

   init {
      this.serializersModule = from.getSerializersModule();
      this.explicitNulls = from.getExplicitNulls();
      this.classDiscriminator = from.getClassDiscriminator();
      this.indentation = from.getIndentation-o8wLciY();
      this.itemsPerLineInBlockArray = from.getItemsPerLineInBlockArray();
      this.uppercaseInteger = from.getUppercaseInteger();
      this.ignoreUnknownKeys = from.getIgnoreUnknownKeys();
   }

   @PublishedApi
   internal fun build(): TomlConfig {
      return new TomlConfig(
         this.serializersModule,
         this.explicitNulls,
         this.classDiscriminator,
         this.indentation,
         RangesKt.coerceAtLeast(this.itemsPerLineInBlockArray, 1),
         this.uppercaseInteger,
         this.ignoreUnknownKeys,
         null
      );
   }
}
