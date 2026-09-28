package kotlinx.coroutines.flow

import kotlinx.coroutines.flow.StartedLazily.command.1

private class StartedLazily : SharingStarted {
   public override fun command(subscriptionCount: StateFlow<Int>): Flow<SharingCommand> {
      return FlowKt.flow(new 1(subscriptionCount, null));
   }

   public override fun toString(): String {
      return "SharingStarted.Lazily";
   }
}
