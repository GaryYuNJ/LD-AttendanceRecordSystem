/* JS */
Date.prototype.format = function(formatStr)   
{   
    var str = formatStr;   
    var Week = ['日','一','二','三','四','五','六'];  
  
    str=str.replace(/yyyy|YYYY/,this.getFullYear());   
    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));   
    var month=this.getMonth()+1;
    str=str.replace(/MM/,month>9?month.toString():'0' + month);   
    str=str.replace(/M/g,month);   
  
    str=str.replace(/w|W/g,Week[this.getDay()]);   
  
    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());   
    str=str.replace(/d|D/g,this.getDate());   
  
    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());   
    str=str.replace(/h|H/g,this.getHours());   
    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());   
    str=str.replace(/m/g,this.getMinutes());   
  
    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());   
    str=str.replace(/s|S/g,this.getSeconds());   
  
    return str;   
}   

$(document).ready(function(){
	  $(".sidebar-dropdown a").on('click',function(e){
	      e.preventDefault();

	      if(!$(this).hasClass("open")) {
	        // hide any open menus and remove all other classes
	        $(".sidebar #nav").slideUp(350);
	        $(".sidebar-dropdown a").removeClass("open");
	        
	        // open our new menu and add the open class
	        $(".sidebar #nav").slideDown(350);
	        $(this).addClass("open");
	      }
	      
	      else if($(this).hasClass("open")) {
	        $(this).removeClass("open");
	        $(".sidebar #nav").slideUp(350);
	      }
	  });

	});

	/* Widget close */

	$('.wclose').click(function(e){
	  e.preventDefault();
	  var $wbox = $(this).parent().parent().parent();
	  $wbox.hide(100);
	});

	/* Widget minimize */

	  $('.wminimize').click(function(e){
	    e.preventDefault();
	    var $wcontent = $(this).parent().parent().next('.widget-content');
	    if($wcontent.is(':visible')) 
	    {
	      $(this).children('i').removeClass('icon-chevron-up');
	      $(this).children('i').addClass('icon-chevron-down');
	    }
	    else 
	    {
	      $(this).children('i').removeClass('icon-chevron-down');
	      $(this).children('i').addClass('icon-chevron-up');
	    }            
	    $wcontent.toggle(500);
	  }); 
