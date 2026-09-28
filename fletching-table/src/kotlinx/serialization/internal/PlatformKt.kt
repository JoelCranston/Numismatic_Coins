@file:SourceDebugExtension(["SMAP\nPlatform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Platform.kt\nkotlinx/serialization/internal/PlatformKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,217:1\n211#1,6:254\n211#1,6:260\n211#1,6:266\n211#1,6:272\n211#1,6:278\n211#1,6:284\n211#1,6:290\n1#2:218\n3170#3,11:219\n1310#3,2:230\n3170#3,11:232\n3170#3,11:243\n*S KotlinDebug\n*F\n+ 1 Platform.kt\nkotlinx/serialization/internal/PlatformKt\n*L\n193#1:254,6\n197#1:260,6\n198#1:266,6\n199#1:272,6\n200#1:278,6\n203#1:284,6\n206#1:290,6\n73#1:219,11\n81#1:230,2\n151#1:232,11\n156#1:243,11\n*E\n"])

package kotlinx.serialization.internal

import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.ArrayList
import java.util.Arrays
import kotlin.jvm.internal.BooleanCompanionObject
import kotlin.jvm.internal.ByteCompanionObject
import kotlin.jvm.internal.CharCompanionObject
import kotlin.jvm.internal.DoubleCompanionObject
import kotlin.jvm.internal.FloatCompanionObject
import kotlin.jvm.internal.IntCompanionObject
import kotlin.jvm.internal.LongCompanionObject
import kotlin.jvm.internal.ShortCompanionObject
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.StringCompanionObject
import kotlin.reflect.KClass
import kotlin.time.Duration
import kotlin.time.Instant
import kotlin.uuid.Uuid
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.BuiltinSerializersKt

internal inline fun <T> Array<T>.getChecked(index: Int): T {
   return (T)`$this$getChecked`[index];
}

internal inline fun BooleanArray.getChecked(index: Int): Boolean {
   return `$this$getChecked`[index];
}

internal fun <T : Any> KClass<T>.isInterface(): Boolean {
   return JvmClassMappingKt.getJavaClass(`$this$isInterface`).isInterface();
}

internal fun <T : Any> KClass<T>.compiledSerializerImpl(): KSerializer<T>? {
   return constructSerializerForGivenTypeArgs(`$this$compiledSerializerImpl`);
}

internal fun <T : Any, E : T?> ArrayList<E>.toNativeArrayImpl(eClass: KClass<T>): Array<E> {
   val var10001: Any = java.lang.reflect.Array.newInstance(JvmClassMappingKt.getJavaClass(eClass), `$this$toNativeArrayImpl`.size());
   val var10000: Array<Any> = `$this$toNativeArrayImpl`.toArray(var10001 as Array<Any>);
   return (E[])var10000;
}

internal fun KClass<*>.platformSpecificSerializerNotRegistered(): Nothing {
   Platform_commonKt.serializerNotRegistered(`$this$platformSpecificSerializerNotRegistered`);
   throw new KotlinNothingValueException();
}

internal fun Class<*>.serializerNotRegistered(): Nothing {
   throw new SerializationException(Platform_commonKt.notRegisteredMessage(JvmClassMappingKt.getKotlinClass(`$this$serializerNotRegistered`)));
}

internal fun <T : Any> KClass<T>.constructSerializerForGivenTypeArgs(vararg args: KSerializer<Any?>): KSerializer<T>? {
   return constructSerializerForGivenTypeArgs(JvmClassMappingKt.getJavaClass(`$this$constructSerializerForGivenTypeArgs`), Arrays.copyOf(args, args.length));
}

internal fun <T : Any> Class<T>.constructSerializerForGivenTypeArgs(vararg args: KSerializer<Any?>): KSerializer<T>? {
   if (`$this$constructSerializerForGivenTypeArgs`.isEnum() && isNotAnnotated(`$this$constructSerializerForGivenTypeArgs`)) {
      return createEnumSerializer(`$this$constructSerializerForGivenTypeArgs`);
   } else {
      val serializer: KSerializer = invokeSerializerOnDefaultCompanion(`$this$constructSerializerForGivenTypeArgs`, Arrays.copyOf(args, args.length));
      if (serializer != null) {
         return serializer;
      } else {
         var fromNamedCompanion: KSerializer = findObjectSerializer(`$this$constructSerializerForGivenTypeArgs`);
         if (fromNamedCompanion != null) {
            return fromNamedCompanion;
         } else {
            fromNamedCompanion = findInNamedCompanion(`$this$constructSerializerForGivenTypeArgs`, Arrays.copyOf(args, args.length));
            if (fromNamedCompanion != null) {
               return fromNamedCompanion;
            } else {
               return if (isPolymorphicSerializer(`$this$constructSerializerForGivenTypeArgs`))
                  new PolymorphicSerializer(JvmClassMappingKt.getKotlinClass(`$this$constructSerializerForGivenTypeArgs`))
                  else
                  null;
            }
         }
      }
   }
}

private fun <T : Any> Class<T>.findInNamedCompanion(vararg args: KSerializer<Any?>): KSerializer<T>? {
   val namedCompanion: Any = findNamedCompanionByAnnotation(`$this$findInNamedCompanion`);
   if (namedCompanion != null) {
      val var3: KSerializer = invokeSerializerOnCompanion(namedCompanion, Arrays.copyOf(args, args.length));
      if (var3 != null) {
         return var3;
      }
   }

   var var15: KSerializer;
   try {
      var var10000: Array<Class> = `$this$findInNamedCompanion`.getDeclaredClasses();
      val `$this$singleOrNull$iv`: Array<Any> = var10000;
      var `single$iv`: Any = null;
      var `found$iv`: Boolean = false;
      var var9: Int = 0;
      val var10: Int = `$this$singleOrNull$iv`.length;

      while (true) {
         if (var9 >= var10) {
            var10000 = (Class[])(if (!`found$iv`) null else `single$iv`);
            break;
         }

         val `element$iv`: Any = `$this$singleOrNull$iv`[var9];
         if ((`$this$singleOrNull$iv`[var9] as Class).getSimpleName() == "$serializer") {
            if (`found$iv`) {
               var10000 = null;
               break;
            }

            `single$iv` = `element$iv`;
            `found$iv` = true;
         }

         var9++;
      }

      label37: {
         val e: Class = var10000 as Class;
         if (var10000 as Class != null) {
            val var17: Field = e.getField("INSTANCE");
            if (var17 != null) {
               var10000 = (Class[])var17.get(null);
               break label37;
            }
         }

         var10000 = null;
      }

      var15 = var10000 as? KSerializer;
   } catch (var14: NoSuchFieldException) {
      var15 = null;
   }

   return var15;
}

private fun <T : Any> Class<T>.findNamedCompanionByAnnotation(): Any? {
   var var10000: Array<Class> = `$this$findNamedCompanionByAnnotation`.getDeclaredClasses();
   val `$this$firstOrNull$iv`: Array<Any> = var10000;
   var var4: Int = 0;
   val var5: Int = `$this$firstOrNull$iv`.length;

   while (true) {
      if (var4 >= var5) {
         var10000 = null;
         break;
      }

      val `element$iv`: Any = `$this$firstOrNull$iv`[var4];
      if ((`$this$firstOrNull$iv`[var4] as Class).getAnnotation(NamedCompanion.class) != null) {
         var10000 = (Class[])`element$iv`;
         break;
      }

      var4++;
   }

   val var10: Class = var10000 as Class;
   if (var10000 as Class == null) {
      return null;
   } else {
      val var10001: java.lang.String = var10.getSimpleName();
      return companionOrNull(`$this$findNamedCompanionByAnnotation`, var10001);
   }
}

private fun <T : Any> Class<T>.isNotAnnotated(): Boolean {
   return `$this$isNotAnnotated`.getAnnotation(Serializable.class) == null && `$this$isNotAnnotated`.getAnnotation(Polymorphic.class) == null;
}

private fun <T : Any> Class<T>.isPolymorphicSerializer(): Boolean {
   if (`$this$isPolymorphicSerializer`.getAnnotation(Polymorphic.class) != null) {
      return true;
   } else {
      val serializable: Serializable = `$this$isPolymorphicSerializer`.getAnnotation(Serializable.class);
      return serializable != null && serializable.with()::class == PolymorphicSerializer::class;
   }
}

private fun <T : Any> invokeSerializerOnDefaultCompanion(jClass: Class<*>, vararg args: KSerializer<Any?>): KSerializer<T>? {
   val var10000: Any = companionOrNull(jClass, "Companion");
   return if (var10000 == null) null else invokeSerializerOnCompanion(var10000, Arrays.copyOf(args, args.length));
}

private fun <T : Any> invokeSerializerOnCompanion(companion: Any, vararg args: KSerializer<Any?>): KSerializer<T>? {
   var types: KSerializer;
   try {
      val var13: Array<Class>;
      if (args.length == 0) {
         var13 = new Class[0];
      } else {
         var e: Int = 0;
         val var11: Int = args.length;

         val var5: Array<Class>;
         for (var5 = new Class[args.length]; e < var11; e++) {
            var5[e] = KSerializer::class.java;
         }

         var13 = var5;
      }

      val var10: Any = companion.getClass()
         .getDeclaredMethod("serializer", Arrays.copyOf(var13, var13.length))
         .invoke(companion, Arrays.copyOf(args, args.length));
      types = var10 as? KSerializer;
   } catch (var7: NoSuchMethodException) {
      types = null;
   } catch (var8: InvocationTargetException) {
      val var10000: java.lang.Throwable = var8.getCause();
      if (var10000 == null) {
         throw var8;
      }

      val var12: InvocationTargetException = new InvocationTargetException;
      var var10003: java.lang.String = var10000.getMessage();
      if (var10003 == null) {
         var10003 = var8.getMessage();
      }

      var12./* $VF: Unable to resugar constructor */<init>(var10000, var10003);
      throw var12;
   }

   return types;
}

private fun Class<*>.companionOrNull(companionName: String): Any? {
   var companion: Field;
   try {
      companion = `$this$companionOrNull`.getDeclaredField(companionName);
      companion.setAccessible(true);
      companion = (Field)companion.get(null);
   } catch (var4: java.lang.Throwable) {
      companion = null;
   }

   return companion;
}

private fun <T : Any> Class<T>.createEnumSerializer(): KSerializer<T> {
   val constants: Array<Any> = `$this$createEnumSerializer`.getEnumConstants();
   val var10002: java.lang.String = `$this$createEnumSerializer`.getCanonicalName();
   return new EnumSerializer(var10002, constants as Array<java.lang.Enum>);
}

private fun <T : Any> Class<T>.findObjectSerializer(): KSerializer<T>? {
   var var10000: java.lang.String = `$this$findObjectSerializer`.getCanonicalName();
   if (var10000 == null || StringsKt.startsWith$default(var10000, "java.", false, 2, null) || StringsKt.startsWith$default(var10000, "kotlin.", false, 2, null)
      )
    {
      return null;
   } else {
      val var26: Array<Field> = `$this$findObjectSerializer`.getDeclaredFields();
      val var14: Array<Any> = var26;
      var `$this$singleOrNull$iv`: Any = null;
      var `$i$f$singleOrNull`: Boolean = false;
      var `single$iv`: Int = 0;
      val `found$iv`: Int = var14.length;

      while (true) {
         if (`single$iv` >= `found$iv`) {
            var10000 = (java.lang.String)(if (!`$i$f$singleOrNull`) null else `$this$singleOrNull$iv`);
            break;
         }

         val `element$iv`: Any = var14[`single$iv`];
         if ((var14[`single$iv`] as Field).getName() == "INSTANCE"
            && (var14[`single$iv`] as Field).getType() == `$this$findObjectSerializer`
            && Modifier.isStatic((var14[`single$iv`] as Field).getModifiers())) {
            if (`$i$f$singleOrNull`) {
               var10000 = null;
               break;
            }

            `$this$singleOrNull$iv` = `element$iv`;
            `$i$f$singleOrNull` = true;
         }

         `single$iv`++;
      }

      val var28: Field = var10000 as Field;
      if (var10000 as Field == null) {
         return null;
      } else {
         val instance: Any = var28.get(null);
         val var29: Array<Method> = `$this$findObjectSerializer`.getMethods();
         `$this$singleOrNull$iv` = var29;
         var `single$ivx`: Any = null;
         var `found$ivx`: Boolean = false;
         var var22: Int = 0;
         val var23: Int = ((Object[])`$this$singleOrNull$iv`).length;

         while (true) {
            if (var22 >= var23) {
               var10000 = (java.lang.String)(if (!`found$ivx`) null else `single$ivx`);
               break;
            }

            var var24: Any;
            label79: {
               var24 = ((Object[])`$this$singleOrNull$iv`)[var22];
               val it: Method = ((Object[])`$this$singleOrNull$iv`)[var22] as Method;
               if ((((Object[])`$this$singleOrNull$iv`)[var22] as Method).getName() == "serializer") {
                  val var30: Array<Class> = it.getParameterTypes();
                  if (var30.length == 0 && it.getReturnType() == KSerializer::class.java) {
                     var31 = true;
                     break label79;
                  }
               }

               var31 = false;
            }

            if (var31) {
               if (`found$ivx`) {
                  var10000 = null;
                  break;
               }

               `single$ivx` = var24;
               `found$ivx` = true;
            }

            var22++;
         }

         val var33: Method = var10000 as Method;
         if (var10000 as Method == null) {
            return null;
         } else {
            val var17: Any = var33.invoke(instance);
            return var17 as? KSerializer;
         }
      }
   }
}

internal fun isReferenceArray(rootClass: KClass<Any>): Boolean {
   return JvmClassMappingKt.getJavaClass(rootClass).isArray();
}

internal fun initBuiltins(): Map<KClass<*>, KSerializer<*>> {
   val var0: java.util.Map = MapsKt.createMapBuilder();
   val `$this$initBuiltins_u24lambda_u2415`: java.util.Map = var0;
   var0.put(java.lang.String::class, BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE));
   var0.put(Character::class, BuiltinSerializersKt.serializer(CharCompanionObject.INSTANCE));
   var0.put(CharArray::class, BuiltinSerializersKt.CharArraySerializer());
   var0.put(java.lang.Double::class, BuiltinSerializersKt.serializer(DoubleCompanionObject.INSTANCE));
   var0.put(DoubleArray::class, BuiltinSerializersKt.DoubleArraySerializer());
   var0.put(java.lang.Float::class, BuiltinSerializersKt.serializer(FloatCompanionObject.INSTANCE));
   var0.put(FloatArray::class, BuiltinSerializersKt.FloatArraySerializer());
   var0.put(java.lang.Long::class, BuiltinSerializersKt.serializer(LongCompanionObject.INSTANCE));
   var0.put(LongArray::class, BuiltinSerializersKt.LongArraySerializer());
   var0.put(ULong::class, BuiltinSerializersKt.serializer(ULong.Companion));
   var0.put(Int::class, BuiltinSerializersKt.serializer(IntCompanionObject.INSTANCE));
   var0.put(IntArray::class, BuiltinSerializersKt.IntArraySerializer());
   var0.put(UInt::class, BuiltinSerializersKt.serializer(UInt.Companion));
   var0.put(java.lang.Short::class, BuiltinSerializersKt.serializer(ShortCompanionObject.INSTANCE));
   var0.put(ShortArray::class, BuiltinSerializersKt.ShortArraySerializer());
   var0.put(UShort::class, BuiltinSerializersKt.serializer(UShort.Companion));
   var0.put(java.lang.Byte::class, BuiltinSerializersKt.serializer(ByteCompanionObject.INSTANCE));
   var0.put(ByteArray::class, BuiltinSerializersKt.ByteArraySerializer());
   var0.put(UByte::class, BuiltinSerializersKt.serializer(UByte.Companion));
   var0.put(java.lang.Boolean::class, BuiltinSerializersKt.serializer(BooleanCompanionObject.INSTANCE));
   var0.put(BooleanArray::class, BuiltinSerializersKt.BooleanArraySerializer());
   var0.put(Unit::class, BuiltinSerializersKt.serializer(Unit.INSTANCE));
   var0.put(Void::class, BuiltinSerializersKt.NothingSerializer());

   try {
      `$this$initBuiltins_u24lambda_u2415`.put(Duration::class, BuiltinSerializersKt.serializer(Duration.Companion));
   } catch (var21: NoClassDefFoundError) {
   } catch (var22: ClassNotFoundException) {
   }

   val var25: java.util.Map = var0;

   try {
      var25.put(ULongArray::class, BuiltinSerializersKt.ULongArraySerializer());
   } catch (var19: NoClassDefFoundError) {
   } catch (var20: ClassNotFoundException) {
   }

   try {
      var25.put(UIntArray::class, BuiltinSerializersKt.UIntArraySerializer());
   } catch (var17: NoClassDefFoundError) {
   } catch (var18: ClassNotFoundException) {
   }

   try {
      var25.put(UShortArray::class, BuiltinSerializersKt.UShortArraySerializer());
   } catch (var15: NoClassDefFoundError) {
   } catch (var16: ClassNotFoundException) {
   }

   try {
      var25.put(UByteArray::class, BuiltinSerializersKt.UByteArraySerializer());
   } catch (var13: NoClassDefFoundError) {
   } catch (var14: ClassNotFoundException) {
   }

   try {
      `$this$initBuiltins_u24lambda_u2415`.put(Uuid::class, BuiltinSerializersKt.serializer(Uuid.Companion));
   } catch (var11: NoClassDefFoundError) {
   } catch (var12: ClassNotFoundException) {
   }

   try {
      `$this$initBuiltins_u24lambda_u2415`.put(Instant::class, BuiltinSerializersKt.serializer(Instant.Companion));
   } catch (var9: NoClassDefFoundError) {
   } catch (var10: ClassNotFoundException) {
   }

   return MapsKt.build(var0);
}

private inline fun loadSafe(block: () -> Unit) {
   try {
      block.invoke();
   } catch (var3: NoClassDefFoundError) {
   } catch (var4: ClassNotFoundException) {
   }
}
