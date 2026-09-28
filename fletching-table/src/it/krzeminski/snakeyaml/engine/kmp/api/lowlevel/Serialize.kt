package it.krzeminski.snakeyaml.engine.kmp.api.lowlevel

import it.krzeminski.snakeyaml.engine.kmp.api.DumpSettings
import it.krzeminski.snakeyaml.engine.kmp.events.Event
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.serializer.Serializer
import java.util.ArrayList

public class Serialize(settings: DumpSettings) {
   private final val settings: DumpSettings

   init {
      this.settings = settings;
   }

   public fun serializeOne(node: Node): List<Event> {
      return this.serializeAll(CollectionsKt.listOf(node));
   }

   public fun serializeAll(nodes: List<Node>): List<Event> {
      val events: java.util.List = new ArrayList();
      val serializer: Serializer = new Serializer(this.settings, Serialize::serializeAll$lambda$0);
      serializer.emitStreamStart();

      for (Node node : nodes) {
         serializer.serializeDocument(node);
      }

      serializer.emitStreamEnd();
      return events;
   }

   @JvmStatic
   fun `serializeAll$lambda$0`(`$events`: java.util.List, it: Event) {
      `$events`.add(it);
   }
}
