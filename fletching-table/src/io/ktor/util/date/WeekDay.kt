package io.ktor.util.date

import kotlin.enums.EnumEntries
import kotlin.jvm.internal.SourceDebugExtension

public enum class WeekDay(value: String) {
   MONDAY("Mon"),
   TUESDAY("Tue"),
   WEDNESDAY("Wed"),
   THURSDAY("Thu"),
   FRIDAY("Fri"),
   SATURDAY("Sat"),
   SUNDAY("Sun")
   public final val value: String
   @JvmStatic
   public WeekDay.Companion Companion = new WeekDay.Companion(null);

   init {
      this.value = value;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<WeekDay> {
      return $ENTRIES;
   }

   @SourceDebugExtension(["SMAP\nDate.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Date.kt\nio/ktor/util/date/WeekDay$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,187:1\n1#2:188\n*E\n"])
   public companion object {
      public fun from(ordinal: Int): WeekDay {
         return WeekDay.getEntries().get(ordinal);
      }

      public fun from(value: String): WeekDay {
         val var3: java.util.Iterator = WeekDay.getEntries().iterator();

         var var10000: Any;
         while (true) {
            if (var3.hasNext()) {
               val var4: Any = var3.next();
               if (!((var4 as WeekDay).getValue() == value)) {
                  continue;
               }

               var10000 = (WeekDay)var4;
               break;
            }

            var10000 = null;
            break;
         }

         var10000 = var10000;
         if (var10000 == null) {
            throw new IllegalStateException(("Invalid day of week: $value").toString());
         } else {
            return var10000;
         }
      }
   }
}
