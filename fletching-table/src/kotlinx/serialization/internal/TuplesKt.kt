package kotlinx.serialization.internal

private final val NULL: Any = new Object()
private const val deprecationMessage: String =
   "This class is used only by the plugin in generated code and should not be used directly. Use corresponding factory functions instead"

@JvmSynthetic
fun `access$getNULL$p`(): Any {
   return NULL;
}
