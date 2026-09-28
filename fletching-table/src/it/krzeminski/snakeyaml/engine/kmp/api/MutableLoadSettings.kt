package it.krzeminski.snakeyaml.engine.kmp.api

import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings.CollectionProvider
import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings.SpecVersionMutator
import it.krzeminski.snakeyaml.engine.kmp.env.EnvConfig
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.schema.Schema

public interface MutableLoadSettings {
   public var label: String
      internal final set

   public var tagConstructors: Map<Tag, ConstructNode>
      internal final set

   public var defaultList: CollectionProvider<MutableList<Any?>>
      internal final set

   public var defaultSet: CollectionProvider<MutableSet<Any?>>
      internal final set

   public var defaultMap: CollectionProvider<MutableMap<Any?, Any?>>
      internal final set

   public var versionFunction: SpecVersionMutator
      internal final set

   public var bufferSize: Int
      internal final set

   public var allowDuplicateKeys: Boolean
      internal final set

   public var allowRecursiveKeys: Boolean
      internal final set

   public var maxAliasesForCollections: Int
      internal final set

   public var useMarks: Boolean
      internal final set

   public var customProperties: Map<SettingKey, Any>
      internal final set

   public var envConfig: EnvConfig?
      internal final set

   public var parseComments: Boolean
      internal final set

   public var codePointLimit: Int
      internal final set

   public var schema: Schema
      internal final set
}
