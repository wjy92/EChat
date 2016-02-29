package com.thirdnet.echat.bean;

/**
 * 作者：杨水强
 * 时间：2016/2/29
 */
public class MessageBean {
    private PersonBean personBean;
    private String msg;

    public MessageBean() {
    }

    public MessageBean(PersonBean personBean, String msg) {
        this.personBean = personBean;
        this.msg = msg;
    }

    public PersonBean getPersonBean() {
        return personBean;
    }

    public void setPersonBean(PersonBean personBean) {
        this.personBean = personBean;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
