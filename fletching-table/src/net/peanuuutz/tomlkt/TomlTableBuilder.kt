package net.peanuuutz.tomlkt

import java.util.LinkedHashMap

@BuilderDsl
public class TomlTableBuilder(initialCapacity: Int = 8) {
   private final val elements: MutableMap<String, TomlElement>
   private final val annotations: MutableMap<String, List<Annotation>>

   init {
      this.elements = new LinkedHashMap<>(initialCapacity);
      this.annotations = new LinkedHashMap<>(initialCapacity);
   }

   public fun element(key: Any?, element: TomlElement, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
      val tomlKey: java.lang.String = TomlElementKt.toTomlKey(key);
      this.elements.put(tomlKey, element);
      this.annotations.put(tomlKey, elementAnnotations);
   }

   public fun elements(elements: Map<*, TomlElement>, annotations: Map<*, List<Annotation>> = MapsKt.emptyMap()) {
      this.elements.putAll(TomlElementBuildersKt.access$toTomlMap(elements));
      this.annotations.putAll(TomlElementBuildersKt.access$toTomlMap(annotations));
   }

   public fun build(): TomlTable {
      return new TomlTable(MapsKt.toMap(this.elements), MapsKt.toMap(this.annotations));
   }

   fun TomlTableBuilder() {
      this(0, 1, null);
   }
}
