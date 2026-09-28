package net.peanuuutz.tomlkt

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.enums.EnumEntries
import kotlinx.serialization.SerialInfo

@Target(allowedTargets = [AnnotationTarget.PROPERTY])
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([])
@SerialInfo
annotation class TomlInteger(
   val base: net.peanuuutz.tomlkt.TomlInteger.Base = TomlInteger.Base.Dec,
   val group: Int = 0
) {
   public enum class Base(value: Int, prefix: String) {
      Bin(2, "0b"),
      Oct(8, "0o"),
      Dec(10, ""),
      Hex(16, "0x")
      public final val value: Int
      public final val prefix: String

      init {
         this.value = value;
         this.prefix = prefix;
      }

      @JvmStatic
      fun getEntries(): EnumEntries<TomlInteger.Base> {
         return $ENTRIES;
      }
   }

   // $VF: Class flags could not be determined
   @JvmSynthetic
   internal class Impl : TomlInteger {
      fun Impl(base: TomlInteger.Base, group: Int) {
         this.base = base;
         this.group = group;
      }
   }
}
