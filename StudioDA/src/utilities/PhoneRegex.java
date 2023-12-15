package utilities;

import javafx.scene.control.Alert.AlertType;

public class PhoneRegex extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PhoneRegex(String str) {
		super(str);
	}

	public static void checkPhone(String str) throws PhoneRegex {
		String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

		boolean kt = str.matches(reg);

		if (kt == false) {
			Notification.alert(AlertType.CONFIRMATION, "Lỗi, số điện thoại không đúng định dạng");
		} 
	}
}
