package it.krzeminski.snakeyaml.engine.kmp.events

import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import kotlin.enums.EnumEntries

public abstract class Event {
   public final val startMark: Mark?
   public final val endMark: Mark?
   public abstract val eventId: it.krzeminski.snakeyaml.engine.kmp.events.Event.ID

   @JvmOverloads
   open fun Event(startMark: Mark?, endMark: Mark?) {
      this.startMark = startMark;
      this.endMark = endMark;
      if (this.startMark != null && this.endMark == null || this.startMark == null && this.endMark != null) {
         throw new NullPointerException("Both marks must be either present or absent.");
      }
   }

   @JvmOverloads
   open fun Event(startMark: Mark?) {
      this(startMark, null, 2, null);
   }

   @JvmOverloads
   open fun Event() {
      this(null, null, 3, null);
   }

   public enum class ID {
      Alias,
      Comment,
      DocumentEnd,
      DocumentStart,
      MappingEnd,
      MappingStart,
      Scalar,
      SequenceEnd,
      SequenceStart,
      StreamEnd,
      StreamStart
      @JvmStatic
      fun getEntries(): EnumEntries<Event.ID> {
         return $ENTRIES;
      }
   }
}
