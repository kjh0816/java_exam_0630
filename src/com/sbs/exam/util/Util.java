package com.sbs.exam.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

public class Util {
	public static String getNowDateStr() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dateStr = format1.format(System.currentTimeMillis());

		return dateStr;
	}
	
	public static String getKeywordsStrFromStr(String str) {
		String keywordsStr = "";

		List<String> keywords = getKeywordsFromStr(str);

		for (String keyword : keywords) {
			keywordsStr += " #" + keyword;
		}

		keywordsStr = keywordsStr.trim();

		return keywordsStr;
	}

	public static List<String> getKeywordsFromStr(String str) {
		List<String> keywords = new ArrayList<>();

		Komoran komoran = new Komoran(DEFAULT_MODEL.EXPERIMENT);

		KomoranResult analyzeResultList = komoran.analyze(str);

		List<Token> tokenList = analyzeResultList.getTokenList();
		for (Token token : tokenList) {
			String keyword = token.getMorph();
			String pos = token.getPos();

			switch (keyword) {
			case "어제":
			case "동시":
			case "이":
			case "날":
			case "대부분":
			case "푄":
			case "일부":
			case "이틀":
			case "때":
				continue;
			}

			switch (pos) {
			case "NNP":
			case "NNG":
				keywords.add(keyword);
				break;
			}
		}

		return keywords;
	}

}
