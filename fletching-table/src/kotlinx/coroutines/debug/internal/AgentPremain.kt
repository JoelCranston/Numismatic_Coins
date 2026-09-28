package kotlinx.coroutines.debug.internal

import android.annotation.SuppressLint
import java.lang.instrument.ClassFileTransformer
import java.lang.instrument.Instrumentation
import java.security.ProtectionDomain
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
import sun.misc.Signal

@SuppressLint(["all"])
@IgnoreJRERequirement
internal object AgentPremain {
   private final val enableCreationStackTraces: Boolean

   @JvmStatic
   public fun premain(args: String?, instrumentation: Instrumentation) {
      AgentInstallationType.INSTANCE.setInstalledStatically$kotlinx_coroutines_core(true);
      instrumentation.addTransformer(AgentPremain.DebugProbesTransformer.INSTANCE);
      DebugProbesImpl.INSTANCE.setEnableCreationStackTraces$kotlinx_coroutines_core(enableCreationStackTraces);
      DebugProbesImpl.INSTANCE.install$kotlinx_coroutines_core();
      INSTANCE.installSignalHandler();
   }

   private fun installSignalHandler() {
      try {
         Signal.handle(new Signal("TRAP"), AgentPremain::installSignalHandler$lambda$1);
      } catch (var2: java.lang.Throwable) {
      }
   }

   @JvmStatic
   fun `installSignalHandler$lambda$1`(it: Signal) {
      if (DebugProbesImpl.INSTANCE.isInstalled$kotlinx_coroutines_debug()) {
         DebugProbesImpl.INSTANCE.dumpCoroutines(System.out);
      } else {
         System.out.println("Cannot perform coroutines dump, debug probes are disabled");
      }
   }

   @JvmStatic
   fun {
      val var0: AgentPremain = INSTANCE;

      var `$this$enableCreationStackTraces_u24lambda_u240`: Any;
      try {
         val var10000: java.lang.String = System.getProperty("kotlinx.coroutines.debug.enable.creation.stack.trace");
         `$this$enableCreationStackTraces_u24lambda_u240` = Result.constructor-impl(if (var10000 != null) java.lang.Boolean.parseBoolean(var10000) else null);
      } catch (var3: java.lang.Throwable) {
         `$this$enableCreationStackTraces_u24lambda_u240` = Result.constructor-impl(ResultKt.createFailure(var3));
      }

      val var4: java.lang.Boolean = (
         if (Result.isFailure-impl(`$this$enableCreationStackTraces_u24lambda_u240`)) null else `$this$enableCreationStackTraces_u24lambda_u240`
      ) as java.lang.Boolean;
      enableCreationStackTraces = var4 ?: DebugProbesImpl.INSTANCE.getEnableCreationStackTraces$kotlinx_coroutines_core();
   }

   internal object DebugProbesTransformer : ClassFileTransformer {
      public override fun transform(
         loader: ClassLoader?,
         className: String,
         classBeingRedefined: Class<*>?,
         protectionDomain: ProtectionDomain,
         classfileBuffer: ByteArray?
      ): ByteArray? {
         if (loader != null && className == "kotlin/coroutines/jvm/internal/DebugProbesKt") {
            AgentInstallationType.INSTANCE.setInstalledStatically$kotlinx_coroutines_core(true);
            return ByteStreamsKt.readBytes(loader.getResourceAsStream("DebugProbesKt.bin"));
         } else {
            return null;
         }
      }
   }
}
