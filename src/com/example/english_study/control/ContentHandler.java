package com.example.english_study.control;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import com.example.english_study.model.DicWord;

public class ContentHandler extends DefaultHandler{
	//http://www.cxyclub.cn/n/54661/
	public DicWord dicWord=null;
    private String tagName=null;
    private String interpret="";       //防止空指针异常
    private String orig="";
    private String trans="";
    private boolean isChinese=false;
    public ContentHandler(){
    	dicWord=new DicWord();
        isChinese=false;
    }
    
    public DicWord getDicWord(){
        
        return dicWord;
    }
    
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
        if(length<=0)
            return;
        for(int i=start; i<start+length; i++){
            if(ch[i]=='\n')
                return;
        }
        
        //去除莫名其妙的换行！
        
        String str=new String(ch,start,length);
        if(tagName=="key"){
        	dicWord.setWord(str);
        }else if(tagName=="ps"){
            if(dicWord.getpUK().length()<=0){
            	dicWord.setpUK(str);
            }else{
            	dicWord.setpUS(str);
            }
        }else if(tagName=="pos"){
            isChinese=false;
            interpret=interpret+str+" ";
        }else if(tagName=="acceptation"){
            interpret=interpret+str+"\n";
            interpret=dicWord.getMeans()+interpret;
            dicWord.setMeans(interpret);
            interpret=""; //初始化操作，预防有多个释义
        }else if(tagName=="orig"){
            
            
            orig=dicWord.getExamples();
            dicWord.setExamples(orig+str+"\n");
            
            
        }else if(tagName=="trans"){
            String temp=dicWord.getExamples_means()+str+"\n";
            dicWord.setExamples_means(temp);
            
        }else if(tagName=="fy"){
            isChinese=true;
            dicWord.setMeans(str);
        }


    }



    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // TODO Auto-generated method stub
        super.endElement(uri, localName, qName);
        tagName=null;
        

    }


    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
        super.startElement(uri, localName, qName, attributes);
        tagName=localName;
    }

    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        super.endDocument();
        if(isChinese)
            return;
        String interpret=dicWord.getMeans();
        if(interpret!=null && interpret.length()>0){
            char[] strArray=interpret.toCharArray();
            dicWord.setMeans(new String(strArray,0,interpret.length()-1));
                //去掉解释的最后一个换行符
        }
        
    }
    
    
    

}
