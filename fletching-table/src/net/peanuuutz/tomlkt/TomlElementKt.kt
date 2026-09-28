@file:SourceDebugExtension(["SMAP\nTomlElement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TomlElement.kt\nnet/peanuuutz/tomlkt/TomlElementKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,1574:1\n666#1,8:1576\n1#2:1575\n1563#3:1584\n1634#3,3:1585\n1285#3,2:1592\n1299#3,4:1594\n1285#3,2:1598\n1299#3,4:1600\n1563#3:1604\n1634#3,3:1605\n11228#4:1588\n11563#4,3:1589\n*S KotlinDebug\n*F\n+ 1 TomlElement.kt\nnet/peanuuutz/tomlkt/TomlElementKt\n*L\n651#1:1576,8\n731#1:1584\n731#1:1585,3\n1137#1:1592,2\n1137#1:1594,4\n1148#1:1598,2\n1148#1:1600,4\n1453#1:1604\n1453#1:1605,3\n739#1:1588\n739#1:1589,3\n*E\n"])

package net.peanuuutz.tomlkt

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.MagicApiIntrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.descriptors.SerialDescriptorKt
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuildersKt
import net.peanuuutz.tomlkt.internal.StringUtilsKt
import net.peanuuutz.tomlkt.internal.TomlSerializationExceptionsKt
import net.peanuuutz.tomlkt.internal.parser.ArrayNode
import net.peanuuutz.tomlkt.internal.parser.KeyNode
import net.peanuuutz.tomlkt.internal.parser.TreeNode
import net.peanuuutz.tomlkt.internal.parser.ValueNode

public fun TomlElement.asTomlNull(): TomlNull {
   contract {
      returns() implies (this is TomlNull)
   }

   val var10000: TomlNull = `$this$asTomlNull` as? TomlNull;
   if ((`$this$asTomlNull` as? TomlNull) == null) {
      throw new IllegalArgumentException(failConversion(`$this$asTomlNull`, "TomlNull").toString());
   } else {
      return var10000;
   }
}

public fun TomlElement.asTomlLiteral(): TomlLiteral {
   contract {
      returns() implies (this is TomlLiteral)
   }

   val var10000: TomlLiteral = `$this$asTomlLiteral` as? TomlLiteral;
   if ((`$this$asTomlLiteral` as? TomlLiteral) == null) {
      throw new IllegalArgumentException(failConversion(`$this$asTomlLiteral`, "TomlLiteral").toString());
   } else {
      return var10000;
   }
}

public fun TomlLiteral(boolean: Boolean): TomlLiteral {
   return new TomlLiteral(java.lang.String.valueOf(var0), TomlLiteral.Type.Boolean);
}

public fun TomlLiteral(byte: Byte): TomlLiteral {
   return TomlLiteral((long)var0);
}

public fun TomlLiteral(short: Short): TomlLiteral {
   return TomlLiteral((long)var0);
}

public fun TomlLiteral(int: Int): TomlLiteral {
   return TomlLiteral((long)var0);
}

public fun TomlLiteral(long: Long): TomlLiteral {
   return new TomlLiteral(java.lang.String.valueOf(var0), TomlLiteral.Type.Integer);
}

public fun TomlLiteral(uByte: UByte): TomlLiteral {
   return new TomlLiteral(UByte.toString-impl(uByte), TomlLiteral.Type.Integer);
}

public fun TomlLiteral(uShort: UShort): TomlLiteral {
   return new TomlLiteral(UShort.toString-impl(uShort), TomlLiteral.Type.Integer);
}

public fun TomlLiteral(uInt: UInt): TomlLiteral {
   return new TomlLiteral(Integer.toUnsignedString(uInt), TomlLiteral.Type.Integer);
}

public fun TomlLiteral(uLong: ULong): TomlLiteral {
   return new TomlLiteral(java.lang.Long.toUnsignedString(uLong), TomlLiteral.Type.Integer);
}

public fun TomlLiteral(float: Float): TomlLiteral {
   return new TomlLiteral(StringUtilsKt.toStringModified(var0), TomlLiteral.Type.Float);
}

public fun TomlLiteral(double: Double): TomlLiteral {
   return new TomlLiteral(StringUtilsKt.toStringModified(var0), TomlLiteral.Type.Float);
}

public fun TomlLiteral(char: Char): TomlLiteral {
   return TomlLiteral(java.lang.String.valueOf(var0));
}

public fun TomlLiteral(string: String): TomlLiteral {
   return new TomlLiteral(string, TomlLiteral.Type.String);
}

public fun TomlLiteral(localDateTime: LocalDateTime): TomlLiteral {
   return new TomlLiteral(localDateTime.toString(), TomlLiteral.Type.LocalDateTime);
}

public fun TomlLiteral(offsetDateTime: OffsetDateTime): TomlLiteral {
   return new TomlLiteral(offsetDateTime.toString(), TomlLiteral.Type.OffsetDateTime);
}

public fun TomlLiteral(localDate: LocalDate): TomlLiteral {
   return new TomlLiteral(localDate.toString(), TomlLiteral.Type.LocalDate);
}

public fun TomlLiteral(localTime: LocalTime): TomlLiteral {
   return new TomlLiteral(localTime.toString(), TomlLiteral.Type.LocalTime);
}

@JvmSynthetic
public inline fun <reified E : Enum<E>> TomlLiteral(enum: E, serializersModule: SerializersModule = SerializersModuleBuildersKt.EmptySerializersModule()): TomlLiteral {
   Intrinsics.reifiedOperationMarker(6, "E");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return TomlLiteral(SerializersKt.serializer(serializersModule, null).getDescriptor().getElementName(var0.ordinal()));
}

@JvmSynthetic
fun `TomlLiteral$default`(var0: java.lang.Enum, serializersModule: SerializersModule, `$i$f$TomlLiteral`: Int, stringRepresentation: Any): TomlLiteral {
   if ((`$i$f$TomlLiteral` and 2) != 0) {
      serializersModule = SerializersModuleBuildersKt.EmptySerializersModule();
   }

   Intrinsics.reifiedOperationMarker(6, "E");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return TomlLiteral(SerializersKt.serializer(serializersModule, null).getDescriptor().getElementName(var0.ordinal()));
}

public fun TomlLiteral.toBoolean(): Boolean {
   val var10000: java.lang.Boolean = toBooleanOrNull(`$this$toBoolean`);
   if (var10000 == null) {
      throw new IllegalArgumentException(("Cannot convert $`$this$toBoolean` to Boolean").toString());
   } else {
      return var10000;
   }
}

public fun TomlLiteral.toBooleanOrNull(): Boolean? {
   val var1: java.lang.String = `$this$toBooleanOrNull`.getContent();
   return if (var1 == "true") true else (if (var1 == "false") false else null);
}

public fun TomlLiteral.toByte(): Byte {
   return java.lang.Byte.parseByte(`$this$toByte`.getContent());
}

public fun TomlLiteral.toByteOrNull(): Byte? {
   return StringsKt.toByteOrNull(`$this$toByteOrNull`.getContent());
}

public fun TomlLiteral.toShort(): Short {
   return java.lang.Short.parseShort(`$this$toShort`.getContent());
}

public fun TomlLiteral.toShortOrNull(): Short? {
   return StringsKt.toShortOrNull(`$this$toShortOrNull`.getContent());
}

public fun TomlLiteral.toInt(): Int {
   return Integer.parseInt(`$this$toInt`.getContent());
}

public fun TomlLiteral.toIntOrNull(): Int? {
   return StringsKt.toIntOrNull(`$this$toIntOrNull`.getContent());
}

public fun TomlLiteral.toLong(): Long {
   return java.lang.Long.parseLong(`$this$toLong`.getContent());
}

public fun TomlLiteral.toLongOrNull(): Long? {
   return StringsKt.toLongOrNull(`$this$toLongOrNull`.getContent());
}

public fun TomlLiteral.toUByte(): UByte {
   return UStringsKt.toUByte(`$this$toUByte`.getContent());
}

public fun TomlLiteral.toUByteOrNull(): UByte? {
   return UStringsKt.toUByteOrNull(`$this$toUByteOrNull`.getContent());
}

public fun TomlLiteral.toUShort(): UShort {
   return UStringsKt.toUShort(`$this$toUShort`.getContent());
}

public fun TomlLiteral.toUShortOrNull(): UShort? {
   return UStringsKt.toUShortOrNull(`$this$toUShortOrNull`.getContent());
}

public fun TomlLiteral.toUInt(): UInt {
   return UStringsKt.toUInt(`$this$toUInt`.getContent());
}

public fun TomlLiteral.toUIntOrNull(): UInt? {
   return UStringsKt.toUIntOrNull(`$this$toUIntOrNull`.getContent());
}

public fun TomlLiteral.toULong(): ULong {
   return UStringsKt.toULong(`$this$toULong`.getContent());
}

public fun TomlLiteral.toULongOrNull(): ULong? {
   return UStringsKt.toULongOrNull(`$this$toULongOrNull`.getContent());
}

public fun TomlLiteral.toFloat(): Float {
   val var10000: java.lang.Float = toFloatOrNull(`$this$toFloat`);
   if (var10000 != null) {
      return var10000;
   } else {
      throw new NumberFormatException("Cannot convert $`$this$toFloat` to Float");
   }
}

public fun TomlLiteral.toFloatOrNull(): Float? {
   val var1: java.lang.String = `$this$toFloatOrNull`.getContent();
   switch (var1.hashCode()) {
      case 104417:
         if (var1.equals("inf")) {
            return java.lang.Float.POSITIVE_INFINITY;
         }
         break;
      case 108827:
         if (var1.equals("nan")) {
            return java.lang.Float.NaN;
         }
         break;
      case 1385430:
         if (var1.equals("+inf")) {
            return java.lang.Float.POSITIVE_INFINITY;
         }
         break;
      case 1445012:
         if (var1.equals("-inf")) {
            return java.lang.Float.NEGATIVE_INFINITY;
         }
      default:
   }

   return StringsKt.toFloatOrNull(`$this$toFloatOrNull`.getContent());
}

public fun TomlLiteral.toDouble(): Double {
   val var10000: java.lang.Double = toDoubleOrNull(`$this$toDouble`);
   if (var10000 != null) {
      return var10000;
   } else {
      throw new NumberFormatException("Cannot convert $`$this$toDouble` to Double");
   }
}

public fun TomlLiteral.toDoubleOrNull(): Double? {
   val var1: java.lang.String = `$this$toDoubleOrNull`.getContent();
   switch (var1.hashCode()) {
      case 104417:
         if (var1.equals("inf")) {
            return java.lang.Double.POSITIVE_INFINITY;
         }
         break;
      case 108827:
         if (var1.equals("nan")) {
            return java.lang.Double.NaN;
         }
         break;
      case 1385430:
         if (var1.equals("+inf")) {
            return java.lang.Double.POSITIVE_INFINITY;
         }
         break;
      case 1445012:
         if (var1.equals("-inf")) {
            return java.lang.Double.NEGATIVE_INFINITY;
         }
      default:
   }

   return StringsKt.toDoubleOrNull(`$this$toDoubleOrNull`.getContent());
}

public fun TomlLiteral.toChar(): Char {
   return StringsKt.single(`$this$toChar`.getContent());
}

public fun TomlLiteral.toCharOrNull(): Char? {
   return StringsKt.singleOrNull(`$this$toCharOrNull`.getContent());
}

public fun TomlLiteral.toLocalDateTime(): LocalDateTime {
   return NativeDateTime_jvmKt.NativeLocalDateTime(`$this$toLocalDateTime`.getContent());
}

public fun TomlLiteral.toLocalDateTimeOrNull(): LocalDateTime? {
   var var1: LocalDateTime;
   try {
      var1 = toLocalDateTime(`$this$toLocalDateTimeOrNull`);
   } catch (var3: IllegalArgumentException) {
      var1 = null;
   }

   return var1;
}

public fun TomlLiteral.toOffsetDateTime(): OffsetDateTime {
   return NativeDateTime_jvmKt.NativeOffsetDateTime(`$this$toOffsetDateTime`.getContent());
}

public fun TomlLiteral.toOffsetDateTimeOrNull(): OffsetDateTime? {
   var var1: OffsetDateTime;
   try {
      var1 = toOffsetDateTime(`$this$toOffsetDateTimeOrNull`);
   } catch (var3: IllegalArgumentException) {
      var1 = null;
   }

   return var1;
}

public fun TomlLiteral.toLocalDate(): LocalDate {
   return NativeDateTime_jvmKt.NativeLocalDate(`$this$toLocalDate`.getContent());
}

public fun TomlLiteral.toLocalDateOrNull(): LocalDate? {
   var var1: LocalDate;
   try {
      var1 = toLocalDate(`$this$toLocalDateOrNull`);
   } catch (var3: IllegalArgumentException) {
      var1 = null;
   }

   return var1;
}

public fun TomlLiteral.toLocalTime(): LocalTime {
   return NativeDateTime_jvmKt.NativeLocalTime(`$this$toLocalTime`.getContent());
}

public fun TomlLiteral.toLocalTimeOrNull(): LocalTime? {
   var var1: LocalTime;
   try {
      var1 = toLocalTime(`$this$toLocalTimeOrNull`);
   } catch (var3: IllegalArgumentException) {
      var1 = null;
   }

   return var1;
}

@JvmSynthetic
public inline fun <reified E : Enum<E>> TomlLiteral.toEnum(serializersModule: SerializersModule = ...): E {
   Intrinsics.reifiedOperationMarker(6, "E");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   val `index$iv`: Int = CollectionsKt.indexOf(
      SerialDescriptorKt.getElementNames(SerializersKt.serializer(serializersModule, null).getDescriptor()), `$this$toEnum`.getContent()
   );
   val var10000: java.lang.Enum;
   if (`index$iv` != -1) {
      Intrinsics.reifiedOperationMarker(5, "E");
      var10000 = (new java.lang.Enum[0])[`index$iv`];
   } else {
      var10000 = null as java.lang.Enum;
   }

   if (var10000 == null) {
      val var9: StringBuilder = new StringBuilder().append("Cannot convert ").append(`$this$toEnum`).append(" to ");
      Intrinsics.reifiedOperationMarker(4, "E");
      throw new IllegalArgumentException(var9.append((java.lang.Enum::class).getSimpleName()).toString().toString());
   } else {
      return (E)(var10000 as java.lang.Enum);
   }
}

@JvmSynthetic
fun TomlLiteral.`toEnum$default`(serializersModule: SerializersModule, `$i$f$toEnum`: Int, `$this$toEnumOrNull$iv`: Any): java.lang.Enum {
   if ((`$i$f$toEnum` and 1) != 0) {
      serializersModule = SerializersModuleBuildersKt.EmptySerializersModule();
   }

   Intrinsics.reifiedOperationMarker(6, "E");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   val `index$iv`: Int = CollectionsKt.indexOf(
      SerialDescriptorKt.getElementNames(SerializersKt.serializer(serializersModule, null).getDescriptor()), `$this$toEnum_u24default`.getContent()
   );
   val var10000: java.lang.Enum;
   if (`index$iv` != -1) {
      Intrinsics.reifiedOperationMarker(5, "E");
      var10000 = (new java.lang.Enum[0])[`index$iv`];
   } else {
      var10000 = null as java.lang.Enum;
   }

   if (var10000 == null) {
      val var10: StringBuilder = new StringBuilder().append("Cannot convert ").append(`$this$toEnum_u24default`).append(" to ");
      Intrinsics.reifiedOperationMarker(4, "E");
      throw new IllegalArgumentException(var10.append((java.lang.Enum::class).getSimpleName()).toString().toString());
   } else {
      return var10000;
   }
}

@JvmSynthetic
public inline fun <reified E : Enum<E>> TomlLiteral.toEnumOrNull(serializersModule: SerializersModule = ...): E? {
   Intrinsics.reifiedOperationMarker(6, "E");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   val index: Int = CollectionsKt.indexOf(
      SerialDescriptorKt.getElementNames(SerializersKt.serializer(serializersModule, null).getDescriptor()), `$this$toEnumOrNull`.getContent()
   );
   val var10000: java.lang.Enum;
   if (index != -1) {
      Intrinsics.reifiedOperationMarker(5, "E");
      var10000 = (new java.lang.Enum[0])[index];
   } else {
      var10000 = null as java.lang.Enum;
   }

   return (E)var10000;
}

@JvmSynthetic
fun TomlLiteral.`toEnumOrNull$default`(serializersModule: SerializersModule, `$i$f$toEnumOrNull`: Int, index: Any): java.lang.Enum {
   if ((`$i$f$toEnumOrNull` and 1) != 0) {
      serializersModule = SerializersModuleBuildersKt.EmptySerializersModule();
   }

   Intrinsics.reifiedOperationMarker(6, "E");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   val var6: Int = CollectionsKt.indexOf(
      SerialDescriptorKt.getElementNames(SerializersKt.serializer(serializersModule, null).getDescriptor()), `$this$toEnumOrNull_u24default`.getContent()
   );
   val var10000: java.lang.Enum;
   if (var6 != -1) {
      Intrinsics.reifiedOperationMarker(5, "E");
      var10000 = (new java.lang.Enum[0])[var6];
   } else {
      var10000 = null as java.lang.Enum;
   }

   return var10000;
}

public fun TomlElement.asTomlArray(): TomlArray {
   contract {
      returns() implies (this is TomlArray)
   }

   val var10000: TomlArray = `$this$asTomlArray` as? TomlArray;
   if ((`$this$asTomlArray` as? TomlArray) == null) {
      throw new IllegalArgumentException(failConversion(`$this$asTomlArray`, "TomlArray").toString());
   } else {
      return var10000;
   }
}

public fun TomlArray(iterable: Iterable<*>): TomlArray {
   val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));

   for (Object item$iv$iv : iterable) {
      `destination$iv$iv`.add(toTomlElement(`item$iv$iv`));
   }

   return new TomlArray(`destination$iv$iv` as java.util.List, null, 2, null);
}

public fun TomlArray(vararg values: Any?): TomlArray {
   val `destination$iv$iv`: java.util.Collection = new ArrayList(values.length);

   for (Object item$iv$iv : values) {
      `destination$iv$iv`.add(toTomlElement(`item$iv$iv`));
   }

   return new TomlArray(`destination$iv$iv` as java.util.List, null, 2, null);
}

public fun TomlArray.annotated(annotations: List<List<Annotation>>): TomlArray {
   return new TomlArray(`$this$annotated`.getContent(), annotations);
}

public fun TomlArray.annotated(vararg annotations: List<Annotation>): TomlArray {
   return new TomlArray(`$this$annotated`.getContent(), ArraysKt.asList(annotations));
}

public fun TomlArray.allAnnotated(annotations: List<Annotation>): TomlArray {
   val var3: Int = `$this$allAnnotated`.size();
   val var4: ArrayList = new ArrayList(var3);

   for (int var5 = 0; var5 < var3; var5++) {
      var4.add(annotations);
   }

   return new TomlArray(`$this$allAnnotated`.getContent(), var4);
}

public fun TomlArray.allAnnotated(vararg annotations: Annotation): TomlArray {
   val annotationsAsList: java.util.List = ArraysKt.asList(annotations);
   val var4: Int = `$this$allAnnotated`.size();
   val var5: ArrayList = new ArrayList(var4);

   for (int var6 = 0; var6 < var4; var6++) {
      var5.add(annotationsAsList);
   }

   return new TomlArray(`$this$allAnnotated`.getContent(), var5);
}

public fun TomlArray.getLiteral(index: Int): TomlLiteral {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getLiteral`, index);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlLiteral with index $index").toString());
   } else {
      return var10000;
   }
}

public fun TomlArray.getLiteralOrNull(index: Int): TomlLiteral? {
   val var2: Any = CollectionsKt.getOrNull(`$this$getLiteralOrNull`, index);
   return var2 as? TomlLiteral;
}

public fun TomlArray.getBoolean(index: Int): Boolean {
   val var10000: java.lang.Boolean = getBooleanOrNull(`$this$getBoolean`, index);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a boolean with index $index").toString());
   } else {
      return var10000;
   }
}

public fun TomlArray.getBooleanOrNull(index: Int): Boolean? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getBooleanOrNull`, index);
   return if (var10000 != null) toBooleanOrNull(var10000) else null;
}

public fun TomlArray.getInteger(index: Int): Long {
   val var10000: java.lang.Long = getIntegerOrNull(`$this$getInteger`, index);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find an integer with index $index").toString());
   } else {
      return var10000.longValue();
   }
}

public fun TomlArray.getIntegerOrNull(index: Int): Long? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getIntegerOrNull`, index);
   return if (var10000 != null) toLongOrNull(var10000) else null;
}

public fun TomlArray.getFloat(index: Int): Double {
   val var10000: java.lang.Double = getFloatOrNull(`$this$getFloat`, index);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a float with index $index").toString());
   } else {
      return var10000.doubleValue();
   }
}

public fun TomlArray.getFloatOrNull(index: Int): Double? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getFloatOrNull`, index);
   return if (var10000 != null) toDoubleOrNull(var10000) else null;
}

public fun TomlArray.getString(index: Int): String {
   val var10000: java.lang.String = getStringOrNull(`$this$getString`, index);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a string with index $index").toString());
   } else {
      return var10000;
   }
}

public fun TomlArray.getStringOrNull(index: Int): String? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getStringOrNull`, index);
   return if (var10000 != null) var10000.toString() else null;
}

public fun TomlArray.getLocalDateTime(index: Int): LocalDateTime {
   val var10000: LocalDateTime = getLocalDateTimeOrNull(`$this$getLocalDateTime`, index);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlLocalDateTime with index $index").toString());
   } else {
      return var10000;
   }
}

public fun TomlArray.getLocalDateTimeOrNull(index: Int): LocalDateTime? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getLocalDateTimeOrNull`, index);
   return if (var10000 != null) toLocalDateTimeOrNull(var10000) else null;
}

public fun TomlArray.getOffsetDateTime(index: Int): OffsetDateTime {
   val var10000: OffsetDateTime = getOffsetDateTimeOrNull(`$this$getOffsetDateTime`, index);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlOffsetDateTime with index $index").toString());
   } else {
      return var10000;
   }
}

public fun TomlArray.getOffsetDateTimeOrNull(index: Int): OffsetDateTime? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getOffsetDateTimeOrNull`, index);
   return if (var10000 != null) toOffsetDateTimeOrNull(var10000) else null;
}

public fun TomlArray.getLocalDate(index: Int): LocalDate {
   val var10000: LocalDate = getLocalDateOrNull(`$this$getLocalDate`, index);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlLocalDate with index $index").toString());
   } else {
      return var10000;
   }
}

public fun TomlArray.getLocalDateOrNull(index: Int): LocalDate? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getLocalDateOrNull`, index);
   return if (var10000 != null) toLocalDateOrNull(var10000) else null;
}

public fun TomlArray.getLocalTime(index: Int): LocalTime {
   val var10000: LocalTime = getLocalTimeOrNull(`$this$getLocalTime`, index);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlLocalTime with index $index").toString());
   } else {
      return var10000;
   }
}

public fun TomlArray.getLocalTimeOrNull(index: Int): LocalTime? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getLocalTimeOrNull`, index);
   return if (var10000 != null) toLocalTimeOrNull(var10000) else null;
}

public fun TomlArray.getArray(index: Int): TomlArray {
   val var10000: TomlArray = getArrayOrNull(`$this$getArray`, index);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlArray with index $index").toString());
   } else {
      return var10000;
   }
}

public fun TomlArray.getArrayOrNull(index: Int): TomlArray? {
   val var2: Any = CollectionsKt.getOrNull(`$this$getArrayOrNull`, index);
   return var2 as? TomlArray;
}

public fun TomlArray.getTable(index: Int): TomlTable {
   val var10000: TomlTable = getTableOrNull(`$this$getTable`, index);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlTable with index $index").toString());
   } else {
      return var10000;
   }
}

public fun TomlArray.getTableOrNull(index: Int): TomlTable? {
   val var2: Any = CollectionsKt.getOrNull(`$this$getTableOrNull`, index);
   return var2 as? TomlTable;
}

public fun TomlElement.asTomlTable(): TomlTable {
   contract {
      returns() implies (this is TomlTable)
   }

   val var10000: TomlTable = `$this$asTomlTable` as? TomlTable;
   if ((`$this$asTomlTable` as? TomlTable) == null) {
      throw new IllegalArgumentException(failConversion(`$this$asTomlTable`, "TomlTable").toString());
   } else {
      return var10000;
   }
}

public fun TomlTable(map: Map<*, *>): TomlTable {
   val var2: java.util.Map = MapsKt.createMapBuilder(map.size());
   val `$this$TomlTable_u24lambda_u2421`: java.util.Map = var2;

   for (Entry var6 : map.entrySet()) {
      `$this$TomlTable_u24lambda_u2421`.put(toTomlKey(var6.getKey()), toTomlElement(var6.getValue()));
   }

   return new TomlTable(MapsKt.build(var2), null, 2, null);
}

public fun TomlTable(vararg entries: Pair<*, *>): TomlTable {
   val var2: java.util.Map = MapsKt.createMapBuilder(entries.length);
   val `$this$TomlTable_u24lambda_u2422`: java.util.Map = var2;

   for (Pair var7 : entries) {
      `$this$TomlTable_u24lambda_u2422`.put(toTomlKey(var7.component1()), toTomlElement(var7.component2()));
   }

   return new TomlTable(MapsKt.build(var2), null, 2, null);
}

public fun TomlTable.annotated(annotations: Map<*, List<Annotation>>): TomlTable {
   val var3: java.util.Map = MapsKt.createMapBuilder(`$this$annotated`.size());
   val `$this$annotated_u24lambda_u2423`: java.util.Map = var3;

   for (Entry var7 : annotations.entrySet()) {
      `$this$annotated_u24lambda_u2423`.put(toTomlKey(var7.getKey()), var7.getValue() as java.util.List);
   }

   return new TomlTable(`$this$annotated`.getContent(), MapsKt.build(var3));
}

public fun TomlTable.annotated(vararg annotations: Pair<*, List<Annotation>>): TomlTable {
   val var3: java.util.Map = MapsKt.createMapBuilder(annotations.length);
   val `$this$annotated_u24lambda_u2424`: java.util.Map = var3;

   for (Pair var8 : annotations) {
      `$this$annotated_u24lambda_u2424`.put(toTomlKey(var8.component1()), var8.component2() as java.util.List);
   }

   return new TomlTable(`$this$annotated`.getContent(), MapsKt.build(var3));
}

public fun TomlTable.allAnnotated(annotations: List<Annotation>): TomlTable {
   val `$this$associateWith$iv`: java.lang.Iterable = `$this$allAnnotated`.getContent().keySet();
   val `result$iv`: LinkedHashMap = new LinkedHashMap(
      RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(`$this$associateWith$iv`, 10)), 16)
   );

   for (Object element$iv$iv : $this$associateWith$iv) {
      val var10000: java.util.Map = `result$iv`;
      val it: java.lang.String = `element$iv$iv` as java.lang.String;
      var10000.put(`element$iv$iv`, annotations);
   }

   return new TomlTable(`$this$allAnnotated`.getContent(), `result$iv`);
}

public fun TomlTable.allAnnotated(vararg annotations: Annotation): TomlTable {
   val annotationsAsList: java.util.List = ArraysKt.asList(annotations);
   val `$this$associateWith$iv`: java.lang.Iterable = `$this$allAnnotated`.getContent().keySet();
   val `result$iv`: LinkedHashMap = new LinkedHashMap(
      RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(`$this$associateWith$iv`, 10)), 16)
   );

   for (Object element$iv$iv : $this$associateWith$iv) {
      val var10000: java.util.Map = `result$iv`;
      val it: java.lang.String = `element$iv$iv` as java.lang.String;
      var10000.put(`element$iv$iv`, annotationsAsList);
   }

   return new TomlTable(`$this$allAnnotated`.getContent(), `result$iv`);
}

public operator fun TomlTable.get(vararg keys: Any?): TomlElement? {
   return if (keys.length == 0) `$this$get` else getByPathRecursively(`$this$get`, keys, 0);
}

public fun TomlTable.getLiteral(key: Any?): TomlLiteral {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getLiteral`, key);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlLiteral with key $key").toString());
   } else {
      return var10000;
   }
}

public fun TomlTable.getLiteralOrNull(key: Any?): TomlLiteral? {
   val var2: TomlElement = `$this$getLiteralOrNull`.get(key);
   return var2 as? TomlLiteral;
}

public fun TomlTable.getBoolean(key: Any?): Boolean {
   val var10000: java.lang.Boolean = getBooleanOrNull(`$this$getBoolean`, key);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a boolean with key $key").toString());
   } else {
      return var10000;
   }
}

public fun TomlTable.getBooleanOrNull(key: Any?): Boolean? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getBooleanOrNull`, key);
   return if (var10000 != null) toBooleanOrNull(var10000) else null;
}

public fun TomlTable.getInteger(key: Any?): Long {
   val var10000: java.lang.Long = getIntegerOrNull(`$this$getInteger`, key);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find an integer with key $key").toString());
   } else {
      return var10000.longValue();
   }
}

public fun TomlTable.getIntegerOrNull(key: Any?): Long? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getIntegerOrNull`, key);
   return if (var10000 != null) toLongOrNull(var10000) else null;
}

public fun TomlTable.getFloat(key: Any?): Double {
   val var10000: java.lang.Double = getFloatOrNull(`$this$getFloat`, key);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a float with key $key").toString());
   } else {
      return var10000.doubleValue();
   }
}

public fun TomlTable.getFloatOrNull(key: Any?): Double? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getFloatOrNull`, key);
   return if (var10000 != null) toDoubleOrNull(var10000) else null;
}

public fun TomlTable.getString(key: Any?): String {
   val var10000: java.lang.String = getStringOrNull(`$this$getString`, key);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a string with key $key").toString());
   } else {
      return var10000;
   }
}

public fun TomlTable.getStringOrNull(key: Any?): String? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getStringOrNull`, key);
   return if (var10000 != null) var10000.toString() else null;
}

public fun TomlTable.getLocalDateTime(key: Any?): LocalDateTime {
   val var10000: LocalDateTime = getLocalDateTimeOrNull(`$this$getLocalDateTime`, key);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlLocalDateTime with key $key").toString());
   } else {
      return var10000;
   }
}

public fun TomlTable.getLocalDateTimeOrNull(key: Any?): LocalDateTime? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getLocalDateTimeOrNull`, key);
   return if (var10000 != null) toLocalDateTimeOrNull(var10000) else null;
}

public fun TomlTable.getOffsetDateTime(key: Any?): OffsetDateTime {
   val var10000: OffsetDateTime = getOffsetDateTimeOrNull(`$this$getOffsetDateTime`, key);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlOffsetDateTime with key $key").toString());
   } else {
      return var10000;
   }
}

public fun TomlTable.getOffsetDateTimeOrNull(key: Any?): OffsetDateTime? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getOffsetDateTimeOrNull`, key);
   return if (var10000 != null) toOffsetDateTimeOrNull(var10000) else null;
}

public fun TomlTable.getLocalDate(key: Any?): LocalDate {
   val var10000: LocalDate = getLocalDateOrNull(`$this$getLocalDate`, key);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlLocalDate with key $key").toString());
   } else {
      return var10000;
   }
}

public fun TomlTable.getLocalDateOrNull(key: Any?): LocalDate? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getLocalDateOrNull`, key);
   return if (var10000 != null) toLocalDateOrNull(var10000) else null;
}

public fun TomlTable.getLocalTime(key: Any?): LocalTime {
   val var10000: LocalTime = getLocalTimeOrNull(`$this$getLocalTime`, key);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlLocalTime with key $key").toString());
   } else {
      return var10000;
   }
}

public fun TomlTable.getLocalTimeOrNull(key: Any?): LocalTime? {
   val var10000: TomlLiteral = getLiteralOrNull(`$this$getLocalTimeOrNull`, key);
   return if (var10000 != null) toLocalTimeOrNull(var10000) else null;
}

public fun TomlTable.getArray(key: Any?): TomlArray {
   val var10000: TomlArray = getArrayOrNull(`$this$getArray`, key);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlArray with key $key").toString());
   } else {
      return var10000;
   }
}

public fun TomlTable.getArrayOrNull(key: Any?): TomlArray? {
   val var2: TomlElement = `$this$getArrayOrNull`.get(key);
   return var2 as? TomlArray;
}

public fun TomlTable.getTable(key: Any?): TomlTable {
   val var10000: TomlTable = getTableOrNull(`$this$getTable`, key);
   if (var10000 == null) {
      throw new IllegalStateException(("Cannot find a TomlTable with key $key").toString());
   } else {
      return var10000;
   }
}

public fun TomlTable.getTableOrNull(key: Any?): TomlTable? {
   val var2: TomlElement = `$this$getTableOrNull`.get(key);
   return var2 as? TomlTable;
}

internal fun TomlArray(arrayNode: ArrayNode): TomlArray {
   val children: java.util.List = arrayNode.getChildren();
   var var10000: TomlArray;
   switch (children.size()) {
      case 0:
         var10000 = TomlArray.Companion.getEmpty();
         break;
      case 1:
         var10000 = new TomlArray(CollectionsKt.listOf(toTomlElement(children.get(0) as KeyNode)), arrayNode.getAnnotations());
         break;
      default:
         val `$this$map$iv`: java.lang.Iterable = children;
         val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(children, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$iv`.add(TomlTable(`item$iv$iv` as KeyNode));
         }

         var10000 = new TomlArray(`destination$iv$iv` as MutableList<TomlElement>, arrayNode.getAnnotations());
   }

   return var10000;
}

internal fun TomlTable(keyNode: KeyNode): TomlTable {
   val children: java.util.Map = keyNode.getChildren();
   val size: Int = children.size();
   var var10000: TomlTable;
   switch (size) {
      case 0:
         var10000 = TomlTable.Companion.getEmpty();
         break;
      case 1:
         val var11: Entry = CollectionsKt.first(children.entrySet());
         var10000 = new TomlTable(
            MapsKt.mapOf(TuplesKt.to(var11.getKey() as java.lang.String, toTomlElement(var11.getValue() as TreeNode))), keyNode.getAnnotations()
         );
         break;
      default:
         val k: java.util.Map = MapsKt.createMapBuilder(size);
         val `$this$TomlTable_u24lambda_u2438`: java.util.Map = k;

         for (Entry var8 : children.entrySet()) {
            `$this$TomlTable_u24lambda_u2438`.put(var8.getKey() as java.lang.String, toTomlElement(var8.getValue() as TreeNode));
         }

         return new TomlTable(MapsKt.build(k), keyNode.getAnnotations());
   }

   return var10000;
}

private fun TreeNode.toTomlElement(): TomlElement {
   val var10000: TomlElement;
   if (`$this$toTomlElement` is KeyNode) {
      var10000 = TomlTable(`$this$toTomlElement` as KeyNode);
   } else if (`$this$toTomlElement` is ArrayNode) {
      var10000 = TomlArray(`$this$toTomlElement` as ArrayNode);
   } else {
      if (`$this$toTomlElement` !is ValueNode) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = (`$this$toTomlElement` as ValueNode).getElement();
   }

   return var10000;
}

private tailrec fun TomlTable.getByPathRecursively(keys: Array<out Any?>, depth: Int): TomlElement? {
   while (true) {
      val value: TomlElement = `$this$getByPathRecursively`.get(keys[depth]);
      val var10000: TomlElement;
      if (depth == ArraysKt.getLastIndex(keys)) {
         var10000 = value;
      } else {
         if (value is TomlTable) {
            val var5: TomlTable = value as TomlTable;
            val var7: Int = depth + 1;
            `$this$getByPathRecursively` = var5;
            keys = keys;
            depth = var7;
            continue;
         }

         var10000 = if (value is TomlArray) getByPathRecursively(value as TomlArray, keys, depth + 1) else null;
      }

      return var10000;
   }
}

private tailrec fun TomlArray.getByPathRecursively(keys: Array<out Any?>, depth: Int): TomlElement? {
   while (true) {
      var value: Any = keys[depth];
      val var10000: Int;
      if (keys[depth] is Int) {
         var10000 = (value as java.lang.Number).intValue();
      } else {
         if (value !is java.lang.String) {
            throw new IllegalArgumentException("Expect integer key when accessing TomlArray, but found $value");
         }

         var10000 = Integer.parseInt(value as java.lang.String);
      }

      value = `$this$getByPathRecursively`.get(var10000);
      val var10: TomlElement;
      if (depth == ArraysKt.getLastIndex(keys)) {
         var10 = (TomlElement)value;
      } else if (value is TomlTable) {
         var10 = getByPathRecursively(value as TomlTable, keys, depth + 1);
      } else {
         if (value is TomlArray) {
            val message: TomlArray = value as TomlArray;
            val var7: Int = depth + 1;
            `$this$getByPathRecursively` = message;
            keys = keys;
            depth = var7;
            continue;
         }

         var10 = null;
      }

      return var10;
   }
}

internal fun Any?.toTomlKey(): String {
   val var10000: java.lang.String;
   if (`$this$toTomlKey` !is java.lang.Boolean
      && `$this$toTomlKey` !is java.lang.Number
      && `$this$toTomlKey` !is UByte
      && `$this$toTomlKey` !is UShort
      && `$this$toTomlKey` !is UInt
      && `$this$toTomlKey` !is ULong
      && `$this$toTomlKey` !is Character) {
      if (`$this$toTomlKey` !is java.lang.String) {
         TomlSerializationExceptionsKt.throwNonPrimitiveKey(`$this$toTomlKey`);
         throw new KotlinNothingValueException();
      }

      var10000 = `$this$toTomlKey` as java.lang.String;
   } else {
      var10000 = `$this$toTomlKey`.toString();
   }

   return var10000;
}

internal fun Any?.toTomlElement(): TomlElement {
   val var10000: TomlElement;
   if (`$this$toTomlElement` == null) {
      var10000 = TomlNull.INSTANCE;
   } else if (`$this$toTomlElement` is TomlElement) {
      var10000 = `$this$toTomlElement` as TomlElement;
   } else if (`$this$toTomlElement` is java.lang.Boolean) {
      var10000 = TomlLiteral(`$this$toTomlElement` as java.lang.Boolean);
   } else if (`$this$toTomlElement` is java.lang.Byte) {
      var10000 = TomlLiteral((`$this$toTomlElement` as java.lang.Number).byteValue());
   } else if (`$this$toTomlElement` is java.lang.Short) {
      var10000 = TomlLiteral((`$this$toTomlElement` as java.lang.Number).shortValue());
   } else if (`$this$toTomlElement` is Int) {
      var10000 = TomlLiteral((`$this$toTomlElement` as java.lang.Number).intValue());
   } else if (`$this$toTomlElement` is java.lang.Long) {
      var10000 = TomlLiteral((`$this$toTomlElement` as java.lang.Number).longValue());
   } else if (`$this$toTomlElement` is UByte) {
      var10000 = TomlLiteral-7apg3OU((`$this$toTomlElement` as UByte).unbox-impl());
   } else if (`$this$toTomlElement` is UShort) {
      var10000 = TomlLiteral-xj2QHRw((`$this$toTomlElement` as UShort).unbox-impl());
   } else if (`$this$toTomlElement` is UInt) {
      var10000 = TomlLiteral-WZ4Q5Ns((`$this$toTomlElement` as UInt).unbox-impl());
   } else if (`$this$toTomlElement` is ULong) {
      var10000 = TomlLiteral-VKZWuLQ((`$this$toTomlElement` as ULong).unbox-impl());
   } else if (`$this$toTomlElement` is java.lang.Float) {
      var10000 = TomlLiteral((`$this$toTomlElement` as java.lang.Number).floatValue());
   } else if (`$this$toTomlElement` is java.lang.Double) {
      var10000 = TomlLiteral((`$this$toTomlElement` as java.lang.Number).doubleValue());
   } else if (`$this$toTomlElement` is Character) {
      var10000 = TomlLiteral(`$this$toTomlElement` as Character);
   } else if (`$this$toTomlElement` is java.lang.String) {
      var10000 = TomlLiteral(`$this$toTomlElement` as java.lang.String);
   } else if (`$this$toTomlElement` is LocalDateTime) {
      var10000 = TomlLiteral(`$this$toTomlElement` as LocalDateTime);
   } else if (`$this$toTomlElement` is OffsetDateTime) {
      var10000 = TomlLiteral(`$this$toTomlElement` as OffsetDateTime);
   } else if (`$this$toTomlElement` is LocalDate) {
      var10000 = TomlLiteral(`$this$toTomlElement` as LocalDate);
   } else if (`$this$toTomlElement` is LocalTime) {
      var10000 = TomlLiteral(`$this$toTomlElement` as LocalTime);
   } else if (`$this$toTomlElement` is BooleanArray) {
      var10000 = TomlArray(ArraysKt.asIterable(`$this$toTomlElement` as BooleanArray));
   } else if (`$this$toTomlElement` is ByteArray) {
      var10000 = TomlArray(ArraysKt.asIterable(`$this$toTomlElement` as ByteArray));
   } else if (`$this$toTomlElement` is ShortArray) {
      var10000 = TomlArray(ArraysKt.asIterable(`$this$toTomlElement` as ShortArray));
   } else if (`$this$toTomlElement` is IntArray) {
      var10000 = TomlArray(ArraysKt.asIterable(`$this$toTomlElement` as IntArray));
   } else if (`$this$toTomlElement` is LongArray) {
      var10000 = TomlArray(ArraysKt.asIterable(`$this$toTomlElement` as LongArray));
   } else if (`$this$toTomlElement` is UByteArray) {
      var10000 = TomlArray(`$this$toTomlElement` as MutableIterable<*>);
   } else if (`$this$toTomlElement` is UShortArray) {
      var10000 = TomlArray(`$this$toTomlElement` as MutableIterable<*>);
   } else if (`$this$toTomlElement` is UIntArray) {
      var10000 = TomlArray(`$this$toTomlElement` as MutableIterable<*>);
   } else if (`$this$toTomlElement` is ULongArray) {
      var10000 = TomlArray(`$this$toTomlElement` as MutableIterable<*>);
   } else if (`$this$toTomlElement` is FloatArray) {
      var10000 = TomlArray(ArraysKt.asIterable(`$this$toTomlElement` as FloatArray));
   } else if (`$this$toTomlElement` is DoubleArray) {
      var10000 = TomlArray(ArraysKt.asIterable(`$this$toTomlElement` as DoubleArray));
   } else if (`$this$toTomlElement` is CharArray) {
      var10000 = TomlArray(ArraysKt.asIterable(`$this$toTomlElement` as CharArray));
   } else if (`$this$toTomlElement` is Array<Any>) {
      var10000 = TomlArray(ArraysKt.asIterable(`$this$toTomlElement` as Array<Any>));
   } else if (`$this$toTomlElement` is java.lang.Iterable) {
      var10000 = TomlArray(`$this$toTomlElement` as MutableIterable<*>);
   } else {
      if (`$this$toTomlElement` !is java.util.Map) {
         throw new IllegalStateException(("Unsupported class: ${(`$this$toTomlElement`.getClass()::class).getSimpleName()}").toString());
      }

      var10000 = TomlTable(`$this$toTomlElement` as MutableMap<*, *>);
   }

   return var10000;
}

private fun TomlElement.failConversion(target: String): String {
   return "Cannot convert ${(`$this$failConversion`.getClass()::class).getSimpleName()} to $target";
}
