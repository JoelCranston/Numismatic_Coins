package io.ktor.serialization.kotlinx.json

import io.ktor.http.ContentType
import io.ktor.serialization.Configuration
import io.ktor.serialization.kotlinx.KotlinxSerializationConverterKt
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonKt

public final val DefaultJson: Json = JsonKt.Json$default(null, JsonSupportKt::DefaultJson$lambda$0, 1, null)

public fun Configuration.json(json: Json = DefaultJson, contentType: ContentType = ContentType.Application.INSTANCE.getJson()) {
   KotlinxSerializationConverterKt.serialization(`$this$json`, contentType, json);
}

@JvmSynthetic
fun `json$default`(var0: Configuration, var1: Json, var2: ContentType, var3: Int, var4: Any) {
   if ((var3 and 1) != 0) {
      var1 = DefaultJson;
   }

   if ((var3 and 2) != 0) {
      var2 = ContentType.Application.INSTANCE.getJson();
   }

   json(var0, var1, var2);
}

@ExperimentalSerializationApi
public fun Configuration.jsonIo(json: Json = DefaultJson, contentType: ContentType = ContentType.Application.INSTANCE.getJson()) {
   Configuration.register$default(`$this$jsonIo`, contentType, new ExperimentalJsonConverter(json), null, 4, null);
}

@JvmSynthetic
fun `jsonIo$default`(var0: Configuration, var1: Json, var2: ContentType, var3: Int, var4: Any) {
   if ((var3 and 1) != 0) {
      var1 = DefaultJson;
   }

   if ((var3 and 2) != 0) {
      var2 = ContentType.Application.INSTANCE.getJson();
   }

   jsonIo(var0, var1, var2);
}

fun JsonBuilder.`DefaultJson$lambda$0`(): Unit {
   `$this$Json`.setEncodeDefaults(true);
   `$this$Json`.setLenient(true);
   `$this$Json`.setAllowSpecialFloatingPointValues(true);
   `$this$Json`.setAllowStructuredMapKeys(true);
   `$this$Json`.setPrettyPrint(false);
   `$this$Json`.setUseArrayPolymorphism(false);
   return Unit.INSTANCE;
}
