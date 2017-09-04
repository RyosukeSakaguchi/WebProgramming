package userManagement;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Common {

	/**パスワードを暗号化するメソッド
	 * @param password
	 * @return 暗号化されたパスワード
	 * @throws NoSuchAlgorithmException
	 */
	public static String encrpt(String password) throws NoSuchAlgorithmException {
		//ハッシュを生成したい元の文字列
		String source = password;
		//ハッシュ生成前にバイト配列に置き換える際のCharset
		Charset charset = StandardCharsets.UTF_8;
		//ハッシュアルゴリズム
		String algorithm = "MD5";

		//ハッシュ生成処理
		byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
		String result = DatatypeConverter.printHexBinary(bytes);

		return result;
	}

}
