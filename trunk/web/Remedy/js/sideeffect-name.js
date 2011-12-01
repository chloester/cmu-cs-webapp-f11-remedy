function ajaxside(str){
	var xmlhttp;
	var txt;
	var xmlDoc;
	if(str.length == 0){
		document.getElementById("sidehint").innerHTML = "";
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
		    	document.getElementById("sidehint").innerHTML = "";
		    	return;
		    }
		    var StrHead ;
		    strHead = str.charAt(0).toLowerCase();
		    switch (strHead){
		    	case "a":
		    		searchByCapital2(strHead);
		    		break;
		    	case "b":
		    		searchByCapital2(strHead);
		    		break;
		    	case "c":
		    		searchByCapital2(strHead);
		    		break;
		    	case "d":
		    		searchByCapital2(strHead);
		    		break;
		    	case "e":
		    		searchByCapital2(strHead);
		    		break;
		    	case "f":
		    		searchByCapital2(strHead);
		    		break;
		    	case "g":
		    		searchByCapital2(strHead);
		    		break;
		    	case "h":
		    		searchByCapital2(strHead);
		    		break;
		    	case "i":
		    		searchByCapital2(strHead);
		    		break;
		    	case "j":
		    		searchByCapital2(strHead);
		    		break;
		    	case "k":
		    		searchByCapital2(strHead);
		    		break;
		    	case "l":
		    		searchByCapital2(strHead);
		    		break;
		    	case "m":
		    		searchByCapital2(strHead);
		    		break;
		    	case "n":
		    		searchByCapital2(strHead);
		    		break;
		    	case "o":
		    		searchByCapital2(strHead);
		    		break;
		    	case "p":
		    		searchByCapital2(strHead);
		    		break;
		    	case "q":
		    		searchByCapital2(strHead);
		    		break;
		    	case "r":
		    		searchByCapital2(strHead);
		    		break;
		    	case "s":
		    		searchByCapital2(strHead);
		    		break;
		    	case "t":
		    		searchByCapital2(strHead);
		    		break;
		    	case "u":
		    		searchByCapital2(strHead);
		    		break;
		    	case "v":
		    		searchByCapital2(strHead);
		    		break;
		    	case "w":
		    		searchByCapital2(strHead);
		    		break;
		    	case "x":
		    		searchByCapital2(strHead);
		    		break;
		    	case "y":
		    		searchByCapital2(strHead);
		    		break;
		    	case "z":
		    		searchByCapital2(strHead);
		    		break;
		    	default:
		    		alert("Please Enter Correct Name");
		    }
		}
	}
	xmlhttp.open("GET","../Remedy/ajax/side_catalog.xml" ,true);
	xmlhttp.send();
	function searchByCapital2(str){
		var g;
		var x=xmlDoc.getElementsByTagName(str);
		num = x.length - 1;
		for(var g=0; g<num ; g++){
			txt=txt + "<li>" +  x[0].childNodes[0].nodeValue + "<br/>" + "</li>";
		}
		 document.getElementById("sidehint").innerHTML=txt;
	}
}
