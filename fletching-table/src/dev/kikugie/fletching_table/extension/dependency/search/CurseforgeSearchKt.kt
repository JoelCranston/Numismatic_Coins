package dev.kikugie.fletching_table.extension.dependency.search

private const val CF_API_KEY: String = "$2a$10$wuAJuNZuted3NORVmpgUC.m8sI.pv1tOPKZyBgLFGjxFp/br0lZCC"
private final val LOADERS: Array<String>
private java.lang.String[] LOADERS = new java.lang.String[]{"", "forge", "cauldron", "liteloader", "fabric", "quilt", "neoforge"};

private fun Collection<*>.isInvariable(): Boolean {
   val var1: Int = `$this$isInvariable`.size();
   return 0 <= var1 && var1 < 2;
}

@JvmSynthetic
fun `access$isInvariable`(`$receiver`: java.util.Collection): Boolean {
   return isInvariable(`$receiver`);
}

@JvmSynthetic
fun `access$getLOADERS$p`(): Array<java.lang.String> {
   return LOADERS;
}
