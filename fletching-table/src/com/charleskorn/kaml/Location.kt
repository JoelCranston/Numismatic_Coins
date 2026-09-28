package com.charleskorn.kaml

public data class Location(line: Int, column: Int) {
   public final val line: Int
   public final val column: Int

   init {
      this.line = line;
      this.column = column;
   }

   public operator fun component1(): Int {
      return this.line;
   }

   public operator fun component2(): Int {
      return this.column;
   }

   public fun copy(line: Int = this.line, column: Int = this.column): Location {
      return new Location(line, column);
   }

   public override fun toString(): String {
      return "Location(line=${this.line}, column=${this.column})";
   }

   public override fun hashCode(): Int {
      return Integer.hashCode(this.line) * 31 + Integer.hashCode(this.column);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is Location) {
         return false;
      } else {
         val var2: Location = other as Location;
         if (this.line != (other as Location).line) {
            return false;
         } else {
            return this.column == var2.column;
         }
      }
   }
}
