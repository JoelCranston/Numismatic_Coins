package io.ktor.client.plugins.api

import io.ktor.client.HttpClient
import io.ktor.events.EventDefinition
import kotlin.jvm.functions.Function1

public class MonitoringEvent<Param, Event extends EventDefinition<Param>>(event: Any) : ClientHook<Function1<? super Param, ? extends Unit>> {
   private final val event: Any

   init {
      this.event = (Event)event;
   }

   public open fun install(client: HttpClient, handler: (Any) -> Unit) {
      client.getMonitor().subscribe(this.event, MonitoringEvent::install$lambda$0);
   }

   @JvmStatic
   fun `install$lambda$0`(`$handler`: Function1, it: Any): Unit {
      `$handler`.invoke(it);
      return Unit.INSTANCE;
   }
}
