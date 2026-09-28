package io.ktor.util.date

import kotlin.time.Duration

public operator fun GMTDate.plus(milliseconds: Long): GMTDate {
   return DateJvmKt.GMTDate(`$this$plus`.getTimestamp() + milliseconds);
}

public operator fun GMTDate.minus(milliseconds: Long): GMTDate {
   return DateJvmKt.GMTDate(`$this$minus`.getTimestamp() - milliseconds);
}

public operator fun GMTDate.plus(duration: Duration): GMTDate {
   return DateJvmKt.GMTDate(`$this$plus_u2dHG0u8IE`.getTimestamp() + Duration.getInWholeMilliseconds-impl(var1));
}

public operator fun GMTDate.minus(duration: Duration): GMTDate {
   return DateJvmKt.GMTDate(`$this$minus_u2dHG0u8IE`.getTimestamp() - Duration.getInWholeMilliseconds-impl(var1));
}

public fun GMTDate.truncateToSeconds(): GMTDate {
   return DateJvmKt.GMTDate(
      `$this$truncateToSeconds`.getSeconds(),
      `$this$truncateToSeconds`.getMinutes(),
      `$this$truncateToSeconds`.getHours(),
      `$this$truncateToSeconds`.getDayOfMonth(),
      `$this$truncateToSeconds`.getMonth(),
      `$this$truncateToSeconds`.getYear()
   );
}
