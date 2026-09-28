@file:SourceDebugExtension(["SMAP\nEnums.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Enums.kt\nkotlinx/serialization/internal/EnumsKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,148:1\n13537#2,2:149\n13472#2,2:151\n13539#2:153\n13472#2,2:154\n13537#2,2:156\n13472#2,2:158\n13539#2:160\n*S KotlinDebug\n*F\n+ 1 Enums.kt\nkotlinx/serialization/internal/EnumsKt\n*L\n68#1:149,2\n71#1:151,2\n68#1:153\n88#1:154,2\n91#1:156,2\n94#1:158,2\n91#1:160\n*E\n"])

package kotlinx.serialization.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer

@PublishedApi
internal fun <T : Enum<T>> createSimpleEnumSerializer(serialName: String, values: Array<T>): KSerializer<T> {
   return new EnumSerializer(serialName, values);
}

@PublishedApi
internal fun <T : Enum<T>> createMarkedEnumSerializer(serialName: String, values: Array<T>, names: Array<String?>, annotations: Array<Array<Annotation>?>): KSerializer<
      T
   > {
   val descriptor: EnumDescriptor = new EnumDescriptor(serialName, values.length);
   var `index$iv`: Int = 0;

   for (Object item$iv : values) {
      val i: Int = `index$iv`++;
      var var22: java.lang.String = ArraysKt.getOrNull(names, i);
      if (var22 == null) {
         var22 = `item$iv`.name();
      }

      PluginGeneratedSerialDescriptor.addElement$default(descriptor, var22, false, 2, null);
      if (ArraysKt.getOrNull(annotations as Array<Any>, i) != null) {
         val `$this$forEach$iv`: Any;
         for (Object element$iv : $this$forEach$iv) {
            descriptor.pushAnnotation((java.lang.annotation.Annotation)`element$iv`);
         }
      }
   }

   return new EnumSerializer(serialName, values, descriptor);
}

@PublishedApi
internal fun <T : Enum<T>> createAnnotatedEnumSerializer(
   serialName: String,
   values: Array<T>,
   names: Array<String?>,
   entryAnnotations: Array<Array<Annotation>?>,
   classAnnotations: Array<Annotation>?
): KSerializer<T> {
   val descriptor: EnumDescriptor = new EnumDescriptor(serialName, values.length);
   if (classAnnotations != null) {
      for (Object element$iv : classAnnotations) {
         descriptor.pushClassAnnotation((java.lang.annotation.Annotation)`element$iv`);
      }
   }

   var var25: Int = 0;

   for (Object item$iv : values) {
      val i: Int = var25++;
      var var28: java.lang.String = ArraysKt.getOrNull(names, i);
      if (var28 == null) {
         var28 = `item$iv`.name();
      }

      PluginGeneratedSerialDescriptor.addElement$default(descriptor, var28, false, 2, null);
      if (ArraysKt.getOrNull(entryAnnotations as Array<Any>, i) != null) {
         val `$this$forEach$iv`: Any;
         for (Object element$iv : $this$forEach$iv) {
            descriptor.pushAnnotation((java.lang.annotation.Annotation)`element$iv`);
         }
      }
   }

   return new EnumSerializer(serialName, values, descriptor);
}
