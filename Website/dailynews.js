window.onload = start;
var currentIndx=0;
var Images=new Array(7);

var Element=new Array(7);
function start()
{
	
	currentIndx=Math.round(Math.random()*7);
	Element[0]=document.getElementById("img1");
	Element[1]=document.getElementById("img2");
	Element[2]=document.getElementById("img3");
	Element[3]=document.getElementById("img4");
	Element[4]=document.getElementById("img5");
	Element[5]=document.getElementById("img6");
	Element[6]=document.getElementById("img7");
	Images[0]='news1.png';
	Images[1]='news2.png';
	Images[2]='news3.png';
	Images[3]='news4.png';
	Images[4]='news5.png';
	Images[5]='news6.png';
	Images[6]='news7.png';
	
	animate();
}
function animate()
{
	for(var i=0;i<7;i++)
		Element[i].style.borderColor = "white";
	Element[currentIndx].style.borderColor = "blue";
	document.getElementById("frontImg").src=Images[currentIndx];
	if(currentIndx+1==7)
		currentIndx=0;
	else
		currentIndx=currentIndx+1;
	
	
	var delay = setTimeout("animate()",3500);
	
}

