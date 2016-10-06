package com.knight.estoque.adaptadores;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class AdaptadorDate extends XmlAdapter<XMLGregorianCalendar, Date> {
	
	@Override
	public Date unmarshal(XMLGregorianCalendar v) throws Exception {
		
		return v.toGregorianCalendar().getTime();
		
	}

	@Override
	public XMLGregorianCalendar marshal(Date v) throws Exception {
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(v);
		XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		
		xmlGregorianCalendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
		xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		
		return xmlGregorianCalendar;
	}

}
