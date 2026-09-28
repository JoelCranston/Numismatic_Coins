package kotlinx.coroutines.internal

@JvmSynthetic
internal class SystemPropsKt__SystemPropsKt {
   internal final val AVAILABLE_PROCESSORS: Int = Runtime.getRuntime().availableProcessors()

   @JvmStatic
   internal fun systemProp(propertyName: String): String? {
      var var1: java.lang.String;
      try {
         var1 = System.getProperty(propertyName);
      } catch (var3: SecurityException) {
         var1 = null;
      }

      return var1;
   }
}
