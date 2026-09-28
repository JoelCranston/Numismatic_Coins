package io.ktor.client.plugins

/** @deprecated */
@Deprecated(message = "This plugin is no longer needed.\nThis API is deprecated and will be removed in Ktor 4.0.0")
public class SaveBodyPluginConfig {
   @Deprecated(
      message = "It is no longer possible to disable body saving for all requests. Use client.prepareRequest(...).execute { ... } syntax to prevent saving the body in memory.\n\nThis API is deprecated and will be removed in Ktor 4.0.0\nIf you were relying on this functionality, share your use case by commenting on this issue: https://youtrack.jetbrains.com/issue/KTOR-8367/"
   )
   public final var disabled: Boolean
}
