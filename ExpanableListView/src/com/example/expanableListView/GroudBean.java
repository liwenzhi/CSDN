package com.example.expanableListView;

import java.util.List;

/**
 * 每一組的Bean
 */
public class GroudBean {

    String name;

    List<String> listChild;

    public GroudBean(String name, List<String> listChild) {
        this.name = name;
        this.listChild = listChild;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getListChild() {
        return listChild;
    }

    public void setListChild(List<String> listChild) {
        this.listChild = listChild;
    }



    @Override
    public String toString() {
        return "GroudBean{" +
                "name='" + name + '\'' +
                ", listChild=" + listChild +
                '}';
    }


}
