package com.sean.model.template;

/**
 * @description: TODO
 * @package_name: com.sean.model.template
 * @data: 2019-8-7 10:20
 * @author: Sean
 * @version: V1.0
 */
public class TemplateData {
    private Template first;
    private Template keyword1;
    private Template keyword2;
    private Template keyword3;
    private Template remark;

    public Template getFirst() {
        return first;
    }

    public void setFirst(Template first) {
        this.first = first;
    }

    public Template getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(Template keyword1) {
        this.keyword1 = keyword1;
    }

    public Template getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(Template keyword2) {
        this.keyword2 = keyword2;
    }

    public Template getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(Template keyword3) {
        this.keyword3 = keyword3;
    }

    public Template getRemark() {
        return remark;
    }

    public void setRemark(Template remark) {
        this.remark = remark;
    }

    public TemplateData(Template first, Template keyword1, Template keyword2, Template keyword3, Template remark) {
        this.first = first;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;
        this.remark = remark;
    }
}
