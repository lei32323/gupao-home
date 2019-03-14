package com.gupaoedu.home.service.jsoncheck;

import java.io.Serializable;
import java.util.List;

public class FieldList implements Serializable{
	
    /**
	 * @author zhaopei
	 */
	private static final long serialVersionUID = 1L;
	private List<Field> field;

	public List<Field> getField() {
		return field;
	}

	public void setField(List<Field> field) {
		this.field = field;
	}
    
    
}
