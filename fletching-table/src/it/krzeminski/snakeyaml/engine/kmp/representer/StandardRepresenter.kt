package it.krzeminski.snakeyaml.engine.kmp.representer

import it.krzeminski.snakeyaml.engine.kmp.api.DumpSettings
import it.krzeminski.snakeyaml.engine.kmp.api.RepresentToNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import java.math.BigInteger
import java.util.Optional
import java.util.UUID
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.optionals.OptionalsKt

@SourceDebugExtension(["SMAP\nStandardRepresenter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StandardRepresenter.kt\nit/krzeminski/snakeyaml/engine/kmp/representer/StandardRepresenter\n+ 2 CommonRepresenter.kt\nit/krzeminski/snakeyaml/engine/kmp/representer/CommonRepresenter\n*L\n1#1,82:1\n223#2:83\n223#2:84\n*S KotlinDebug\n*F\n+ 1 StandardRepresenter.kt\nit/krzeminski/snakeyaml/engine/kmp/representer/StandardRepresenter\n*L\n39#1:83\n50#1:84\n*E\n"])
public open class StandardRepresenter(settings: DumpSettings) : CommonRepresenter(settings) {
   private final val settings: DumpSettings
   private final val representJvmNumber: RepresentToNode
   private final val representUuid: RepresentToNode
   private final val representOptional: RepresentToNode

   init {
      this.settings = settings;
      this.representJvmNumber = StandardRepresenter::representJvmNumber$lambda$1;
      this.representUuid = StandardRepresenter::representUuid$lambda$3;
      this.representOptional = StandardRepresenter::representOptional$lambda$5;
      this.representers.putAll(MapsKt.mapOf(new Pair[]{TuplesKt.to(UUID::class, this.representUuid), TuplesKt.to(Optional::class, this.representOptional)}));
      this.parentClassRepresenters.putAll(MapsKt.mapOf(TuplesKt.to(java.lang.Number::class, this.representJvmNumber)));
   }

   @JvmStatic
   fun `representJvmNumber$lambda$1`(`this$0`: StandardRepresenter, data: Any): Node {
      val var9: Node;
      if (data is BigInteger) {
         val var10000: java.lang.String = (data as BigInteger).toString();
         val var8: BaseRepresenter = `this$0`;
         var var10001: Any = CommonRepresenter.access$getClassTags(`this$0`).get(data.getClass()::class);
         if (var10001 == null) {
            var10001 = Tag.INT;
         }

         var9 = BaseRepresenter.representScalar$default(var8, var10001 as Tag, var10000, null, 4, null);
      } else {
         var9 = `this$0`.getRepresentNumber().representData(data);
      }

      return var9;
   }

   @JvmStatic
   fun `representUuid$lambda$3`(`this$0`: StandardRepresenter, data: Any): Node {
      val var10000: BaseRepresenter = `this$0`;
      var var10001: Any = CommonRepresenter.access$getClassTags(`this$0`).get(data.getClass()::class);
      if (var10001 == null) {
         var10001 = Tag.Companion.forType("java.util.UUID");
      }

      return BaseRepresenter.representScalar$default(var10000, var10001 as Tag, data.toString(), null, 4, null);
   }

   @JvmStatic
   fun `representOptional$lambda$5`(`this$0`: StandardRepresenter, data: Any): Node {
      val opt: Any = OptionalsKt.getOrNull(data as Optional);
      val var10000: Node;
      if (opt != null) {
         val var3: Node = `this$0`.represent(opt);
         var3.setTag(Tag.Companion.forType("java.util.Optional"));
         var10000 = var3;
      } else {
         var10000 = `this$0`.nullRepresenter();
      }

      return var10000;
   }
}
