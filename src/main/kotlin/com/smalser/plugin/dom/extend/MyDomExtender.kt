package com.smalser.plugin.dom.extend

import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.module.Module
import com.intellij.openapi.util.Key
import com.intellij.pom.PomTarget
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.psi.xml.XmlElement
import com.intellij.psi.xml.XmlTag
import com.intellij.util.xml.*
import com.intellij.util.xml.reflect.*
import com.smalser.plugin.dom.model.Action
import java.lang.reflect.Type

class MyDomExtender : DomExtender<Action>() {
    private val log = logger<MyDomExtender>()

    override fun registerExtensions(t: Action, registrar: DomExtensionsRegistrar) {
//        registrar.registerCustomChildrenExtension(XmlAttribute::class.java, CustomDescriptor() )
        val xmlTag = t.xmlElement as XmlTag
        xmlTag.attributes
            .filter { it.name != "class" && it.name != "actionset" }
            .forEach {
                log.info("Registering custom attribute ${it.name} in <action name=\"${t.getAttributeClass()}\"  ...>")
//                registrar.registerAttributeChildExtension(XmlName(it.localName), GenericAttributeValue::class.java)
                registrar.registerAttributeChildExtension(XmlName(it.localName), MyGenericAttributeValue::class.java)
            }
    }
}

open class MyGenericAttributeValue : GenericAttributeValue<ActionsetRef>{
    override fun <T : Annotation?> getAnnotation(annotationClass: Class<T>?): T? {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> getUserData(key: Key<T>): T? {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> putUserData(key: Key<T>, value: T?) {
        TODO("Not yet implemented")
    }

    override fun getXmlTag(): XmlTag? {
        TODO("Not yet implemented")
    }

    override fun getXmlElement(): XmlElement? {
        TODO("Not yet implemented")
    }

    override fun getParent(): DomElement {
        TODO("Not yet implemented")
    }

    override fun ensureTagExists(): XmlTag {
        TODO("Not yet implemented")
    }

    override fun ensureXmlElementExists(): XmlElement {
        TODO("Not yet implemented")
    }

    override fun undefine() {
        TODO("Not yet implemented")
    }

    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }

    override fun exists(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getGenericInfo(): DomGenericInfo {
        TODO("Not yet implemented")
    }

    override fun getXmlElementName(): String {
        TODO("Not yet implemented")
    }

    override fun getXmlElementNamespace(): String {
        TODO("Not yet implemented")
    }

    override fun getXmlElementNamespaceKey(): String? {
        TODO("Not yet implemented")
    }

    override fun accept(visitor: DomElementVisitor?) {
        TODO("Not yet implemented")
    }

    override fun acceptChildren(visitor: DomElementVisitor?) {
        TODO("Not yet implemented")
    }

    override fun getManager(): DomManager {
        TODO("Not yet implemented")
    }

    override fun getDomElementType(): Type {
        TODO("Not yet implemented")
    }

    override fun getChildDescription(): AbstractDomChildrenDescription {
        TODO("Not yet implemented")
    }

    override fun getNameStrategy(): DomNameStrategy {
        TODO("Not yet implemented")
    }

    override fun getPresentation(): ElementPresentation {
        TODO("Not yet implemented")
    }

    override fun getResolveScope(): GlobalSearchScope {
        TODO("Not yet implemented")
    }

    override fun <T : DomElement?> getParentOfType(requiredClass: Class<T>?, strict: Boolean): T? {
        TODO("Not yet implemented")
    }

    override fun getModule(): Module? {
        TODO("Not yet implemented")
    }

    override fun copyFrom(other: DomElement?) {
        TODO("Not yet implemented")
    }

    override fun <T : DomElement?> createMockCopy(physical: Boolean): T {
        TODO("Not yet implemented")
    }

    override fun <T : DomElement?> createStableCopy(): T {
        TODO("Not yet implemented")
    }

    override fun getStringValue(): String? {
        TODO("Not yet implemented")
    }

    override fun getValue(): ActionsetRef? {
        TODO("Not yet implemented")
    }

    override fun setStringValue(value: String?) {
        TODO("Not yet implemented")
    }

    override fun setValue(value: ActionsetRef?) {
        TODO("Not yet implemented")
    }

    override fun getConverter(): Converter<ActionsetRef> {
        return MyCustomReferenceConverter2()
    }

    override fun getRawText(): String? {
        TODO("Not yet implemented")
    }

    override fun getXmlAttribute(): XmlAttribute? {
        TODO("Not yet implemented")
    }

    override fun getXmlAttributeValue(): XmlAttributeValue? {
        TODO("Not yet implemented")
    }

}

class CustomDescriptor : CustomDomChildrenDescription.AttributeDescriptor() {
    override fun getCompletionVariants(parent: DomElement): MutableSet<EvaluatedXmlName> {
        return super.getCompletionVariants(parent)
    }

    override fun findDeclaration(parent: DomElement?, name: EvaluatedXmlName): PomTarget? {
        return super.findDeclaration(parent, name)
    }

    override fun findDeclaration(child: DomElement): PomTarget? {
        return super.findDeclaration(child)
    }

    override fun getElementType(child: DomElement?): Type {
        return super.getElementType(child)
    }
}