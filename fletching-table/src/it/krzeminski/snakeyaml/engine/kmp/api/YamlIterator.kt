package it.krzeminski.snakeyaml.engine.kmp.api

import it.krzeminski.snakeyaml.engine.kmp.composer.Composer
import it.krzeminski.snakeyaml.engine.kmp.constructor.BaseConstructor
import kotlin.jvm.internal.markers.KMappedMarker

private class YamlIterator(composer: Composer, constructor: BaseConstructor) : java.util.Iterator<Object>, KMappedMarker {
   private final val composer: Composer
   private final val constructor: BaseConstructor
   private final var composerInitiated: Boolean

   init {
      this.composer = composer;
      this.constructor = constructor;
   }

   public override operator fun hasNext(): Boolean {
      this.composerInitiated = true;
      return this.composer.hasNext();
   }

   public override operator fun next(): Any? {
      if (!this.composerInitiated) {
         this.hasNext();
      }

      return this.constructor.constructSingleDocument(this.composer.next());
   }

   override fun remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
