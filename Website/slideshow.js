window.onload = start;
var currentIndx=0;
var Images=new Array(3);

Images[0] = 'rucker1.png';
Images[1] = 'rucker2.png';
Images[2] = 'rucker3.png';
function start()
{
	 currentIndx=0;
	 slideshow();
	
}
function slideshow() {
   
    document.getElementById("Image").src=Images[currentIndx];
    if(currentIndx+1==3)
    	    currentIndx = 0;
    else
    	    currentIndx=currentIndx+1;
   
    var delay = setTimeout("slideshow()",2000);
    
}





