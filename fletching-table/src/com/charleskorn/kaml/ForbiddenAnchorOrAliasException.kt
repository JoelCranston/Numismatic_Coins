package com.charleskorn.kaml

public class ForbiddenAnchorOrAliasException(message: String, path: YamlPath) : YamlException(message, path, null, 4)
