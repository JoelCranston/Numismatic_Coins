@file:SourceDebugExtension(["SMAP\nMaps.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Maps.kt\ndev/kikugie/commons/collections/MapsKt\n+ 2 ControlFlow.kt\ndev/kikugie/commons/ControlFlowKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,59:1\n22#1:62\n22#1:64\n22#1:66\n22#1:68\n31#1:70\n22#1:71\n31#1:73\n22#1:74\n36#1:75\n22#1:76\n36#1:78\n22#1:79\n41#1:80\n22#1:81\n41#1:83\n22#1:84\n23#2:60\n1#3:61\n1#3:63\n1#3:65\n1#3:67\n1#3:69\n1#3:72\n1#3:77\n1#3:82\n*S KotlinDebug\n*F\n+ 1 Maps.kt\ndev/kikugie/commons/collections/MapsKt\n*L\n26#1:62\n31#1:64\n36#1:66\n41#1:68\n47#1:70\n47#1:71\n47#1:73\n47#1:74\n53#1:75\n53#1:76\n53#1:78\n53#1:79\n59#1:80\n59#1:81\n59#1:83\n59#1:84\n14#1:60\n26#1:63\n31#1:65\n36#1:67\n41#1:69\n47#1:72\n53#1:77\n59#1:82\n*E\n"])

package dev.kikugie.commons.collections

import dev.kikugie.commons.collections.MapsKt.getAs.3
import dev.kikugie.commons.collections.MapsKt.getAs.5
import dev.kikugie.commons.collections.MapsKt.getOrThrow.1
import java.util.NoSuchElementException
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlin.reflect.KClasses

@PublishedApi
internal const val MISSING_KEY: String = "Key is not present in the map"

public inline fun <K, V, M : Map<K, V>> M.ifNotEmpty(action: (M) -> Unit): M {
   val var10000: java.util.Map;
   if (!`$this$ifNotEmpty`.isEmpty()) {
      action.invoke(`$this$ifNotEmpty`);
      var10000 = `$this$ifNotEmpty`;
   } else {
      var10000 = `$this$ifNotEmpty`;
   }

   return (M)var10000;
}

public inline fun <K, V> Map<K, V>.getOrDefault(key: K, default: V): V {
   var var10000: Any = `$this$getOrDefault`.get(key);
   if (var10000 == null) {
      var10000 = var2;
   }

   return (V)var10000;
}

public inline fun <K, V> Map<K, V>.getOrThrow(key: K, message: (Map<K, V>, K) -> String = 1.INSTANCE as Function2): V {
   val var10000: Any = `$this$getOrThrow`.get(key);
   if (var10000 == null) {
      throw new NoSuchElementException(message.invoke(`$this$getOrThrow`, key) as java.lang.String);
   } else {
      return (V)var10000;
   }
}

@JvmSynthetic
fun java.util.Map.`getOrThrow$default`(key: Any, message: Function2, `$i$f$getOrThrow`: Int, var4: Any): Any {
   if ((`$i$f$getOrThrow` and 2) != 0) {
      message = 1.INSTANCE;
   }

   val var10000: Any = `$this$getOrThrow_u24default`.get(key);
   if (var10000 == null) {
      throw new NoSuchElementException(message.invoke(`$this$getOrThrow_u24default`, key) as java.lang.String);
   } else {
      return var10000;
   }
}

public inline fun <K, V> Map<K, V>.getResult(key: K, message: (Map<K, V>, K) -> String = ...): Result<V> {
   val var4: java.util.Map = `$this$getResult`;

   var `$this$getResult_u24lambda_u242`: Any;
   try {
      val var10000: Any = var4.get(key);
      if (var10000 == null) {
         throw new NoSuchElementException(message.invoke(var4, key) as java.lang.String);
      }

      `$this$getResult_u24lambda_u242` = Result.constructor-impl(var10000);
   } catch (var11: java.lang.Throwable) {
      `$this$getResult_u24lambda_u242` = Result.constructor-impl(ResultKt.createFailure(var11));
   }

   return `$this$getResult_u24lambda_u242`;
}

@JvmSynthetic
fun java.util.Map.`getResult$default`(key: Any, message: Function2, `$i$f$getResult`: Int, var4: Any): Any {
   if ((`$i$f$getResult` and 2) != 0) {
      message = dev.kikugie.commons.collections.MapsKt.getResult.1.INSTANCE;
   }

   var4 = `$this$getResult_u24default`;

   var `$this$getResult_u24lambda_u242`: Any;
   try {
      val var10000: Any = var4.get(key);
      if (var10000 == null) {
         throw new NoSuchElementException(message.invoke(var4, key) as java.lang.String);
      }

      `$this$getResult_u24lambda_u242` = Result.constructor-impl(var10000);
   } catch (var11: java.lang.Throwable) {
      `$this$getResult_u24lambda_u242` = Result.constructor-impl(ResultKt.createFailure(var11));
   }

   return `$this$getResult_u24lambda_u242`;
}

public inline fun <K, V> Map<K, *>.getAs(
   key: K,
   type: Class<out V>,
   message: (Map<K, *>, K) -> String = dev.kikugie.commons.collections.MapsKt.getAs.1.INSTANCE as Function2
): V {
   val var10000: Any = `$this$getAs`.get(key);
   if (var10000 == null) {
      throw new NoSuchElementException(message.invoke(`$this$getAs`, key) as java.lang.String);
   } else {
      return (V)type.cast(var10000);
   }
}

@JvmSynthetic
fun java.util.Map.`getAs$default`(key: Any, type: Class, message: Function2, `$i$f$getAs`: Int, `$this$getOrThrow$iv`: Any): Any {
   if ((`$i$f$getAs` and 4) != 0) {
      message = dev.kikugie.commons.collections.MapsKt.getAs.1.INSTANCE;
   }

   val var10000: Any = `$this$getAs_u24default`.get(key);
   if (var10000 == null) {
      throw new NoSuchElementException(message.invoke(`$this$getAs_u24default`, key) as java.lang.String);
   } else {
      return type.cast(var10000);
   }
}

public inline fun <K, V> Map<K, *>.getAs(key: K, type: KClass<out V>, message: (Map<K, *>, K) -> String = 3.INSTANCE as Function2): V {
   val var10000: Any = `$this$getAs`.get(key);
   if (var10000 == null) {
      throw new NoSuchElementException(message.invoke(`$this$getAs`, key) as java.lang.String);
   } else {
      return (V)KClasses.cast(type, var10000);
   }
}

@JvmSynthetic
fun java.util.Map.`getAs$default`(key: Any, type: KClass, message: Function2, `$i$f$getAs`: Int, `$this$getOrThrow$iv`: Any): Any {
   if ((`$i$f$getAs` and 4) != 0) {
      message = 3.INSTANCE;
   }

   val var10000: Any = `$this$getAs_u24default`.get(key);
   if (var10000 == null) {
      throw new NoSuchElementException(message.invoke(`$this$getAs_u24default`, key) as java.lang.String);
   } else {
      return KClasses.cast(type, var10000);
   }
}

@JvmSynthetic
public inline fun <K, reified V> Map<K, *>.getAs(key: K, message: (Map<K, *>, K) -> String = 5.INSTANCE as Function2): V {
   val var10000: Any = `$this$getAs`.get(key);
   if (var10000 == null) {
      throw new NoSuchElementException(message.invoke(`$this$getAs`, key) as java.lang.String);
   } else {
      Intrinsics.reifiedOperationMarker(1, "V");
      return (V)(var10000 as Any);
   }
}

@JvmSynthetic
fun java.util.Map.`getAs$default`(key: Any, message: Function2, `$i$f$getAs`: Int, `$this$getOrThrow$iv`: Any): Any {
   if ((`$i$f$getAs` and 2) != 0) {
      message = 5.INSTANCE;
   }

   val var10000: Any = `$this$getAs_u24default`.get(key);
   if (var10000 == null) {
      throw new NoSuchElementException(message.invoke(`$this$getAs_u24default`, key) as java.lang.String);
   } else {
      Intrinsics.reifiedOperationMarker(1, "V");
      return var10000;
   }
}

public inline fun <K, V> Map<K, *>.getAsResult(key: K, type: Class<out V>, message: (Map<K, *>, K) -> String = ...): Result<V> {
   val var5: java.util.Map = `$this$getAsResult`;

   var `$this$getAsResult_u24lambda_u244`: Any;
   try {
      val var10000: Any = var5.get(key);
      if (var10000 == null) {
         throw new NoSuchElementException(message.invoke(var5, key) as java.lang.String);
      }

      `$this$getAsResult_u24lambda_u244` = Result.constructor-impl(type.cast(var10000));
   } catch (var16: java.lang.Throwable) {
      `$this$getAsResult_u24lambda_u244` = Result.constructor-impl(ResultKt.createFailure(var16));
   }

   return `$this$getAsResult_u24lambda_u244`;
}

@JvmSynthetic
fun java.util.Map.`getAsResult$default`(key: Any, type: Class, message: Function2, `$i$f$getAsResult`: Int, var5: Any): Any {
   if ((`$i$f$getAsResult` and 4) != 0) {
      message = dev.kikugie.commons.collections.MapsKt.getAsResult.1.INSTANCE;
   }

   var5 = `$this$getAsResult_u24default`;

   var `$this$getAsResult_u24lambda_u244`: Any;
   try {
      val var10000: Any = var5.get(key);
      if (var10000 == null) {
         throw new NoSuchElementException(message.invoke(var5, key) as java.lang.String);
      }

      `$this$getAsResult_u24lambda_u244` = Result.constructor-impl(type.cast(var10000));
   } catch (var16: java.lang.Throwable) {
      `$this$getAsResult_u24lambda_u244` = Result.constructor-impl(ResultKt.createFailure(var16));
   }

   return `$this$getAsResult_u24lambda_u244`;
}

public inline fun <K, V> Map<K, *>.getAsResult(key: K, type: KClass<out V>, message: (Map<K, *>, K) -> String = ...): Result<V> {
   val var5: java.util.Map = `$this$getAsResult`;

   var `$this$getAsResult_u24lambda_u245`: Any;
   try {
      val var10000: Any = var5.get(key);
      if (var10000 == null) {
         throw new NoSuchElementException(message.invoke(var5, key) as java.lang.String);
      }

      `$this$getAsResult_u24lambda_u245` = Result.constructor-impl(KClasses.cast(type, var10000));
   } catch (var16: java.lang.Throwable) {
      `$this$getAsResult_u24lambda_u245` = Result.constructor-impl(ResultKt.createFailure(var16));
   }

   return `$this$getAsResult_u24lambda_u245`;
}

@JvmSynthetic
fun java.util.Map.`getAsResult$default`(key: Any, type: KClass, message: Function2, `$i$f$getAsResult`: Int, var5: Any): Any {
   if ((`$i$f$getAsResult` and 4) != 0) {
      message = dev.kikugie.commons.collections.MapsKt.getAsResult.3.INSTANCE;
   }

   var5 = `$this$getAsResult_u24default`;

   var `$this$getAsResult_u24lambda_u245`: Any;
   try {
      val var10000: Any = var5.get(key);
      if (var10000 == null) {
         throw new NoSuchElementException(message.invoke(var5, key) as java.lang.String);
      }

      `$this$getAsResult_u24lambda_u245` = Result.constructor-impl(KClasses.cast(type, var10000));
   } catch (var16: java.lang.Throwable) {
      `$this$getAsResult_u24lambda_u245` = Result.constructor-impl(ResultKt.createFailure(var16));
   }

   return `$this$getAsResult_u24lambda_u245`;
}

@JvmSynthetic
public inline fun <K, reified V> Map<K, *>.getAsResult(key: K, message: (Map<K, *>, K) -> String = ...): Result<V> {
   val var4: java.util.Map = `$this$getAsResult`;

   var `$this$getAsResult_u24lambda_u246`: java.util.Map;
   try {
      `$this$getAsResult_u24lambda_u246` = var4;
      val var10000: Any = var4.get(key);
      if (var10000 == null) {
         throw new NoSuchElementException(message.invoke(`$this$getAsResult_u24lambda_u246`, key) as java.lang.String);
      }

      Intrinsics.reifiedOperationMarker(1, "V");
      `$this$getAsResult_u24lambda_u246` = (java.util.Map)Result.constructor-impl(var10000);
   } catch (var14: java.lang.Throwable) {
      `$this$getAsResult_u24lambda_u246` = (java.util.Map)Result.constructor-impl(ResultKt.createFailure(var14));
   }

   return `$this$getAsResult_u24lambda_u246`;
}

@JvmSynthetic
fun java.util.Map.`getAsResult$default`(key: Any, message: Function2, `$i$f$getAsResult`: Int, var4: Any): Any {
   if ((`$i$f$getAsResult` and 2) != 0) {
      message = dev.kikugie.commons.collections.MapsKt.getAsResult.5.INSTANCE;
   }

   var4 = `$this$getAsResult_u24default`;

   var `$this$getAsResult_u24lambda_u246`: java.util.Map;
   try {
      `$this$getAsResult_u24lambda_u246` = var4;
      val var10000: Any = var4.get(key);
      if (var10000 == null) {
         throw new NoSuchElementException(message.invoke(`$this$getAsResult_u24lambda_u246`, key) as java.lang.String);
      }

      Intrinsics.reifiedOperationMarker(1, "V");
      `$this$getAsResult_u24lambda_u246` = (java.util.Map)Result.constructor-impl(var10000);
   } catch (var14: java.lang.Throwable) {
      `$this$getAsResult_u24lambda_u246` = (java.util.Map)Result.constructor-impl(ResultKt.createFailure(var14));
   }

   return `$this$getAsResult_u24lambda_u246`;
}
