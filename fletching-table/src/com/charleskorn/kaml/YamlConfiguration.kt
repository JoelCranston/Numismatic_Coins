package com.charleskorn.kaml

import kotlinx.serialization.ExperimentalSerializationApi

public data class YamlConfiguration(encodeDefaults: Boolean = true,
   strictMode: Boolean = true,
   extensionDefinitionPrefix: String? = null,
   polymorphismStyle: PolymorphismStyle = PolymorphismStyle.Tag,
   polymorphismPropertyName: String = "type",
   encodingIndentationSize: Int = 2,
   breakScalarsAt: Int = 80,
   sequenceStyle: SequenceStyle = SequenceStyle.Block,
   singleLineStringStyle: SingleLineStringStyle = SingleLineStringStyle.DoubleQuoted,
   multiLineStringStyle: MultiLineStringStyle = singleLineStringStyle.getMultiLineStringStyle(),
   ambiguousQuoteStyle: AmbiguousQuoteStyle = AmbiguousQuoteStyle.DoubleQuoted,
   sequenceBlockIndent: Int = 0,
   anchorsAndAliases: AnchorsAndAliases = AnchorsAndAliases.Forbidden.INSTANCE as AnchorsAndAliases,
   yamlNamingStrategy: YamlNamingStrategy? = null,
   codePointLimit: Int? = null,
   decodeEnumCaseInsensitive: Boolean = false
) {
   internal final val encodeDefaults: Boolean
   internal final val strictMode: Boolean
   internal final val extensionDefinitionPrefix: String?
   internal final val polymorphismStyle: PolymorphismStyle
   internal final val polymorphismPropertyName: String
   internal final val encodingIndentationSize: Int
   internal final val breakScalarsAt: Int
   internal final val sequenceStyle: SequenceStyle
   internal final val singleLineStringStyle: SingleLineStringStyle
   internal final val multiLineStringStyle: MultiLineStringStyle
   internal final val ambiguousQuoteStyle: AmbiguousQuoteStyle
   internal final val sequenceBlockIndent: Int
   internal final val anchorsAndAliases: AnchorsAndAliases
   internal final val yamlNamingStrategy: YamlNamingStrategy?
   internal final val codePointLimit: Int?

   @ExperimentalSerializationApi
   internal final val decodeEnumCaseInsensitive: Boolean

   init {
      this.encodeDefaults = encodeDefaults;
      this.strictMode = strictMode;
      this.extensionDefinitionPrefix = extensionDefinitionPrefix;
      this.polymorphismStyle = polymorphismStyle;
      this.polymorphismPropertyName = polymorphismPropertyName;
      this.encodingIndentationSize = encodingIndentationSize;
      this.breakScalarsAt = breakScalarsAt;
      this.sequenceStyle = sequenceStyle;
      this.singleLineStringStyle = singleLineStringStyle;
      this.multiLineStringStyle = multiLineStringStyle;
      this.ambiguousQuoteStyle = ambiguousQuoteStyle;
      this.sequenceBlockIndent = sequenceBlockIndent;
      this.anchorsAndAliases = anchorsAndAliases;
      this.yamlNamingStrategy = yamlNamingStrategy;
      this.codePointLimit = codePointLimit;
      this.decodeEnumCaseInsensitive = decodeEnumCaseInsensitive;
   }

   internal operator fun component1(): Boolean {
      return this.encodeDefaults;
   }

   internal operator fun component2(): Boolean {
      return this.strictMode;
   }

   internal operator fun component3(): String? {
      return this.extensionDefinitionPrefix;
   }

   internal operator fun component4(): PolymorphismStyle {
      return this.polymorphismStyle;
   }

   internal operator fun component5(): String {
      return this.polymorphismPropertyName;
   }

   internal operator fun component6(): Int {
      return this.encodingIndentationSize;
   }

   internal operator fun component7(): Int {
      return this.breakScalarsAt;
   }

   internal operator fun component8(): SequenceStyle {
      return this.sequenceStyle;
   }

   internal operator fun component9(): SingleLineStringStyle {
      return this.singleLineStringStyle;
   }

   internal operator fun component10(): MultiLineStringStyle {
      return this.multiLineStringStyle;
   }

   internal operator fun component11(): AmbiguousQuoteStyle {
      return this.ambiguousQuoteStyle;
   }

   internal operator fun component12(): Int {
      return this.sequenceBlockIndent;
   }

   internal operator fun component13(): AnchorsAndAliases {
      return this.anchorsAndAliases;
   }

   internal operator fun component14(): YamlNamingStrategy? {
      return this.yamlNamingStrategy;
   }

   internal operator fun component15(): Int? {
      return this.codePointLimit;
   }

   internal operator fun component16(): Boolean {
      return this.decodeEnumCaseInsensitive;
   }

   public fun copy(
      encodeDefaults: Boolean = this.encodeDefaults,
      strictMode: Boolean = this.strictMode,
      extensionDefinitionPrefix: String? = this.extensionDefinitionPrefix,
      polymorphismStyle: PolymorphismStyle = this.polymorphismStyle,
      polymorphismPropertyName: String = this.polymorphismPropertyName,
      encodingIndentationSize: Int = this.encodingIndentationSize,
      breakScalarsAt: Int = this.breakScalarsAt,
      sequenceStyle: SequenceStyle = this.sequenceStyle,
      singleLineStringStyle: SingleLineStringStyle = this.singleLineStringStyle,
      multiLineStringStyle: MultiLineStringStyle = this.multiLineStringStyle,
      ambiguousQuoteStyle: AmbiguousQuoteStyle = this.ambiguousQuoteStyle,
      sequenceBlockIndent: Int = this.sequenceBlockIndent,
      anchorsAndAliases: AnchorsAndAliases = this.anchorsAndAliases,
      yamlNamingStrategy: YamlNamingStrategy? = this.yamlNamingStrategy,
      codePointLimit: Int? = this.codePointLimit,
      decodeEnumCaseInsensitive: Boolean = this.decodeEnumCaseInsensitive
   ): YamlConfiguration {
      return new YamlConfiguration(
         encodeDefaults,
         strictMode,
         extensionDefinitionPrefix,
         polymorphismStyle,
         polymorphismPropertyName,
         encodingIndentationSize,
         breakScalarsAt,
         sequenceStyle,
         singleLineStringStyle,
         multiLineStringStyle,
         ambiguousQuoteStyle,
         sequenceBlockIndent,
         anchorsAndAliases,
         yamlNamingStrategy,
         codePointLimit,
         decodeEnumCaseInsensitive
      );
   }

   public override fun toString(): String {
      return "YamlConfiguration(encodeDefaults=${this.encodeDefaults}, strictMode=${this.strictMode}, extensionDefinitionPrefix=${this.extensionDefinitionPrefix}, polymorphismStyle=${this.polymorphismStyle}, polymorphismPropertyName=${this.polymorphismPropertyName}, encodingIndentationSize=${this.encodingIndentationSize}, breakScalarsAt=${this.breakScalarsAt}, sequenceStyle=${this.sequenceStyle}, singleLineStringStyle=${this.singleLineStringStyle}, multiLineStringStyle=${this.multiLineStringStyle}, ambiguousQuoteStyle=${this.ambiguousQuoteStyle}, sequenceBlockIndent=${this.sequenceBlockIndent}, anchorsAndAliases=${this.anchorsAndAliases}, yamlNamingStrategy=${this.yamlNamingStrategy}, codePointLimit=${this.codePointLimit}, decodeEnumCaseInsensitive=${this.decodeEnumCaseInsensitive})";
   }

   public override fun hashCode(): Int {
      return (
               (
                        (
                                 (
                                          (
                                                   (
                                                            (
                                                                     (
                                                                              (
                                                                                       (
                                                                                                (
                                                                                                         (
                                                                                                                  (
                                                                                                                           (
                                                                                                                                    java.lang.Boolean.hashCode(
                                                                                                                                             this.encodeDefaults
                                                                                                                                          )
                                                                                                                                          * 31
                                                                                                                                       + java.lang.Boolean.hashCode(
                                                                                                                                          this.strictMode
                                                                                                                                       )
                                                                                                                                 )
                                                                                                                                 * 31
                                                                                                                              + (
                                                                                                                                 if (this.extensionDefinitionPrefix
                                                                                                                                       == null)
                                                                                                                                    0
                                                                                                                                    else
                                                                                                                                    this.extensionDefinitionPrefix
                                                                                                                                       .hashCode()
                                                                                                                              )
                                                                                                                        )
                                                                                                                        * 31
                                                                                                                     + this.polymorphismStyle.hashCode()
                                                                                                               )
                                                                                                               * 31
                                                                                                            + this.polymorphismPropertyName.hashCode()
                                                                                                      )
                                                                                                      * 31
                                                                                                   + Integer.hashCode(this.encodingIndentationSize)
                                                                                             )
                                                                                             * 31
                                                                                          + Integer.hashCode(this.breakScalarsAt)
                                                                                    )
                                                                                    * 31
                                                                                 + this.sequenceStyle.hashCode()
                                                                           )
                                                                           * 31
                                                                        + this.singleLineStringStyle.hashCode()
                                                                  )
                                                                  * 31
                                                               + this.multiLineStringStyle.hashCode()
                                                         )
                                                         * 31
                                                      + this.ambiguousQuoteStyle.hashCode()
                                                )
                                                * 31
                                             + Integer.hashCode(this.sequenceBlockIndent)
                                       )
                                       * 31
                                    + this.anchorsAndAliases.hashCode()
                              )
                              * 31
                           + (if (this.yamlNamingStrategy == null) 0 else this.yamlNamingStrategy.hashCode())
                     )
                     * 31
                  + (if (this.codePointLimit == null) 0 else this.codePointLimit.hashCode())
            )
            * 31
         + java.lang.Boolean.hashCode(this.decodeEnumCaseInsensitive);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is YamlConfiguration) {
         return false;
      } else {
         val var2: YamlConfiguration = other as YamlConfiguration;
         if (this.encodeDefaults != (other as YamlConfiguration).encodeDefaults) {
            return false;
         } else if (this.strictMode != var2.strictMode) {
            return false;
         } else if (!(this.extensionDefinitionPrefix == var2.extensionDefinitionPrefix)) {
            return false;
         } else if (this.polymorphismStyle != var2.polymorphismStyle) {
            return false;
         } else if (!(this.polymorphismPropertyName == var2.polymorphismPropertyName)) {
            return false;
         } else if (this.encodingIndentationSize != var2.encodingIndentationSize) {
            return false;
         } else if (this.breakScalarsAt != var2.breakScalarsAt) {
            return false;
         } else if (this.sequenceStyle != var2.sequenceStyle) {
            return false;
         } else if (this.singleLineStringStyle != var2.singleLineStringStyle) {
            return false;
         } else if (this.multiLineStringStyle != var2.multiLineStringStyle) {
            return false;
         } else if (this.ambiguousQuoteStyle != var2.ambiguousQuoteStyle) {
            return false;
         } else if (this.sequenceBlockIndent != var2.sequenceBlockIndent) {
            return false;
         } else if (!(this.anchorsAndAliases == var2.anchorsAndAliases)) {
            return false;
         } else if (!(this.yamlNamingStrategy == var2.yamlNamingStrategy)) {
            return false;
         } else if (!(this.codePointLimit == var2.codePointLimit)) {
            return false;
         } else {
            return this.decodeEnumCaseInsensitive == var2.decodeEnumCaseInsensitive;
         }
      }
   }

   fun YamlConfiguration() {
      this(false, false, null, null, null, 0, 0, null, null, null, null, 0, null, null, null, false, 65535, null);
   }
}
