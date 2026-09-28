package kotlin.contracts

import kotlin.internal.ContractsDsl

@ContractsDsl
@ExperimentalContracts
@SinceKotlin(version = "1.3")
public interface ContractBuilder {
   @ContractsDsl
   public abstract fun returns(): Returns {
   }

   @ContractsDsl
   public abstract fun returns(value: Any?): Returns {
   }

   @ContractsDsl
   public abstract fun returnsNotNull(): ReturnsNotNull {
   }

   @ContractsDsl
   public abstract fun <R> callsInPlace(lambda: () -> R, kind: InvocationKind = ...): CallsInPlace {
   }

   @ExperimentalExtendedContracts
   @ContractsDsl
   public abstract infix fun Boolean.implies(value: ReturnsNotNull) {
   }

   @ExperimentalExtendedContracts
   @ContractsDsl
   public abstract infix fun <R> Boolean.holdsIn(lambda: () -> R): HoldsIn {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls
}
