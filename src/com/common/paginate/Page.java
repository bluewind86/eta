package com.common.paginate;

import java.util.List;

import com.common.util.StringUtils;


/**
 * 分页实例
 * 
 * @作者 bulang
 * @创建日期 2012-8-15下午08:57:24
 * @版本 V 1.0
 */
public class Page {
	private int pageSize = 20; //每页显示记录数
	private int totalPage;		//总页数
	private int totalResult;	//总记录数
	private int currentPage;	//当前页
	private int currentResult;	//当前记录起始索引
	private boolean entityOrField;	//true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr;		//最终页面显示的底部翻页导航，详细见：getPageStr();
	private List<?> result ;//存放查询的结果集
	private String formName = ""; //查询条件在页面表单中的位置
	
	public Page() {
	}
	public Page(int pageSize) {
		this.pageSize = pageSize;
	}
	public Page(int pageSize,String formName) {
		this.pageSize = pageSize;
		this.formName = formName;
	}
	public Page(String formName) {
		this.formName = formName;
	}
	public int getTotalPage() {
		if(totalResult%pageSize==0)
			totalPage = totalResult/pageSize;
		else
			totalPage = totalResult/pageSize+1;
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public int getCurrentPage() {
		if(currentPage<=0)
			currentPage = 1;
		if(currentPage>getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if(totalResult>0){
			sb.append("<div class='pages_bar'>\n");
			if(currentPage==1){
				sb.append("	<a href='javascript:void(0);'>首页</a>\n");
				sb.append("	<a href='javascript:void(0);'>上页</a>\n");
			}else{	
				sb.append("	<a onclick=\"nextPage(1);return false;\" href=\"javascript:void(0);\">首页</a>\n");
				sb.append("	<a onclick=\"nextPage("+(currentPage-1)+");return false;\" href=\"javascript:void(0);\">上页</a>\n");
			}
			int showTag = 5;	//分页标签显示数量
			int startTag = 1;
			if(currentPage>showTag){
				startTag = currentPage-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=totalPage && i<=endTag; i++){
				if(currentPage==i)
					sb.append("<a href='javascript:void(0);' class='current_page'>"+i+"</a>\n");
				else
					sb.append("	<a onclick=\"nextPage("+i+");return false;\" href=\"javascript:void(0);\">"+i+"</a>\n");
			}
			if(currentPage==totalPage){
				sb.append("	<a href='javascript:void(0);'>下页</a>\n");
				sb.append("	<a href='javascript:void(0);'>尾页</a>\n");
			}else{
				sb.append("	<a onclick=\"nextPage("+(currentPage+1)+");return false;\" href=\"javascript:void(0);\">下页</a>\n");
				sb.append("	<a onclick=\"nextPage("+totalPage+");return false;\" href=\"javascript:void(0);\">尾页</a>\n");
			}
			sb.append("	<span>第"+currentPage+"页</span>\n");
			sb.append("	<span>共"+totalPage+"页</span>\n");
			sb.append("	<span>每页"+pageSize+"条记录,共"+totalResult+"条</span>\n");
			sb.append("</div>\n");
			
			
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function nextPage(page){");
			if(StringUtils.isNotBlank(formName)){//存在form表单
				sb.append("	if(true && document.forms['").append(formName).append("']){\n");
				sb.append("		var url = document.forms['").append(formName).append("'].getAttribute(\"action\");\n");
				sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
				sb.append("		else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
				sb.append("		document.forms['").append(formName).append("'].action = url+page+\"&pageChangeFlag=1\";\n");
				sb.append("		document.forms['").append(formName).append("'].submit();\n");
				sb.append("	}else{\n");
				sb.append("		var url = document.location+'';\n");
				sb.append("		if(url.indexOf('?')>-1){\n");
				sb.append("			if(url.indexOf('currentPage')>-1){\n");
				sb.append("				var reg = /currentPage=\\d*/g;\n");
				sb.append("				url = url.replace(reg,'currentPage=');\n");
				sb.append("			}else{\n");
				sb.append("				url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\";\n");
				sb.append("			}\n");
				sb.append("		}else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
				sb.append("		document.location = url + page + \"&pageChangeFlag=1\";\n");
				sb.append("	}\n");
			}else{
				sb.append("		var url = document.location+'';\n");
				sb.append("		if(url.indexOf('?')>-1){\n");
				sb.append("			if(url.indexOf('currentPage')>-1){\n");
				sb.append("				var reg = /currentPage=\\d*/g;\n");
				sb.append("				url = url.replace(reg,'currentPage=');\n");
				sb.append("			}else{\n");
				sb.append("				url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\";\n");
				sb.append("			}\n");
				sb.append("		}else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
				sb.append("		document.location = url + page;\n");
			}
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}
	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentResult() {
		currentResult = (getCurrentPage()-1)*getPageSize();
		if(currentResult<0)
			currentResult = 0;
		return currentResult;
	}
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	public boolean isEntityOrField() {
		return entityOrField;
	}
	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}
	public List<?> getResult() {
		return result;
	}
	public void setResult(List<?> result) {
		this.result = result;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
}
