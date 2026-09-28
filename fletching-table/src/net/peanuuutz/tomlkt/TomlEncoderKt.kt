package net.peanuuutz.tomlkt

import kotlinx.serialization.encoding.Encoder

public fun Encoder.asTomlEncoder(): TomlEncoder {
   contract {
      returns() implies (this is TomlEncoder)
   }

   val var10000: TomlEncoder = `$this$asTomlEncoder` as? TomlEncoder;
   if ((`$this$asTomlEncoder` as? TomlEncoder) == null) {
      throw new IllegalArgumentException(("Expect TomlEncoder, but found ${(`$this$asTomlEncoder`.getClass()::class).getSimpleName()}").toString());
   } else {
      return var10000;
   }
}
