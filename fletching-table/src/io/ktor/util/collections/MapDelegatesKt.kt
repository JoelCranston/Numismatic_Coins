@file:SourceDebugExtension(["SMAP\nMapDelegates.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MapDelegates.kt\nio/ktor/util/collections/MapDelegatesKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,102:1\n1#2:103\n*E\n"])

package io.ktor.util.collections

import io.ktor.util.collections.MapDelegatesKt.asBoolean.1
import io.ktor.util.collections.MapDelegatesKt.asBoolean.2
import io.ktor.utils.io.InternalAPI
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KProperty

@InternalAPI
public operator fun String.getValue(thisRef: StringMap, property: KProperty<*>): String? {
   return thisRef.get(`$this$getValue`);
}

@InternalAPI
public operator fun String.setValue(thisRef: StringMap, property: KProperty<*>, value: String?) {
   if (value == null) {
      thisRef.remove(`$this$setValue`);
   } else {
      thisRef.set(`$this$setValue`, value);
   }
}

@InternalAPI
public operator fun <T> SerializedMapValue<Any>.getValue(thisRef: StringMap, property: KProperty<*>): Any? {
   val var10000: java.lang.String = thisRef.get(`$this$getValue`.getKey$ktor_utils());
   return (T)(if (var10000 != null) `$this$getValue`.getDeserialize$ktor_utils().invoke(var10000) else null);
}

@InternalAPI
public operator fun <T> SerializedMapValue<Any>.setValue(thisRef: StringMap, property: KProperty<*>, value: Any?) {
   val serializedValue: java.lang.String = if (value != null) `$this$setValue`.getSerialize$ktor_utils().invoke(value) as java.lang.String else null;
   if (serializedValue == null) {
      thisRef.remove(`$this$setValue`.getKey$ktor_utils());
   } else {
      thisRef.set(`$this$setValue`.getKey$ktor_utils(), serializedValue);
   }
}

@InternalAPI
public fun String.asBoolean(): SerializedMapValue<Boolean> {
   return new SerializedMapValue<>(`$this$asBoolean`, 1.INSTANCE, 2.INSTANCE);
}

@InternalAPI
public fun String.asPresenceBoolean(): SerializedMapValue<Boolean?> {
   return new SerializedMapValue<>(`$this$asPresenceBoolean`, MapDelegatesKt::asPresenceBoolean$lambda$0, MapDelegatesKt::asPresenceBoolean$lambda$1);
}

fun `asPresenceBoolean$lambda$0`(bool: java.lang.Boolean): java.lang.String {
   return if (!(bool == false)) "" else null;
}

fun `asPresenceBoolean$lambda$1`(it: java.lang.String): java.lang.Boolean {
   return true;
}
