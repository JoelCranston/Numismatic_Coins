package io.ktor.client.plugins.sse

import io.ktor.sse.ServerSentEvent

internal sealed interface BodyBuffer {
   public open fun appendLine(line: String) {
   }

   public open fun appendEvent(event: ServerSentEvent) {
   }

   public open fun toByteArray(): ByteArray {
      return SSEBufferPolicyKt.getEMPTY();
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun appendLine(`$this`: BodyBuffer, line: java.lang.String) {
         BodyBuffer.access$appendLine$jd(`$this`, line);
      }

      @Deprecated
      @JvmStatic
      fun appendEvent(`$this`: BodyBuffer, event: ServerSentEvent) {
         BodyBuffer.access$appendEvent$jd(`$this`, event);
      }

      @Deprecated
      @JvmStatic
      fun toByteArray(`$this`: BodyBuffer): ByteArray {
         return BodyBuffer.access$toByteArray$jd(`$this`);
      }
   }

   public object Empty : BodyBuffer

   public class Events(capacity: Int) : BodyBuffer {
      private final val capacity: Int
      private final val events: ArrayDeque<ServerSentEvent>

      init {
         this.capacity = capacity;
         this.events = new ArrayDeque<>();
      }

      public override fun appendEvent(event: ServerSentEvent) {
         if (this.events.size() == this.capacity) {
            this.events.removeFirst();
         }

         this.events.addLast(event);
      }

      public override fun toByteArray(): ByteArray {
         return SSEBufferPolicyKt.access$toByteArray(this.events);
      }
   }

   public class Lines(capacity: Int) : BodyBuffer {
      private final val capacity: Int
      private final val lines: ArrayDeque<String>

      init {
         this.capacity = capacity;
         this.lines = new ArrayDeque<>();
      }

      public override fun appendLine(line: String) {
         if (this.lines.size() == this.capacity) {
            this.lines.removeFirst();
         }

         this.lines.addLast(line);
      }

      public override fun toByteArray(): ByteArray {
         return SSEBufferPolicyKt.access$toByteArray(this.lines);
      }
   }
}
