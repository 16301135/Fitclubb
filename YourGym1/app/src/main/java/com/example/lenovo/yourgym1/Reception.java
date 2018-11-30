package com.example.lenovo.yourgym1;
//创建接受服务器返回数据的类
public class Reception {
    //根据返回数据的格式和数据解析方式定义
    private int status;
    private content content;
    private static class content{
        private String username;
    }
    public String toString(){
        return content.username;
    }
    public int getStatus(){
        return status;
    }
    public content getContent(){
        return content;
    }
}
