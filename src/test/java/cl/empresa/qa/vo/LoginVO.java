package cl.empresa.qa.vo;


import org.apache.poi.xssf.usermodel.XSSFSheet;

import cl.empresa.qa.helpers.Helper;
	
	public class LoginVO {

		
		private String userVO;
		private String passVO;

		

		public LoginVO(String rutaExcel, String hoja) {

			XSSFSheet sheet = Helper.obtenerPaginaExcel(rutaExcel, hoja);

			this.userVO = Helper.obtenerCelda(sheet, 6, "B").getStringCellValue();
			this.passVO = Helper.obtenerCelda(sheet, 7, "B").getStringCellValue();
			

		}
		
		

	

		public String getUserVO() {
			return userVO;
		}




		public String getPassVO() {
			return passVO;
		}






		@Override
		public String toString() {
			return "LoginVO [userVO=" + userVO + ", passVO=" + passVO + "]";
		}




}
