package kotlinx.serialization.json.internal

import kotlinx.serialization.json.Json

internal fun Composer(sb: InternalJsonWriter, json: Json): Composer {
   return if (json.getConfiguration().getPrettyPrint()) new ComposerWithPrettyPrint(sb, json) else new Composer(sb);
}
