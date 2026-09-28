package net.peanuuutz.tomlkt

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.format.DateTimeParseException

public fun NativeLocalDateTime(text: String): LocalDateTime {
   try {
      val var1: LocalDateTime = LocalDateTime.parse(text);
      return var1;
   } catch (var3: DateTimeParseException) {
      throw new IllegalArgumentException(var3);
   }
}

public fun NativeOffsetDateTime(text: String): OffsetDateTime {
   try {
      val var1: OffsetDateTime = OffsetDateTime.parse(text);
      return var1;
   } catch (var3: DateTimeParseException) {
      throw new IllegalArgumentException(var3);
   }
}

public fun NativeLocalDate(text: String): LocalDate {
   try {
      val var1: LocalDate = LocalDate.parse(text);
      return var1;
   } catch (var3: DateTimeParseException) {
      throw new IllegalArgumentException(var3);
   }
}

public fun NativeLocalTime(text: String): LocalTime {
   try {
      val var1: LocalTime = LocalTime.parse(text);
      return var1;
   } catch (var3: DateTimeParseException) {
      throw new IllegalArgumentException(var3);
   }
}
