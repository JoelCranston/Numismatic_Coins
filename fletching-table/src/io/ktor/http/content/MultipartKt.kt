package io.ktor.http.content

import io.ktor.http.content.MultipartKt.asFlow.1
import io.ktor.http.content.MultipartKt.sam.kotlinx_coroutines_flow_FlowCollector.0
import java.util.ArrayList
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowKt

public fun MultiPartData.asFlow(): Flow<PartData> {
   return FlowKt.flow(new 1(`$this$asFlow`, null));
}

public suspend fun MultiPartData.forEachPart(partHandler: (PartData, Continuation<Unit>) -> Any?) {
   val var10000: Any = asFlow(`$this$forEachPart`).collect(new 0(partHandler), `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@Deprecated(message = "This method can deadlock on large requests. Use `forEachPart` instead.", level = DeprecationLevel.ERROR)
public suspend fun MultiPartData.readAllParts(): List<PartData> {
   var `$continuation`: Continuation;
   label48: {
      if (`$completion` is io.ktor.http.content.MultipartKt.readAllParts.1) {
         `$continuation` = `$completion` as io.ktor.http.content.MultipartKt.readAllParts.1;
         if (((`$completion` as io.ktor.http.content.MultipartKt.readAllParts.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label48;
         }
      }

      `$continuation` = new io.ktor.http.content.MultipartKt.readAllParts.1(`$completion`);
   }

   var parts: ArrayList;
   var var6: Any;
   var var7: PartData;
   label51: {
      val `$result`: Any = `$continuation`.result;
      var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var8: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.L$0 = `$this$readAllParts`;
            `$continuation`.label = 1;
            var8 = `$this$readAllParts`.readPart(`$continuation`);
            if (var8 === var6) {
               return var6;
            }
            break;
         case 1:
            `$this$readAllParts` = `$continuation`.L$0 as MultiPartData;
            ResultKt.throwOnFailure(`$result`);
            var8 = `$result`;
            break;
         case 2:
            parts = `$continuation`.L$2 as ArrayList;
            var7 = `$continuation`.L$1 as PartData;
            `$this$readAllParts` = `$continuation`.L$0 as MultiPartData;
            ResultKt.throwOnFailure(`$result`);
            var8 = `$result` as PartData;
            if (`$result` as PartData == null) {
               return parts;
            }

            var7 = (PartData)var8;
            parts.add(var8);
            break label51;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      var8 = var8 as PartData;
      if (var8 as PartData == null) {
         return CollectionsKt.emptyList();
      }

      var7 = (PartData)var8;
      parts = new ArrayList();
      parts.add(var8);
   }

   while (true) {
      `$continuation`.L$0 = `$this$readAllParts`;
      `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var7);
      `$continuation`.L$2 = parts;
      `$continuation`.label = 2;
      var var10: Any = `$this$readAllParts`.readPart(`$continuation`);
      if (var10 === var6) {
         return var6;
      }

      var10 = var10 as PartData;
      if (var10 as PartData == null) {
         return parts;
      }

      var7 = (PartData)var10;
      parts.add(var10);
   }
}
