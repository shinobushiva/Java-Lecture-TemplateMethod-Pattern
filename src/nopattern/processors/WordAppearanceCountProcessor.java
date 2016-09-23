package nopattern.processors;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nopattern.TextProcessorBase;

/**
 * テキスト内の各単語の出現回数を数える
 * 
 * @author shiva
 *
 */
public class WordAppearanceCountProcessor extends TextProcessorBase {

	private Map<String, Integer> map = new HashMap<String, Integer>();

	/**
	 * 出現した単語数を数える（重複は省く）
	 */
	@Override
	public void process(String[] words) {

		for (String word : words) {
			// 全て小文字に変換
			word = word.toLowerCase();

			// まだ出現していなければ追加
			if (!map.containsKey(word)) {
				map.put(word, 0);
			}

			// 単語の出現回数をインクリメント
			int num = map.get(word) + 1;
			map.put(word, num);
		}

	}

	/**
	 * ワードカウントの結果をコンソールに表示する
	 */
	@Override
	public void printResult() {

		// ラムダを用いて出現回数の多い順にソートして出力
		Set<String> keys = map.keySet();
		keys.stream().sorted((s1, s2) -> {
			// 出現回数順にソート
				return map.get(s2) - map.get(s1);
			}).forEach((key) -> {
			// 出力
				System.out.println(key + ":" + map.get(key));
			});
	}

	public static void main(String[] args) {

		// インスタンスの生成
		TextProcessorBase wcp = new WordAppearanceCountProcessor();

		// 1. ファイルの読み込み
		String[] words = wcp.textFileToStringArray("resources/jobs_speach.txt");
		
		// 2. 文字配列の処理
		wcp.process(words);

		// 3. 結果の表示
		wcp.printResult();


	}

}
