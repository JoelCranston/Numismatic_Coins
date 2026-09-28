package kotlinx.coroutines.flow

// $VF: Class flags could not be determined
@JvmSynthetic
internal class `FlowKt__ShareKt$launchSharing$1$2$WhenMappings` {
   @JvmStatic
   fun {
      val var0: IntArray = new int[SharingCommand.values().length];

      try {
         var0[SharingCommand.START.ordinal()] = 1;
      } catch (var4: NoSuchFieldError) {
      }

      try {
         var0[SharingCommand.STOP.ordinal()] = 2;
      } catch (var3: NoSuchFieldError) {
      }

      try {
         var0[SharingCommand.STOP_AND_RESET_REPLAY_CACHE.ordinal()] = 3;
      } catch (var2: NoSuchFieldError) {
      }

      $EnumSwitchMapping$0 = var0;
   }
}
