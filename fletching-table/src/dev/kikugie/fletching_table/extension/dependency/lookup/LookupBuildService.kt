package dev.kikugie.fletching_table.extension.dependency.lookup

import dev.kikugie.fletching_table.extension.dependency.FTModSpec
import dev.kikugie.fletching_table.extension.dependency.data.ModQuery
import dev.kikugie.fletching_table.extension.dependency.data.ModVersion
import dev.kikugie.fletching_table.extension.dependency.data.ModDependency.Version
import dev.kikugie.fletching_table.extension.dependency.lookup.LookupBuildService.find.1
import dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch
import dev.kikugie.fletching_table.extension.dependency.search.ModSearch
import dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch
import io.ktor.client.HttpClient
import java.io.Closeable
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.OpenOption
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.nio.file.attribute.FileAttribute
import java.time.Instant
import java.util.Arrays
import java.util.LinkedHashMap
import java.util.concurrent.ConcurrentHashMap
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.time.jdk8.InstantConversionsJDK8Kt
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JvmStreamsKt
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters

@SourceDebugExtension(["SMAP\nLookupBuildService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LookupBuildService.kt\ndev/kikugie/fletching_table/extension/dependency/lookup/LookupBuildService\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 JvmStreams.kt\nkotlinx/serialization/json/JvmStreamsKt\n+ 4 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,123:1\n1#2:124\n42#3:125\n80#3:126\n682#4:127\n712#4,4:128\n*S KotlinDebug\n*F\n+ 1 LookupBuildService.kt\ndev/kikugie/fletching_table/extension/dependency/lookup/LookupBuildService\n*L\n96#1:125\n107#1:126\n113#1:127\n113#1:128,4\n*E\n"])
public abstract class LookupBuildService : BuildService<LookupBuildService.Parameters>, ModSearch, AutoCloseable {
   public open val client: HttpClient by LazyKt.lazy(LookupBuildService::client_delegate$lambda$0)
      public open get() {
         return this.client$delegate.getValue() as HttpClient;
      }


   private final val cache: MutableMap<ModQuery, ModLookupResult> = (new ConcurrentHashMap()) as java.util.Map
   private final val ids: MutableMap<String, Int> = (new ConcurrentHashMap()) as java.util.Map

   open fun LookupBuildService() {
      this.load();
   }

   public override fun close() {
      this.getClient().close();
      this.save();
   }

   public override suspend fun get(query: ModQuery, spec: FTModSpec): List<ModVersion> {
      return get$suspendImpl(this, query, spec, `$completion`);
   }

   public override suspend fun get(dependency: Version, spec: FTModSpec): ModVersion {
      return get$suspendImpl(this, dependency, spec, `$completion`);
   }

   private fun find(query: ModQuery, provider: ModSearch, spec: FTModSpec): ModLookupResult? {
      return this.cache.compute(query, new 1<>(spec, this, provider));
   }

   private fun save() {
      label19: {
         val `$this$save_u24lambda_u240`: Path = ((this.getParameters() as LookupBuildService.Parameters).getStorage().getAsFile().get() as File).toPath();
         val data: ModLookupCache = new ModLookupCache(this.ids, CollectionsKt.toList(this.cache.values()));
         val var10000: Path = `$this$save_u24lambda_u240`.getParent();
         val var5: Array<FileAttribute> = new FileAttribute[0];
         val var17: Array<OpenOption> = new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE};
         val var20: OutputStream = Files.newOutputStream(`$this$save_u24lambda_u240`, Arrays.copyOf(var17, var17.length));
         val var16: Closeable = var20;
         var var18: java.lang.Throwable = null;

         try {
            try {
               val it: OutputStream = var16 as OutputStream;
               val `$this$encodeToStream$iv`: Json = Json.Default;
               Json.Default.getSerializersModule();
               JvmStreamsKt.encodeToStream(`$this$encodeToStream$iv`, ModLookupCache.Companion.serializer(), data, it);
            } catch (var12: java.lang.Throwable) {
               var18 = var12;
               throw var12;
            }
         } catch (var13: java.lang.Throwable) {
            CloseableKt.closeFinally(var16, var18);
         }

         CloseableKt.closeFinally(var16, null);
      }
   }

   private fun load() {
      if (!(this.getParameters() as LookupBuildService.Parameters).getRefresh().get() as java.lang.Boolean) {
         val file: Path = ((this.getParameters() as LookupBuildService.Parameters).getStorage().getAsFile().get() as File).toPath();
         var var10001: Array<LinkOption> = new LinkOption[0];
         if (!Files.notExists(file, Arrays.copyOf(var10001, var10001.length))) {
            label69: {
               val var10000: Instant = Instant.now();
               val var26: kotlin.time.Instant = InstantConversionsJDK8Kt.toKotlinInstant(var10000);
               var10001 = (LinkOption[])(this.getParameters() as LookupBuildService.Parameters).getExpiration().get();

               var mapped: Closeable;
               try {
                  val var29: Array<OpenOption> = new OpenOption[0];
                  val var27: InputStream = Files.newInputStream(file, Arrays.copyOf(var29, var29.length));
                  mapped = var27;
                  var `$this$associateBy$iv`: java.lang.Throwable = null;

                  try {
                     try {
                        val `$i$f$associateBy`: InputStream = mapped as InputStream;
                        val `destination$iv$iv`: Json = Json.Default;
                        Json.Default.getSerializersModule();
                        val var22: ModLookupCache = JvmStreamsKt.decodeFromStream(
                           `destination$iv$iv`, ModLookupCache.Companion.serializer(), `$i$f$associateBy`
                        );
                     } catch (var15: java.lang.Throwable) {
                        `$this$associateBy$iv` = var15;
                        throw var15;
                     }
                  } catch (var16: java.lang.Throwable) {
                     CloseableKt.closeFinally(mapped, `$this$associateBy$iv`);
                  }

                  CloseableKt.closeFinally(mapped, null);
               } catch (var17: Exception) {
                  return;
               }

               val var21: Sequence = SequencesKt.filter(CollectionsKt.asSequence(mapped.getLookupEntries()), LookupBuildService::load$lambda$1);
               val var24: java.util.Map = new LinkedHashMap();

               for (Object element$iv$iv : var21) {
                  var24.put((`element$iv$iv` as ModLookupResult).getQuery(), `element$iv$iv`);
               }

               this.cache.putAll(var24);
               this.ids.putAll(mapped.getCurseforgeIds());
            }
         }
      }
   }

   private fun ModQuery.provider(): ModSearch {
      val var2: java.lang.String = `$this$provider`.getProvider();
      val var10000: ModSearch;
      if (var2 == "modrinth") {
         var10000 = new ModrinthSearch(this.getClient());
      } else {
         if (!(var2 == "curseforge")) {
            throw new IllegalArgumentException("Unsupported provider '${`$this$provider`.getProvider()}'");
         }

         var10000 = new CurseforgeSearch(this.getClient(), this.ids);
      }

      return var10000;
   }

   @JvmStatic
   fun `client_delegate$lambda$0`(): HttpClient {
      return LookupBuildServiceKt.access$newClient();
   }

   @JvmStatic
   fun `load$lambda$1`(`$expiration`: kotlin.time.Instant, it: ModLookupResult): Boolean {
      return it.getTimestamp().compareTo(`$expiration`) < 0;
   }

   public interface Parameters : BuildServiceParameters {
      public val expiration: Property<Long>
      public val storage: RegularFileProperty
      public val offline: Property<Boolean>
      public val refresh: Property<Boolean>
   }
}
