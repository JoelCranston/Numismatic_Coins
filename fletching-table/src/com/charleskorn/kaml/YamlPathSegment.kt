package com.charleskorn.kaml

public sealed class YamlPathSegment protected constructor(location: Location) {
   public open val location: Location

   init {
      this.location = location;
   }

   public data class AliasDefinition(name: String, location: Location) : YamlPathSegment(location) {
      public final val name: String
      public open val location: Location

      init {
         this.name = name;
         this.location = location;
      }

      public operator fun component1(): String {
         return this.name;
      }

      public operator fun component2(): Location {
         return this.location;
      }

      public fun copy(name: String = this.name, location: Location = this.location): com.charleskorn.kaml.YamlPathSegment.AliasDefinition {
         return new YamlPathSegment.AliasDefinition(name, location);
      }

      public override fun toString(): String {
         return "AliasDefinition(name=${this.name}, location=${this.location})";
      }

      public override fun hashCode(): Int {
         return this.name.hashCode() * 31 + this.location.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is YamlPathSegment.AliasDefinition) {
            return false;
         } else {
            val var2: YamlPathSegment.AliasDefinition = other as YamlPathSegment.AliasDefinition;
            if (!(this.name == (other as YamlPathSegment.AliasDefinition).name)) {
               return false;
            } else {
               return this.location == var2.location;
            }
         }
      }
   }

   public data class AliasReference(name: String, location: Location) : YamlPathSegment(location) {
      public final val name: String
      public open val location: Location

      init {
         this.name = name;
         this.location = location;
      }

      public operator fun component1(): String {
         return this.name;
      }

      public operator fun component2(): Location {
         return this.location;
      }

      public fun copy(name: String = this.name, location: Location = this.location): com.charleskorn.kaml.YamlPathSegment.AliasReference {
         return new YamlPathSegment.AliasReference(name, location);
      }

      public override fun toString(): String {
         return "AliasReference(name=${this.name}, location=${this.location})";
      }

      public override fun hashCode(): Int {
         return this.name.hashCode() * 31 + this.location.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is YamlPathSegment.AliasReference) {
            return false;
         } else {
            val var2: YamlPathSegment.AliasReference = other as YamlPathSegment.AliasReference;
            if (!(this.name == (other as YamlPathSegment.AliasReference).name)) {
               return false;
            } else {
               return this.location == var2.location;
            }
         }
      }
   }

   public data class Error(location: Location) : YamlPathSegment(location) {
      public open val location: Location

      init {
         this.location = location;
      }

      public operator fun component1(): Location {
         return this.location;
      }

      public fun copy(location: Location = this.location): com.charleskorn.kaml.YamlPathSegment.Error {
         return new YamlPathSegment.Error(location);
      }

      public override fun toString(): String {
         return "Error(location=${this.location})";
      }

      public override fun hashCode(): Int {
         return this.location.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is YamlPathSegment.Error) {
            return false;
         } else {
            return this.location == (other as YamlPathSegment.Error).location;
         }
      }
   }

   public data class ListEntry(index: Int, location: Location) : YamlPathSegment(location) {
      public final val index: Int
      public open val location: Location

      init {
         this.index = index;
         this.location = location;
      }

      public operator fun component1(): Int {
         return this.index;
      }

      public operator fun component2(): Location {
         return this.location;
      }

      public fun copy(index: Int = this.index, location: Location = this.location): com.charleskorn.kaml.YamlPathSegment.ListEntry {
         return new YamlPathSegment.ListEntry(index, location);
      }

      public override fun toString(): String {
         return "ListEntry(index=${this.index}, location=${this.location})";
      }

      public override fun hashCode(): Int {
         return Integer.hashCode(this.index) * 31 + this.location.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is YamlPathSegment.ListEntry) {
            return false;
         } else {
            val var2: YamlPathSegment.ListEntry = other as YamlPathSegment.ListEntry;
            if (this.index != (other as YamlPathSegment.ListEntry).index) {
               return false;
            } else {
               return this.location == var2.location;
            }
         }
      }
   }

   public data class MapElementKey(key: String, location: Location) : YamlPathSegment(location) {
      public final val key: String
      public open val location: Location

      init {
         this.key = key;
         this.location = location;
      }

      public operator fun component1(): String {
         return this.key;
      }

      public operator fun component2(): Location {
         return this.location;
      }

      public fun copy(key: String = this.key, location: Location = this.location): com.charleskorn.kaml.YamlPathSegment.MapElementKey {
         return new YamlPathSegment.MapElementKey(key, location);
      }

      public override fun toString(): String {
         return "MapElementKey(key=${this.key}, location=${this.location})";
      }

      public override fun hashCode(): Int {
         return this.key.hashCode() * 31 + this.location.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is YamlPathSegment.MapElementKey) {
            return false;
         } else {
            val var2: YamlPathSegment.MapElementKey = other as YamlPathSegment.MapElementKey;
            if (!(this.key == (other as YamlPathSegment.MapElementKey).key)) {
               return false;
            } else {
               return this.location == var2.location;
            }
         }
      }
   }

   public data class MapElementValue(location: Location) : YamlPathSegment(location) {
      public open val location: Location

      init {
         this.location = location;
      }

      public operator fun component1(): Location {
         return this.location;
      }

      public fun copy(location: Location = this.location): com.charleskorn.kaml.YamlPathSegment.MapElementValue {
         return new YamlPathSegment.MapElementValue(location);
      }

      public override fun toString(): String {
         return "MapElementValue(location=${this.location})";
      }

      public override fun hashCode(): Int {
         return this.location.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is YamlPathSegment.MapElementValue) {
            return false;
         } else {
            return this.location == (other as YamlPathSegment.MapElementValue).location;
         }
      }
   }

   public data class Merge(location: Location) : YamlPathSegment(location) {
      public open val location: Location

      init {
         this.location = location;
      }

      public operator fun component1(): Location {
         return this.location;
      }

      public fun copy(location: Location = this.location): com.charleskorn.kaml.YamlPathSegment.Merge {
         return new YamlPathSegment.Merge(location);
      }

      public override fun toString(): String {
         return "Merge(location=${this.location})";
      }

      public override fun hashCode(): Int {
         return this.location.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is YamlPathSegment.Merge) {
            return false;
         } else {
            return this.location == (other as YamlPathSegment.Merge).location;
         }
      }
   }

   public object Root : YamlPathSegment(new Location(1, 1))
}
