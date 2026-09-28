package kotlinx.coroutines.internal

import java.util.ServiceLoader
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.MainCoroutineDispatcher

@SourceDebugExtension(["SMAP\nMainDispatchers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MainDispatchers.kt\nkotlinx/coroutines/internal/MainDispatcherLoader\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,130:1\n1971#2,14:131\n*S KotlinDebug\n*F\n+ 1 MainDispatchers.kt\nkotlinx/coroutines/internal/MainDispatcherLoader\n*L\n34#1:131,14\n*E\n"])
internal object MainDispatcherLoader {
   private final val FAST_SERVICE_LOADER_ENABLED: Boolean = SystemPropsKt.systemProp("kotlinx.coroutines.fast.service.loader", true)
   public final val dispatcher: MainCoroutineDispatcher = INSTANCE.loadMainDispatcher()

   private fun loadMainDispatcher(): MainCoroutineDispatcher {
      var factories: MainCoroutineDispatcher;
      try {
         val var12: java.util.List = if (FAST_SERVICE_LOADER_ENABLED)
            FastServiceLoader.INSTANCE.loadMainDispatcherFactory$kotlinx_coroutines_core()
            else
            SequencesKt.toList(SequencesKt.asSequence(ServiceLoader.load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader()).iterator()));
         val `iterator$iv`: java.util.Iterator = var12.iterator();
         var var10000: Any;
         if (!`iterator$iv`.hasNext()) {
            var10000 = null;
         } else {
            var `maxElem$iv`: Any = `iterator$iv`.next();
            if (!`iterator$iv`.hasNext()) {
               var10000 = `maxElem$iv`;
            } else {
               var var13: Int = (`maxElem$iv` as MainDispatcherFactory).getLoadPriority();

               do {
                  val var14: Any = `iterator$iv`.next();
                  val var15: Int = (var14 as MainDispatcherFactory).getLoadPriority();
                  if (var13 < var15) {
                     `maxElem$iv` = var14;
                     var13 = var15;
                  }
               } while (iterator$iv.hasNext());

               var10000 = `maxElem$iv`;
            }
         }

         label30: {
            var10000 = var10000 as MainDispatcherFactory;
            if (var10000 as MainDispatcherFactory != null) {
               var10000 = MainDispatchersKt.tryCreateDispatcher((MainDispatcherFactory)var10000, var12);
               if (var10000 != null) {
                  break label30;
               }
            }

            var10000 = MainDispatchersKt.createMissingDispatcher$default(null, null, 3, null);
         }

         factories = (MainCoroutineDispatcher)var10000;
      } catch (var11: java.lang.Throwable) {
         factories = MainDispatchersKt.createMissingDispatcher$default(var11, null, 2, null);
      }

      return factories;
   }
}
