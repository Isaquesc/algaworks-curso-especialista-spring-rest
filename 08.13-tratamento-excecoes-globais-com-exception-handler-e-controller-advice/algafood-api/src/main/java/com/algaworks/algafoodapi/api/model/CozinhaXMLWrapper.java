//package com.algaworks.algafoodapi.api.model;
//
//import com.algaworks.algafoodapi.domain.model.Cozinha;
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
//import lombok.Data;
//import lombok.NonNull;
//
//import java.util.List;
//
///*
// * @JacksonXmlRootElement é uma alternativa à @JsonRootName e
// * @JacksonXmlProperty à @JsonProperty.
// *
// * A diferença é que as anotações iniciadas com @JacksonXml só afetam
// * a serialização em XML. Já as anotações iniciadas com @Json
// * afetam tanto a serialização JSON como também XML.
// */
//
//@Data
//@JacksonXmlRootElement(localName = "cozinhas")
//public class CozinhaXMLWrapper {
//
//    @NonNull
//    @JacksonXmlElementWrapper(useWrapping = false)
//    @JacksonXmlProperty(localName = "cozinha")
//    private List<Cozinha> cozinhas;
//}
