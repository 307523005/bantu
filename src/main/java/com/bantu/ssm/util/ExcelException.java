package com.bantu.ssm.util;



/**
 * 用来处理导入导出中遇到的问题  数据源为空 有重复行,待导入数据已在数据库中存在等
 * @author 钮豪
 * @version 创建时间 ： 2018-1-28
 *
 */
@SuppressWarnings("serial")
public class ExcelException extends Exception {
	
	public ExcelException() {
        // TODO Auto-generated constructor stub
    }

    public ExcelException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public ExcelException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public ExcelException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
}

