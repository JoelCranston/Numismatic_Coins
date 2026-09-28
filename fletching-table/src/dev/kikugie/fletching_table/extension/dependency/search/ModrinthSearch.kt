package dev.kikugie.fletching_table.extension.dependency.search

import dev.kikugie.fletching_table.extension.dependency.FTModSpec
import dev.kikugie.fletching_table.extension.dependency.data.ModDependency
import dev.kikugie.fletching_table.extension.dependency.data.ModQuery
import dev.kikugie.fletching_table.extension.dependency.data.ModVersion
import dev.kikugie.fletching_table.extension.dependency.data.ModDependency.DependencyType
import dev.kikugie.fletching_table.extension.dependency.data.ModDependency.Version
import dev.kikugie.fletching_table.extension.dependency.data.ModVersion.ReleaseType
import dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.get.1
import dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.get.4
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestKt
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpStatement
import io.ktor.http.ParametersBuilder
import io.ktor.http.URLBuilder
import io.ktor.util.reflect.TypeInfo
import java.util.ArrayList
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.LinkedHashSetSerializer
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.Json

@SourceDebugExtension(["SMAP\nModrinthSearch.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ModrinthSearch.kt\ndev/kikugie/fletching_table/extension/dependency/search/ModrinthSearch\n+ 2 HttpClientCall.kt\nio/ktor/client/call/HttpClientCallKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 builders.kt\nio/ktor/client/request/BuildersKt\n+ 7 builders.kt\nio/ktor/client/request/BuildersKt$request$4\n+ 8 StringUtil.kt\ndev/kikugie/fletching_table/util/StringUtilKt\n+ 9 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,82:1\n162#2:83\n162#2:98\n69#3:84\n84#3,8:85\n69#3:99\n84#3,8:100\n1#4:93\n1563#5:94\n1634#5,3:95\n128#6:108\n85#6:109\n129#6,3:110\n43#6:113\n125#6,4:114\n85#6:118\n129#6,2:119\n131#6:122\n43#6:123\n127#7:121\n13#8:124\n13#8:126\n205#9:125\n205#9:127\n*S KotlinDebug\n*F\n+ 1 ModrinthSearch.kt\ndev/kikugie/fletching_table/extension/dependency/search/ModrinthSearch\n*L\n16#1:83\n21#1:98\n16#1:84\n16#1:85,8\n21#1:99\n21#1:100,8\n18#1:94\n18#1:95,3\n23#1:108\n23#1:109\n23#1:110,3\n23#1:113\n30#1:114,4\n30#1:118\n30#1:119,2\n30#1:122\n30#1:123\n30#1:121\n25#1:124\n26#1:126\n25#1:125\n26#1:127\n*E\n"])
public class ModrinthSearch(client: HttpClient) : ModSearch {
   public open val client: HttpClient

   init {
      this.client = client;
   }

   public override suspend fun get(query: ModQuery, spec: FTModSpec): List<ModVersion> {
      var `$continuation`: Continuation;
      label57: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label57;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      var var10000: Any;
      label61: {
         val `$result`: Any = `$continuation`.result;
         val var17: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(query);
               `$continuation`.L$1 = spec;
               `$continuation`.label = 1;
               var10000 = this.versions(query, `$continuation`);
               if (var10000 === var17) {
                  return var17;
               }
               break;
            case 1:
               spec = `$continuation`.L$1 as FTModSpec;
               query = `$continuation`.L$0 as ModQuery;
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break;
            case 2:
               val `$i$f$map`: Int = `$continuation`.I$0;
               val `$this$map$iv`: HttpResponse = `$continuation`.L$2 as HttpResponse;
               spec = `$continuation`.L$1 as FTModSpec;
               query = `$continuation`.L$0 as ModQuery;
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break label61;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var `item$iv$iv`: KType;
         try {
            `item$iv$iv` = Reflection.typeOf(java.util.List.class, KTypeProjection.Companion.invariant(Reflection.typeOf(ModrinthSearch.ModrinthVersion.class)));
         } catch (var18: java.lang.Throwable) {
            `item$iv$iv` = null;
         }

         val var10001: TypeInfo = new TypeInfo(java.util.List::class, `item$iv$iv`);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(query);
         `$continuation`.L$1 = spec;
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var10000 as HttpResponse);
         `$continuation`.I$0 = 0;
         `$continuation`.label = 2;
         var10000 = (var10000 as HttpResponse).getCall().bodyNullable(var10001, `$continuation`);
         if (var10000 === var17) {
            return var17;
         }
      }

      if (var10000 == null) {
         throw new NullPointerException(
            "null cannot be cast to non-null type kotlin.collections.List<dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthVersion>"
         );
      } else {
         val var23: java.util.List = var10000 as java.util.List;
         if (spec.getLimit().isPresent()) {
            var10000 = var23;
            val var33: Any = spec.getLimit().get();
            var10000 = CollectionsKt.take((java.lang.Iterable)var10000, (var33 as java.lang.Number).intValue());
         } else {
            var10000 = var23;
         }

         val var21: java.lang.Iterable = var10000 as java.lang.Iterable;
         val var26: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var10000 as java.lang.Iterable, 10));

         for (Object item$iv$ivx : var21) {
            var26.add((`item$iv$ivx` as ModrinthSearch.ModrinthVersion).asModVersion());
         }

         return var26 as java.util.List;
      }
   }

   public override suspend fun get(dependency: Version, spec: FTModSpec): ModVersion {
      var `$continuation`: Continuation;
      label43: {
         if (`$completion` is 4) {
            `$continuation` = `$completion` as 4;
            if (((`$completion` as 4).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label43;
            }
         }

         `$continuation` = new 4(this, `$completion`);
      }

      var var10000: Any;
      label47: {
         val `$result`: Any = `$continuation`.result;
         val var16: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               val var10001: java.lang.String = dependency.getVersion();
               `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(dependency);
               `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(spec);
               `$continuation`.label = 1;
               var10000 = this.version(var10001, `$continuation`);
               if (var10000 === var16) {
                  return var16;
               }
               break;
            case 1:
               spec = `$continuation`.L$1 as FTModSpec;
               dependency = `$continuation`.L$0 as ModDependency.Version;
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break;
            case 2:
               val `$i$f$body`: Int = `$continuation`.I$0;
               val `$this$body$iv`: HttpResponse = `$continuation`.L$2 as HttpResponse;
               spec = `$continuation`.L$1 as FTModSpec;
               dependency = `$continuation`.L$0 as ModDependency.Version;
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break label47;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var var10: KType;
         try {
            var10 = Reflection.typeOf(ModrinthSearch.ModrinthVersion.class);
         } catch (var17: java.lang.Throwable) {
            var10 = null;
         }

         val var23: TypeInfo = new TypeInfo(ModrinthSearch.ModrinthVersion::class, var10);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(dependency);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(spec);
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var10000 as HttpResponse);
         `$continuation`.I$0 = 0;
         `$continuation`.label = 2;
         var10000 = (var10000 as HttpResponse).getCall().bodyNullable(var23, `$continuation`);
         if (var10000 === var16) {
            return var16;
         }
      }

      if (var10000 == null) {
         throw new NullPointerException(
            "null cannot be cast to non-null type dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthVersion"
         );
      } else {
         return (var10000 as ModrinthSearch.ModrinthVersion).asModVersion();
      }
   }

   private suspend fun versions(query: ModQuery): HttpResponse {
      val `$this$request$iv`: HttpClient = this.getClient();
      val `urlString$iv`: java.lang.String = "https://api.modrinth.com/v2/project/${query.getId()}/version";
      val `builder$iv$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
      HttpRequestKt.url(`builder$iv$iv$iv`, `urlString$iv`);
      `builder$iv$iv$iv`.url(ModrinthSearch::versions$lambda$0$0);
      return new HttpStatement(`builder$iv$iv$iv`, `$this$request$iv`).execute(`$completion`);
   }

   private suspend fun version(id: String): HttpResponse {
      val `$this$request_u24default$iv`: HttpClient = this.getClient();
      val `urlString$iv`: java.lang.String = "https://api.modrinth.com/v2/version/$id";
      val `builder$iv$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
      HttpRequestKt.url(`builder$iv$iv$iv`, `urlString$iv`);
      return new HttpStatement(`builder$iv$iv$iv`, `$this$request_u24default$iv`).execute(`$completion`);
   }

   @JvmStatic
   fun `versions$lambda$0$0`(`$query`: ModQuery, `$this$url`: URLBuilder, it: URLBuilder): Unit {
      if (!`$query`.getLoaders().isEmpty()) {
         val var10000: ParametersBuilder = `$this$url`.getParameters();
         val `$this$toJsonString$iv`: Any = `$query`.getLoaders();
         val `this_$iv$iv`: Json = Json.Default;
         Json.Default.getSerializersModule();
         var10000.append(
            "loaders",
            `this_$iv$iv`.encodeToString(
               new LinkedHashSetSerializer<>(StringSerializer.INSTANCE), (java.util.Set<? extends java.lang.String>)`$this$toJsonString$iv`
            )
         );
      }

      if (!`$query`.getVersions().isEmpty()) {
         val var12: ParametersBuilder = `$this$url`.getParameters();
         val var8: Any = `$query`.getVersions();
         val var10: Json = Json.Default;
         Json.Default.getSerializersModule();
         var12.append(
            "game_versions", var10.encodeToString(new LinkedHashSetSerializer<>(StringSerializer.INSTANCE), (java.util.Set<? extends java.lang.String>)var8)
         );
      }

      return Unit.INSTANCE;
   }

   @Serializable
   @SourceDebugExtension(["SMAP\nModrinthSearch.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ModrinthSearch.kt\ndev/kikugie/fletching_table/extension/dependency/search/ModrinthSearch$ModrinthDependency\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,82:1\n1#2:83\n*E\n"])
   private data class ModrinthDependency(versionId: String? = null, projectId: String? = null, dependencyType: String) {
      @SerialName("version_id")
      public final val versionId: String?

      @SerialName("project_id")
      public final val projectId: String?

      @SerialName("dependency_type")
      public final val dependencyType: String

      public final val resolvedDependencyType: DependencyType?
         public final get() {
            val var1: java.lang.String = this.dependencyType;
            switch (this.dependencyType.hashCode()) {
               case -393139297:
                  if (var1.equals("required")) {
                     return ModDependency.DependencyType.REQUIRED;
                  }
                  break;
               case -79017120:
                  if (var1.equals("optional")) {
                     return ModDependency.DependencyType.OPTIONAL;
                  }
                  break;
               case 785848970:
                  if (var1.equals("embedded")) {
                     return ModDependency.DependencyType.EMBEDDED;
                  }
               default:
            }

            return null;
         }


      init {
         this.versionId = versionId;
         this.projectId = projectId;
         this.dependencyType = dependencyType;
      }

      public fun asModDependency(): ModDependency? {
         val var6: ModDependency;
         if (this.versionId != null) {
            val var10000: ModDependency.DependencyType = this.getResolvedDependencyType();
            var6 = if (var10000 != null) new ModDependency.Version(this.versionId, var10000) else null;
         } else if (this.projectId != null) {
            val var7: ModDependency.DependencyType = this.getResolvedDependencyType();
            var6 = if (var7 != null) new ModDependency.Project(this.projectId, var7) else null;
         } else {
            var6 = null;
         }

         return var6;
      }

      public operator fun component1(): String? {
         return this.versionId;
      }

      public operator fun component2(): String? {
         return this.projectId;
      }

      public operator fun component3(): String {
         return this.dependencyType;
      }

      public fun copy(versionId: String? = this.versionId, projectId: String? = this.projectId, dependencyType: String = this.dependencyType): dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthDependency {
         return new ModrinthSearch.ModrinthDependency(versionId, projectId, dependencyType);
      }

      public override fun toString(): String {
         return "ModrinthDependency(versionId=${this.versionId}, projectId=${this.projectId}, dependencyType=${this.dependencyType})";
      }

      public override fun hashCode(): Int {
         return ((if (this.versionId == null) 0 else this.versionId.hashCode()) * 31 + (if (this.projectId == null) 0 else this.projectId.hashCode())) * 31
            + this.dependencyType.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ModrinthSearch.ModrinthDependency) {
            return false;
         } else {
            val var2: ModrinthSearch.ModrinthDependency = other as ModrinthSearch.ModrinthDependency;
            if (!(this.versionId == (other as ModrinthSearch.ModrinthDependency).versionId)) {
               return false;
            } else if (!(this.projectId == var2.projectId)) {
               return false;
            } else {
               return this.dependencyType == var2.dependencyType;
            }
         }
      }

      public companion object {
         public fun serializer(): KSerializer<dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthDependency> {
            return ModrinthSearch.ModrinthDependency.$serializer.INSTANCE;
         }
      }
   }

   @Serializable
   private data class ModrinthFile(primary: Boolean, url: String) {
      public final val primary: Boolean
      public final val url: String

      init {
         this.primary = primary;
         this.url = url;
      }

      public operator fun component1(): Boolean {
         return this.primary;
      }

      public operator fun component2(): String {
         return this.url;
      }

      public fun copy(primary: Boolean = this.primary, url: String = this.url): dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthFile {
         return new ModrinthSearch.ModrinthFile(primary, url);
      }

      public override fun toString(): String {
         return "ModrinthFile(primary=${this.primary}, url=${this.url})";
      }

      public override fun hashCode(): Int {
         return java.lang.Boolean.hashCode(this.primary) * 31 + this.url.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ModrinthSearch.ModrinthFile) {
            return false;
         } else {
            val var2: ModrinthSearch.ModrinthFile = other as ModrinthSearch.ModrinthFile;
            if (this.primary != (other as ModrinthSearch.ModrinthFile).primary) {
               return false;
            } else {
               return this.url == var2.url;
            }
         }
      }

      public companion object {
         public fun serializer(): KSerializer<dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthFile> {
            return ModrinthSearch.ModrinthFile.$serializer.INSTANCE;
         }
      }
   }

   @Serializable
   @SourceDebugExtension(["SMAP\nModrinthSearch.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ModrinthSearch.kt\ndev/kikugie/fletching_table/extension/dependency/search/ModrinthSearch$ModrinthVersion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,82:1\n295#2,2:83\n1617#2,9:85\n1869#2:94\n1870#2:96\n1626#2:97\n1#3:95\n*S KotlinDebug\n*F\n+ 1 ModrinthSearch.kt\ndev/kikugie/fletching_table/extension/dependency/search/ModrinthSearch$ModrinthVersion\n*L\n51#1:83,2\n54#1:85,9\n54#1:94\n54#1:96\n54#1:97\n54#1:95\n*E\n"])
   private data class ModrinthVersion(id: String,
      dependencies: List<dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthDependency> = CollectionsKt.emptyList(),
      project: String,
      version: String,
      versionType: String,
      modLoaders: Set<String>,
      gameVersions: Set<String>,
      files: List<dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthFile>
   ) {
      public final val id: String
      public final val dependencies: List<dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthDependency>

      @SerialName("project_id")
      public final val project: String

      @SerialName("version_number")
      public final val version: String

      @SerialName("version_type")
      public final val versionType: String

      @SerialName("loaders")
      public final val modLoaders: Set<String>

      @SerialName("game_versions")
      public final val gameVersions: Set<String>

      public final val files: List<dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthFile>

      public final val releaseType: ReleaseType
         public final get() {
            val var1: java.lang.String = this.versionType;
            var var10000: ModVersion.ReleaseType;
            switch (this.versionType.hashCode()) {
               case 3020272:
                  if (!var1.equals("beta")) {
                     throw new IllegalStateException(("Unsupported release type ${this.versionType}").toString());
                  }

                  var10000 = ModVersion.ReleaseType.BETA;
                  break;
               case 92909918:
                  if (!var1.equals("alpha")) {
                     throw new IllegalStateException(("Unsupported release type ${this.versionType}").toString());
                  }

                  var10000 = ModVersion.ReleaseType.ALPHA;
                  break;
               case 1090594823:
                  if (var1.equals("release")) {
                     var10000 = ModVersion.ReleaseType.STABLE;
                     break;
                  }

                  throw new IllegalStateException(("Unsupported release type ${this.versionType}").toString());
               default:
                  throw new IllegalStateException(("Unsupported release type ${this.versionType}").toString());
            }

            return var10000;
         }


      public final val primaryFile: String
         public final get() {
            val var3: java.util.Iterator = this.files.iterator();

            var var10000: Any;
            while (true) {
               if (var3.hasNext()) {
                  val `element$iv`: Any = var3.next();
                  if (!(`element$iv` as ModrinthSearch.ModrinthFile).getPrimary()) {
                     continue;
                  }

                  var10000 = (ModrinthSearch.ModrinthFile)`element$iv`;
                  break;
               }

               var10000 = null;
               break;
            }

            var10000 = var10000;
            if (var10000 != null) {
               val var8: java.lang.String = var10000.getUrl();
               if (var8 != null) {
                  return var8;
               }
            }

            return CollectionsKt.first(this.files).getUrl();
         }


      init {
         this.id = id;
         this.dependencies = dependencies;
         this.project = project;
         this.version = version;
         this.versionType = versionType;
         this.modLoaders = modLoaders;
         this.gameVersions = gameVersions;
         this.files = files;
      }

      public fun asModVersion(): ModVersion {
         val `$this$mapNotNull$iv`: java.lang.Iterable = this.dependencies;
         val `destination$iv$iv`: java.util.Collection = new ArrayList();

         for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            val var10000: ModDependency = (`element$iv$iv$iv` as ModrinthSearch.ModrinthDependency).asModDependency();
            if (var10000 != null) {
               `destination$iv$iv`.add(var10000);
            }
         }

         return new ModVersion(
            this.id,
            this.project,
            this.version,
            this.getPrimaryFile(),
            this.getReleaseType(),
            this.modLoaders,
            this.gameVersions,
            `destination$iv$iv` as MutableList<ModDependency>
         );
      }

      public operator fun component1(): String {
         return this.id;
      }

      public operator fun component2(): List<dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthDependency> {
         return this.dependencies;
      }

      public operator fun component3(): String {
         return this.project;
      }

      public operator fun component4(): String {
         return this.version;
      }

      public operator fun component5(): String {
         return this.versionType;
      }

      public operator fun component6(): Set<String> {
         return this.modLoaders;
      }

      public operator fun component7(): Set<String> {
         return this.gameVersions;
      }

      public operator fun component8(): List<dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthFile> {
         return this.files;
      }

      public fun copy(
         id: String = this.id,
         dependencies: List<dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthDependency> = this.dependencies,
         project: String = this.project,
         version: String = this.version,
         versionType: String = this.versionType,
         modLoaders: Set<String> = this.modLoaders,
         gameVersions: Set<String> = this.gameVersions,
         files: List<dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthFile> = this.files
      ): dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthVersion {
         return new ModrinthSearch.ModrinthVersion(id, dependencies, project, version, versionType, modLoaders, gameVersions, files);
      }

      public override fun toString(): String {
         return "ModrinthVersion(id=${this.id}, dependencies=${this.dependencies}, project=${this.project}, version=${this.version}, versionType=${this.versionType}, modLoaders=${this.modLoaders}, gameVersions=${this.gameVersions}, files=${this.files})";
      }

      public override fun hashCode(): Int {
         return (
                  (
                           (
                                    (((this.id.hashCode() * 31 + this.dependencies.hashCode()) * 31 + this.project.hashCode()) * 31 + this.version.hashCode())
                                          * 31
                                       + this.versionType.hashCode()
                                 )
                                 * 31
                              + this.modLoaders.hashCode()
                        )
                        * 31
                     + this.gameVersions.hashCode()
               )
               * 31
            + this.files.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ModrinthSearch.ModrinthVersion) {
            return false;
         } else {
            val var2: ModrinthSearch.ModrinthVersion = other as ModrinthSearch.ModrinthVersion;
            if (!(this.id == (other as ModrinthSearch.ModrinthVersion).id)) {
               return false;
            } else if (!(this.dependencies == var2.dependencies)) {
               return false;
            } else if (!(this.project == var2.project)) {
               return false;
            } else if (!(this.version == var2.version)) {
               return false;
            } else if (!(this.versionType == var2.versionType)) {
               return false;
            } else if (!(this.modLoaders == var2.modLoaders)) {
               return false;
            } else if (!(this.gameVersions == var2.gameVersions)) {
               return false;
            } else {
               return this.files == var2.files;
            }
         }
      }

      public companion object {
         public fun serializer(): KSerializer<dev.kikugie.fletching_table.extension.dependency.search.ModrinthSearch.ModrinthVersion> {
            return ModrinthSearch.ModrinthVersion.$serializer.INSTANCE;
         }
      }
   }
}
