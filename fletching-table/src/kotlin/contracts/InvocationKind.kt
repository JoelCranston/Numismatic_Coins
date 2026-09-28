package kotlin.contracts

import kotlin.enums.EnumEntries
import kotlin.internal.ContractsDsl

@ContractsDsl
@ExperimentalContracts
@SinceKotlin(version = "1.3")
public enum class InvocationKind {
   @ContractsDsl
   AT_MOST_ONCE,
   @ContractsDsl
   AT_LEAST_ONCE,
   @ContractsDsl
   EXACTLY_ONCE,
   @ContractsDsl
   UNKNOWN
   @JvmStatic
   fun getEntries(): EnumEntries<InvocationKind> {
      return $ENTRIES;
   }
}
