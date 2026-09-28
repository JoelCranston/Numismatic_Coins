package io.ktor.client.plugins

private object Messages {
   private const val USE_STREAMING_SYNTAX: String = "Use client.prepareRequest(...).execute { ... } syntax to prevent saving the body in memory."
   private const val API_WILL_BE_REMOVED: String = "This API is deprecated and will be removed in Ktor 4.0.0"
   private const val SHARE_USE_CASE: String =
      "If you were relying on this functionality, share your use case by commenting on this issue: https://youtrack.jetbrains.com/issue/KTOR-8367/"
      public const val SAVE_BODY_ENABLED_MESSAGE: String =
      "The SaveBodyPlugin plugin is deprecated and can be safely removed. Request bodies are now saved in memory by default for all non-streaming responses."
      public const val SAVE_BODY_DISABLED_MESSAGE: String =
      "It is no longer possible to disable body saving for all requests. Use client.prepareRequest(...).execute { ... } syntax to prevent saving the body in memory.\n\nThis API is deprecated and will be removed in Ktor 4.0.0\nIf you were relying on this functionality, share your use case by commenting on this issue: https://youtrack.jetbrains.com/issue/KTOR-8367/"
      public const val PLUGIN_DEPRECATED_MESSAGE: String = "This plugin is no longer needed.\nThis API is deprecated and will be removed in Ktor 4.0.0"
   public const val SKIP_SAVING_BODY_MESSAGE: String =
      "Skipping of body saving for a specific request is no longer allowed.\nUse client.prepareRequest(...).execute { ... } syntax to prevent saving the body in memory.\n\nThis API is deprecated and will be removed in Ktor 4.0.0\nIf you were relying on this functionality, share your use case by commenting on this issue: https://youtrack.jetbrains.com/issue/KTOR-8367/"
   }
