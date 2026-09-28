package it.krzeminski.snakeyaml.engine.kmp.resolver

import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import java.util.ArrayList
import java.util.LinkedHashMap
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nBaseScalarResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseScalarResolver.kt\nit/krzeminski/snakeyaml/engine/kmp/resolver/BaseScalarResolver\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,96:1\n295#2,2:97\n*S KotlinDebug\n*F\n+ 1 BaseScalarResolver.kt\nit/krzeminski/snakeyaml/engine/kmp/resolver/BaseScalarResolver\n*L\n48#1:97,2\n*E\n"])
public abstract class BaseScalarResolver : ScalarResolver {
   private final val yamlImplicitResolvers: Map<Char?, List<ResolverTuple>>

   @JvmOverloads
   open fun BaseScalarResolver(buildImplicitResolvers: (BaseScalarResolver.ImplicitResolversBuilder?) -> Unit) {
      val var2: BaseScalarResolver.ImplicitResolversBuilder = new BaseScalarResolver.ImplicitResolversBuilder();
      buildImplicitResolvers.invoke(var2);
      this.yamlImplicitResolvers = var2.resolvers$snakeyaml_engine_kmp();
   }

   public override fun resolve(value: String, implicit: Boolean): Tag {
      if (!implicit) {
         return Tag.STR;
      } else {
         var var10000: Character = StringsKt.getOrNull(value, 0);
         var var13: java.util.List = this.yamlImplicitResolvers.get(Character.valueOf((char)(var10000 ?: 0)));
         if (var13 == null) {
            var13 = this.yamlImplicitResolvers.get(null);
            if (var13 == null) {
               var13 = CollectionsKt.emptyList();
            }
         }

         val var8: java.util.Iterator = var13.iterator();

         while (true) {
            if (var8.hasNext()) {
               val `element$iv`: Any = var8.next();
               if (!(`element$iv` as ResolverTuple).getRegexp().matches(value)) {
                  continue;
               }

               var10000 = (Character)`element$iv`;
               break;
            }

            var10000 = null;
            break;
         }

         val var5: ResolverTuple = var10000 as ResolverTuple;
         if (var10000 as ResolverTuple != null) {
            val var12: Tag = var5.getTag();
            if (var12 != null) {
               return var12;
            }
         }

         return Tag.STR;
      }
   }

   @JvmOverloads
   open fun BaseScalarResolver() {
      this(null, 1, null);
   }

   @JvmStatic
   fun `_init_$lambda$0`(var0: BaseScalarResolver.ImplicitResolversBuilder): Unit {
      return Unit.INSTANCE;
   }

   public companion object {
      public final val EMPTY: Regex
      public final val ENV_FORMAT: Regex
   }

   @SourceDebugExtension(["SMAP\nBaseScalarResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseScalarResolver.kt\nit/krzeminski/snakeyaml/engine/kmp/resolver/BaseScalarResolver$ImplicitResolversBuilder\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,96:1\n11308#2:97\n11643#2,3:98\n382#3,7:101\n*S KotlinDebug\n*F\n+ 1 BaseScalarResolver.kt\nit/krzeminski/snakeyaml/engine/kmp/resolver/BaseScalarResolver$ImplicitResolversBuilder\n*L\n69#1:97\n69#1:98,3\n76#1:101,7\n*E\n"])
   public class ImplicitResolversBuilder {
      private final val resolvers: MutableMap<Char?, MutableList<ResolverTuple>> = (new LinkedHashMap()) as java.util.Map

      internal fun resolvers(): Map<Char?, MutableList<ResolverTuple>> {
         return MapsKt.toMap(this.resolvers);
      }

      public fun addImplicitResolver(tag: Tag, regexp: Regex, first: String?) {
         var var24: java.util.List;
         label38: {
            if (first != null) {
               val var10000: CharArray = first.toCharArray();
               if (var10000 != null) {
                  val `$i$f$getOrPut`: java.util.Collection = new ArrayList(var10000.length);

                  val var5: CharArray;
                  for (char item$iv$iv : var5) {
                     `$i$f$getOrPut`.add(if (`item$iv$iv` == 0) null else `item$iv$iv`);
                  }

                  var24 = `$i$f$getOrPut` as java.util.List;
                  break label38;
               }
            }

            var24 = CollectionsKt.listOf(null);
         }

         for (Character key : CollectionsKt.distinct(var24)) {
            val var19: java.util.Map = this.resolvers;
            val var21: Any = this.resolvers.get(key);
            if (var21 == null) {
               val var23: Any = new ArrayList();
               var19.put(key, var23);
               var24 = (java.util.List)var23;
            } else {
               var24 = (java.util.List)var21;
            }

            var24.add(new ResolverTuple(tag, regexp));
         }
      }
   }
}
