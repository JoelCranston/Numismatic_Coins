package kotlin.jvm.internal

import java.lang.reflect.Constructor
import java.lang.reflect.Method
import java.util.ArrayList
import kotlin.jvm.functions.Function0
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function10
import kotlin.jvm.functions.Function11
import kotlin.jvm.functions.Function12
import kotlin.jvm.functions.Function13
import kotlin.jvm.functions.Function14
import kotlin.jvm.functions.Function15
import kotlin.jvm.functions.Function16
import kotlin.jvm.functions.Function17
import kotlin.jvm.functions.Function18
import kotlin.jvm.functions.Function19
import kotlin.jvm.functions.Function2
import kotlin.jvm.functions.Function20
import kotlin.jvm.functions.Function21
import kotlin.jvm.functions.Function22
import kotlin.jvm.functions.Function3
import kotlin.jvm.functions.Function4
import kotlin.jvm.functions.Function5
import kotlin.jvm.functions.Function6
import kotlin.jvm.functions.Function7
import kotlin.jvm.functions.Function8
import kotlin.jvm.functions.Function9
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter
import kotlin.reflect.KVisibility

@SourceDebugExtension(["SMAP\nClassReference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClassReference.kt\nkotlin/jvm/internal/ClassReference\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,290:1\n1573#2:291\n1604#2,4:292\n*S KotlinDebug\n*F\n+ 1 ClassReference.kt\nkotlin/jvm/internal/ClassReference\n*L\n107#1:291\n107#1:292,4\n*E\n"])
public class ClassReference(jClass: Class<*>) : KClass<Object>, ClassBasedDeclarationContainer {
   public open val jClass: Class<*>

   public open val simpleName: String?
      public open get() {
         return Companion.getClassSimpleName(this.getJClass());
      }


   public open val qualifiedName: String?
      public open get() {
         return Companion.getClassQualifiedName(this.getJClass());
      }


   public open val members: Collection<KCallable<*>>
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   public open val constructors: Collection<KFunction<Any>>
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   public open val nestedClasses: Collection<KClass<*>>
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   public open val annotations: List<Annotation>
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   public open val objectInstance: Any?
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   @SinceKotlin(
      version = "1.1"
   )
   public open val typeParameters: List<KTypeParameter>
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   @SinceKotlin(
      version = "1.1"
   )
   public open val supertypes: List<KType>
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   @SinceKotlin(
      version = "1.3"
   )
   public open val sealedSubclasses: List<KClass<out Any>>
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   @SinceKotlin(
      version = "1.1"
   )
   public open val visibility: KVisibility?
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   @SinceKotlin(
      version = "1.1"
   )
   public open val isFinal: Boolean
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   @SinceKotlin(
      version = "1.1"
   )
   public open val isOpen: Boolean
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   @SinceKotlin(
      version = "1.1"
   )
   public open val isAbstract: Boolean
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   @SinceKotlin(
      version = "1.1"
   )
   public open val isSealed: Boolean
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   @SinceKotlin(
      version = "1.1"
   )
   public open val isData: Boolean
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   @SinceKotlin(
      version = "1.1"
   )
   public open val isInner: Boolean
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   @SinceKotlin(
      version = "1.1"
   )
   public open val isCompanion: Boolean
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   @SinceKotlin(
      version = "1.4"
   )
   public open val isFun: Boolean
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   @SinceKotlin(
      version = "1.5"
   )
   public open val isValue: Boolean
      public open get() {
         this.error();
         throw new KotlinNothingValueException();
      }


   init {
      this.jClass = jClass;
   }

   @SinceKotlin(version = "1.1")
   public override fun isInstance(value: Any?): Boolean {
      return Companion.isInstance(value, this.getJClass());
   }

   private fun error(): Nothing {
      throw new KotlinReflectionNotSupportedError();
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is ClassReference
         && JvmClassMappingKt.<Object>getJavaObjectType(this as KClass<Object>) == JvmClassMappingKt.getJavaObjectType(other as KClass);
   }

   public override fun hashCode(): Int {
      return JvmClassMappingKt.getJavaObjectType(this as KClass<Object>).hashCode();
   }

   public override fun toString(): String {
      return "${this.getJClass().toString()} (Kotlin reflection is not available)";
   }

   @JvmStatic
   fun {
      val var13: java.lang.Iterable = CollectionsKt.listOf(
         new Class[]{
            Function0.class,
            Function1.class,
            Function2.class,
            Function3.class,
            Function4.class,
            Function5.class,
            Function6.class,
            Function7.class,
            Function8.class,
            Function9.class,
            Function10.class,
            Function11.class,
            Function12.class,
            Function13.class,
            Function14.class,
            Function15.class,
            Function16.class,
            Function17.class,
            Function18.class,
            Function19.class,
            Function20.class,
            Function21.class,
            Function22.class
         }
      );
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var13, 10));
      var `index$iv$iv`: Int = 0;

      for (Object item$iv$iv : var13) {
         val var8: Int = `index$iv$iv`++;
         if (var8 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         `destination$iv$iv`.add(TuplesKt.to(`item$iv$iv` as Class, var8));
      }

      FUNCTION_CLASSES = MapsKt.toMap(`destination$iv$iv`);
   }

   @SourceDebugExtension(["SMAP\nClassReference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClassReference.kt\nkotlin/jvm/internal/ClassReference$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,290:1\n1#2:291\n*E\n"])
   public companion object {
      private final val FUNCTION_CLASSES: Map<Class<out () -> *>, Int>

      private fun classFqNameOf(type: String): String? {
         switch (type.hashCode()) {
            case -2061550653:
               if (type.equals("kotlin.jvm.internal.DoubleCompanionObject")) {
                  return "kotlin.Double.Companion";
               }
               break;
            case -2056817302:
               if (type.equals("java.lang.Integer")) {
                  return "kotlin.Int";
               }
               break;
            case -2034166429:
               if (type.equals("java.lang.Cloneable")) {
                  return "kotlin.Cloneable";
               }
               break;
            case -1979556166:
               if (type.equals("java.lang.annotation.Annotation")) {
                  return "kotlin.Annotation";
               }
               break;
            case -1811142716:
               if (type.equals("kotlin.jvm.functions.Function10")) {
                  return "kotlin.Function10";
               }
               break;
            case -1811142715:
               if (type.equals("kotlin.jvm.functions.Function11")) {
                  return "kotlin.Function11";
               }
               break;
            case -1811142714:
               if (type.equals("kotlin.jvm.functions.Function12")) {
                  return "kotlin.Function12";
               }
               break;
            case -1811142713:
               if (type.equals("kotlin.jvm.functions.Function13")) {
                  return "kotlin.Function13";
               }
               break;
            case -1811142712:
               if (type.equals("kotlin.jvm.functions.Function14")) {
                  return "kotlin.Function14";
               }
               break;
            case -1811142711:
               if (type.equals("kotlin.jvm.functions.Function15")) {
                  return "kotlin.Function15";
               }
               break;
            case -1811142710:
               if (type.equals("kotlin.jvm.functions.Function16")) {
                  return "kotlin.Function16";
               }
               break;
            case -1811142709:
               if (type.equals("kotlin.jvm.functions.Function17")) {
                  return "kotlin.Function17";
               }
               break;
            case -1811142708:
               if (type.equals("kotlin.jvm.functions.Function18")) {
                  return "kotlin.Function18";
               }
               break;
            case -1811142707:
               if (type.equals("kotlin.jvm.functions.Function19")) {
                  return "kotlin.Function19";
               }
               break;
            case -1811142685:
               if (type.equals("kotlin.jvm.functions.Function20")) {
                  return "kotlin.Function20";
               }
               break;
            case -1811142684:
               if (type.equals("kotlin.jvm.functions.Function21")) {
                  return "kotlin.Function21";
               }
               break;
            case -1811142683:
               if (type.equals("kotlin.jvm.functions.Function22")) {
                  return "kotlin.Function22";
               }
               break;
            case -1571515090:
               if (type.equals("java.lang.Comparable")) {
                  return "kotlin.Comparable";
               }
               break;
            case -1383349348:
               if (type.equals("java.util.Map")) {
                  return "kotlin.collections.Map";
               }
               break;
            case -1383343454:
               if (type.equals("java.util.Set")) {
                  return "kotlin.collections.Set";
               }
               break;
            case -1325958191:
               if (type.equals("double")) {
                  return "kotlin.Double";
               }
               break;
            case -1182275604:
               if (type.equals("kotlin.jvm.internal.ByteCompanionObject")) {
                  return "kotlin.Byte.Companion";
               }
               break;
            case -1062240117:
               if (type.equals("java.lang.CharSequence")) {
                  return "kotlin.CharSequence";
               }
               break;
            case -688322466:
               if (type.equals("java.util.Collection")) {
                  return "kotlin.collections.Collection";
               }
               break;
            case -527879800:
               if (type.equals("java.lang.Float")) {
                  return "kotlin.Float";
               }
               break;
            case -515992664:
               if (type.equals("java.lang.Short")) {
                  return "kotlin.Short";
               }
               break;
            case -246476834:
               if (type.equals("kotlin.jvm.internal.CharCompanionObject")) {
                  return "kotlin.Char.Companion";
               }
               break;
            case -207262728:
               if (type.equals("kotlin.jvm.internal.LongCompanionObject")) {
                  return "kotlin.Long.Companion";
               }
               break;
            case -165139126:
               if (type.equals("java.util.Map$Entry")) {
                  return "kotlin.collections.Map.Entry";
               }
               break;
            case 104431:
               if (type.equals("int")) {
                  return "kotlin.Int";
               }
               break;
            case 3039496:
               if (type.equals("byte")) {
                  return "kotlin.Byte";
               }
               break;
            case 3052374:
               if (type.equals("char")) {
                  return "kotlin.Char";
               }
               break;
            case 3327612:
               if (type.equals("long")) {
                  return "kotlin.Long";
               }
               break;
            case 64711720:
               if (type.equals("boolean")) {
                  return "kotlin.Boolean";
               }
               break;
            case 65821278:
               if (type.equals("java.util.List")) {
                  return "kotlin.collections.List";
               }
               break;
            case 77230534:
               if (type.equals("kotlin.jvm.internal.ShortCompanionObject")) {
                  return "kotlin.Short.Companion";
               }
               break;
            case 80123371:
               if (type.equals("kotlin.jvm.functions.Function0")) {
                  return "kotlin.Function0";
               }
               break;
            case 80123372:
               if (type.equals("kotlin.jvm.functions.Function1")) {
                  return "kotlin.Function1";
               }
               break;
            case 80123373:
               if (type.equals("kotlin.jvm.functions.Function2")) {
                  return "kotlin.Function2";
               }
               break;
            case 80123374:
               if (type.equals("kotlin.jvm.functions.Function3")) {
                  return "kotlin.Function3";
               }
               break;
            case 80123375:
               if (type.equals("kotlin.jvm.functions.Function4")) {
                  return "kotlin.Function4";
               }
               break;
            case 80123376:
               if (type.equals("kotlin.jvm.functions.Function5")) {
                  return "kotlin.Function5";
               }
               break;
            case 80123377:
               if (type.equals("kotlin.jvm.functions.Function6")) {
                  return "kotlin.Function6";
               }
               break;
            case 80123378:
               if (type.equals("kotlin.jvm.functions.Function7")) {
                  return "kotlin.Function7";
               }
               break;
            case 80123379:
               if (type.equals("kotlin.jvm.functions.Function8")) {
                  return "kotlin.Function8";
               }
               break;
            case 80123380:
               if (type.equals("kotlin.jvm.functions.Function9")) {
                  return "kotlin.Function9";
               }
               break;
            case 97526364:
               if (type.equals("float")) {
                  return "kotlin.Float";
               }
               break;
            case 109413500:
               if (type.equals("short")) {
                  return "kotlin.Short";
               }
               break;
            case 155276373:
               if (type.equals("java.lang.Character")) {
                  return "kotlin.Char";
               }
               break;
            case 226173651:
               if (type.equals("kotlin.jvm.internal.EnumCompanionObject")) {
                  return "kotlin.Enum.Companion";
               }
               break;
            case 344809556:
               if (type.equals("java.lang.Boolean")) {
                  return "kotlin.Boolean";
               }
               break;
            case 398507100:
               if (type.equals("java.lang.Byte")) {
                  return "kotlin.Byte";
               }
               break;
            case 398585941:
               if (type.equals("java.lang.Enum")) {
                  return "kotlin.Enum";
               }
               break;
            case 398795216:
               if (type.equals("java.lang.Long")) {
                  return "kotlin.Long";
               }
               break;
            case 482629606:
               if (type.equals("kotlin.jvm.internal.FloatCompanionObject")) {
                  return "kotlin.Float.Companion";
               }
               break;
            case 499831342:
               if (type.equals("java.util.Iterator")) {
                  return "kotlin.collections.Iterator";
               }
               break;
            case 577341676:
               if (type.equals("java.util.ListIterator")) {
                  return "kotlin.collections.ListIterator";
               }
               break;
            case 599019395:
               if (type.equals("kotlin.jvm.internal.StringCompanionObject")) {
                  return "kotlin.String.Companion";
               }
               break;
            case 761287205:
               if (type.equals("java.lang.Double")) {
                  return "kotlin.Double";
               }
               break;
            case 1052881309:
               if (type.equals("java.lang.Number")) {
                  return "kotlin.Number";
               }
               break;
            case 1063877011:
               if (type.equals("java.lang.Object")) {
                  return "kotlin.Any";
               }
               break;
            case 1195259493:
               if (type.equals("java.lang.String")) {
                  return "kotlin.String";
               }
               break;
            case 1275614662:
               if (type.equals("java.lang.Iterable")) {
                  return "kotlin.collections.Iterable";
               }
               break;
            case 1383693018:
               if (type.equals("kotlin.jvm.internal.BooleanCompanionObject")) {
                  return "kotlin.Boolean.Companion";
               }
               break;
            case 1630335596:
               if (type.equals("java.lang.Throwable")) {
                  return "kotlin.Throwable";
               }
               break;
            case 1877171123:
               if (type.equals("kotlin.jvm.internal.IntCompanionObject")) {
                  return "kotlin.Int.Companion";
               }
            default:
         }

         return null;
      }

      private fun simpleNameOf(type: String): String? {
         switch (type.hashCode()) {
            case -2061550653:
               if (type.equals("kotlin.jvm.internal.DoubleCompanionObject")) {
                  return "Companion";
               }
               break;
            case -2056817302:
               if (type.equals("java.lang.Integer")) {
                  return "Int";
               }
               break;
            case -2034166429:
               if (type.equals("java.lang.Cloneable")) {
                  return "Cloneable";
               }
               break;
            case -1979556166:
               if (type.equals("java.lang.annotation.Annotation")) {
                  return "Annotation";
               }
               break;
            case -1811142716:
               if (type.equals("kotlin.jvm.functions.Function10")) {
                  return "Function10";
               }
               break;
            case -1811142715:
               if (type.equals("kotlin.jvm.functions.Function11")) {
                  return "Function11";
               }
               break;
            case -1811142714:
               if (type.equals("kotlin.jvm.functions.Function12")) {
                  return "Function12";
               }
               break;
            case -1811142713:
               if (type.equals("kotlin.jvm.functions.Function13")) {
                  return "Function13";
               }
               break;
            case -1811142712:
               if (type.equals("kotlin.jvm.functions.Function14")) {
                  return "Function14";
               }
               break;
            case -1811142711:
               if (type.equals("kotlin.jvm.functions.Function15")) {
                  return "Function15";
               }
               break;
            case -1811142710:
               if (type.equals("kotlin.jvm.functions.Function16")) {
                  return "Function16";
               }
               break;
            case -1811142709:
               if (type.equals("kotlin.jvm.functions.Function17")) {
                  return "Function17";
               }
               break;
            case -1811142708:
               if (type.equals("kotlin.jvm.functions.Function18")) {
                  return "Function18";
               }
               break;
            case -1811142707:
               if (type.equals("kotlin.jvm.functions.Function19")) {
                  return "Function19";
               }
               break;
            case -1811142685:
               if (type.equals("kotlin.jvm.functions.Function20")) {
                  return "Function20";
               }
               break;
            case -1811142684:
               if (type.equals("kotlin.jvm.functions.Function21")) {
                  return "Function21";
               }
               break;
            case -1811142683:
               if (type.equals("kotlin.jvm.functions.Function22")) {
                  return "Function22";
               }
               break;
            case -1571515090:
               if (type.equals("java.lang.Comparable")) {
                  return "Comparable";
               }
               break;
            case -1383349348:
               if (type.equals("java.util.Map")) {
                  return "Map";
               }
               break;
            case -1383343454:
               if (type.equals("java.util.Set")) {
                  return "Set";
               }
               break;
            case -1325958191:
               if (type.equals("double")) {
                  return "Double";
               }
               break;
            case -1182275604:
               if (type.equals("kotlin.jvm.internal.ByteCompanionObject")) {
                  return "Companion";
               }
               break;
            case -1062240117:
               if (type.equals("java.lang.CharSequence")) {
                  return "CharSequence";
               }
               break;
            case -688322466:
               if (type.equals("java.util.Collection")) {
                  return "Collection";
               }
               break;
            case -527879800:
               if (type.equals("java.lang.Float")) {
                  return "Float";
               }
               break;
            case -515992664:
               if (type.equals("java.lang.Short")) {
                  return "Short";
               }
               break;
            case -246476834:
               if (type.equals("kotlin.jvm.internal.CharCompanionObject")) {
                  return "Companion";
               }
               break;
            case -207262728:
               if (type.equals("kotlin.jvm.internal.LongCompanionObject")) {
                  return "Companion";
               }
               break;
            case -165139126:
               if (type.equals("java.util.Map$Entry")) {
                  return "Entry";
               }
               break;
            case 104431:
               if (type.equals("int")) {
                  return "Int";
               }
               break;
            case 3039496:
               if (type.equals("byte")) {
                  return "Byte";
               }
               break;
            case 3052374:
               if (type.equals("char")) {
                  return "Char";
               }
               break;
            case 3327612:
               if (type.equals("long")) {
                  return "Long";
               }
               break;
            case 64711720:
               if (type.equals("boolean")) {
                  return "Boolean";
               }
               break;
            case 65821278:
               if (type.equals("java.util.List")) {
                  return "List";
               }
               break;
            case 77230534:
               if (type.equals("kotlin.jvm.internal.ShortCompanionObject")) {
                  return "Companion";
               }
               break;
            case 80123371:
               if (type.equals("kotlin.jvm.functions.Function0")) {
                  return "Function0";
               }
               break;
            case 80123372:
               if (type.equals("kotlin.jvm.functions.Function1")) {
                  return "Function1";
               }
               break;
            case 80123373:
               if (type.equals("kotlin.jvm.functions.Function2")) {
                  return "Function2";
               }
               break;
            case 80123374:
               if (type.equals("kotlin.jvm.functions.Function3")) {
                  return "Function3";
               }
               break;
            case 80123375:
               if (type.equals("kotlin.jvm.functions.Function4")) {
                  return "Function4";
               }
               break;
            case 80123376:
               if (type.equals("kotlin.jvm.functions.Function5")) {
                  return "Function5";
               }
               break;
            case 80123377:
               if (type.equals("kotlin.jvm.functions.Function6")) {
                  return "Function6";
               }
               break;
            case 80123378:
               if (type.equals("kotlin.jvm.functions.Function7")) {
                  return "Function7";
               }
               break;
            case 80123379:
               if (type.equals("kotlin.jvm.functions.Function8")) {
                  return "Function8";
               }
               break;
            case 80123380:
               if (type.equals("kotlin.jvm.functions.Function9")) {
                  return "Function9";
               }
               break;
            case 97526364:
               if (type.equals("float")) {
                  return "Float";
               }
               break;
            case 109413500:
               if (type.equals("short")) {
                  return "Short";
               }
               break;
            case 155276373:
               if (type.equals("java.lang.Character")) {
                  return "Char";
               }
               break;
            case 226173651:
               if (type.equals("kotlin.jvm.internal.EnumCompanionObject")) {
                  return "Companion";
               }
               break;
            case 344809556:
               if (type.equals("java.lang.Boolean")) {
                  return "Boolean";
               }
               break;
            case 398507100:
               if (type.equals("java.lang.Byte")) {
                  return "Byte";
               }
               break;
            case 398585941:
               if (type.equals("java.lang.Enum")) {
                  return "Enum";
               }
               break;
            case 398795216:
               if (type.equals("java.lang.Long")) {
                  return "Long";
               }
               break;
            case 482629606:
               if (type.equals("kotlin.jvm.internal.FloatCompanionObject")) {
                  return "Companion";
               }
               break;
            case 499831342:
               if (type.equals("java.util.Iterator")) {
                  return "Iterator";
               }
               break;
            case 577341676:
               if (type.equals("java.util.ListIterator")) {
                  return "ListIterator";
               }
               break;
            case 599019395:
               if (type.equals("kotlin.jvm.internal.StringCompanionObject")) {
                  return "Companion";
               }
               break;
            case 761287205:
               if (type.equals("java.lang.Double")) {
                  return "Double";
               }
               break;
            case 1052881309:
               if (type.equals("java.lang.Number")) {
                  return "Number";
               }
               break;
            case 1063877011:
               if (type.equals("java.lang.Object")) {
                  return "Any";
               }
               break;
            case 1195259493:
               if (type.equals("java.lang.String")) {
                  return "String";
               }
               break;
            case 1275614662:
               if (type.equals("java.lang.Iterable")) {
                  return "Iterable";
               }
               break;
            case 1383693018:
               if (type.equals("kotlin.jvm.internal.BooleanCompanionObject")) {
                  return "Companion";
               }
               break;
            case 1630335596:
               if (type.equals("java.lang.Throwable")) {
                  return "Throwable";
               }
               break;
            case 1877171123:
               if (type.equals("kotlin.jvm.internal.IntCompanionObject")) {
                  return "Companion";
               }
            default:
         }

         return null;
      }

      public fun getClassSimpleName(jClass: Class<*>): String? {
         var var10000: java.lang.String;
         if (jClass.isAnonymousClass()) {
            var10000 = null;
         } else if (jClass.isLocalClass()) {
            val componentType: java.lang.String = jClass.getSimpleName();
            val var9: Method = jClass.getEnclosingMethod();
            if (var9 != null) {
               var10000 = StringsKt.substringAfter$default(componentType, "${var9.getName()}$", null, 2, null);
               if (var10000 != null) {
                  return var10000;
               }
            }

            val var10: Constructor = jClass.getEnclosingConstructor();
            if (var10 != null) {
               var10000 = StringsKt.substringAfter$default(componentType, "${var10.getName()}$", null, 2, null);
            } else {
               var10000 = StringsKt.substringAfter$default(componentType, '$', null, 2, null);
            }
         } else if (jClass.isArray()) {
            val var7: Class = jClass.getComponentType();
            if (var7.isPrimitive()) {
               val var10001: java.lang.String = var7.getName();
               val var3: java.lang.String = this.simpleNameOf(var10001);
               var10000 = if (var3 != null) "$var3Array" else null;
            } else {
               var10000 = null;
            }

            if (var10000 == null) {
               var10000 = "Array";
            }
         } else {
            val var11: java.lang.String = jClass.getName();
            var10000 = this.simpleNameOf(var11);
            if (var10000 == null) {
               var10000 = jClass.getSimpleName();
            }
         }

         return var10000;
      }

      public fun getClassQualifiedName(jClass: Class<*>): String? {
         var var10000: java.lang.String;
         if (jClass.isAnonymousClass()) {
            var10000 = null;
         } else if (jClass.isLocalClass()) {
            var10000 = null;
         } else if (jClass.isArray()) {
            val componentType: Class = jClass.getComponentType();
            if (componentType.isPrimitive()) {
               val var10001: java.lang.String = componentType.getName();
               val var3: java.lang.String = this.classFqNameOf(var10001);
               var10000 = if (var3 != null) "$var3Array" else null;
            } else {
               var10000 = null;
            }

            if (var10000 == null) {
               var10000 = "kotlin.Array";
            }
         } else {
            val var4: java.lang.String = jClass.getName();
            var10000 = this.classFqNameOf(var4);
            if (var10000 == null) {
               var10000 = jClass.getCanonicalName();
            }
         }

         return var10000;
      }

      public fun isInstance(value: Any?, jClass: Class<*>): Boolean {
         val var10000: java.util.Map = ClassReference.access$getFUNCTION_CLASSES$cp();
         val objectType: Int = var10000.get(jClass) as Int;
         return if (objectType != null)
            TypeIntrinsics.isFunctionOfArity(value, objectType.intValue())
            else
            (if (jClass.isPrimitive()) JvmClassMappingKt.getJavaObjectType(JvmClassMappingKt.getKotlinClass(jClass)) else jClass).isInstance(value);
      }
   }
}
