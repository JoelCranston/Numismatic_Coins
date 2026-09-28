package dev.kikugie.fletching_table.transformer.accessconverter

import dev.kikugie.fletching_table.transformer.accessconverter.Aw2AtConversionKt.collect.1
import dev.kikugie.fletching_table.transformer.accessconverter.Aw2AtConversionKt.collect.2
import dev.kikugie.fletching_table.transformer.accessconverter.Aw2AtConversionKt.collect.3
import java.util.LinkedHashMap

private final val mutability: AwTokenType
   private final get() {
      return if (`$this$mutability` === AwTokenType.EXTENDABLE) AwTokenType.MUTABLE else `$this$mutability`;
   }


internal fun AccessTransformer.collect(): String {
   val classes: java.util.Map = new LinkedHashMap();
   val methods: java.util.Map = new LinkedHashMap();
   val fields: java.util.Map = new LinkedHashMap();

   for (AccessTransformer.Entry it : $this$collect.getEntries()) {
      if (`$this$collect_u24lambda_u240` is AccessTransformer.ClassEntry) {
         classes.compute((`$this$collect_u24lambda_u240` as AccessTransformer.ClassEntry).getClassName(), new 1(`$this$collect_u24lambda_u240`));
      } else if (`$this$collect_u24lambda_u240` is AccessTransformer.FieldEntry) {
         val method: AccessTransformer.FieldEntry = fields.compute(
            "${(`$this$collect_u24lambda_u240` as AccessTransformer.FieldEntry).getClassName()}${(`$this$collect_u24lambda_u240` as AccessTransformer.FieldEntry)
               .getFieldName()}",
            new dev.kikugie.fletching_table.transformer.accessconverter.Aw2AtConversionKt.collect.field.1<>(`$this$collect_u24lambda_u240`)
         );
         if (method.getVisibility() != AwTokenType.MUTABLE) {
            classes.compute((`$this$collect_u24lambda_u240` as AccessTransformer.FieldEntry).getClassName(), new 2(method, `$this$collect_u24lambda_u240`));
         }
      } else {
         if (`$this$collect_u24lambda_u240` !is AccessTransformer.MethodEntry) {
            throw new NoWhenBranchMatchedException();
         }

         classes.compute(
            (`$this$collect_u24lambda_u240` as AccessTransformer.MethodEntry).getClassName(),
            new 3(
               methods.compute(
                  "${(`$this$collect_u24lambda_u240` as AccessTransformer.MethodEntry).getClassName()}${(`$this$collect_u24lambda_u240` as AccessTransformer.MethodEntry)
                     .getMethodName()}${(`$this$collect_u24lambda_u240` as AccessTransformer.MethodEntry).getMethodDesc()}",
                  new dev.kikugie.fletching_table.transformer.accessconverter.Aw2AtConversionKt.collect.method.1<>(`$this$collect_u24lambda_u240`)
               ),
               `$this$collect_u24lambda_u240`
            )
         );
      }
   }

   val var9: StringBuilder = new StringBuilder();
   val var10: StringBuilder = var9;

   for (AccessTransformer.ClassEntry itx : classes.values()) {
      var10.append(itx.toAtString()).append('\n');
   }

   for (AccessTransformer.MethodEntry itx : methods.values()) {
      var10.append(itx.toAtString()).append('\n');
   }

   for (AccessTransformer.FieldEntry itx : fields.values()) {
      var10.append(itx.toAtString()).append('\n');
   }

   return var9.toString();
}

@JvmSynthetic
fun `access$getMutability`(`$receiver`: AwTokenType): AwTokenType {
   return getMutability(`$receiver`);
}
