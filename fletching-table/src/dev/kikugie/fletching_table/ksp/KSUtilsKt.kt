@file:SourceDebugExtension(["SMAP\nKSUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 KSUtils.kt\ndev/kikugie/fletching_table/ksp/KSUtilsKt\n*L\n1#1,37:1\n21#1,11:38\n31#1:49\n31#1:50\n*S KotlinDebug\n*F\n+ 1 KSUtils.kt\ndev/kikugie/fletching_table/ksp/KSUtilsKt\n*L\n14#1:38,11\n21#1:49\n26#1:50\n*E\n"])

package dev.kikugie.fletching_table.ksp

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.Location
import com.google.devtools.ksp.symbol.NonExistLocation
import kotlin.jvm.internal.SourceDebugExtension

public fun KSClassDeclaration.resolveQualifier(): String {
   val name: KSNode = `$this$resolveQualifier` as KSNode;
   val var10000: KSName = `$this$resolveQualifier`.getQualifiedName();
   val `value$iv`: Any = if (var10000 != null) var10000.asString() else null;
   if (`value$iv` == null) {
      throw new IllegalStateException(("e: ${resolve(name.getLocation())} Class must not be anonymous").toString());
   } else {
      val var9: java.lang.String = StringsKt.removePrefix((java.lang.String)`value$iv`, "${`$this$resolveQualifier`.getPackageName().asString()}.");
      return (java.lang.String)(if (StringsKt.contains$default(var9, '.', false, 2, null))
         "${`$this$resolveQualifier`.getPackageName().asString()}.${StringsKt.replace$default(var9, ".", "$", false, 4, null)}"
         else
         `value$iv`);
   }
}

internal inline fun <T : Any> KSNode.verifyNotNull(value: T?, message: () -> String): T {
   contract {
      returns() implies (value != null)
   }

   if (value == null) {
      throw new IllegalStateException(("e: ${resolve(`$this$verifyNotNull`.getLocation())} ${message.invoke() as java.lang.String}").toString());
   } else {
      return (T)value;
   }
}

internal inline fun KSNode.verify(check: Boolean, message: () -> String) {
   contract {
      returns() implies (check)
   }

   if (!check) {
      throw new IllegalStateException(("e: ${resolve(`$this$verify`.getLocation())} ${message.invoke() as java.lang.String}").toString());
   }
}

internal inline fun KSNode.report(message: String): Nothing {
   throw new IllegalStateException(("e: ${resolve(`$this$report`.getLocation())} $message").toString());
}

internal fun Location.resolve(): String {
   val var10000: java.lang.String;
   if (`$this$resolve` is FileLocation) {
      var10000 = "file://${(`$this$resolve` as FileLocation).getFilePath()}:${(`$this$resolve` as FileLocation).getLineNumber()}";
   } else {
      if (!(`$this$resolve` == NonExistLocation.INSTANCE)) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = "(Unknown location)";
   }

   return var10000;
}
