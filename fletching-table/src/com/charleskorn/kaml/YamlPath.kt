package com.charleskorn.kaml

public data class YamlPath(segments: List<YamlPathSegment>) {
   public final val segments: List<YamlPathSegment>
   public final val endLocation: Location

   init {
      this.segments = segments;
      if (this.segments.isEmpty()) {
         throw new IllegalArgumentException("Path must contain at least one segment.");
      } else if (CollectionsKt.first(this.segments) !is YamlPathSegment.Root && CollectionsKt.first(this.segments) !is YamlPathSegment.AliasDefinition) {
         throw new IllegalArgumentException("First element of path must be root segment or alias definition.");
      } else if (CollectionsKt.drop(this.segments, 1).contains(YamlPathSegment.Root.INSTANCE)) {
         throw new IllegalArgumentException("Root segment can only be first element of path.");
      } else {
         this.endLocation = CollectionsKt.last(this.segments).getLocation();
      }
   }

   public constructor(vararg segments: YamlPathSegment) : this(ArraysKt.toList(segments))
   public fun withError(location: Location): YamlPath {
      return this.withSegment(new YamlPathSegment.Error(location));
   }

   public fun withListEntry(index: Int, location: Location): YamlPath {
      return this.withSegment(new YamlPathSegment.ListEntry(index, location));
   }

   public fun withMapElementKey(key: String, location: Location): YamlPath {
      return this.withSegment(new YamlPathSegment.MapElementKey(key, location));
   }

   public fun withMapElementValue(location: Location): YamlPath {
      return this.withSegment(new YamlPathSegment.MapElementValue(location));
   }

   public fun withAliasReference(name: String, location: Location): YamlPath {
      return this.withSegment(new YamlPathSegment.AliasReference(name, location));
   }

   public fun withAliasDefinition(name: String, location: Location): YamlPath {
      return this.withSegment(new YamlPathSegment.AliasDefinition(name, location));
   }

   public fun withMerge(location: Location): YamlPath {
      return this.withSegment(new YamlPathSegment.Merge(location));
   }

   private fun withSegment(segment: YamlPathSegment): YamlPath {
      return new YamlPath(CollectionsKt.plus(this.segments, segment));
   }

   public fun toHumanReadableString(): String {
      val builder: StringBuilder = new StringBuilder();
      var nextSegmentIndex: Int = 1;

      while (nextSegmentIndex <= CollectionsKt.getLastIndex(this.segments)) {
         val segment: YamlPathSegment = this.segments.get(nextSegmentIndex++);
         if (segment is YamlPathSegment.ListEntry) {
            builder.append('[');
            builder.append((segment as YamlPathSegment.ListEntry).getIndex());
            builder.append(']');
         } else if (segment is YamlPathSegment.MapElementKey) {
            if (builder.length() > 0) {
               builder.append('.');
            }

            builder.append((segment as YamlPathSegment.MapElementKey).getKey());
         } else if (segment is YamlPathSegment.AliasReference) {
            builder.append("->&");
            builder.append((segment as YamlPathSegment.AliasReference).getName());
         } else if (segment is YamlPathSegment.Merge) {
            builder.append(">>(merged");
            if (nextSegmentIndex <= CollectionsKt.getLastIndex(this.segments) && this.segments.get(nextSegmentIndex) is YamlPathSegment.ListEntry) {
               builder.append(" entry ");
               val var10001: Any = this.segments.get(nextSegmentIndex);
               builder.append((var10001 as YamlPathSegment.ListEntry).getIndex());
               nextSegmentIndex++;
            }

            if (nextSegmentIndex <= CollectionsKt.getLastIndex(this.segments) && this.segments.get(nextSegmentIndex) is YamlPathSegment.AliasReference) {
               builder.append(" &");
               val var5: Any = this.segments.get(nextSegmentIndex);
               builder.append((var5 as YamlPathSegment.AliasReference).getName());
               nextSegmentIndex++;
            }

            builder.append(")");
         } else if (segment !is YamlPathSegment.Root
            && segment !is YamlPathSegment.Error
            && segment !is YamlPathSegment.MapElementValue
            && segment !is YamlPathSegment.AliasDefinition) {
            throw new NoWhenBranchMatchedException();
         }
      }

      if (builder.length() > 0) {
         val var10000: java.lang.String = builder.toString();
         return var10000;
      } else {
         return "<root>";
      }
   }

   public operator fun component1(): List<YamlPathSegment> {
      return this.segments;
   }

   public fun copy(segments: List<YamlPathSegment> = this.segments): YamlPath {
      return new YamlPath(segments);
   }

   public override fun toString(): String {
      return "YamlPath(segments=${this.segments})";
   }

   public override fun hashCode(): Int {
      return this.segments.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is YamlPath) {
         return false;
      } else {
         return this.segments == (other as YamlPath).segments;
      }
   }

   public companion object {
      public final val root: YamlPath

      public fun forAliasDefinition(name: String, location: Location): YamlPath {
         return new YamlPath(new YamlPathSegment.AliasDefinition(name, location));
      }
   }
}
