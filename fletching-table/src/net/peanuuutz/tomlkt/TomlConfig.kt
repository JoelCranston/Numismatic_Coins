package net.peanuuutz.tomlkt

import kotlinx.serialization.modules.SerializersModule

internal class TomlConfig(serializersModule: SerializersModule,
   explicitNulls: Boolean,
   classDiscriminator: String,
   indentation: TomlIndentation,
   itemsPerLineInBlockArray: Int,
   uppercaseInteger: Boolean,
   ignoreUnknownKeys: Boolean
) : TomlConfig(serializersModule, explicitNulls, classDiscriminator, indentation, itemsPerLineInBlockArray, uppercaseInteger, ignoreUnknownKeys) {
   public final val serializersModule: SerializersModule
   public final val explicitNulls: Boolean
   public final val classDiscriminator: String
   public final val indentation: TomlIndentation
   public final val itemsPerLineInBlockArray: Int
   public final val uppercaseInteger: Boolean
   public final val ignoreUnknownKeys: Boolean

   fun TomlConfig(
      serializersModule: SerializersModule,
      explicitNulls: Boolean,
      classDiscriminator: java.lang.String,
      indentation: java.lang.String,
      itemsPerLineInBlockArray: Int,
      uppercaseInteger: Boolean,
      ignoreUnknownKeys: Boolean
   ) {
      this.serializersModule = serializersModule;
      this.explicitNulls = explicitNulls;
      this.classDiscriminator = classDiscriminator;
      this.indentation = indentation;
      this.itemsPerLineInBlockArray = itemsPerLineInBlockArray;
      this.uppercaseInteger = uppercaseInteger;
      this.ignoreUnknownKeys = ignoreUnknownKeys;
   }

   public companion object {
      public final val Default: TomlConfig
   }
}
