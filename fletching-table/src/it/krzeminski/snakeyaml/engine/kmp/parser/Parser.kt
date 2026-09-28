package it.krzeminski.snakeyaml.engine.kmp.parser

import it.krzeminski.snakeyaml.engine.kmp.events.Event
import it.krzeminski.snakeyaml.engine.kmp.events.Event.ID
import kotlin.jvm.internal.markers.KMappedMarker

public interface Parser : java.util.Iterator<Event>, KMappedMarker {
   public abstract fun checkEvent(choice: ID): Boolean {
   }

   public abstract fun peekEvent(): Event {
   }

   public abstract operator fun next(): Event {
   }
}
