package patterned;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * テキスト処理のための派生クラスに共通の処理を定義する抽象クラス
 * 
 * @author shiva
 *
 */
public abstract class TextProcessorBase {

	/**
	 * メソッドの実行順序を規定する
	 * 
	 * final修飾子をつけることで、小クラスでのオーバーライドを禁止する
	 * 
	 * @param filePath
	 */
	final public void completeProcess(String filePath) {
		// 1. ファイルの読み込み
		String[] words = textFileToStringArray(filePath);
		// 2. 文字配列の処理
		process(words);
		// 3. 結果の表示
		printResult();
	}

	/**
	 * 文字列の配列を受け取り処理を行う
	 * 
	 * @param words
	 */
	protected abstract void process(String[] words);

	/**
	 * 処理結果をコンソールに表示
	 */
	protected abstract void printResult();

	/**
	 * テキストファイルを読み込みスペースで区切って文字列の配列に格納する
	 * 
	 * @param filePath
	 *            ファイルの場所
	 * @return 分割された文字列の配列
	 */
	protected String[] textFileToStringArray(String filePath) {
		// System.out.println(new File(filePath).getAbsolutePath());

		// try-with-resource 構文
		try (BufferedReader br = new BufferedReader(new FileReader(new File(
				filePath)))) {
			ArrayList<String> list = new ArrayList<String>();

			String line = null;
			while ((line = br.readLine()) != null) {
				String[] words = line.split(" ");
				for (String word : words) {
					String w = filter(word);
					if (w.length() > 0) {
						list.add(w);
					}
				}
			}
			// ArrayListをString[]に変換してリターン
			return list.toArray(new String[0]);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// 読み込みに失敗した場合は空の配列をリターン
		return new String[0];
	}

	/**
	 * 不要な文字を取り除く
	 * 
	 * @param word
	 * @return
	 */
	private String filter(String word) {
		String w = word.replace(",", "").replace(".", "").replace(":", "")
				.replace("-", "").replace("—", "").replace("?", "")
				.replace("\"", "");
		try {
			// 数字として認識できる場合は空文字列を返す
			Double.parseDouble(word);
			return "";
		} catch (Exception e) {
		}

		return w.trim();
	}

}
