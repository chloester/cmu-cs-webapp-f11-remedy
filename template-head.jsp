<html>
<head>

<link href="./css/homepage.css" rel="stylesheet" type="text/css">
<link href="./css/slidedown.css" rel="stylesheet" type="text/css">
<link href="./css/managemainbody.css" rel="stylesheet" type="text/css">
<link href="./css/aboutmewindow.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script src="./JavaScript/showmore.js"></script>
<script src="./JavaScript/slidedown.js"></script>
<script src="./JavaScript/deleteScript.js"></script>
<script src="./JavaScript/postScript.js"></script>
<script src="./JavaScript/showmyinfo.js"></script>

<link rel="stylesheet" type="text/css" href="jquery.ad-gallery.css">
<script type="text/javascript" src="jquery.ad-gallery.js"></script>

<script type="text/javascript">
  $(function() {
    var galleries = $('.ad-gallery').adGallery();
    $('#switch-effect').change(
      function() {
        galleries[0].settings.effect = $(this).val();
        return false;
      }
    );
    $('#toggle-slideshow').click(
      function() {
        galleries[0].slideshow.toggle();
        return false;
      }
    );
    $('#toggle-description').click(
      function() {
        if(!galleries[0].settings.description_wrapper) {
          galleries[0].settings.description_wrapper = $('#descriptions');
        } else {
          galleries[0].settings.description_wrapper = false;
        }
        return false;
      }
    );
  });
  </script>

  <style type="text/css">



  .example {
    border: 1px solid #CCC;
    background: #f2f2f2;
    padding: 10px;
  }
  ul {
    list-style-image:url(list-style.gif);
  }
  pre {
    font-family: "Lucida Console", "Courier New", Verdana;
    border: 1px solid #CCC;
    background: #f2f2f2;
    padding: 10px;
  }
  code {
    font-family: "Lucida Console", "Courier New", Verdana;
    margin: 0;
    padding: 0;
  }

  #gallery {
    padding: 30px;
    background: #e1eef5;
  }
  #descriptions {
    position: relative;
    height: 50px;
    background: #EEE;
    margin-top: 10px;
    width: 640px;
    padding: 10px;
    overflow: hidden;
  }
    #descriptions .ad-image-description {
      position: absolute;
    }
      #descriptions .ad-image-description .ad-description-title {
        display: block;
      }
  </style>

</head>
<body>

<div id="pop" class="pop" style="display:none">
 <div class="pop_head"><a href="javascript:void(0);" onclick="hide('pop')">X</a></div>
 <div class="pop_body">&nbsp;&nbsp;&nbsp;&nbsp;My name is Hua Liang, an CMU ECE master student.
 This website is for course 15-437. You can contatct me <span class="blue">hualiang@andrew.cmu.edu</span></div>
</div>

	<div id="container">
		<div id="header">
			<div id="menu">
				<ul>
					<li><a href="logout.do">Home Page</a>
					</li>
					<li class="menuDiv"></li>
					<li><a href="manage.do">SNS</a>
					</li>
					<li class="menuDiv"></li>
					<li><a href="#">Game</a>
					</li>
					<li class="menuDiv"></li>
					<li><a href="mailto:hualiang@andrew.cmu.edu">Bug Report</a>
					</li>
					<li class="menuDiv"></li>
					<li><a href="javascript:void(0);" onclick="show('pop')">About Me!</a>
					</li>
				</ul>
			</div>
			<div id="banner"></div>
		</div>