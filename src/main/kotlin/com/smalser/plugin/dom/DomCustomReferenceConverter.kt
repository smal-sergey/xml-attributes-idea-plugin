package com.smalser.plugin.dom

import com.intellij.openapi.diagnostic.logger
import com.intellij.psi.*
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.util.SmartList
import com.intellij.util.xml.ConvertContext
import com.intellij.util.xml.CustomReferenceConverter
import com.intellij.util.xml.ElementPresentationManager
import com.intellij.util.xml.GenericDomValue
import com.smalser.plugin.dom.model.Actionset


class DomCustomReferenceConverter : CustomReferenceConverter<String?> {
    private val log = logger<DomCustomReferenceConverter>()

    override fun createReferences(
        value: GenericDomValue<String?>,
        element: PsiElement,
        context: ConvertContext
    ): Array<PsiReference> {
        return arrayOf(ActionsetReference(element as XmlAttributeValue))
    }

    class ActionsetReference(element: XmlAttributeValue) : PsiPolyVariantReferenceBase<XmlAttributeValue?>(element) {
        override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
            val nameToSearch = value
            val project = myElement!!.project
            val scope = myElement.resolveScope
            val results = SmartList<PsiElement?>()
            log.info("Invoking multiResolve with $nameToSearch")
            processActionsFiles(project, scope) { actionsRoot ->
                if (actionsRoot == null) {
                    return@processActionsFiles true
                }

                for (actionset in actionsRoot.getActionsets()) {
                    if (nameToSearch == ElementPresentationManager.getElementName(actionset)) {
                        val target = getTarget(actionset)
                        log.info("Resolving $nameToSearch in $actionset = $target")
                        results.add(target)
                        return@processActionsFiles true
                    }
                }
                return@processActionsFiles true

//                val actionset: Actionset = actionsRoot?.findActionset(nameToSearch) ?: return@processActionsFiles true
//                val target = getTarget(actionset)
//                log.info("Resolving $nameToSearch in $actionset = $target")
//                results.add(target)
//                true
            }
            return PsiElementResolveResult.createResults(results)
        }

        companion object {
            /** Approach #1: */
            private fun getTarget(actionset: Actionset): PsiElement {
                return actionset.getAttributeName().getXmlAttributeValue()!!
            }
//            /** Approach #2:  */
//            private fun getTarget(actionset: Actionset): PsiElement? {
//                val target = DomTarget.getTarget(actionset)
//                return if (target == null) null else PomService.convertToPsi(target)
        }
    }
}
