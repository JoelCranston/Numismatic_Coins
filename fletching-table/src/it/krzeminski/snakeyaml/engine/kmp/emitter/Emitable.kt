package it.krzeminski.snakeyaml.engine.kmp.emitter

import it.krzeminski.snakeyaml.engine.kmp.events.Event

public fun interface Emitable {
   public abstract fun emit(event: Event) {
   }
}
