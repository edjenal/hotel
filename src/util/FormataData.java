package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormataData {

	public static String ddMMyyyy(Date data){
		String retorno = "";
		if (data != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    retorno = sdf.format(data);
		}
		return retorno;
	}
	
}