package dev.kikugie.fletching_table.extension.dependency

import dev.kikugie.fletching_table.extension.dependency.data.ModVersion

public fun interface ModVersionPredicate {
   public abstract fun check(version: ModVersion): Boolean {
   }
}
