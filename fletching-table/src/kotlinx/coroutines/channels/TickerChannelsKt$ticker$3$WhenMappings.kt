package kotlinx.coroutines.channels

// $VF: Class flags could not be determined
@JvmSynthetic
internal class `TickerChannelsKt$ticker$3$WhenMappings` {
   @JvmStatic
   fun {
      val var0: IntArray = new int[TickerMode.values().length];

      try {
         var0[TickerMode.FIXED_PERIOD.ordinal()] = 1;
      } catch (var3: NoSuchFieldError) {
      }

      try {
         var0[TickerMode.FIXED_DELAY.ordinal()] = 2;
      } catch (var2: NoSuchFieldError) {
      }

      $EnumSwitchMapping$0 = var0;
   }
}
