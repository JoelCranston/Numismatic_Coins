package com.charleskorn.kaml

import kotlin.jvm.internal.Intrinsics
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@JvmSynthetic
private inline fun <reified I : YamlInput> Decoder.asYamlInput(): I {
   Intrinsics.reifiedOperationMarker(2, "I");
   val var10000: YamlInput = `$this$asYamlInput` as YamlInput;
   if (`$this$asYamlInput` as YamlInput == null) {
      Intrinsics.reifiedOperationMarker(4, "I");
      throw new IllegalStateException(
         ("This serializer can be used only with Yaml format. Expected Decoder to be ${(YamlInput::class).getSimpleName()}, got ${`$this$asYamlInput`.getClass()::class}")
            .toString()
      );
   } else {
      return (I)(var10000 as YamlInput);
   }
}

private fun Encoder.asYamlOutput(): YamlOutput {
   val var10000: YamlOutput = `$this$asYamlOutput` as? YamlOutput;
   if ((`$this$asYamlOutput` as? YamlOutput) == null) {
      throw new IllegalStateException(
         ("This serializer can be used only with Yaml format. Expected Encoder to be YamlOutput, got ${`$this$asYamlOutput`.getClass()::class}").toString()
      );
   } else {
      return var10000;
   }
}

@JvmSynthetic
fun `access$asYamlOutput`(`$receiver`: Encoder): YamlOutput {
   return asYamlOutput(`$receiver`);
}
