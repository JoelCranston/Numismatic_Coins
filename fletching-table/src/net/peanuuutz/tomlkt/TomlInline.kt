package net.peanuuutz.tomlkt

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlinx.serialization.SerialInfo
import net.peanuuutz.tomlkt.TomlInline.Companion.annotationImpl.net_peanuuutz_tomlkt_TomlInline.0

@Target(allowedTargets = [AnnotationTarget.PROPERTY])
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([])
@SerialInfo
annotation class TomlInline(

) {
   public companion object {
      public final val Instance: TomlInline = (new 0()) as TomlInline
   }

   // $VF: Class flags could not be determined
   @JvmSynthetic
   internal class Impl : TomlInline
}
