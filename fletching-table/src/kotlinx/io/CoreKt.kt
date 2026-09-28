package kotlinx.io

public fun RawSource.buffered(): Source {
   return new RealSource(`$this$buffered`);
}

public fun RawSink.buffered(): Sink {
   return new RealSink(`$this$buffered`);
}

public fun discardingSink(): RawSink {
   return new DiscardingSink();
}
