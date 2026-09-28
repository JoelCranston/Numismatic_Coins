package io.ktor.sse

public data class ServerSentEvent(data: String? = null, event: String? = null, id: String? = null, retry: Long? = null, comments: String? = null) :
   ServerSentEventMetadata<java.lang.String> {
   public open val data: String?
   public open val event: String?
   public open val id: String?
   public open val retry: Long?
   public open val comments: String?

   init {
      this.data = data;
      this.event = event;
      this.id = id;
      this.retry = retry;
      this.comments = comments;
   }

   public override fun toString(): String {
      return ServerSentEventKt.access$eventToString(this.getData(), this.getEvent(), this.getId(), this.getRetry(), this.getComments());
   }

   public operator fun component1(): String? {
      return this.data;
   }

   public operator fun component2(): String? {
      return this.event;
   }

   public operator fun component3(): String? {
      return this.id;
   }

   public operator fun component4(): Long? {
      return this.retry;
   }

   public operator fun component5(): String? {
      return this.comments;
   }

   public fun copy(data: String? = this.data, event: String? = this.event, id: String? = this.id, retry: Long? = this.retry, comments: String? = this.comments): ServerSentEvent {
      return new ServerSentEvent(data, event, id, retry, comments);
   }

   public override fun hashCode(): Int {
      return (
               (
                        ((if (this.data == null) 0 else this.data.hashCode()) * 31 + (if (this.event == null) 0 else this.event.hashCode())) * 31
                           + (if (this.id == null) 0 else this.id.hashCode())
                     )
                     * 31
                  + (if (this.retry == null) 0 else this.retry.hashCode())
            )
            * 31
         + (if (this.comments == null) 0 else this.comments.hashCode());
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is ServerSentEvent) {
         return false;
      } else {
         val var2: ServerSentEvent = other as ServerSentEvent;
         if (!(this.data == (other as ServerSentEvent).data)) {
            return false;
         } else if (!(this.event == var2.event)) {
            return false;
         } else if (!(this.id == var2.id)) {
            return false;
         } else if (!(this.retry == var2.retry)) {
            return false;
         } else {
            return this.comments == var2.comments;
         }
      }
   }

   fun ServerSentEvent() {
      this(null, null, null, null, null, 31, null);
   }
}
