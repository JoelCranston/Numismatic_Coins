package io.ktor.util.date

import kotlin.enums.EnumEntries
import kotlin.jvm.internal.SourceDebugExtension

public enum class Month(value: String) {
   JANUARY("Jan"),
   FEBRUARY("Feb"),
   MARCH("Mar"),
   APRIL("Apr"),
   MAY("May"),
   JUNE("Jun"),
   JULY("Jul"),
   AUGUST("Aug"),
   SEPTEMBER("Sep"),
   OCTOBER("Oct"),
   NOVEMBER("Nov"),
   DECEMBER("Dec")
   public final val value: String
   @JvmStatic
   public Month.Companion Companion = new Month.Companion(null);

   init {
      this.value = value;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<Month> {
      return $ENTRIES;
   }

   @SourceDebugExtension(["SMAP\nDate.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Date.kt\nio/ktor/util/date/Month$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,187:1\n1#2:188\n*E\n"])
   public companion object {
      public fun from(ordinal: Int): Month {
         return Month.getEntries().get(ordinal);
      }

      public fun from(value: String): Month {
         val var3: java.util.Iterator = Month.getEntries().iterator();

         var var10000: Any;
         while (true) {
            if (var3.hasNext()) {
               val var4: Any = var3.next();
               if (!((var4 as Month).getValue() == value)) {
                  continue;
               }

               var10000 = (Month)var4;
               break;
            }

            var10000 = null;
            break;
         }

         var10000 = var10000;
         if (var10000 == null) {
            throw new IllegalStateException(("Invalid month: $value").toString());
         } else {
            return var10000;
         }
      }
   }
}
