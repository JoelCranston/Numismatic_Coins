package io.ktor.util.converters

import io.ktor.util.reflect.TypeInfo
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection

@SourceDebugExtension(["SMAP\nConversionService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConversionService.kt\nio/ktor/util/converters/DefaultConversionService\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,127:1\n1374#2:128\n1460#2,5:129\n1563#2:134\n1634#2,3:135\n*S KotlinDebug\n*F\n+ 1 ConversionService.kt\nio/ktor/util/converters/DefaultConversionService\n*L\n46#1:128\n46#1:129,5\n73#1:134\n73#1:135,3\n*E\n"])
public object DefaultConversionService : ConversionService {
   public override fun toValues(value: Any?): List<String> {
      if (value == null) {
         return CollectionsKt.emptyList();
      } else {
         val converted: java.util.List = ConversionServiceJvmKt.platformDefaultToValues(value);
         if (converted != null) {
            return converted;
         } else {
            val var10000: java.util.List;
            if (value is java.lang.Iterable) {
               val klass: java.lang.Iterable = value as java.lang.Iterable;
               val `destination$iv$iv`: java.util.Collection = new ArrayList();

               for (Object element$iv$iv : $this$flatMap$iv) {
                  CollectionsKt.addAll(`destination$iv$iv`, INSTANCE.toValues(`element$iv$iv`));
               }

               var10000 = `destination$iv$iv` as java.util.List;
            } else {
               val var12: KClass = value.getClass()::class;
               if (!(var12 == Int::class)
                  && !(var12 == java.lang.Float::class)
                  && !(var12 == java.lang.Double::class)
                  && !(var12 == java.lang.Long::class)
                  && !(var12 == java.lang.Short::class)
                  && !(var12 == Character::class)
                  && !(var12 == java.lang.Boolean::class)
                  && !(var12 == java.lang.String::class)) {
                  throw new DataConversionException("Class $var12 is not supported in default data conversion service");
               }

               var10000 = CollectionsKt.listOf(value.toString());
            }

            return var10000;
         }
      }
   }

   public override fun fromValues(values: List<String>, type: TypeInfo): Any? {
      if (values.isEmpty()) {
         return null;
      } else {
         if (type.getType() == java.util.List::class || type.getType() == java.util.List::class) {
            var var18: KClassifier;
            label46: {
               val var10000: KType = type.getKotlinType();
               if (var10000 != null) {
                  val var15: java.util.List = var10000.getArguments();
                  if (var15 != null) {
                     val var16: KTypeProjection = CollectionsKt.single(var15);
                     if (var16 != null) {
                        val var17: KType = var16.getType();
                        if (var17 != null) {
                           var18 = var17.getClassifier();
                           break label46;
                        }
                     }
                  }
               }

               var18 = null;
            }

            val argumentType: KClass = var18 as? KClass;
            if ((var18 as? KClass) != null) {
               val var14: java.lang.Iterable = values;
               val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));

               for (Object item$iv$iv : $this$map$iv) {
                  `destination$iv$iv`.add(INSTANCE.fromValue(`item$iv$iv` as java.lang.String, argumentType));
               }

               return `destination$iv$iv` as java.util.List;
            }
         }

         if (values.isEmpty()) {
            throw new DataConversionException("There are no values when trying to construct single value $type");
         } else if (values.size() > 1) {
            throw new DataConversionException("There are multiple values when trying to construct single value $type");
         } else {
            return this.fromValue(CollectionsKt.single(values), type.getType());
         }
      }
   }

   public fun fromValue(value: String, klass: KClass<*>): Any {
      val converted: Any = this.convertPrimitives(klass, value);
      if (converted != null) {
         return converted;
      } else {
         val platformConverted: Any = ConversionServiceJvmKt.platformDefaultFromValues(value, klass);
         if (platformConverted != null) {
            return platformConverted;
         } else {
            this.throwConversionException(klass.toString());
            throw new KotlinNothingValueException();
         }
      }
   }

   private fun convertPrimitives(klass: KClass<*>, value: String): Any? {
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
                                    if (klass == Character::class)
                                       StringsKt.single(value)
                                       else
                                       (
                                          if (klass == java.lang.Boolean::class)
                                             java.lang.Boolean.parseBoolean(value)
                                             else
                                             (if (klass == java.lang.String::class) value else null)
                                       )
                                 )
                           )
                     )
               )
         );
   }

   private fun throwConversionException(typeName: String): Nothing {
      throw new DataConversionException("Type $typeName is not supported in default data conversion service");
   }
}
