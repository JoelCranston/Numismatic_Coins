package io.ktor.network.sockets

public interface Configurable<T extends Configurable<? extends T, Options>, Options extends SocketOptions> {
   public var options: Any

   public open fun configure(block: (Any) -> Unit): Any {
      val var10000: SocketOptions = this.getOptions().copy$ktor_network();
      block.invoke(var10000);
      this.setOptions((Options)var10000);
      return (T)this;
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun <T extends Configurable<? extends T, Options>, Options extends SocketOptions> configure(
         `$this`: Configurable<? extends T, Options>, block: (Options?) -> Unit
      ): T {
         return (T)Configurable.access$configure$jd(`$this`, block);
      }
   }
}
