package dev.kikugie.fletching_table.extension.dependency

import org.gradle.api.provider.Property

public interface FTModSpec {
   public val limit: Property<Int>
   public val constraint: Property<ModVersionPredicate>

   public open fun constraint(action: ModVersionPredicate) {
      this.getConstraint().set(action);
   }
}
