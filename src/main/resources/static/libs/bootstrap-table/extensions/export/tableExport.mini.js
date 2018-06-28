!function(t){t.fn.extend({tableExport:function(e){function o(t,e,o){var n="";if(null!==t){var r=b(t,e,o),i=null===r||""===r?"":r.toString();"tsv"==P.type?(r instanceof Date&&r.toLocaleString(),n=y(i,"\t"," ")):r instanceof Date?n=P.csvEnclosure+r.toLocaleString()+P.csvEnclosure:((n=y(i,P.csvEnclosure,P.csvEnclosure+P.csvEnclosure)).indexOf(P.csvSeparator)>=0||/[\r\n ]/g.test(n))&&(n=P.csvEnclosure+n+P.csvEnclosure)}return n}function n(e){var o=[];return r(e,"tbody").each(function(){o.push.apply(o,i(t(this),P.tbodySelector))}),P.tfootSelector.length&&r(e,"tfoot").each(function(){o.push.apply(o,i(t(this),P.tfootSelector))}),o}function r(e,o){var n=e.parents("table").length;return e.find(o).filter(function(){return t(this).closest("table").parents("table").length===n})}function i(e,o){return e.find(o).filter(function(){return 0===t(this).find("table").length&&1===t(this).parents("table").length})}function s(e){var o=[];return t(e).find("thead").first().find("th").each(function(e,n){void 0!==t(n).attr("data-field")?o[e]=t(n).attr("data-field"):o[e]=e.toString()}),o}function a(e){var o=void 0!==e[0].cellIndex,n=void 0!==e[0].rowIndex,r=o||n?l(e):e.is(":visible"),i=e.data("tableexport-display");return o&&"none"!=i&&"always"!=i&&(n=void 0!==(e=t(e[0].parentNode))[0].rowIndex,i=e.data("tableexport-display")),n&&"none"!=i&&"always"!=i&&(i=e.closest("table").data("tableexport-display")),"none"!==i&&(1==r||"always"==i)}function l(t){var e=[];return Q&&(e=q.filter(function(){var e=!1;return this.nodeType==t[0].nodeType&&(void 0!==this.rowIndex&&this.rowIndex==t[0].rowIndex?e=!0:void 0!==this.cellIndex&&this.cellIndex==t[0].cellIndex&&void 0!==this.parentNode.rowIndex&&void 0!==t[0].parentNode.rowIndex&&this.parentNode.rowIndex==t[0].parentNode.rowIndex&&(e=!0)),e})),0==Q||0==e.length}function c(e,o,n){var r=!1;return a(e)?P.ignoreColumn.length>0&&(-1!=t.inArray(n,P.ignoreColumn)||-1!=t.inArray(n-o,P.ignoreColumn)||K.length>n&&void 0!==K[n]&&-1!=t.inArray(K[n],P.ignoreColumn))&&(r=!0):r=!0,r}function h(e,o,n,r,i){if("function"==typeof i){var s=!1;if("function"==typeof P.onIgnoreRow&&(s=P.onIgnoreRow(t(e),n)),!1===s&&-1==t.inArray(n,P.ignoreRow)&&-1==t.inArray(n-r,P.ignoreRow)&&a(t(e))){var l=t(e).find(o),h=0;l.each(function(e){var o,r=t(this),s=parseInt(this.getAttribute("colspan")),a=parseInt(this.getAttribute("rowspan"));if(X.forEach(function(t){if(n>=t.s.r&&n<=t.e.r&&h>=t.s.c&&h<=t.e.c)for(o=0;o<=t.e.c-t.s.c;++o)i(null,n,h++)}),!1===c(r,l.length,e)&&((a||s)&&(a=a||1,s=s||1,X.push({s:{r:n,c:h},e:{r:n+a-1,c:h+s-1}})),i(this,n,h++)),s)for(o=0;o<s-1;++o)i(null,n,h++)}),X.forEach(function(t){if(n>=t.s.r&&n<=t.e.r&&h>=t.s.c&&h<=t.e.c)for(ct=0;ct<=t.e.c-t.s.c;++ct)i(null,n,h++)})}}}function d(t,e){if(!0===P.consoleLog&&console.log(t.output()),"string"===P.outputMode)return t.output();if("base64"===P.outputMode)return L(t.output());if("window"===P.outputMode)return window.URL=window.URL||window.webkitURL,void window.open(window.URL.createObjectURL(t.output("blob")));try{var o=t.output("blob");saveAs(o,P.fileName+".pdf")}catch(o){j(P.fileName+".pdf","data:application/pdf"+(e?"":";base64")+",",e?t.output("blob"):t.output())}}function f(t,e,o){var n=0;if(void 0!==o&&(n=o.colspan),n>=0){for(var r=t.width,i=t.textPos.x,s=e.table.columns.indexOf(e.column),a=1;a<n;a++)r+=e.table.columns[s+a].width;if(n>1&&("right"===t.styles.halign?i=t.textPos.x+r-t.width:"center"===t.styles.halign&&(i=t.textPos.x+(r-t.width)/2)),t.width=r,t.textPos.x=i,void 0!==o&&o.rowspan>1&&(t.height=t.height*o.rowspan),"middle"===t.styles.valign||"bottom"===t.styles.valign){var l=("string"==typeof t.text?t.text.split(/\r\n|\r|\n/g):t.text).length||1;l>2&&(t.textPos.y-=(2-O)/2*e.row.styles.fontSize*(l-2)/3)}return!0}return!1}function u(e,o,n){void 0!==n.images&&o.each(function(){var o=t(this).children();if(t(this).is("img")){var r=E(this.src);n.images[r]={url:this.src,src:this.src}}void 0!==o&&o.length>0&&u(e,o,n)})}function p(e,o,n){o.each(function(){var o=t(this).children(),r=0;if(t(this).is("div")){var i=S(N(this,"background-color"),[255,255,255]),s=S(N(this,"border-top-color"),[0,0,0]),a=A(this,"border-top-width",P.jspdf.unit),l=this.getBoundingClientRect(),c=this.offsetLeft*n.dw;r=this.offsetTop*n.dh;var h=l.width*n.dw,d=l.height*n.dh;n.doc.setDrawColor.apply(void 0,s),n.doc.setFillColor.apply(void 0,i),n.doc.setLineWidth(a),n.doc.rect(e.x+c,e.y+r,h,d,a?"FD":"F")}else if(t(this).is("img")&&void 0!==n.images){var f=E(this.src),u=n.images[f];if(void 0!==u){var g=e.width/e.height,m=this.width/this.height,y=e.width,v=e.height;m<=g?(v=Math.min(e.height,this.height),y=this.width*v/this.height):m>g&&(y=Math.min(e.width,this.width),v=this.height*y/this.width),y*=19.049976/25.4,(v*=19.049976/25.4)<e.height&&(r=(e.height-v)/2);try{n.doc.addImage(u.src,e.textPos.x,e.y+r,y,v)}catch(t){}e.textPos.x+=y}}void 0!==o&&o.length>0&&p(e,o,n)})}function g(e,o,n){if("function"==typeof n.onAutotableText)n.onAutotableText(n.doc,e,o);else{var r=e.textPos.x,i=e.textPos.y,s={halign:e.styles.halign,valign:e.styles.valign};if(o.length){for(var a=o[0];a.previousSibling;)a=a.previousSibling;for(var l=!1,c=!1;a;){var h=a.innerText||a.textContent||"";h=(h.length&&" "==h[0]?" ":"")+t.trim(h)+(h.length>1&&" "==h[h.length-1]?" ":""),t(a).is("br")&&(r=e.textPos.x,i+=n.doc.internal.getFontSize()),t(a).is("b")?l=!0:t(a).is("i")&&(c=!0),(l||c)&&n.doc.setFontType(l&&c?"bolditalic":l?"bold":"italic");var d=n.doc.getStringUnitWidth(h)*n.doc.internal.getFontSize();if(d){if("linebreak"===e.styles.overflow&&r>e.textPos.x&&r+d>e.textPos.x+e.width){if(".,!%*;:=-".indexOf(h.charAt(0))>=0){var f=h.charAt(0);r+(d=n.doc.getStringUnitWidth(f)*n.doc.internal.getFontSize())<=e.textPos.x+e.width&&(n.doc.autoTableText(f,r,i,s),h=h.substring(1,h.length)),d=n.doc.getStringUnitWidth(h)*n.doc.internal.getFontSize()}r=e.textPos.x,i+=n.doc.internal.getFontSize()}for(;h.length&&r+d>e.textPos.x+e.width;)h=h.substring(0,h.length-1),d=n.doc.getStringUnitWidth(h)*n.doc.internal.getFontSize();n.doc.autoTableText(h,r,i,s),r+=d}(l||c)&&(t(a).is("b")?l=!1:t(a).is("i")&&(c=!1),n.doc.setFontType(l||c?l?"bold":"italic":"normal")),a=a.nextSibling}e.textPos.x=r,e.textPos.y=i}else n.doc.autoTableText(e.text,e.textPos.x,e.textPos.y,s)}}function m(t){return t.replace(/([.*+?^=!:${}()|\[\]\/\\])/g,"\\$1")}function y(t,e,o){return t.replace(new RegExp(m(e),"g"),o)}function v(t){return t=t||"0",t=y(t,P.numbers.html.thousandsSeparator,""),("number"==typeof(t=y(t,P.numbers.html.decimalMark,"."))||!1!==jQuery.isNumeric(t))&&t}function w(t){return t.indexOf("%")>-1?!1!==(t=v(t.replace(/%/g,"")))&&(t/=100):t=!1,t}function b(e,o,n){var r="";if(null!==e){var i,s=t(e);if(s[0].hasAttribute("data-tableexport-value"))i=s.data("tableexport-value"),i=i?i+"":"";else if(i=s.html(),"function"==typeof P.onCellHtmlData)i=P.onCellHtmlData(s,o,n,i);else if(""!=i){var a=t.parseHTML(i),l=0,c=0;i="",t.each(a,function(){t(this).is("input")?i+=s.find("input").eq(l++).val():t(this).is("select")?i+=s.find("select option:selected").eq(c++).text():void 0===t(this).html()?i+=t(this).text():(void 0===jQuery().bootstrapTable||!0!==t(this).hasClass("filterControl")&&0===t(e).parents(".detail-view").length)&&(i+=t(this).html())})}if(!0===P.htmlContent)r=t.trim(i);else if(i&&""!=i)if(""!=t(e).data("tableexport-cellformat")){var h=i.replace(/\n/g,"\u2028").replace(/<br\s*[\/]?>/gi,"⁠"),d=t("<div/>").html(h).contents(),f=!1;if(h="",t.each(d.text().split("\u2028"),function(e,o){e>0&&(h+=" "),h+=t.trim(o)}),t.each(h.split("⁠"),function(e,o){e>0&&(r+="\n"),r+=t.trim(o).replace(/\u00AD/g,"")}),"json"==P.type||"excel"===P.type&&"xmlss"===P.excelFileFormat||!1===P.numbers.output)!1!==(f=v(r))&&(r=Number(f));else if((P.numbers.html.decimalMark!=P.numbers.output.decimalMark||P.numbers.html.thousandsSeparator!=P.numbers.output.thousandsSeparator)&&!1!==(f=v(r))){var u=(""+f.substr(f<0?1:0)).split(".");1==u.length&&(u[1]="");var p=u[0].length>3?u[0].length%3:0;r=(f<0?"-":"")+(P.numbers.output.thousandsSeparator?(p?u[0].substr(0,p)+P.numbers.output.thousandsSeparator:"")+u[0].substr(p).replace(/(\d{3})(?=\d)/g,"$1"+P.numbers.output.thousandsSeparator):u[0])+(u[1].length?P.numbers.output.decimalMark+u[1]:"")}}else r=i;!0===P.escape&&(r=escape(r)),"function"==typeof P.onCellData&&(r=P.onCellData(s,o,n,r))}return r}function x(t,e,o){return e+"-"+o.toLowerCase()}function S(t,e){var o=/^rgb\((\d{1,3}),\s*(\d{1,3}),\s*(\d{1,3})\)$/.exec(t),n=e;return o&&(n=[parseInt(o[1]),parseInt(o[2]),parseInt(o[3])]),n}function C(e){var o=N(e,"text-align"),n=N(e,"font-weight"),r=N(e,"font-style"),i="";"start"==o&&(o="rtl"==N(e,"direction")?"right":"left"),n>=700&&(i="bold"),"italic"==r&&(i+=r),""===i&&(i="normal");var s={style:{align:o,bcolor:S(N(e,"background-color"),[255,255,255]),color:S(N(e,"color"),[0,0,0]),fstyle:i},colspan:parseInt(t(e).attr("colspan"))||0,rowspan:parseInt(t(e).attr("rowspan"))||0};if(null!==e){var a=e.getBoundingClientRect();s.rect={width:a.width,height:a.height}}return s}function N(t,e){try{return window.getComputedStyle?(e=e.replace(/([a-z])([A-Z])/,x),window.getComputedStyle(t,null).getPropertyValue(e)):t.currentStyle?t.currentStyle[e]:t.style[e]}catch(t){}return""}function k(t,e,o){var n=document.createElement("div");n.style.overflow="hidden",n.style.visibility="hidden",t.appendChild(n),n.style.width=100+o;var r=100/n.offsetWidth;return t.removeChild(n),e*r}function A(t,e,o){var n=N(t,e).match(/\d+/);return null!==n?(n=n[0],k(t.parentElement,n,o)):0}function T(){if(!(this instanceof T))return new T;this.SheetNames=[],this.Sheets={}}function R(t){for(var e=new ArrayBuffer(t.length),o=new Uint8Array(e),n=0;n!=t.length;++n)o[n]=255&t.charCodeAt(n);return e}function I(t,e){return e&&(t+=1462),(Date.parse(t)-new Date(Date.UTC(1899,11,30)))/864e5}function E(t){var e,o,n=0;if(0===t.length)return n;for(e=0,o=t.length;e<o;e++)n=(n<<5)-n+t.charCodeAt(e),n|=0;return n}function j(t,e,o){var n=window.navigator.userAgent;if(!1!==t&&window.navigator.msSaveOrOpenBlob)window.navigator.msSaveOrOpenBlob(new Blob([o]),t);else if(!1!==t&&(n.indexOf("MSIE ")>0||n.match(/Trident.*rv\:11\./))){var r=document.createElement("iframe");r&&(document.body.appendChild(r),r.setAttribute("style","display:none"),r.contentDocument.open("txt/html","replace"),r.contentDocument.write(o),r.contentDocument.close(),r.focus(),r.contentDocument.execCommand("SaveAs",!0,t),document.body.removeChild(r))}else{var i=document.createElement("a");if(i){var s=null;i.style.display="none",!1!==t?i.download=t:i.target="_blank","object"==typeof o?(window.URL=window.URL||window.webkitURL,s=window.URL.createObjectURL(o),i.href=s):e.toLowerCase().indexOf("base64,")>=0?i.href=e+L(o):i.href=e+encodeURIComponent(o),document.body.appendChild(i),document.createEvent?(null===F&&(F=document.createEvent("MouseEvents")),F.initEvent("click",!0,!1),i.dispatchEvent(F)):document.createEventObject?i.fireEvent("onclick"):"function"==typeof i.onclick&&i.onclick(),setTimeout(function(){s&&window.URL.revokeObjectURL(s),document.body.removeChild(i)},100)}}}function D(t){if("string"==typeof t){t=t.replace(/\x0d\x0a/g,"\n");for(var e="",o=0;o<t.length;o++){var n=t.charCodeAt(o);n<128?e+=String.fromCharCode(n):n>127&&n<2048?(e+=String.fromCharCode(n>>6|192),e+=String.fromCharCode(63&n|128)):(e+=String.fromCharCode(n>>12|224),e+=String.fromCharCode(n>>6&63|128),e+=String.fromCharCode(63&n|128))}return e}return t}function L(t){var e,o,n,r,i,s,a,l="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",c="",h=0;for(t=D(t);h<t.length;)r=(e=t.charCodeAt(h++))>>2,i=(3&e)<<4|(o=t.charCodeAt(h++))>>4,s=(15&o)<<2|(n=t.charCodeAt(h++))>>6,a=63&n,isNaN(o)?s=a=64:isNaN(n)&&(a=64),c=c+l.charAt(r)+l.charAt(i)+l.charAt(s)+l.charAt(a);return c}var M,P={consoleLog:!1,csvEnclosure:'"',csvSeparator:",",csvUseBOM:!0,displayTableName:!1,escape:!1,excelFileFormat:"xlshtml",excelRTL:!1,excelstyles:[],exportHiddenCells:!1,fileName:"tableExport",htmlContent:!1,ignoreColumn:[],ignoreRow:[],jsonScope:"all",jspdf:{orientation:"p",unit:"pt",format:"a4",margins:{left:20,right:10,top:10,bottom:10},onDocCreated:null,autotable:{styles:{cellPadding:2,rowHeight:12,fontSize:8,fillColor:255,textColor:50,fontStyle:"normal",overflow:"ellipsize",halign:"left",valign:"middle"},headerStyles:{fillColor:[52,73,94],textColor:255,fontStyle:"bold",halign:"center"},alternateRowStyles:{fillColor:245},tableExport:{doc:null,onAfterAutotable:null,onBeforeAutotable:null,onAutotableText:null,onTable:null,outputImages:!0}}},numbers:{html:{decimalMark:".",thousandsSeparator:","},output:{decimalMark:".",thousandsSeparator:","}},onCellData:null,onCellHtmlData:null,onIgnoreRow:null,onMsoNumberFormat:null,outputMode:"file",pdfmake:{enabled:!1,docDefinition:{pageOrientation:"portrait",defaultStyle:{font:"Roboto"}},fonts:{}},tbodySelector:"tr",tfootSelector:"tr",theadSelector:"tr",tableName:"Table",type:"csv",worksheetName:""},O=1.15,W=this,F=null,U=[],B=[],H=0,z="",K=[],X=[],q=[],Q=!1;if(t.extend(!0,P,e),K=s(W),"csv"==P.type||"tsv"==P.type||"txt"==P.type){var V="",Y=0;X=[],H=0;var J=function(e,n,r){return e.each(function(){z="",h(this,n,H,r+e.length,function(t,e,n){z+=o(t,e,n)+("tsv"==P.type?"\t":P.csvSeparator)}),(z=t.trim(z).substring(0,z.length-1)).length>0&&(V.length>0&&(V+="\n"),V+=z),H++}),e.length};if(Y+=J(t(W).find("thead").first().find(P.theadSelector),"th,td",Y),r(t(W),"tbody").each(function(){Y+=J(i(t(this),P.tbodySelector),"td,th",Y)}),P.tfootSelector.length&&J(t(W).find("tfoot").first().find(P.tfootSelector),"td,th",Y),V+="\n",!0===P.consoleLog&&console.log(V),"string"===P.outputMode)return V;if("base64"===P.outputMode)return L(V);if("window"===P.outputMode)return void j(!1,"data:text/"+("csv"==P.type?"csv":"plain")+";charset=utf-8,",V);try{M=new Blob([V],{type:"text/"+("csv"==P.type?"csv":"plain")+";charset=utf-8"}),saveAs(M,P.fileName+"."+P.type,"csv"!=P.type||!1===P.csvUseBOM)}catch(t){j(P.fileName+"."+P.type,"data:text/"+("csv"==P.type?"csv":"plain")+";charset=utf-8,"+("csv"==P.type&&P.csvUseBOM?"\ufeff":""),V)}}else if("sql"==P.type){H=0,X=[];var $="INSERT INTO `"+P.tableName+"` (";if((U=t(W).find("thead").first().find(P.theadSelector)).each(function(){h(this,"th,td",H,U.length,function(t,e,o){$+="'"+b(t,e,o)+"',"}),H++,$=t.trim($),$=t.trim($).substring(0,$.length-1)}),$+=") VALUES ",B=n(t(W)),t(B).each(function(){z="",h(this,"td,th",H,U.length+B.length,function(t,e,o){z+="'"+b(t,e,o)+"',"}),z.length>3&&($+="("+z,$=t.trim($).substring(0,$.length-1),$+="),"),H++}),$=t.trim($).substring(0,$.length-1),$+=";",!0===P.consoleLog&&console.log($),"string"===P.outputMode)return $;if("base64"===P.outputMode)return L($);try{M=new Blob([$],{type:"text/plain;charset=utf-8"}),saveAs(M,P.fileName+".sql")}catch(t){j(P.fileName+".sql","data:application/sql;charset=utf-8,",$)}}else if("json"==P.type){var _=[];X=[],(U=t(W).find("thead").first().find(P.theadSelector)).each(function(){var t=[];h(this,"th,td",H,U.length,function(e,o,n){t.push(b(e,o,n))}),_.push(t)});var G=[];B=n(t(W)),t(B).each(function(){var e={},o=0;h(this,"td,th",H,U.length+B.length,function(t,n,r){_.length?e[_[_.length-1][o]]=b(t,n,r):e[o]=b(t,n,r),o++}),!1===t.isEmptyObject(e)&&G.push(e),H++});var Z="";if(Z="head"==P.jsonScope?JSON.stringify(_):"data"==P.jsonScope?JSON.stringify(G):JSON.stringify({header:_,data:G}),!0===P.consoleLog&&console.log(Z),"string"===P.outputMode)return Z;if("base64"===P.outputMode)return L(Z);try{M=new Blob([Z],{type:"application/json;charset=utf-8"}),saveAs(M,P.fileName+".json")}catch(t){j(P.fileName+".json","data:application/json;charset=utf-8;base64,",Z)}}else if("xml"===P.type){H=0,X=[];var tt='<?xml version="1.0" encoding="utf-8"?>';tt+="<tabledata><fields>",(U=t(W).find("thead").first().find(P.theadSelector)).each(function(){h(this,"th,td",H,U.length,function(t,e,o){tt+="<field>"+b(t,e,o)+"</field>"}),H++}),tt+="</fields><data>";var et=1;if(B=n(t(W)),t(B).each(function(){var t=1;z="",h(this,"td,th",H,U.length+B.length,function(e,o,n){z+="<column-"+t+">"+b(e,o,n)+"</column-"+t+">",t++}),z.length>0&&"<column-1></column-1>"!=z&&(tt+='<row id="'+et+'">'+z+"</row>",et++),H++}),tt+="</data></tabledata>",!0===P.consoleLog&&console.log(tt),"string"===P.outputMode)return tt;if("base64"===P.outputMode)return L(tt);try{M=new Blob([tt],{type:"application/xml;charset=utf-8"}),saveAs(M,P.fileName+".xml")}catch(t){j(P.fileName+".xml","data:application/xml;charset=utf-8;base64,",tt)}}else if("excel"===P.type&&"xmlss"===P.excelFileFormat){var ot=[],nt=[];t(W).filter(function(){return a(t(this))}).each(function(){function e(e,o,n){var r=[];return t(e).each(function(){var o=0,i=0;z="",h(this,"td,th",H,n+e.length,function(e,n,s){if(null!==e){var a="",l=b(e,n,s),c="String";if(!1!==jQuery.isNumeric(l))c="Number";else{var h=w(l);!1!==h&&(l=h,c="Number",a+=' ss:StyleID="pct1"')}"Number"!==c&&(l=l.replace(/\n/g,"<br>"));var d=parseInt(e.getAttribute("colspan")),f=parseInt(e.getAttribute("rowspan"));r.forEach(function(t){if(H>=t.s.r&&H<=t.e.r&&i>=t.s.c&&i<=t.e.c)for(var e=0;e<=t.e.c-t.s.c;++e)i++,o++}),(f||d)&&(f=f||1,d=d||1,r.push({s:{r:H,c:i},e:{r:H+f-1,c:i+d-1}})),d>1&&(a+=' ss:MergeAcross="'+(d-1)+'"',i+=d-1),f>1&&(a+=' ss:MergeDown="'+(f-1)+'" ss:StyleID="rsp1"'),o>0&&(a+=' ss:Index="'+(i+1)+'"',o=0),z+="<Cell"+a+'><Data ss:Type="'+c+'">'+t("<div />").text(l).html()+"</Data></Cell>\r",i++}}),z.length>0&&(gt+='<Row ss:AutoFitHeight="0">\r'+z+"</Row>\r"),H++}),e.length}var o=t(this),r="";"string"==typeof P.worksheetName&&P.worksheetName.length?r=P.worksheetName+" "+(nt.length+1):void 0!==P.worksheetName[nt.length]&&(r=P.worksheetName[nt.length]),r.length||(r=o.find("caption").text()||""),r.length||(r="Table "+(nt.length+1)),r=r.replace(/[\\\/[\]*:?'"]/g,"").substring(0,31).trim(),nt.push(t("<div />").text(r).html()),!1===P.exportHiddenCells&&(q=o.find("tr, th, td").filter(":hidden"),Q=q.length>0),H=0,K=s(this),gt="<Table>\r";var i=0;i+=e(o.find("thead").first().find(P.theadSelector),"th,td",i),e(n(o),"td,th",i),gt+="</Table>\r",ot.push(gt),!0===P.consoleLog&&console.log(gt)});for(var rt,it,st={},at={},lt=0,ct=nt.length;lt<ct;lt++)it=st[rt=nt[lt]],2==(it=st[rt]=null==it?1:it+1)&&(nt[at[rt]]=nt[at[rt]].substring(0,29)+"-1"),st[rt]>1?nt[lt]=nt[lt].substring(0,29)+"-"+st[rt]:at[rt]=lt;for(var ht='<?xml version="1.0" encoding="UTF-8"?>\r<?mso-application progid="Excel.Sheet"?>\r<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"\r xmlns:o="urn:schemas-microsoft-com:office:office"\r xmlns:x="urn:schemas-microsoft-com:office:excel"\r xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"\r xmlns:html="http://www.w3.org/TR/REC-html40">\r<DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">\r  <Created>'+(new Date).toISOString()+'</Created>\r</DocumentProperties>\r<OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">\r  <AllowPNG/>\r</OfficeDocumentSettings>\r<ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">\r  <WindowHeight>9000</WindowHeight>\r  <WindowWidth>13860</WindowWidth>\r  <WindowTopX>0</WindowTopX>\r  <WindowTopY>0</WindowTopY>\r  <ProtectStructure>False</ProtectStructure>\r  <ProtectWindows>False</ProtectWindows>\r</ExcelWorkbook>\r<Styles>\r  <Style ss:ID="Default" ss:Name="Normal">\r    <Alignment ss:Vertical="Bottom"/>\r    <Borders/>\r    <Font/>\r    <Interior/>\r    <NumberFormat/>\r    <Protection/>\r  </Style>\r  <Style ss:ID="rsp1">\r    <Alignment ss:Vertical="Center"/>\r  </Style>\r  <Style ss:ID="pct1">\r    <NumberFormat ss:Format="Percent"/>\r  </Style>\r</Styles>\r',dt=0;dt<ot.length;dt++)ht+='<Worksheet ss:Name="'+nt[dt]+'" ss:RightToLeft="'+(P.excelRTL?"1":"0")+'">\r'+ot[dt],P.excelRTL?ht+='<WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">\r<DisplayRightToLeft/>\r</WorksheetOptions>\r':ht+='<WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel"/>\r',ht+="</Worksheet>\r";if(ht+="</Workbook>\r",!0===P.consoleLog&&console.log(ht),"string"===P.outputMode)return ht;if("base64"===P.outputMode)return L(ht);try{M=new Blob([ht],{type:"application/xml;charset=utf-8"}),saveAs(M,P.fileName+".xml")}catch(t){j(P.fileName+".xml","data:application/xml;charset=utf-8;base64,",ht)}}else if("excel"==P.type||"xls"==P.type||"word"==P.type||"doc"==P.type){var ft="excel"==P.type||"xls"==P.type?"excel":"word",ut="excel"==ft?"xls":"doc",pt='xmlns:x="urn:schemas-microsoft-com:office:'+ft+'"',gt="",mt="";t(W).filter(function(){return a(t(this))}).each(function(){var e=t(this);""===mt&&(mt=P.worksheetName||e.find("caption").text()||"Table",mt=mt.replace(/[\\\/[\]*:?'"]/g,"").substring(0,31).trim()),!1===P.exportHiddenCells&&(q=e.find("tr, th, td").filter(":hidden"),Q=q.length>0),H=0,X=[],K=s(this),gt+="<table><thead>",(U=e.find("thead").first().find(P.theadSelector)).each(function(){z="",h(this,"th,td",H,U.length,function(e,o,n){if(null!==e){var r="";z+="<th";for(var i in P.excelstyles)if(P.excelstyles.hasOwnProperty(i)){var s=t(e).css(P.excelstyles[i]);""!==s&&"0px none rgb(0, 0, 0)"!=s&&"rgba(0, 0, 0, 0)"!=s&&(r+=""===r?'style="':";",r+=P.excelstyles[i]+":"+s)}""!==r&&(z+=" "+r+'"'),t(e).is("[colspan]")&&(z+=' colspan="'+t(e).attr("colspan")+'"'),t(e).is("[rowspan]")&&(z+=' rowspan="'+t(e).attr("rowspan")+'"'),z+=">"+b(e,o,n)+"</th>"}}),z.length>0&&(gt+="<tr>"+z+"</tr>"),H++}),gt+="</thead><tbody>",B=n(e),t(B).each(function(){var e=t(this);z="",h(this,"td,th",H,U.length+B.length,function(o,n,r){if(null!==o){var i=b(o,n,r),s="",a=t(o).data("tableexport-msonumberformat");void 0===a&&"function"==typeof P.onMsoNumberFormat&&(a=P.onMsoNumberFormat(o,n,r)),void 0!==a&&""!==a&&(s="style=\"mso-number-format:'"+a+"'");for(var l in P.excelstyles)P.excelstyles.hasOwnProperty(l)&&(""===(a=t(o).css(P.excelstyles[l]))&&(a=e.css(P.excelstyles[l])),""!==a&&"0px none rgb(0, 0, 0)"!=a&&"rgba(0, 0, 0, 0)"!=a&&(s+=""===s?'style="':";",s+=P.excelstyles[l]+":"+a));z+="<td",""!==s&&(z+=" "+s+'"'),t(o).is("[colspan]")&&(z+=' colspan="'+t(o).attr("colspan")+'"'),t(o).is("[rowspan]")&&(z+=' rowspan="'+t(o).attr("rowspan")+'"'),"string"==typeof i&&""!=i&&(i=i.replace(/\n/g,"<br>")),z+=">"+i+"</td>"}}),z.length>0&&(gt+="<tr>"+z+"</tr>"),H++}),P.displayTableName&&(gt+="<tr><td></td></tr><tr><td></td></tr><tr><td>"+b(t("<p>"+P.tableName+"</p>"))+"</td></tr>"),gt+="</tbody></table>",!0===P.consoleLog&&console.log(gt)});var yt='<html xmlns:o="urn:schemas-microsoft-com:office:office" '+pt+' xmlns="http://www.w3.org/TR/REC-html40">';if(yt+='<meta http-equiv="content-type" content="application/vnd.ms-'+ft+'; charset=UTF-8">',yt+="<head>","excel"===ft&&(yt+="\x3c!--[if gte mso 9]>",yt+="<xml>",yt+="<x:ExcelWorkbook>",yt+="<x:ExcelWorksheets>",yt+="<x:ExcelWorksheet>",yt+="<x:Name>",yt+=mt,yt+="</x:Name>",yt+="<x:WorksheetOptions>",yt+="<x:DisplayGridlines/>",P.excelRTL&&(yt+="<x:DisplayRightToLeft/>"),yt+="</x:WorksheetOptions>",yt+="</x:ExcelWorksheet>",yt+="</x:ExcelWorksheets>",yt+="</x:ExcelWorkbook>",yt+="</xml>",yt+="<![endif]--\x3e"),yt+="<style>br {mso-data-placement:same-cell;}</style>",yt+="</head>",yt+="<body>",yt+=gt,yt+="</body>",yt+="</html>",!0===P.consoleLog&&console.log(yt),"string"===P.outputMode)return yt;if("base64"===P.outputMode)return L(yt);try{M=new Blob([yt],{type:"application/vnd.ms-"+P.type}),saveAs(M,P.fileName+"."+ut)}catch(t){j(P.fileName+"."+ut,"data:application/vnd.ms-"+ft+";base64,",yt)}}else if("xlsx"==P.type){var vt=[],wt=[];H=0,(B=t(W).find("thead").first().find(P.theadSelector)).push.apply(B,n(t(W))),t(B).each(function(){var t=[];h(this,"th,td",H,B.length,function(e,o,n){if(void 0!==e&&null!==e){var r=b(e,o,n),i=parseInt(e.getAttribute("colspan")),s=parseInt(e.getAttribute("rowspan"));if(wt.forEach(function(e){if(H>=e.s.r&&H<=e.e.r&&t.length>=e.s.c&&t.length<=e.e.c)for(var o=0;o<=e.e.c-e.s.c;++o)t.push(null)}),(s||i)&&(s=s||1,i=i||1,wt.push({s:{r:H,c:t.length},e:{r:H+s-1,c:t.length+i-1}})),"function"!=typeof P.onCellData&&""!==r&&r==+r&&(r=+r),t.push(""!==r?r:null),i)for(var a=0;a<i-1;++a)t.push(null)}}),vt.push(t),H++});var bt=new T,xt=function(t){for(var e={},o={s:{c:1e7,r:1e7},e:{c:0,r:0}},n=0;n!=t.length;++n)for(var r=0;r!=t[n].length;++r){o.s.r>n&&(o.s.r=n),o.s.c>r&&(o.s.c=r),o.e.r<n&&(o.e.r=n),o.e.c<r&&(o.e.c=r);var i={v:t[n][r]};if(null!==i.v){var s=XLSX.utils.encode_cell({c:r,r:n});"number"==typeof i.v?i.t="n":"boolean"==typeof i.v?i.t="b":i.v instanceof Date?(i.t="n",i.z=XLSX.SSF._table[14],i.v=I(i.v)):i.t="s",e[s]=i}}return o.s.c<1e7&&(e["!ref"]=XLSX.utils.encode_range(o)),e}(vt);xt["!merges"]=wt,bt.SheetNames.push(P.worksheetName),bt.Sheets[P.worksheetName]=xt;var St=XLSX.write(bt,{bookType:P.type,bookSST:!1,type:"binary"});try{M=new Blob([R(St)],{type:"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8"}),saveAs(M,P.fileName+"."+P.type)}catch(t){j(P.fileName+"."+P.type,"data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8,",R(St))}}else if("png"==P.type)html2canvas(t(W)[0]).then(function(t){for(var e=t.toDataURL(),o=atob(e.substring(22)),n=new ArrayBuffer(o.length),r=new Uint8Array(n),i=0;i<o.length;i++)r[i]=o.charCodeAt(i);if(!0===P.consoleLog&&console.log(o),"string"===P.outputMode)return o;if("base64"===P.outputMode)return L(e);if("window"!==P.outputMode)try{M=new Blob([n],{type:"image/png"}),saveAs(M,P.fileName+".png")}catch(t){j(P.fileName+".png","data:image/png,",M)}else window.open(e)});else if("pdf"==P.type)if(!0===P.pdfmake.enabled){var Ct=[],Nt=[];H=0,X=[];for(var kt=function(e,o,n){var r=0;return t(e).each(function(){var t=[];h(this,o,H,n,function(e,o,n){if(void 0!==e&&null!==e){var r=parseInt(e.getAttribute("colspan")),i=parseInt(e.getAttribute("rowspan")),s=b(e,o,n)||" ";r>1||i>1?(r=r||1,i=i||1,t.push({colSpan:r,rowSpan:i,text:s})):t.push(s)}else t.push(" ")}),t.length&&Nt.push(t),r<t.length&&(r=t.length),H++}),r},At=kt(U=t(this).find("thead").first().find(P.theadSelector),"th,td",U.length),Tt=Ct.length;Tt<At;Tt++)Ct.push("*");kt(B=n(t(this)),"th,td",U.length+B.length);var Rt={content:[{table:{headerRows:U.length,widths:Ct,body:Nt}}]};t.extend(!0,Rt,P.pdfmake.docDefinition),pdfMake.fonts={Roboto:{normal:"Roboto-Regular.ttf",bold:"Roboto-Medium.ttf",italics:"Roboto-Italic.ttf",bolditalics:"Roboto-MediumItalic.ttf"}},t.extend(!0,pdfMake.fonts,P.pdfmake.fonts),pdfMake.createPdf(Rt).getBuffer(function(t){try{var e=new Blob([t],{type:"application/pdf"});saveAs(e,P.fileName+".pdf")}catch(e){j(P.fileName+".pdf","data:application/pdf;base64,",t)}})}else if(!1===P.jspdf.autotable){var It={dim:{w:A(t(W).first().get(0),"width","mm"),h:A(t(W).first().get(0),"height","mm")},pagesplit:!1},Et=new jsPDF(P.jspdf.orientation,P.jspdf.unit,P.jspdf.format);Et.addHTML(t(W).first(),P.jspdf.margins.left,P.jspdf.margins.top,It,function(){d(Et,!1)})}else{var jt=P.jspdf.autotable.tableExport;if("string"==typeof P.jspdf.format&&"bestfit"===P.jspdf.format.toLowerCase()){var Dt={a0:[2383.94,3370.39],a1:[1683.78,2383.94],a2:[1190.55,1683.78],a3:[841.89,1190.55],a4:[595.28,841.89]},Lt="",Mt="",Pt=0;t(W).each(function(){if(a(t(this))){var e=A(t(this).get(0),"width","pt");if(e>Pt){e>Dt.a0[0]&&(Lt="a0",Mt="l");for(var o in Dt)Dt.hasOwnProperty(o)&&Dt[o][1]>e&&(Lt=o,Mt="l",Dt[o][0]>e&&(Mt="p"));Pt=e}}}),P.jspdf.format=""===Lt?"a4":Lt,P.jspdf.orientation=""===Mt?"w":Mt}null==jt.doc&&(jt.doc=new jsPDF(P.jspdf.orientation,P.jspdf.unit,P.jspdf.format),"function"==typeof P.jspdf.onDocCreated&&P.jspdf.onDocCreated(jt.doc)),!0===jt.outputImages&&(jt.images={}),void 0!==jt.images&&(t(W).filter(function(){return a(t(this))}).each(function(){var e=0;X=[],!1===P.exportHiddenCells&&(q=t(this).find("tr, th, td").filter(":hidden"),Q=q.length>0),U=t(this).find("thead").find(P.theadSelector),B=n(t(this)),t(B).each(function(){h(this,"td,th",U.length+e,U.length+B.length,function(e){if(void 0!==e&&null!==e){var o=t(e).children();void 0!==o&&o.length>0&&u(e,o,jt)}}),e++})}),U=[],B=[]),function(t,e){function o(){e(r)}var n,r=0,i=0;if(void 0!==t.images)for(n in t.images)t.images.hasOwnProperty(n)&&function(t){if(t.url){var e=new Image;r=++i,e.crossOrigin="Anonymous",e.onerror=e.onload=function(){if(e.complete&&(0===e.src.indexOf("data:image/")&&(e.width=t.width||e.width||0,e.height=t.height||e.height||0),e.width+e.height)){var n=document.createElement("canvas"),r=n.getContext("2d");n.width=e.width,n.height=e.height,r.drawImage(e,0,0),t.src=n.toDataURL("image/jpeg")}--i||o()},e.src=t.url}}(t.images[n]);i||o()}(jt,function(){t(W).filter(function(){return a(t(this))}).each(function(){var e;if(H=0,X=[],!1===P.exportHiddenCells&&(q=t(this).find("tr, th, td").filter(":hidden"),Q=q.length>0),K=s(this),jt.columns=[],jt.rows=[],jt.rowoptions={},"function"==typeof jt.onTable&&!1===jt.onTable(t(this),P))return!0;P.jspdf.autotable.tableExport=null;var o=t.extend(!0,{},P.jspdf.autotable);if(P.jspdf.autotable.tableExport=jt,o.margin={},t.extend(!0,o.margin,P.jspdf.margins),o.tableExport=jt,"function"!=typeof o.beforePageContent&&(o.beforePageContent=function(t){1==t.pageCount&&t.table.rows.concat(t.table.headerRow).forEach(function(e){e.height>0&&(e.height+=(2-O)/2*e.styles.fontSize,t.table.height+=(2-O)/2*e.styles.fontSize)})}),"function"!=typeof o.createdHeaderCell&&(o.createdHeaderCell=function(e,n){if(e.styles=t.extend({},n.row.styles),void 0!==jt.columns[n.column.dataKey]){var r=jt.columns[n.column.dataKey];if(void 0!==r.rect){var i;e.contentWidth=r.rect.width,void 0!==jt.heightRatio&&0!==jt.heightRatio||(i=n.row.raw[n.column.dataKey].rowspan?n.row.raw[n.column.dataKey].rect.height/n.row.raw[n.column.dataKey].rowspan:n.row.raw[n.column.dataKey].rect.height,jt.heightRatio=e.styles.rowHeight/i),(i=n.row.raw[n.column.dataKey].rect.height*jt.heightRatio)>e.styles.rowHeight&&(e.styles.rowHeight=i)}void 0!==r.style&&!0!==r.style.hidden&&(e.styles.halign=r.style.align,"inherit"===o.styles.fillColor&&(e.styles.fillColor=r.style.bcolor),"inherit"===o.styles.textColor&&(e.styles.textColor=r.style.color),"inherit"===o.styles.fontStyle&&(e.styles.fontStyle=r.style.fstyle))}}),"function"!=typeof o.createdCell&&(o.createdCell=function(t,e){var n=jt.rowoptions[e.row.index+":"+e.column.dataKey];void 0!==n&&void 0!==n.style&&!0!==n.style.hidden&&(t.styles.halign=n.style.align,"inherit"===o.styles.fillColor&&(t.styles.fillColor=n.style.bcolor),"inherit"===o.styles.textColor&&(t.styles.textColor=n.style.color),"inherit"===o.styles.fontStyle&&(t.styles.fontStyle=n.style.fstyle))}),"function"!=typeof o.drawHeaderCell&&(o.drawHeaderCell=function(t,e){var o=jt.columns[e.column.dataKey];return(!0!==o.style.hasOwnProperty("hidden")||!0!==o.style.hidden)&&o.rowIndex>=0&&f(t,e,o)}),"function"!=typeof o.drawCell&&(o.drawCell=function(t,e){var o=jt.rowoptions[e.row.index+":"+e.column.dataKey];if(f(t,e,o))if(jt.doc.rect(t.x,t.y,t.width,t.height,t.styles.fillStyle),void 0!==o&&void 0!==o.kids&&o.kids.length>0){var n=t.height/o.rect.height;(n>jt.dh||void 0===jt.dh)&&(jt.dh=n),jt.dw=t.width/o.rect.width;var r=t.textPos.y;p(t,o.kids,jt),t.textPos.y=r,g(t,o.kids,jt)}else g(t,{},jt);return!1}),jt.headerrows=[],(U=t(this).find("thead").find(P.theadSelector)).each(function(){e=0,jt.headerrows[H]=[],h(this,"th,td",H,U.length,function(t,o,n){var r=C(t);r.title=b(t,o,n),r.key=e++,r.rowIndex=H,jt.headerrows[H].push(r)}),H++}),H>0)for(var r=H-1;r>=0;)t.each(jt.headerrows[r],function(){var t=this;r>0&&null===this.rect&&(t=jt.headerrows[r-1][this.key]),null!==t&&t.rowIndex>=0&&(!0!==t.style.hasOwnProperty("hidden")||!0!==t.style.hidden)&&jt.columns.push(t)}),r=jt.columns.length>0?-1:r-1;var i=0;B=[],B=n(t(this)),t(B).each(function(){var o=[];e=0,h(this,"td,th",H,U.length+B.length,function(n,r,s){var a;void 0===jt.columns[e]&&(a={title:"",key:e,style:{hidden:!0}},jt.columns.push(a)),void 0!==n&&null!==n?((a=C(n)).kids=t(n).children(),jt.rowoptions[i+":"+e++]=a):((a=t.extend(!0,{},jt.rowoptions[i+":"+(e-1)])).colspan=-1,jt.rowoptions[i+":"+e++]=a),o.push(b(n,r,s))}),o.length&&(jt.rows.push(o),i++),H++}),"function"==typeof jt.onBeforeAutotable&&jt.onBeforeAutotable(t(this),jt.columns,jt.rows,o),jt.doc.autoTable(jt.columns,jt.rows,o),"function"==typeof jt.onAfterAutotable&&jt.onAfterAutotable(t(this),o),P.jspdf.autotable.startY=jt.doc.autoTableEndPosY()+o.margin.top}),d(jt.doc,void 0!==jt.images&&!1===jQuery.isEmptyObject(jt.images)),void 0!==jt.headerrows&&(jt.headerrows.length=0),void 0!==jt.columns&&(jt.columns.length=0),void 0!==jt.rows&&(jt.rows.length=0),delete jt.doc,jt.doc=null})}return this}})}(jQuery);