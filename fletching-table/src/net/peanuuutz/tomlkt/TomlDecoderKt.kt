package net.peanuuutz.tomlkt

import kotlinx.serialization.encoding.Decoder

public fun Decoder.asTomlDecoder(): TomlDecoder {
   contract {
      returns() implies (this is TomlDecoder)
   }

   val var10000: TomlDecoder = `$this$asTomlDecoder` as? TomlDecoder;
   if ((`$this$asTomlDecoder` as? TomlDecoder) == null) {
      throw new IllegalArgumentException(("Expect TomlDecoder, but found ${(`$this$asTomlDecoder`.getClass()::class).getSimpleName()}").toString());
   } else {
      return var10000;
   }
}
