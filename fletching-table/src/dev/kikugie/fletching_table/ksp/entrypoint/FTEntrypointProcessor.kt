package dev.kikugie.fletching_table.ksp.entrypoint

import com.google.devtools.ksp.UtilsKt
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint
import dev.kikugie.fletching_table.ksp.KSUtilsKt
import java.io.Closeable
import java.io.OutputStream
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JvmStreamsKt

@SourceDebugExtension(["SMAP\nFTEntrypointProcessor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FTEntrypointProcessor.kt\ndev/kikugie/fletching_table/ksp/entrypoint/FTEntrypointProcessor\n+ 2 KSUtils.kt\ndev/kikugie/fletching_table/ksp/KSUtilsKt\n*L\n1#1,76:1\n31#2:77\n31#2:78\n31#2:79\n31#2:80\n*S KotlinDebug\n*F\n+ 1 FTEntrypointProcessor.kt\ndev/kikugie/fletching_table/ksp/entrypoint/FTEntrypointProcessor\n*L\n40#1:77\n48#1:78\n56#1:79\n65#1:80\n*E\n"])
internal class FTEntrypointProcessor(generator: CodeGenerator, options: Map<String, String>) : SymbolProcessor {
   public final val generator: CodeGenerator
   private final val mapping: Map<String, String>

   init {
      this.generator = generator;
      val var3: java.util.Map = MapsKt.createMapBuilder();
      val `$this$mapping_u24lambda_u240`: java.util.Map = var3;
      val prefix: java.lang.String = "fletching-table.entrypoint.";

      for (Entry var8 : options.entrySet()) {
         val k: java.lang.String = var8.getKey() as java.lang.String;
         val v: java.lang.String = var8.getValue() as java.lang.String;
         if (StringsKt.startsWith$default(k, prefix, false, 2, null)) {
            `$this$mapping_u24lambda_u240`.put(v, StringsKt.removePrefix(k, prefix));
         }
      }

      this.mapping = MapsKt.build(var3);
   }

   public open fun process(resolver: Resolver): List<KSAnnotated> {
      this.save(
         SequencesKt.toList(
            SequencesKt.flatMap(
               SequencesKt.flatMap(
                  Resolver.getSymbolsWithAnnotation$default(resolver, "dev.kikugie.fletching_table.annotation.fabric.Entrypoint", false, 2, null),
                  FTEntrypointProcessor::process$lambda$0
               ),
               FTEntrypointProcessor::process$lambda$1
            )
         )
      );
      return CollectionsKt.emptyList();
   }

   private fun processAnnotated(it: KSAnnotated, entrypoint: Entrypoint): Sequence<FTEntrypointModel> {
      val var10000: Sequence;
      if (it is KSClassDeclaration) {
         var10000 = this.processClass(it as KSClassDeclaration, entrypoint);
      } else if (it is KSFunctionDeclaration) {
         var10000 = this.processFunction(it as KSFunctionDeclaration, entrypoint);
      } else {
         if (it !is KSPropertyDeclaration) {
            throw new IllegalStateException(
               ("e: ${KSUtilsKt.resolve((it as KSNode).getLocation())} Entrypoints can only be applied to classes, functions and fields").toString()
            );
         }

         var10000 = this.processProperty(it as KSPropertyDeclaration, entrypoint);
      }

      return var10000;
   }

   private fun processClass(cls: KSClassDeclaration, entrypoint: Entrypoint): Sequence<FTEntrypointModel> {
      return SequencesKt.map(
         SequencesKt.ifEmpty(
            SequencesKt.ifEmpty(ArraysKt.asSequence(entrypoint.value()), FTEntrypointProcessor::processClass$lambda$0),
            FTEntrypointProcessor::processClass$lambda$1
         ),
         FTEntrypointProcessor::processClass$lambda$2
      );
   }

   private fun processFunction(fn: KSFunctionDeclaration, entrypoint: Entrypoint): Sequence<FTEntrypointModel> {
      return SequencesKt.map(
         SequencesKt.ifEmpty(ArraysKt.asSequence(entrypoint.value()), FTEntrypointProcessor::processFunction$lambda$0),
         FTEntrypointProcessor::processFunction$lambda$1
      );
   }

   private fun processProperty(prop: KSPropertyDeclaration, entrypoint: Entrypoint): Sequence<FTEntrypointModel> {
      return SequencesKt.map(
         SequencesKt.ifEmpty(
            SequencesKt.ifEmpty(ArraysKt.asSequence(entrypoint.value()), FTEntrypointProcessor::processProperty$lambda$0),
            FTEntrypointProcessor::processProperty$lambda$1
         ),
         FTEntrypointProcessor::processProperty$lambda$2
      );
   }

   private fun save(entrypoints: List<FTEntrypointModel>) {
      if (!entrypoints.isEmpty()) {
         label34: {
            val var2: Pair = EntrypointResolveUtilsKt.splitAtLast("fletching-table.entrypoints.config.json", '.');
            val var5: Closeable = this.generator
               .createNewFileByPath(Dependencies.Companion.getALL_FILES(), var2.component1() as java.lang.String, var2.component2() as java.lang.String);
            var var6: java.lang.Throwable = null;

            try {
               try {
                  JvmStreamsKt.encodeToStream(Json.Default, FTEntrypointModel.Companion.getLIST_SERIALIZER(), entrypoints, var5 as OutputStream);
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
   fun `process$lambda$0`(cls: KSAnnotated): Sequence {
      return SequencesKt.map(UtilsKt.getAnnotationsByType(cls, Entrypoint::class), FTEntrypointProcessor::process$lambda$0$0);
   }

   @JvmStatic
   fun `process$lambda$0$0`(`$cls`: KSAnnotated, it: Entrypoint): Pair {
      return TuplesKt.to(`$cls`, it);
   }

   @JvmStatic
   fun `process$lambda$1`(`this$0`: FTEntrypointProcessor, `<destruct>`: Pair): Sequence {
      return `this$0`.processAnnotated(`<destruct>`.component1() as KSAnnotated, `<destruct>`.component2() as Entrypoint);
   }

   @JvmStatic
   fun `processClass$lambda$0`(`$cls`: KSClassDeclaration, `this$0`: FTEntrypointProcessor): Sequence {
      return EntrypointResolveUtilsKt.getEntrypointKinds(`$cls` as KSDeclaration, `this$0`.mapping);
   }

   @JvmStatic
   fun `processClass$lambda$1`(`$cls`: KSClassDeclaration): Sequence {
      throw new IllegalStateException(("e: ${KSUtilsKt.resolve((`$cls` as KSNode).getLocation())} Unable to resolve any entrypoints").toString());
   }

   @JvmStatic
   fun `processClass$lambda$2`(`$qualifier`: java.lang.String, `$adapter`: java.lang.String, it: java.lang.String): FTEntrypointModel {
      return new FTEntrypointModel(it, `$qualifier`, `$adapter`);
   }

   @JvmStatic
   fun `processFunction$lambda$0`(`$fn`: KSFunctionDeclaration): Sequence {
      throw new IllegalStateException(
         ("e: ${KSUtilsKt.resolve((`$fn` as KSNode).getLocation())} Function entrypoints must explicitly specify the kind").toString()
      );
   }

   @JvmStatic
   fun `processFunction$lambda$1`(`$qualifier`: java.lang.String, `$adapter`: java.lang.String, it: java.lang.String): FTEntrypointModel {
      return new FTEntrypointModel(it, `$qualifier`, `$adapter`);
   }

   @JvmStatic
   fun `processProperty$lambda$0`(`$prop`: KSPropertyDeclaration, `this$0`: FTEntrypointProcessor): Sequence {
      return EntrypointResolveUtilsKt.getEntrypointKinds(`$prop` as KSDeclaration, `this$0`.mapping);
   }

   @JvmStatic
   fun `processProperty$lambda$1`(`$prop`: KSPropertyDeclaration): Sequence {
      throw new IllegalStateException(("e: ${KSUtilsKt.resolve((`$prop` as KSNode).getLocation())} Unable to resolve any entrypoints").toString());
   }

   @JvmStatic
   fun `processProperty$lambda$2`(`$qualifier`: java.lang.String, `$adapter`: java.lang.String, it: java.lang.String): FTEntrypointModel {
      return new FTEntrypointModel(it, `$qualifier`, `$adapter`);
   }

   public class Provider : SymbolProcessorProvider {
      public open fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
         return new FTEntrypointProcessor(environment.getCodeGenerator(), environment.getOptions());
      }
   }
}
