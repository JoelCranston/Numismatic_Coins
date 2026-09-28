package io.ktor.events

import org.slf4j.Logger

public fun <T> Events.raiseCatching(definition: EventDefinition<Any>, value: Any, logger: Logger? = null) {
   try {
      `$this$raiseCatching`.raise(definition, value);
   } catch (var5: java.lang.Throwable) {
      if (logger != null) {
         logger.error("Some handlers have thrown an exception", var5);
      }
   }
}

@JvmSynthetic
fun `raiseCatching$default`(var0: Events, var1: EventDefinition, var2: Any, var3: Logger, var4: Int, var5: Any) {
   if ((var4 and 4) != 0) {
      var3 = null;
   }

   raiseCatching(var0, var1, var2, var3);
}
