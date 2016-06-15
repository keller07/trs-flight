(function($){
    $.fn.citypicker=function(options){
    	if ( ! $("#outer").length > 0 ) {
    		domesAirportHotList = options.domesAirportHotList;
    		domesAirportListMap = options.domesAirportListMap;
    		var $outer = $("<div id=\"outer\"></div>");
    		var $tab = $("<ul id=\"tab\"><li class=\"current\">国内热门</li><li>ABCDEFG</li>"
    				+"<li>HIJKLMN</li><li>OPQ-RST</li><li>UVW-XYZ</li></ul>");
    		var $content = $("<ul id=\"content\"></ul>");
    		
    		//加入热门
    		var $domesHot = $("<li style=\"display:block;\"></li>");
    		var $hotcities = $("<dl><dt>&nbsp;</dt><dd></dd></dl>");
    		var $ul = $("<ul class=\"cityGroup\"></ul>");
    		$.each(domesAirportHotList,function(idx,city){
    			$li = $("<li class=\"cityli\"></li>");
    			$li.html(city.cityName);
    			$li.attr("data-code",city.code);
    			$ul.append($li);
    		});
    		$hotcities.find("dd").eq(0).append($ul);
    		$domesHot.append($hotcities);
    		$content.append($domesHot);
    		
    		//加入所有
    		var $AtoG = $("<li></li>");
    		var $HtoN = $("<li></li>");
    		var $OtoT = $("<li></li>");
    		var $UtoZ = $("<li></li>");
    		$.each(domesAirportListMap,function(initial,cityList){
    			var $temp = null;
    			if (initial <= 'G'){
    				var $temp = $AtoG;
    			}else if (initial >= 'H' && initial <='N'){
    				$temp = $HtoN;
    			}else if (initial >= 'O' && initial <='T'){
    				$temp = $OtoT;
    			}else{
    				$temp = $UtoZ;
    			}
    			
    			var $dl = $("<dl></dl>");
    			var $dt = $("<dt></dt>");
    			$dt.html(initial);
    			$dl.append($dt);
    			var $dd = $("<dd><ul class=\"cityGroup\"></ul></dd>");
    			$.each(cityList,function(idx,city){
    				var $li = $("<li class=\"cityli\"></li>");
    				$li.html(city.cityName);
    				$li.attr("data-code",city.code);
    				$dd.find("ul").eq(0).append($li);
    			});
    			$dl.append($dd);
    			$temp.append($dl);
    		});
    		$content.append($AtoG);
    		$content.append($HtoN);
    		$content.append($OtoT);
    		$content.append($UtoZ);
            
    		$outer.append($tab);
    		$outer.append($content);
    		$outer.css("z-index",9999);
        	$("body").append($outer);
        	$outer.hide();
        	
        	var $li_tab = $('#tab > li');
    		var $li_content = $('#content > li');
    		$li_tab.mouseover(function(){
    			var $this = $(this);
    			var $t = $this.index();
    			$li_tab.removeClass();
    			$this.addClass('current');
    			$li_content.css('display','none');
    			$li_content.eq($t).css('display','block');
    		});
    		
    		$(".cityli").click(function(){
    			var code = $(this).attr("data-code");
    			var cityName = $(this).html();
    			var $target = $($("#outer").attr("data-target"));
    			$target.attr("data-code",code);
    			$target.val(cityName);
    			$("#outer").hide();
    		});
    		
    		$(this).focus(function(){
    			var top = $(this).offset().top+$(this).innerHeight();
    	  	  	var left = $(this).offset().left;
    	  	  	var targetid = $(this).attr("id");
    	  	  	$("#outer").css({top:top,left:left});
    	  	  	$("#outer").attr("data-target",'#'+targetid);
    	  	  	$("#outer").show();
    		});
    		
    	}
    	
    };
})(jQuery);