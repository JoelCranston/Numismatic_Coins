package kotlinx.serialization.json

import kotlinx.serialization.json.internal.JsonSerializersModuleValidator
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuildersKt

private class JsonImpl(configuration: JsonConfiguration, module: SerializersModule) : Json(configuration, module) {
   init {
      this.validateConfiguration();
   }

   private fun validateConfiguration() {
      if (!(this.getSerializersModule() == SerializersModuleBuildersKt.EmptySerializersModule())) {
         this.getSerializersModule().dumpTo(new JsonSerializersModuleValidator(this.getConfiguration()));
      }
   }
}
