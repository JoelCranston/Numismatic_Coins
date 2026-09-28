package it.krzeminski.snakeyaml.engine.kmp.exceptions

import it.krzeminski.snakeyaml.engine.kmp.common.SpecVersion

public class YamlVersionException(specVersion: SpecVersion) : YamlEngineException(specVersion.toString())
