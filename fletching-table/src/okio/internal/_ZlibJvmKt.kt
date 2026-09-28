package okio.internal

import java.util.GregorianCalendar

internal final val DEFAULT_COMPRESSION: Int = -1
internal final val EMPTY_BYTE_ARRAY: ByteArray = new byte[0]

internal fun datePartsToEpochMillis(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): Long {
   val calendar: GregorianCalendar = new GregorianCalendar();
   calendar.set(14, 0);
   calendar.set(year, month - 1, day, hour, minute, second);
   return calendar.getTime().getTime();
}
