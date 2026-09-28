package net.peanuuutz.tomlkt

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import kotlinx.serialization.KSerializer
import net.peanuuutz.tomlkt.internal.LocalDateSerializer
import net.peanuuutz.tomlkt.internal.LocalDateTimeSerializer
import net.peanuuutz.tomlkt.internal.LocalTimeSerializer
import net.peanuuutz.tomlkt.internal.OffsetDateTimeSerializer

public fun TomlLocalDateTime(text: String): LocalDateTime {
   return NativeDateTime_jvmKt.NativeLocalDateTime(text);
}

public fun TomlLocalDateTimeSerializer(): KSerializer<LocalDateTime> {
   return LocalDateTimeSerializer.INSTANCE;
}

public fun TomlOffsetDateTime(text: String): OffsetDateTime {
   return NativeDateTime_jvmKt.NativeOffsetDateTime(text);
}

public fun TomlOffsetDateTimeSerializer(): KSerializer<OffsetDateTime> {
   return OffsetDateTimeSerializer.INSTANCE;
}

public fun TomlLocalDate(text: String): LocalDate {
   return NativeDateTime_jvmKt.NativeLocalDate(text);
}

public fun TomlLocalDateSerializer(): KSerializer<LocalDate> {
   return LocalDateSerializer.INSTANCE;
}

public fun TomlLocalTime(text: String): LocalTime {
   return NativeDateTime_jvmKt.NativeLocalTime(text);
}

public fun TomlLocalTimeSerializer(): KSerializer<LocalTime> {
   return LocalTimeSerializer.INSTANCE;
}
