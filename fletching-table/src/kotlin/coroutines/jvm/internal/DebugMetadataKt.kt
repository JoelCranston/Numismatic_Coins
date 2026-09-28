@file:SourceDebugExtension(["SMAP\nDebugMetadata.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DebugMetadata.kt\nkotlin/coroutines/jvm/internal/DebugMetadataKt\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,158:1\n37#2,2:159\n*S KotlinDebug\n*F\n+ 1 DebugMetadata.kt\nkotlin/coroutines/jvm/internal/DebugMetadataKt\n*L\n131#1:159,2\n*E\n"])

package kotlin.coroutines.jvm.internal

import java.lang.reflect.Field
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

private const val COROUTINES_DEBUG_METADATA_VERSION_1_3: Int = 1
private const val COROUTINES_DEBUG_METADATA_VERSION_2_2: Int = 2

@SinceKotlin(version = "1.3")
@JvmName(name = "getStackTraceElement")
@PublishedApi
internal fun BaseContinuationImpl.getStackTraceElementImpl(): StackTraceElement? {
   val var10000: DebugMetadata = getDebugMetadataAnnotation(`$this$getStackTraceElementImpl`);
   if (var10000 == null) {
      return null;
   } else if (var10000.v() < 1) {
      return null;
   } else {
      val label: Int = getLabel(`$this$getStackTraceElementImpl`);
      val lineNumber: Int = if (label < 0) -1 else var10000.l()[label];
      val moduleName: java.lang.String = ModuleNameRetriever.INSTANCE.getModuleName(`$this$getStackTraceElementImpl`);
      return new StackTraceElement(if (moduleName == null) var10000.c() else "$moduleName/${var10000.c()}", var10000.m(), var10000.f(), lineNumber);
   }
}

private fun BaseContinuationImpl.getDebugMetadataAnnotation(): DebugMetadata? {
   return `$this$getDebugMetadataAnnotation`.getClass().getAnnotation(DebugMetadata.class);
}

private fun BaseContinuationImpl.getLabel(): Int {
   var field: Int;
   try {
      val var5: Field = `$this$getLabel`.getClass().getDeclaredField("label");
      var5.setAccessible(true);
      val var3: Any = var5.get(`$this$getLabel`);
      field = (if ((var3 as? Int) != null) var3 as? Int else 0) - 1;
   } catch (var4: Exception) {
      field = -1;
   }

   return field;
}

@SinceKotlin(version = "1.3")
@JvmName(name = "getSpilledVariableFieldMapping")
@PublishedApi
internal fun BaseContinuationImpl.getSpilledVariableFieldMapping(): Array<String>? {
   val var10000: DebugMetadata = getDebugMetadataAnnotation(`$this$getSpilledVariableFieldMapping`);
   if (var10000 == null) {
      return null;
   } else {
      val debugMetadata: DebugMetadata = var10000;
      if (var10000.v() < 1) {
         return null;
      } else {
         val res: ArrayList = new ArrayList();
         val label: Int = getLabel(`$this$getSpilledVariableFieldMapping`);
         val `$this$toTypedArray$iv`: IntArray = var10000.i();
         var `$i$f$toTypedArray`: Int = 0;

         for (int thisCollection$iv = $this$toTypedArray$iv.length; $i$f$toTypedArray < thisCollection$iv; $i$f$toTypedArray++) {
            if (`$this$toTypedArray$iv`[`$i$f$toTypedArray`] == label) {
               res.add(debugMetadata.s()[`$i$f$toTypedArray`]);
               res.add(debugMetadata.n()[`$i$f$toTypedArray`]);
            }
         }

         return res.toArray(new java.lang.String[0]);
      }
   }
}

@SinceKotlin(version = "2.2")
@PublishedApi
internal fun BaseContinuationImpl.getNextLineNumber(): Int {
   val var10000: DebugMetadata = getDebugMetadataAnnotation(`$this$getNextLineNumber`);
   if (var10000 == null) {
      return -1;
   } else if (var10000.v() < 2) {
      return -1;
   } else {
      val label: Int = getLabel(`$this$getNextLineNumber`);
      if (label < 0) {
         return -1;
      } else {
         return if (label >= var10000.nl().length) -1 else var10000.nl()[label];
      }
   }
}
