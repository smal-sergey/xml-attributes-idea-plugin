package com.smalser.plugin.dom.model

import com.intellij.psi.xml.XmlAttribute
import com.intellij.util.xml.*
import com.smalser.plugin.dom.*
import com.smalser.plugin.dom.convert.ActionSetReferenceConverter


interface Action : DomElement {
    @Attribute("class")
    fun getAttributeClass(): GenericAttributeValue<String>

    //todo WORKING simple converter with built-in caching!!!
    @Convert(value = ActionSetReferenceConverter::class)
    @Attribute("actionset")
    fun getAttributeActionSetRef(): GenericAttributeValue<XmlAttribute>

    //todo WORKING custom referencing within different files, no caching!!!
//    @Referencing(DomCustomReferenceConverter::class)
//    @Attribute("actionset")
//    fun getAttributeActionSetRef(): GenericAttributeValue<String>

    //todo  to extend custom attributes
//    @Convert(value = MyCustomReferenceConverter2::class)
//    @Referencing(MyCustomReferenceConverter2::class)
//    @Attribute("actionset")
//    fun getAttributeActionSetRef(): GenericAttributeValue<ActionsetRef>
}