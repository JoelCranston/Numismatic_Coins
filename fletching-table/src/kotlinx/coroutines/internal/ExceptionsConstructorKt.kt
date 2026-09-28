@file:SourceDebugExtension(["SMAP\nExceptionsConstructor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExceptionsConstructor.kt\nkotlinx/coroutines/internal/ExceptionsConstructorKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,112:1\n1#2:113\n11158#3:114\n11493#3,3:115\n12727#3,3:132\n1971#4,14:118\n*S KotlinDebug\n*F\n+ 1 ExceptionsConstructor.kt\nkotlinx/coroutines/internal/ExceptionsConstructorKt\n*L\n41#1:114\n41#1:115,3\n78#1:132,3\n59#1:118,14\n*E\n"])

package kotlinx.coroutines.internal

import java.lang.reflect.Constructor
import java.lang.reflect.Modifier
import java.util.ArrayList
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlinx.coroutines.CopyableThrowable
import kotlinx.coroutines.internal.ExceptionsConstructorKt.createConstructor.nullResult.1

private final val throwableFields: Int = fieldsCountOrDefault(java.lang.Throwable.class, -1)
private final val ctorCache: CtorCache

internal fun <E : Throwable> tryCopyException(exception: E): E? {
   if (exception is CopyableThrowable) {
      var var1: Any;
      try {
         var1 = Result.constructor-impl((exception as CopyableThrowable).createCopy());
      } catch (var3: java.lang.Throwable) {
         var1 = Result.constructor-impl(ResultKt.createFailure(var3));
      }

      return (E)((if (Result.isFailure-impl(var1)) null else var1) as java.lang.Throwable);
   } else {
      return (E)(ctorCache.get((Class<? extends java.lang.Throwable>)exception.getClass()).invoke(exception) as java.lang.Throwable);
   }
}

private fun <E : Throwable> createConstructor(clz: Class<E>): (Throwable) -> Throwable? {
   val nullResult: Function1 = 1.INSTANCE;
   if (throwableFields != fieldsCountOrDefault(clz, 0)) {
      return nullResult;
   } else {
      var `$this$maxByOrNull$iv`: Array<Any> = clz.getConstructors();
      val `maxElem$iv`: java.util.Collection = new ArrayList(`$this$maxByOrNull$iv`.length);

      for (Object item$iv$iv : $this$map$iv) {
         val p: Array<Class> = var10.getParameterTypes();
         var var10000: Pair;
         switch (p.length) {
            case 0:
               var10000 = TuplesKt.to(safeCtor(ExceptionsConstructorKt::createConstructor$lambda$7$lambda$6), 0);
               break;
            case 1:
               var10000 = if (p[0] == java.lang.String::class.java)
                  TuplesKt.to(safeCtor(ExceptionsConstructorKt::createConstructor$lambda$7$lambda$3), 2)
                  else
                  (
                     if (p[0] == java.lang.Throwable::class.java)
                        TuplesKt.to(safeCtor(ExceptionsConstructorKt::createConstructor$lambda$7$lambda$4), 1)
                        else
                        TuplesKt.to(null, -1)
                  );
               break;
            case 2:
               var10000 = if (p[0] == java.lang.String::class.java && p[1] == java.lang.Throwable::class.java)
                  TuplesKt.to(safeCtor(ExceptionsConstructorKt::createConstructor$lambda$7$lambda$1), 3)
                  else
                  TuplesKt.to(null, -1);
               break;
            default:
               var10000 = TuplesKt.to(null, -1);
         }

         `maxElem$iv`.add(var10000);
      }

      val var19: java.util.Iterator = (`maxElem$iv` as java.util.List).iterator();
      val var28: Any;
      if (!var19.hasNext()) {
         var28 = null;
      } else {
         var var20: Any = var19.next();
         if (!var19.hasNext()) {
            var28 = var20;
         } else {
            var var22: Int = ((var20 as Pair).getSecond() as java.lang.Number).intValue();

            do {
               val var24: Any = var19.next();
               val var26: Int = ((var24 as Pair).getSecond() as java.lang.Number).intValue();
               if (var22 < var26) {
                  var20 = var24;
                  var22 = var26;
               }
            } while (iterator$iv.hasNext());

            var28 = var20;
         }
      }

      val var2: Pair = var28 as Pair;
      if (var28 as Pair != null) {
         `$this$maxByOrNull$iv` = var2.getFirst() as Function1;
         if (`$this$maxByOrNull$iv` != null) {
            return `$this$maxByOrNull$iv`;
         }
      }

      return nullResult;
   }
}

private fun safeCtor(block: (Throwable) -> Throwable): (Throwable) -> Throwable? {
   return ExceptionsConstructorKt::safeCtor$lambda$9;
}

private fun Class<*>.fieldsCountOrDefault(defaultValue: Int): Int {
   val var2: KClass = JvmClassMappingKt.getKotlinClass(`$this$fieldsCountOrDefault`);

   var `$this$fieldsCountOrDefault_u24lambda_u2410`: Any;
   try {
      `$this$fieldsCountOrDefault_u24lambda_u2410` = Result.constructor-impl(fieldsCount$default(`$this$fieldsCountOrDefault`, 0, 1, null));
   } catch (var5: java.lang.Throwable) {
      `$this$fieldsCountOrDefault_u24lambda_u2410` = Result.constructor-impl(ResultKt.createFailure(var5));
   }

   return ((if (Result.isFailure-impl(`$this$fieldsCountOrDefault_u24lambda_u2410`)) defaultValue else `$this$fieldsCountOrDefault_u24lambda_u2410`) as java.lang.Number)
      .intValue();
}

private tailrec fun Class<*>.fieldsCount(accumulator: Int = 0): Int {
   while (true) {
      val totalFields: Array<Any> = `$this$fieldsCount`.getDeclaredFields();
      var `count$iv`: Int = 0;

      for (Object element$iv : $this$count$iv) {
         if (!Modifier.isStatic(`element$iv`.getModifiers())) {
            `count$iv`++;
         }
      }

      val var11: Int = accumulator + `count$iv`;
      val var10000: Class = `$this$fieldsCount`.getSuperclass();
      if (var10000 == null) {
         return var11;
      }

      `$this$fieldsCount` = var10000;
      accumulator = var11;
   }
}

@JvmSynthetic
fun `fieldsCount$default`(var0: Class, var1: Int, var2: Int, var3: Any): Int {
   if ((var2 and 1) != 0) {
      var1 = 0;
   }

   return fieldsCount(var0, var1);
}

fun `createConstructor$lambda$7$lambda$1`(`$constructor`: Constructor, e: java.lang.Throwable): java.lang.Throwable {
   val var10000: Any = `$constructor`.newInstance(e.getMessage(), e);
   return var10000 as java.lang.Throwable;
}

fun `createConstructor$lambda$7$lambda$3`(`$constructor`: Constructor, e: java.lang.Throwable): java.lang.Throwable {
   val var10000: Any = `$constructor`.newInstance(e.getMessage());
   val var2: java.lang.Throwable = var10000 as java.lang.Throwable;
   (var10000 as java.lang.Throwable).initCause(e);
   return var2;
}

fun `createConstructor$lambda$7$lambda$4`(`$constructor`: Constructor, e: java.lang.Throwable): java.lang.Throwable {
   val var10000: Any = `$constructor`.newInstance(e);
   return var10000 as java.lang.Throwable;
}

fun `createConstructor$lambda$7$lambda$6`(`$constructor`: Constructor, e: java.lang.Throwable): java.lang.Throwable {
   val var10000: Any = `$constructor`.newInstance();
   val var2: java.lang.Throwable = var10000 as java.lang.Throwable;
   (var10000 as java.lang.Throwable).initCause(e);
   return var2;
}

fun `safeCtor$lambda$9`(`$block`: Function1, e: java.lang.Throwable): java.lang.Throwable {
   var var2: Any;
   try {
      val result: java.lang.Throwable = `$block`.invoke(e) as java.lang.Throwable;
      var2 = Result.constructor-impl(if (!(e.getMessage() == result.getMessage()) && !(result.getMessage() == e.toString())) null else result);
   } catch (var4: java.lang.Throwable) {
      var2 = Result.constructor-impl(ResultKt.createFailure(var4));
   }

   return (if (Result.isFailure-impl(var2)) null else var2) as java.lang.Throwable;
}

@JvmSynthetic
fun `access$createConstructor`(clz: Class): Function1 {
   return createConstructor(clz);
}
