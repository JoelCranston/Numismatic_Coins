package it.krzeminski.snakeyaml.engine.kmp.api

import it.krzeminski.snakeyaml.engine.kmp.common.SpecVersion
import it.krzeminski.snakeyaml.engine.kmp.env.EnvConfig
import it.krzeminski.snakeyaml.engine.kmp.exceptions.YamlVersionException
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.schema.Schema
import it.krzeminski.snakeyaml.engine.kmp.schema.SchemaKt
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.LinkedHashSet

public class LoadSettings(label: String = "reader",
   tagConstructors: Map<Tag, ConstructNode> = MapsKt.emptyMap(),
   defaultList: it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings.CollectionProvider<MutableList<Any?>> = LoadSettings::_init_$lambda$0,
   defaultSet: it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings.CollectionProvider<MutableSet<Any?>> = LoadSettings::_init_$lambda$1,
   defaultMap: it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings.CollectionProvider<MutableMap<Any?, Any?>> = LoadSettings::_init_$lambda$2,
   versionFunction: it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings.SpecVersionMutator = LoadSettings::_init_$lambda$3,
   bufferSize: Int = 1024,
   allowDuplicateKeys: Boolean = false,
   allowRecursiveKeys: Boolean = false,
   maxAliasesForCollections: Int = 50,
   useMarks: Boolean = true,
   customProperties: Map<SettingKey, Any> = MapsKt.emptyMap(),
   envConfig: EnvConfig? = null,
   parseComments: Boolean = false,
   codePointLimit: Int = 3145728,
   schema: Schema = SchemaKt.getDEFAULT_SCHEMA() as Schema
) {
   public final val label: String
   public final val tagConstructors: Map<Tag, ConstructNode>
   public final val defaultList: it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings.CollectionProvider<MutableList<Any?>>
   public final val defaultSet: it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings.CollectionProvider<MutableSet<Any?>>
   public final val defaultMap: it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings.CollectionProvider<MutableMap<Any?, Any?>>
   public final val versionFunction: it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings.SpecVersionMutator
   public final val bufferSize: Int
   public final val allowDuplicateKeys: Boolean
   public final val allowRecursiveKeys: Boolean
   public final val maxAliasesForCollections: Int
   public final val useMarks: Boolean
   public final val customProperties: Map<SettingKey, Any>
   public final val envConfig: EnvConfig?
   public final val parseComments: Boolean
   public final val codePointLimit: Int
   public final val schema: Schema

   init {
      this.label = label;
      this.tagConstructors = tagConstructors;
      this.defaultList = defaultList;
      this.defaultSet = defaultSet;
      this.defaultMap = defaultMap;
      this.versionFunction = versionFunction;
      this.bufferSize = bufferSize;
      this.allowDuplicateKeys = allowDuplicateKeys;
      this.allowRecursiveKeys = allowRecursiveKeys;
      this.maxAliasesForCollections = maxAliasesForCollections;
      this.useMarks = useMarks;
      this.customProperties = customProperties;
      this.envConfig = envConfig;
      this.parseComments = parseComments;
      this.codePointLimit = codePointLimit;
      this.schema = schema;
   }

   @JvmStatic
   fun `_init_$lambda$0`(initialCapacity: Int): java.util.List {
      return new ArrayList(initialCapacity);
   }

   @JvmStatic
   fun `_init_$lambda$1`(initialCapacity: Int): java.util.Set {
      return new LinkedHashSet(initialCapacity);
   }

   @JvmStatic
   fun `_init_$lambda$2`(initialCapacity: Int): java.util.Map {
      return new LinkedHashMap(initialCapacity);
   }

   @JvmStatic
   fun `_init_$lambda$3`(version: SpecVersion): SpecVersion {
      if (version.getMajor() != 1) {
         throw new YamlVersionException(version);
      } else {
         return version;
      }
   }

   fun LoadSettings() {
      this(null, null, null, null, null, null, 0, false, false, 0, false, null, null, false, 0, null, 65535, null);
   }

   public fun interface CollectionProvider<T> {
      public abstract operator fun invoke(initialCapacity: Int): Any {
      }
   }

   public fun interface SpecVersionMutator {
      public abstract operator fun invoke(version: SpecVersion): SpecVersion {
      }
   }
}
