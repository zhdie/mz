var pageDoms = new Object();
var pageIfms = new Object();
var pageDomIds = new Array();
var pageIfmIds = new Array();
var curMenu;
var menuItems = new Object();
var testMenu = [
	{"start": true, "active": true, "title": "Load DOM", "menuid": "_MU_LDOM", "url": "one/testdom.html", "clearCache": false, "useCache": true, "mtype": 1, "icon": "icon-home", "pagetype": "multi"},
	{
		"title": "MAP",
		"mtype": 2,
		"icon": "icon-cogs",
		"children": [{
			"start": false,
			"active": false,
			"title": "Load Map",
			"menuid": "_MU_LMAP",
			"url": "one/map-cq.html",
			"clearCache": false,
			"useCache": true,
			"pagetype": "multi"
		},{
			"start": false,
			"active": false,
			"title": "load Map Iframe",
			"menuid": "_MU_LMAP_IFM",
			"url": "one/map-ifm.html",
			"clearCache": false,
			"useCache": true,
			"pagetype": "multi"
		}]
	},
	{
		"title": "TEST",
		"mtype": 2,
		"icon": "icon-cogs",
		"children": [{
			"start": false,
			"active": false,
			"title": "LOGIN Iframe",
			"menuid": "_LOGIN_IFM",
			"url": "one/login-ifm.html",
			"clearCache": false,
			"useCache": true,
			"pagetype": "ifm"
		},{
			"start": false,
			"active": false,
			"title": "J DataGrid",
			"menuid": "_MU_JDATAGRID_DOM",
			"url": "one/jdatagrid.html",
			"clearCache": false,
			"useCache": true,
			"pagetype": "multi"
		},{
			"start": false,
			"active": false,
			"title": "Datagrid Ifm",
			"menuid": "_DATAGRID_IFM",
			"url": "one/datagrid-ifm.html",
			"clearCache": false,
			"useCache": true,
			"pagetype": "ifm"
		},{
			"start": false,
			"active": false,
			"title": "Single Datagrid Ifm",
			"menuid": "_SINGLE_DG_IFM",
			"url": "one/single-ifm.html",
			"clearCache": false,
			"useCache": true,
			"pagetype": "single"
		}]
	},
	{
		"title": "MAP",
		"mtype": 2,
		"icon": "icon-cogs",
		"children": [{
			"start": false,
			"active": false,
			"title": "Load Map",
			"menuid": "_MU_LMAP2",
			"url": "one/map-cq.html",
			"clearCache": false,
			"useCache": true,
			"pagetype": "multi"
		},{
			"start": false,
			"active": false,
			"title": "load Map Iframe",
			"menuid": "_MU_LMAP_IFM2",
			"url": "one/map-ifm.html",
			"clearCache": false,
			"useCache": true,
			"pagetype": "multi"
		}]
	}
]

/*
* 
*
*/
function initSiderBar(userid, applicationid) {
	var data = testMenu;
	$.ajax({
		type: "POST",
		url: webProjRoot + "login/getmenu.action?tmpStamp="+new Date(),
		async: false,
		data: {userid : userid, applicationid: applicationid},
		dataType: "json",
		success: function(resp){
			//alert(data.result);
			if (resp.success == true) {
				data = resp.data;	
			}else{
				alert("Username or password error！");
				window.top.location.href='/index.html?service=' + url + '&applicationid='+applicationid;
			}			
		}
	});
	
	var pbar = $(".page-sidebar-menu");
	for (var i = 0; i < data.length; i++) {
		var menudata = data[i];
		var li;
		if (menudata["mtype"] == 1) {
			var listr = "\"<li class='";
			if (menudata["start"] == true) {
				listr = listr + "start active";
			}
			listr = listr + "'>\"";
			li = $(listr);
		
			var astr = "\"<a href='javascript:void(0)' menuid='" + menudata["menuid"] +"' url='" + menudata["url"] +"' clearCache=" + menudata["clearCache"]+" useCache="+ menudata["useCache"] + " mtype=" + menudata["mtype"];
			if (menudata["pagetype"] == "ifm") {
				astr = astr + " onclick='switchIframe(event)'></a>\"";
			} else if (menudata["pagetype"] == "multi") {
				astr = astr + " onclick='switchPage(event)'></a>\"";
			} else {
				//astr = astr + " onclick='switchSingleIfm(event)'>"+ submenud["title"] +"</a>\"";
				astr = astr + " onclick='switchSingleIfm(event)'>" +"</a>\"";
			}
			if (menudata["active"] == true) {
				li.append($(astr).append("<i class='"+ menudata["icon"] +"'></i><span class='title'>"+ menudata["title"] +"</span><span class='selected'></span>"));
			} else {
				li.append($(astr).append("<i class='"+ menudata["icon"] +"'></i><span class='title'>"+ menudata["title"] +"</span><span class='noselected'></span>"));
			}
		} else if (menudata["mtype"] == 2) {
			var listr = "\"<li class='";
			if (menudata["start"] == true) {
				listr = listr + "start open active";
			}
			listr = listr + "'>\"";
			li = $(listr);
			
			var navstr = "\"<a href='javascript:;'>";
			if (menudata["active"] == true) {
				navstr = navstr + "<i class='"+menudata["icon"]+"'></i><span class='title'>"+menudata["title"]+"</span><span class='selected'></span><span class='arrow open'></span>";
			} else {
				navstr = navstr + "<i class='"+menudata["icon"]+"'></i><span class='title'>"+menudata["title"]+"</span><span class='noselected'></span><span class='arrow'></span>";
			}
			navstr = navstr + "</a>\"";
			li.append($(navstr));
			
			var submenu = $("<ul class='sub-menu'></ul>");
			if ($.isArray(menudata["children"])) {
				for (var j = 0; j < menudata["children"].length; j++) {
					var submenud = menudata["children"][j];
					var astr = "\"<a href='javascript:void(0)' menuid='" + submenud["menuid"] +"' url='" + submenud["url"] +"' clearCache=" + submenud["clearCache"]+" useCache="+ submenud["useCache"] + " mtype=2 ";
					if (submenud["pagetype"] == "ifm") {
						astr = astr + " onclick='switchIframe(event)'>"+ submenud["title"] +"</a>\"";
					} else if (submenud["pagetype"] == "multi") {
						astr = astr + " onclick='switchPage(event)'>"+ submenud["title"] +"</a>\"";
					} else {
						astr = astr + " onclick='switchSingleIfm(event)'>"+ submenud["title"] +"</a>\"";
					}
					var subli = $("<li></li>");
					subli.append($(astr));
					submenu.append(subli);
				}
			}
			li.append(submenu);			
		}
		
		pbar.append(li);
	}
}

function initMenuItems() {
	var items = $("*[menuid]",$("ul.page-sidebar-menu"));
	$.each(items, function(idx, item) {
		menuItems[$(item).attr("menuid")] = item;
	});
}
function removeAllPageDoms() {
	for (var i = 0; i < pageDomIds.length; i++) {
		pageDoms[pageDomIds[i]].remove();
		delete pageDoms[pageDomIds[i]];
	}
}
function removeAllPageIfms() {
	for (var i = 0; i < pageIfmIds.length; i++) {
		$(pageIfms[pageIfmIds[i]]).remove();
		delete pageIfms[pageIfmIds[i]];
	}
}
function hideAllPageDoms() {
	for (var i = 0; i < pageDomIds.length; i++) {
		pageDoms[pageDomIds[i]].hide();
	}
}
function hideAllPageIfms() {
	for (var i = 0; i < pageIfmIds.length; i++) {
		pageIfms[pageIfmIds[i]].style.display = "none";
	}
}
function removeAllPages() {
	removeAllPageDoms();
	removeAllPageIfms();
	hideSingleIfm();
}
function hideAllPages() {
	hideAllPageDoms();
	hideAllPageIfms();
	hideSingleIfm();
}
function removePage(menuid) {
	if (!!pageDoms[menuid]) {
		pageDoms[menuid].remove();
		delete pageDoms[menuid];
		var idx = $.inArray(menuid, pageDomIds);
		pageDomIds.splice(idx,1);
	} else if (!!pageIfms[menuid]) {
		$(pageIfms[menuid]).remove();
		delete pageIfms[menuid];
		var idx = $.inArray(menuid, pageIfmIds);
		pageIfmIds.splice(idx,1);
	}
}
function hidePage(menuid) {
	if (!!pageDoms[menuid]) {
		pageDoms[menuid].hide();
	} else if (!!pageIfms[menuid]) {
		pageIfms[menuid].style.display = "none";
	}
}
function hideSingleIfm() {
	$(".main_content_ifm").hide();
}
function showSingleIfm() {
	$(".main_content_ifm").show();
}

function showPage(menuid) {
	if (!!pageDoms[menuid]) {
		pageDoms[menuid].show();
	} else if (!!pageIfms[menuid]) {
		pageIfms[menuid].style.display = "block";
	}
}

function getContentWidth() {
	//alert($('.btn-navbar').hasClass('collapsed'));
	if ($(window).width() > 979) {
		var sidebarWidth = $(".page-sidebar").width();				
		if (sidebarWidth > 225) {
			return $(window).width() - 225 - 40;
		} else {
			return $(window).width() - sidebarWidth - 40;
		}		
	} else {
		return $(window).width() - 40;
	}
}
// 除去 head 和 面包屑的显示内容高度
function getContentHeight() {
	//return getPageHeight() - 42;
	return getPageHeight() - 72;
}
// 除去 head 的页面高度
function getPageHeight() {
	var headHeight = $(".header .navbar-inner").height();
	//return $(window).height() - 42;
	return $(window).height() - headHeight;
}
function procSiderbarHeight() {
	var pageContent = $(".page-content");
	var height = getPageHeight() + "px";
	pageContent.height(height);
	pageContent.css("min-height", height);
	$(".page-sidebar-menu").slimScroll({
		height: height
	});
}


function addPageDom(menuid, url) {
	//alert(url+":"+token);
	//alert($(window).height());
	removePage(menuid);
	var pagediv=$("<div id='"+menuid+"' ></div>");	
	
	$(".main_content:first").append(pagediv);
	pagediv.load(url, function(result) {
		//alert($("ul.breadcrumb").width());		
		//alert(result);
	});

	pageDomIds.push(menuid);
	pageDoms[menuid] = pagediv;
	$.unique(pageDomIds);
}
function addPageIfm(menuid, url) {
	removePage(menuid);			
	var ifm = document.createElement("iframe");
	ifm.setAttribute("frameborder","0");
	ifm.scrolling = "auto";
	ifm.style.width = "100%";
	ifm.style.height = "100%";
	ifm.name = menuid;
	ifm.id = menuid;
	if (ifm.attachEvent){
		ifm.attachEvent("onload", function(){
			ifm.style.height = getContentHeight() + "px";
		});
	} else {
		ifm.onload = function() {
			ifm.style.height = getContentHeight() + "px";
		};
	}
	ifm.src = url;
	$(".main_content:first")[0].appendChild(ifm);
	pageIfmIds.push(menuid);
	pageIfms[menuid] = ifm;
	$.unique(pageIfmIds);
}
function switchPage(event) {
	//$(".page-content").children().remove();
	//$(".page-content").load(url);
	var menuObj;
	if (event.target) {
		if (!!event.target.getAttribute("menuid")) {
			menuObj = event.target;
		} else {
			menuObj = event.target.parentNode;
		}
	} else if (event.srcElement){
		if (!!event.srcElement.getAttribute("menuid")) {
			menuObj = event.srcElement;
		} else {
			menuObj = event.srcElement.parentNode;
		}
	}
	var menuid = menuObj.getAttribute("menuid");
	var clearCache = menuObj.getAttribute("clearCache");
	var useCache = menuObj.getAttribute("useCache");
	var url = menuObj.getAttribute("url");
	if (!!webProjRoot) {
		url = webProjRoot + url;
	}
	if (true == clearCache) {
		removeAllPages();
	} else {
		hideAllPages();
	}	
	var pageDom = pageDoms[menuid];//$("#" + menuid);
	if (!!pageDom && (true == useCache)) {
		showPage(menuid);
	} else {
		removePage(menuid);
		addPageDom(menuid, url);
	}
	processCurMenu(menuObj);
	processBreadcrumb(menuObj);
}
function switchIframe(event) {
	var menuObj;
	if (event.target) {
		if (!!event.target.getAttribute("menuid")) {
			menuObj = event.target;
		} else {
			menuObj = event.target.parentNode;
		}
	} else if (event.srcElement){
		if (!!event.srcElement.getAttribute("menuid")) {
			menuObj = event.srcElement;
		} else {
			menuObj = event.srcElement.parentNode;
		}
	}
	var menuid = menuObj.getAttribute("menuid");
	var clearCache = menuObj.getAttribute("clearCache");
	var useCache = menuObj.getAttribute("useCache");
	var url;
	if (!!webProjRoot) {
		var url = webProjRoot + menuObj.getAttribute("url");
	} else {
		var url = menuObj.getAttribute("url");
	}
	
	if (true == clearCache) {
		removeAllPages();
	} else {
		hideAllPages();
	}
	var pageIfm = pageIfms[menuid];//$("#" + menuid);
	if (!!pageIfm && (true == useCache)) {
		showPage(menuid);
	} else {
		removePage(menuid);
		addPageIfm(menuid, url);
	}
	processCurMenu(menuObj);
	processBreadcrumb(menuObj);
}
function switchSingleIfm(event) {
	var menuObj;
	if (event.target) {
		if (!!event.target.getAttribute("menuid")) {
			menuObj = event.target;
		} else {
			menuObj = event.target.parentNode;
		}
	} else if (event.srcElement){
		if (!!event.srcElement.getAttribute("menuid")) {
			menuObj = event.srcElement;
		} else {
			menuObj = event.srcElement.parentNode;
		}
	}
	var menuid = menuObj.getAttribute("menuid");
	var clearCache = menuObj.getAttribute("clearCache");
	var useCache = menuObj.getAttribute("useCache");
	var url = menuObj.getAttribute("url");
	if (true == clearCache) {
		removeAllPages();
	} else {
		hideAllPages();
	}
	showSingleIfm();
	var ifm = $(".main_content_ifm")[0];
	if (ifm.attachEvent){
		ifm.attachEvent("onload", function(){
			ifm.style.height = getContentHeight() + "px";
		});
	} else {
		ifm.onload = function() {
			ifm.style.height = getContentHeight() + "px";
		};
	}
	ifm.src = url;	
	processCurMenu(menuObj);
	processBreadcrumb(menuObj);
}

function processCurMenu(menuObj) {
	if (!!curMenu) {
		if (curMenu.getAttribute("mtype") == 2) {
			$(curMenu).parent().parent().parent().removeClass("active");
			$(curMenu).parent().removeClass("active");					
			$(curMenu).parent().parent().parent().find("a:first").find(".selected").removeClass("selected").addClass("noselected");
			$(curMenu).parent().parent().parent().find("a:first").find(".arrow").removeClass("open");
		} else if (curMenu.getAttribute("mtype") == 1) {
			$(curMenu).parent().removeClass("active");
			$(curMenu).find(".selected").removeClass("selected").addClass("noselected");
		}
	}
	if (menuObj) {
		if (menuObj.getAttribute("mtype") == 2) {
			$(menuObj).parent().parent().parent().addClass("active");
			$(menuObj).parent().addClass("active");
			$(menuObj).parent().parent().parent().find("a:first").find(".noselected").removeClass("noselected").addClass("selected");
			$(menuObj).parent().parent().parent().find("a:first").find(".arrow").addClass("open");
		} else if (menuObj.getAttribute("mtype") == 1) {
			$(menuObj).parent().addClass("active");
			$(menuObj).find(".noselected").removeClass("noselected").addClass("selected");
		}
		curMenu = menuObj;
	}
}
function processBreadcrumb(menuObj) {
	var breadcrumb = $("ul.breadcrumb");
	var bread1 = breadcrumb.find("li:eq(1)");
	if (menuObj.getAttribute("mtype") == 2) {				
		bread1.find("a").text($(menuObj).parent().parent().prev().find(".title").text());
		bread1.find("i").show();
		var bread2 = breadcrumb.find("li:eq(2)");
		bread2.find("a").text($(menuObj).text());
		bread2.show();
		bread2.find("a").attr("onclick", "breadcrumbClick('"+ menuObj.getAttribute("menuid") +"')");
	} else if (menuObj.getAttribute("mtype") == 1) {
		bread1.find("a").text($(menuObj).find(".title").text());
		bread1.find("i").hide();
		var bread2 = breadcrumb.find("li:eq(2)");
		bread2.hide();
		bread1.find("a").attr("onclick", "breadcrumbClick('"+ menuObj.getAttribute("menuid") +"')");
	}			
}

function getCurIframe() {
	if (!!curMenu) {
		var menuid = $(curMenu).attr("menuid");
		if (!!menuid) {
			return pageIfms[menuid];
		}
	}
}

function breadcrumbClick(menuid) {
	if (menuItems[menuid]) {
		menuItems[menuid].click();
	}
}

 