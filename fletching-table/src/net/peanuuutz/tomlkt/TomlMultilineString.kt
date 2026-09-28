package net.peanuuutz.tomlkt

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlinx.serialization.SerialInfo
import net.peanuuutz.tomlkt.TomlMultilineString.Companion.annotationImpl.net_peanuuutz_tomlkt_TomlMultilineString.0

@Target(allowedTargets = [AnnotationTarget.PROPERTY])
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([])
@SerialInfo
annotation class TomlMultilineString(

) {
   public companion object {
      public final val Instance: TomlMultilineString = (new 0()) as TomlMultilineString
   }

   // $VF: Class flags could not be determined
   @JvmSynthetic
   internal class Impl : TomlMultilineString
}
