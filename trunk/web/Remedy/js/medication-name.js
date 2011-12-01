function ajaxmed(str){
	var xmlhttp;
	var txt;
	var xmlDoc;
	if(str.length == 0){
		document.getElementById("medhint").innerHTML = "";
	} 
	if(window.XMLHttpRequest){
		xmlhttp = new XMLHttpRequest();
	}else{
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState==4 && xmlhttp.status==200){
			xmlDoc=xmlhttp.responseXML;
		    txt="";
		    if(str.length == 0){
		    	document.getElementById("medhint").innerHTML = "";
		    	return;
		    }
		    var StrHead ;
		    strHead = str.charAt(0).toLowerCase();
		    switch (strHead){
		    	case "a":
		    		searchByCapital(strHead);
		    		break;
		    	case "b":
		    		searchByCapital(strHead);
		    		break;
		    	case "c":
		    		searchByCapital(strHead);
		    		break;
		    	case "d":
		    		searchByCapital(strHead);
		    		break;
		    	case "e":
		    		searchByCapital(strHead);
		    		break;
		    	case "f":
		    		searchByCapital(strHead);
		    		break;
		    	case "g":
		    		searchByCapital(strHead);
		    		break;
		    	case "h":
		    		searchByCapital(strHead);
		    		break;
		    	case "i":
		    		searchByCapital(strHead);
		    		break;
		    	case "j":
		    		searchByCapital(strHead);
		    		break;
		    	case "k":
		    		searchByCapital(strHead);
		    		break;
		    	case "l":
		    		searchByCapital(strHead);
		    		break;
		    	case "m":
		    		searchByCapital(strHead);
		    		break;
		    	case "n":
		    		searchByCapital(strHead);
		    		break;
		    	case "o":
		    		searchByCapital(strHead);
		    		break;
		    	case "p":
		    		searchByCapital(strHead);
		    		break;
		    	case "q":
		    		searchByCapital(strHead);
		    		break;
		    	case "r":
		    		searchByCapital(strHead);
		    		break;
		    	case "s":
		    		searchByCapital(strHead);
		    		break;
		    	case "t":
		    		searchByCapital(strHead);
		    		break;
		    	case "u":
		    		searchByCapital(strHead);
		    		break;
		    	case "v":
		    		searchByCapital(strHead);
		    		break;
		    	case "w":
		    		searchByCapital(strHead);
		    		break;
		    	case "x":
		    		searchByCapital(strHead);
		    		break;
		    	case "y":
		    		searchByCapital(strHead);
		    		break;
		    	case "z":
		    		searchByCapital(strHead);
		    		break;
		    	default:
		    		alert("Please Enter Correct Name");
		    }
		}
	}
	xmlhttp.open("GET","../Remedy/ajax/med_catalog.xml" ,true);
	xmlhttp.send();
	function searchByCapital(str){
		var x=xmlDoc.getElementsByTagName(str);
	    for (var i=0;i<x.length;i++)
	      {
	      txt=txt + "<li>" +  x[i].childNodes[0].nodeValue + "<br/>" + "</li>";
	      }
	    document.getElementById("medhint").innerHTML=txt;
	}
}