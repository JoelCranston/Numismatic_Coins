package it.krzeminski.snakeyaml.engine.kmp.env

public interface EnvConfig {
   public open fun getValueFor(name: String, separator: String?, value: String?, environment: String?): String? {
      return null;
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun getValueFor(`$this`: EnvConfig, name: java.lang.String, separator: java.lang.String?, value: java.lang.String?, environment: java.lang.String?): java.lang.String {
         return EnvConfig.access$getValueFor$jd(`$this`, name, separator, value, environment);
      }
   }
}
