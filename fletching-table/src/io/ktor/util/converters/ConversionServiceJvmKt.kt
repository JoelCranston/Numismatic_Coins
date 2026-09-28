@file:SourceDebugExtension(["SMAP\nConversionServiceJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConversionServiceJvm.kt\nio/ktor/util/converters/ConversionServiceJvmKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,61:1\n1400#2,2:62\n*S KotlinDebug\n*F\n+ 1 ConversionServiceJvm.kt\nio/ktor/util/converters/ConversionServiceJvmKt\n*L\n18#1:62,2\n*E\n"])

package io.ktor.util.converters

import java.math.BigDecimal
import java.math.BigInteger
import java.util.UUID
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass

internal fun platformDefaultFromValues(value: String, klass: KClass<*>): Any? {
   val converted: Any = convertSimpleTypes(value, klass);
   if (converted != null) {
      return converted;
   } else if (!JvmClassMappingKt.getJavaClass(klass).isEnum()) {
      return null;
   } else {
      var var10000: Array<Any> = JvmClassMappingKt.<Object>getJavaClass(klass).getEnumConstants();
      if (var10000 != null) {
         val `$this$firstOrNull$iv`: Array<Any> = var10000;
         var var5: Int = 0;
         val var6: Int = var10000.length;

         while (true) {
            if (var5 >= var6) {
               var10000 = null;
               break;
            }

            val `element$iv`: Any = `$this$firstOrNull$iv`[var5];
            if ((`element$iv` as java.lang.Enum).name() == value) {
               var10000 = (Object[])`element$iv`;
               break;
            }

            var5++;
         }

         if (var10000 != null) {
            return var10000;
         }
      }

      throw new DataConversionException("Value $value is not a enum member name of $klass");
   }
}

private fun convertSimpleTypes(value: String, klass: KClass<*>): Any? {
   return if (klass == Int::class)
      Integer.parseInt(value)
      else
      (
         if (klass == java.lang.Float::class)
            java.lang.Float.parseFloat(value)
            else
            (
               if (klass == java.lang.Double::class)
                  java.lang.Double.parseDouble(value)
                  else
                  (
                     if (klass == java.lang.Long::class)
                        java.lang.Long.parseLong(value)
                        else
                        (
                           if (klass == java.lang.Short::class)
                              java.lang.Short.parseShort(value)
                              else
                              (
                                 if (klass == java.lang.Boolean::class)
                                    java.lang.Boolean.parseBoolean(value)
                                    else
                                    (
                                       if (klass == java.lang.String::class)
                                          value
                                          else
                                          (
                                             if (klass == Character::class)
                                                value.charAt(0)
                                                else
                                                (
                                                   if (klass == BigDecimal::class)
                                                      new BigDecimal(value)
                                                      else
                                                      (
                                                         if (klass == BigInteger::class)
                                                            new BigInteger(value)
                                                            else
                                                            (if (klass == UUID::class) UUID.fromString(value) else null)
                                                      )
                                                )
                                          )
                                    )
                              )
                        )
                  )
            )
      );
}

internal fun platformDefaultToValues(value: Any): List<String>? {
   if (value is java.lang.Enum) {
      return CollectionsKt.listOf((value as java.lang.Enum).name());
   } else {
      return if (value is Int)
         CollectionsKt.listOf((value as Int).toString())
         else
         (
            if (value is java.lang.Float)
               CollectionsKt.listOf((value as java.lang.Float).toString())
               else
               (
                  if (value is java.lang.Double)
                     CollectionsKt.listOf((value as java.lang.Double).toString())
                     else
                     (
                        if (value is java.lang.Long)
                           CollectionsKt.listOf((value as java.lang.Long).toString())
                           else
                           (
                              if (value is java.lang.Boolean)
                                 CollectionsKt.listOf((value as java.lang.Boolean).toString())
                                 else
                                 (
                                    if (value is java.lang.Short)
                                       CollectionsKt.listOf((value as java.lang.Short).toString())
                                       else
                                       (
                                          if (value is java.lang.String)
                                             CollectionsKt.listOf((value as java.lang.String).toString())
                                             else
                                             (
                                                if (value is Character)
                                                   CollectionsKt.listOf((value as Character).toString())
                                                   else
                                                   (
                                                      if (value is BigDecimal)
                                                         CollectionsKt.listOf((value as BigDecimal).toString())
                                                         else
                                                         (
                                                            if (value is BigInteger)
                                                               CollectionsKt.listOf((value as BigInteger).toString())
                                                               else
                                                               (if (value is UUID) CollectionsKt.listOf((value as UUID).toString()) else null)
                                                         )
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
   }
}
