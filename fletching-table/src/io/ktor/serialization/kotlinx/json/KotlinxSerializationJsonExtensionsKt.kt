package io.ktor.serialization.kotlinx.json

import io.ktor.util.reflect.TypeInfo
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KType

internal fun TypeInfo.argumentTypeInfo(): TypeInfo {
   var var10000: KType = `$this$argumentTypeInfo`.getKotlinType();
   var10000 = var10000.getArguments().get(0).getType();
   val var10002: KClassifier = var10000.getClassifier();
   return new TypeInfo(var10002 as KClass<?>, var10000);
}
