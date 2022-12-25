plugins {
    id("barcode.shared")
    id("com.vjh0107.ksp-extension")
    id("com.vjh0107.bukkit-resource-generator")
}

barcodeTasks {
    bukkitResource {
        main = "com.vjh0107.barcode.mmintegration.MMIntegrationPlugin"
        name = "BarcodeModules-MMIntegration"
        author = "vjh0107"
        depend = listOf("MythicMobs", "MagicSpells")
    }
}

repositories {
    maven("https://mvn.lumine.io/repository/maven-public/")
}

dependencies {
    compileOnly(Deps.Minecraft.PAPER_API)
    compileOnly(Deps.Minecraft.Plugin.MYTHICMOBS)
    compileOnly(Deps.Barcode.FRAMEWORK)
    ksp(Deps.Koin.KSP_COMPILER)
    compileOnly(files("libs/MagicSpellsLib.jar"))
}
