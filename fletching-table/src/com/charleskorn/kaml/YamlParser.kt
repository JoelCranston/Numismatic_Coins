package com.charleskorn.kaml

import com.charleskorn.kaml.internal.OkioUtilsKt
import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings
import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettingsCopyDslKt
import it.krzeminski.snakeyaml.engine.kmp.api.MutableLoadSettings
import it.krzeminski.snakeyaml.engine.kmp.events.Event
import it.krzeminski.snakeyaml.engine.kmp.events.Event.ID
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import it.krzeminski.snakeyaml.engine.kmp.exceptions.MarkedYamlEngineException
import it.krzeminski.snakeyaml.engine.kmp.parser.ParserImpl
import it.krzeminski.snakeyaml.engine.kmp.scanner.StreamReader
import okio.Source

internal class YamlParser(reader: Source, codePointLimit: Int? = null) {
   private final val dummyFileName: String = "DUMMY_FILE_NAME"
   private final val loadSettings: LoadSettings
   private final val streamReader: StreamReader
   private final val events: ParserImpl

   init {
      this.loadSettings = LoadSettingsCopyDslKt.copy(
         new LoadSettings(this.dummyFileName, null, null, null, null, null, 0, false, false, 0, false, null, null, false, 0, null, 65534, null),
         YamlParser::loadSettings$lambda$0
      );
      this.streamReader = new StreamReader(this.loadSettings, reader);
      this.events = new ParserImpl(this.loadSettings, this.streamReader);
      this.consumeEventOfType(Event.ID.StreamStart, YamlPath.Companion.getRoot());
      if (this.peekEvent(YamlPath.Companion.getRoot()).getEventId() === Event.ID.StreamEnd) {
         throw new EmptyYamlDocumentException("The YAML document is empty.", YamlPath.Companion.getRoot());
      } else {
         this.consumeEventOfType(Event.ID.DocumentStart, YamlPath.Companion.getRoot());
      }
   }

   internal constructor(source: String) : this(OkioUtilsKt.bufferedSource(source), null, 2, null)
   public fun ensureEndOfStreamReached() {
      this.consumeEventOfType(Event.ID.DocumentEnd, YamlPath.Companion.getRoot());
      this.consumeEventOfType(Event.ID.StreamEnd, YamlPath.Companion.getRoot());
   }

   public fun consumeEvent(path: YamlPath): Event {
      return this.checkEvent(path, YamlParser::consumeEvent$lambda$0);
   }

   public fun peekEvent(path: YamlPath): Event {
      return this.checkEvent(path, YamlParser::peekEvent$lambda$0);
   }

   public fun consumeEventOfType(type: ID, path: YamlPath) {
      val event: Event = this.consumeEvent(path);
      if (event.getEventId() != type) {
         val var10002: java.lang.String = "Unexpected ${event.getEventId()}, expected $type";
         val var10006: Mark = event.getStartMark();
         val var4: Int = var10006.getLine();
         val var10007: Mark = event.getStartMark();
         throw new MalformedYamlException(var10002, path.withError(new Location(var4, var10007.getColumn())));
      }
   }

   private fun checkEvent(path: YamlPath, retrieve: () -> Event): Event {
      try {
         return retrieve.invoke() as Event;
      } catch (var4: MarkedYamlEngineException) {
         throw this.translateYamlEngineException(var4, path);
      }
   }

   private fun translateYamlEngineException(e: MarkedYamlEngineException, path: YamlPath): MalformedYamlException {
      val updatedMessage: StringBuilder = new StringBuilder();
      val context: java.lang.String = e.getContext();
      val contextMark: Mark = e.getContextMark();
      if (context != null && contextMark != null) {
         updatedMessage.append(
            StringsKt.trimMargin$default(
               "\n                    |$context\n                    | at line ${contextMark.getLine() + 1}, column ${contextMark.getColumn() + 1}:\n                    |${contextMark.createSnippet(
                  4, Integer.MAX_VALUE
               )}\n                    |\n                ",
               null,
               1,
               null
            )
         );
      }

      val var9: Mark = e.getProblemMark();
      if (var9 != null) {
         updatedMessage.append(
            StringsKt.trimMargin$default(
               "\n                    |${this.translateYamlEngineExceptionMessage(e.getProblem())}\n                    | at line ${var9.getLine() + 1}, column ${var9.getColumn()
                  + 1}:\n                    |${var9.createSnippet(4, Integer.MAX_VALUE)}\n                ",
               null,
               1,
               null
            )
         );
      }

      val var10: YamlPath = if (var9 != null) path.withError(new Location(var9.getLine() + 1, var9.getColumn() + 1)) else path;
      val var10002: java.lang.String = updatedMessage.toString();
      return new MalformedYamlException(var10002, var10);
   }

   private fun translateYamlEngineExceptionMessage(message: String): String {
      switch (message.hashCode()) {
         case -664098129:
            if (message.equals("expected <block end>, but found '<block sequence start>'")) {
               return "$message (is the indentation level of this line or a line nearby incorrect?)";
            }
            break;
         case 1972089805:
            if (message.equals("mapping values are not allowed here")) {
               return "$message (is the indentation level of this line or a line nearby incorrect?)";
            }
            break;
         case 1988393302:
            if (message.equals("expected <block end>, but found '<block mapping start>'")) {
               return "$message (is the indentation level of this line or a line nearby incorrect?)";
            }
         default:
      }

      return message;
   }

   @JvmStatic
   fun `loadSettings$lambda$0`(`$codePointLimit`: Int, `$this$copy`: MutableLoadSettings): Unit {
      if (`$codePointLimit` != null) {
         `$this$copy`.setCodePointLimit(`$codePointLimit`);
      }

      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `consumeEvent$lambda$0`(`this$0`: YamlParser): Event {
      return `this$0`.events.next();
   }

   @JvmStatic
   fun `peekEvent$lambda$0`(`this$0`: YamlParser): Event {
      return `this$0`.events.peekEvent();
   }
}
