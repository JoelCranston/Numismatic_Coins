package io.ktor.http

public fun contentRangeHeaderValue(range: LongRange?, fullLength: Long? = null, unit: RangeUnits = RangeUnits.Bytes): String {
   return contentRangeHeaderValue(range, fullLength, unit.getUnitToken());
}

@JvmSynthetic
fun `contentRangeHeaderValue$default`(var0: LongRange, var1: java.lang.Long, var2: RangeUnits, var3: Int, var4: Any): java.lang.String {
   if ((var3 and 2) != 0) {
      var1 = null;
   }

   if ((var3 and 4) != 0) {
      var2 = RangeUnits.Bytes;
   }

   return contentRangeHeaderValue(var0, var1, var2);
}

public fun contentRangeHeaderValue(range: LongRange?, fullLength: Long? = null, unit: String = RangeUnits.Bytes.getUnitToken()): String {
   val var3: StringBuilder = new StringBuilder();
   var3.append(unit);
   var3.append(" ");
   if (range != null) {
      var3.append(range.getFirst());
      var3.append('-');
      var3.append(range.getLast());
   } else {
      var3.append('*');
   }

   var3.append('/');
   var var10001: Any = fullLength;
   if (fullLength == null) {
      var10001 = "*";
   }

   var3.append(var10001);
   return var3.toString();
}

@JvmSynthetic
fun `contentRangeHeaderValue$default`(var0: LongRange, var1: java.lang.Long, var2: java.lang.String, var3: Int, var4: Any): java.lang.String {
   if ((var3 and 2) != 0) {
      var1 = null;
   }

   if ((var3 and 4) != 0) {
      var2 = RangeUnits.Bytes.getUnitToken();
   }

   return contentRangeHeaderValue(var0, var1, var2);
}
