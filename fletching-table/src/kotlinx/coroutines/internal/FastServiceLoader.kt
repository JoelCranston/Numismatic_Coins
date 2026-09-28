package kotlinx.coroutines.internal

import java.io.BufferedReader
import java.io.Closeable
import java.io.InputStreamReader
import java.net.URL
import java.util.ArrayList
import java.util.Collections
import java.util.LinkedHashSet
import java.util.ServiceLoader
import java.util.jar.JarFile
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nFastServiceLoader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FastServiceLoader.kt\nkotlinx/coroutines/internal/FastServiceLoader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,169:1\n85#1,5:170\n85#1,5:175\n139#1,13:191\n1#2:180\n1368#3:181\n1454#3,5:182\n1557#3:187\n1628#3,3:188\n1069#4,2:204\n*S KotlinDebug\n*F\n+ 1 FastServiceLoader.kt\nkotlinx/coroutines/internal/FastServiceLoader\n*L\n62#1:170,5\n69#1:175,5\n125#1:191,13\n107#1:181\n107#1:182,5\n109#1:187\n109#1:188,3\n161#1:204,2\n*E\n"])
internal object FastServiceLoader {
   private const val PREFIX: String = "META-INF/services/"

   internal fun loadMainDispatcherFactory(): List<MainDispatcherFactory> {
      val clz: Class = MainDispatcherFactory::class.java;
      if (!FastServiceLoaderKt.getANDROID_DETECTED()) {
         return this.load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader());
      } else {
         var result: java.util.List;
         try {
            val var13: ArrayList = new ArrayList(2);
            val `this_$iv`: java.lang.String = "kotlinx.coroutines.android.AndroidDispatcherFactory";

            var var7: MainDispatcherFactory;
            try {
               var7 = clz.cast(Class.forName(`this_$iv`, true, clz.getClassLoader()).getDeclaredConstructor().newInstance()) as MainDispatcherFactory;
            } catch (var11: ClassNotFoundException) {
               var7 = null;
            }

            if (var7 == null) {
               return this.load(clz, clz.getClassLoader());
            }

            var13.add(var7);
            val `serviceClass$ivx`: java.lang.String = "kotlinx.coroutines.test.internal.TestMainDispatcherFactory";

            var `clz$iv`: MainDispatcherFactory;
            try {
               `clz$iv` = clz.cast(Class.forName(`serviceClass$ivx`, true, clz.getClassLoader()).getDeclaredConstructor().newInstance()) as MainDispatcherFactory;
            } catch (var10: ClassNotFoundException) {
               `clz$iv` = null;
            }

            if (`clz$iv` != null) {
               var13.add(`clz$iv`);
            }

            result = var13;
         } catch (var12: java.lang.Throwable) {
            result = this.load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader());
         }

         return result;
      }
   }

   private inline fun createInstanceOf(baseClass: Class<MainDispatcherFactory>, serviceClass: String): MainDispatcherFactory? {
      var clz: MainDispatcherFactory;
      try {
         clz = baseClass.cast(Class.forName(serviceClass, true, baseClass.getClassLoader()).getDeclaredConstructor().newInstance()) as MainDispatcherFactory;
      } catch (var6: ClassNotFoundException) {
         clz = null;
      }

      return clz;
   }

   private fun <S> load(service: Class<S>, loader: ClassLoader): List<S> {
      var var3: java.util.List;
      try {
         var3 = this.loadProviders$kotlinx_coroutines_core(service, loader);
      } catch (var5: java.lang.Throwable) {
         var3 = CollectionsKt.toList(ServiceLoader.load(service, loader));
      }

      return var3;
   }

   internal fun <S> loadProviders(service: Class<S>, loader: ClassLoader): List<S> {
      val var10000: ArrayList = Collections.list(loader.getResources("META-INF/services/${service.getName()}"));
      var `$this$map$iv`: java.lang.Iterable = var10000;
      var `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$map$iv) {
         CollectionsKt.addAll(`destination$iv$iv`, INSTANCE.parse(`item$iv$iv` as URL));
      }

      val providers: java.util.Set = CollectionsKt.toSet(`destination$iv$iv` as java.util.List);
      if (providers.isEmpty()) {
         throw new IllegalArgumentException("No providers were loaded with FastServiceLoader".toString());
      } else {
         `$this$map$iv` = providers;
         `destination$iv$iv` = new ArrayList(CollectionsKt.collectionSizeOrDefault(providers, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$iv`.add(INSTANCE.getProviderInstance(var23 as java.lang.String, loader, service));
         }

         return `destination$iv$iv` as MutableList<S>;
      }
   }

   private fun <S> getProviderInstance(name: String, loader: ClassLoader, service: Class<S>): S {
      val clazz: Class = Class.forName(name, false, loader);
      if (!service.isAssignableFrom(clazz)) {
         throw new IllegalArgumentException(("Expected service of class $service, but found $clazz").toString());
      } else {
         return (S)service.cast(clazz.getDeclaredConstructor().newInstance());
      }
   }

   private fun parse(url: URL): List<String> {
      val path: java.lang.String = url.toString();
      label64:
      if (StringsKt.startsWith$default(path, "jar", false, 2, null)) {
         val var50: java.lang.String = StringsKt.substringBefore$default(StringsKt.substringAfter$default(path, "jar:file:", null, 2, null), '!', null, 2, null);
         val var51: java.lang.String = StringsKt.substringAfter$default(path, "!/", null, 2, null);
         val var53: JarFile = new JarFile(var50, false);
         var `cause$iv`: java.lang.Throwable = null;

         try {
            try {
               val var54: Any;
               try {
                  try {
                     val var15: java.util.List = INSTANCE.parseFile(var54 as BufferedReader);
                  } catch (var18: java.lang.Throwable) {
                     throw var18;
                  }
               } catch (var19: java.lang.Throwable) {
                  val var12: Any;
                  CloseableKt.closeFinally((Closeable)var54, (java.lang.Throwable)var12);
               }

               val var55: Any;
               CloseableKt.closeFinally((Closeable)var54, (java.lang.Throwable)var55);
            } catch (var20: java.lang.Throwable) {
               `cause$iv` = var20;
               throw var20;
            }
         } catch (var24: java.lang.Throwable) {
            try {
               var53.close();
            } catch (var23: java.lang.Throwable) {
               if (`cause$iv` == null) {
                  throw var23;
               } else {
                  ExceptionsKt.addSuppressed(`cause$iv`, var23);
                  throw `cause$iv`;
               }
            }
         }

         try {
            var53.close();
            val var16: Any;
            return (java.util.List<java.lang.String>)var16;
         } catch (var17: java.lang.Throwable) {
            throw var17;
         }
      } else {
         label150: {
            val pathToJar: Closeable = new BufferedReader(new InputStreamReader(url.openStream()));
            var entry: java.lang.Throwable = null;

            try {
               try {
                  val var52: java.util.List = INSTANCE.parseFile(pathToJar as BufferedReader);
               } catch (var21: java.lang.Throwable) {
                  entry = var21;
                  throw var21;
               }
            } catch (var22: java.lang.Throwable) {
               CloseableKt.closeFinally(pathToJar, entry);
            }

            CloseableKt.closeFinally(pathToJar, null);
         }
      }
   }

   private inline fun <R> JarFile.use(block: (JarFile) -> R): R {
      label39: {
         var cause: java.lang.Throwable = null;

         try {
            try {
               ;
            } catch (var9: java.lang.Throwable) {
               cause = var9;
               throw var9;
            }
         } catch (var11: java.lang.Throwable) {
            InlineMarker.finallyStart(1);

            try {
               `$this$use`.close();
            } catch (var10: java.lang.Throwable) {
               if (cause == null) {
                  throw var10;
               }

               ExceptionsKt.addSuppressed(cause, var10);
               throw cause;
            }

            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);

         try {
            `$this$use`.close();
         } catch (var8: java.lang.Throwable) {
            throw var8;
         }

         InlineMarker.finallyEnd(1);
         val var5: Any;
         return (R)var5;
      }
   }

   private fun parseFile(r: BufferedReader): List<String> {
      val names: java.util.Set = new LinkedHashSet();

      while (true) {
         val var10000: java.lang.String = r.readLine();
         if (var10000 == null) {
            return CollectionsKt.toList(names);
         }

         val serviceName: java.lang.String = StringsKt.trim(StringsKt.substringBefore$default(var10000, "#", null, 2, null)).toString();
         val `$this$all$iv`: java.lang.CharSequence = serviceName;
         var var7: Int = 0;

         while (true) {
            if (var7 >= `$this$all$iv`.length()) {
               var13 = true;
               break;
            }

            val `element$iv`: Char = `$this$all$iv`.charAt(var7);
            if (`element$iv` != '.' && !Character.isJavaIdentifierPart(`element$iv`)) {
               var13 = false;
               break;
            }

            var7++;
         }

         if (!var13) {
            throw new IllegalArgumentException(("Illegal service provider class name: $serviceName").toString());
         }

         if (serviceName.length() > 0) {
            names.add(serviceName);
         }
      }
   }
}
