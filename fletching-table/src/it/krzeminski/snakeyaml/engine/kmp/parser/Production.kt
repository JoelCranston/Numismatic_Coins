package it.krzeminski.snakeyaml.engine.kmp.parser

import it.krzeminski.snakeyaml.engine.kmp.events.Event

internal fun interface Production {
   public abstract fun produce(): Event {
   }
}
