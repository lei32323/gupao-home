package com.gupaoedu.home.service.jsoncheck;

public class Obj{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String obj_type;
	
	private String obj_num;
	
	private String obj_name;
	
	private String par_obj_num;
	
	private String oper_type;
	
	private String asset_id;
	
	private String yl_flag;
	
	private String pre_action_code;
	
	private AttrList attr_list;
    
	private String par_asset_id;
	
	private String orig_obj_num;
	
	private FieldList field_list;
	
	
	
	public String getOrig_obj_num() {
		return orig_obj_num;
	}

	public void setOrig_obj_num(String orig_obj_num) {
		this.orig_obj_num = orig_obj_num;
	}

	public String getPar_asset_id() {
		return par_asset_id;
	}

	public void setPar_asset_id(String par_asset_id) {
		this.par_asset_id = par_asset_id;
	}

	public String getObj_type() {
		return obj_type;
	}

	public void setObj_type(String obj_type) {
		this.obj_type = obj_type;
	}

	public String getObj_num() {
		return obj_num;
	}

	public void setObj_num(String obj_num) {
		this.obj_num = obj_num;
	}

	public String getObj_name() {
		return obj_name;
	}

	public void setObj_name(String obj_name) {
		this.obj_name = obj_name;
	}

	public String getPar_obj_num() {
		return par_obj_num;
	}

	public void setPar_obj_num(String par_obj_num) {
		this.par_obj_num = par_obj_num;
	}

	public String getOper_type() {
		return oper_type;
	}

	public void setOper_type(String oper_type) {
		this.oper_type = oper_type;
	}

	public String getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}

	public String getYl_flag() {
		return yl_flag;
	}

	public void setYl_flag(String yl_flag) {
		this.yl_flag = yl_flag;
	}

	public String getPre_action_code() {
		return pre_action_code;
	}

	public void setPre_action_code(String pre_action_code) {
		this.pre_action_code = pre_action_code;
	}

	public AttrList getAttr_list() {
		return attr_list;
	}

	public void setAttr_list(AttrList attr_list) {
		this.attr_list = attr_list;
	}

	public FieldList getField_list() {
		return field_list;
	}

	public void setField_list(FieldList field_list) {
		this.field_list = field_list;
	}
	
	
}
