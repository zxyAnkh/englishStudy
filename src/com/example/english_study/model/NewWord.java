package com.example.english_study.model;

public class NewWord {
	private String word;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	public NewWord(){
		word = "";
	}
	public NewWord(String word){
		this.word=word;
	}
}
