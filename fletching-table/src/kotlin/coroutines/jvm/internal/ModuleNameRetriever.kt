package kotlin.coroutines.jvm.internal

import java.lang.reflect.Method
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nDebugMetadata.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DebugMetadata.kt\nkotlin/coroutines/jvm/internal/ModuleNameRetriever\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,158:1\n1#2:159\n*E\n"])
private object ModuleNameRetriever {
   private final val notOnJava9: kotlin.coroutines.jvm.internal.ModuleNameRetriever.Cache = new ModuleNameRetriever.Cache(null, null, null)
   private final var cache: kotlin.coroutines.jvm.internal.ModuleNameRetriever.Cache?

   public fun getModuleName(continuation: BaseContinuationImpl): String? {
      var var10000: ModuleNameRetriever.Cache = cache;
      if (cache == null) {
         var10000 = this.buildCache(continuation);
      }

      if (var10000 === notOnJava9) {
         return null;
      } else {
         if (var10000.getModuleMethod != null) {
            var var6: Any = var10000.getModuleMethod.invoke(continuation.getClass());
            if (var6 != null) {
               if (var10000.getDescriptorMethod != null) {
                  var6 = var10000.getDescriptorMethod.invoke(var6);
                  if (var6 != null) {
                     val var5: Any = if (var10000.nameMethod != null) var10000.nameMethod.invoke(var6) else null;
                     return var5 as? java.lang.String;
                  }
               }

               return null;
            }
         }

         return null;
      }
   }

   private fun buildCache(continuation: BaseContinuationImpl): kotlin.coroutines.jvm.internal.ModuleNameRetriever.Cache {
      try {
         val var7: ModuleNameRetriever.Cache = new ModuleNameRetriever.Cache(
            Class.class.getDeclaredMethod("getModule"),
            continuation.getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor"),
            continuation.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name")
         );
         cache = var7;
         return var7;
      } catch (var10: Exception) {
         val getDescriptorMethod: ModuleNameRetriever.Cache = notOnJava9;
         cache = notOnJava9;
         return getDescriptorMethod;
      }
   }

   private class Cache(getModuleMethod: Method?, getDescriptorMethod: Method?, nameMethod: Method?) {
      public final val getModuleMethod: Method?
      public final val getDescriptorMethod: Method?
      public final val nameMethod: Method?

      init {
         this.getModuleMethod = getModuleMethod;
         this.getDescriptorMethod = getDescriptorMethod;
         this.nameMethod = nameMethod;
      }
   }
}
