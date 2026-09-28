package io.ktor.network.selector

import java.util.concurrent.CancellationException

public class ClosedChannelCancellationException : CancellationException("Closed channel.")
