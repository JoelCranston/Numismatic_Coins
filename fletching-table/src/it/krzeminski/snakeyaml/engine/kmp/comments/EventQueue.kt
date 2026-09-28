package it.krzeminski.snakeyaml.engine.kmp.comments

import it.krzeminski.snakeyaml.engine.kmp.comments.EventQueue.1
import it.krzeminski.snakeyaml.engine.kmp.comments.EventQueue.2
import it.krzeminski.snakeyaml.engine.kmp.comments.EventQueue.3
import it.krzeminski.snakeyaml.engine.kmp.comments.EventQueue.4
import it.krzeminski.snakeyaml.engine.kmp.events.Event
import it.krzeminski.snakeyaml.engine.kmp.parser.Parser

private class EventQueue(pollFn: () -> Event, peekFn: () -> Event?) {
   private final val pollFn: () -> Event
   private final val peekFn: () -> Event?

   init {
      this.pollFn = pollFn;
      this.peekFn = peekFn;
   }

   public constructor(parser: Parser) : this(new 1(parser), new 2(parser))
   public constructor(eventQueue: ArrayDeque<Event>) : this(new 3(eventQueue), new 4(eventQueue))
   public fun poll(): Event {
      return this.pollFn.invoke();
   }

   public fun peek(): Event? {
      return this.peekFn.invoke();
   }
}
