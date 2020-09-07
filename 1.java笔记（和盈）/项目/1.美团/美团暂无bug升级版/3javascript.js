var sum=0;
// 加入购物车的按钮
function add_btn(obj){
    var a1=document.getElementById("htext_1");
    a1.innerHTML++;
    var text_ale1="加入购物车成功!";
    alert(text_ale1);
    var a5=document.getElementById("box_4top_tab").children[0];
    var a6=document.createElement("tr");
    var a7=document.getElementsByClassName("c_th");
    for(var i=0;i<a7.length;i++){
        var a8=document.createElement("td");
        if(i==0){
            a8.innerHTML="<input type='checkbox' class='select' onclick='solo_select()'>"
        }else if(i==a7.length-1){
            a8.innerHTML="<input type='button' value='删除' onclick='del_solo(this)'>";
        }else if(i==1){
            a8.innerHTML=obj.parentNode.parentNode.parentNode.parentNode.children[0].children[i-1].innerHTML;
        }else{
            a8.innerHTML=obj.parentNode.parentNode.parentNode.parentNode.children[0].children[i-1].innerHTML;
        }
        a6.appendChild(a8);    
    }  
    a5.appendChild(a6);
}
//购物车
function add_shopping(){
    var a3=document.getElementById("box_3");
    var a4=document.getElementById("box_4");
    var a13=document.getElementById("box_4top_form");
    var a17=document.getElementById("box_4topBottom");
    a3.style.display="none";
    a4.style.display="block"; 
    a17.style.display="none";
    a13.setAttribute("disabled","disabled");
}
//单个删除
function del_solo(solo){
    var a5=document.getElementById("box_4top_tab").children[0];
    var del_alert="确定要删除吗？";
    var a18=document.getElementById("box_4top_span2");
    var a1=document.getElementById("htext_1");
    var a12=document.getElementsByClassName("select");
    var a10=window.confirm(del_alert);
    if(a10==true){  
        a5.removeChild(solo.parentNode.parentNode);
        a1.innerHTML--;
        alert("删除成功");
        solo_select();
    }else{
        alert("没有删除");
    }
}
//全选
function selectAll(all){
    var total_money=0;
    var a12=document.getElementsByClassName("select");
    var a13=document.getElementById("box_4top_form");
    var a18=document.getElementById("box_4top_span2");
    if(all.checked==true){
        for(var x=0;x<a12.length;x++){
            a12[x].checked=true;
        }
       
        a13.removeAttribute("disabled");
    }else{
        for(var x=0;x<a12.length;x++){
            a12[x].checked=false;
        }
        a13.setAttribute("disabled","disabled");
    }
    for(var y=0;y<a12.length;y++){
        if(a12[y].checked==true){
            total_money+=parseFloat(a12[y].parentNode.parentNode.children[2].children[0].innerHTML);
        }
    }
    a18.innerHTML=String(total_money);
}
//单个选择
function solo_select(){
    var count=0;
    var total_money=0;
    var a12=document.getElementsByClassName("select");
    var a14=document.getElementById("select_all");
    var a13=document.getElementById("box_4top_form");
    var a18=document.getElementById("box_4top_span2");
    for(var m=0;m<a12.length;m++){
        if(a12[m].checked==true){
            count++;   
        }
    }
    if(count==a12.length&&a12.length>0){
        a14.checked=true;
        a13.removeAttribute("disabled");
    }else{
        a14.checked=false;
        a13.setAttribute("disabled","disabled");
    }
    for(var y=0;y<a12.length;y++){
        if(a12[y].checked==true){
            total_money+=parseFloat(a12[y].parentNode.parentNode.children[2].children[0].innerHTML);
        }
    }
    a18.innerHTML=String(total_money);
}
//结算
function spend_money(){
    var a18=document.getElementById("box_4top_span2");
    var a16=document.getElementById("box_4topBottom");
    var a1=document.getElementById("htext_1");
    var a14=document.getElementById("select_all");
    var a12=document.getElementsByClassName("select");
    var a13=document.getElementById("box_4top_form");
    var a5=document.getElementById("box_4top_tab").children[0];
    var a20=parseFloat(a18.innerHTML);
    alert("总共付款"+a20+"元");
    for(var count2=0,i=a12.length-1;i>=0;i--){
        if(a12[i].checked==true){
            a5.removeChild(a12[i].parentNode.parentNode);
            count2++;
        }
    }
    a1.innerHTML=String(parseInt(a1.innerHTML)-count2);
    a18.innerHTML="0";
    a14.checked=false;
    a13.setAttribute("disabled","disabled");
}
//全部删除
function delAll(){
    var a13=document.getElementById("box_4top_form");
    var a18=document.getElementById("box_4top_span2");
    var a16=document.getElementById("box_4topBottom");
    var a14=document.getElementById("select_all");
    var a5=document.getElementById("box_4top_tab").children[0];
    var a1=document.getElementById("htext_1");
    var del_all="确定要删除购物车中所有订单吗？";
    var a15=window.confirm(del_all);
    if(a15==true){
       while(a5.children.length>1){
        a5.removeChild(a5.children[a5.children.length-1]);
       }
       a1.innerHTML="0";
       alert("删除成功");
       a13.setAttribute("disabled","disabled");
       a16.style.display="block";
       sum=0;
       a18.innerHTML=String(sum);
       a14.checked=false;
    }else{
        alert("没有删除");
    }
}
function continue_shopping(){
    var a3=document.getElementById("box_3");
    var a4=document.getElementById("box_4");
    a3.style.display="block";
    a4.style.display="none";
    solo_select();
    var a14=document.getElementById("select_all");
    a14.checked=false;
}
   