package it.krzeminski.snakeyaml.engine.kmp.api.lowlevel

import it.krzeminski.snakeyaml.engine.kmp.api.DumpSettings
import it.krzeminski.snakeyaml.engine.kmp.api.StringStreamDataWriter
import it.krzeminski.snakeyaml.engine.kmp.emitter.Emitter
import it.krzeminski.snakeyaml.engine.kmp.events.Event
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nPresent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Present.kt\nit/krzeminski/snakeyaml/engine/kmp/api/lowlevel/Present\n+ 2 Iterators.kt\nkotlin/collections/CollectionsKt__IteratorsKt\n*L\n1#1,42:1\n32#2,2:43\n*S KotlinDebug\n*F\n+ 1 Present.kt\nit/krzeminski/snakeyaml/engine/kmp/api/lowlevel/Present\n*L\n38#1:43,2\n*E\n"])
public class Present(settings: DumpSettings) {
   private final val settings: DumpSettings

   init {
      this.settings = settings;
   }

   public fun emitToString(events: Iterator<Event>): String {
      val writer: StringStreamDataWriter = new StringStreamDataWriter(null, 1, null);
      val emitter: Emitter = new Emitter(this.settings, writer);
      val var6: java.util.Iterator = events;

      while (var6.hasNext()) {
         emitter.emit(var6.next() as Event);
      }

      return writer.toString();
   }
}
