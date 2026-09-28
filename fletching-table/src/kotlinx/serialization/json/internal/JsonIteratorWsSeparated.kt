package kotlinx.serialization.json.internal

import kotlin.jvm.internal.markers.KMappedMarker
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json

private class JsonIteratorWsSeparated<T>(json: Json, lexer: ReaderJsonLexer, deserializer: DeserializationStrategy<Any>) : java.util.Iterator<T>, KMappedMarker {
   private final val json: Json
   private final val lexer: ReaderJsonLexer
   private final val deserializer: DeserializationStrategy<Any>

   init {
      this.json = json;
      this.lexer = lexer;
      this.deserializer = deserializer;
   }

   public override operator fun next(): Any {
      return new StreamingJsonDecoder(this.json, WriteMode.OBJ, this.lexer, this.deserializer.getDescriptor(), null).decodeSerializableValue(this.deserializer);
   }

   public override operator fun hasNext(): Boolean {
      return this.lexer.isNotEof();
   }

   override fun remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
