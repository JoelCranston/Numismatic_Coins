package dev.kikugie.fletching_table.extension.dependency.search

import dev.kikugie.fletching_table.extension.dependency.FTModSpec
import dev.kikugie.fletching_table.extension.dependency.data.ModDependency
import dev.kikugie.fletching_table.extension.dependency.data.ModQuery
import dev.kikugie.fletching_table.extension.dependency.data.ModVersion
import dev.kikugie.fletching_table.extension.dependency.data.ModDependency.DependencyType
import dev.kikugie.fletching_table.extension.dependency.data.ModDependency.Version
import dev.kikugie.fletching_table.extension.dependency.data.ModVersion.ReleaseType
import dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.get.1
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestKt
import io.ktor.client.request.UtilsKt
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpStatement
import io.ktor.http.ContentType
import io.ktor.http.HttpMessagePropertiesKt
import io.ktor.http.URLBuilder
import io.ktor.util.reflect.TypeInfo
import java.util.ArrayList
import java.util.Locale
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowKt
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementKt
import kotlinx.serialization.json.JsonObject

@SourceDebugExtension(["SMAP\nCurseforgeSearch.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CurseforgeSearch.kt\ndev/kikugie/fletching_table/extension/dependency/search/CurseforgeSearch\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 builders.kt\nio/ktor/client/request/BuildersKt\n+ 4 HttpClientCall.kt\nio/ktor/client/call/HttpClientCallKt\n+ 5 Type.kt\nio/ktor/util/reflect/TypeKt\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 7 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,164:1\n382#2,7:165\n128#3:172\n85#3:173\n129#3,3:174\n43#3:177\n128#3:189\n85#3:190\n129#3,3:191\n43#3:194\n162#4:178\n69#5:179\n84#5,8:180\n1#6:188\n1563#7:195\n1634#7,3:196\n*S KotlinDebug\n*F\n+ 1 CurseforgeSearch.kt\ndev/kikugie/fletching_table/extension/dependency/search/CurseforgeSearch\n*L\n38#1:165,7\n41#1:172\n41#1:173\n41#1:174,3\n41#1:177\n80#1:189\n80#1:190\n80#1:191,3\n80#1:194\n50#1:178\n50#1:179\n50#1:180,8\n63#1:195\n63#1:196,3\n*E\n"])
public class CurseforgeSearch(client: HttpClient, ids: MutableMap<String, Int>) : ModSearch {
   public open val client: HttpClient
   public final val ids: MutableMap<String, Int>

   init {
      this.client = client;
      this.ids = ids;
   }

   public override suspend fun get(query: ModQuery, spec: FTModSpec): List<ModVersion> {
      var `$continuation`: Continuation;
      label31: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label31;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var5: CurseforgeSearch;
      var var12: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            var5 = this;
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(query);
            `$continuation`.L$1 = spec;
            `$continuation`.L$2 = this;
            `$continuation`.label = 1;
            var12 = this.adjust(query, `$continuation`);
            if (var12 === var8) {
               return var8;
            }
            break;
         case 1:
            var5 = `$continuation`.L$2 as CurseforgeSearch;
            spec = `$continuation`.L$1 as FTModSpec;
            query = `$continuation`.L$0 as ModQuery;
            ResultKt.throwOnFailure(`$result`);
            var12 = `$result`;
            break;
         case 2:
            val jobs: Flow = `$continuation`.L$2 as Flow;
            spec = `$continuation`.L$1 as FTModSpec;
            query = `$continuation`.L$0 as ModQuery;
            ResultKt.throwOnFailure(`$result`);
            return `$result`;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      var var11: Flow = FlowKt.merge(SequencesKt.asIterable(SequencesKt.map(var5.permutate(var12 as ModQuery), CurseforgeSearch::get$lambda$0)));
      if (spec.getLimit().isPresent()) {
         val var10001: Any = spec.getLimit().get();
         var11 = FlowKt.take(var11, (var10001 as java.lang.Number).intValue());
      }

      `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(query);
      `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(spec);
      `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var11);
      `$continuation`.label = 2;
      var12 = FlowKt.toList$default(var11, null, `$continuation`, 1, null);
      return if (var12 === var8) var8 else var12;
   }

   public override suspend fun get(dependency: Version, spec: FTModSpec): ModVersion {
      throw new UnsupportedOperationException("Curseforge doesn't support direct version dependencies");
   }

   private suspend fun ModQuery.adjust(): ModQuery {
      var `$continuation`: Continuation;
      label38: {
         if (`$completion` is dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.adjust.1) {
            `$continuation` = `$completion` as dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.adjust.1;
            if (((`$completion` as dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.adjust.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label38;
            }
         }

         `$continuation` = new dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.adjust.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var12: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var `$this$getOrPut$iv`: java.util.Map;
      var `key$iv`: Any;
      var var9: ModQuery;
      var var10000: Any;
      switch ($continuation.label) {
         case 0: {
            ResultKt.throwOnFailure(`$result`);
            if (StringsKt.toIntOrNull(`$this$adjust`.getId()) != null) {
               return `$this$adjust`;
            }

            `$this$getOrPut$iv` = this.ids;
            `key$iv` = `$this$adjust`.getId();
            val var15: Any = `$this$getOrPut$iv`.get(`key$iv`);
            if (var15 != null) {
               return ModQuery.copy$default(`$this$adjust`, java.lang.String.valueOf((var15 as java.lang.Number).intValue()), null, null, null, 14, null);
            }

            var9 = `$this$adjust`;
            val var10001: java.lang.String = `$this$adjust`.getId();
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$adjust`);
            `$continuation`.L$1 = `$this$getOrPut$iv`;
            `$continuation`.L$2 = `key$iv`;
            `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(var15);
            `$continuation`.L$4 = `$this$adjust`;
            `$continuation`.I$0 = 0;
            `$continuation`.I$1 = 0;
            `$continuation`.label = 1;
            var10000 = this.id(var10001, `$continuation`);
            if (var10000 === var12) {
               return var12;
            }
            break;
         }
         case 1: {
            val var7: Int = `$continuation`.I$1;
            val `$i$f$getOrPut`: Int = `$continuation`.I$0;
            var9 = `$continuation`.L$4 as ModQuery;
            val `value$iv`: Any = `$continuation`.L$3;
            `key$iv` = `$continuation`.L$2 as java.lang.String;
            `$this$getOrPut$iv` = `$continuation`.L$1 as java.util.Map;
            `$this$adjust` = `$continuation`.L$0 as ModQuery;
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val `answer$iv`: Any = Boxing.boxInt((var10000 as java.lang.Number).intValue());
      `$this$getOrPut$iv`.put(`key$iv`, `answer$iv`);
      return ModQuery.copy$default(var9, java.lang.String.valueOf((`answer$iv` as java.lang.Number).intValue()), null, null, null, 14, null);
   }

   public suspend fun id(slug: String): Int {
      var `$continuation`: Continuation;
      label52: {
         if (`$completion` is dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.id.1) {
            `$continuation` = `$completion` as dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.id.1;
            if (((`$completion` as dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.id.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label52;
            }
         }

         `$continuation` = new dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.id.1(this, `$completion`);
      }

      var var10000: HttpStatement;
      label56: {
         val `$result`: Any = `$continuation`.result;
         val var18: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               val var21: HttpClient = this.getClient();
               val var35: HttpRequestBuilder = new HttpRequestBuilder();
               HttpRequestKt.url(var35, "https://api.curseforge.com/v1/mods/search");
               UtilsKt.header(var35, "x-api-key", "$2a$10$wuAJuNZuted3NORVmpgUC.m8sI.pv1tOPKZyBgLFGjxFp/br0lZCC");
               HttpMessagePropertiesKt.contentType(var35, ContentType.Application.INSTANCE.getJson());
               var35.url(CurseforgeSearch::id$lambda$0$0);
               var10000 = new HttpStatement(var35, var21);
               `$continuation`.L$0 = slug;
               `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var21);
               `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable("https://api.curseforge.com/v1/mods/search");
               `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(var21);
               `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var21);
               `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(var35);
               `$continuation`.I$0 = 0;
               `$continuation`.I$1 = 0;
               `$continuation`.I$2 = 0;
               `$continuation`.label = 1;
               var10000 = var10000.execute(`$continuation`);
               if (var10000 === var18) {
                  return var18;
               }
               break;
            case 1:
               val `$i$f$request`: Int = `$continuation`.I$2;
               val `$i$f$requestx`: Int = `$continuation`.I$1;
               val `$i$f$requestxx`: Int = `$continuation`.I$0;
               val `$i$f$typeOfOrNull`: HttpRequestBuilder = `$continuation`.L$5 as HttpRequestBuilder;
               val `$this$request$iv$iv$iv`: HttpClient = `$continuation`.L$4 as HttpClient;
               val var7: HttpClient = `$continuation`.L$3 as HttpClient;
               val var23: java.lang.String = `$continuation`.L$2 as java.lang.String;
               val json: HttpClient = `$continuation`.L$1 as HttpClient;
               slug = `$continuation`.L$0 as java.lang.String;
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break;
            case 2:
               val `$i$f$body`: Int = `$continuation`.I$0;
               val data: HttpResponse = `$continuation`.L$2 as HttpResponse;
               val response: HttpResponse = `$continuation`.L$1 as HttpResponse;
               slug = `$continuation`.L$0 as java.lang.String;
               ResultKt.throwOnFailure(`$result`);
               var10000 = (HttpStatement)`$result`;
               break label56;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var `$this$request_u24lambda_u240$iv`: KType;
         try {
            `$this$request_u24lambda_u240$iv` = Reflection.typeOf(JsonObject.class);
         } catch (var19: java.lang.Throwable) {
            `$this$request_u24lambda_u240$iv` = null;
         }

         val var10001: TypeInfo = new TypeInfo(JsonObject::class, `$this$request_u24lambda_u240$iv`);
         `$continuation`.L$0 = slug;
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var10000 as HttpResponse);
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var10000 as HttpResponse);
         `$continuation`.L$3 = null;
         `$continuation`.L$4 = null;
         `$continuation`.L$5 = null;
         `$continuation`.I$0 = 0;
         `$continuation`.label = 2;
         var10000 = (HttpStatement)(var10000 as HttpResponse).getCall().bodyNullable(var10001, `$continuation`);
         if (var10000 === var18) {
            return var18;
         }
      }

      if (var10000 == null) {
         throw new NullPointerException("null cannot be cast to non-null type kotlinx.serialization.json.JsonObject");
      } else {
         var10000 = (HttpStatement)(var10000 as JsonObject).get("data");
         val var25: JsonArray = JsonElementKt.getJsonArray(var10000 as JsonElement);
         if (var25.size() != 1) {
            throw new IllegalStateException(("Unable to find the match for '$slug'").toString());
         } else {
            var10000 = (HttpStatement)JsonElementKt.getJsonObject(CollectionsKt.first(var25)).get("id");
            return Boxing.boxInt(JsonElementKt.getInt(JsonElementKt.getJsonPrimitive(var10000 as JsonElement)));
         }
      }
   }

   private fun ModQuery.permutate(): Sequence<ModQuery> {
      return if (CurseforgeSearchKt.access$isInvariable(`$this$permutate`.getVersions())
            && CurseforgeSearchKt.access$isInvariable(`$this$permutate`.getLoaders()))
         SequencesKt.sequenceOf(`$this$permutate`)
         else
         (
            if (`$this$permutate`.getVersions().isEmpty())
               SequencesKt.map(CollectionsKt.asSequence(`$this$permutate`.getLoaders()), CurseforgeSearch::permutate$lambda$0)
               else
               (
                  if (`$this$permutate`.getLoaders().isEmpty())
                     SequencesKt.map(CollectionsKt.asSequence(`$this$permutate`.getVersions()), CurseforgeSearch::permutate$lambda$1)
                     else
                     SequencesKt.map(
                        SequencesKt.flatMapIterable(CollectionsKt.asSequence(`$this$permutate`.getVersions()), CurseforgeSearch::permutate$lambda$2),
                        CurseforgeSearch::permutate$lambda$3
                     )
               )
         );
   }

   private fun ModQuery.paginate(spec: FTModSpec): Flow<ModVersion> {
      return FlowKt.flow(new dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.paginate.1(spec, this, `$this$paginate`, null));
   }

   private suspend fun request(query: ModQuery, index: Int): HttpResponse {
      val `$this$request$iv`: HttpClient = this.getClient();
      val `urlString$iv`: java.lang.String = "https://api.curseforge.com/v1/mods/${query.getId()}/files";
      val `builder$iv$iv$iv`: HttpRequestBuilder = new HttpRequestBuilder();
      HttpRequestKt.url(`builder$iv$iv$iv`, `urlString$iv`);
      UtilsKt.header(`builder$iv$iv$iv`, "x-api-key", "$2a$10$wuAJuNZuted3NORVmpgUC.m8sI.pv1tOPKZyBgLFGjxFp/br0lZCC");
      HttpMessagePropertiesKt.contentType(`builder$iv$iv$iv`, ContentType.Application.INSTANCE.getJson());
      `builder$iv$iv$iv`.url(CurseforgeSearch::request$lambda$0$0);
      return new HttpStatement(`builder$iv$iv$iv`, `$this$request$iv`).execute(`$completion`);
   }

   private fun String.enum(): Int {
      val var2: Int = ArraysKt.indexOf(CurseforgeSearchKt.access$getLOADERS$p(), `$this$enum`);
      if (var2 < 0) {
         throw new IllegalArgumentException(("Unsupported loader type '$`$this$enum`'").toString());
      } else {
         return var2;
      }
   }

   @JvmStatic
   fun `get$lambda$0`(`this$0`: CurseforgeSearch, `$spec`: FTModSpec, it: ModQuery): Flow {
      return `this$0`.paginate(it, `$spec`);
   }

   @JvmStatic
   fun `id$lambda$0$0`(`$slug`: java.lang.String, `$this$url`: URLBuilder, it: URLBuilder): Unit {
      `$this$url`.getParameters().append("gameId", "432");
      `$this$url`.getParameters().append("slug", `$slug`);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `permutate$lambda$0`(`$this_permutate`: ModQuery, it: java.lang.String): ModQuery {
      return new ModQuery(`$this_permutate`.getId(), `$this_permutate`.getProvider(), SetsKt.emptySet(), SetsKt.setOf(it));
   }

   @JvmStatic
   fun `permutate$lambda$1`(`$this_permutate`: ModQuery, it: java.lang.String): ModQuery {
      return new ModQuery(`$this_permutate`.getId(), `$this_permutate`.getProvider(), SetsKt.setOf(it), SetsKt.emptySet());
   }

   @JvmStatic
   fun `permutate$lambda$2`(`$this_permutate`: ModQuery, v: java.lang.String): java.lang.Iterable {
      val `$this$map$iv`: java.lang.Iterable = `$this_permutate`.getLoaders();
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$map$iv`, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add(TuplesKt.to(v, `item$iv$iv` as java.lang.String));
      }

      return `destination$iv$iv`;
   }

   @JvmStatic
   fun `permutate$lambda$3`(`$this_permutate`: ModQuery, `<destruct>`: Pair): ModQuery {
      return new ModQuery(
         `$this_permutate`.getId(),
         `$this_permutate`.getProvider(),
         SetsKt.setOf(`<destruct>`.component1() as java.lang.String),
         SetsKt.setOf(`<destruct>`.component2() as java.lang.String)
      );
   }

   @JvmStatic
   fun `request$lambda$0$0`(`$query`: ModQuery, `this$0`: CurseforgeSearch, `$index`: Int, `$this$url`: URLBuilder, it: URLBuilder): Unit {
      if (!`$query`.getVersions().isEmpty()) {
         `$this$url`.getParameters().append("gameVersion", CollectionsKt.first(`$query`.getVersions()));
      }

      if (!`$query`.getLoaders().isEmpty()) {
         `$this$url`.getParameters().append("modLoaderType", java.lang.String.valueOf(`this$0`.enum(CollectionsKt.first(`$query`.getLoaders()))));
      }

      if (`$index` > 0) {
         `$this$url`.getParameters().append("index", java.lang.String.valueOf(`$index`));
      }

      return Unit.INSTANCE;
   }

   @Serializable
   private data class CurseforgeDependency(modId: Int, relationType: Int) {
      public final val modId: Int
      public final val relationType: Int

      public final val resolvedDependencyType: DependencyType?
         public final get() {
            var var10000: ModDependency.DependencyType;
            switch (this.relationType) {
               case 1:
               case 6:
                  var10000 = ModDependency.DependencyType.EMBEDDED;
                  break;
               case 2:
                  var10000 = ModDependency.DependencyType.OPTIONAL;
                  break;
               case 3:
                  var10000 = ModDependency.DependencyType.REQUIRED;
                  break;
               case 4:
               case 5:
               default:
                  var10000 = null;
            }

            return var10000;
         }


      init {
         this.modId = modId;
         this.relationType = relationType;
      }

      public fun asModDependency(): ModDependency? {
         val var10000: ModDependency.DependencyType = this.getResolvedDependencyType();
         return if (var10000 != null) new ModDependency.Project(java.lang.String.valueOf(this.modId), var10000) else null;
      }

      public operator fun component1(): Int {
         return this.modId;
      }

      public operator fun component2(): Int {
         return this.relationType;
      }

      public fun copy(modId: Int = this.modId, relationType: Int = this.relationType): dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeDependency {
         return new CurseforgeSearch.CurseforgeDependency(modId, relationType);
      }

      public override fun toString(): String {
         return "CurseforgeDependency(modId=${this.modId}, relationType=${this.relationType})";
      }

      public override fun hashCode(): Int {
         return Integer.hashCode(this.modId) * 31 + Integer.hashCode(this.relationType);
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is CurseforgeSearch.CurseforgeDependency) {
            return false;
         } else {
            val var2: CurseforgeSearch.CurseforgeDependency = other as CurseforgeSearch.CurseforgeDependency;
            if (this.modId != (other as CurseforgeSearch.CurseforgeDependency).modId) {
               return false;
            } else {
               return this.relationType == var2.relationType;
            }
         }
      }

      public companion object {
         public fun serializer(): KSerializer<dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeDependency> {
            return CurseforgeSearch.CurseforgeDependency.$serializer.INSTANCE;
         }
      }
   }

   @Serializable
   private data class CurseforgePagination(index: Int, pageSize: Int, resultCount: Int, totalCount: Int) {
      public final val index: Int
      public final val pageSize: Int
      public final val resultCount: Int
      public final val totalCount: Int

      init {
         this.index = index;
         this.pageSize = pageSize;
         this.resultCount = resultCount;
         this.totalCount = totalCount;
      }

      public operator fun component1(): Int {
         return this.index;
      }

      public operator fun component2(): Int {
         return this.pageSize;
      }

      public operator fun component3(): Int {
         return this.resultCount;
      }

      public operator fun component4(): Int {
         return this.totalCount;
      }

      public fun copy(index: Int = this.index, pageSize: Int = this.pageSize, resultCount: Int = this.resultCount, totalCount: Int = this.totalCount): dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgePagination {
         return new CurseforgeSearch.CurseforgePagination(index, pageSize, resultCount, totalCount);
      }

      public override fun toString(): String {
         return "CurseforgePagination(index=${this.index}, pageSize=${this.pageSize}, resultCount=${this.resultCount}, totalCount=${this.totalCount})";
      }

      public override fun hashCode(): Int {
         return ((Integer.hashCode(this.index) * 31 + Integer.hashCode(this.pageSize)) * 31 + Integer.hashCode(this.resultCount)) * 31
            + Integer.hashCode(this.totalCount);
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is CurseforgeSearch.CurseforgePagination) {
            return false;
         } else {
            val var2: CurseforgeSearch.CurseforgePagination = other as CurseforgeSearch.CurseforgePagination;
            if (this.index != (other as CurseforgeSearch.CurseforgePagination).index) {
               return false;
            } else if (this.pageSize != var2.pageSize) {
               return false;
            } else if (this.resultCount != var2.resultCount) {
               return false;
            } else {
               return this.totalCount == var2.totalCount;
            }
         }
      }

      public companion object {
         public fun serializer(): KSerializer<dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgePagination> {
            return CurseforgeSearch.CurseforgePagination.$serializer.INSTANCE;
         }
      }
   }

   @Serializable
   @SourceDebugExtension(["SMAP\nCurseforgeSearch.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CurseforgeSearch.kt\ndev/kikugie/fletching_table/extension/dependency/search/CurseforgeSearch$CurseforgeProject\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,164:1\n1617#2,9:165\n1869#2:174\n1870#2:177\n1626#2:178\n774#2:179\n865#2,2:180\n1617#2,9:182\n1869#2:191\n1870#2:193\n1626#2:194\n1#3:175\n1#3:176\n1#3:192\n*S KotlinDebug\n*F\n+ 1 CurseforgeSearch.kt\ndev/kikugie/fletching_table/extension/dependency/search/CurseforgeSearch$CurseforgeProject\n*L\n134#1:165,9\n134#1:174\n134#1:177\n134#1:178\n137#1:179\n137#1:180,2\n139#1:182,9\n139#1:191\n139#1:193\n139#1:194\n134#1:176\n139#1:192\n*E\n"])
   private data class CurseforgeProject(id: Int,
      modId: Int,
      fileName: String,
      downloadUrl: String,
      isAvailable: Boolean,
      fileStatus: Int,
      releaseType: Int,
      gameVersions: Set<String>,
      dependencies: List<dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeDependency> = CollectionsKt.emptyList()
   ) {
      public final val id: Int
      public final val modId: Int
      public final val fileName: String
      public final val downloadUrl: String
      public final val isAvailable: Boolean
      public final val fileStatus: Int
      public final val releaseType: Int
      public final val gameVersions: Set<String>
      public final val dependencies: List<dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeDependency>

      public final val canBeUsed: Boolean
         public final get() {
            return this.isAvailable && this.fileStatus == 4 || this.fileStatus == 10;
         }


      public final val resolvedReleaseType: ReleaseType
         public final get() {
            var var10000: ModVersion.ReleaseType;
            switch (this.releaseType) {
               case 1:
                  var10000 = ModVersion.ReleaseType.STABLE;
                  break;
               case 2:
                  var10000 = ModVersion.ReleaseType.BETA;
                  break;
               case 3:
                  var10000 = ModVersion.ReleaseType.ALPHA;
                  break;
               default:
                  throw new IllegalStateException(("Unsupported release type ${this.releaseType}").toString());
            }

            return var10000;
         }


      public final val resolvedModLoaders: Set<String>
         public final get() {
            val `$this$mapNotNull$iv`: java.lang.Iterable = this.gameVersions;
            val `destination$iv$iv`: java.util.Collection = new ArrayList();

            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
               var var10000: java.lang.String = (`element$iv$iv$iv` as java.lang.String).toLowerCase(Locale.ROOT);
               var10000 = if (ArraysKt.contains(CurseforgeSearchKt.access$getLOADERS$p(), var10000)) var10000 else null;
               if (var10000 != null) {
                  `destination$iv$iv`.add(var10000);
               }
            }

            return CollectionsKt.toSet(`destination$iv$iv`);
         }


      public final val resolvedGameVersions: Set<String>
         public final get() {
            val `$this$filter$iv`: java.lang.Iterable = this.gameVersions;
            val `destination$iv$iv`: java.util.Collection = new ArrayList();

            for (Object element$iv$iv : $this$filter$iv) {
               if (Character.isDigit(dev.kikugie.commons.text.StringsKt.getOrDefault$default(`element$iv$iv` as java.lang.String, 0, '\u0000', 2, null))) {
                  `destination$iv$iv`.add(`element$iv$iv`);
               }
            }

            return CollectionsKt.toSet(`destination$iv$iv`);
         }


      init {
         this.id = id;
         this.modId = modId;
         this.fileName = fileName;
         this.downloadUrl = downloadUrl;
         this.isAvailable = isAvailable;
         this.fileStatus = fileStatus;
         this.releaseType = releaseType;
         this.gameVersions = gameVersions;
         this.dependencies = dependencies;
      }

      public fun asModVersion(): ModVersion? {
         val var10000: ModVersion;
         if (!this.getCanBeUsed()) {
            var10000 = null;
         } else {
            val `$this$mapNotNull$iv`: java.lang.Iterable = this.dependencies;
            val `destination$iv$iv`: java.util.Collection = new ArrayList();

            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
               val var17: ModDependency = (`element$iv$iv$iv` as CurseforgeSearch.CurseforgeDependency).asModDependency();
               if (var17 != null) {
                  `destination$iv$iv`.add(var17);
               }
            }

            var10000 = new ModVersion(
               java.lang.String.valueOf(this.id),
               java.lang.String.valueOf(this.modId),
               this.fileName,
               this.downloadUrl,
               this.getResolvedReleaseType(),
               this.getResolvedModLoaders(),
               this.getResolvedGameVersions(),
               `destination$iv$iv` as MutableList<ModDependency>
            );
         }

         return var10000;
      }

      public operator fun component1(): Int {
         return this.id;
      }

      public operator fun component2(): Int {
         return this.modId;
      }

      public operator fun component3(): String {
         return this.fileName;
      }

      public operator fun component4(): String {
         return this.downloadUrl;
      }

      public operator fun component5(): Boolean {
         return this.isAvailable;
      }

      public operator fun component6(): Int {
         return this.fileStatus;
      }

      public operator fun component7(): Int {
         return this.releaseType;
      }

      public operator fun component8(): Set<String> {
         return this.gameVersions;
      }

      public operator fun component9(): List<dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeDependency> {
         return this.dependencies;
      }

      public fun copy(
         id: Int = this.id,
         modId: Int = this.modId,
         fileName: String = this.fileName,
         downloadUrl: String = this.downloadUrl,
         isAvailable: Boolean = this.isAvailable,
         fileStatus: Int = this.fileStatus,
         releaseType: Int = this.releaseType,
         gameVersions: Set<String> = this.gameVersions,
         dependencies: List<dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeDependency> = this.dependencies
      ): dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeProject {
         return new CurseforgeSearch.CurseforgeProject(id, modId, fileName, downloadUrl, isAvailable, fileStatus, releaseType, gameVersions, dependencies);
      }

      public override fun toString(): String {
         return "CurseforgeProject(id=${this.id}, modId=${this.modId}, fileName=${this.fileName}, downloadUrl=${this.downloadUrl}, isAvailable=${this.isAvailable}, fileStatus=${this.fileStatus}, releaseType=${this.releaseType}, gameVersions=${this.gameVersions}, dependencies=${this.dependencies})";
      }

      public override fun hashCode(): Int {
         return (
                  (
                           (
                                    (
                                             (
                                                      ((Integer.hashCode(this.id) * 31 + Integer.hashCode(this.modId)) * 31 + this.fileName.hashCode()) * 31
                                                         + this.downloadUrl.hashCode()
                                                   )
                                                   * 31
                                                + java.lang.Boolean.hashCode(this.isAvailable)
                                          )
                                          * 31
                                       + Integer.hashCode(this.fileStatus)
                                 )
                                 * 31
                              + Integer.hashCode(this.releaseType)
                        )
                        * 31
                     + this.gameVersions.hashCode()
               )
               * 31
            + this.dependencies.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is CurseforgeSearch.CurseforgeProject) {
            return false;
         } else {
            val var2: CurseforgeSearch.CurseforgeProject = other as CurseforgeSearch.CurseforgeProject;
            if (this.id != (other as CurseforgeSearch.CurseforgeProject).id) {
               return false;
            } else if (this.modId != var2.modId) {
               return false;
            } else if (!(this.fileName == var2.fileName)) {
               return false;
            } else if (!(this.downloadUrl == var2.downloadUrl)) {
               return false;
            } else if (this.isAvailable != var2.isAvailable) {
               return false;
            } else if (this.fileStatus != var2.fileStatus) {
               return false;
            } else if (this.releaseType != var2.releaseType) {
               return false;
            } else if (!(this.gameVersions == var2.gameVersions)) {
               return false;
            } else {
               return this.dependencies == var2.dependencies;
            }
         }
      }

      public companion object {
         public fun serializer(): KSerializer<dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeProject> {
            return CurseforgeSearch.CurseforgeProject.$serializer.INSTANCE;
         }
      }
   }

   @Serializable
   private data class CurseforgeResponse(data: List<dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeProject>,
      pagination: dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgePagination
   ) {
      public final val data: List<dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeProject>
      public final val pagination: dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgePagination

      init {
         this.data = data;
         this.pagination = pagination;
      }

      public operator fun component1(): List<dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeProject> {
         return this.data;
      }

      public operator fun component2(): dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgePagination {
         return this.pagination;
      }

      public fun copy(
         data: List<dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeProject> = this.data,
         pagination: dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgePagination = this.pagination
      ): dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeResponse {
         return new CurseforgeSearch.CurseforgeResponse(data, pagination);
      }

      public override fun toString(): String {
         return "CurseforgeResponse(data=${this.data}, pagination=${this.pagination})";
      }

      public override fun hashCode(): Int {
         return this.data.hashCode() * 31 + this.pagination.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is CurseforgeSearch.CurseforgeResponse) {
            return false;
         } else {
            val var2: CurseforgeSearch.CurseforgeResponse = other as CurseforgeSearch.CurseforgeResponse;
            if (!(this.data == (other as CurseforgeSearch.CurseforgeResponse).data)) {
               return false;
            } else {
               return this.pagination == var2.pagination;
            }
         }
      }

      public companion object {
         public fun serializer(): KSerializer<dev.kikugie.fletching_table.extension.dependency.search.CurseforgeSearch.CurseforgeResponse> {
            return CurseforgeSearch.CurseforgeResponse.$serializer.INSTANCE;
         }
      }
   }
}
