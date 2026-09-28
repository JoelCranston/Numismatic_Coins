package kotlinx.serialization

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.enums.EnumEntries

@MustBeDocumented
@Target(allowedTargets = [AnnotationTarget.PROPERTY])
@ExperimentalSerializationApi
@Documented
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([])
annotation class EncodeDefault(
   val mode: kotlinx.serialization.EncodeDefault.Mode = EncodeDefault.Mode.ALWAYS
) {
   @ExperimentalSerializationApi
   public enum class Mode {
      ALWAYS,
      NEVER
      @JvmStatic
      fun getEntries(): EnumEntries<EncodeDefault.Mode> {
         return $ENTRIES;
      }
   }
}
