package com.smalser.plugin.psi

import com.intellij.openapi.diagnostic.logger
import com.intellij.patterns.StandardPatterns
import com.intellij.patterns.XmlPatterns
import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceRegistrar

class ActionSetReferenceContributor : PsiReferenceContributor() {
    private val log = logger<ActionSetReferenceContributor>()

    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        val refPattern = XmlPatterns.xmlAttribute()
            .withName("actionset")
            .withValue(StandardPatterns.string().endsWith("ActionSet"))
        log.info(">>> Registering ActionSetReferenceContributor with pattern $refPattern")
        registrar.registerReferenceProvider(refPattern, ActionSetReferenceProvider())
    }
}