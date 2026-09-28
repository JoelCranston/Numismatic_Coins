package kotlin.jvm.internal

import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection

@SinceKotlin(version = "1.4")
public class TypeReference @SinceKotlin(version = "1.6")  public constructor(classifier: KClassifier,
      arguments: List<KTypeProjection>,
      platformTypeUpperBound: KType?,
      flags: Int
   ) :
   KType {
   public open val classifier: KClassifier
   public open val arguments: List<KTypeProjection>

   @SinceKotlin(
      version = "1.6"
   )
   internal final val platformTypeUpperBound: KType?

   @SinceKotlin(
      version = "1.6"
   )
   internal final val flags: Int

   public open val annotations: List<Annotation>
      public open get() {
         return CollectionsKt.emptyList();
      }


   public open val isMarkedNullable: Boolean
      public open get() {
         return (this.flags and 1) != 0;
      }


   private final val arrayClassName: String
      private final get() {
         return if (`$this$arrayClassName` == boolean[]::class.java)
            "kotlin.BooleanArray"
            else
            (
               if (`$this$arrayClassName` == char[]::class.java)
                  "kotlin.CharArray"
                  else
                  (
                     if (`$this$arrayClassName` == byte[]::class.java)
                        "kotlin.ByteArray"
                        else
                        (
                           if (`$this$arrayClassName` == short[]::class.java)
                              "kotlin.ShortArray"
                              else
                              (
                                 if (`$this$arrayClassName` == int[]::class.java)
                                    "kotlin.IntArray"
                                    else
                                    (
                                       if (`$this$arrayClassName` == float[]::class.java)
                                          "kotlin.FloatArray"
                                          else
                                          (
                                             if (`$this$arrayClassName` == long[]::class.java)
                                                "kotlin.LongArray"
                                                else
                                                (if (`$this$arrayClassName` == double[]::class.java) "kotlin.DoubleArray" else "kotlin.Array")
                                          )
                                    )
                              )
                        )
                  )
            );
      }


   init {
      this.classifier = classifier;
      this.arguments = arguments;
      this.platformTypeUpperBound = platformTypeUpperBound;
      this.flags = flags;
   }

   public constructor(classifier: KClassifier, arguments: List<KTypeProjection>, isMarkedNullable: Boolean) : this(
         classifier, arguments, null, if (isMarkedNullable) 1 else 0
      )
   public override operator fun equals(other: Any?): Boolean {
      return other is TypeReference
         && this.getClassifier() == (other as TypeReference).getClassifier()
         && this.getArguments() == (other as TypeReference).getArguments()
         && this.platformTypeUpperBound == (other as TypeReference).platformTypeUpperBound
         && this.flags == (other as TypeReference).flags;
   }

   public override fun hashCode(): Int {
      return (this.getClassifier().hashCode() * 31 + this.getArguments().hashCode()) * 31 + Integer.hashCode(this.flags);
   }

   public override fun toString(): String {
      return "${this.asString(false)} (Kotlin reflection is not available)";
   }

   private fun asString(convertPrimitiveToWrapper: Boolean): String {
      val args: KClassifier = this.getClassifier();
      val javaClass: Class = if ((args as? KClass) != null) JvmClassMappingKt.getJavaClass(args as? KClass) else null;
      var var10000: java.lang.String;
      if (javaClass == null) {
         var10000 = this.getClassifier().toString();
      } else if ((this.flags and 4) != 0) {
         var10000 = "kotlin.Nothing";
      } else if (javaClass.isArray()) {
         var10000 = this.getArrayClassName(javaClass);
      } else if (convertPrimitiveToWrapper && javaClass.isPrimitive()) {
         val var10: KClassifier = this.getClassifier();
         var10000 = JvmClassMappingKt.getJavaObjectType(var10 as KClass).getName();
      } else {
         var10000 = javaClass.getName();
      }

      val result: java.lang.String = "$var10000${if (this.getArguments().isEmpty())
         ""
         else
         CollectionsKt.joinToString$default(this.getArguments(), ", ", "<", ">", 0, null, TypeReference::asString$lambda$0, 24, null)}${if (this.isMarkedNullable())
         "?"
         else
         ""}";
      if (this.platformTypeUpperBound is TypeReference) {
         val renderedUpper: java.lang.String = (this.platformTypeUpperBound as TypeReference).asString(true);
         var10000 = if (renderedUpper == result) result else (if (renderedUpper == "$result?") "$result!" else "($result..$renderedUpper)");
      } else {
         var10000 = result;
      }

      return var10000;
   }

   private fun KTypeProjection.asString(): String {
      if (`$this$asString`.getVariance() == null) {
         return "*";
      } else {
         var var4: java.lang.String;
         label24: {
            val var3: KType = `$this$asString`.getType();
            val var10000: TypeReference = var3 as? TypeReference;
            if ((var3 as? TypeReference) != null) {
               var4 = var10000.asString(true);
               if (var4 != null) {
                  break label24;
               }
            }

            var4 = java.lang.String.valueOf(`$this$asString`.getType());
         }

         switch (TypeReference.WhenMappings.$EnumSwitchMapping$0[$this$asString.getVariance().ordinal()]) {
            case 1:
               var4 = var4;
               break;
            case 2:
               var4 = "in $var4";
               break;
            case 3:
               var4 = "out $var4";
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         return var4;
      }
   }

   @JvmStatic
   fun `asString$lambda$0`(`this$0`: TypeReference, it: KTypeProjection): java.lang.CharSequence {
      return `this$0`.asString(it);
   }

   internal companion object {
      internal const val IS_MARKED_NULLABLE: Int
      internal const val IS_MUTABLE_COLLECTION_TYPE: Int
      internal const val IS_NOTHING_TYPE: Int
   }
}
