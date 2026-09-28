package it.krzeminski.snakeyaml.engine.kmp.api

import it.krzeminski.snakeyaml.engine.kmp.api.Dump.dump.iter.1
import it.krzeminski.snakeyaml.engine.kmp.emitter.Emitter
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.representer.PlatformRepresenter_jvmKt
import it.krzeminski.snakeyaml.engine.kmp.representer.Representer
import it.krzeminski.snakeyaml.engine.kmp.serializer.Serializer

public class Dump @JvmOverloads  public constructor(settings: DumpSettings, representer: Representer = PlatformRepresenter_jvmKt.Representer(settings)) {
   private final val settings: DumpSettings
   private final val representer: Representer

   init {
      this.settings = settings;
      this.representer = representer;
   }

   public fun dumpAll(instancesIterator: Iterator<Any?>, streamDataWriter: StreamDataWriter) {
      val serializer: Serializer = new Serializer(this.settings, new Emitter(this.settings, streamDataWriter));
      serializer.emitStreamStart();
      val var4: java.util.Iterator = instancesIterator;

      while (var4.hasNext()) {
         serializer.serializeDocument(this.representer.represent(var4.next()));
      }

      serializer.emitStreamEnd();
   }

   public fun dump(yaml: Any?, streamDataWriter: StreamDataWriter) {
      this.dumpAll(SequencesKt.iterator(new 1(yaml, null)), streamDataWriter);
   }

   public fun dumpAllToString(instancesIterator: Iterator<Any?>): String {
      val writer: StringStreamDataWriter = new StringStreamDataWriter(null, 1, null);
      this.dumpAll(instancesIterator, writer);
      return writer.toString();
   }

   public fun dumpToString(yaml: Any?): String {
      val writer: StringStreamDataWriter = new StringStreamDataWriter(null, 1, null);
      this.dump(yaml, writer);
      return writer.toString();
   }

   public fun dumpNode(node: Node, streamDataWriter: StreamDataWriter) {
      val serializer: Serializer = new Serializer(this.settings, new Emitter(this.settings, streamDataWriter));
      serializer.emitStreamStart();
      serializer.serializeDocument(node);
      serializer.emitStreamEnd();
   }

   @JvmOverloads
   fun Dump(settings: DumpSettings) {
      this(settings, null, 2, null);
   }
}
