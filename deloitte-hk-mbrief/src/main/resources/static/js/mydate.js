Date.prototype.myFromat=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "/u65e5",         
    "1" : "/u4e00",         
    "2" : "/u4e8c",         
    "3" : "/u4e09",         
    "4" : "/u56db",         
    "5" : "/u4e94",         
    "6" : "/u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
}

function DateAdd(interval, number, date) {
    switch (interval) {
    case "y ": {
        date.setFullYear(date.getFullYear() + number);
        return date;
        break;
    }
    case "q ": {
        date.setMonth(date.getMonth() + number * 3);
        return date;
        break;
    }
    case "m ": {
        date.setMonth(date.getMonth() + number);
        return date;
        break;
    }
    case "w ": {
        date.setDate(date.getDate() + number * 7);
        return date;
        break;
    }
    case "d ": {
        date.setDate(date.getDate() + number);
        return date;
        break;
    }
    case "h ": {
        date.setHours(date.getHours() + number);
        return date;
        break;
    }
    case "m ": {
        date.setMinutes(date.getMinutes() + number);
        return date;
        break;
    }
    case "s ": {
        date.setSeconds(date.getSeconds() + number);
        return date;
        break;
    }
    default: {
        date.setDate(d.getDate() + number);
        return date;
        break;
    }
    }
}
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format1 = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
//调用： var time1 = new Date().Format("yyyy-MM-dd");var time2 = new Date().Format("yyyy-MM-dd HH:mm:ss");

function isInteger(obj) {
// return (obj | 0) === obj
// ^[0-9]*[1-9][0-9]*$
 if(/^\d+$/.test(obj))  
   return true;
  else 
   return false;
}

function encapsUrlParamJson(url, params) {
	return JSON.stringify(encapsUrlParam(url, params));
}
function encapsUrlParam(url, params) {
	var index = {};
	data = [];
	if (!!params) {
		var parms = params.split('&');
		for (var i = 0, len = parms.length; i < len; i++) {
			if (parms[i] != '') {
				var p = new Array();
				var idx = parms[i].indexOf("=");
				if (idx == -1) {
					p.push(parms[i]);
				} else {
					p.push(parms[i].substring(0, idx));
					p.push(parms[i].substring(idx + 1, parms[i].length));
				};
				if (p.length == 1 || (p.length == 2 && p[1] == '')) {
					data.push(['']);
					index[p[0]] = data.length - 1
				} else if (typeof(p[0]) == 'undefined' || p[0] == '') {
					data[0] = [p[1]]
				} else if (typeof(index[p[0]]) == 'undefined') {
					data.push([p[1]]);
					index[p[0]] = data.length - 1
				} else {
					data[index[p[0]]].push(p[1])
				}
			}
		}
	}
	var obj = new Object();
	obj["url"] = url;
	obj["params"] = data;
	obj["index"] = index;
	return obj;
}

function parseUrlParamJson(json) {
	try {
		return parseUrlParam(eval('(' + json + ')'));
	} catch (error) {
		return "";
	}	
}
function parseUrlJson(json) {
	try {
		return parseUrl(eval('(' + json + ')'));
	} catch (error) {
		return "";
	}	
}
function parseUrlParam(obj) {
	var result = "";
	if (!!obj) {
		var url = obj["url"];
		var index = obj["index"];
		var data = obj["params"];
		for (var attr in index) {
			var idx = index[attr];
			//result = result + idx + "=";
			var vv;
			try {
				vv = typeof(idx) == 'number' ? data[idx]: data[index[idx]];
			} catch (error) {}
			if (!!vv) {
				result = result + "&" + attr + "=" + vv;
			}
		}
	}
	if (result.length > 0) {
		result = result.substr(1, result.length);
	}	
	return result;
}
function parseUrl(obj) {
	var result = "&";
	if (!!obj) {
		result = obj["url"];
	}
	return result;
}

