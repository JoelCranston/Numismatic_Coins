package dev.kikugie.fletching_table.ksp.entrypoint

import com.google.devtools.ksp.UtilsKt
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.FunctionKind
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSTypeAlias
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.Modifier
import com.google.devtools.ksp.symbol.Origin
import dev.kikugie.fletching_table.ksp.KSUtilsKt

internal fun String.splitAtLast(ch: Char): Pair<String, String> {
   val index: Int = StringsKt.lastIndexOf$default(`$this$splitAtLast`, ch, 0, false, 6, null);
   var var10000: java.lang.String = `$this$splitAtLast`.substring(0, index);
   if (index + 1 >= `$this$splitAtLast`.length()) {
      var10000 = "";
   } else {
      var10000 = `$this$splitAtLast`.substring(index + 1);
   }

   return TuplesKt.to(var10000, var10000);
}

internal fun KSDeclaration.getEntrypointKinds(mapping: Map<String, String>): Sequence<String> {
   val var10000: Sequence;
   if (`$this$getEntrypointKinds` is KSTypeAlias) {
      var10000 = getEntrypointKinds((`$this$getEntrypointKinds` as KSTypeAlias).getType(), mapping);
   } else if (`$this$getEntrypointKinds` is KSPropertyDeclaration) {
      var10000 = getEntrypointKinds((`$this$getEntrypointKinds` as KSPropertyDeclaration).getType(), mapping);
   } else if (`$this$getEntrypointKinds` is KSClassDeclaration) {
      label22: {
         val var6: KSName = `$this$getEntrypointKinds`.getQualifiedName();
         if (var6 != null) {
            val var7: java.lang.String = var6.asString();
            if (var7 != null) {
               var8 = mapping.get(var7) as java.lang.String;
               break label22;
            }
         }

         var8 = null;
      }

      var10000 = if (var8 != null)
         SequencesKt.sequenceOf(var8)
         else
         SequencesKt.flatMap((`$this$getEntrypointKinds` as KSClassDeclaration).getSuperTypes(), EntrypointResolveUtilsKt::getEntrypointKinds$lambda$0);
   } else {
      var10000 = SequencesKt.emptySequence();
   }

   return var10000;
}

internal fun KSTypeReference.getEntrypointKinds(mapping: Map<String, String>): Sequence<String> {
   return getEntrypointKinds(`$this$getEntrypointKinds`.resolve().getDeclaration(), mapping);
}

internal fun KSNode.getEntrypointAdapter(): String {
   val it: Origin = `$this$getEntrypointAdapter`.getOrigin();
   var var10000: java.lang.String;
   switch (EntrypointResolveUtilsKt.WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
      case 1:
         var10000 = "java";
         break;
      case 2:
         var10000 = "kotlin";
         break;
      default:
         throw new IllegalStateException(("e: ${KSUtilsKt.resolve(`$this$getEntrypointAdapter`.getLocation())} Unsupported origin type $it").toString());
   }

   return var10000;
}

internal fun KSClassDeclaration.checkAndResolveQualifier(): String {
   var `$this$any$iv`: KSNode = `$this$checkAndResolveQualifier` as KSNode;
   if (!UtilsKt.isPublic(`$this$checkAndResolveQualifier` as KSDeclaration)) {
      throw new IllegalStateException(("e: ${KSUtilsKt.resolve(`$this$any$iv`.getLocation())} Class must be public").toString());
   } else {
      `$this$any$iv` = `$this$checkAndResolveQualifier` as KSNode;
      if (UtilsKt.isAbstract(`$this$checkAndResolveQualifier`)) {
         throw new IllegalStateException(("e: ${KSUtilsKt.resolve(`$this$any$iv`.getLocation())} Class must not be abstract or an interface").toString());
      } else {
         var var26: Boolean;
         label48: {
            if (`$this$checkAndResolveQualifier`.getClassKind() != ClassKind.OBJECT) {
               val var17: java.util.Iterator = UtilsKt.getConstructors(`$this$checkAndResolveQualifier`).iterator();

               while (true) {
                  if (!var17.hasNext()) {
                     var26 = false;
                     break;
                  }

                  val `check$iv`: KSFunctionDeclaration = var17.next() as KSFunctionDeclaration;
                  if (UtilsKt.isPublic(`check$iv` as KSDeclaration) && `check$iv`.getParameters().isEmpty()) {
                     var26 = true;
                     break;
                  }
               }

               if (!var26) {
                  var26 = false;
                  break label48;
               }
            }

            var26 = true;
         }

         val var19: KSNode = `$this$checkAndResolveQualifier` as KSNode;
         if (!var26) {
            throw new IllegalStateException(("e: ${KSUtilsKt.resolve(var19.getLocation())} Class must have a public constructor with no arguments").toString());
         } else {
            return KSUtilsKt.resolveQualifier(`$this$checkAndResolveQualifier`);
         }
      }
   }
}

internal fun KSFunctionDeclaration.checkAndResolveQualifier(): String {
   val kind: FunctionKind = `$this$checkAndResolveQualifier`.getFunctionKind();
   var var10000: java.lang.String;
   switch (EntrypointResolveUtilsKt.WhenMappings.$EnumSwitchMapping$1[kind.ordinal()]) {
      case 1:
         val var14: KSNode = `$this$checkAndResolveQualifier` as KSNode;
         if (!UtilsKt.isPublic(`$this$checkAndResolveQualifier` as KSDeclaration)) {
            throw new IllegalStateException(("e: ${KSUtilsKt.resolve(var14.getLocation())} Function must be public").toString());
         }

         var10000 = "${`$this$checkAndResolveQualifier`.getPackageName().asString()}::${`$this$checkAndResolveQualifier`.getSimpleName().asString()}";
         break;
      case 2:
         val var12: KSNode = `$this$checkAndResolveQualifier` as KSNode;
         if (!UtilsKt.isPublic(`$this$checkAndResolveQualifier` as KSDeclaration)) {
            throw new IllegalStateException(("e: ${KSUtilsKt.resolve(var12.getLocation())} Function must be public").toString());
         }

         val var18: KSNode = `$this$checkAndResolveQualifier` as KSNode;
         var var24: Any = `$this$checkAndResolveQualifier`.getParent();
         var24 = var24 as? KSClassDeclaration;
         if ((var24 as? KSClassDeclaration) == null) {
            throw new IllegalStateException(("e: ${KSUtilsKt.resolve(var18.getLocation())} Function must be a class member").toString());
         }

         var10000 = "${KSUtilsKt.resolveQualifier((KSClassDeclaration)var24)}::${`$this$checkAndResolveQualifier`.getSimpleName().asString()}";
         break;
      case 3:
         val var10: KSNode = `$this$checkAndResolveQualifier` as KSNode;
         if (!UtilsKt.isPublic(`$this$checkAndResolveQualifier` as KSDeclaration)) {
            throw new IllegalStateException(("e: ${KSUtilsKt.resolve(var10.getLocation())} Function must be public").toString());
         }

         val var16: KSNode = `$this$checkAndResolveQualifier` as KSNode;
         var var21: Any = `$this$checkAndResolveQualifier`.getParent();
         var21 = var21 as? KSClassDeclaration;
         if ((var21 as? KSClassDeclaration) == null) {
            throw new IllegalStateException(("e: ${KSUtilsKt.resolve(var16.getLocation())} Function must be a class member").toString());
         }

         var10000 = "${checkAndResolveQualifier((KSClassDeclaration)var21)}::${`$this$checkAndResolveQualifier`.getSimpleName().asString()}";
         break;
      default:
         throw new IllegalStateException(
            ("e: ${KSUtilsKt.resolve((`$this$checkAndResolveQualifier` as KSNode).getLocation())} Unsupported function kind $kind").toString()
         );
   }

   return var10000;
}

private fun KSPropertyDeclaration.isStaticLike(): Boolean {
   if (!`$this$isStaticLike`.getModifiers().contains(Modifier.JAVA_STATIC)) {
      val var10000: KSNode = `$this$isStaticLike`.getParent();
      if ((var10000 as KSClassDeclaration).getClassKind() != ClassKind.OBJECT) {
         return false;
      }
   }

   return true;
}

internal fun KSPropertyDeclaration.checkAndResolveQualifier(): String {
   var `$this$verify$iv`: KSNode = `$this$checkAndResolveQualifier` as KSNode;
   var `check$iv`: Any = `$this$checkAndResolveQualifier`.getParent();
   `check$iv` = `check$iv` as? KSClassDeclaration;
   if ((`check$iv` as? KSClassDeclaration) == null) {
      throw new IllegalStateException(("e: ${KSUtilsKt.resolve(`$this$verify$iv`.getLocation())} Field must be a class member").toString());
   } else {
      val parent: java.lang.String = KSUtilsKt.resolveQualifier((KSClassDeclaration)`check$iv`);
      `$this$verify$iv` = `$this$checkAndResolveQualifier` as KSNode;
      if (!UtilsKt.isPublic(`$this$checkAndResolveQualifier` as KSDeclaration)) {
         throw new IllegalStateException(("e: ${KSUtilsKt.resolve(`$this$verify$iv`.getLocation())} Field must be public").toString());
      } else {
         `$this$verify$iv` = `$this$checkAndResolveQualifier` as KSNode;
         if (!`$this$checkAndResolveQualifier`.getHasBackingField()) {
            throw new IllegalStateException(("e: ${KSUtilsKt.resolve(`$this$verify$iv`.getLocation())} Field must have a backing value").toString());
         } else {
            `$this$verify$iv` = `$this$checkAndResolveQualifier` as KSNode;
            if (!isStaticLike(`$this$checkAndResolveQualifier`)) {
               throw new IllegalStateException(
                  ("e: ${KSUtilsKt.resolve(`$this$verify$iv`.getLocation())} Field must be static or belong to an object").toString()
               );
            } else {
               return "$parent::${`$this$checkAndResolveQualifier`.getSimpleName().asString()}";
            }
         }
      }
   }
}

fun `getEntrypointKinds$lambda$0`(`$mapping`: java.util.Map, it: KSTypeReference): Sequence {
   return getEntrypointKinds(it, `$mapping`);
}
// $VF: Class flags could not be determined
@JvmSynthetic
internal class WhenMappings {
   @JvmStatic
   fun {
      var var0: IntArray = new int[Origin.values().length];

      try {
         var0[Origin.JAVA.ordinal()] = 1;
      } catch (var6: NoSuchFieldError) {
      }

      try {
         var0[Origin.KOTLIN.ordinal()] = 2;
      } catch (var5: NoSuchFieldError) {
      }

      $EnumSwitchMapping$0 = var0;
      var0 = new int[FunctionKind.values().length];

      try {
         var0[FunctionKind.TOP_LEVEL.ordinal()] = 1;
      } catch (var4: NoSuchFieldError) {
      }

      try {
         var0[FunctionKind.STATIC.ordinal()] = 2;
      } catch (var3: NoSuchFieldError) {
      }

      try {
         var0[FunctionKind.MEMBER.ordinal()] = 3;
      } catch (var2: NoSuchFieldError) {
      }

      $EnumSwitchMapping$1 = var0;
   }
}
