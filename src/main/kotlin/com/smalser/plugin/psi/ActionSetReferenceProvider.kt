package com.smalser.plugin.psi

import com.intellij.openapi.diagnostic.logger
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.xml.XmlAttribute
import com.intellij.util.ProcessingContext
import java.util.concurrent.atomic.AtomicInteger

class ActionSetReferenceProvider : PsiReferenceProvider() {
    private val log = logger<ActionSetReferenceProvider>()

    companion object {
        val counter = AtomicInteger()
    }

    override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
        val id = counter.incrementAndGet()
        log.info("<$id> getReferencesByElement ${toStringAttribute(element)}")
        val xmlAttribute = element as XmlAttribute
        val searchStr = xmlAttribute.value

        val result = arrayListOf<PsiReference>()
        if (xmlAttribute.name == "actionset" && searchStr?.endsWith("ActionSet") == true) {
            result.add(ActionSetPsiReference(xmlAttribute, id))
        }

        log.info("<$id> result = $result")
        return result.toTypedArray()
    }
}

fun toStringAttribute(element: PsiElement?): String {
    return if (element is XmlAttribute)
        element.name + "=" + element.value
    else
        element.toString()
}