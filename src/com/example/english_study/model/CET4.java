package com.example.english_study.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class CET4 {
	private String id;
	private String word;
	private String pUK;
	private String pUS;
	private String means;
	private String examples;
	private String examples_means;
	private String flag;
	public CET4(String id,String word,String means,String flag){
		this.setId(id);
		this.word=word;
		this.means=means;
		this.flag=flag;
	}
	public CET4(){
		this.setId("");
		this.word="";
		this.pUK="";
		this.pUS="";
		this.means="";
		this.examples="";
		this.examples_means="";
		this.flag="";
	}
	public CET4(String id,String word,String pUK,String pUS,String means,String examples,String examples_means,String flag){
		this.setId(id);
		this.word=word;
		this.pUK=pUK;
		this.pUS=pUS;
		this.means=means;
		this.examples=examples;
		this.examples_means=examples_means;
		this.flag=flag;
	}
	public void printDicWord(){
		System.out.println(this.word);
		System.out.println(this.pUK);
		System.out.println(this.pUS);
		System.out.println(this.means);
		System.out.println(this.examples);
		System.out.println(this.examples_means);
		System.out.println(this.flag);
	}
	public ArrayList<String> getExamplesList() throws IOException{
		ArrayList<String> arrayList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new StringReader(this.examples));
		String str = null;
		while((str=br.readLine())!=null){
			arrayList.add(str);
		}
		return arrayList;
	}
	public ArrayList<String> getExamplesMeansList() throws IOException{
		ArrayList<String> arrayList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new StringReader(this.examples_means));
		String str = null;
		while((str=br.readLine())!=null){
			arrayList.add(str);
		}
		return arrayList;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getpUK() {
		return pUK;
	}
	public void setpUK(String pUK) {
		this.pUK = pUK;
	}
	public String getpUS() {
		return pUS;
	}
	public void setpUS(String pUS) {
		this.pUS = pUS;
	}
	public String getMeans() {
		return means;
	}
	public void setMeans(String means) {
		this.means = means;
	}
	public String getExamples() {
		return examples;
	}
	public void setExamples(String examples) {
		this.examples = examples;
	}
	public String getExamples_means() {
		return examples_means;
	}
	public void setExamples_means(String examples_means) {
		this.examples_means = examples_means;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
