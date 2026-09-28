package kotlinx.coroutines.internal

// $VF: Class flags could not be determined
internal class SystemPropsKt {
   @JvmStatic
   fun getAVAILABLE_PROCESSORS(): Int {
      return SystemPropsKt__SystemPropsKt.getAVAILABLE_PROCESSORS();
   }

   @JvmStatic
   fun systemProp(propertyName: java.lang.String): java.lang.String? {
      return SystemPropsKt__SystemPropsKt.systemProp(propertyName);
   }

   @JvmStatic
   fun systemProp(propertyName: java.lang.String, defaultValue: Boolean): Boolean {
      return SystemPropsKt__SystemProps_commonKt.systemProp(propertyName, defaultValue);
   }

   @JvmStatic
   fun systemProp(propertyName: java.lang.String, defaultValue: Int, minValue: Int, maxValue: Int): Int {
      return SystemPropsKt__SystemProps_commonKt.systemProp(propertyName, defaultValue, minValue, maxValue);
   }

   @JvmStatic
   fun systemProp(propertyName: java.lang.String, defaultValue: Long, minValue: Long, maxValue: Long): Long {
      return SystemPropsKt__SystemProps_commonKt.systemProp(propertyName, defaultValue, minValue, maxValue);
   }

   @JvmStatic
   fun systemProp(propertyName: java.lang.String, defaultValue: java.lang.String): java.lang.String {
      return SystemPropsKt__SystemProps_commonKt.systemProp(propertyName, defaultValue);
   }
}
