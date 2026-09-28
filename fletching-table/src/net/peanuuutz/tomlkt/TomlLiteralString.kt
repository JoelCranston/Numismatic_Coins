package net.peanuuutz.tomlkt

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlinx.serialization.SerialInfo
import net.peanuuutz.tomlkt.TomlLiteralString.Companion.annotationImpl.net_peanuuutz_tomlkt_TomlLiteralString.0

@Target(allowedTargets = [AnnotationTarget.PROPERTY])
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([])
@SerialInfo
annotation class TomlLiteralString(

) {
   public companion object {
      public final val Instance: TomlLiteralString = (new 0()) as TomlLiteralString
   }

   // $VF: Class flags could not be determined
   @JvmSynthetic
   internal class Impl : TomlLiteralString
}
