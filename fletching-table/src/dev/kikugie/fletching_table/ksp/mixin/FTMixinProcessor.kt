package dev.kikugie.fletching_table.ksp.mixin

import com.google.devtools.ksp.UtilsKt
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import dev.kikugie.fletching_table.annotation.MixinEnvironment
import dev.kikugie.fletching_table.annotation.MixinIgnore
import dev.kikugie.fletching_table.annotation.MixinEnvironment.Env
import dev.kikugie.fletching_table.ksp.KSUtilsKt
import dev.kikugie.fletching_table.ksp.entrypoint.EntrypointResolveUtilsKt
import java.io.Closeable
import java.io.OutputStream
import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JvmStreamsKt

@SourceDebugExtension(["SMAP\nFTMixinProcessor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FTMixinProcessor.kt\ndev/kikugie/fletching_table/ksp/mixin/FTMixinProcessor\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,52:1\n488#2,7:53\n478#2:60\n424#2:61\n1252#3,4:62\n477#4:66\n767#4,2:67\n783#4,4:69\n1#5:73\n*S KotlinDebug\n*F\n+ 1 FTMixinProcessor.kt\ndev/kikugie/fletching_table/ksp/mixin/FTMixinProcessor\n*L\n25#1:53,7\n26#1:60\n26#1:61\n26#1:62,4\n31#1:66\n31#1:67,2\n31#1:69,4\n*E\n"])
internal class FTMixinProcessor(generator: CodeGenerator, options: Map<String, String>) : SymbolProcessor {
   public final val generator: CodeGenerator
   private final val automatic: Boolean
   private final val packages: Map<String, String>

   init {
      this.generator = generator;
      val var10001: java.lang.String = options.get("fletching-table.automatic") as java.lang.String;
      this.automatic = var10001 != null && java.lang.Boolean.parseBoolean(var10001);
      val `$this$mapKeysTo$iv$iv`: LinkedHashMap = new LinkedHashMap();

      for (Entry entry$iv : options.entrySet()) {
         if (StringsKt.startsWith$default(`$i$f$mapKeysTo`.getKey() as java.lang.String, "fletching-table.package.", false, 2, null)) {
            `$this$mapKeysTo$iv$iv`.put(`$i$f$mapKeysTo`.getKey(), `$i$f$mapKeysTo`.getValue());
         }
      }

      val var23: java.util.Map = new LinkedHashMap(MapsKt.mapCapacity(`$this$mapKeysTo$iv$iv`.size()));
      val var25: java.lang.Iterable = `$this$mapKeysTo$iv$iv`.entrySet();
      val var26: java.util.Map = var23;

      for (Object element$iv$iv$iv : var25) {
         var26.put(
            StringsKt.removePrefix((`element$iv$iv$iv` as Entry).getKey() as java.lang.String, "fletching-table.package."),
            (`element$iv$iv$iv` as Entry).getValue()
         );
      }

      this.packages = var26;
   }

   public open fun process(resolver: Resolver): List<KSAnnotated> {
      val `destination$iv$iv`: java.util.Map = new LinkedHashMap();

      val var15: Sequence;
      for (Object element$iv$iv : var15) {
         `destination$iv$iv`.put(`element$iv$iv`, this.process(`element$iv$iv` as KSClassDeclaration));
      }

      this.save(`destination$iv$iv`);
      return CollectionsKt.emptyList();
   }

   private fun process(cls: KSClassDeclaration): FTMixinModel {
      var env: MixinEnvironment;
      var var10000: FTMixinModel;
      var var10002: java.lang.String;
      var var10003: java.lang.String;
      label19: {
         env = SequencesKt.firstOrNull(UtilsKt.getAnnotationsByType(cls as KSAnnotated, MixinEnvironment::class));
         var10000 = new FTMixinModel;
         var10002 = KSUtilsKt.resolveQualifier(cls);
         if (env != null) {
            var10003 = env.value();
            if (var10003 != null) {
               break label19;
            }
         }

         var10003 = "default";
      }

      var var10004: MixinEnvironment.Env;
      label14: {
         if (env != null) {
            var10004 = env.type();
            if (var10004 != null) {
               break label14;
            }
         }

         var10004 = this.find(cls.getPackageName().asString());
      }

      var10000./* $VF: Unable to resugar constructor */<init>(var10002, var10003, var10004);
      return var10000;
   }

   private fun find(name: String): Env {
      val var5: java.util.Iterator = this.packages.entrySet().iterator();
      val var10000: Any;
      if (!var5.hasNext()) {
         var10000 = null;
      } else {
         var it: Any = var5.next();
         if (!var5.hasNext()) {
            var10000 = it;
         } else {
            var var14: Int = StringsKt.removePrefix(name, (it as Entry).getKey() as java.lang.String).length();

            do {
               val var17: Any = var5.next();
               val var19: Int = StringsKt.removePrefix(name, (var17 as Entry).getKey() as java.lang.String).length();
               if (var14 > var19) {
                  it = var17;
                  var14 = var19;
               }
            } while (var5.hasNext());

            var10000 = it;
         }
      }

      val var2: Entry = var10000 as Entry;
      if (var10000 as Entry != null) {
         val var3: Entry = if (StringsKt.startsWith$default(name, var2.getKey() as java.lang.String, false, 2, null)) var2 else null;
         if (var3 != null) {
            val var12: MixinEnvironment.Env = MixinEnvironment.Env.valueOf(var3.getValue() as java.lang.String);
            if (var12 != null) {
               return var12;
            }
         }
      }

      return MixinEnvironment.Env.DEFAULT;
   }

   private fun save(mixins: Map<KSClassDeclaration, FTMixinModel>) {
      if (!mixins.isEmpty()) {
         label34: {
            val var2: Pair = EntrypointResolveUtilsKt.splitAtLast("fletching-table.mixins.config.json", '.');
            val var5: Closeable = this.generator
               .createNewFileByPath(Dependencies.Companion.getALL_FILES(), var2.component1() as java.lang.String, var2.component2() as java.lang.String);
            var var6: java.lang.Throwable = null;

            try {
               try {
                  JvmStreamsKt.encodeToStream(
                     Json.Default, FTMixinModel.Companion.getLIST_SERIALIZER(), CollectionsKt.toList(mixins.values()), var5 as OutputStream
                  );
               } catch (var9: java.lang.Throwable) {
                  var6 = var9;
                  throw var9;
               }
            } catch (var10: java.lang.Throwable) {
               CloseableKt.closeFinally(var5, var6);
            }

            CloseableKt.closeFinally(var5, null);
         }
      }
   }

   @JvmStatic
   fun `process$lambda$0`(`this$0`: FTMixinProcessor, it: KSAnnotated): Boolean {
      return UtilsKt.isAnnotationPresent(it, MixinIgnore::class) || !`this$0`.automatic && !UtilsKt.isAnnotationPresent(it, MixinEnvironment::class);
   }

   public class Provider : SymbolProcessorProvider {
      public open fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
         return new FTMixinProcessor(environment.getCodeGenerator(), environment.getOptions());
      }
   }
}
