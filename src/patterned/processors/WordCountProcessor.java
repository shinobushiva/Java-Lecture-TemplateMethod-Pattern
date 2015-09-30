package patterned.processors;

import java.util.HashSet;
import java.util.Set;

import patterned.TextProcessorBase;

/**
 * テキスト内の単語数を数える
 * 
 * @author shiva
 *
 */
public class WordCountProcessor extends TextProcessorBase {

	private int wordCount;

	/**
	 * 出現した単語数を数える（重複は省く）
	 */
	@Override
	public void process(String[] words) {

		// セットは重複要素を保持しない
		Set<String> set = new HashSet<String>();

		for (String word : words) {
			set.add(word);
		}

		// 重複要素は含まれないのでセットのサイズが単語の数
		wordCount = set.size();

	}

	/**
	 * ワードカウントの結果をコンソールに表示する
	 */
	@Override
	public void printResult() {
		System.out.println("Word Count : " + wordCount);
	}

	public static void main(String[] args) {

		// インスタンスの生成
		TextProcessorBase wcp = new WordCountProcessor();
		wcp.completeProcess("resources/jobs_speach.txt");

		// // 1. ファイルの読み込み
		// String[] words =
		// wcp.textFileToStringArray("resources/jobs_speach.txt");
		// // 2. 文字配列の処理
		// wcp.process(words);
		// // 3. 結果の表示
		// wcp.printResult();

	}

}
