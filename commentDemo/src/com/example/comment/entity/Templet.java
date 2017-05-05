package com.example.comment.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-11
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
public class Templet {

    private String id;
    private String text;
    private String classe;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
