package io.ktor.util

import kotlin.reflect.KClass

internal fun createStackTraceElement(kClass: KClass<*>, methodName: String, fileName: String, lineNumber: Int): StackTraceElement {
   return new StackTraceElement(JvmClassMappingKt.getJavaClass(kClass).getName(), methodName, fileName, lineNumber);
}

/** @deprecated */
@JvmSynthetic
fun `CoroutineStackFrame$annotations`() {
}

/** @deprecated */
@JvmSynthetic
fun `StackTraceElement$annotations`() {
}
