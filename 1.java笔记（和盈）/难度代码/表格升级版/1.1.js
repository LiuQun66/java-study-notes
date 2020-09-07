//添加一行变成完成，并显示出div.
function addTableTr(obj){
    // var div_1Input=document.getElementById("div_1input");
    var div_1Div=document.getElementById("div_1div");
    var secTab=document.getElementById("div_2table").children[0];
    var addTr=document.createElement("tr");
    var trTh=document.getElementsByTagName("th");
    var leftText=document.getElementsByClassName("leftTexts");
    var div_1Input=document.getElementById("div_1input");
    if(obj.value=="添加一行"){
        obj.value="完成";
        obj.setAttribute("disabled","disabled");
        div_1Div.style.display='block';
        
    }else{  
        obj.value=="添加一行";
        div_1Div.style.display='none';
        obj.removeAttribute("disabled");
        if(leftText[0].value!=null&&leftText[0].value!=undefined&&leftText[0].value!=""){
            // div_1Input.removeAttribute("disabled");
            for(var i=0;i<trTh.length;i++){
                var addTd=document.createElement("td");
                if(i<trTh.length-1){
                    addTd.innerHTML=leftText[i].value;
                    leftText[i].value="";
                }else{
                    addTd.innerHTML="<input type='button' value='删除' onclick='delSolo(this)'>";
                }
                addTr.appendChild(addTd);
            }
            secTab.appendChild(addTr);
        }
    }   
}
function addRight(){
    var div_1Input=document.getElementById("div_1input");
    var leftText=document.getElementsByClassName("leftTexts");
    if(leftText[0].value!=null&&leftText[0].value!=undefined&&leftText[0].value!=""){
        div_1Input.removeAttribute("disabled");
    }else{
        div_1Input.setAttribute("disabled","disabled");
    }  
}
//删除行
function delSolo(obj){
    var secTab=document.getElementById("div_2table").children[0];
    secTab.removeChild(obj.parentNode.parentNode);
}

