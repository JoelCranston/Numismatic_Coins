package kotlinx.serialization.json.io.internal

import kotlinx.io.Source
import kotlinx.io.Utf8Kt
import kotlinx.serialization.json.internal.InternalJsonReaderCodePointImpl

internal class IoSerialReader(source: Source) : InternalJsonReaderCodePointImpl {
   private final val source: Source

   init {
      this.source = source;
   }

   public override fun exhausted(): Boolean {
      return this.source.exhausted();
   }

   public override fun nextCodePoint(): Int {
      return Utf8Kt.readCodePointValue(this.source);
   }
}
