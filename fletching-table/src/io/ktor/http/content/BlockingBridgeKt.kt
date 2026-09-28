package io.ktor.http.content

import io.ktor.http.content.BlockingBridgeKt.withBlockingAndRedispatch.2
import java.lang.reflect.Method
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function1
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.Dispatchers

private final val isParkingAllowedFunction: Method? by LazyKt.lazy(BlockingBridgeKt::isParkingAllowedFunction_delegate$lambda$0)
   private final get() {
      return isParkingAllowedFunction$delegate.getValue() as Method;
   }


internal suspend fun withBlocking(block: (Continuation<Unit>) -> Any?) {
   if (safeToRunInPlace()) {
      val var2: Any = block.invoke(`$completion`);
      return if (var2 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var2 else Unit.INSTANCE;
   } else {
      val var10000: Any = withBlockingAndRedispatch(block, `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }
}

private fun safeToRunInPlace(): Boolean {
   var var0: Boolean;
   try {
      val var10000: Method = isParkingAllowedFunction();
      var0 = var10000 != null && var10000.invoke(null) == true;
   } catch (var2: java.lang.Throwable) {
      var0 = false;
   }

   return var0;
}

private suspend fun withBlockingAndRedispatch(block: (Continuation<Unit>) -> Any?) {
   val var10000: Any = BuildersKt.withContext(Dispatchers.getIO(), new 2(block, null), `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

fun `isParkingAllowedFunction_delegate$lambda$0`(): Method {
   var var0: Method;
   try {
      var0 = Class.forName("io.ktor.utils.io.jvm.javaio.PollersKt").getMethod("isParkingAllowed");
   } catch (var2: java.lang.Throwable) {
      var0 = null;
   }

   return var0;
}

@JvmSynthetic
fun `access$withBlockingAndRedispatch`(block: Function1, `$completion`: Continuation): Any {
   return withBlockingAndRedispatch(block, `$completion`);
}
