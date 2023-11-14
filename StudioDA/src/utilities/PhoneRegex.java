package utilities;

public class PhoneRegex extends Exception {

	public PhoneRegex(String str) {
		super(str);
	}

	public static void checkPhone(String str) throws PhoneRegex {
		String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

		boolean kt = str.matches(reg);

		if (kt == false) {
			throw new PhoneRegex("Loi: Khong dung dinh dang!");
		} else {
			System.out.println("Dung dinh dang so dien thoai!");
		}
	}
}
