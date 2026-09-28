package io.ktor.util

public object PlatformUtils {
   public final val IS_BROWSER: Boolean
   public final val IS_NODE: Boolean
   public final val IS_JS: Boolean = PlatformUtilsJvmKt.getPlatform(INSTANCE) is Platform.Js
   public final val IS_WASM_JS: Boolean = PlatformUtilsJvmKt.getPlatform(INSTANCE) is Platform.WasmJs
   public final val IS_JVM: Boolean = PlatformUtilsJvmKt.getPlatform(INSTANCE) == Platform.Jvm.INSTANCE
   public final val IS_NATIVE: Boolean = PlatformUtilsJvmKt.getPlatform(INSTANCE) == Platform.Native.INSTANCE
   public final val IS_DEVELOPMENT_MODE: Boolean = PlatformUtilsJvmKt.isDevelopmentMode(INSTANCE)

   @Deprecated(
      message = "New memory model is now enabled by default. The property will be removed in the future.",
      replaceWith = @ReplaceWith(
         expression = "true",
         imports = {}
      ),
      level = DeprecationLevel.WARNING
   )
   public final val IS_NEW_MM_ENABLED: Boolean = true

   @JvmStatic
   fun {
      var platform: Platform = PlatformUtilsJvmKt.getPlatform(INSTANCE);
      IS_BROWSER = if (platform is Platform.Js)
         (platform as Platform.Js).getJsPlatform() === Platform.JsPlatform.Browser
         else
         platform is Platform.WasmJs && (platform as Platform.WasmJs).getJsPlatform() === Platform.JsPlatform.Browser;
      platform = PlatformUtilsJvmKt.getPlatform(INSTANCE);
      IS_NODE = if (platform is Platform.Js)
         (platform as Platform.Js).getJsPlatform() === Platform.JsPlatform.Node
         else
         platform is Platform.WasmJs && (platform as Platform.WasmJs).getJsPlatform() === Platform.JsPlatform.Node;
   }
}
