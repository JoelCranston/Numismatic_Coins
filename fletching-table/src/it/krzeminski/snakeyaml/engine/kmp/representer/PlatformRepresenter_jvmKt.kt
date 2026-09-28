package it.krzeminski.snakeyaml.engine.kmp.representer

import it.krzeminski.snakeyaml.engine.kmp.api.DumpSettings

public fun Representer(settings: DumpSettings): Representer {
   return new StandardRepresenter(settings);
}
